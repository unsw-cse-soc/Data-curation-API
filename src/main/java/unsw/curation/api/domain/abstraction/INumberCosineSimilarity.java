package unsw.curation.api.domain.abstraction;

import java.io.IOException;
import java.util.List;

import unsw.curation.api.domain.ExtractNumberSimilarity;



public interface INumberCosineSimilarity {

	double Cosine_Vector_Vector(double [] number1,double [] number2);
	List<ExtractNumberSimilarity> Cosine_Vector_VectorS(String filePath) throws IOException;
	List<ExtractNumberSimilarity> Cosine_Vector_VectorS(double [] vector,String filePath) throws IOException;
	
}
