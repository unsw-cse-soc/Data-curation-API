package unsw.curation.api.domain.abstraction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import unsw.curation.api.domain.ExtractionKeyword;

public interface IKeywordEx {

	String ExtractTweetKeyword(String inputTweet,File stopWordList) throws Exception;
	List<ExtractionKeyword> ExtractTweetKeywordFromFile(File fileName, File stopWordList) throws FileNotFoundException, IOException;
	String ExtractSentenceKeyword(String inputSentence, File stopWordList) throws Exception;
	//String ExtractSentenceKeyPhrase(String inputSentence,File stopWordList) throws Exception;
	String ExtractFileKeyword(File fileName, File stopWordList) throws FileNotFoundException, IOException;
	/*ExtractionKeyword ExtractSentenceKeywords(String inputSentence) throws Exception;
	ExtractionKeyword ExtractFileKeywords(String inputFilePath) throws Exception;*/
}
