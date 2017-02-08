package unsw.curation.api.extractsimilarity;


import org.apache.commons.lang3.StringUtils;

import unsw.curation.api.domain.abstraction.ITextJaroSimilarity;

public class ExtractTextJaroSimialrity implements ITextJaroSimilarity {

	@Override
	public double ComputeJaroSimilarity(String Word1, String word2) {
		double jaroSimilarity=StringUtils.getJaroWinklerDistance(Word1, word2);
		return jaroSimilarity;
	}

}
