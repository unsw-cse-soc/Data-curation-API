package unsw.curation.api.domain;


public class ExtractTextTfidfSimilarity {

	private String query;

	private String sentence;
    private String similarSentence;
    private String score;
    public ExtractTextTfidfSimilarity(){}
    
    public ExtractTextTfidfSimilarity(String searchText, String similarSentence, String score) {
		this.sentence=searchText;
		this.similarSentence=similarSentence;
		this.score=score;
	}
    public void setQuery(String query)
    {
    	this.query=query;
    }
    public String getQuery()
    {
    	return this.query;
    }
	public void setSentence(String sentence)
    {
    	this.sentence=sentence;
    }
    public String getSentence()
    {
    	return this.sentence;
    }
    
    public void setSimilaritySentence(String similaritySentence)
    {
    	this.similarSentence=similaritySentence;
    }
    
    public String getSimilaritySentence()
    {
    	return this.similarSentence;
    }
    
    public void serScore(String score)
    {
    	this.score=score;
    }
    public String getScore()
    {
    	return this.score;
    }
}
