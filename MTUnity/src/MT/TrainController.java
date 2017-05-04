package MT;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainController 
{
	//Think to add a hashmap inside <>
	private HashMap<String,String> hm;
	private PairNGram pair;
	//
	//Proposal HashMap<String,HashMap<String,Count>>()
	public TrainController()
	{
		hm = new HashMap<String, String>();
		pair = new PairNGram();
	}

	public void addPair(ArrayList<String[]> eng,ArrayList<String[]> esp)
	{
		//In case there are different sizes of arrays
		int min = Math.min(eng.size(), esp.size());
		int i;
		int j=0;
		System.out.println("MINTRAIN: "+min);
		for(i = 0; i<min;i++)
		{

			System.out.println("ARRAYLIST ENG: "+eng.get(i)[j]+" "+eng.get(i)[j+1]+" ");
			System.out.println("ARRAYLIST ESP: "+esp.get(i)[j]+ " "+esp.get(i)[j+1]+" ");
			pair.addPair(eng.get(i), esp.get(i));
		    
		}


	}

}
