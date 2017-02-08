package unsw.curation.api.domain.abstraction;

import java.io.IOException;
import java.util.List;

import unsw.curation.api.domain.ExtractNumberSimilarity;


public interface INumberDiceSimilarity {

	double Dice_Vector_Vector(double [] number1,double [] number2);
	List<ExtractNumberSimilarity> Dice_Vector_VectorS(String filePath) throws IOException;
	List<ExtractNumberSimilarity> Dice_Vector_VectorS(Double [] vector,String filePath) throws IOException;
}
