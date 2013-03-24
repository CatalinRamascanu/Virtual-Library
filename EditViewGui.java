import java.awt.*;


import javax.swing.*;

import element.Book;

import viewBook.ViewMode;


public class EditViewGui extends JFrame {
	ViewMode view;
	JTabbedPane tabbedPane;
	public EditViewGui(Book book){
		setTitle( "Edit/View Book " + book.getName() );
		setSize( 700, 800 );
		setMaximumSize(getSize());
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
		view = new ViewMode(book);
		JPanel editPanel = view.authorPanel();
		JPanel publishPanel = view.publishPanel();
        tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Author Mode", editPanel);
		tabbedPane.addTab("Publish Mode", publishPanel);
		topPanel.add( tabbedPane, BorderLayout.CENTER );
		
	}
}
