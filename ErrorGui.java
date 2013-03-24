import javax.swing.*;

import java.awt.*;

public class ErrorGui extends JFrame  {
	
	public ErrorGui(String message){
		super("Error!");
		setLayout(new FlowLayout());
		JLabel errorLabel = new JLabel(message);
		add(errorLabel);
		pack();
		show();
	}
	
}
