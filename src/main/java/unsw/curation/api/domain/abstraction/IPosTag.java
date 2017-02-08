package unsw.curation.api.domain.abstraction;

import java.io.File;
import java.util.List;

import unsw.curation.api.domain.ExtractPosTag;

public interface IPosTag {

	List<ExtractPosTag> ExtractNoun(String sentence);
	List<ExtractPosTag> ExtractAdjective(String sentence);
	List<ExtractPosTag> ExtractAdverb(String sentence);
	List<ExtractPosTag> ExtractVerb(String sentence);
	List<ExtractPosTag> ExtractQuotaion(String sentence);
	List<String> ExtractPhrase(String sentence);
	List<ExtractPosTag> ExtractNoun(File filePath)throws Exception;
	List<ExtractPosTag> ExtractAdjective(File filePath)throws Exception;
	List<ExtractPosTag> ExtractAdverb(File filePath) throws Exception;
	List<ExtractPosTag> ExtractVerb(File filePath) throws Exception;
	
	List<ExtractPosTag> ExtractPosTagsSentence(String sentence);
	List<ExtractPosTag> ExtractPosTagsSentenceNew(String sentence);
	List<ExtractPosTag> ExtractPosTagsFile(File filePath) throws Exception;
	
	List<String> ExtractData(File filePath) throws Exception;
	List<ExtractPosTag> ExtractPosTags(List<String> inputData);
}
