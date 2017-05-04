package MT;

import java.util.ArrayList;
import java.util.HashMap;

public class PairNGram 
{

	private String nGramEng;

	//private HashMap<String,Integer> esps;
	private HashMap<String[],HashMap<String[],Integer>> eng;

	//ArrayList approach has to be done with the manual implementation of ngram
	public PairNGram()
	{
		eng = new HashMap<String[],HashMap<String[],Integer>>();


	}

	public void addPair(String[] nGramEng,String[] nGramEsp)
	{
	
		if(eng.containsKey(nGramEng))
		{
			if(eng.get(nGramEng).containsKey(nGramEsp))
			{
				eng.get(nGramEng).put(nGramEsp, eng.get(nGramEng).get(nGramEsp)+1);
			}
			else
			{
				eng.get(nGramEng).put(nGramEsp,1);
			}
		}
		else
		{

			eng.put(nGramEng, new HashMap<String[],Integer>());
		}
	}


	public HashMap<String[],HashMap<String[],Integer>> getNEnglish()
	{
		return eng;
	}
	public HashMap<String[],Integer> getNSpanish(String[] engg)
	{

		return eng.get(engg);
	}




}
