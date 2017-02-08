package unsw.curation.api.domain;


public class ExtractionKeyword {

	public ExtractionKeyword(){}
	public ExtractionKeyword(String tweet,String keyword)
	{
		this.tweet=tweet;
		this.keyword=keyword;
	}
	public ExtractionKeyword(String keyword)
	{
		this.keyword=keyword;
	}
	public String tweet;
	public String keyword;
	public String inputSentence;
	public String inputTweet;

	
	public void setInputSentence(String inputSentence)
	{
		this.inputSentence=inputSentence;
	}
	
	public String getInputSentence()
	{
		return inputSentence;
	}
	public void setInputTweet(String inputTweet)
	{
	  this.inputTweet=inputTweet;
	}
	public String getInputTweet()
	{
		return inputTweet;
	}
	public void setTweet(String tweet)
	{
		this.tweet=tweet;
	}
	public String getTweet()
	{
		return tweet;
	}
	
	public void setKeyword(String keyword)
	{
		this.keyword=keyword;
	}
	public String getKeyword()
	{
		return keyword;
	}
	
}
