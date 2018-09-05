import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*; 
class Fill_in_the_Blanks extends JFrame implements ActionListener,WindowListener
{
	QA q;
	JLabel display,note,msg,errmsg,invalid;
	JButton back1,back2,mod,adq,ada;
	JPanel p;
	JTextField enteredQuestion,enteredAnswer;
	Manage m; 
	Fill_in_the_Blanks(Manage k)
	{
		super("FILL IN THE BLANK");
		m=k;
		q=new QA();
		msg=new JLabel("Your question doesn't have three consecutive underscores.Please Re-enter the Question");
		addWindowListener(this);
		setBackground(Color.WHITE);
	}
	boolean validat(StringBuilder s)
	{
		int c=0;
		for(int i=0;i<s.length();i++)
		{
			int cnt=0;
			for(int j=0;j<3 && j+i<s.length();j++)
			{
				if(q.question.charAt(i+j)=='_')
					cnt++;
				else
					break;
			}
			if(cnt==3)
				c++;
		}
		if(c>0)
			return true;
		else
			return false;
	}
	void takeQuestion()
	{
		p=new JPanel(new GridLayout(0,1));

		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel();

		display=new JLabel("Enter the question.");
		note=new JLabel("A blank is denoted by three consecutive underscores joined together(___).This should be present is the question.");
		enteredQuestion=new JTextField(100);
		back1=new JButton("back");
		adq=new JButton("Enter Answer");

		back1.setPreferredSize(new Dimension(80,30));
		display.setPreferredSize(new Dimension(150,30));
		note.setPreferredSize(new Dimension(1000,30));
		enteredQuestion.setPreferredSize(new Dimension(80,30));
		adq.setPreferredSize(new Dimension(150,30));

		p1.add(display);
		p1.add(enteredQuestion);
		p2.add(adq);
		p3.add(note);
		p4.add(msg);
		p5.add(back1);

		p.add(new JPanel());p.add(p1);p.add(p2);p.add(p3);p.add(p4);p.add(p5);
		add(p);

		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		setVisible(true);
		msg.setVisible(false);

		adq.addActionListener(this);
		back1.addActionListener(this);
	}
	void takeAnswer()
	{
		remove(p);
		p=new JPanel(new GridLayout(0,1));

		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();

		display=new JLabel("Enter the answer");
		back2=new JButton("Back");
		enteredAnswer=new JTextField(100);
		ada=new JButton("ADD");
		invalid=new JLabel("This field can't be left empty.Please Re-enter");

		back2.setPreferredSize(new Dimension(80,30));
		display.setPreferredSize(new Dimension(150,30));
		enteredAnswer.setPreferredSize(new Dimension(80,30));
		ada.setPreferredSize(new Dimension(80,30));

		p1.add(display);
		p1.add(enteredAnswer);
		p2.add(ada);
		p3.add(back2);
		p4.add(invalid);

		p.add(new JPanel());p.add(p1);p.add(p2);p.add(p3);p.add(p4);
		add(p);

		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		setVisible(true);
		invalid.setVisible(false);

		ada.addActionListener(this);
		back2.addActionListener(this);
	}
	void modify(QA h)
	{
		q=h;
		p=new JPanel(new GridLayout(0,1));
		enteredAnswer=new JTextField(q.answer.toString());
		enteredQuestion=new JTextField(q.question.toString());
		back1=new JButton("back");
		errmsg=new JLabel("Either one of the fields has been left empty or the answer doesn't match any option.Please try again");
		mod=new JButton("Modify");
		back1.setPreferredSize(new Dimension(100,30));
		mod.setPreferredSize(new Dimension(100,30));
		JLabel disq=new JLabel("Modify the question");
		JLabel disa=new JLabel("Modify the answer"); 

		p.add(disq);p.add(enteredQuestion);
		p.add(disa);p.add(enteredAnswer);
		p.add(errmsg);
		p.add(msg);
		JPanel p1=new JPanel();
		p1.add(mod);
		JPanel p2=new JPanel();
		p2.add(back1);
		p.add(p1);
		p.add(p2);
		add(p);

		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		setVisible(true);
		msg.setVisible(false);
		errmsg.setVisible(false);

		mod.addActionListener(this);
		back1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==adq)
		{
			String s=enteredQuestion.getText();
			q.question=new StringBuilder(s);
			if(validat(q.question))
			{
				takeAnswer();
			}
			else
			{
				msg.setVisible(true);
			}
		}
		else
			if(evt.getSource()==ada)
			{
				String s=enteredAnswer.getText();
				if(s.equals(""))
					invalid.setVisible(true);
				else
				{
					q.answer=new StringBuilder(s);
					display=new JLabel("Your question has been successfully added.");
					JOptionPane.showMessageDialog(null,"Your question has been successfully added.","succes",JOptionPane.INFORMATION_MESSAGE);
					dispose();
					m.insert(q);
				}
			}
			else
				if(evt.getSource()==back2)
				{
					remove(p);
					takeQuestion();
				}
				else
					if(evt.getSource()==back1)
					{
						dispose();
						m.gui();
					}
					else
						if(evt.getSource()==mod)
						{
							msg.setVisible(false);
							if(enteredQuestion.getText().equals("") || enteredAnswer.getText().equals(""))
								errmsg.setVisible(true);
							else
							{
								errmsg.setVisible(false);
							
							if(validat(new StringBuilder(enteredQuestion.getText())))
							{
								
								JOptionPane.showMessageDialog(null,"Your question has been successfully modified.","Success",JOptionPane.INFORMATION_MESSAGE);
								q.answer=new StringBuilder(enteredAnswer.getText());
								q.question=new StringBuilder(enteredQuestion.getText());
								m.v.set(m.qno-1,q);
								dispose();
								m.gui();
							}
							else
								msg.setVisible(true);
							}
						}
	}
	public void windowClosing(WindowEvent e)
	{
	    setVisible(false);
	    dispose();
	    m.gui();
	}
	public void windowOpened(WindowEvent evt) { }
	public void windowClosed(WindowEvent evt) { }
	public void windowIconified(WindowEvent evt) { }
	public void windowDeiconified(WindowEvent evt) { }
	public void windowActivated(WindowEvent evt) {setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); }
	public void windowDeactivated(WindowEvent evt) { }
} 