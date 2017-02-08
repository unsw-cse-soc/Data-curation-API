package unsw.curation.api.domain.abstraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import unsw.curation.api.domain.ExtractNamedEntity;

public interface INamedEntity {

	List<ExtractNamedEntity>ExtractNamedEntityFile(File filePath) throws Exception;
	//List<ExtractNamedEntity>ExtractNamedEntity(boolean useRegexNer,List<String> lstData) throws Exception;
	List<ExtractNamedEntity> ExtractNamedEntitySentence(String inputSentence) throws Exception;
	List<String> ExtractOrganization(String inputSentence) throws URISyntaxException, Exception;
	List<String> ExtractPerson(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractLocation(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractDate(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractMoney(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractCity(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractState(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractCountry(String inputSentence)throws URISyntaxException, FileNotFoundException, IOException, Exception;
	List<String> ExtractContinent(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractCrime(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractSport(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractHoliday(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractCompany(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractNaturalDisaster(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractDrug(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractProduct(String inputSentence)throws URISyntaxException, Exception;
	//List<String> ExtractRadioProgram(String inputSentence)throws URISyntaxException, Exception;
	//List<String> ExtractRadioStation(String inputSentence)throws URISyntaxException, Exception;
	//List<String> ExtractTvShows(String inputSentence)throws URISyntaxException;
	List<String> ExtractMedia(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractOperatingSystem(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractDegree(String inputSentence)throws URISyntaxException, Exception;
	List<String> ExtractSportEvents(String inputSentence)throws URISyntaxException, Exception;
	//List<String> ExtractRegion(String inputSentence)throws URISyntaxException;
	//List<String> ExtractGeographicFeature(String inputSentence)throws URISyntaxException;
	List<String> ReadRawData(File filePath) throws Exception;
	
		
	
}
