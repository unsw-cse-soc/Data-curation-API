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
public class SynonymDomain {
    
    public SynonymDomain(){}
    public SynonymDomain(String word, String synset)
    {
        this.word=word;
        this.synset=synset;
    }
    public String word;
    public String synset;
    
    public void setWord(String word)
    {
        this.word=word;
    }
    public String getWord()
    {
        return this.word;
    }
    public void setSynset(String synset)
    {
        this.synset=synset;
    }
    public String getSynset()
    {
        return synset;
    }
}
