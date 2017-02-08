package unsw.curation.api.domain.abstraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import unsw.curation.api.domain.Classification;

public interface IClassificationTextLogisticRegression {

	void LoadDataset(File arffFileName) throws IOException;
	List<Classification> EvaluateLogisticRegression() throws Exception;
	void LearnLogisticRegression() throws Exception;
	void SaveModel(String modelName) throws FileNotFoundException, IOException;
}
