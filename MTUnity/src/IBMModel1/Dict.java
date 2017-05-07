package IBMModel1;

import java.util.ArrayList;
import java.util.HashMap;

public class Dict 
{
	//Can do first word and ArrayList<PROBWORD>
	//OR can do <firstWord,ArrayList<WordTranslated> and each word has a count
	HashMap<String, ArrayList<WordPair>> dic ;
	
	public Dict()
	{
		dic = new HashMap<String,ArrayList<WordPair>>();
		
	}
	
	public void addPair(String eng, String esp)
	{
		if(dic.containsKey(eng))
		{
		
			int i;
			int tam =dic.get(eng).size();
			boolean found =false;
			for(i=0;i<tam&&found==false;i++)
			{
				WordPair actual  = dic.get(eng).get(i);
				if(actual.getSpanish().equalsIgnoreCase(esp))
				{
					found = true;
					dic.get(eng).get(i).addCount(dic.get(eng));
				}
			}
			if(found==false)
			{
				//BUGGG
				WordPair temp  = new WordPair(eng, esp);
				//
				dic.get(eng).add(temp);
				dic.get(eng).get(i).addCount(dic.get(eng));
			}
		}
		else
		{
			WordPair pair = new WordPair(eng, esp);
			ArrayList<WordPair> ar = new ArrayList<>();
			ar.add(pair);
			pair.addCount(ar);
			
			dic.put(eng,ar);
		}
	}
	
	public HashMap<String, ArrayList<WordPair>> getDictionary()
	{
		return this.dic;
	}
	
	public Dict getDictionaryClass()
	{
		return this;
	}
	
	/*
	public boolean pairExist(String eng, String esp)
	{
		if(!dic.containsKey(eng))
		{
			System.out.println("Dictionary doesn't contain word in English");
			return false;
		}
		else
		{
			if(dic.get(eng).)
		}
	}
	*/
	
	//if contains wordpaircountprobabilidad
}
