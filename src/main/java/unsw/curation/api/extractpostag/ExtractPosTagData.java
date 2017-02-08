package unsw.curation.api.extractpostag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.TregexPattern;
import edu.stanford.nlp.util.CoreMap;
import unsw.curation.api.domain.ExtractPosTag;
import unsw.curation.api.domain.abstraction.IPosTag;

public class ExtractPosTagData implements IPosTag {

	@Override
	public List<ExtractPosTag> ExtractNoun(String sentence) {
		 List<ExtractPosTag>lstNoun=new ArrayList<>();
		 List<ExtractPosTag> lstTaggedData=ExtractPosTagsSentence(sentence);
		 for(ExtractPosTag posTags:lstTaggedData)
		 {
		       if ((posTags.getTag().trim().toLowerCase().equals("nn")) ||
		                (posTags.getTag().trim().toLowerCase().equals("nns")) ||
		                (posTags.getTag().trim().toLowerCase().equals("nnp")) ||
		                (posTags.getTag().trim().toLowerCase().equals("nnps")))
		    	   lstNoun.add(new ExtractPosTag(posTags.getWordPart(),"Noun -> "+posTags.getTag()));
		 }
		 return lstNoun;
	}

	@Override
	public List<ExtractPosTag> ExtractAdjective(String sentence) {
		List<ExtractPosTag>lstAdj=new ArrayList<>();
		 List<ExtractPosTag> lstTaggedData=ExtractPosTagsSentence(sentence);
		 for(ExtractPosTag posTags:lstTaggedData)
		 {
		       if ((posTags.getTag().trim().toLowerCase().equals("jj")) ||
		                (posTags.getTag().trim().toLowerCase().equals("jjr")) ||
		                (posTags.getTag().trim().toLowerCase().equals("jjs")))
		    	   lstAdj.add(new ExtractPosTag(posTags.getWordPart(),"Adjective -> "+posTags.getTag()));
		 }
		 return lstAdj;
	}

	@Override
	public List<ExtractPosTag> ExtractAdverb(String sentence) {
		List<ExtractPosTag>lstAdverb=new ArrayList<>();
		 List<ExtractPosTag> lstTaggedData=ExtractPosTagsSentence(sentence);
		 for(ExtractPosTag posTags:lstTaggedData)
		 {
		       if ((posTags.getTag().trim().toLowerCase().equals("rb")) ||
		                (posTags.getTag().trim().toLowerCase().equals("rbr")) ||
		                (posTags.getTag().trim().toLowerCase().equals("rbs")))
		    	   lstAdverb.add(new ExtractPosTag(posTags.getWordPart(),"Adverb -> "+posTags.getTag()));
		 }
		 return lstAdverb;
	}

	@Override
	public List<ExtractPosTag> ExtractVerb(String sentence) {
		List<ExtractPosTag>lstVerb=new ArrayList<>();
		 List<ExtractPosTag> lstTaggedData=ExtractPosTagsSentence(sentence);
		 for(ExtractPosTag posTags:lstTaggedData)
		 {
		       if ((posTags.getTag().trim().toLowerCase().equals("vb")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbd")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbg")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbn")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbp")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbz")))
		    	   lstVerb.add(new ExtractPosTag(posTags.getWordPart(),"Verb -> "+posTags.getTag()));
		 }
		 return lstVerb;
	}

	@Override
	public List<ExtractPosTag> ExtractNoun(File filePath) throws Exception {
		List<ExtractPosTag>lstNoun=new ArrayList<>();
		 List<String> lstData=ExtractData(filePath);
		 List<ExtractPosTag> lstTaggedData=ExtractPosTags(lstData);
		 for(ExtractPosTag posTags:lstTaggedData)
		 {
		       if ((posTags.getTag().trim().toLowerCase().equals("nn")) ||
		                (posTags.getTag().trim().toLowerCase().equals("nns")) ||
		                (posTags.getTag().trim().toLowerCase().equals("nnp")) ||
		                (posTags.getTag().trim().toLowerCase().equals("nnps")))
		    	   lstNoun.add(new ExtractPosTag(posTags.getWordPart(),posTags.getTag()));
		 }
		 return lstNoun;
	}

	@Override
	public List<ExtractPosTag> ExtractAdjective(File filePath) throws Exception {
		List<ExtractPosTag>lstAdj=new ArrayList<>();
		 List<String> lstData=ExtractData(filePath);
		 List<ExtractPosTag> lstTaggedData=ExtractPosTags(lstData);
		 for(ExtractPosTag posTags:lstTaggedData)
		 {
		       if ((posTags.getTag().trim().toLowerCase().equals("jj")) ||
		                (posTags.getTag().trim().toLowerCase().equals("jjr")) ||
		                (posTags.getTag().trim().toLowerCase().equals("jjs")))
		    	   lstAdj.add(new ExtractPosTag(posTags.getWordPart(),posTags.getTag()));
		 }
		 return lstAdj;
	}

	@Override
	public List<ExtractPosTag> ExtractAdverb(File filePath) throws Exception {
		List<ExtractPosTag>lstAdVerb=new ArrayList<>();
		 List<String> lstData=ExtractData(filePath);
		 List<ExtractPosTag> lstTaggedData=ExtractPosTags(lstData);
		 for(ExtractPosTag posTags:lstTaggedData)
		 {
		       if ((posTags.getTag().trim().toLowerCase().equals("rb")) ||
		        (posTags.getTag().trim().toLowerCase().equals("rbr")) ||
		        (posTags.getTag().trim().toLowerCase().equals("rbs")))
		    	   lstAdVerb.add(new ExtractPosTag(posTags.getWordPart(),posTags.getTag()));
		 }
		 return lstAdVerb;
	}

	@Override
	public List<ExtractPosTag> ExtractVerb(File filePath) throws Exception {
		List<ExtractPosTag>lstVerb=new ArrayList<>();
		 List<String> lstData=ExtractData(filePath);
		 List<ExtractPosTag> lstTaggedData=ExtractPosTags(lstData);
		 for(ExtractPosTag posTags:lstTaggedData)
		 {
		       if ((posTags.getTag().trim().toLowerCase().equals("vb")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbd")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbg")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbn")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbp")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbz")))
		    	   lstVerb.add(new ExtractPosTag(posTags.getWordPart(),posTags.getTag()));
		 }
		 return lstVerb;
	}

	@Override
	public List<ExtractPosTag> ExtractPosTagsFile(File filePath) throws Exception {
        List<String> lstData=ExtractData(filePath);
        List<ExtractPosTag> lstTaggedSentences = new ArrayList<>();
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        for(String str:lstData)
        {
        Annotation annotation = new Annotation(str);
        pipeline.annotate(annotation);
        List<CoreMap> senten=annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap map:senten)
        {
            map.get(TokensAnnotation.class).stream().forEach((tok) -> {
                String PosTagg=tok.get(PartOfSpeechAnnotation.class);
                lstTaggedSentences.add(new ExtractPosTag(tok.originalText(),PosTagg));
            });
        }
      } 
     return lstTaggedSentences;
	}

	@Override
	public List<String> ExtractData(File filePath) throws Exception {
		  List<String> lstSingleFileContent = new ArrayList<>();
	        BufferedReader singleBr;
	        String singleLine;
	        singleBr = new BufferedReader(new FileReader(filePath));
	         while ((singleLine = singleBr.readLine()) != null) 
	         {
	        	 lstSingleFileContent.add(singleLine);
	         }
	        return lstSingleFileContent;
	}

	@Override
	public List<ExtractPosTag> ExtractPosTags(List<String> inputData) 
	{
		List<ExtractPosTag> lstTaggedSentences = new ArrayList<>();		
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        for(String str:inputData)
        {
        Annotation annotation = new Annotation(str);
        pipeline.annotate(annotation);
        List<CoreMap> senten=annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap map:senten)
        {
            map.get(TokensAnnotation.class).stream().forEach((tok) -> {
                String getPosTag=tok.get(PartOfSpeechAnnotation.class);
                lstTaggedSentences.add(new ExtractPosTag(tok.originalText(),getPosTag));
            });
        }
      } 
     return lstTaggedSentences;
	}
	
	@Override
	 public List<ExtractPosTag> ExtractPosTagsSentenceNew(String sentence) 
	    {
		    List<ExtractPosTag> lstFinalPos=new ArrayList<>();
	        List<ExtractPosTag> lstTaggedSentences = new ArrayList<>();
	        Properties props = new Properties();
	        props.setProperty("annotators", "tokenize,ssplit,pos");
	        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

	        Annotation annotation = new Annotation(sentence);
	        pipeline.annotate(annotation);
	        List<CoreMap> senten=annotation.get(CoreAnnotations.SentencesAnnotation.class);
	        for(CoreMap map:senten)
	        {
	            map.get(TokensAnnotation.class).stream().forEach((tok) -> {
	                String getPosTag=tok.get(PartOfSpeechAnnotation.class);
	                lstTaggedSentences.add(new ExtractPosTag(tok.originalText(),getPosTag));
	            });
	        }
	        
	        String setPos="";
	        for(ExtractPosTag posTags:lstTaggedSentences)
	        {
	        	if ((posTags.getTag().trim().toLowerCase().equals("vb")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbd")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbg")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbn")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbp")) ||
		                (posTags.getTag().trim().toLowerCase().equals("vbz")))
	        		setPos="verb -> "+posTags.getTag();
	        	 if ((posTags.getTag().trim().toLowerCase().equals("rb")) ||
	     		        (posTags.getTag().trim().toLowerCase().equals("rbr")) ||
	     		        (posTags.getTag().trim().toLowerCase().equals("rbs")))
	        		 setPos="Adverb -> "+posTags.getTag();
	        	 if ((posTags.getTag().trim().toLowerCase().equals("jj")) ||
			                (posTags.getTag().trim().toLowerCase().equals("jjr")) ||
			                (posTags.getTag().trim().toLowerCase().equals("jjs")))
			    	setPos="Adjective -> "+posTags.getTag();
	        	 if ((posTags.getTag().trim().toLowerCase().equals("nn")) ||
			                (posTags.getTag().trim().toLowerCase().equals("nns")) ||
			                (posTags.getTag().trim().toLowerCase().equals("nnp")) ||
			                (posTags.getTag().trim().toLowerCase().equals("nnps")))
	        		 setPos="Noun -> "+posTags.getTag();
	        	 lstFinalPos.add((new ExtractPosTag(posTags.getWordPart(),setPos)));
	        	
	        }
	        
	        return lstFinalPos;

        }

	@Override
	public List<ExtractPosTag> ExtractQuotaion(String sentence) {
		 List<String> lstQuotation=new ArrayList<>();
	        List<ExtractPosTag> lstQuotationResult=new ArrayList<>();
	        
	        Pattern pattern = Pattern.compile(".*\\\"(.*)\\\".*");
	        String [] arrSplitedBydot=sentence.split("\\.");
	        for(String senten:arrSplitedBydot){
	            Matcher matcher = pattern.matcher(senten);
	            if(matcher.find()) 
	            {
	                lstQuotation.add(matcher.group(1));
	            }
	        }
	        for(String Quotation:lstQuotation)
	        {

	            lstQuotationResult.addAll(ExtractPosTagsSentence(Quotation));
	            /*String ConcatQuotationPos="";
	            for(ExtractPosTag Token:getQuotationResult)
	            {
	                ConcatQuotationPos+=Token.getTag()+" "+Token.get
	            }
	            lstQuotationResult.add(ConcatQuotationPos);
	        
                 */	      
	        }
	        return lstQuotationResult;
	}

	@Override
	public List<String> ExtractPhrase(String sentence) {
		 List<String> lstResult=new ArrayList<>();
	        List<String> lstPhrase=new ArrayList<>();
	        List<String> lstFinalPhrase=new ArrayList<>();
	        Properties props = new Properties();
	        props.setProperty("annotators", "tokenize,ssplit,parse");
	        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	        Annotation annotation = new Annotation(sentence);
	        pipeline.annotate(annotation);
	        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
	        for(CoreMap sent:sentences)
	        {
	            Tree tree = sent.get(TreeAnnotation.class);
	            lstResult.add(tree.pennString());
	        }
	        TregexPattern tPattern = TregexPattern.compile("NP");
	        for(String strVal:lstResult)
	            {
	             Tree t = Tree.valueOf(strVal);
	             TregexMatcher tMatcher = tPattern.matcher(t);
	             while (tMatcher.find()) {
	                 lstPhrase.add(tMatcher.getMatch().toString());
	                }
	            }
	        TregexPattern tPattern2 = TregexPattern.compile("VP");
	        for(String strVal2:lstResult)
	            {
	             Tree t2 = Tree.valueOf(strVal2);
	             TregexMatcher tMatcher2 = tPattern2.matcher(t2);
	             while (tMatcher2.find()) 
	             {
	             lstPhrase.add(tMatcher2.getMatch().toString());
	             }
	           }
	        //////////////////////////////////////////////////////////////
//	        for(String Data:lstPhrase) {
//	            String line="";
//	           String [] tempVal=Pattern.compile("\\)").split(Data);
//	           for(int i=0;i<tempVal.length;i++)
//	           {
//	               String [] getFinalVal=tempVal[i].split(" ");
//	               line+=getFinalVal[getFinalVal.length-1]+" ";
//	           }
//	           lstFinalPhrase.add(line);
//	            }
	        ///return lstFinalPhrase;
	        return lstPhrase;
	}

	@Override
	public List<ExtractPosTag> ExtractPosTagsSentence(String sentence) 
	{
        List<ExtractPosTag> lstTaggedSentences = new ArrayList<>();
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Annotation annotation = new Annotation(sentence);
        pipeline.annotate(annotation);
        List<CoreMap> senten=annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap map:senten)
        {
            map.get(TokensAnnotation.class).stream().forEach((tok) -> {
                String getPosTag=tok.get(PartOfSpeechAnnotation.class);
                lstTaggedSentences.add(new ExtractPosTag(tok.originalText(),getPosTag));
            });
        }        
        return lstTaggedSentences;
	}
}
