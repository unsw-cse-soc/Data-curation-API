package unsw.curation.api.extractsimilarity;


import info.debatty.java.stringsimilarity.QGram;
import unsw.curation.api.domain.abstraction.ITextQGramSimilarity;


public class ExtractTextQGramSimilarity implements ITextQGramSimilarity {

	@Override
	public double ComputeQGramSimilarity(String word1, String word2) {
		QGram qG=new QGram();
		double qGramDistance=qG.distance(word1, word2);
		
		return qGramDistance;
	}

}
