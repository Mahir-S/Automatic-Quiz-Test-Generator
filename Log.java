import java.util.*;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
class Log extends JFrame implements ActionListener,WindowListener
{
	JPanel p;
	JTextField user;
	JPasswordField pass;
	JButton login;
	JLabel invalid;

	Log()
	{

		super("LOGIN");
		//UIManager.put("Button.font", new FontUIResource(new Font ("Serif", Font.BOLD, 16)));
		UIManager.getLookAndFeelDefaults()
        .put("defaultFont", new Font("Serif", Font.BOLD, 16));

        UIManager.put("TextField.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("PasswordField.font", new Font("Arial", Font.BOLD, 14));
		addWindowListener(this);
	}
	void gui()
	{
		p=new JPanel(new GridLayout(0,1));

		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();

		JLabel enterUserName=new JLabel("Username: ");
		user=new JTextField(70);
		JLabel enterPassword=new JLabel("Password: ");
		pass=new JPasswordField(70);
		login=new JButton("Login");
		invalid=new JLabel("Invalid Credentials.Please try again.");
		JCheckBox ch=new JCheckBox("show",false);

		user.setPreferredSize(new Dimension(100,35));
		enterUserName.setPreferredSize(new Dimension(100,35));
		enterPassword.setPreferredSize(new Dimension(100,35));
		pass.setPreferredSize(new Dimension(100,35));
		login.setPreferredSize(new Dimension(100,35));
		invalid.setPreferredSize(new Dimension(500,35));

		p1.add(enterUserName);
		p1.add(user);
		p2.add(enterPassword);
		p2.add(pass);p2.add(ch);
		p3.add(login);
		p4.add(invalid);
		p.add(new JPanel());
		p.add(new JPanel());
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		add(p);

		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setVisible(true);

		invalid.setVisible(false);
		pass.setEchoChar('*');
		login.addActionListener(this);
		pass.addActionListener(this);
		ch.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange()==1)
				{
					pass.setEchoChar((char)0);
				}
				else
					pass.setEchoChar('*');
			}
		});
	}
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==login || evt.getSource()==pass)
			{
				char[] ar={'M','8','3'};
				if(user.getText().equals("mahir") && Arrays.equals(pass.getPassword(),ar))
				{
					Subject obj=new Subject();
					obj.enterSubject();
					dispose();
				}
				else
				{
					invalid.setVisible(true);
				}
			}
	}
	public void windowClosing(WindowEvent e){System.exit(0);}
	public void windowOpened(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}

	public static void main(String args[])
	{
		Welcome obj=new Welcome();
		try{
		Thread.currentThread().sleep(2000);
		}
		catch(Exception e){}
		obj.dispose();
		Log ob=new Log();
		ob.gui();
	}

} 
