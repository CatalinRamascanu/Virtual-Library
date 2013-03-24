import javax.swing.*;

import java.awt.*;

public class AddParagraphGui extends JFrame  {
	JButton add = new JButton("Add");
	JButton cancel = new JButton("Cancel");
	JTextArea text = new JTextArea(20,20);
	public AddParagraphGui(){
		super("Add a new Paragraph");
		setLayout(new FlowLayout());
		setSize(30, 20);
		JPanel buttons = new JPanel();
		buttons.add(add);
		buttons.add(cancel);
		text.setLineWrap( true );
		text.setWrapStyleWord( true );
		add(text);
		add(buttons);
		pack();
		show();
	}
	
}
