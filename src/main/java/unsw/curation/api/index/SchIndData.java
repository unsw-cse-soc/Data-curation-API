/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unsw.curation.api.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;




/**
 *
 * @author Alireza
 */
public class SchIndData 
{  
    static String Pattern="(http:|https:|ftp)[:-_?\\a-zA-Z\\d.*//]+";
    public SchIndData(){} 
    Index dInd;
    DataSearch DSch;
    String twitterData="";
    String current = System.getProperty("user.dir");
    String SchTxt="";
    public static void delete(File file)
    	throws IOException{
 
    	if(file.isDirectory())
        {
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
    	}
        else
        {
    		file.delete();
    	}
    }
/*    private List<String>ReadData(String filePath) throws IOException
	{
		List<String> lstTw=new ArrayList<>();
		
		BufferedReader reader=new BufferedReader(new FileReader(filePath));
		String line="";
		while((line=reader.readLine())!=null)
		{
		  lstTw.add(line);	
		}
		return lstTw;
	}*/
     /*public void CreateIndex(String sentence) throws IOException, ParseException
     {
    	 //List<String>lstValues=ReadData(filePath);
         File fileCheck=new File(current+"\\File_Index");
         if(!fileCheck.exists())
         {
              fileCheck.mkdir();
              
              dInd = new Index(current+"\\File_Index\\");
              System.out.println("Start Indexing Data: "+System.currentTimeMillis());	
              for(String inputValues: lstValues)
              {
                dInd.IndexDocuments(inputValues);
              }
              System.out.println("Finished Indexing Data: "+System.currentTimeMillis());
              dInd.Close();
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
                      dInd = new Index(current+"\\File_Index\\");
                      System.out.println("Start Indexing Data: "+System.currentTimeMillis());	
                      for(String inputValues: lstValues)
                      {
                        dInd.IndexDocuments(inputValues);
                      }
                      System.out.println("Finished Indexing Data: "+System.currentTimeMillis());
                      dInd.Close();
                 }
                 else
                     if(answer.equalsIgnoreCase("n"))
                     {
                         System.out.println("Search "
                                     + "Based on the previous Indexed files...");
                     }
             }
     }*/
   public List<String> search(String token, String indexDir, int slop) throws IOException, ParseException
   {
	  List<String>lstSearch=new ArrayList<>();
      DSch = new DataSearch(indexDir);
      
      
          TopDocs hits = DSch.search(token, slop);
          //System.out.println(searchSentence+" "+hits.totalHits);
          for(ScoreDoc scoreDoc : hits.scoreDocs)
          {
        	 
             Document doc = DSch.getDocument(scoreDoc);
             lstSearch.add(doc.get("body"));
/*           lso.setTweet(doc.get("body"));
             lso.setNeType(searchSentence);*/
             //System.out.println(lso.getSimilarSentence());
             //System.out.println(lso.getScore());
             //lso.setNeExistance(true);
             //System.out.println(lso.getSimilarSentence());
             //System.out.println(searchSentence);
             //lso.setScore(String.valueOf(scoreDoc.score));

          }
      return lstSearch;
    }
  /* private String PreProcessSentence(String inputSentence)
   {
       inputSentence=inputSentence.replaceAll(Pattern, "");
             String [] arrSLine=inputSentence.split(" ");
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
             return Line;
   }*/
}
