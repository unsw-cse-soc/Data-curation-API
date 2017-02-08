/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unsw.curation.api.twitter;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import unsw.curation.api.twitterdomain.NamedEntityDomain;
/**
 *
 * @author Alireza
 */
public class NamedEntityExtraction {

  public List<NamedEntityDomain> ExtractTweetNamedEntities(String tweet) throws IOException, Exception
     {

        List<NamedEntityDomain> lstEntityList=new ArrayList<>();
        Properties props = new Properties();
        boolean useRegexner = true;
        if (useRegexner) {
          props.put("annotators", "tokenize, ssplit, pos, lemma, ner,regexner");
          props.put("regexner.mapping", "data.txt");
          
        } else {
          props.put("annotators", "tokenize, ssplit, pos,lemma, ner");
        }
          String values=preProcessTweet(tweet);
          StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
          Annotation document = new Annotation(values);
          pipeline.annotate(document);
          List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
           for (CoreMap sentence : sentences) 
           {
               for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) 
               {
                   String tToken = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                   String word = token.get(CoreAnnotations.TextAnnotation.class);
                   if(tToken.equalsIgnoreCase("O"))
                   {
                           continue;
                   }
                   lstEntityList.add(new NamedEntityDomain(word,tToken));
               }
           }
        return lstEntityList;
     }
  
  public static String preProcessTweet(String tweet)
     {
         String Pattern="(http:|https:|ftp)[:-_?\\a-zA-Z\\d.*//]+";
         tweet=tweet.replaceAll(Pattern, "");
         String Line="";
         if(tweet.toCharArray().length<141)
         {
             String [] arrSLine=tweet.split(" ");
             for(String str:arrSLine)
             {
                     str=str.replace("'","");
                     str=str.replace("(","");
                     str=str.replace(")","");
                     str=str.replace("!","");
                     str=str.replace("[","");
                     str=str.replace("]","");
                     str=str.replace("{","");
                     str=str.replace("}","");
                     str=str.replace("\"","");
                     str=str.replace("?","");
                     str=str.replace(".","");
                     str=str.replace("#","");
                     str=str.replace("@","");
                     Line+=str.trim()+" ";
             }
         }
             return Line;
     }
  
   public List<NamedEntityDomain> ExtractTweetEntities(String tweet) throws IOException, Exception
     {

        List<NamedEntityDomain> lstEntityList=new ArrayList<>();
        Properties props = new Properties();
        boolean useRegexner = true;
        if (useRegexner) {
          props.put("annotators", "tokenize, ssplit, pos, lemma, ner,regexner");
          props.put("regexner.mapping", "data.txt");
          
        } else {
          props.put("annotators", "tokenize, ssplit, pos,lemma, ner");
        }
          //String values=preProcessTweet(tweet);
          StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
          Annotation document = new Annotation(tweet);
          pipeline.annotate(document);
          List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
           for (CoreMap sentence : sentences) 
           {
               for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) 
               {
                   String tToken = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                   String word = token.get(CoreAnnotations.TextAnnotation.class);
                   if(tToken.equalsIgnoreCase("O"))
                   {
                           continue;
                   }
                   lstEntityList.add(new NamedEntityDomain(word,tToken));
               }
           }
        return lstEntityList;
     }
}
