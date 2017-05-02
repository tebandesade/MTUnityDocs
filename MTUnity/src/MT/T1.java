package MT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class T1 
{
	private static final File TESTENG = new File("AnimationLayerTestEng.txt");
	private static final File TESTESP = new File("AnimationLayerTestEsp.txt");
	private static BufferedReader brEng ;
	private static BufferedReader brEsp ;

	public T1() throws FileNotFoundException
	{
		readFile();
	
		
	}
	
	private void readFile()
	{
		try {
			brEng = new BufferedReader(new FileReader(TESTENG));
			brEsp = new BufferedReader(new FileReader(TESTESP));

			String lineaEng = brEng.readLine();
			String lineaEsp = brEsp.readLine();

				while(lineaEng!=null && lineaEsp!=null)
				{
			
					//System.out.println("Ingles: "+ lineaEng);
					//System.out.println("Espanol:  "+ lineaEsp);
					 lineaEng = brEng.readLine();
					 lineaEsp = brEsp.readLine();
				}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			brEng.close();
			brEsp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args)
	{
		try {
			new T1();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}