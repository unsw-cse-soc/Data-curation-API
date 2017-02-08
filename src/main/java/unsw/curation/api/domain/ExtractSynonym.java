package unsw.curation.api.domain;

public class ExtractSynonym {

	public ExtractSynonym(){}
	public ExtractSynonym(String word,String synset)
	{
		this.word=word;
		this.synset=synset;
	}
	private String word;
	
	private String synset;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSynset() {
		return synset;
	}

	public void setSynset(String synset) {
		this.synset = synset;
	}
	
}
