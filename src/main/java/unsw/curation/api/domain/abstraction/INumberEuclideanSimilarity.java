package unsw.curation.api.domain.abstraction;

import java.io.IOException;
import java.util.List;

import unsw.curation.api.domain.ExtractNumberSimilarity;



public interface INumberEuclideanSimilarity {

	double Euclidean_Vector_Vector(double [] number1,double [] number2);
	List<ExtractNumberSimilarity> Euclidean_Vector_VectorS(String filePath) throws IOException;
	List<ExtractNumberSimilarity> Euclidean_Vector_VectorS(double [] vector,String filePath) throws IOException;
}
