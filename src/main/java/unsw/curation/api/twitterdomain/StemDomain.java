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
public class StemDomain {
    public StemDomain(){}
    private String word1;
    public void setWord1(String word)
    {
        this.word1=word;
    }
    public String getWord1()
    {
        return word1;
    }
    private String derived1;
    public void setDerived1(String derived)
    {
        this.derived1=derived;
    }
    public String getDerived1()
    {
        return this.derived1;
    }
    
    private String word2;
    public void setWord2(String word)
    {
        this.word2=word;
    }
    public String getWord2()
    {
        return word2;
    }
    private String derived2;
    public void setDerived2(String derived)
    {
        this.derived2=derived;
    }
    public String getDerived2()
    {
        return this.derived2;
    }
    public StemDomain(String word1,String derived1,String word2,String derived2)
    {
        this.word1=word1;
        this.word2=word2;
        this.derived1=derived1;
        this.derived2=derived2;
    }
    
//    public StemDomain(StemDomain Domain)
//    {
//        this.word1=Domain.getWord1();
//        this.word2=Domain.getWord2();
//        this.derived1=Domain.getDerived1();
//        this.derived2=Domain.getDerived2();
//    }
}
