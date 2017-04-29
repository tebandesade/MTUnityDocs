package Data;

import java.util.ArrayList;

public class Pagina 
{ 
	private ArrayList<String> esp;
	private ArrayList<String> eng;
	
	public Pagina()	
	{
		esp = new ArrayList();
		eng = new ArrayList();
	}
	
	public void addEspSentence(String espa)
	{
		esp.add(espa);
	}
	
	public void addEngSentence(String engl)
	{
		eng.add(engl);
	}
	
	public ArrayList<String> getEsp()
	{
		return esp;
	}
	
	public ArrayList<String> getEng()
	{
		return eng;
	}
}
