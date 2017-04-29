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

	private static final File DIRECTORIOREPO = new File ("res/repo/manual/");

	private static BufferedReader br ;
	private static int debugInit = 0;
	private ArrayList<Pagina> paginas;

	public Extraction()
	{
		//LeerArchivos();
		LeerArchivo(TESTFILE);
	}

	//Add File Test to parameters
	public static void LeerArchivo(File file)
	{
		boolean ingles = true;
		
		Pagina actual = new Pagina(file.getName());
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
		actual.cerrarOut();
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
						LeerArchivo(index2);
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
		String temp = linea.replace(lang, "");
		if(temp.length()>2)
		{
			//System.out.println("Es mayor a 2 "+ temp);
			return extractMsg(temp);
			
		}
		else
		{
			//System.out.println("No es mayor a 2 " + temp);
			return "noEsMayorA2";
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

