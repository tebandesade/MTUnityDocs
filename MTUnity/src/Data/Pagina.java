package Data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Pagina 
{ 
	private ArrayList<String> esp;
	private ArrayList<String> eng;
	private static PrintWriter outIngles;
	private static PrintWriter outEsp;
	private String nombrePagina;

	public Pagina(String name)	
	{
			nombrePagina = name;
			String archivoEsp = //"/output/"+
			name + "Esp.txt";
			String archivoEng = //"/output/"+
			name + "Eng.txt";
		try {
			outIngles = new PrintWriter(archivoEng);
			outEsp = new PrintWriter(archivoEsp);
			esp = new ArrayList();
			eng = new ArrayList();
		} 
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addEspSentence(String espa)
	{
		esp.add(espa);
		outEsp.println(espa);
		
	}
	
	public void addEngSentence(String engl)
	{
		eng.add(engl);
		outIngles.println(engl);
	}
	
	public ArrayList<String> getEsp()
	{
		return esp;
	}
	
	public ArrayList<String> getEng()
	{
		return eng;
	}
	
	public void cerrarOut()
	{
		outEsp.close();
		outIngles.close();
	}
	public String giveName()
	{
		return nombrePagina;
	}
}
