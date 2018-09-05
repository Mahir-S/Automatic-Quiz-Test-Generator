import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*;
class Mcq extends JFrame implements ActionListener,WindowListener
{
	QA q;
	JButton ad,back,back1,mod,adq;
	JLabel display,errmsg,success,not;
	JPanel p;
	JTextField enteredQuestion,enteredAnswer,optA,optB,optC,optD;
	Manage m;
	Mcq(Manage k)
	{
		super("MULTIPLE CHOICE QUESTIONS(MCQ)");
		m=k;
		q=new QA();
		addWindowListener(this);
		setBackground(Color.WHITE);
	}
	void takeQuestion()
	{
		p=new JPanel(new GridLayout(0,1));

		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();

		display=new JLabel("Enter the question");
		enteredQuestion=new JTextField(100);
		back=new JButton("back");
		not=new JLabel("This can't be left empty.Please try again.");
		adq=new JButton("Enter Answer and options");

		display.setPreferredSize(new Dimension(500,40));
		enteredQuestion.setPreferredSize(new Dimension(500,40));
		adq.setPreferredSize(new Dimension(300,40));
		back.setPreferredSize(new Dimension(300,40));

		p1.add(display);
		p1.add(enteredQuestion);
		p2.add(adq);
		p3.add(back);
		p4.add(not);
		p.add(new JPanel());p.add(p1);p.add(p2);p.add(p3);p.add(p4);
		add(p);

		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		setVisible(true);
		not.setVisible(false);

		adq.addActionListener(this);
		back.addActionListener(this);
	}
	void takeAnswer()
	{
		remove(p);
		p=new JPanel(new GridLayout(0,1));

		display=new JLabel("Enter the answer and the options");
		back1=new JButton("back");
		enteredAnswer=new JTextField(100);
		errmsg=new JLabel("Either one of the fields has been left empty or the answer doesn't match any option.Please try again");
		ad=new JButton("Add");
		JLabel disans=new JLabel("Answer");
		JLabel disA=new JLabel("OptionA");
		JLabel disB=new JLabel("OptionB");
		JLabel disC=new JLabel("OptionC");
		JLabel disD=new JLabel("OptionD");
		optA=new JTextField(100);
		optB=new JTextField(100);
		optC=new JTextField(100);
		optD=new JTextField(100);

		p.add(display);
		p.add(disA);p.add(optA);
		p.add(disB);p.add(optB);
		p.add(disC);p.add(optC);
		p.add(disD);p.add(optD);
		p.add(disans);p.add(enteredAnswer);
		p.add(errmsg);
		JPanel p1=new JPanel(new FlowLayout());
		back1.setPreferredSize(new Dimension(80,30));
		ad.setPreferredSize(new Dimension(80,30));
		p1.add(back1);
		
		JPanel p2=new JPanel();
		p2.add(ad);
		p.add(p2);
		p.add(p1);
		add(p);

		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		setVisible(true);

		ad.addActionListener(this);
		back1.addActionListener(this);
		errmsg.setVisible(false);

	}
	boolean validat()//left to implement if two options are same.
	{
		boolean ch=true;
		String ans=(enteredAnswer.getText());
		String A=(optA.getText());
		String B=(optB.getText());
		String C=(optC.getText());
		String D=(optD.getText());
		if(ans.equals("") || A.equals("") || B.equals("") || C.equals("") || D.equals(""))
			ch=false;
		if(ans.equals(A) || ans.equals(B) || ans.equals(C) || ans.equals(D))//enter answer as an option A,B and so on.....see 
			;
		else
			ch=false;
		if(ch)
		{
			q.answer=new StringBuilder(ans);
			q.optionA=new StringBuilder(A);
			q.optionB=new StringBuilder(B);
			q.optionC=new StringBuilder(C);
			q.optionD=new StringBuilder(D);
			return ch;
		}
		else
			return ch;

	}
	void modify(QA h)
	{
		q=h;
		p=new JPanel(new GridLayout(0,1));
		display=new JLabel("You may modify the question.");
		back=new JButton("back");
		enteredQuestion=new JTextField(q.question.toString());
		JLabel disq=new JLabel("Question");
		JLabel disans=new JLabel("Answer");
		JLabel disA=new JLabel("OptionA");
		JLabel disB=new JLabel("OptionB");
		JLabel disC=new JLabel("OptionC");
		JLabel disD=new JLabel("OptionD");
		optA=new JTextField(q.optionA.toString());
		optB=new JTextField(q.optionB.toString());
		optC=new JTextField(q.optionC.toString());
		optD=new JTextField(q.optionD.toString());
		errmsg=new JLabel("Either one of the fields has been left empty or the answer doesn't match any option.Please try again");
		enteredAnswer=new JTextField(q.answer.toString());
		mod=new JButton("Modify");

		p.add(display);
		p.add(disq);p.add(enteredQuestion);
		p.add(disA);p.add(optA);
		p.add(disB);p.add(optB);
		p.add(disC);p.add(optC);
		p.add(disD);p.add(optD);
		p.add(disans);p.add(enteredAnswer);
		p.add(errmsg);

		back.setPreferredSize(new Dimension(100,30));
		mod.setPreferredSize(new Dimension(100,30));
		JPanel p1=new JPanel();
		p1.add(mod);
		JPanel p2=new JPanel();
		p2.add(back);
		p.add(p1);
		p.add(p2);
		add(p);

		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		setVisible(true);
		errmsg.setVisible(false);

		back.addActionListener(this);
		mod.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==adq)
		{
			if(enteredQuestion.getText().equals(""))
				not.setVisible(true);
			else
			{
				q.question=new StringBuilder(enteredQuestion.getText());
				takeAnswer();
			}
		}
		else
			if(evt.getSource()==ad)
			{
				boolean d=validat();
				if(d)
				{
					JOptionPane.showMessageDialog(null,"Your Question has been successfully added","success",JOptionPane.INFORMATION_MESSAGE);
					dispose();
					m.insert(q);
				}
				else
				{
					errmsg.setVisible(true);
				}
			}
			else
			if(evt.getSource()==back)
			{
				dispose();
				m.gui();
			}
			else
			if(evt.getSource()==back1)
			{
				remove(p);
				takeQuestion();
			}
			else
				if(evt.getSource()==mod)
				{
					boolean d=validat();
					if(enteredQuestion.getText().equals(""))
						d=false;
					if(d)
					{
						q.question=new StringBuilder(enteredQuestion.getText());
						JOptionPane.showMessageDialog(null,"Your Question has been successfully modified","success",JOptionPane.INFORMATION_MESSAGE);
						m.v.set(m.qno-1,q);
						dispose();
						m.gui();
					}
					else
					{
						errmsg.setVisible(true);
					}
				}
	}
	public void windowOpened(WindowEvent evt) { }
	public void windowClosed(WindowEvent evt) { }
	public void windowIconified(WindowEvent evt) { }
	public void windowDeiconified(WindowEvent evt) { }
	public void windowActivated(WindowEvent evt) {setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); }
	public void windowDeactivated(WindowEvent evt) { }
	public void windowClosing(WindowEvent e)
	{
	    setVisible(false);
	    dispose();
	    m.gui();
	}
}