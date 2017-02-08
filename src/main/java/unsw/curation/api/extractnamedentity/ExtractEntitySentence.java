package unsw.curation.api.extractnamedentity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import unsw.curation.api.domain.ExtractNamedEntity;
import unsw.curation.api.domain.abstraction.INamedEntity;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class ExtractEntitySentence implements INamedEntity {

	@Override
	public List<ExtractNamedEntity> ExtractNamedEntityFile(File filePath) throws Exception {
		
		List<String> lstData=ReadRawData(filePath);
        List<ExtractNamedEntity> lstEntityList=new ArrayList<>();
        Properties props = new Properties();
        boolean useRegexner = true;
       // java.net.URL url = getClass().getClassLoader().getResource("data.txt");
		//File file = new File(url.toURI());
        if (useRegexner) {
          props.put("annotators", "tokenize, ssplit, pos, lemma, ner,regexner");
          props.put("regexner.mapping", "data.txt");
          
        } else {
          props.put("annotators", "tokenize, ssplit, pos,lemma, ner");
        }
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        for(String str:lstData){
          Annotation document = new Annotation(str);
          pipeline.annotate(document);
          List<CoreMap> sentences = document.get(SentencesAnnotation.class);
          int wordPosition=0; 
          for (CoreMap sentence : sentences) {
               for (CoreLabel token : sentence.get(TokensAnnotation.class)) 
               {
                   String tToken = token.get(NamedEntityTagAnnotation.class);
                   String word = token.get(TextAnnotation.class);
                   //lstEntityList.add(new Entity(word,tToken));
                   wordPosition++;
                   if(true)
                   {
                       if(tToken.equalsIgnoreCase("O"))
                       {
                               continue;
                       }
                       lstEntityList.add(new ExtractNamedEntity(word,tToken,wordPosition));
                   }
              /*     else
                   {
                       lstEntityList.add(new ExtractNamedEntity(word,tToken,wordPosition));
                   }*/
               }
           }
        }
        List<ExtractNamedEntity> lstFinal=new ArrayList<>();
        for(int i=0;i<lstEntityList.size();i++)
        {
            try
            {
            String concatWord=lstEntityList.get(i).word+" ";
            String concatEntity=lstEntityList.get(i).ner;
            String concatEntityNext=lstEntityList.get(i+1).ner;
            while (concatEntity.equals(concatEntityNext))
            {
              concatWord+=lstEntityList.get(i+1).word+" ";
              concatEntity=concatEntityNext;
              i++;
              concatEntityNext=lstEntityList.get(i+1).ner;
            }
            // lstFinal.add(new ExtractNamedEntity(concatWord,concatEntity));
             concatWord="";
            }
            catch(Exception ex){}
        }
        return lstEntityList;
	}

	@Override
	public List<ExtractNamedEntity> ExtractNamedEntitySentence(String inputSentence) throws URISyntaxException {
		    //List<ExtractNamedEntity> lstWordsLocation=new ArrayList<>();
		    List<ExtractNamedEntity> lstFinal=new ArrayList<>();
		    List<ExtractNamedEntity> lstEntityList=new ArrayList<>();
		    /*List<String>lstData=new ArrayList<>();
		    lstData.add(inputSentence);
		    lstData.add(inputSentence.toLowerCase());*/	        
		    Properties props = new Properties();
	        boolean useRegexner = true;
	        //java.net.URL url = getClass().getClassLoader().getResource("data.txt");
			//File file = new File(url.toURI());
	        if (useRegexner) 
	        {
	          props.put("annotators", "tokenize, ssplit, pos, lemma, ner,regexner");
	          props.put("regexner.mapping", "data.txt");
	        }
	        else 
	        {
	          props.put("annotators", "tokenize, ssplit, pos,lemma, ner");
	        }
	        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	        //for(String str:lstData){
	          
	          Annotation document = new Annotation(inputSentence);
	          pipeline.annotate(document);
	          int wordPosition=0;
	          List<CoreMap> sentences = document.get(SentencesAnnotation.class);
	          
	           for (CoreMap sentence : sentences) {
	               for (CoreLabel token : sentence.get(TokensAnnotation.class)) 
	               {
	                   String tToken = token.get(NamedEntityTagAnnotation.class);
	                   String word = token.get(TextAnnotation.class);
	                   //lstWordsLocation.add(new ExtractNamedEntity(word,wordPosition));
	                   wordPosition++;
	                   if(true)
	                   {
	                      if(tToken.equalsIgnoreCase("O"))
	                      {
	                           continue;
	                      }
	                      lstEntityList.add(new ExtractNamedEntity(word,tToken,wordPosition));
	                   }
/*	                   else
	                   {
	                      lstEntityList.add(new ExtractNamedEntity(word,tToken,wordPosition));
	                   }*/
	               }
	           }
	           lstEntityList.add(new ExtractNamedEntity(".",".",0));
	           
	        for(int i=0;i<lstEntityList.size();i++)
	        {
	            try
	            {
	            String wordEnt=lstEntityList.get(i).word+" ";
	            String entityEnt=lstEntityList.get(i).ner;
	            int positionEnt=lstEntityList.get(i).position;
	            int positionNextEnt=lstEntityList.get(i+1).position;
	            while ((positionEnt+1)==positionNextEnt)
	            {
	              wordEnt+=lstEntityList.get(i+1).word+" ";
	              positionEnt=positionNextEnt;
	              i++;
	              positionNextEnt=lstEntityList.get(i+1).position;
	            }
	             lstFinal.add(new ExtractNamedEntity(wordEnt,entityEnt,positionEnt));
	             wordEnt="";
	            }
	            catch(Exception ex){}
	        }
	       // }
	        //lstFinal=lstFinal.stream().filter(s->s.word).distinct().collect(Collectors.toList());
	        return lstFinal;
	}

	@Override
	public List<String> ExtractOrganization(String inputSentence) throws URISyntaxException {
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
        List<String> lstOrganization=new ArrayList<>();
        lstTaggedData.stream().filter((ne) -> (ne.ner.equalsIgnoreCase("organization"))).forEach((ne) -> {
            lstOrganization.add(ne.word);
        });
        return lstOrganization;
	}

	@Override
	public List<String> ExtractPerson(String inputSentence) throws URISyntaxException {
	     List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
	        List<String> lstPerson=new ArrayList<>();
	        lstTaggedData.stream().filter((ne) -> (ne.ner.equalsIgnoreCase("person"))).forEach((ne) -> {
	        	lstPerson.add(ne.word);
	        });
	        return lstPerson;
	}

	@Override
	public List<String> ExtractLocation(String inputSentence) throws URISyntaxException {
		 List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
	        List<String> lstLocation=new ArrayList<>();
	        lstTaggedData.stream().filter((ne) -> (ne.ner.equalsIgnoreCase("location"))).forEach((ne) -> {
	        	lstLocation.add(ne.word);
	        });
	        return lstLocation;
	}

	@Override
	public List<String> ExtractDate(String inputSentence) throws URISyntaxException{
		 List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
	        List<String> lstDate=new ArrayList<>();
	        lstTaggedData.stream().filter((ne) -> (ne.ner.equalsIgnoreCase("date"))).forEach((ne) -> {
	        	lstDate.add(ne.word);
	        });
	        return lstDate;
	}

	@Override
	public List<String> ExtractMoney(String inputSentence) throws URISyntaxException {
		 List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
	        List<String> lstMoney=new ArrayList<>();
	        lstTaggedData.stream().filter((ne) -> (ne.ner.equalsIgnoreCase("money"))).forEach((ne) -> {
	        	lstMoney.add(ne.word);
	        });
	        return lstMoney;
	}

	@Override
	public List<String> ExtractCity(String inputSentence) throws URISyntaxException{
        List<String> lstCity=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence.toLowerCase());
        if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("City")))
        {
            lstTaggedData.stream().filter(ns->ns.ner.equalsIgnoreCase("City")).forEach(ns->{
                lstCity.add(ns.word);
            });   
        }
        else
        {
            List<String> lstCityNull=new ArrayList<>();
            return lstCityNull;
        }
        return lstCity;
	}

	@Override
	public List<String> ExtractState(String inputSentence) throws URISyntaxException
	{
		List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
        List<String> lstState=new ArrayList<>();
        if(lstTaggedData.stream().anyMatch(f->f.ner.equalsIgnoreCase("state")))
        {
            lstTaggedData.stream().filter(c->c.ner.equalsIgnoreCase("state")).forEach(c->{
                lstState.add(c.word);
            });
        }
        else
        {
            List<String> lstStaNull=new ArrayList<>();
            return lstStaNull;
        }    
       return lstState;  
	}

	@Override
	public List<String> ExtractCountry(String filePath) throws Exception 
	{
		/*java.net.URL url = getClass().getClassLoader().getResource("Country.txt");
		File file = new File(url.toURI());
		List<String> lstCountries=new ArrayList<>();
		BufferedReader reader=new BufferedReader(new FileReader(file.getPath()));
		String line="";
		while((line=reader.readLine())!=null)
		{
			lstCountries.add(line.trim());
		}
		List<String>lstExtractedCountries=new ArrayList<>();
		for(String country:lstCountries)
		{
			if(inputSentence.contains(country))
				lstExtractedCountries.add(country);
				
		}
		return lstExtractedCountries;*/

		 List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntityFile(new File(filePath));
	        List<String> lstCountry=new ArrayList<>();
	        if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("country")))
	        {
	            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("country")).forEach(f->{
	                lstCountry.add(f.word);
	            });
	        }
	        else
	        {
	           // List<String> lstCont=ExtractLocation(inputSentence);
	        	List<String> lstCountryNull=new ArrayList<>();
	            return lstCountryNull;
	        }
	        return lstCountry;
	}

	@Override
	public List<String> ExtractContinent(String inputSentence) throws URISyntaxException{
		 List<String> lstContinent=new ArrayList<>();
	        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence.toLowerCase());
	         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("continent")))
	        {
	            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("continent")).forEach(f->{
	                lstContinent.add(f.word);
	            });
	        }
	        else
	        {
	            List<String> lstConten=ExtractLocation(inputSentence);
	            return lstConten;
	        }
	        return lstContinent;

	}

	@Override
	public List<String> ExtractCrime(String inputSentence) throws URISyntaxException{
		List<String> lstCrime=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("crime")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("crime")).forEach(f->{
            	lstCrime.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
            //lstDr.add("No Data Provided for Crime / Can not Find any entity for Crime");
            return lstDr;
        }
        return lstCrime;

	}

	@Override
	public List<String> ExtractSport(String inputSentence) throws URISyntaxException{

        List<String> lstSport=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence.toLowerCase());
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("sport")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("sport")).forEach(f->{
            	lstSport.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
           // lstDr.add("No Data Provided for Sport / Can not Find any entity for sport");
            return lstDr;
        }
        return lstSport;
	}

	@Override
	public List<String> ExtractHoliday(String inputSentence) throws URISyntaxException{
		 List<String> lstHoliday=new ArrayList<>();
	        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence.toLowerCase());
	         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("holiday")))
	        {
	            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("holiday")).forEach(f->{
	            	lstHoliday.add(f.word);
	            });
	        }
	        else
	        {
	            List<String> lstDr=new ArrayList<>();
	            lstDr.add("No Data Provided for holidy / Can not Find any entity for holiday");
	            return lstDr;
	        }
	         return lstHoliday;

	}

	@Override
	public List<String> ExtractCompany(String inputSentence) throws URISyntaxException{
		List<String> lstCompany=new ArrayList<>();
		List<String> lstCompanyFinal=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
        lstTaggedData.addAll(ExtractNamedEntitySentence(inputSentence.toLowerCase()));
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("company")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("company")).forEach(f->{
                lstCompany.add(f.word);
            });
        }
        else
        {
            List<String> lstComp=ExtractOrganization(inputSentence);
            return lstComp;
        }
         lstCompanyFinal=lstCompany.stream().distinct().collect(Collectors.toList());
         return lstCompanyFinal;
	}

	@Override
	public List<String> ExtractNaturalDisaster(String inputSentence) throws URISyntaxException{
		List<String> lstDisaster=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("disaster")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("disaster")).forEach(f->{
            	lstDisaster.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
            //lstDr.add("No Data Provided for Natural Disaster / Can not Find any entity for Natural Disaster");
            return lstDr;
        }
         return lstDisaster;
	}

	@Override
	public List<String> ExtractDrug(String inputSentence) throws URISyntaxException{
		List<String> lstDrug=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("drug")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("drug")).forEach(f->{
                lstDrug.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
            //lstDr.add("No Data Provided for Drug / Can not Find any entity for Drug");
            return lstDr;
        }
         return lstDrug;
	}

	@Override
	public List<String> ExtractProduct(String inputSentence) throws URISyntaxException{
		List<String> lstProduct=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
        lstTaggedData.addAll(ExtractNamedEntitySentence(inputSentence.toLowerCase()));
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("Product")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("Product")).forEach(f->{
                lstProduct.add(f.word);
            });
        }  
        else
        {
            List<String> lstDr=new ArrayList<>();
           // lstDr.add("No Data Provided for Product / Can not Find any entity for Product");
            return lstDr;
        }
         List<String>lstFinalProducts=lstProduct.stream().distinct().collect(Collectors.toList());
         return lstFinalProducts;
	}

	/*@Override
	public List<String> ExtractRadioProgram(String inputSentence) throws URISyntaxException{
		List<String> lstProgram=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence, true);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("radioprog")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("radioprog")).forEach(f->{
                lstProgram.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
            //lstDr.add("No Data Provided for Radio Program / Can not Find any entity for Radio Program");
            return lstDr;
        }
         return lstProgram;
	}

	@Override
	public List<String> ExtractRadioStation(String inputSentence) throws URISyntaxException{
		List<String> lstRStation=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence, true);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("radiostat")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("radiostat")).forEach(f->{
                lstRStation.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
            //lstDr.add("No Data Provided for Radio Station / Can not Find any entity for Radio Station");
            return lstDr;
        }
         return lstRStation;
	}*/

	/*@Override
	public List<String> ExtractTvShows(String inputSentence) throws URISyntaxException {
		List<String> lstTvProgram=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence, true);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("tvshow")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("tvshow")).forEach(f->{
                lstTvProgram.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
           // lstDr.add("No Data Provided for Tv Shows / Can not Find any entity for Tv Shows");
            return lstDr;
        }
         return lstTvProgram;
	}*/

	@Override
	public List<String> ExtractMedia(String inputSentence) throws URISyntaxException {
		 List<String> lstTvProgram=new ArrayList<>();
	        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
	         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("media")))
	        {
	            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("media")).forEach(f->{
	                lstTvProgram.add(f.word);
	            });
	        }
	        else
	        {
	            List<String> lstDr=new ArrayList<>();
	           // lstDr.add("No Data Provided for Tv Shows / Can not Find any entity for Tv Shows");
	            return lstDr;
	        }
	         return lstTvProgram;
	}

	@Override
	public List<String> ExtractOperatingSystem(String inputSentence) throws URISyntaxException{
		List<String> lstOS=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("os")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("os")).forEach(f->{
            	lstOS.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
           // lstDr.add("No Data Provided for Operating System / Can not Find any entity for Operating System");
            return lstDr;
        }
         return lstOS;
	}

	@Override
	public List<String> ExtractDegree(String inputSentence) throws URISyntaxException{
		List<String> lstDegree=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("degree")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("degree")).forEach(f->{
            	lstDegree.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
           // lstDr.add("No Data Provided for Degree / Can not Find any entity for Degree");
            return lstDr;
        }
         return lstDegree;
	}

	@Override
	public List<String> ExtractSportEvents(String inputSentence) throws URISyntaxException{
		List<String> lstSportEv=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("sportev")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("sportev")).forEach(f->{
            	lstSportEv.add(f.word);
            });
        }
        else
        {
           List<String> lstDr=new ArrayList<>();
            //lstDr.add("No Data Provided for Sport Event / Can not Find any entity for Sport Event");
            return lstDr;
        }
         return lstSportEv;
	}

/*	@Override
	public List<String> ExtractRegion(String inputSentence) throws URISyntaxException{
		List<String> lstTvStation=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence, true);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("region")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("region")).forEach(f->{
                lstTvStation.add(f.word);
            });
        }
        else
        {
            List<String> lstDr=new ArrayList<>();
            //lstDr.add("No Data Provided for Region / Can not Find any entity for Region");
            return lstDr;
        }
         return lstTvStation;
	}*/

/*	@Override
	public List<String> ExtractGeographicFeature(String inputSentence) throws URISyntaxException{
		List<String> lstTvStation=new ArrayList<>();
        List<ExtractNamedEntity> lstTaggedData=ExtractNamedEntitySentence(inputSentence, true);
         if(lstTaggedData.stream().anyMatch(t->t.ner.equalsIgnoreCase("geo")))
        {
            lstTaggedData.stream().filter(f->f.ner.equalsIgnoreCase("geo")).forEach(f->{
                lstTvStation.add(f.word);
            });
        }
        else
        {
            List<String> lstProg=new ArrayList<>();
            //lstProg.add("No Data Provided for Geographic Features / Can not Find any entity for Region");
            return lstProg;
        }
         return lstTvStation;
	}*/

	@Override
	public List<String> ReadRawData(File filePath) throws Exception {
        List<String> lstData=new ArrayList<>();
        BufferedReader sr;
        String sLine;
        sr = new BufferedReader(new FileReader(filePath));
         while ((sLine = sr.readLine()) != null) {
         lstData.add(sLine);
         }
         return lstData;
	}

}
