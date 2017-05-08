package IBMModel1;

import java.util.ArrayList;

public class AlignmentProposal 
{
	private ArrayList<WordPair> wPairs;
	private double probability;
	private final double  MIN_VALUE = Double.MIN_VALUE;
	
	public AlignmentProposal()
	{
		this.probability= 1;
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
	
	
	public double calculatePEAF()
	{
		
		int i;
		int tam = wPairs.size();
		for(i=0;i<tam;i++)
		{
			probability*= wPairs.get(i).getLikelihood();
			//System.out.println("WPAIRSENG: "+ wPairs.get(i).getEnglish());
			//System.out.println("WPAIRSENGSIZE: "+ wPairs.get(i).getEnglish().length());
			//System.out.println("WPAIRSESP: "+ wPairs.get(i).getSpanish());
			//System.out.println("WPAIRSESPSIZE: "+ wPairs.get(i).getSpanish().length());
		}
		
		if(probability<=MIN_VALUE)
		{
			probability = MIN_VALUE;
			System.out.println("ES MENOR OMG: "+probability);
		}
		
		//System.out.println("AlignmentProposal: "+ wPairs.get(0));
		//System.out.println("PEAF IS : "+ probability);
		return probability;
			
	}
	public void initializeProbability(int l, int m)
	{
		this.probability =  1/(Math.pow((l+1), m));
	}
	

	
}