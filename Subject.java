import java.util.*;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*; 
class Subject extends JFrame implements WindowListener,ActionListener
{
	JPanel p;
	JButton math,chem,phy,back;
	Subject()
	{
		super("SELECT SUBJECT");
		addWindowListener(this);
		setBackground(Color.WHITE);
		setVisible(false);
	}
	void enterSubject()
	{
		p=new JPanel(new GridLayout(0,1));

		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();

		JLabel display=new JLabel("Choose the subject");
		math=new JButton("MATH");
		chem=new JButton("CHEMISTRY");
		phy=new JButton("PHYSICS");
		back=new JButton("Back");
		p.add(display);
		p.add(math);
		p.add(chem);
		p.add(phy);
		p.add(back);
		add(p);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setVisible(true);
		math.addActionListener(this);
		chem.addActionListener(this);
		phy.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent evt)
	{
		this.dispose();
		if(evt.getSource()==math)
		{
			Manage ob=new Manage("math.ser");
			setVisible(false);
			ob.gui();
			dispose();
		}
		else
			if(evt.getSource()==phy)
			{
				Manage ob=new Manage("phy.ser");
				this.dispose();
				ob.gui();
			}
			else
				if(evt.getSource()==chem)
				{
					Manage ob=new Manage("chem.ser");
					this.dispose();
					ob.gui();
				}
				else
				{
					dispose();
					Log ob=new Log();
					ob.gui();
				}
	}

	public void windowClosing(WindowEvent e)
	{
		setVisible(false);
		System.exit(0);
		dispose();

	}
	public void windowClosed(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
}