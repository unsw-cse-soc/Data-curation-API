package unsw.curation.api.domain.abstraction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IUrlExtraction {

	String ExtractTitle(String url) throws IOException;
	List<String> ExtractHeadings(String url) throws IOException;
	List<String> ExtractHrefText(String url) throws IOException;
	List<String> ExtractParagraphes(String url) throws IOException;
	List<String> ExtractImageALTtext(String url) throws IOException;
}
