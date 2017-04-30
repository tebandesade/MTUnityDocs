package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.DebugGraphics;

public class Extraction 
{
	private static final File TESTFILE = new File("res/test/testInput.txt");
	private static final File TESTFILEB6 = new File("res/test/bug6.txt");
	private static final File DIRECTORIOREPO = new File ("res/repo/manual/");

	private static BufferedReader br ;
	private static int debugInit = 0;
	private static int debugGlobal = 0;
	private ArrayList<Pagina> paginas;
	
	private static boolean helpBug6 =true;
	

	public Extraction()
	{
		LeerArchivos();
		
		//Debug count variables	
		//System.out.println("GLOBAL: "+ debugGlobal);
		//System.err.println("Error: "+ debugInit);
		//To debug files
		//LeerArchivo(TESTFILE);
		
		//LeerArchivo(TESTFILEB6,"pruebaB6");
	}

	//Add File Test to parameters
	public static void LeerArchivo(File file,String nombreArchivo)
	{
		boolean ingles = true;
		
		Pagina actual = new Pagina(nombreArchivo);
		try {
			br = new BufferedReader(new FileReader(	file));
			String linea =  br.readLine();
			while(linea!=null)
			{
				if(linea.contains("msgid")|| linea.contains("msgstr"))
				{
					
					String text = auxSplit(linea);
					if(!text.equals("noEsMayorA2"))
					{
						if(ingles)
						{
							ingles = false;
							actual.addEngSentence(text);
						}
						else
						{
							ingles=true;
							actual.addEspSentence(text);
						}
						//System.out.println(text);
					}
				}

				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//OUTPUT FILES
		//actual.cerrarOut();
		
		
		if(actual.getEng().size()==actual.getEsp().size())	{
			System.out.println("true "+actual.getEng().size());
			debugGlobal++;
			/*
			int mayor = Math.min(actual.getEng().size(), actual.getEsp().size());
			for(int i = 0; i<mayor;i++)
			{
				System.err.println("Eng: "+ actual.getEng().get(i));
				System.err.println("Esp: "+ actual.getEsp().get(i));
			}
			*/
		}
		else
		{
			System.err.println("false "+"One: "+actual.getEng().size()+" Two: "+actual.getEsp().size());
			System.err.println("Name of the page with size errors: "+actual.giveName());
			/*
			int mayor = Math.min(actual.getEng().size(), actual.getEsp().size());
			for(int i = 0; i<mayor;i++)
			{
				System.err.println("Eng: "+ actual.getEng().get(i));
				System.err.println("Esp: "+ actual.getEsp().get(i));
			}
			*/
			
			
			
			//In here we have two debug int counts that will allow us to check how many red (problem) pages
			debugInit++;
			debugGlobal++;
		}
	}
	


	//Reads all Spanish Files from all directories
	public static void LeerArchivos()
	{
		File[] listFiles = DIRECTORIOREPO.listFiles();
		int tamArchivos =  listFiles.length;
		int count =0 ;
		
		//Check size of files
		//System.out.println(tamArchivos);
		//have to skip first file (.ds_store)
		for (int i = 1; i <tamArchivos; i++) {


			File index = listFiles[i];
			//System.out.println(index);
			if(index.isDirectory())
			{
				int  tamSubArchivos = index.listFiles().length;

				//Test if size is the same
				//Test Archivos = 1351 (toca -1 por el primero y -1 por el otro .config);
				//Test cosos de Esp = 1349 

				for(int j = 0; j< tamSubArchivos;j++)
				{
					File index2 = index.listFiles()[j];
					if(index2.getName().equalsIgnoreCase("es.po"))
					{
						//Passes the current file but gets the name of the Parent directory
						//Parent directory is the Page's name
						LeerArchivo(index2,index.getName());
						count++;
						//System.out.println(index2.getName());
					}
				}
			}
		}
		
		//Count how much spanish files there are 
		//System.out.println(count);
	}

	public static void main (String[] args)
	{
		new Extraction();
	}


	private static String auxSplit(String linea)
	{
		String lang = "";
		if(linea.contains("msgid"))
		{
			lang = "msgid ";
		}
		else if(linea.contains("msgstr"))
		{
			lang = "msgstr ";
		}
		//Not always the line begins with msgid|msgstring 
		int test = linea.indexOf(lang);
		if(test>0)
		{
			linea = linea.substring(test, linea.length());
		}
		
		//Have to fix bug 6 would put flag and see if it works
		//MakeGlobalFlag
		String temp = linea.replace(lang, "");
		
		// to check if msgid ""
		if(temp.length()>2)
		{
			//System.out.println("Es mayor a 2 "+ temp);
			return extractMsg(temp);
			
		}
		else
		{
			//System.out.println("No es mayor a 2 " + temp);
			if(helpBug6 && lang.contains("msgid"))
			{
				helpBug6 = false;
				return "noEsMayorA2";
			}
			else if(helpBug6 && lang.contains("msgstr"))
			{
			    return "";
			}
			else if (!helpBug6 && lang.contains("msgstr"))
			{
				helpBug6 =true;
				return "noEsMayorA2";
			}
			else 
			{
				return "noEsMayorA2";
			}
		
		}
	}

	private static String extractMsg(String linea)
	{
		int tam = linea.length();  
		int indexL = tam-1;
		linea = linea.substring(1, indexL);
		
		//System.out.println(linea);
		return linea;
	}
	

}


