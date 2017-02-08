package unsw.curation.api.domain;

public class ExtractNumberSimilarity {

	private String vector1;
	private String vector2;
	private double score;
	
	public ExtractNumberSimilarity(){}
	
	public ExtractNumberSimilarity(String vector1,String vector2,double score)
	{
		this.vector1=vector1;
		this.vector2=vector2;
		this.score=score;
	}
	
	public void setVector1(String vector1)
	{
		this.vector1=vector1;
	}
	public String getVector1()
	{
		return this.vector1;
	}
	
	public void setVecor2(String vector2)
	{
		this.vector2=vector2;
	}
	public String getVector2()
	{
		return this.vector2;
	}
	public void setScore(double score)
	{
		this.score=score;
	}
	public double getScore()
	{
		return this.score;
	}
}
