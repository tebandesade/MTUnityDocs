package IBMModel1;

import java.util.ArrayList;

public class Model1 
{
	//Archivos
	//Dictionarr
	private Dict dict;
	private ArrayList<SentenceAlignment> sentenceAlignments;

	public Model1()
	{
		dict = new Dict();
	}



	//Tengo dos sentences

	public void init()
	{
		//Lee archivos
		String[] eng = new String[2] ;
		String[] esp = new String[2] ; 
		new SentenceAlignment(eng,esp,dict);
	}



}
