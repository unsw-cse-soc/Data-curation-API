package unsw.curation.api.domain;


public class ExtractTextCosineSimilarity {

	public ExtractTextCosineSimilarity(){}
    public ExtractTextCosineSimilarity(String DocName, String DocCandidate,double Similarity)
    {
        this.DocName=DocName;
        this.DocCandidate=DocCandidate;
        this.Similarity=Similarity;
    }
    public String query;
    public void setQuery(String query)
    {
    	this.query=query;
    }
    public String getQuery()
    {
    	return this.query;
    }
    public String DocName;
    public void setDocName(String DocName)
    {
        this.DocName=DocName;
    }
    public String getDocName()
    {
        return DocName;
    }
    public String DocCandidate;
    public void setDocCandidate(String DocCandidate)
    {
        this.DocCandidate=DocCandidate;
    }
    public String getDocCandidate()
    {
        return this.DocCandidate;
    }
    public double Similarity;
    public void setSimilairty(double CosineSimilarity)
    {
        this.Similarity=CosineSimilarity;
    }
    public double getSimilarity()
    {
        return this.Similarity;
    }

}
