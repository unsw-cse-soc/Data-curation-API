package unsw.curation.api.cosinesentence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


public class VectorGeneratorSentence 
{

    public int DocId;
    public String DocName;
    public VectorGeneratorSentence(int DocId,String DocName)
    {
        this.DocId=DocId;
        this.DocName=DocName;
    }
    DocVectorSentence[] docVector;
    private Map allterms;
    Integer totalNoOfDocumentInIndex;
    IndexReader indexReader;
    
    private List<VectorGeneratorSentence> lstData=new ArrayList<>();
    public void setLstData(VectorGeneratorSentence VG)
    {
        lstData.add(new VectorGeneratorSentence(VG.DocId, VG.DocName));
    }
    public List<VectorGeneratorSentence> getLstData()
    {
        return lstData;
    }
    
    public VectorGeneratorSentence() throws IOException
    {
       String current = System.getProperty("user.dir");
       allterms = new HashMap<>();
       indexReader=DirectoryReader.open(FSDirectory.open(new File(current+"\\IndexSentence\\")));
       totalNoOfDocumentInIndex=indexReader.maxDoc();
       docVector = new DocVectorSentence[totalNoOfDocumentInIndex];
    }
    
    public void GetAllTerms() throws IOException
    {
        AllTermsSentence allTerms = new AllTermsSentence();
        allTerms.initAllTerms();
        allterms = allTerms.getAllTerms();
    }
    public void ExtractVectorsName(int i) throws IOException
    {
        
        Document doc=indexReader.document(i);
        String docName=doc.get("contents");
        setLstData(new VectorGeneratorSentence(i, docName));

    }
    
    public DocVectorSentence[] GetDocumentVectors() throws IOException
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
            docVector[docId] = new DocVectorSentence(allterms);            
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