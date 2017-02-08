package unsw.curation.api.cosinetext;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;


public class Index {

    private final File sourceDirectory;
    private final File indexDirectory;
    private static String fieldName;

    public Index(String DataDir)
    {
       String current = System.getProperty("user.dir");
       this.sourceDirectory = new File(DataDir);
       this.indexDirectory = new File(current+"\\Index\\");
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
        for (File f : sourceDirectory.listFiles()) {
            System.out.println("Indexing Document  "+f.getName());
            Document doc = new Document();
            FieldType fieldType = new FieldType();
            fieldType.setIndexed(true);
            fieldType.setIndexOptions(FieldInfo.IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            fieldType.setStored(true);
            fieldType.setStoreTermVectors(true);
            fieldType.setTokenized(true);
            Field contentField = new Field(fieldName, ExtractText(f), fieldType);
            doc.add(contentField);
            doc.add(new TextField("FileName", f.getName(), Field.Store.YES));
            doc.add(new TextField("FilePath",f.getCanonicalPath(),Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.close();
    }
    public String ExtractText(File f) throws FileNotFoundException, IOException 
    {
        String textFileContent = "";
        for (String line : Files.readAllLines(Paths.get(f.getAbsolutePath())))
        {
            textFileContent += line;
        }
        return textFileContent;
    }
    

}