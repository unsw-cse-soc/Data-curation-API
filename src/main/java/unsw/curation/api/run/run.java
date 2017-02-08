package unsw.curation.api.run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import unsw.curation.api.classify.TextClassifier;
import unsw.curation.api.domain.Classification;
import unsw.curation.api.domain.ExtractNamedEntity;
import unsw.curation.api.domain.ExtractNumberSimilarity;
import unsw.curation.api.domain.ExtractPosTag;
import unsw.curation.api.domain.ExtractStem;
import unsw.curation.api.domain.ExtractSynonym;
import unsw.curation.api.domain.ExtractTextCosineSimilarity;
import unsw.curation.api.domain.ExtractTextTfidfSimilarity;
import unsw.curation.api.extractnamedentity.ExtractEntityFile;
import unsw.curation.api.extractnamedentity.ExtractEntitySentence;
import unsw.curation.api.extractpostag.ExtractPosTagData;
import unsw.curation.api.extractsimilarity.ExtractNumberCosineSimilarityImpl;
import unsw.curation.api.extractsimilarity.ExtractNumberEuclideanSimilarity;
import unsw.curation.api.extractsimilarity.ExtractTextCosineSimilarityImpl;
import unsw.curation.api.extractsimilarity.ExtractTextTfIdfSimilarityImpl;
import unsw.curation.api.extractstem.ExtractStemImpl;
import unsw.curation.api.extractsynonym.WordNetFile;
import unsw.curation.api.index.Index;
import unsw.curation.api.index.SchIndData;
import unsw.curation.api.linking.ConceptNet;
import unsw.curation.api.linking.GoogleKnowledgeGraph;
import unsw.curation.api.linking.WikiData;
import unsw.curation.api.textclassification.EvaluateClassifier;
import unsw.curation.api.textclassification.ExtractClassificationTextKNNImpl;
import unsw.curation.api.textclassification.TextClassifierImpl;
import unsw.curation.api.tokenization.ExtractionKeywordImpl;
import unsw.curation.api.twitter.TweetInfo;
import unsw.curation.api.url.GetURL;
public class run 
{

	public static void main(String [] args) throws Exception
	{
		//Extract Named Entities of a File
		
		/*ExtractEntityFile fileEntity=new ExtractEntityFile();
		List<ExtractNamedEntity>lstentities=fileEntity.ExtractNamedEntityFile(new File("entity.txt"));
		for(ExtractNamedEntity ent:lstentities)
		{
			System.out.println(ent.word+" "+ent.ner);
		}*/
		//System.out.println(fileEntity.ExtractCountry("entity.txt"));
		//System.out.println(fileEntity.ExtractCompany("entity.txt"));
		/*ExtractEntitySentence sentenceEntity=new ExtractEntitySentence();
		List<String> lstLocation=sentenceEntity
				.ExtractLocation("Its singles Shake It Off Blank Space and Bad"
				+ " Blood reached number one in the US, Australia and Canada.");
		for(String location:lstLocation)
		{
			System.out.println(location);
		}*/
		/*List<ExtractNamedEntity> lstFileEntities=fileEntity.
				ExtractNamedEntityFile(new File("entity.txt"));
		for(ExtractNamedEntity fEntity:lstFileEntities)
		{
			System.out.println(fEntity.word+" "+fEntity.ner);
		}
		//Extract Named Entities of a Sentence
		ExtractEntitySentence sentenceEntity=new ExtractEntitySentence();
		List<ExtractNamedEntity> lstSentenceEntities=sentenceEntity
				.ExtractNamedEntitySentence
				("Taylor Alison Swift (born December 13, 1989) is an American singer-songwriter. "
						+ "Throughout her career, she has become one of the most"
						+ " popular female contemporary singers");
		for(ExtractNamedEntity sEntity:lstSentenceEntities)
		{
			System.out.println(sEntity.word+" "+sEntity.ner);
		}*/
		///////////////////////////////////////////////////////////////////////////////
/*		ExtractPosTagData posFile=new ExtractPosTagData();
		List<ExtractPosTag> lstVerb=posFile.ExtractVerb(new File("pos.txt"));
		for(ExtractPosTag pos:lstVerb)
			System.out.println(pos.getWordPart()+" "+pos.getTag());
		List<ExtractPosTag> lstAdjective=posFile.ExtractAdjective(new File("pos.txt"));
		for(ExtractPosTag pos:lstAdjective)
			System.out.println(pos.getWordPart()+" "+pos.getTag());
		List<ExtractPosTag> lstAdverb=posFile.ExtractAdverb(new File("pos.txt"));
		for(ExtractPosTag pos:lstAdverb)
			System.out.println(pos.getWordPart()+" "+pos.getTag());*/
/*		ExtractPosTagData posSentence=new ExtractPosTagData();
		List<ExtractPosTag> lstAdjective=posSentence.ExtractPosTagsSentence("RT @ningin: Junsu battles it out in Starcraft");
		for(ExtractPosTag pos:lstAdjective)
			System.out.println(pos.getWordPart()+" "+pos.getTag());*/
		/////////////////////////////////////////////////////////////////////////////////
		
/*		WordNetFile extractWordnet=new WordNetFile();
		List<ExtractSynonym>lstSynonyms=extractWordnet
				.ExtractSynonymSentence("The British Colony of Ceylon achieved "
						+ " independence on 4 February 1948, with an"
						+ " amended constitution taking effect on the same date."
						+ " Independence was granted under the Ceylon Independence Act 1947");*/
	/*	for(ExtractSynonym synonym:lstSynonyms)
		{
			System.out.println(synonym.getWord()+": "+synonym.getSynset());
		}*/
		
		
	/*	WordNetFile extractWordnetWord=new WordNetFile();
		List<String> lstHypernym=extractWordnetWord.ExtractHypernymWord("car");
		for(String hypernym:lstHypernym)
		{
			System.out.println(hypernym);
		}*/
		/*List<String> lstSynonym=extractWordnetWord.ExtractSynonymWord("car");
		for(String synonym:lstSynonym)
		{
			System.out.println(synonym);
		}*/
		///////////////////////////////////////////////////////////////////////////////
	/*	ExtractNumberCosineSimilarityImpl cosineIns=new ExtractNumberCosineSimilarityImpl();
		double []v1= {1,2,4,6};
		double []v2={4,7,8,4};
		System.out.println(cosineIns.Cosine_Vector_Vector(v1, v2));
		
		List<ExtractNumberSimilarity>lstCosineSimilarity=cosineIns
				.Cosine_Vector_VectorS(v1, "cosine.txt");
		for(ExtractNumberSimilarity cosine:lstCosineSimilarity)
		{
			System.out.println(cosine.getVector1()+" "+cosine.getVector2()+" "+cosine.getScore());
		}
		
		List<ExtractNumberSimilarity>lstCosineFileSimilrity=cosineIns
				.Cosine_Vector_VectorS("cosine.txt");
		for(ExtractNumberSimilarity cosineFile:lstCosineFileSimilrity)
		{
			System.out.println(cosineFile.getVector1()
					+" "+cosineFile.getVector2()+" "+cosineFile.getScore());
		}
		////////////////////////////////////////////////////////////////////////////////
		ExtractNumberEuclideanSimilarity euclideanIns=new ExtractNumberEuclideanSimilarity();
	    System.out.println(euclideanIns.Euclidean_Vector_Vector(v1, v2));
	    
	    List<ExtractNumberSimilarity> lstEuclidean=
	    		euclideanIns.Euclidean_Vector_VectorS(v1, "cosine.txt");
	    for(ExtractNumberSimilarity euclidean:lstEuclidean)
		{
			System.out.println(euclidean.getVector1()
					+" "+euclidean.getVector2()+" "+euclidean.getScore());
		}*/
	    /////////////////////////////////////////////////////////////////////////////////////
	    
	    //////////////////////////////////////////////////////////////////////
	  /*  ExtractTextCosineSimilarityImpl textCosineIns=new ExtractTextCosineSimilarityImpl();
		   List<ExtractTextCosineSimilarity> lstFileCosine=textCosineIns
				   .Cosine_Document_DocumentS("C:\\Users\\Alireza\\workspace\\TextAPI\\tweets.txt", "cosineDoc/");
		   for(ExtractTextCosineSimilarity fileCosine:lstFileCosine)
		   {
			   System.out.println(fileCosine.DocName+" "+fileCosine.DocCandidate+" "+fileCosine.Similarity);
		   }
		   
		   ExtractTextTfIdfSimilarityImpl tfIdfIns=new ExtractTextTfIdfSimilarityImpl();
		    tfIdfIns.CreateIndex("tweets.txt");
		    List<ExtractTextTfidfSimilarity>lstTfIdf= tfIdfIns.SearchText("Tell the congress don't censor the web");
		    for(ExtractTextTfidfSimilarity tfidf:lstTfIdf)
		    {
			   System.out.println(tfidf.getSentence()+
					   " "+tfidf.getSimilaritySentence()+" "+tfidf.getScore());
			   
		    }*/
		   
	 
	    
	   

	   //////////////////////////////////////////////////////////////////////
	 /*  GetURL url=new GetURL();
	   url.Extract("https://en.wikipedia.org/wiki/Super_Meat_Boy");
	   List<String> lstparagraphes=url.O_ExtractParagraphes();
	   System.out.println(lstparagraphes.size());
	   for(String pargraph:lstparagraphes)
	   {
		   System.out.println(pargraph);
	   }
	   
	   List<String> lstHeadings=url.O_ExtractHeadings();
	   for(String heading:lstHeadings)
	   {
		   System.out.println(heading);
	   }*/
	   /////////////////////////////////////////////////////////////////////////////
	   /*ExtractionKeywordImpl keywordIns=new ExtractionKeywordImpl();
	   String Values= keywordIns.ExtractFileKeyword(new File("tweets.txt"), new File("englishStopwords.txt"));
	   System.out.println(Values);
	   
	   ExtractionKeywordImpl keywordIns2=new ExtractionKeywordImpl();
	   String sentences= keywordIns2.ExtractSentenceKeyword("Tell the congress dont censor the web", new File("englishStopwords.txt"));
	   System.out.println(sentences);*/
	   /////////////////////////////////////////////////////////////////////////////////////////////////
	  /* ExtractStemImpl eStemIns=new ExtractStemImpl();
	   eStemIns.ReadDataset();
	   List<ExtractStem>lstStem=eStemIns.FindWordDerivedForms("play");
	   for(ExtractStem stem:lstStem)
	   {
		   System.out.println(stem.getWord1()+" "+stem.getDerived1()+" "+stem.getWord2()+" "+stem.getDerived2());
	   }*/
	   ///////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	/*  TextClassifierImpl tc=new TextClassifierImpl();
	  tc.TKnn(new File("D:\\classification\\train.arff"),
			  new File("D:\\classification\\test.arff"),
			  new File("D:\\classification\\Knn-result.txt"));
	  
	  tc.TNaiveBayes(new File("D:\\classification\\train.arff"),
			  new File("D:\\classification\\test.arff"),
			  new File("D:\\classification\\NaiveBayes-result.txt"));
	 EvaluateClassifier ec=new EvaluateClassifier();
	 double accuracy= ec.ComputeAccuracy(new File("D:\\classification\\testlabel.txt"),
			  new File("D:\\classification\\Knn-result.txt"));
	 List<String> lstPrecision= ec.ComputePrecision(new File("D:\\classification\\testlabel.txt"),
			  new File("D:\\classification\\Knn-result.txt"));
	 List<String> lstRecall= ec.ComputeRecall(new File("D:\\classification\\testlabel.txt"),
			  new File("D:\\classification\\Knn-result.txt"));
	  System.out.println("Data Processed Successfully"+accuracy);
	  System.out.println("Data Processed Successfully"+lstPrecision);
	  System.out.println("Data Processed Successfully"+lstRecall);
	  
	  double accuracy2= ec.ComputeAccuracy(new File("D:\\classification\\testlabel.txt"),
			  new File("D:\\classification\\NaiveBayes-result.txt"));
	 List<String> lstPrecision2= ec.ComputePrecision(new File("D:\\classification\\testlabel.txt"),
			  new File("D:\\classification\\NaiveBayes-result.txt"));
	 List<String> lstRecall2= ec.ComputeRecall(new File("D:\\classification\\testlabel.txt"),
			  new File("D:\\classification\\NaiveBayes-result.txt"));
	  
	 System.out.println("Data Processed Successfully"+accuracy2);
	  System.out.println("Data Processed Successfully"+lstPrecision2);
	  System.out.println("Data Processed Successfully"+lstRecall2);*/
	 
	 
	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
/*	    String OAuthConsumerKey="yJfMtMvwBo8ygMXW6C53nIiai";
	    String OAUTHConsumerSecret="YaO21MifLQVBlPTPCkgL7iBEk3CrtpsViaVE4B6Dsriss3Z1QM";
	    String OAUTHAccessToken="704646632747331584-LNRXY9JmLaAxbPyrIknf1eh6Dxc6ggF";
	    String OAUTHAccessTokenSecret="gGmwmnmpbkO3eZmBpXjRnuF5sMqGWhxASu49RFm9H5Saz";
	    String [] arrStr=new String[1];
	    arrStr[0]="Budget";
	    TweetInfo inf=new TweetInfo(10,new File("englishStopwords.txt"),
	    		OAuthConsumerKey
	    		,OAUTHConsumerSecret
	    		,OAUTHAccessToken
	    		,OAUTHAccessTokenSecret);
	    inf.StartStreamingByKeyword(arrStr);*/
	    ////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	/*    ConceptNet cnet=new ConceptNet();
	    cnet.ConceptNetLookUp("shirt");
	    cnet.ConceptNetPartOfSearch("car", 10);
	    cnet.ConceptNetAssociation("cat");
	    String [] arr={"toyota","ikco","holden","jeep"};
	    cnet.ConceptNetAssociationWords(arr);
	    
	    List<String> lstValues=Arrays.asList("taylor swift","toyota","lionel messi");
	    GoogleKnowledgeGraph kg=new GoogleKnowledgeGraph();
	    kg.ParseGoogleKnowledgeGraph(lstValues, "text.txt");
	    //////////////////////////////////////////////////////////////////////////////
	    WikiData wd=new WikiData();
	    wd.ParseWikiData(lstValues, "text.txt");*/
		//////////////////////////////////Indexing///////////////////////////////////////////
		BufferedReader reader=new BufferedReader(new FileReader("D://Twitter//tweet.txt"));
		String indexDir="D://Index_File//";
		Index ind=new Index(indexDir);
		SchIndData sd=new SchIndData();
		String line="";
		
		while((line=reader.readLine())!=null)
		ind.IndexDocuments(line);
		ind.Close();
		reader.close();
		
		List<String>lstValues= sd.search("mental health",indexDir, 10);
		System.out.println(lstValues);
		
	}
}
