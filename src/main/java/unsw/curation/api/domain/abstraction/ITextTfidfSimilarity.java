package unsw.curation.api.domain.abstraction;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import unsw.curation.api.domain.ExtractTextTfidfSimilarity;


public interface ITextTfidfSimilarity 
{
	//List<ExtractTextTfidfSimilarity> SearchFile(String FilePath) throws IOException, ParseException;
	List<ExtractTextTfidfSimilarity> SearchText(String searchText) throws IOException, ParseException;
	void CreateIndex(String IndexFilePath) throws IOException, ParseException;
	void delete(File file) throws IOException;
}
