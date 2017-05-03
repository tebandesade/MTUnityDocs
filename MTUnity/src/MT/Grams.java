package MT;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import edu.stanford.nlp.ling.CoreLabel;

public class Grams 
{
	private ArrayList<String[]> eng;
	private ArrayList<String[]> esp;
	private int size;
	
	
	public Grams(int sizee) {
		// TODO Auto-generated constructor stub
		eng = new ArrayList<String[]>();
		esp = new ArrayList<String[]>();
		size=sizee;
		
	}

	//ONLY IMPLEMENTED WITH GRAM3
	public void operate(List<CoreLabel> englishSent, List<CoreLabel> spanishSent)
	{
		List<CoreLabel> mayor = (englishSent.size()>=spanishSent.size())?englishSent:spanishSent;
		int min = Math.min(englishSent.size(), spanishSent.size());
		//System.out.println("SIZE OF MIN: "+min);
		int minRemainder = min%size;
		//System.out.println("MINREMAINDER: "+minRemainder);
		int diff= Math.max(englishSent.size(),spanishSent.size()) - min;
		//System.out.println("DIFFERENCES: "+diff);
		if(minRemainder>0)
		{
			String[] ingles = new String[3];
			String[] spanish = new String[3];
			int i; 
			for(i=0; i<(min-minRemainder);i++)
			{
				
				//THOUGHT FOR 3-gram
				ingles[0] = clean(englishSent.get(i));
				spanish[0]= clean(spanishSent.get(i));
				i++;
				ingles[1] = clean(englishSent.get(i));
				spanish[1]= clean(spanishSent.get(i));
				i++;
				ingles[2] = clean(englishSent.get(i));
				spanish[2]= clean(spanishSent.get(i));
				eng.add(ingles);
				esp.add(spanish);
				//PASA ALGO CON LOS PARES
			}
			
		}
		//Se pierde información porque no se considera lo restante 
		else
		{
			String[] ingles = new String[3];
			String[] spanish = new String[3];
			int i; 
			for(i=0; i<min;i++)
			{
				
				
				//Consider Stringbuilder (its better)
				//STRING can be CoreLabel check that out later
			
				
				
				//THOUGHT FOR 3-gram
				ingles[0] = clean(englishSent.get(i));
				spanish[0]= clean(spanishSent.get(i));
				i++;
				ingles[1] = clean(englishSent.get(i));
				spanish[1]= clean(spanishSent.get(i));
				i++;
				ingles[2] = clean(englishSent.get(i));
				spanish[2]= clean(spanishSent.get(i));
				eng.add(ingles);
				esp.add(spanish);
				//PASA ALGO CON LOS PARES
			}
			if(diff>0)
			{
				int remainDiff = diff%3;
				if(englishSent.size()>=spanishSent.size())
				{
					///La condicion puede ser el string
					// While ()
					//while(i<diff)
					//{
						
					//}
				   int j;
				   for(j=0;j<(3-remainDiff) &&(i<englishSent.size());j++)
				   {
					   ingles[j]= clean(englishSent.get(i++));
					   
				   }
				   eng.add(ingles);
				   esp.add(spanish);
				}
				else
				{
					//spanish.get(i)
					//while(i<diff)
					//{
						
					//}
					System.out.println("I is: "+i);
					 int j;
					   for(j=0;j<(3-remainDiff)&&(i<spanishSent.size());j++)
					   {
							System.out.println("II is: "+i);
						   spanish[j]= clean(spanishSent.get(i++));
					   }
					   eng.add(ingles);
					   esp.add(spanish);
				}
				
			}
			
		}
		
	}
	public String clean(CoreLabel coreLabel)
	{
		//System.out.println(coreLabel.originalText());
			return coreLabel.originalText();	
		//return coreLabel.substring(0, coreLabel.size()-2);
	}
	
	public ArrayList<String[]> getEngGrams()
	{
		System.out.println("English Grams Size: "+ eng.size());
		return eng;
	}
	public ArrayList<String[]> getEspGrams()
	{
		System.out.println("Spanish Grams Size: "+esp.size());
		return esp;
	}
}
