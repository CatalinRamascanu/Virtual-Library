package viewBook;

import java.awt.*;

import javax.swing.*;


import element.Book;

public class ViewMode extends AbstractViewMode {
	public JTextField author = new JTextField(20);
	public JTextField bookName = new JTextField(20);
	public JTextField isbn = new JTextField(20);
	public DefaultListModel chapters = new DefaultListModel();
	public JList chapterList;
	public DefaultListModel paragraphs = new DefaultListModel();
	public JList paragraphList = new JList(paragraphs);
	public JButton addP = new JButton("Add Paragraph");
	public JButton deleteP = new JButton("Delete Paragraph");
	public JButton save = new JButton("Save Modifications");
	public JButton addC = new JButton("Add Chapter");
	public JButton deleteC = new JButton("Delete Chapter");
	public JButton exit = new JButton("Exit Edit/View Mode");
	public JLabel authorLabel2;
	public JLabel bookLabel2;
	public JLabel isbnLabel2;
	public ViewMode(Book book){
		author.setText(book.getAuthor());
		bookName.setText(book.getName());
		isbn.setText(book.getISBN());
		authorLabel2 = new JLabel(book.getAuthor());
		bookLabel2 = new JLabel(book.getName());
		isbnLabel2 = new JLabel(book.getISBN());
		for (int i = 0; i < book.size(); i++)
			chapters.addElement(book.getChapter(i).getName());
		chapterList = new JList(chapters);
	}
	public JPanel authorPanel() {
		JPanel mainPanel = new JPanel(new GridLayout(9,1));
		JPanel authorPanel = new JPanel();
		JPanel bookPanel = new JPanel();
		JPanel isbnPanel = new JPanel();
		JLabel authorLabel = new JLabel("Author");
		JLabel bookLabel = new JLabel("Name of the book");
		JLabel isbnLabel = new JLabel("ISBN");
		authorPanel.add(authorLabel);
		authorPanel.add(author);
		bookPanel.add(bookLabel);
		bookPanel.add(bookName);
		isbnPanel.add(isbnLabel);
		isbnPanel.add(isbn);
		JLabel chapterLabel = new JLabel("List of chapters:");	
		JScrollPane scrollChapter = new JScrollPane(chapterList);
		JLabel paragraphLabel = new JLabel("List of paragraphs for the selected chapter:");	
		JScrollPane scrollParagraph = new JScrollPane(paragraphList);
		JPanel buttonsChapter = new JPanel();
		buttonsChapter.add(addC);
		buttonsChapter.add(deleteC);
		JPanel buttonsParagraph = new JPanel();
		buttonsParagraph.add(addP);
		buttonsParagraph.add(deleteP);
		buttonsParagraph.add(save);
		buttonsParagraph.add(exit);
		mainPanel.add(authorPanel);
		mainPanel.add(bookPanel);
		mainPanel.add(isbnPanel);
		mainPanel.add(chapterLabel);
		mainPanel.add(scrollChapter);
		mainPanel.add(buttonsChapter);
		mainPanel.add(paragraphLabel);
		mainPanel.add(scrollParagraph);
		mainPanel.add(buttonsParagraph);
		return mainPanel;
		}
	public JTextArea context = new JTextArea(20, 60);
	public JButton find = new JButton("Find");
	public JTextField wordSearch = new JTextField(20);
	public JButton change = new JButton("Change Font");
	public JButton increaseSize = new JButton("Increase size of text");
	public JButton decreaseSize = new JButton("Decrease size of text");
	public JButton nextPage = new JButton("Next Page");
	public JButton previousPage = new JButton("Previous Page");
	public DefaultListModel font = new DefaultListModel();
	public JList fontList = new JList(font);
	public JPanel publishPanel() {
		JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.setSize(200, 200);
		JPanel authorPanel = new JPanel();
		JPanel bookPanel = new JPanel();
		JPanel isbnPanel = new JPanel();
		JLabel authorLabel = new JLabel("Author:  ");
		JLabel bookLabel = new JLabel("Name of the book:  ");
		JLabel isbnLabel = new JLabel("ISBN:  ");
		authorPanel.add(authorLabel);
		authorPanel.add(authorLabel2);
		bookPanel.add(bookLabel);
		bookPanel.add(bookLabel2);
		isbnPanel.add(isbnLabel);
		isbnPanel.add(isbnLabel2);
		context.setEditable(false);
		context.setLineWrap(true);
		JScrollPane scrollContext = new JScrollPane(context);
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for ( int i = 0; i < fonts.length ; i++ )
			font.addElement(fonts[i]);
	       JScrollPane scrollFont = new JScrollPane(fontList);
		mainPanel.add(authorPanel);
		mainPanel.add(bookPanel);
		mainPanel.add(isbnPanel);
		mainPanel.add(scrollContext);
		mainPanel.add(find);
		mainPanel.add(wordSearch);
		mainPanel.add(scrollFont);
		mainPanel.add(change);
		mainPanel.add(increaseSize);
		mainPanel.add(decreaseSize);
		mainPanel.add(previousPage);
		mainPanel.add(nextPage);
		return mainPanel;
	}

}
