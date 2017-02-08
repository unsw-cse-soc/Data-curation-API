package unsw.curation.api.domain.abstraction;

import java.io.IOException;
import java.util.List;

import unsw.curation.api.domain.ExtractTextSimilarity;



public interface ITextLevenshtainSimilarity 
{

	List<ExtractTextSimilarity> Leveneshtain_Word_Document(String word1, String filePath) throws IOException;
	int Leveneshtain_Word_Word(String word1, String word2);
}
