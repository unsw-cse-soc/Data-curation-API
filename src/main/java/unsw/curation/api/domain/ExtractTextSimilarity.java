package unsw.curation.api.domain;

public class ExtractTextSimilarity {

	private String word;
    public void setWord(String word)
    {
        this.word=word;
    }
    public String getWord()
    {
        return this.word;
    }
    private String candidate;
    public void setCandidate(String candidate)
    {
        this.candidate=candidate;
    }
    public String getCandidate()
    {
        return this.candidate;
    }
    private double similarity;
    public void setSimilarity(double similarity)
    {
        this.similarity=similarity;
    }
    public double getSimilarity()
    {
        return this.similarity;
    }
    public ExtractTextSimilarity(){}
    public ExtractTextSimilarity (String Word, String Candidate, double Similarity)
    {
        this.word=Word;
        this.candidate=Candidate;
        this.similarity=Similarity;
    }
}
