package unsw.curation.api.domain.abstraction;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.lucene.store.LockObtainFailedException;

import unsw.curation.api.domain.ExtractTextCosineSimilarity;


public interface ITextCosineSimilarity {

	List<ExtractTextCosineSimilarity> Cosine_Document_DocumentS(String QueryFilePath, String DataDirectoryPath) 
			throws LockObtainFailedException, IOException, URISyntaxException;
	//List<ExtractTextCosineSimilarity> Cosine_Sentence_Document(String Query, String FileName) throws LockObtainFailedException, IOException;
	//public List<String> ExtractListKeyword(List<String> lstSentence) throws Exception;
}
