package unsw.curation.api.domain.abstraction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ISynonym {

	List<String> ExtractSynonymWord(String word) throws URISyntaxException, IOException;
	List<String> ExtractHypernymWord(String word);
}
