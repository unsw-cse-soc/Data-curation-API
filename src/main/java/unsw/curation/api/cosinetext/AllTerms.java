package unsw.curation.api.cosinetext;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;


public class AllTerms {
    private Map<String,Integer> allTerms;
    Integer totalNoOfDocumentInIndex;
    IndexReader indexReader;
    
    public AllTerms() throws IOException
    {    
        allTerms = new HashMap<>();
        String current = System.getProperty("user.dir");
         indexReader = DirectoryReader
                .open(FSDirectory.open(new File(current+"\\Index\\")));
        totalNoOfDocumentInIndex = indexReader.maxDoc();
    }
        
    public void initAllTerms() throws IOException
    {
        int pos = 0;
        for (int docId = 0; docId < totalNoOfDocumentInIndex; docId++) {
            Terms vector = indexReader.getTermVector(docId, "contents");
            TermsEnum termsEnum = null;
            termsEnum = vector.iterator(termsEnum);
            BytesRef text = null;
            while ((text = termsEnum.next()) != null) {
                String term = text.utf8ToString();
                allTerms.put(term, pos++);
            }
        }       
        pos = 0;
        for(Entry<String,Integer> s : allTerms.entrySet())
        {        
           s.setValue(pos++);
        }
    }
    public Map<String, Integer> getAllTerms() {
        return allTerms;
    }
}
