import javax.swing.*;

import java.awt.*;

public class AddChapterGui extends JFrame  {
	JButton add = new JButton("Add");
	JButton cancel = new JButton("Cancel");
	JTextField name = new JTextField(20);
	public AddChapterGui(){
		super("New Chapter");
		setLayout(new FlowLayout());
		JLabel label = new JLabel("Add a new chapter");
		add(label);
		add(name);
		add(add);
		add(cancel);
		pack();
		show();
	}
	
}
