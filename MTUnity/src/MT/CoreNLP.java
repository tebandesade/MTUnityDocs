package MT;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CollectionUtils;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;

public class CoreNLP 
{
	private Properties props;
	private StanfordCoreNLP pipeline;


	//Have to remove Parameters if want to do it full scale
	//	public CoreNLP(String textEng, String textEsp)
	public CoreNLP()
	{
		props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
		pipeline = new StanfordCoreNLP(props);

		// Se puede hacer siempre la creacion del StanfordCoreNLP pero creo que eso solo
		// se quiere una vez

		//Toca crear una Anotation por documento? 
		//O una anotation por "ROW"
		//Si esto toca hacer toca agregar otra clase que maneje esto

		//process(textEng,textEsp);
	}


	//Podria ir en otra clase
	public void process(String textEng, String textEsp)
	{
		//Spanish Annotation --> Doesn't offer right now CORENLP
		Annotation documentEng = new Annotation(textEng);
		Annotation documentEsp = new Annotation(textEsp);


		pipeline.annotate(documentEng);
		pipeline.annotate(documentEsp);

		List<CoreMap>  sentEng = documentEng.get(SentencesAnnotation.class);
		List<CoreMap>  sentEsp = documentEsp.get(SentencesAnnotation.class);

		//Different size of sentences
		int mayor = Math.max(sentEng.size(), sentEsp.size());
		int i;
		for(i=0;i<mayor;i++)
		{
			//Potencial de investigacion
			//Invocar qué método es más fácil
			//Si se implementa una función auxiliar que
			//retorne unos valores
			//O realizar un if statement para saber cual get. (ya que son dif size)
			if(sentEng.size()==0 && sentEsp.size()>0)
			{
				sentEsp.get(i);
			}
			//Check the IFs manually
			else if (sentEsp.size()==0 && sentEng.size()>0)
			{
				sentEng.get(i);
			}
			else
			{

				//PRINTS SENTENCES TO STRINGS
				CoreMap esp = 	sentEsp.get(i);
				CoreMap eng = 	sentEng.get(i);

				//Check other data type instead of ArrayList
				//Gets en array with sentences tokenized
				//ArrayList spanishSent = (ArrayList) esp.get(CoreAnnotations.TokensAnnotation.class);
				//ArrayList englishSent = (ArrayList) eng.get(CoreAnnotations.TokensAnnotation.class);

				List<CoreLabel> spanishSent =  esp.get(CoreAnnotations.TokensAnnotation.class);
				List<CoreLabel> englishSent =  eng.get(CoreAnnotations.TokensAnnotation.class);

				//Searching for bugFix that excludes last point.
				//System.out.println("TestModulos: "+englishSent.size()%3);
				//System.out.println("TestModulos: "+spanishSent.size()%3);
				//this is one approach to fix the bug pero hay otro que es un 
				//workaround

				//System.out.println("TokenSizeEng: "+englishSent.size());
				//System.out.println("TokenSizeEsp: "+spanishSent.size());

				//System.out.println("TokensEng: "+ englishSent.get(0));
				//System.out.println("TokensSpa: "+spanishSent);

				//Gets n-grams with min 3 and max 3
				//SEE IF YOU CAN MIX THIS METHOD WITH YOUR IMPLEMENTATION OF GRAMS
				//englishSent = CollectionUtils.getNGrams((List) englishSent, 3,3);
				//spanishSent = CollectionUtils.getNGrams((List) spanishSent, 3, 3);

				/*Pseudocode
				i=14
				while()
				i+1(diferencia) ==0 && i
					resolve:  se incluye la interseccion de todos y se añade the last.
				 */


				//Design idea have a pipeline where each sentence is processed
				//Where each sentence would have an n-gram model and count probability

				//Have n-gram output is with parentesis
				//Can take them out with regex (regular expressions)

				System.out.println("SizeEng: "+englishSent.size());
				System.out.println("SizeEsp: "+spanishSent.size());

				
				
				//loopNGrams(englishSent,spanishSent);
				Grams gr =  new Grams(3);
				gr.operate(englishSent, spanishSent);
				
				gr.getEngGrams();
				gr.getEspGrams();

				//Shows string
				//System.out.println("Eng; "+sentEsp.get(i));
				//System.out.println("Esp: "+sentEng.get(i));
			}
		}
		//System.out.println(sentEng);
		//System.out.println(sentEsp);

		//String anoEng = documentEng.get(CoreAnnotations.TokensAnnotation.class);
		//String anoEsp = documentEsp.get(CoreAnnotations.TokensAnnotation.class);

		//System.out.println(documentEng.get(CoreAnnotations.TokensAnnotation.class));
		//System.out.println(documentEsp.get(CoreAnnotations.TokensAnnotation.class));

	}

	public static void main (String[] args)
	{
		String testEng = "Unity uses <span class=\"doc-keyword\">Animation Layers</span> for managing complex state machines for different body parts. An example of this is if you have a lower-body layer for walking-jumping, and an upper-body layer for throwing objects / shooting.";
		String testEsp = "Unity usa <span class=\"doc-keyword\">Animation Layers</span> para manejar state machines complejos para differente partes del cuerpo. Un ejemplo de esto es si se tiene una capa inferior de cuerpo para caminar-saltar, y una capa superior de cuerpo para tirar objetos / disparar.";

		//new CoreNLP(testEng,testEsp);
	}

	private void loopNGrams(List englishSent, List spanishSent)
	{
		int min = Math.min(englishSent.size(),spanishSent.size());
		int i;
		//Have to think of somethin (look above) to fix the different sizes
		for(i = 0; i<min;i++)
		{
			//It is object,but object of what type
			//Workaround quick make it tostring
			//PairNGram pair = new PairNGram(englishSent.get(i).toString(),spanishSent.get(i).toString());
		}
	
	}
}
