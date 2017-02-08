package unsw.curation.api.extractsimilarity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import unsw.curation.api.domain.ExtractNumberSimilarity;
import unsw.curation.api.domain.abstraction.INumberCosineSimilarity;




public class ExtractNumberCosineSimilarityImpl implements INumberCosineSimilarity
{
	@Override
	public double Cosine_Vector_Vector(double [] vector1, double [] vector2)
    {
        double dotProduct=0.0;
        double vector1Len=0.0;
        double vector2Len=0.0;
        for(int i=0;i<vector1.length;i++)
        {
           dotProduct+=vector1[i]*vector2[i];
           vector1Len+=Math.pow(vector1[i],2);
           vector2Len+=Math.pow(vector2[i],2);       
        }
        return 1-(dotProduct/ (Math.sqrt(vector1Len)* Math.sqrt(vector2Len)));
    }
    @Override
    //dar meghdar bazgashti dobareh check kon
    public List<ExtractNumberSimilarity> Cosine_Vector_VectorS(double [] vector1, String fileName) throws IOException
    {
      List<ExtractNumberSimilarity> lstValues=new ArrayList<>();
      List<String[]> lstData=ReadData(fileName);
      for(String [] vector2:lstData)
      {
          
        double dotProduct=0.0;
        double vector1Len=0.0;
        double vector2Len=0.0;
        for(int i=0;i<vector1.length;i++)
        {
           dotProduct+=vector1[i]*Double.parseDouble(vector2[i]);
           vector1Len+=Math.pow(vector1[i],2);
           vector2Len+=Math.pow(Double.parseDouble(vector2[i]),2);       
        }
        double CosineSimilarity= 1-(dotProduct/ (Math.sqrt(vector1Len)* Math.sqrt(vector2Len)));
        lstValues.add(new ExtractNumberSimilarity(Arrays.toString(vector1),
                Arrays.toString(vector2), 
                CosineSimilarity));
      }
        Collections.sort(lstValues,new MyCosineComp());
        List<ExtractNumberSimilarity> lstTopRecords=lstValues.stream()
                .limit(10)
                .collect(Collectors.toList());
      return lstTopRecords;
    }
    @Override
  //dar meghdar bazgashti dobareh check kon
    public List<ExtractNumberSimilarity> Cosine_Vector_VectorS(String fileName) throws IOException
    {
      List<ExtractNumberSimilarity> lstValues=new ArrayList<>();
      List<String[]> lstData=ReadData(fileName);
      List<ExtractNumberSimilarity> lstTopRecords=new ArrayList<>();
      for(String [] vector1:lstData)
      {
       List<String[]> lstTempValues=new ArrayList<>();
       lstTempValues.addAll(lstData);
       lstTempValues.remove(vector1);
       for(String [] vector2:lstTempValues)
       {
        double dotProduct=0.0;
        double vector1Len=0.0;
        double vector2Len=0.0;
        for(int i=0;i<vector1.length;i++)
        {
           dotProduct+=Double.parseDouble(vector1[i])*Double.parseDouble(vector2[i]);
           vector1Len+=Math.pow(Double.parseDouble(vector1[i]),2);
           vector2Len+=Math.pow(Double.parseDouble(vector2[i]),2);       
        }
        double CosineSimilarity= 1-(dotProduct/ (Math.sqrt(vector1Len)* Math.sqrt(vector2Len)));
        lstValues.add(new ExtractNumberSimilarity(Arrays.toString(vector1),
        Arrays.toString(vector2), 
        CosineSimilarity));
       }
        Collections.sort(lstValues,new MyCosineComp());
        lstValues=lstValues.stream().limit(10).collect(Collectors.toList());
        lstTopRecords.addAll(lstValues);
        lstValues.clear();
      }
      return lstTopRecords;
    }
    public List<String[]> ReadData(String FilePath) throws FileNotFoundException, IOException
    {
        List<String[]> lstValues=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(FilePath));
        String line="";
        while((line=reader.readLine())!=null)
        {
           String [] arrLine=line.split(",");
           lstValues.add(arrLine);
        }
        return lstValues;
    }
    public class MyCosineComp implements Comparator<ExtractNumberSimilarity>
    {
            @Override
            public int compare(ExtractNumberSimilarity o1, ExtractNumberSimilarity o2) {
              if(o1.getScore()<o2.getScore())
                  return 1;
              else
                  return -1;
            }
    }
	
}
