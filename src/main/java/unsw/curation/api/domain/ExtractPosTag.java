package unsw.curation.api.domain;

public class ExtractPosTag {
	 
  public ExtractPosTag(){}
  public ExtractPosTag(String wordPart,String tag)
  {
	  this.wordPart=wordPart;
	  this.tag=tag;
  }
  private String wordPart;
  private String tag;
  private int itemCount;
  
  public void setWordPart(String wordPart)
  {
	  this.wordPart=wordPart;
  }
  public String getWordPart()
  {
	  return this.wordPart;
  }
  public void setTag(String tag)
  {
	  this.tag=tag;
  }
  public String getTag()
  {
	  return this.tag;
  }
  public void setItemCount(int itemCount)
  {
	  this.itemCount=itemCount;
  }
  public int getItemCount()
  {
	  return this.itemCount;
  }
}
