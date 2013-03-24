import javax.swing.*;

import java.awt.*;

public class EditParagraphGui extends JFrame  {
	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	JTextArea text = new JTextArea(20,20);
	public EditParagraphGui(){
		super("Edit paragraph");
		setLayout(new FlowLayout());
		setSize(30, 20);
		JPanel buttons = new JPanel();
		buttons.add(save);
		buttons.add(cancel);
		text.setLineWrap( true );
		text.setWrapStyleWord( true );
		add(text);
		add(buttons);
		pack();
		show();
	}
	
}
