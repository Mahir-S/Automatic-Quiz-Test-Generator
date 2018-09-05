import java.util.*;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.*;
class Generate extends JFrame implements WindowListener,ActionListener
{
	Manage m;
	JButton back1,back2,back3,view,export,adn,expans;
	JLabel err1,err2;
	JTextField number;
	String s;
	JPanel p,temp;
	int n;
	ArrayList<QA> a;

	Generate(Manage k,String ss)
	{
		super("GENERATE");
		addWindowListener(this);
		m=k;
		s=ss;
		setBackground(Color.WHITE);
	}
	void gen()
	{
		p=new JPanel(new GridLayout(0,1));
		
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel();

		JLabel display=new JLabel("Enter the number of random questions to be generated");
		number=new JTextField(100);
		back1=new JButton("Back");
		err1=new JLabel("Entered number is out of list of questions.No. of question in total are "+m.v.size()+" .Please try again");
		err2=new JLabel("Entered string is not a number.Please try again.");
		adn=new JButton("Generate");

		display.setPreferredSize(new Dimension(500,30));
		number.setPreferredSize(new Dimension(500,30));
		back1.setPreferredSize(new Dimension(80,30));
		adn.setPreferredSize(new Dimension(150,30));

		p1.add(display);
		p1.add(number);
		p2.add(adn);
		p3.add(back1);
		p4.add(err1);
		p5.add(err2);

		p.add(new JPanel());p.add(p1);p.add(p2);p.add(p3);p.add(p4);p.add(p5);
		add(p);

		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		err1.setVisible(false);
		err2.setVisible(false);

		back1.addActionListener(this);
		adn.addActionListener(this);
	}
	void askexpo(int ch)
	{
		if(ch==0)
		{
		a=new ArrayList<QA>();
		for(int i=0;i<m.v.size();i++)
		{
			QA ob=m.v.get(i);
			QA ob2=new QA();
			ob2.question=ob.question;
			ob2.answer=ob.answer;
			ob2.optionA=ob.optionA;
			ob2.optionB=ob.optionB;
			ob2.optionC=ob.optionC;
			ob2.optionD=ob.optionD; 
			a.add(ob2);
		}
		Collections.shuffle(a);
		remove(p);
		}
		
		p=new JPanel(new GridLayout(0,1));
		JLabel display=new JLabel(n+" random questions have been generated.Select if you want to export them.");
		view=new JButton("View the List of Question of generated");
		export=new JButton("Export the Questions");
		expans=new JButton("Export the answers");
		back3=new JButton("back");

		p.add(display);
		p.add(view);
		p.add(export);
		p.add(expans);
		p.add(back3);
		add(p);

		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);

		view.addActionListener(this);
		export.addActionListener(this);
		back3.addActionListener(this);
		expans.addActionListener(this);

	}
	void showList()
	{
		remove(p);
		ScrollPane scroller=new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		Canvas c=new Canvas();
		c.setSize(1000,1000);
		scroller.add(c);;
		Adjustable vadjust = scroller.getVAdjustable();
        Adjustable hadjust = scroller.getHAdjustable();
        hadjust.setUnitIncrement(10);
        vadjust.setUnitIncrement(10);
        p=new JPanel(new GridLayout(0,1));
        JLabel l;
        JPanel jp;
		for(int i=0;i<n;i++)
		{
			l=new JLabel((i+1)+"). "+a.get(i).question.toString());
			jp=new JPanel();
			jp.add(l);
			p.add(jp);
		}
		JPanel pp=new JPanel();
		back2=new JButton("back");
		pp.add(back2);
		p.add(pp);
		scroller.add(p);

		add(scroller);
		pack();
		setVisible(true);
		back2.addActionListener(this);
	}
	void expo()
	{	
		try
		{
			String home=System.getProperty("user.home");
			FileWriter fout = new FileWriter(home+"/Desktop/"+s+"_questions.txt");
            BufferedWriter buf =new BufferedWriter(fout);
			for(int i=0;i<n;i++)
			{
				if(a.get(i).optionB.toString().equals("not an mcq"))
				{
					buf.write((i+1)+". "+a.get(i).question.toString());
				}
				else
				{
					buf.write(i+1+". "+a.get(i).question.toString());
					buf.newLine();
					buf.write("A: "+a.get(i).optionA.toString()+"          B: "+a.get(i).optionB.toString());
					buf.newLine();
					buf.write("C: "+a.get(i).optionC.toString()+"          D: "+a.get(i).optionD.toString());
				}
				if(i!=n-1)
				{
					buf.newLine();
					buf.newLine();
				}

			}
			JOptionPane.showMessageDialog(null,"Succesfully export as "+s +"_ques.txt to Desktop","Success", JOptionPane.INFORMATION_MESSAGE);
			buf.close();

		}
		catch(Exception e){e.printStackTrace();}
		getContentPane().removeAll();
		askexpo(1);
	}
	void expoans()
	{	

		try
		{
			String home=System.getProperty("user.home");
			FileWriter fout = new FileWriter(home+"/Desktop/"+s+"_ans.txt");
            BufferedWriter buf =new BufferedWriter(fout);
			for(int i=0;i<n;i++)
			{
				buf.write((i+1)+". "+a.get(i).answer.toString());
				if(i!=n-1)
				{
					buf.newLine();
					buf.newLine();
				}
			}
			JOptionPane.showMessageDialog(null,"Succesfully export as "+s +"_ans.txt to Desktop","Success", JOptionPane.INFORMATION_MESSAGE);
			buf.close();
		}
		catch(Exception e){e.printStackTrace();}
		getContentPane().removeAll();
		askexpo(1);
	}

	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==adn)
		{
			String f=number.getText();
			try{
				n=Integer.parseInt(f);
				if(n<0 || n>m.v.size())
				{
					err2.setVisible(false);
					err1.setVisible(true);
				}
				else
					{

						askexpo(0);
					}
			}
			catch(NumberFormatException e)
			{
				err1.setVisible(false);
				err2.setVisible(true);
			}
		}
		else
			if(evt.getSource()==view)
			{
				
				showList();

			}
			else
				if(evt.getSource()==export)
				{
					expo();
				}
				else
					if(evt.getSource()==back2)
					{
						getContentPane().removeAll();
						askexpo(1);
					}
					else
						if(evt.getSource()==back1)
						{
							dispose();
							m.gui();
						}
						else
						if(evt.getSource()==back3)
						{
							remove(p);
							gen();
						}
						else
						if(evt.getSource()==expans)
						{
							expoans();
						}
	}
	public void windowClosing(WindowEvent e)
	{
	    setVisible(false);
	    dispose();
	    m.gui();
	}
	public void windowOpened(WindowEvent evt){}
	public void windowClosed(WindowEvent evt){}
	public void windowIconified(WindowEvent evt){}
	public void windowDeiconified(WindowEvent evt){}
	public void windowActivated(WindowEvent evt){setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);}
	public void windowDeactivated(WindowEvent evt){}

}