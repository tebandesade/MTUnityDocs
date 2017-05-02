package MT;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class CoreNLP 
{
	private Properties props;
	private StanfordCoreNLP pipeline;


	public CoreNLP(String textEng, String textEsp)
	{
		props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
		pipeline = new StanfordCoreNLP(props);

		// Se puede hacer siempre la creacion del StanfordCoreNLP pero creo que eso solo
		// se quiere una vez

		//Toca crear una Anotation por documento? 
		//O una anotation por "ROW"
		//Si esto toca hacer toca agregar otra clase que maneje esto
		process(textEng,textEsp);
	}


	//Podria ir en otra clase
	private void process(String textEng, String textEsp)
	{
		Annotation documentEng = new Annotation(textEng);
		Annotation documentEsp = new Annotation(textEsp);

		pipeline.annotate(documentEng);
		pipeline.annotate(documentEsp);

		List<CoreMap>  sentEng = documentEng.get(SentencesAnnotation.class);
		List<CoreMap>  sentEsp = documentEng.get(SentencesAnnotation.class);

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
				sentEsp.get(i);
				sentEng.get(i);
				System.out.println(sentEsp.get(i));
				System.out.println(sentEng.get(i));
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

		new CoreNLP(testEng,testEsp);
	}
}