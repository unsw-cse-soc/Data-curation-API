package unsw.curation.api.index;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.document.Field;



/**
 *
 * @author Alireza
 */
public class Index {
    private IndexWriter writer;
    private StandardAnalyzer Analyzer=new StandardAnalyzer(Version.LUCENE_46);
    
    public Index(String indexDirectory) throws IOException
    {
     FSDirectory indexDir=FSDirectory.open(new File(indexDirectory));
     IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_46,Analyzer);
	 writer=new IndexWriter(indexDir,config);
    }
    public void Close() throws CorruptIndexException, IOException
    {
	  writer.close();
    }
    
    private Document ListDoc(String text) throws IOException
    {
      Document doc=new Document();
      doc.add(new TextField("body",text,Field.Store.YES));
      //doc.add(new TextField("tweet",inputMongo.getBody(), Field.Store.YES));
      //doc.add(new TextField("description", inputMongo.getDescription(), Field.Store.YES));
      //System.out.println("Name  "+inputMongo.getBody());
     // doc.add(new TextField("displayName",inputMongo.getDisplayName(), Field.Store.YES));
     //System.out.println("Indexing: "+inputMongo.getId()+" "+inputMongo.getBody()+" "+inputMongo.getDescription()+" "+inputMongo.getDisplayName());
      return doc;
    }
    public void IndexDocuments(String getValues) throws IOException
    {
    // for(LuceneData<String, String> mongoVal:getLstMongoValues)
         // {
              try
              {
              Document document = ListDoc(getValues);
              
              writer.addDocument(document);
              }
              catch(Exception ex)
              {         
                  System.out.print(ex.getMessage());
              }
       //   }
     
    }
     
}
