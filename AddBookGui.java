import javax.swing.*;

import java.awt.*;

public class AddBookGui extends JFrame  {
	JButton addBook = new JButton("Add book");
	JButton cancel = new JButton("Cancel");
	JTextField author = new JTextField(20);
	JTextField bookName = new JTextField(20);
	JTextField isbn = new JTextField(20);
	public AddBookGui(){
		super("Add a book");
		setLayout(new GridLayout(4,1));
		JLabel authorLabel = new JLabel("Author");
		JLabel bookLabel = new JLabel("Name of the book");
		JLabel isbnLabel = new JLabel("ISBN");
		JPanel authorPanel = new JPanel();
		JPanel bookPanel = new JPanel();
		JPanel isbnPanel = new JPanel();
		authorPanel.add(authorLabel);
		authorPanel.add(author);
		bookPanel.add(bookLabel);
		bookPanel.add(bookName);
		isbnPanel.add(isbnLabel);
		isbnPanel.add(isbn);
		add(authorPanel);
		add(bookPanel);
		add(isbnPanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addBook);
		buttonPanel.add(cancel);
		add(buttonPanel);
		pack();
	}
	
}
