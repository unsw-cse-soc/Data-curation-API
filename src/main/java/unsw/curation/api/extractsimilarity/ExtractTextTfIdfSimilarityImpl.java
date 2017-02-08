package unsw.curation.api.extractsimilarity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import unsw.curation.api.domain.ExtractTextTfidfSimilarity;
import unsw.curation.api.domain.abstraction.ITextTfidfSimilarity;
import unsw.curation.api.tfidf.DataSearchSentence;
import unsw.curation.api.tfidf.IndexSentence;
import unsw.curation.api.tfidf.ReadDataSentence;

public class ExtractTextTfIdfSimilarityImpl implements ITextTfidfSimilarity {

	IndexSentence dInd;
    DataSearchSentence DSch;
    String twitterData="";
    String current = System.getProperty("user.dir");
    String SchTxt="";
    
    @Override
    public void delete(File file)
    	throws IOException{
 
    	if(file.isDirectory()){
    		if(file.list().length==0){
    		   file.delete();
    		}else{
        	   String files[] = file.list();
     
        	   for (String temp : files) {
        	      File fileDelete = new File(file, temp);
        	     delete(fileDelete);
        	   }
        	   if(file.list().length==0){
           	     file.delete();
        	   }
    		}	
    	}else{
    		file.delete();
    	}
    }
    
    @Override
     public void CreateIndex(String IndexFilePath) throws IOException, ParseException
     {
         
         File fileCheck=new File(current+"\\Sentence_Index");
         twitterData=IndexFilePath;
         if(!fileCheck.exists())
         {
              fileCheck.mkdir();
              dInd = new IndexSentence(current+"\\Sentence_Index\\");
              boolean numIndexed;
              long startTime = System.currentTimeMillis();	
              numIndexed = dInd.IndexTweets(twitterData);
              long endTime = System.currentTimeMillis();
              dInd.Close();
              if(numIndexed)
              System.out.println(" File indexed, time taken: "
                 +(endTime-startTime)+" ms");
              else
                     System.out.println("Can not index data. Check the file name or make sure file exists...");
         }
         else
             if(fileCheck.exists()&& fileCheck.listFiles().length>0)
             {
                 Scanner sc=new Scanner(System.in);
                 System.out.println("Index directory is exist; Do you want to index data again? (Y/N)");
                 String answer=sc.next();
                 if(answer.equalsIgnoreCase("y"))
                 {
                     delete(fileCheck);
                     System.out.println("All Index Files are deleted.");
                     fileCheck.mkdir();
                      dInd = new IndexSentence(current+"\\Sentence_Index\\");
                      boolean numIndexed;
                      long startTime = System.currentTimeMillis();	
                      numIndexed = dInd.IndexTweets(twitterData);
                      long endTime = System.currentTimeMillis();
                      dInd.Close();
                      if(numIndexed)
                      System.out.println(" File indexed, time taken: "
                         +(endTime-startTime)+" ms");
                      else
                             System.out.println("Can not index data. "
                                     + "Check the file name or make sure file exists...");
                 }
                 else
                     if(answer.equalsIgnoreCase("n"))
                     {
                         System.out.println("Searching "
                                     + "Based on the previous Indexed file...");
                     }
             }
     }
    
   @Override
   public List<ExtractTextTfidfSimilarity> SearchText(String searchText) throws IOException, ParseException
   {
      //String processedTweet=PreProcessTweet(searchText);
      List<ExtractTextTfidfSimilarity> lstSearch=new ArrayList<>();
      DSch = new DataSearchSentence(current+"\\Sentence_Index\\");
      try
      {
          TopDocs hits = DSch.search(searchText);
          for(ScoreDoc scoreDoc : hits.scoreDocs)
          {
             Document doc = DSch.getDocument(scoreDoc);
             lstSearch.add(new ExtractTextTfidfSimilarity(searchText,doc.get("Content"), 
                     String.valueOf(scoreDoc.score)));
          }
      }
      catch(Exception ex){}
      return lstSearch;
   }
/*   public List<ExtractTextTfidfSimilarity> searchFile(String FilePath) throws IOException, ParseException
   {
       int randNumber=ThreadLocalRandom.current().nextInt(1, 1000000);
      List<ExtractTextTfidfSimilarity> lstSearchResult=new ArrayList<>();
      List<String> lstData=ReadDataSentence.ReadPreProcessedData(FilePath);
      DSch = new DataSearchSentence(current+"\\Sentence_Index\\");
      for(String tweets : lstData)
      {
          try
          {
              TopDocs hits = DSch.search(tweets);
              for(ScoreDoc scoreDoc : hits.scoreDocs)
              {
                 Document doc = DSch.getDocument(scoreDoc);
                 lstSearchResult.add(new ExtractTextTfidfSimilarity(tweets,doc.get("Content"), 
                         String.valueOf(scoreDoc.score)));      
              }
          }
          catch(Exception ex){}
      }
      return lstSearchResult;
   }*/
   /*private String PreProcessTweet(String inputTweet)
   {
       inputTweet=inputTweet.replaceAll(Pattern, "");
             String [] arrSLine=inputTweet.split(" ");
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
                     str=str.replace("-","");
                     str=str.replace("_","");
                     str=str.replace("=","");
                     str=str.replace("*","");
                     str=str.replace("?","");
                     str=str.replace(":","");
                     Line+=str+" ";
             }
             return Line;
   }*/

}
