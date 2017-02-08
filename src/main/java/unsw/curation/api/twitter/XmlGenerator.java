package unsw.curation.api.twitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
//import twitter.abstraction.impl.Synonyms;
//import twitter.abstraction.impl.NamedEntityExtraction;
import unsw.curation.api.twitterdomain.*;
//import twitter.abstraction.impl.MyStemExtraction;
public class XmlGenerator {

    XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();	
    XMLStreamWriter xMLStreamWriter;
    Synonyms syn;
    URLExtraction UE;
    NamedEntityExtraction NEE;
    KeywordExtraction KE;
     MyStemExtraction SE;
    File stopwordsPath=new File("EnglishStopWords.txt");
    File stemFilePath=new File("Stem.txt");
    List<StemDomain>lstStems=new ArrayList<>();
    List<StemDomain>lstWordForms=new ArrayList<>();
    List<StemDomain>lstDescWordForms=new ArrayList<>();
    List<StemDomain>lstWordFormsLINK=new ArrayList<>();
    List<StemDomain>lstAllListForms=new ArrayList<>();

    public XmlGenerator(File englishStopwordsFilePath) throws XMLStreamException, FileNotFoundException, IOException
    {
        stopwordsPath=englishStopwordsFilePath;
        SE=new MyStemExtraction();
        syn=new Synonyms();
        UE=new URLExtraction();
        NEE=new NamedEntityExtraction();
        KE=new KeywordExtraction();
        this.xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter
                (new FileOutputStream("Resulttt.xml"));
        
    }
    
	public void StartXML() throws XMLStreamException, FileNotFoundException, IOException
        {
        xMLStreamWriter.writeStartDocument();
        xMLStreamWriter.writeStartElement("Twitter");
        lstStems=SE.ReadData(stemFilePath);
        }
	public void CloseXMLFile() throws XMLStreamException
        {
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndDocument();
        xMLStreamWriter.flush();
        xMLStreamWriter.close();
        }
	  // twitter.abstraction.impl.KeywordExtraction ke=new twitter.abstraction.impl.KeywordExtraction();
	 public void AddToXml(TweetInfoDomain tweetDomain) throws XMLStreamException, FileNotFoundException, Exception
	 {
             
	   xMLStreamWriter.writeStartElement("tweets");
	   
               xMLStreamWriter.writeStartElement("tweet");
	   
                   xMLStreamWriter.writeStartElement("tweetID");
                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getTweetID()));
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("text");
                   xMLStreamWriter.writeCharacters(tweetDomain.getText()+" ");
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("NamedEntities");
                 
                   List<NamedEntityDomain> lstEntities=NEE.ExtractTweetNamedEntities(tweetDomain.getText());
                   if(lstEntities.size()>0)
                   {
                       for(NamedEntityDomain ned:lstEntities)
                       {
                          xMLStreamWriter.writeStartElement("NamedEntity");
                            xMLStreamWriter.writeAttribute("Entity",ned._Word);
                            xMLStreamWriter.writeCharacters(ned._Ner);
                          xMLStreamWriter.writeEndElement();
                       }
                   }
                   else
                   {
                       xMLStreamWriter.writeStartElement("NamedEntity");
                       xMLStreamWriter.writeEndElement();
                   }
                   
                   xMLStreamWriter.writeEndElement();
                   
                   String [] str=tweetDomain.getText().split(" ");
                   String hashTags="";
                   for(String s:str)
                   {
                       if(s.contains("#"))
                           hashTags+=s+",";
                   }
                   xMLStreamWriter.writeStartElement("HashTags");
                   xMLStreamWriter.writeCharacters(hashTags);
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("Synonyms");
                   List<SynonymDomain> lstSyns=syn.ExtractSynsetsSentence(tweetDomain.getText(), stopwordsPath);
                   if(lstSyns.size()>0)
                   {
                       for(SynonymDomain getSyns:lstSyns)
                       {
                         xMLStreamWriter.writeStartElement("Synonym");
                           xMLStreamWriter.writeAttribute("keyword",getSyns.word);
                           xMLStreamWriter.writeCharacters(getSyns.synset);
                         xMLStreamWriter.writeEndElement();
                       }
                   }
                   else
                   {
                      xMLStreamWriter.writeStartElement("Synonym");
                      xMLStreamWriter.writeEndElement();
                   }
                   xMLStreamWriter.writeEndElement();
                   try
                   {
                    String [] keyList=KE.ExtractTweetKeyword(tweetDomain.getText(), stopwordsPath).split(",");
                    
                    for(String myKey:keyList)
                    {
                        List<StemDomain>lstTempForm=lstStems.stream()
                           .filter(c->c.getWord1().equalsIgnoreCase(myKey.trim()))
                           .collect(Collectors.toList());
                        lstWordForms.addAll(lstTempForm);
                    }
                   }
                   catch(Exception ex)
                   {
                       
                   }
                    xMLStreamWriter.writeStartElement("Stems");
                    try
                    {
                    List<StemDomain>distinctItems=lstWordForms.stream().distinct().collect(Collectors.toList());
                    for(StemDomain sForm:distinctItems)
                    {
                        xMLStreamWriter.writeStartElement("Stem");
                         xMLStreamWriter.writeAttribute("Word",sForm.getWord1());
                           xMLStreamWriter.writeCharacters(sForm.getDerived1());
                        xMLStreamWriter.writeEndElement();
                        
                        xMLStreamWriter.writeStartElement("Stem");
                         xMLStreamWriter.writeAttribute("Word",sForm.getWord2());
                           xMLStreamWriter.writeCharacters(sForm.getDerived2());
                        xMLStreamWriter.writeEndElement();
                    }
                    lstWordForms.clear();
                    }
                    catch(Exception ex)
                    {}
                    xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("source");
                   xMLStreamWriter.writeCharacters(tweetDomain.getSource());
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("CreatedDate");
                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getCreateDate()));
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("Language");
                   xMLStreamWriter.writeCharacters(tweetDomain.getLanguage());
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("RetweetedCount");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getRetweetCount()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("IsPossiblySentive");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getIsPossiblySensetive()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("Links");
                   
                    xMLStreamWriter.writeStartElement("Title");
                    List<String> lstTitle=UE.ExtractTitle(tweetDomain.getText());
                    for(String title:lstTitle)
                    {
                    xMLStreamWriter.writeCharacters(title);
                    }
                    xMLStreamWriter.writeEndElement();
                   
                   
                   List<String> lstPargraphes=UE.ExtractLinkInfo(tweetDomain.getText());
                   String StringList=String.join(" ",lstPargraphes);
                   
                    xMLStreamWriter.writeStartElement("Paragraph");
                    for(String text:lstPargraphes)
                    {
                    xMLStreamWriter.writeCharacters(text);
                    }
                    xMLStreamWriter.writeEndElement();
                   
                   
//                    xMLStreamWriter.writeStartElement("Keywords");
//                    String [] linkKeyword=KE.ExtractKeyword(StringList, stopwordsPath).split(",");
//                    for(String linkKey:linkKeyword)
//                    {
//                       xMLStreamWriter.writeCharacters(linkKey+",");
//                    }
//                    xMLStreamWriter.writeEndElement();
                    
     
//                   List<SynonymDomain> StringSyns=syn.ExtractSynsetsSentence(StringList, stopwordsPath);
//                   xMLStreamWriter.writeStartElement("Synonyms");
//                   for(SynonymDomain getSyns:StringSyns)
//                   {
//                     xMLStreamWriter.writeStartElement("Synonym");
//                     xMLStreamWriter.writeAttribute("keyword",getSyns.word);
//                       xMLStreamWriter.writeCharacters(getSyns.synset);
//                     xMLStreamWriter.writeEndElement();
//                   }      
//                   xMLStreamWriter.writeEndElement();
//                   
//                    try
//                   {
//                    String StringListLink=String.join(" ",lstPargraphes);
//                    String [] ListLinkWord=KE.ExtractKeyword(StringListLink, stopwordsPath).split(",");
//                    
//                    for(String myKey:ListLinkWord)
//                    {
//                        List<StemDomain>lstlinkForm=lstStems.stream()
//                           .filter(c->c.getWord1().equalsIgnoreCase(myKey.trim()))
//                           .collect(Collectors.toList());
//                        lstAllListForms.addAll(lstlinkForm);
//                    }
//                   }
//                   catch(Exception ex)
//                   {
//                   }
//                    xMLStreamWriter.writeStartElement("Stems");
//                    try
//                    {
//                    List<StemDomain>lstLinkItems=lstAllListForms.stream().distinct().collect(Collectors.toList());
//                    for(StemDomain sForm:lstLinkItems)
//                    {
//                        xMLStreamWriter.writeStartElement("Stem");
//                         xMLStreamWriter.writeAttribute("Word",sForm.getWord1());
//                           xMLStreamWriter.writeCharacters(sForm.getDerived1());
//                        xMLStreamWriter.writeEndElement();
//                        
//                        xMLStreamWriter.writeStartElement("Stem");
//                         xMLStreamWriter.writeAttribute("Word",sForm.getWord2());
//                           xMLStreamWriter.writeCharacters(sForm.getDerived2());
//                        xMLStreamWriter.writeEndElement();
//                    }
//                    lstAllListForms.clear();
//                    }
//                    catch(Exception ex)
//                    {}
//                    xMLStreamWriter.writeEndElement();
                   
                   
                   
                   
                    //End of Link Tags
                   xMLStreamWriter.writeEndElement();
                   
                   /* xMLStreamWriter.writeStartElement("Country");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getCountry()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("CountryCode");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getCountryCode()));
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("Country");
                   xMLStreamWriter.writeCharacters(tweetDomain.getCountry());
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("StreetAddress");
                   xMLStreamWriter.writeCharacters(tweetDomain.getStreetAddress());
                   xMLStreamWriter.writeEndElement();*/
	   
               xMLStreamWriter.writeEndElement();
	   
               xMLStreamWriter.writeStartElement("users");
               
//                   xMLStreamWriter.writeStartElement("DisplayName");
//                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getName()));
//                   xMLStreamWriter.writeEndElement();
//	   
                   xMLStreamWriter.writeStartElement("userID");
                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getUserID()));
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("Description");
                   
                   
                   xMLStreamWriter.writeStartElement("Text");
                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getDescription()));
                   xMLStreamWriter.writeEndElement();
                   
                    xMLStreamWriter.writeStartElement("Keywords");
                    try
                    {
                    String [] keyDescription=KE.ExtractKeyword(tweetDomain.getDescription(), stopwordsPath).split(",");
                    for(String sForm:keyDescription)
                    {
                        xMLStreamWriter.writeStartElement("Keyword");
                           xMLStreamWriter.writeCharacters(sForm);
                        xMLStreamWriter.writeEndElement();
                    }
                    }
                    catch(Exception ex){}
                    
                    xMLStreamWriter.writeEndElement();
                   xMLStreamWriter.writeStartElement("NamedEntities");
                 
                   try
                   {
                       List<NamedEntityDomain> lstDescEntities=NEE.ExtractTweetEntities(tweetDomain.getDescription());
                       if(lstDescEntities.size()>0)
                       {
                           for(NamedEntityDomain ned:lstDescEntities)
                           {
                              xMLStreamWriter.writeStartElement("NamedEntity");
                                xMLStreamWriter.writeAttribute("Entity",ned._Word);
                                xMLStreamWriter.writeCharacters(ned._Ner);
                              xMLStreamWriter.writeEndElement();
                           }
                       }
                       else
                       {
                           xMLStreamWriter.writeStartElement("NamedEntity");
                           xMLStreamWriter.writeEndElement();
                       }
                   }
                catch(Exception ex)
                       {
                           xMLStreamWriter.writeStartElement("NamedEntity");
                           xMLStreamWriter.writeEndElement();
                       }
                  
                   
                   xMLStreamWriter.writeEndElement();
                   
                   
                    xMLStreamWriter.writeStartElement("Synonyms");
                   try
                   {
                       List<SynonymDomain> lstSynsDescription=syn.ExtractSynsetsSentence(tweetDomain.getDescription(), stopwordsPath);
                       if(lstSynsDescription.size()>0)
                       {
                           for(SynonymDomain getSyns:lstSynsDescription)
                           {
                             xMLStreamWriter.writeStartElement("Synonym");
                             xMLStreamWriter.writeAttribute("keyword",getSyns.word);
                               xMLStreamWriter.writeCharacters(getSyns.synset);
                             xMLStreamWriter.writeEndElement();
                           }
                       }
                       else
                       {
                          xMLStreamWriter.writeStartElement("Synonym");
                          xMLStreamWriter.writeEndElement();
                       }
                   }
                   catch(Exception ex)
                           {
                               xMLStreamWriter.writeStartElement("Synonym");
                               xMLStreamWriter.writeEndElement();
                           }
                   xMLStreamWriter.writeEndElement();
                   
                   
                   
                    try
                   {
                    String [] descList2=KE.ExtractTweetKeyword(tweetDomain.getDescription(), stopwordsPath).split(",");
                    
                    for(String myKey:descList2)
                    {
                        List<StemDomain>lstdescForm2=lstStems.stream()
                           .filter(c->c.getWord1().equalsIgnoreCase(myKey.trim()))
                           .collect(Collectors.toList());
                        lstDescWordForms.addAll(lstdescForm2);
                    }
                   }
                   catch(Exception ex)
                   {
                   }
                    xMLStreamWriter.writeStartElement("Stems");
                    try
                    {
                    List<StemDomain>lstDescdistinctItems2=lstDescWordForms.stream().distinct().collect(Collectors.toList());
                    for(StemDomain sForm2:lstDescdistinctItems2)
                    {
                        xMLStreamWriter.writeStartElement("Stem");
                         xMLStreamWriter.writeAttribute("Word",sForm2.getWord1());
                           xMLStreamWriter.writeCharacters(sForm2.getDerived1());
                        xMLStreamWriter.writeEndElement();
                        
                        xMLStreamWriter.writeStartElement("Stem");
                         xMLStreamWriter.writeAttribute("Word",sForm2.getWord2());
                           xMLStreamWriter.writeCharacters(sForm2.getDerived2());
                        xMLStreamWriter.writeEndElement();
                    }
                    lstDescWordForms.clear();
                    }
                    catch(Exception ex)
                    {}
                    xMLStreamWriter.writeEndElement();
                   //End of Description Tag
                  xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("TimeZone");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getTimeZone()));
                   xMLStreamWriter.writeEndElement();
                     
                   xMLStreamWriter.writeStartElement("URL");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getUrl()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("IsRetweeted");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getIsRetweeted()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("IsGeoEnabled");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getGeoEnabled()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("ProfileAge");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getProfileAge()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("ProfileImageURL");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getProfileImageUrl()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("GeometryType");
                      xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getGeometryType()));
                   xMLStreamWriter.writeEndElement();
                   
                   xMLStreamWriter.writeStartElement("Name");
                   xMLStreamWriter.writeCharacters(tweetDomain.getUserName());
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("Location");
                   xMLStreamWriter.writeCharacters(tweetDomain.getLocation());
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("InReplyToUserID");
                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getInReplyToUserID()));
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("FavouritsCount");
                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getFavouriteCount()));
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("FollowerCount");
                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getFollowerCount()));
                   xMLStreamWriter.writeEndElement();

                   xMLStreamWriter.writeStartElement("FriendsCount");
                   xMLStreamWriter.writeCharacters(String.valueOf(tweetDomain.getfriendsCount()));
                   xMLStreamWriter.writeEndElement();
	   
               xMLStreamWriter.writeEndElement();
           
           xMLStreamWriter.writeEndElement();
	   
	   
	 }
	
}
