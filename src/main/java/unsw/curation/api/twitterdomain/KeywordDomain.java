/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unsw.curation.api.twitterdomain;

/**
 *
 * @author Alireza
 */
public class KeywordDomain {
    
    public KeywordDomain(){}
	public KeywordDomain(String tweet,String keyword)
	{
		this.tweet=tweet;
		this.keyword=keyword;
	}
	public KeywordDomain(String keyword)
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
