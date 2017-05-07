package IBMModel1;

import java.util.ArrayList;

public class AlignmentProposal 
{
	private ArrayList<WordPair> wPairs;
	
	public AlignmentProposal()
	{
		wPairs = new ArrayList<WordPair>();
		
	}
	public void addWordPair(WordPair pair)
	{
		wPairs.add(pair);
	}
	public ArrayList<WordPair> getWordPairs()
	{
		return wPairs;
	}
	
}
