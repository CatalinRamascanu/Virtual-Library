import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import javax.swing.DefaultListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import element.*;


public class Main  {
	static Library library;
	static LibraryGui libraryGui = new LibraryGui();
	static AddBookGui addButtonGui;
	static EditViewGui editGui;
	static Book actualBook,newActualBook;
	static boolean libExistance = true;
	static String[] bookContext;
	static int indexSelectedBook;
	static int pageNumber;
	public static void main(String args[]) throws ClassNotFoundException,BadLocationException{
		
		libraryGui.setVisible(true);
		if ( loadLibrary() == null){
			new ErrorGui("Atention! There is no library in the database. Please create a new one");
			libExistance = false;
		}
		
		if (libExistance == false){
			libraryGui.addBook.setVisible(false);
			libraryGui.bookList.setVisible(false);
			libraryGui.editBook.setVisible(false);
			libraryGui.longestParagraph.setVisible(false);
		}
		else{
			library = loadLibrary();
			for (int i = 0; i < library.size(); i++)
				libraryGui.books.addElement(library.getBook(i).getName());
			libraryGui.bookMostChapters.setText(bookMostChapters(library));
			libraryGui.longestParagraph.setText(longestParagraph(library));
			
		}
		libraryGui.createLibrary.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( libExistance == false){
					library = new Library();
					libraryGui.addBook.setVisible(true);
					libraryGui.bookList.setVisible(true);
					libraryGui.createLibrary.setVisible(true);
					libraryGui.editBook.setVisible(true);
					libraryGui.longestParagraph.setVisible(true);
					libExistance = true;
					
				}
				else 
					new ErrorGui("A library already exists.");
			}
		});
		
		libraryGui.addBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				libraryGui.setVisible(false);
				addButtonGui = new AddBookGui();
				addButtonGui.setVisible(true);
				addButtonGui.addBook.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e)  {
						String author = addButtonGui.author.getText();
						String book = addButtonGui.bookName.getText();
						String isbn = addButtonGui.isbn.getText();
						if (author.length() == 0 || book.length() == 0 || isbn.length() == 0)
							new ErrorGui("Te rog completeaza toate campurile");
						else{
							int ok = 0;
							for (int i = 0 ; i<isbn.length(); i++)
								if (isbn.charAt(i)> '9' || isbn.charAt(i) < '0'){
									new ErrorGui("Te rog sa introduci un ISBN valid");
									ok = 1;
									break;
								}
							if (ok == 0){
								Book new_book = new Book(author,book,isbn);
								library.add(new_book);
								libraryGui.books.addElement(book);
								libraryGui.setVisible(true);
								addButtonGui.dispose();
								try {
									saveLibrary(library);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								}
							
							}
					}
				});
				addButtonGui.cancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						libraryGui.setVisible(true);
						addButtonGui.dispose();
					}
				});
			
			}
		});
		
		libraryGui.editBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (libraryGui.bookList.getSelectedIndex() == -1)
					new ErrorGui("You have not selected a book");
				else{
				libraryGui.setVisible(false);
				indexSelectedBook = libraryGui.bookList.getSelectedIndex();
				actualBook = library.getBook(libraryGui.bookList.getSelectedIndex());
				newActualBook = new Book(actualBook.getAuthor(),actualBook.getName(),actualBook.getISBN());
				for (int i = 0; i < actualBook.getAllChapters().size(); i++){
					Chapter chapter = new Chapter(actualBook.getAllChapters().getElement(i));
					newActualBook.add(chapter);
				}
				editGui = new EditViewGui(actualBook);
				editGui.setVisible(true);
				editGui.view.save.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						actualBook = newActualBook;
						actualBook.renameAuthor(editGui.view.author.getText());
						actualBook.renameBook(editGui.view.bookName.getText());
						actualBook.changeISBN(editGui.view.isbn.getText());
						library.removeAtIndex(indexSelectedBook);
						library.addAtIndex(indexSelectedBook, actualBook);
						try {
							saveLibrary(library);
						} catch (IOException e) {
							e.printStackTrace();
						}
						int index = libraryGui.bookList.getSelectedIndex();
						DefaultListModel new_list = new DefaultListModel();
						for (int i = 0; i<libraryGui.books.size();i++)
							if (i == index)
								new_list.addElement(actualBook.getName());
							else
								new_list.addElement(libraryGui.books.get(i));
						libraryGui.books = new_list;
						libraryGui.bookList.setModel(libraryGui.books);
						actualBook = library.getBook(indexSelectedBook);
						
					}
				});
				editGui.view.chapterList.addListSelectionListener( new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						editGui.view.paragraphs.clear();
						int index = editGui.view.chapterList.getSelectedIndex();
						if (index != -1){
						Chapter chapter = newActualBook.getChapter(index);
						int length = chapter.size();
						for (int i = 0 ; i<length; i++)
							editGui.view.paragraphs.addElement(chapter.getParagraph(i).getText().substring(0, 9)+"...");
						}
						
					}
				});
				
				editGui.view.addC.addActionListener(new ActionListener() {
					AddChapterGui add_gui;
					@Override
					public void actionPerformed(ActionEvent e) {
						add_gui = new AddChapterGui();
						add_gui.add.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								String chapter_name = add_gui.name.getText();
								newActualBook.add(new Chapter(chapter_name));
								editGui.view.chapters.addElement(chapter_name);
								add_gui.dispose();
							}
						});
						add_gui.cancel.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								add_gui.dispose();
							}
						});
					}
				});
				editGui.view.deleteC.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int index = editGui.view.chapterList.getSelectedIndex();
						if (index != -1)
							newActualBook.remove(index);
						editGui.view.chapters.clear();
						for (int i = 0; i< newActualBook.size();i++)
								editGui.view.chapters.addElement(newActualBook.getChapter(i).getName());
						
					}
				});

				editGui.view.addP.addActionListener(new ActionListener() {
					AddParagraphGui add_gui;
					Chapter chapter;
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int chapter_index = editGui.view.chapterList.getSelectedIndex();
						if (chapter_index == -1)
							new ErrorGui("No chapter selected");
						else{
						
						add_gui = new AddParagraphGui();
						chapter = newActualBook.getChapter(chapter_index);
						add_gui.add.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
							String text = add_gui.text.getText();
							if (text.length() <=9)
								new ErrorGui("A new paragraph requires at least 10 characters");
							else{
							Paragraph new_para = new Paragraph(text);
							int para_index = editGui.view.paragraphList.getSelectedIndex();
							if (para_index == -1)
								chapter.add(new_para);
							else
								chapter.add(para_index,new_para);
							
							editGui.view.paragraphs.clear();
							int length = chapter.size();
							for (int i = 0 ; i<length; i++)
								editGui.view.paragraphs.addElement(chapter.getParagraph(i).getText().substring(0, 9)+"...");
							add_gui.dispose();
							}
							}
						});
						add_gui.cancel.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								add_gui.dispose();
							}
						});
						
						}
					}
				});
				
				editGui.view.paragraphList.addMouseListener(new MouseAdapter() {
					EditParagraphGui edit_para;   
					int para_index;
					int chapter_index;
					public void mouseClicked(MouseEvent e) {
						      if (e.getClickCount() == 2) {
						    	 para_index = editGui.view.paragraphList.getSelectedIndex();
						    	 chapter_index = editGui.view.chapterList.getSelectedIndex();
						    	 if (para_index != -1){
						    		 edit_para = new EditParagraphGui();
						    		 edit_para.text.setText(newActualBook.getChapter(chapter_index).getParagraph(para_index).getText());
						    		 edit_para.save.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent arg0) {
											newActualBook.getChapter(chapter_index).remove(para_index);
											newActualBook.getChapter(chapter_index).add(para_index, new Paragraph(edit_para.text.getText()));
											editGui.view.paragraphs.clear();
											int length = newActualBook.getChapter(chapter_index).size();
											for (int i = 0 ; i<length; i++)
												editGui.view.paragraphs.addElement(newActualBook.getChapter(chapter_index).getParagraph(i).getText().substring(0, 9)+"...");
											edit_para.dispose();
										}
									});
						    	    
						    		edit_para.cancel.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent e) {
											edit_para.dispose();
										}
									}); 
						    	 }
						      }
						   }
						});
				
				editGui.view.deleteP.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int para_index = editGui.view.paragraphList.getSelectedIndex();
						int chapter_index = editGui.view.chapterList.getSelectedIndex();
						if (para_index != -1 && chapter_index != -1)
							newActualBook.getChapter(chapter_index).remove(para_index);
						editGui.view.paragraphs.clear();
						for (int i = 0; i < newActualBook.getChapter(chapter_index).size(); i++){
							String text = newActualBook.getChapter(chapter_index).getParagraph(i).getText();
							editGui.view.paragraphs.addElement(text.substring(0, 9)+"....");
						}
					}
				});
				
				editGui.view.exit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						editGui.dispose();
						libraryGui.bookMostChapters.setText(bookMostChapters(library));
						libraryGui.longestParagraph.setText(longestParagraph(library));
						libraryGui.setVisible(true);
					}
				});
				
				editGui.tabbedPane.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent arg0) {
						editGui.view.authorLabel2.setText(editGui.view.author.getText());
						editGui.view.bookLabel2.setText(editGui.view.bookName.getText());
						editGui.view.isbnLabel2.setText(editGui.view.isbn.getText());
						int bookSize = newActualBook.size();
						bookContext = new String[bookSize + 1];
						String header = "\t " + newActualBook.getName() + " by " + newActualBook.getAuthor();
						String dots = "..................................................................  ";
						String text = "";
						String content ="\t" + header + "\n\t\t\t Content \n\n";
						for (int i= 0; i<bookSize; i++){
							content += "# " + newActualBook.getChapter(i).getName() + dots + (i+1) + "\n"; 
							text +=header + " | " + "Chapter " + (i+1) +": "  + newActualBook.getChapter(i).getName() +"\n\n";
							int chapterSize = newActualBook.getChapter(i).size();
								for (int j = 0; j < chapterSize; j++)
									text += "      " + newActualBook.getChapter(i).getParagraph(j).getText() +"\n\n";
							text += "\n\n\t\t\t Page number " + (i+2);
							bookContext[i+1] = text;
							text = "";
						}
						content += "\n\n\t\t\t Page number 1"; 
						bookContext[0] = content;
						pageNumber = 0;
						editGui.view.context.setText(bookContext[0]);
					}
				});
				
				editGui.view.nextPage.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if ( pageNumber < bookContext.length -1)
							editGui.view.context.setText(bookContext[++pageNumber]);
						else {
							pageNumber = 0;
							editGui.view.context.setText(bookContext[pageNumber]);
						}	
					}
				});
				
				editGui.view.previousPage.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if ( pageNumber > 0)
							editGui.view.context.setText(bookContext[--pageNumber]);
						else {
							pageNumber = bookContext.length - 1;
							editGui.view.context.setText(bookContext[pageNumber]);
						}
					}
				});
				
				editGui.view.find.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String word = editGui.view.wordSearch.getText();
						String text = editGui.view.context.getText();
						Highlighter h = editGui.view.context.getHighlighter();
						h.removeAllHighlights();
						int index = text.indexOf(word);
						while ( index >= 0 ) {
						    int len = word.length();
						    try {
								h.addHighlight(index, index+len, DefaultHighlighter.DefaultPainter);
							} catch (BadLocationException e) {
								e.printStackTrace();
							}
						    index = text.indexOf(word, index+len);
						}
						
					}
				});
				
				editGui.view.change.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String font_name = "" + editGui.view.fontList.getSelectedValue();	
						Font font = editGui.view.context.getFont();
						int style = font.getStyle();
						int size = font.getSize();
						Font new_font = new Font(font_name,style,size);
						editGui.view.context.setFont(new_font);
					}
				});
				editGui.view.increaseSize.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Font font = editGui.view.context.getFont();
						String font_name = font.getFontName();	
						int style = font.getStyle();
						int size = font.getSize() + 1;
						Font new_font = new Font(font_name,style,size);
						editGui.view.context.setFont(new_font);	
					}
				});
				
				editGui.view.decreaseSize.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Font font = editGui.view.context.getFont();
						String font_name = font.getFontName();	
						int style = font.getStyle();
						int size = font.getSize() - 1;
						if (size >= 1){
						Font new_font = new Font(font_name,style,size);
						editGui.view.context.setFont(new_font);
						}
					}
				});
				
				}	
			
			}
		});
		
		
	}

	public static Library loadLibrary() {
		try{
			ObjectInputStream readLib = new ObjectInputStream(new FileInputStream("data.xml"));
			Library library = (Library) readLib.readObject();
			readLib.close();
			return library;
		}
		catch (Exception e) {
			return  null;
		}

	}
	
	 public static void saveLibrary(Library library) throws IOException {

		 ObjectOutputStream writeLib = new ObjectOutputStream(new FileOutputStream("data.xml"));
		 writeLib.writeObject(library);
		 writeLib.close();
	 }
	
	 public static String bookMostChapters(Library library){
		 int max_chapters = 0;
		 String book_most_chapters="";
			for(int i = 0 ; i < library.size(); i++)
				if (max_chapters < library.getBook(i).size()){
					book_most_chapters = library.getBook(i).getName();
					max_chapters = library.getBook(i).size();
				}
		return book_most_chapters;	
	 }
	 
	 public static String longestParagraph(Library library){
		    int max_words = 0;
			String para_text="";
			for(int i = 0; i < library.size(); i++)
				for (int j = 0; j < library.getBook(i).size(); j++)
					for (int k =0; k < library.getBook(i).getChapter(j).size();k++){
						int nr_words = 0;
						for (int l = 0; l < library.getBook(i).getChapter(j).getParagraph(k).size(); l++)
							nr_words += library.getBook(i).getChapter(j).getParagraph(k).getElement(l).size();
						if (max_words < nr_words){
							max_words = nr_words;
							para_text = library.getBook(i).getChapter(j).getParagraph(k).getText();
						}
					}
			return para_text;
	 }
}
