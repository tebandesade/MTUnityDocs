package MT;

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
	
	public void addPair(String eng,String esp)
	{
		
		pair.addPair(eng, esp);
		
	}
	
}
