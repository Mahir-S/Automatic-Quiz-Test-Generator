import java.util.*;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*; 
class Manage extends JFrame implements ActionListener,WindowListener
{

	File file;
	ArrayList<QA> v=new ArrayList<QA>();
	JPanel p;
	JButton ad,del,mod,view,back,back1,b1,b2,b3,gene,reb,mob;
	JTextField re,mo,questionNumber;
	JLabel outOfBound,numberFormat,success;
	int qno; 
	int b=1;
	String ss;
	Manage(String s)
	{
		super("Manage");
		ss=s;
		addWindowListener(this);
		outOfBound=new JLabel("Entered integer is out of range of the list of the questions.Please enter a valid number");
		numberFormat=new JLabel("The entered string is not an integer.Please enter a valid question number.");
		p=new JPanel();
		
		add(p);
		file=new File(s);
		try
		{
			file.createNewFile();
		}
		catch(IOException e){}
		setVisible(false);
		load();
		

	}

	// load and save
	void load()
	{
		ObjectInputStream in;
		try
		{
			in=new ObjectInputStream(new FileInputStream(file));
			try
			{
				while(true)
				{
					QA ob=(QA)in.readObject();
					v.add(ob);
				}
			}
			catch(EOFException e){};
		}
		catch(Exception e)
		{}
	}

	void save()
	{
		ObjectOutputStream out;
		try{
				out=new ObjectOutputStream(new FileOutputStream(file));
				for(int i=0;i<v.size();i++)
				{
					out.writeObject(v.get(i));
				}				
		}
		catch(Exception e){}
		
	}

	//end load and save

	//view list of questions
	void viewList()
	{
		remove(p);
		ScrollPane scroller = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		Canvas c=new Canvas();
		c.setSize(1000,1000);
		scroller.add(c);;
		Adjustable vadjust = scroller.getVAdjustable();
        Adjustable hadjust = scroller.getHAdjustable();
        hadjust.setUnitIncrement(10);
        vadjust.setUnitIncrement(10);
        JLabel l,k;
        p=new JPanel(new GridLayout(0,1));
        JPanel jp; 
		for(int i=0;i<v.size();i++)
		{
			k=new JLabel("Question "+ (i+1) + " : ");
			l=new JLabel(v.get(i).question.toString()+"   ");
			jp=new JPanel(new FlowLayout());
			jp.add(k);
			jp.add(l);
			p.add(jp);
			if(v.get(i).optionB.toString().equals("not an mcq"))
			{
				k=new JLabel("Answer: ");
				l=new JLabel(v.get(i).answer.toString());
				jp.add(k);
				jp.add(l);
				p.add(jp);
			}
			else
			{
				k=new JLabel("Option A: ");
				l=new JLabel(v.get(i).optionA.toString()+"    ");
				jp.add(k);jp.add(l);
				p.add(jp);
				k=new JLabel("Option B: ");
				l=new JLabel(v.get(i).optionB.toString()+"    ");
				jp.add(k);jp.add(l);
				p.add(jp);
				k=new JLabel("Option C: ");
				l=new JLabel(v.get(i).optionC.toString()+"    ");
				jp.add(k);jp.add(l);
				p.add(jp);
				k=new JLabel("Option D: ");
				l=new JLabel(v.get(i).optionD.toString()+"    ");
				p.add(jp);
				jp.add(k);jp.add(l);
				k=new JLabel("Answer: ");
				l=new JLabel(v.get(i).answer.toString());
				jp.add(k);jp.add(l);
				p.add(jp);
			}
			jp=new JPanel(new FlowLayout());
			p.add(jp);
		}
		jp=new JPanel();
		back1=new JButton("Back");
		back1.addActionListener(this);
		jp.add(back1);
		p.add(jp);
		scroller.add(p);
		add(scroller);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
	}
	// end of list of questions

	//intializae the window
	void gui()
	{
		remove(p);
		ad=new JButton("ADD A QUESTION");
		del=new JButton("DELETE A QUESTION");
		mod=new JButton("MODIFY A QUESTION");
		view=new JButton("VIEW THE LIST OF QUESTIONS");
		gene=new JButton("GENERATE RANDOM QUESTION BANK");
		back=new JButton("Back");
		p=new JPanel(new GridLayout(0,1));
		p.add(ad);
		p.add(del);
		p.add(mod);
		p.add(view);
		p.add(gene);
		p.add(back);
		add(p);
		setExtendedState ( java.awt.Frame.MAXIMIZED_BOTH ) ;
		setVisible(true);

		ad.addActionListener(this);
		del.addActionListener(this);
		mod.addActionListener(this);
		view.addActionListener(this);
		gene.addActionListener(this);
		back.addActionListener(this);
		
	}
	//end intialization

	//removal
	void preremove()
	{
		remove(p);

		p=new JPanel(new GridLayout(0,1));
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel(); 
		JPanel p6=new JPanel();

		view=new JButton("VIEW THE LIST OF QUESTIONS");
		JLabel qno=new JLabel("Enter the question number of the question to be removed: ");
		re=new JTextField(30);
		reb=new JButton("Remove");
		back1=new JButton("Back");

		p1.add(qno);
		p1.add(re);
		p2.add(reb);
		p3.add(view);
		p4.add(outOfBound);
		p5.add(numberFormat);
		p6.add(back1);

		qno.setPreferredSize(new Dimension(500,40));
		view.setPreferredSize(new Dimension(250,30));
		re.setPreferredSize(new Dimension(500,40));
		reb.setPreferredSize(new Dimension(150,30));
		back1.setPreferredSize(new Dimension(150,30));

		p.add(new JPanel());p.add(p1);p.add(p2);p.add(p3);p.add(p4);p.add(p5);p.add(p6);
		add(p);
		setVisible(true);

		outOfBound.setVisible(false);
		numberFormat.setVisible(false);

		reb.addActionListener(this);
		back1.addActionListener(this);
		view.addActionListener(this);
	
		
	}
	void removee()
	{
		v.remove(qno-1);
		JOptionPane.showMessageDialog(null,"Question number "+qno+" removed succesfully.Before Deleting or Modifying the questions any further please see the list as the question numbers may have changed.","success",JOptionPane.INFORMATION_MESSAGE);
		gui();	
		return ;
	}
	//end removal

	//modification
	void premodify()
	{
		remove(p);

		p=new JPanel(new GridLayout(0,1));
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel(); 
		JPanel p6=new JPanel();

		view=new JButton("VIEW THE LIST OF QUESTIONS");
		JLabel qno=new JLabel("Enter the question number of the question to be modfied");
		mo=new JTextField(30);
		mob=new JButton("Modify");
		back1=new JButton("Back");

		view.setPreferredSize(new Dimension(500,40));
		qno.setPreferredSize(new Dimension(500,40));
		mo.setPreferredSize(new Dimension(500,40));
		back1.setPreferredSize(new Dimension(150,30));
		mob.setPreferredSize(new Dimension(150,30));


		p1.add(qno);
		p1.add(mo);
		p2.add(mob);
		p3.add(view);
		p4.add(outOfBound);
		p5.add(numberFormat);
		p6.add(back1);



		p.add(new JPanel());p.add(p1);p.add(p2);p.add(p3);p.add(p4);p.add(p5);p.add(p6);
		add(p);

		setVisible(true);
		outOfBound.setVisible(false);
		numberFormat.setVisible(false);

		mob.addActionListener(this);
		back1.addActionListener(this);
		view.addActionListener(this);
		
		
	}
	
	void modify()
	{
		QA obj=v.get(qno-1);
		if(obj.optionA.toString().equals("True/False") && obj.optionB.toString().equals("not an mcq"))
		{
			True_False ob=new True_False(this);
			ob.modify(obj);
			dispose();
		}
		else
			if(obj.optionA.toString().equals("not an mcq"))
			{
				Fill_in_the_Blanks ob=new Fill_in_the_Blanks(this);
				ob.modify(obj);
				dispose();
			}
			else
			{
				Mcq ob=new Mcq(this);
				ob.modify(obj);
				dispose();
			}
	}
	//end modification

	//add a question
	void preinsert()
	{
		remove(p);
		p=new JPanel(new GridLayout(0,1));
		JLabel l=new JLabel("Enter the type of question you want to add");
		b1=new JButton("True/False");
		b2=new JButton("Fill in the Blank");
		b3=new JButton("Multiple Choice Question");
		back1=new JButton("Back");
		p.add(l);
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(back1);
		add(p);
		setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		back1.addActionListener(this);
		
	}
	void insert(QA q)
	{
		if(q.answer.toString().equals("answer not entered"))
			;
		else
			v.add(q);
		remove(p);
		p=new JPanel();

		setState(NORMAL);
		p.add(new JLabel("Insertion successful"));
		add(p);
		setVisible(true);
		
		gui();
		
	}
	//end addition process.

	//generate questions and answers
	void generateQuestions()
	{
		String k;
		if(ss.charAt(0)=='m')
			k="math";
		else
			if(ss.charAt(0)=='c')
				k="chemistry";
			else
				k="physics";
		Generate ob=new Generate(this,k);
		ob.gen();
	}

	//actionlisetener
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()!=ad && evt.getSource()!=back && evt.getSource()!=back1 &&  evt.getSource()!=b1 && evt.getSource()!=b2 && evt.getSource()!=b3  && v.size()==0)
		{
			JOptionPane.showMessageDialog(null,"There are 0 questions so this operation cannot be performed","",JOptionPane.INFORMATION_MESSAGE);
			return ;
		}
		if(evt.getSource()==view)
		{
			viewList();
		}
		else
		if(evt.getSource()==ad)
			{
				preinsert();
			}
		else
		if(evt.getSource()==mod)
			premodify();
		else
		if(evt.getSource()==del)
			preremove();
		else
			if(evt.getSource()==reb)
			{
				String s=re.getText();
				try{
					qno=Integer.parseInt(s);
					if(qno>v.size() || qno<1)
					{outOfBound.setVisible(true);numberFormat.setVisible(false);}
					else
						removee();

				}
				catch(NumberFormatException e)
				{
					numberFormat.setVisible(true);
					outOfBound.setVisible(false);
				}
			}
			else
				if(evt.getSource()==mob)
				{
					String s=mo.getText();
					try{
						qno=Integer.parseInt(s);
						if(qno>v.size() || qno<1)
							{outOfBound.setVisible(true);numberFormat.setVisible(false);}
						else
							modify();
					}
					catch(NumberFormatException e)
					{
						outOfBound.setVisible(false);
						numberFormat.setVisible(true);
					}
					
				}
				else
					if(evt.getSource()==b1)
					{
						True_False ob=new True_False(this);
						dispose();
						ob.takeQuestion();
					}
					else
						if(evt.getSource()==b2)
						{
							Fill_in_the_Blanks ob=new Fill_in_the_Blanks(this);
							dispose();
							ob.takeQuestion();

						}
						else
							if(evt.getSource()==b3)
							{
								Mcq ob=new Mcq(this);
								dispose();
								ob.takeQuestion();
							}
							else
								if(evt.getSource()==back1)
								{

									 getContentPane().removeAll();
									dispose();
									gui();
								}
								else
									if(evt.getSource()==gene)
									{
										generateQuestions();
									}
								else
								{
									setVisible(false);
									dispose();
									save();
									Subject ob=new Subject();
									ob.enterSubject();
									
								}
	}
	//end actionlistener

	public void windowClosing(WindowEvent e)
	{
	    setVisible(false);
	    save();
	    dispose();
	    System.exit(0);
	}
	public void windowOpened(WindowEvent evt) { }
	public void windowClosed(WindowEvent evt) {}
	public void windowIconified(WindowEvent evt) {}
	public void windowDeiconified(WindowEvent evt) {}
	public void windowActivated(WindowEvent evt) { setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);}
	public void windowDeactivated(WindowEvent evt) { }


}