package unsw.curation.api.cosinetext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;


public class VectorGenerator 
{

    public int DocId;
    public String DocName;
    public VectorGenerator(int DocId,String DocName)
    {
        this.DocId=DocId;
        this.DocName=DocName;
    }
    DocVector[] docVector;
    private Map allterms;
    Integer totalNoOfDocumentInIndex;
    IndexReader indexReader;
    
    private List<VectorGenerator> lstData=new ArrayList<>();
    public void setLstData(VectorGenerator VG)
    {
        lstData.add(new VectorGenerator(VG.DocId, VG.DocName));
    }
    public List<VectorGenerator> getLstData()
    {
        return lstData;
    }
    
    public VectorGenerator() throws IOException
    {
       String current = System.getProperty("user.dir");
       allterms = new HashMap<>();
       indexReader=DirectoryReader.open(FSDirectory.open(new File(current+"\\Index\\")));
       totalNoOfDocumentInIndex=indexReader.maxDoc();
       docVector = new DocVector[totalNoOfDocumentInIndex];
    }
    
    public void GetAllTerms() throws IOException
    {
        AllTerms allTerms = new AllTerms();
        allTerms.initAllTerms();
        allterms = allTerms.getAllTerms();
    }
    public void ExtractVectorsName(int i) throws IOException
    {
        
        Document doc=indexReader.document(i);
        String docName=doc.get("FileName");
        setLstData(new VectorGenerator(i, docName));

    }
    
    public DocVector[] GetDocumentVectors() throws IOException
    {
        for (int docId = 0; docId < totalNoOfDocumentInIndex; docId++) 
        {
            Terms vector = indexReader.getTermVector(docId, "contents");
//            Document doc=indexReader.document(docId);
//            String FileName=doc.get("FileName");
//            System.out.println(FileName+" "+docId);
            ExtractVectorsName(docId);
            TermsEnum termsEnum = null;
            termsEnum = vector.iterator(termsEnum);
            BytesRef text = null;            
            docVector[docId] = new DocVector(allterms);            
            while ((text = termsEnum.next()) != null) {
                String term = text.utf8ToString();
                int freq = (int) termsEnum.totalTermFreq();
                docVector[docId].setEntry(term, freq);
            }
            docVector[docId].normalize();
        }
        indexReader.close();
        return docVector;
    }
}