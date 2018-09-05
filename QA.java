import java.io.Serializable;
class QA implements Serializable
{
	StringBuilder question,answer,optionA,optionB,optionC,optionD;
	QA()
	{
		question=new StringBuilder("question not entered");
		answer=new StringBuilder("answer not entered");
		optionA=new StringBuilder("not an mcq");
		optionB=new StringBuilder("not an mcq");
		optionC=new StringBuilder("not an mcq");
		optionD=new StringBuilder("not an mcq");
	}
	
}
