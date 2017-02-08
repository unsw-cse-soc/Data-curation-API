package unsw.curation.api.domain.abstraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import unsw.curation.api.domain.ExtractStem;

public interface IStem {

	void ReadDataset() throws FileNotFoundException, IOException, URISyntaxException;
	List<ExtractStem> FindWordDerivedForms(String word) throws FileNotFoundException, IOException, URISyntaxException;
}
