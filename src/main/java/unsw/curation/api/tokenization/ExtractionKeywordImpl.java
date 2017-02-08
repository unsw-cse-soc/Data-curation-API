package unsw.curation.api.tokenization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;

import edu.stanford.nlp.patterns.surface.CreatePatterns.CreatePatternsThread;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import unsw.curation.api.domain.ExtractionKeyword;
import unsw.curation.api.domain.abstraction.IKeywordEx;


public class ExtractionKeywordImpl implements IKeywordEx {

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
	 String Patternn="(http:|https:|ftp)[:-_?\\a-zA-Z\\d.*//]+";
	 private List<String> lstStopWords=new ArrayList<>();
	 
	@Override
	public String ExtractTweetKeyword(String inputTweet, File stopwordList) throws Exception 
	{
		lstStopWords=ReadRawData(stopwordList);
		String trimmedText=inputTweet.replaceAll(Patternn, "");
        trimmedText=trimmedText.replaceAll("\\d", "");
        String values=preProcessTweet(trimmedText);
        CharArraySet stopWords=new CharArraySet(org.apache.lucene.util.Version.LUCENE_46,lstStopWords,true);
        TokenStream tokenStreamer = new 
    		StandardTokenizer(org.apache.lucene.util.Version.LUCENE_46, new StringReader(values));
        tokenStreamer = new StopFilter(org.apache.lucene.util.Version.LUCENE_46, tokenStreamer, stopWords);
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

	@Override
	public List<ExtractionKeyword> ExtractTweetKeywordFromFile(File fileName, File stopwordList) throws FileNotFoundException, IOException 
	{
		lstStopWords=ReadRawData(stopwordList);
		List<ExtractionKeyword> lstTweetKeyWords=new ArrayList<>();
        List<String> lstData=ReadFile(fileName);
        for(String str:lstData)
        {
            String trimmedText=str.replaceAll(Patternn, "");
            trimmedText=trimmedText.replaceAll("\\d", "");
            String values=preProcessTweet(trimmedText);
            CharArraySet stopWords=new 
            CharArraySet(org.apache.lucene.util.Version.LUCENE_41,lstStopWords,true);
            TokenStream tokenStreamer = new 
            StandardTokenizer(org.apache.lucene.util.Version.LUCENE_41, 
                    new StringReader(values));
            tokenStreamer = new 
            StopFilter(org.apache.lucene.util.Version.LUCENE_41, tokenStreamer, stopWords);
            StringBuilder sb = new StringBuilder();
            CharTermAttribute charTermAttribute = tokenStreamer.addAttribute(CharTermAttribute.class);
            tokenStreamer.reset();
            while (tokenStreamer.incrementToken())
            {
            String term = charTermAttribute.toString();
            sb.append(term).append(",");
            }
            lstTweetKeyWords.add(new ExtractionKeyword(str, sb.toString()));
       }
    return lstTweetKeyWords;
	}

	@Override
	public String ExtractSentenceKeyword(String inputSentence, File stopwordList) throws Exception 
	{
		lstStopWords=ReadRawData(stopwordList);
		StringBuilder sb = new StringBuilder();
        List<String> lstData=new ArrayList<>(Arrays.asList(inputSentence.split(" ")));
        for(String str:lstData)
        {
            String trimmedText=str.replaceAll(Patternn, "");
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
             while (tokenStreamer.incrementToken()) 
             {
              String term = charTermAttribute.toString();
              sb.append(term).append(",");
             }
        }
        return sb.toString();
	}

	@Override
	public String ExtractFileKeyword(File fileName, File stopwordList) throws FileNotFoundException, IOException 
	{
		lstStopWords=ReadRawData(stopwordList);
		StringBuilder sb = new StringBuilder();
        List<String> lstData=ReadFile(fileName);
        for(String str:lstData)
        {
            String trimmedText=str.replaceAll(Patternn, "");
            trimmedText=trimmedText.replaceAll("\\d", "");
            CharArraySet stopWords=new 
            CharArraySet(org.apache.lucene.util.Version.LUCENE_41,lstStopWords,true);
            TokenStream tokenStreamer = new 
            StandardTokenizer(org.apache.lucene.util.Version.LUCENE_41, new StringReader(trimmedText.trim()));
            tokenStreamer = new 
            StopFilter(org.apache.lucene.util.Version.LUCENE_41, tokenStreamer, stopWords);
            CharTermAttribute charTermAttribute = tokenStreamer.addAttribute(CharTermAttribute.class);
            tokenStreamer.reset();
            while (tokenStreamer.incrementToken()) 
            {
                String term = charTermAttribute.toString();
                sb.append(term).append(",");
            }
        }
        return sb.toString();
	}
	
	private String ExtractFileFrequentWords(File FileName, File stopwordList) throws Exception
    {
		lstStopWords=ReadRawData(stopwordList);
        StringBuilder sb = new StringBuilder();
        List<String> lstData=ReadFile(FileName);
        for(String str:lstData)
        {
            String trimmedText=str.replaceAll(Patternn, "");
            trimmedText=trimmedText.replaceAll("\\d", "");
        //List<String> lstStopWords=ReadRawData(StopWordFilePath);
            CharArraySet stopWords=new 
            CharArraySet(org.apache.lucene.util.Version.LUCENE_41,lstStopWords,true);
            TokenStream tokenStreamer = new 
            StandardTokenizer(org.apache.lucene.util.Version.LUCENE_41, 
                    new StringReader(trimmedText.trim()));
            tokenStreamer = new 
            StopFilter(org.apache.lucene.util.Version.LUCENE_41, tokenStreamer, stopWords);
        
            CharTermAttribute charTermAttribute = tokenStreamer.addAttribute(CharTermAttribute.class);
            tokenStreamer.reset();
            while (tokenStreamer.incrementToken()) 
            {
                String term = charTermAttribute.toString();
                sb.append(term).append(",");
            }
        }
        return sb.toString();
    }
    
    private List<String> ReadFile(File FileName) throws FileNotFoundException, IOException
    {
        BufferedReader reader=new BufferedReader(new FileReader(FileName));
        List<String> lstData=new ArrayList<>();
        String sLine="";
        while((sLine=reader.readLine())!=null)
        {
            String [] arrSLine=sLine.split(" ");
             String Line="";
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
                     Line+=str+" ";
             }
             lstData.add(Line);
        }
                
        return lstData;
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

	/*@Override
	public String ExtractSentenceKeyPhrase(String inputSentence, File stopWordList) throws Exception {
		java.net.URL url = getClass().getClassLoader().getResource("en-sent.zip");
		File file = new File(url.toURI());
		lstStopWords=ReadRawData(stopWordList);
		List<String>lstPhrases=new ArrayList<>();
		InputStream modelIn=new FileInputStream(file);
		String finalString="";
		try
		{
		String pattern="(.*)(\\d+)(.*)";
		Pattern r = Pattern.compile(pattern);
		
		SentenceModel model=new SentenceModel(modelIn);
		SentenceDetectorME sentenceDetector=new SentenceDetectorME(model);
		String [] sentDetector=sentenceDetector.sentDetect(inputSentence);
		
		String createPhrase="";
		  for(String sentence:sentDetector)
		  {
			String []splitedSentence=sentence.split(" ");
			for(String myToken:splitedSentence)
			{
				String token=myToken.toLowerCase();
				Matcher m=r.matcher(token);
				if(token.length()>1)
				{
				if(token.contains("!")||token.contains(".")||token.contains("'")||token.contains("?")||token.contains(",")
						||token.contains("“"))
				{
					//if(createPhrase.length()>1)
					//	finalString+=createPhrase+", ";
					token=token.replace(token.substring(token.length()-1), "");
					if(createPhrase.length()>3)
					finalString+=createPhrase+" "+token+", ";
					else
						finalString+=token+", ";
					createPhrase="";
					continue;
				}
				else if(!lstStopWords.contains(token.toLowerCase()))
				{
					createPhrase+=token+" ";
				}
				else
				{
					if(createPhrase.length()>3)
					{
					  //lstPhrases.add(createPhrase);
						finalString+=createPhrase+", ";
					  createPhrase="";
					
					}
				}
				}
			}
	 	  }
		}
		catch(IOException ex)
		{
			
		}
		finally 
		{
			if (modelIn != null)
            {
              try
              {
                modelIn.close();
              }
              catch (IOException e)
              {
              }
            }
		}
		
		
		return finalString;
	}*/
    
}
