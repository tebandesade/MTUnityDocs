# MTUnityDocs
Machine Translation of Unity's Documentation using IBM Model 1 as inspiration. 

This is a work in progress. It uses Stanford's CoreNLP to do some NLP tasks while the machine translation is implemented by myself. 

Pipeline:
1. Extraction
The paralel corpus belongs to Unity and it is formated.
From the information extracted from the translation platform, the system created to do machine translation prepares the data to train, develop and test from it. 
This was organized like this: 60% of the data for training, 20% each for testing and developing. First, the system receives the training data and prepares it by un-formatting the information. 
Second, it eliminates noise by cleaning the data with regular expressions.

2. Analyzing
The system created uses Natural Language Processing to tokenize the parallel corpus converting it to a tokenized parallel corpus. 
For the Natural Language Processing part of the project, Stanford’s CoreNLP library was used.  

3. IBM Model 1
For each sentence aligned in the parallel corpus, there are two sentences: A Spanish sentence of size l words, and an English sentence of size m words. Before any computation, an empty dictionary is created to store the word pairs that will arise after. 
It is an empty dictionary because there is no knowledge of the translation meaning of any word from the parallel corpus.
Additionally, since there is no knowledge about what is the correct alignment between the words of the paired sentence, there is going to be an initial alignment function given to every paired sentence of the parallel corpus. 
This function will initialize all possible alignments between the entire given word pairs of each sentence aligned. This means that the possible alignments for each paired sentence will be initially l x m. 
Because of the alignment, each word pair created will be stored in the dictionary. It is expected that the dictionary holds all possible word pairs and knows what is the likelihood of each pair, at the end of the process.
By this point the system will have l x m alignment proposals for each sentence aligned in the parallel corpus. 
As a result, each alignment proposal (WordPair wp) will have a probability calculated. The system will create an array, which will store all alignment proposals with the highest probability of each aligned sentence. 
Once the system has stopped extracting the alignment pairs with the highest probability, it will update its dictionary with the word pairs stored in each alignment proposal. 
When the system updates the dictionary, it will also have the likelihood of each word pair updated.

4.Translation
The system first receives as input the English testing files and later analyzes it with NLP similar to the way the system did at the beginning of the pipeline -- extracting English tokenized text. 
 Each word in each English sentence of the text will be translated according to the dictionary that the system has by getting the Spanish word that has the maximum likelihood probability of the given English word. 
