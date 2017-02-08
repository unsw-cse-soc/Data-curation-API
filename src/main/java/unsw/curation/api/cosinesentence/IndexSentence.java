package unsw.curation.api.cosinesentence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;


public class IndexSentence {

    private final File sourceFileName;
    private final File indexDirectory;
    private static String fieldName;
    private final String QueryText;

    public IndexSentence(String fileName, String Query)
    {
       QueryText=Query;
       String current = System.getProperty("user.dir");
       this.sourceFileName = new File(fileName);
       this.indexDirectory = new File(current+"\\IndexSentence\\");
       fieldName="contents";
    }
    public void index() throws CorruptIndexException,
        LockObtainFailedException, IOException {
        Directory dir = FSDirectory.open(indexDirectory);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_41,StandardAnalyzer.STOP_WORDS_SET);
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_41, analyzer);
        if (indexDirectory.exists()) {
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        } else {
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }
        IndexWriter writer = new IndexWriter(dir, iwc);
        List<String> lstText=ExtractText(sourceFileName);
        for (String f : lstText) 
        {
            System.out.println("Indexing Sentences...  ");//+f.getName());
            Document doc = new Document();
            FieldType fieldType = new FieldType();
            fieldType.setIndexed(true);
            fieldType.setIndexOptions(FieldInfo.IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            fieldType.setStored(true);
            fieldType.setStoreTermVectors(true);
            fieldType.setTokenized(true);
            Field contentField = new Field(fieldName, f, fieldType);
            doc.add(contentField);
            writer.addDocument(doc);
        }
            Document doc = new Document();
            FieldType fieldType = new FieldType();
            fieldType.setIndexed(true);
            fieldType.setIndexOptions(FieldInfo.IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            fieldType.setStored(true);
            fieldType.setStoreTermVectors(true);
            fieldType.setTokenized(true);
            Field contentField = new Field(fieldName, QueryText, fieldType);
            doc.add(contentField);
            writer.addDocument(doc);
        System.out.println("Indexing Finished...  ");
        writer.close();
    }
    public List<String> ExtractText(File f) throws FileNotFoundException, IOException 
    {
        List<String> lstValues=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(f));
        String Line="";
        while((Line=reader.readLine())!=null)
        {
            lstValues.add(Line);
        }
        return lstValues;
    }
    

}