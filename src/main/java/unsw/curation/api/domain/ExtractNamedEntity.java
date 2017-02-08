package unsw.curation.api.domain;

public class ExtractNamedEntity {

	public ExtractNamedEntity()
	{
		
	}
	
	 public String word;
	 public String ner;
	 public int position;
	/*public ExtractNamedEntity(String word,String ner)
	{
		this.word=word;
		this.ner=ner;
	}*/
	public ExtractNamedEntity(String word,String ner,int position)
	{
		this.word=word;
		this.ner=ner;
		this.position=position;
	}
	 public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}	 
	 public void setWord(String word)
	 {
		 this.word=word;
	 }
	 public String getWord()
	 {
		 return this.word;
	 }
	 
	 public void setNer(String ner)
	 {
		 this.ner=ner;
	 }
	 public String getNer()
	 {
		 return this.ner;
	 }
}
