/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unsw.curation.api.tfidf;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Alireza
 */
public class IndexSentence {
    private IndexWriter writer;
    private StandardAnalyzer Analyzer=new StandardAnalyzer(Version.LUCENE_41);
    public IndexSentence(String indexDirectory) throws IOException
    {
      FSDirectory indexDir=FSDirectory.open(new File(indexDirectory));
         IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_41,Analyzer);
	 writer=new IndexWriter(indexDir,config);
    }
    public void Close() throws CorruptIndexException, IOException
    {
	  writer.close();
    }
    
    private Document ListDoc(String  tweet) throws IOException
    {
        Document doc=new Document();
//        Field fileContent=new Field("Content",new FileReader("FileName Must Be Provided"));
//        doc.add(fileContent);
    doc.add(new TextField("Content",tweet, Field.Store.YES));
    //doc.add(new TextField("FilePath",file.getCanonicalPath(),Field.Store.YES));
    return doc;
    }
      private void IndexDocuments(String tweetFilePath) throws IOException{
      System.out.println("Indexing Sentences  ");
      List<String> lstProcessedData=ReadDataSentence.ReadPreProcessedData(tweetFilePath);
      for(String tweet:lstProcessedData)
          {
              try
              {
              Document document = ListDoc(tweet);
              writer.addDocument(document);
              }
              catch(Exception ex)
              {         
                  System.out.print(ex.getMessage());
              }
          }
      }
      public boolean IndexTweets(String tweetFilePath) 
      throws IOException{
      //File[] files = new File(dataDir).listFiles();
     //File f=new File(tweetFilePath);
      //for (File file : files) {
        // if(!tweetFilePath.isDirectory()&& tweetFilePath.exists())
         //{
            IndexDocuments(new File(tweetFilePath).getPath());
         //}
        /* else
         {
             return false;
         }*/
     // }
      //return writer.numDocs();
      return true;
   }
     
}
