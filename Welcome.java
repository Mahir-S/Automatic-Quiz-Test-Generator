import java.util.*;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import java.awt.Font;
import javax.swing.event.*;

class Welcome extends JFrame
{
	Welcome()
	{
		super("WELCOME");
		JPanel p=new JPanel(new GridLayout(0,1));
		JLabel display=new JLabel("WELCOME TO QUIZ MANAGER PORTAL");
		display.setFont(new Font("Serif",Font.BOLD,70));
		display.setForeground(Color.orange);
		p.add(display);
		p.add(new JLabel(new ImageIcon("/home/mahir/Downloads/Quiz.gif")));
		add(p);
		setVisible(true);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}

}

