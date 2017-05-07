package IBMModel1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SentenceAlignment 
{
	private int l;
	private int m;
	private String[] tokensEng;
	private String[] tokensEsp;
	private double peaf;


	//Uniform alignments
	private static ArrayList<AlignmentProposal> alignments;
	private static Dict dic;


	private int index;

	//Receives sentEng(le), sentEsp(me)
	public SentenceAlignment(String[] le,String[] me, Dict dict)
	{
		this.alignments= new ArrayList<AlignmentProposal>();
		this.dic = dict;
		this.tokensEng = le;
		this.tokensEsp = me;

		this.l =le.length;
		this.m =me.length;
		System.out.println("L: "+l);
		System.out.println("M: "+m);


		//peaf = probAlem();

	}

	public String[] getEnglishTokens()
	{
		return tokensEng;
	}
	public String[] getSpanishTokens()
	{
		return tokensEsp;
	}

	//Tocaria correr todo el programa con un alignment igual (todos con todos)
	//Saca un diccionario 
	//Se recorren los alignments actuales, se sacan las probabilidades de cada uno
	//Se cortan los menores, se sacan los max.

	//In IBM model 1 all allignments a are equally likely
	//This will get us off the ground. It is simple.
	//p(a|(e,m))
	public double probAlem()
	{
		return (1/(Math.pow((l+1), m)));
	}


	public void align()
	{
		checkti();
	}

	private void checkti()
	{
		boolean eng = false;
		if(tokensEng.length>=tokensEsp.length)
		{
			eng = true;
			int max = tokensEng.length;
			int min = tokensEsp.length;
			ti(tokensEng,tokensEsp,max,min,eng);
		}
		else
		{
			eng= false;
			int max = tokensEsp.length;
			int min = tokensEng.length;

			ti(tokensEng, tokensEsp,max,min,eng);
		}
	}

	private void ti(String[] tokensEng2,String[] tokensEsp2,int max, int min,boolean eng)
	{
		String[] mayor = (eng?tokensEng:tokensEsp);
		String[] menor = (!eng?tokensEsp:tokensEng);

		//CREO QUE ESTO VA ACA
		AlignmentProposal ap = new AlignmentProposal();
		int i ;
		for(i=0;i<max;i++)
		{
			int j;
			for(j=0;j<min;j++)
			{
				if(eng)
				{
					WordPair wp = new WordPair(tokensEng2[i], tokensEsp2[j]);
					ap.addWordPair(wp);
					dic.addPair(wp.getEnglish(), wp.getSpanish());
					wp.getPair();

				}
				else
				{
					WordPair wp = new WordPair(tokensEng2[j], tokensEsp2[i]);
					ap.addWordPair(wp);
					dic.addPair(wp.getEnglish(), wp.getSpanish());
					wp.getPair();
				}
			}
		}
		alignments.add(ap);
	}

	public void calculatePEAFPair(WordPair wp,Dict dict)
	{

		peaf *= wp.calculateLikeLiHood(dict.getDictionary().get(wp.getEnglish()).size());
	}

	public ArrayList<AlignmentProposal> getSentenceAlignments()
	{
		return alignments;
	}

	public void setSentenceAlignments(ArrayList<AlignmentProposal> pro)
	{
		alignments =pro;
	}

	//Next step come up with an estimate for
	//p(f|(a,e,m))

	public static void main(String[] args)
	{
		ArrayList<WordPair> arg = new ArrayList<WordPair>();
		// This is 3x4
		String[] eng = new String[3];
		eng[0] = "I";
		eng[1] = "like";
		eng[2] = "tacos";

		String[] esp = new String[4];
		esp[0] = "Me";
		esp[1] = "gusta";
		esp[2] = "los";
		esp[3] = "tacos";
		WordPair wp = new WordPair(eng, esp);
		arg.add(wp);
	
		
		String[] eng1 = new String[3];
		eng1[0] = "I";
		eng1[1] = "like";
		eng1[2] = "April";

		String[] esp1 = new String[3];
		esp1[0] = "Me";
		esp1[1] = "gusta";
		esp1[2] = "Abril";
		WordPair wp1 = new WordPair(eng1, esp1);
		 arg.add(wp1);
		dic = new Dict();
		
		int k ; 
		for(k=0;k<arg.size();k++)
		{
			WordPair now = arg.get(k);
			SentenceAlignment today = new SentenceAlignment(now.getEngAr(),now.getEspAr(),dic);
			today.align();

			System.out.println("What size does it have: "+today.getSentenceAlignments().size());
			int i ;
			for(i=0;i<today.getSentenceAlignments().size();i++)
			{
				AlignmentProposal actual = today.getSentenceAlignments().get(i);
				int tamProposal = actual.getWordPairs().size();
				System.out.println("AlignmentProposalArraySize: "+ tamProposal);
				int j;
				for(j=0;j<tamProposal;j++)
				{
					actual.getWordPairs().get(j).getPair();;
				}


			}
		}
		
		Iterator<String> iterDict = (Iterator<String>) dic.getDictionary().keySet().iterator();
		System.out.println("IteratorDict: "+iterDict);
		while(iterDict.hasNext())
		{
			String englishWord = iterDict.next();
			//sSystem.out.println("IterActual: "+englishWord);
			ArrayList<WordPair> arPairs =  dic.getDictionary().get(englishWord);
			int sizeOfIterArray =arPairs.size();
			//System.out.println("Size of array of iterActual: "+sizeOfIterArray);
			int m;
			for(m=0;m<sizeOfIterArray;m++)
			{
			
				System.out.println("WPairs arE: "+englishWord+" | "+arPairs.get(m).getSpanish());
				System.out.println("Counts: "+ arPairs.get(m).getCount());
				System.out.println("LikeLeehoodProba: "+ arPairs.get(m).getLikelihood());
			}
			
		}
		System.out.println(dic.getDictionary().size());
		

	}
}
