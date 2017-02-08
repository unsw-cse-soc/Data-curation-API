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
public class NamedEntityDomain {
    
    public String _Word;
    public String _Ner;
    
    public void setWord(String _Word)
    {
        this._Word=_Word;
    }
    public String getWord()
    {
        return _Word;
    }
    
    public void setNer(String _Ner)
    {
        this._Ner=_Ner;
    }
    public String getNer()
    {
        return _Ner;
    }
//    private String HashTag;
//    private String Text;
//    private String Domain;
//    private String KeyWords;
//    private String Synonyms;
//    private String Links;
//    private String Entities;
    public NamedEntityDomain(){}
    
    public NamedEntityDomain(String word,String Ner)
    {
        _Word=word;
        _Ner=Ner;
    }
//    public NamedEntityDomain(String Text,String Entities,String HashTag,String Domain, String KeyWords,String Synonyms,String Links)
//    {
//        this.HashTag=HashTag;
//        this.Text=Text;
//        this.Entities=Entities;
//        this.Domain=Domain;
//        this.KeyWords=KeyWords;
//        this.Synonyms=Synonyms;
//        this.Links=Links;
//    }
}
