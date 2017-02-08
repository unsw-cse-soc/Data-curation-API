
package unsw.curation.api.twitter;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.WordnetStemmer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import unsw.curation.api.twitterdomain.SynonymDomain;

/**
 *
 * @author Alireza
 */
public class Synonyms {
    KeywordExtraction EX;
    private String path="C:\\Program Files (x86)\\WordNet\\2.1\\dict\\";
    public Synonyms() throws IOException
    {
      EX=new KeywordExtraction();
    }
    public Synonyms(String dictionaryFilePath) throws IOException
    {
      path=dictionaryFilePath;  
      EX=new KeywordExtraction();
    }
    
    
    public List<SynonymDomain> ExtractSynsetsSentence(String Sentence,File englishStopwordsFilePath) throws IOException, Exception
    {
        List<SynonymDomain> lstSynset=new ArrayList<>();
        String sentenceKeyWords=EX.ExtractKeyword(Sentence, englishStopwordsFilePath);
        for(String str:sentenceKeyWords.split(","))
        {
        String strSynset="";
        File dicFile=new File(path);
        IDictionary dict=new Dictionary(dicFile);
        dict.open();
        WordnetStemmer stemmer=new WordnetStemmer(dict);
         try
         {
            List<String> lstStem=stemmer.findStems(str, POS.NOUN);
            IIndexWord idxWord = dict . getIndexWord (lstStem.get(0), POS.NOUN);
            IWordID wordID = idxWord . getWordIDs ().get(0);
            IWord word = dict.getWord(wordID);
            ISynset sen=word.getSynset();
             for(IWord w:sen.getWords())
             {
               strSynset+=w.getLemma()+",";
               
             }
            lstSynset.add(new SynonymDomain(str, strSynset));
         }
         catch(Exception ex)
         {
            
         }
        }
        return lstSynset;
    }
}
