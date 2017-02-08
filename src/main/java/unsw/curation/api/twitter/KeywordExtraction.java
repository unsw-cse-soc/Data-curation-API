package unsw.curation.api.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;

/**
 *
 * @author Alireza
 */
public class KeywordExtraction {
    
    public KeywordExtraction()
    {
        
    }
    private String preProcessTweet(String tweet)
    {
        String Pattern="(http:|https:|ftp)[:-_?\\a-zA-Z\\d.*//]+";
        tweet=tweet.replaceAll(Pattern, "");
        String Line="";
        if(tweet.toCharArray().length<141)
        {
            String [] arrSLine=tweet.split(" ");
            for(String str:arrSLine)
            {
                    str=str.replace("'","");
                    str=str.replace("(","");
                    str=str.replace(")","");
                    str=str.replace("!","");
                    str=str.replace("[","");
                    str=str.replace("]","");
                    str=str.replace("{","");
                    str=str.replace("}","");
                    str=str.replace("\"","");
                    str=str.replace("?","");
                    str=str.replace(".","");
                    str=str.replace("#","");
                    str=str.replace("@","");
                    Line+=str.trim()+" ";
            }
        }
            return Line;
    }
    
         String Pattern="(http:|https:|ftp)[:-_?\\a-zA-Z\\d.*//]+";
	 private List<String> lstStopWords=new ArrayList<>();
        public String ExtractTweetKeyword(String inputTweet, File stopwordList) throws Exception 
	{
	lstStopWords=ReadRawData(stopwordList);
	String trimmedText=inputTweet.replaceAll(Pattern, "");
        trimmedText=trimmedText.replaceAll("\\d", "");
        String values=preProcessTweet(trimmedText);
        CharArraySet stopWords=new CharArraySet(org.apache.lucene.util.Version.LUCENE_41,lstStopWords,true);
        TokenStream tokenStreamer = new 
    		StandardTokenizer(org.apache.lucene.util.Version.LUCENE_41, new StringReader(values));
        tokenStreamer = new StopFilter(org.apache.lucene.util.Version.LUCENE_41, tokenStreamer, stopWords);
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStreamer.addAttribute(CharTermAttribute.class);
        tokenStreamer.reset();
        while (tokenStreamer.incrementToken()) 
        {
           String term = charTermAttribute.toString();
           sb.append(term).append(",");
        }
        return sb.toString();
	}
        
        
        public String ExtractKeyword(String inputTweet, File stopwordList) throws Exception 
	{
	lstStopWords=ReadRawData(stopwordList);
	String trimmedText=inputTweet.replaceAll(Pattern, "");
        trimmedText=trimmedText.replaceAll("\\d", "");
        CharArraySet stopWords=new CharArraySet(org.apache.lucene.util.Version.LUCENE_41,lstStopWords,true);
        TokenStream tokenStreamer = new 
    		StandardTokenizer(org.apache.lucene.util.Version.LUCENE_41, new StringReader(trimmedText));
        tokenStreamer = new StopFilter(org.apache.lucene.util.Version.LUCENE_41, tokenStreamer, stopWords);
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStreamer.addAttribute(CharTermAttribute.class);
        tokenStreamer.reset();
        while (tokenStreamer.incrementToken()) 
        {
           String term = charTermAttribute.toString();
           sb.append(term).append(",");
        }
        return sb.toString();
	}
        
       public String ExtractKeywordsList(List<String> lstData) throws Exception
       {
        StringBuilder sb = new StringBuilder();
        for(String str:lstData)
        {
            String trimmedText=str.replaceAll(Pattern, "");
            trimmedText=trimmedText.replaceAll("\\d", "");
        CharArraySet stopWords=new 
            CharArraySet(org.apache.lucene.util.Version.LUCENE_41,lstStopWords,true);
        TokenStream tokenStreamer = new 
            StandardTokenizer(org.apache.lucene.util.Version.LUCENE_41, 
                    new StringReader(trimmedText.trim()));
        tokenStreamer = new 
            StopFilter(org.apache.lucene.util.Version.LUCENE_41, tokenStreamer, stopWords);
        
        CharTermAttribute charTermAttribute = tokenStreamer.addAttribute(CharTermAttribute.class);
        tokenStreamer.reset();
        while (tokenStreamer.incrementToken()) {
            String term = charTermAttribute.toString();
            sb.append(term).append(",");
        }
        }
        return sb.toString();
       }
        
        
        private static List<String> ReadRawData(File filePath) throws FileNotFoundException, IOException 
        {
        List<String> lstData=new ArrayList<>();
        String sLine;
        BufferedReader sr = new BufferedReader(new FileReader(filePath));
         while ((sLine = sr.readLine()) != null) {
         lstData.add(sLine);
         }
         return lstData;
        }
}
