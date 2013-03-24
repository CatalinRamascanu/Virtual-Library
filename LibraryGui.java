import javax.swing.*;

import java.awt.*;

public class LibraryGui extends JFrame  {
	JButton createLibrary = new JButton("Create new library");
	JButton addBook = new JButton("Add book");
	JButton editBook = new JButton("Edit/View book");
	DefaultListModel books = new DefaultListModel();
	JList bookList = new JList(books);
	JTextField bookMostChapters = new JTextField();
	JTextArea longestParagraph = new JTextArea();
	public LibraryGui(){
		super("My Library");
		setLayout(new GridLayout(2,2));
		JPanel p1 = new JPanel(new GridLayout(3,1));
		p1.add(createLibrary);
		p1.add(addBook);
		p1.add(editBook);
		add(p1);
		JPanel p2 = new JPanel(new GridLayout(2,1));
		JLabel listBook = new JLabel("List of books");
		JScrollPane scrollBookList = new JScrollPane(bookList);
		p2.add(listBook);
		p2.add(scrollBookList);
		add(p2);
		JPanel p3 = new JPanel(new GridLayout(2,1));
		JLabel titleBooks = new JLabel("Book with most chapters");
		p3.add(titleBooks);
		p3.add(bookMostChapters);
		bookMostChapters.setEditable(false);
		add(p3);
		JPanel p4 = new JPanel(new GridLayout(2,1));
		JLabel titleParagraph = new JLabel("The longest paragraph");
		longestParagraph.setEditable(false);
		longestParagraph.setLineWrap(true);
		JScrollPane scrollParagraph = new JScrollPane(longestParagraph);
		p4.add(titleParagraph);
		p4.add(scrollParagraph);
		add(p4);
		pack();
	}
	
}
