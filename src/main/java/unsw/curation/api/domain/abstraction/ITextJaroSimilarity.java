package unsw.curation.api.domain.abstraction;

public interface ITextJaroSimilarity {

	public double ComputeJaroSimilarity(String Word1,String word2);
}
