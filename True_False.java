import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*;

class True_False extends JFrame implements ActionListener,WindowListener
{
	QA q;
	JLabel display,errmsg,invalid,not;
	JButton back1,back2,mod,adq,ada;
	JPanel p;
	JTextField enteredQuestion,enteredAnswer; 
	Manage m;
	True_False(Manage l)
	{
		super("TRUE/FALSE");
		m=l;
		q=new QA();
		q.optionA=new StringBuilder("True/False");
		addWindowListener(this);
		setBackground(Color.WHITE);
		setVisible(false);
	}
	boolean validat(String f)
	{
		f=f.toLowerCase();
		if(f.equals("true") || f.equals("false"))
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
		display=new JLabel("Enter the question.");
		invalid=new JLabel("No question entered.This field cannot be left empty");
		enteredQuestion=new JTextField(100);
		adq=new JButton("Enter Answer");
		back1=new JButton("back");

		adq.setPreferredSize(new Dimension(200,40));
		back1.setPreferredSize(new Dimension(100,40));
		display.setPreferredSize(new Dimension(200,30));
		enteredQuestion.setPreferredSize(new Dimension(80,30));

		p1.add(display);
		p1.add(enteredQuestion);
		p2.add(adq);
		p3.add(back1);
		p4.add(invalid);
		p.add(new JPanel());
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		add(p);
		setVisible(true);
		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		invalid.setVisible(false);
		back1.addActionListener(this);
		adq.addActionListener(this);
	}
	void takeAnswer(QA ob)
	{
		remove(p);
		p=new JPanel(new GridLayout(0,1));
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();

		display=new JLabel("Enter the answer");
		back2=new JButton("Back");
		ada=new JButton("ADD");
		invalid=new JLabel("No answer entered.This field cannot be left empty");
		not=new JLabel("Answer should be either 'true' or 'false' and nothing else.Please try again.");
		enteredAnswer=new JTextField(100);
		
		ada.setPreferredSize(new Dimension(200,40));
		back2.setPreferredSize(new Dimension(100,40));
		display.setPreferredSize(new Dimension(200,30));
		enteredAnswer.setPreferredSize(new Dimension(80,30));

		p1.add(display);
		p1.add(enteredAnswer);
		p2.add(ada);
		p3.add(back2);
		p4.add(invalid);
		p4.add(not);
		p.add(new JPanel());
		p.add(p1);p.add(p2);p.add(p3);p.add(p4);
		add(p);

		setVisible(true);
		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		invalid.setVisible(false);
		not.setVisible(false);

		back2.addActionListener(this);
		ada.addActionListener(this);
	}
	void modify(QA m)
	{
		q=m;
		p=new JPanel(new GridLayout(0,1));
		display=new JLabel("Modify the question");
		enteredQuestion=new JTextField(m.question.toString());
		JLabel display2=new JLabel("Modify the answer");
		enteredAnswer=new JTextField(m.answer.toString());
		errmsg=new JLabel("Either one of the fields has been left empty or the answer isn't 'true' or 'false'.Please try again");
		mod=new JButton("Modify");
		back1=new JButton("back");

		back1.setPreferredSize(new Dimension(100,30));
		mod.setPreferredSize(new Dimension(100,30));

		p.add(display);
		p.add(enteredQuestion);
		p.add(display2);
		p.add(enteredAnswer);
		p.add(errmsg);
		JPanel p1=new JPanel();
		p1.add(mod);
		JPanel p2=new JPanel();
		p2.add(back1);
		p.add(p1);
		p.add(p2);
		add(p);

		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH );
		setVisible(true);

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
			if(s.equals(""))
					invalid.setVisible(true);
			else
				takeAnswer(q);			
		}
		else
			if(evt.getSource()==ada)
			{
				String s=enteredAnswer.getText();
				if(s.equals(""))
					invalid.setVisible(true);
				else
				{
					if(validat(s))
					{
						JOptionPane.showMessageDialog(null,"Your question has been successfully added.","success",JOptionPane.INFORMATION_MESSAGE);
						q.answer=new StringBuilder(s.toLowerCase());
						dispose();
						m.insert(q);
					}
					else
						not.setVisible(true);
					
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
							if(enteredQuestion.getText().equals("") || enteredAnswer.getText().equals("") || validat(enteredAnswer.getText())==false)
							{
								errmsg.setVisible(true);
							}
							else
							{
								
								q.question=new StringBuilder(enteredQuestion.getText());
								q.answer=new StringBuilder(enteredAnswer.getText().toLowerCase());
								JOptionPane.showMessageDialog(null,"Your question has been successfully modifed.","success",JOptionPane.INFORMATION_MESSAGE);
								m.v.set(m.qno-1,q);
								dispose();
								m.gui();
								
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