package unsw.curation.api.extractsynonym;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;
import unsw.curation.api.domain.ExtractSynonym;
import unsw.curation.api.domain.abstraction.ISynonym;


public class WordNetFile implements ISynonym {

	private String path="C:\\Program Files (x86)\\WordNet\\2.1\\dict\\";
	
	@Override
	public List<String> ExtractSynonymWord(String word) throws URISyntaxException, IOException 
	{
		List<String> lstSynonym=new ArrayList<>();
		java.net.URL url = getClass().getClassLoader().getResource("dict\\");
	    //File dicFile = new File(url.toURI());
	    String strSynset="";
        IDictionary dict=new Dictionary(new File(path));
        dict.open();
        WordnetStemmer stemmer=new WordnetStemmer(dict);
         try
         {
            List<String> lstStem=stemmer.findStems(word, POS.NOUN);
            for(int i=0;i<1000;i++)
            {
            IIndexWord idxWord = dict . getIndexWord (lstStem.get(0), POS.NOUN);
            IWordID wordID = idxWord . getWordIDs ().get(i);
            IWord words = dict.getWord(wordID);
            ISynset sen=words.getSynset();
             for(IWord w:sen.getWords())
             {
            	 
            	 lstSynonym.add(w.getLemma());
             }
             
            }
         }
         catch(Exception ex)
         {
         }
        return lstSynonym;
	}

	@Override
	public List<String> ExtractHypernymWord(String word) {
		 //String strHypernym="";
		 List<String> lstHypernym=new ArrayList<>();
	      try
	        {
	    	   // java.net.URL url = getClass().getClassLoader().getResource("dict\\");
	  	       // File dicFile = new File(url.toURI());
	            IDictionary dict=new Dictionary(new File(path));
	            dict.open();
	            WordnetStemmer stemmer=new WordnetStemmer(dict);
	            List<String> lstStem=stemmer.findStems(word, POS.NOUN);
	            for(int j=0;j<1000;j++)
	            {
	            IIndexWord idxWord = dict.getIndexWord(lstStem.get(0),POS.NOUN);
	            IWordID wordID = idxWord.getWordIDs().get (j) ;
	            IWord mywords = dict . getWord (wordID);
	            ISynset sen=mywords.getSynset();
	            List <ISynsetID> hypernyms = sen.getRelatedSynsets (Pointer.HYPERNYM);
	            List<IWord> words;
	            for(ISynsetID sid : hypernyms)
	            {
	                words = dict . getSynset (sid). getWords ();
	                for( Iterator <IWord > i = words . iterator (); i. hasNext () ;)
	                {
	                	
	                	lstHypernym.add((i. next (). getLemma ()));
	                //if(i. hasNext ())
	                 //  strHypernym+=",";
	                }
	            }
	            }
	         }
	       catch(Exception ex)
	       {
	       }
	        return lstHypernym;
	}
	
	public List<ExtractSynonym> ExtractSynonymFile(File filePath) throws MalformedURLException, IOException
    {
        List<ExtractSynonym> lstSynset=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(filePath));
        String Line="";
        List<String> lstText=new ArrayList<>();
        while((Line=reader.readLine())!=null)
        {
            lstText.add(Line);
        }
        for(String Sentence:lstText)
        {
            for(String str:Sentence.split(" "))
            {
                String strSynset="";
                File dicFile=new File(path);
                IDictionary dict=new Dictionary(dicFile);
                dict.open();
                WordnetStemmer stemmer=new WordnetStemmer(dict);
                for(int j=0;j<10;j++)
	            {
                 try
                 {
                	List<String> lstStem=stemmer.findStems(str, POS.NOUN);
                    IIndexWord idxWord = dict . getIndexWord (lstStem.get(0), POS.NOUN);
                    IWordID wordID = idxWord . getWordIDs ().get(j);
                    IWord word = dict.getWord(wordID);
                    ISynset sen=word.getSynset();
                     for(IWord w:sen.getWords())
                     {
                       strSynset+=w.getLemma()+",";

                     }
                    lstSynset.add(new ExtractSynonym(str, strSynset));
                 }
                 catch(Exception ex)
                 {
                 }
	            }
            }
        }
        return lstSynset;
    }

	public List<ExtractSynonym> ExtractSynonymSentence(String inputSentence) throws MalformedURLException, IOException
    {
        List<ExtractSynonym> lstSynset=new ArrayList<>();
            for(String str:inputSentence.split(" "))
            {
                String strSynset="";
                File dicFile=new File(path);
                IDictionary dict=new Dictionary(dicFile);
                dict.open();
                WordnetStemmer stemmer=new WordnetStemmer(dict);
                for(int j=0;j<10;j++)
	            {
                  try
                  {
                    List<String> lstStem=stemmer.findStems(str, POS.NOUN);
                    IIndexWord idxWord = dict . getIndexWord (lstStem.get(0), POS.NOUN);
                    IWordID wordID = idxWord . getWordIDs ().get(j);
                    IWord word = dict.getWord(wordID);
                    ISynset sen=word.getSynset();
                     for(IWord w:sen.getWords())
                     {
                       strSynset+=w.getLemma()+",";
                     }
                    
                  }
                  
                  catch(Exception ex)
                  {

                  }
                  
	            }
                lstSynset.add(new ExtractSynonym(str, strSynset));
            }
        return lstSynset;
    }

	

}
