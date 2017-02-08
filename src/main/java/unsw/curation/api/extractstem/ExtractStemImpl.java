package unsw.curation.api.extractstem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import unsw.curation.api.domain.ExtractStem;
import unsw.curation.api.domain.abstraction.IStem;



public class ExtractStemImpl implements IStem {

	private List<ExtractStem> lstValues=new ArrayList<>();
	@Override
	public List<ExtractStem> FindWordDerivedForms(String word) throws FileNotFoundException, IOException, URISyntaxException
	{
		String getWord=word.trim().toLowerCase();
		ReadDataset();
		List<ExtractStem> lstDerivedStems=lstValues.stream()
				.filter(s->s.getWord1().equalsIgnoreCase(getWord))
				.collect(Collectors.toList());
	     return lstDerivedStems;
	}

	@Override
	public void ReadDataset() throws FileNotFoundException, IOException, URISyntaxException 
	{
		//java.net.URL url = getClass().getClassLoader().getResource("Stem.txt");
	    File file = new File("Stem.txt");
		BufferedReader reader=new BufferedReader(new FileReader(file));
        String line="";
        while((line=reader.readLine())!=null)
        {
            try
            {
            String [] lineValues=line.split("\\|");
            String myWord1=lineValues[0].trim().toLowerCase();
            String myDerived1=lineValues[1].trim().toLowerCase();
            String myWord2=lineValues[3].trim().toLowerCase();
            String myDerived2=lineValues[4].trim().toLowerCase();
            lstValues.add(new ExtractStem(myWord1,myDerived1,myWord2,myDerived2));
            }
            catch(Exception ex)
            {
                
            }
        }
	}
}
