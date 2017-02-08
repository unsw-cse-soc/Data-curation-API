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
import unsw.curation.api.domain.abstraction.INumberEuclideanSimilarity;


public class ExtractNumberEuclideanSimilarity implements INumberEuclideanSimilarity{
	
	@Override
	public double Euclidean_Vector_Vector(double [] vector1,double [] vector2)
    {
        if(vector1.length==vector2.length)
        {
            double sum=0.0;
           for(int i=0;i<vector1.length;i++)
           {
              double tempValue= vector2[i]-vector1[i];
              tempValue=Math.pow(tempValue, 2);
              sum+=tempValue;
           }
           double EclideanDistance=Math.sqrt(sum);
           return EclideanDistance;
        }
        else
        {
            System.err.println("Error in Vectors length...");
            return 0.0;
        }
    }
	@Override
	public List<ExtractNumberSimilarity> Euclidean_Vector_VectorS(String filePath) throws IOException
    {
        List<ExtractNumberSimilarity> lstSimilarity=new ArrayList<>();
        List<ExtractNumberSimilarity> lstTopRecords=new ArrayList<>();
        List<String[]> lstValues=ReadData(filePath);
        for(String [] arrVal1: lstValues)
        {
            List<String[]> lstTempVal=new ArrayList<>();
            lstTempVal.addAll(lstValues);
            lstTempVal.remove(arrVal1);
            for(String [] arrVal2:lstTempVal)
            {
                double sum=0.0;
                for(int i=0;i<arrVal1.length;i++)
                {
                  double tempValue= Double.parseDouble(arrVal2[i])-Double.parseDouble(arrVal1[i]);
                  tempValue=Math.pow(tempValue, 2);
                  sum+=tempValue;
                }
                 double EclideanDistance=Math.sqrt(sum);
                lstSimilarity.add(new ExtractNumberSimilarity(Arrays.toString(arrVal1),Arrays.toString(arrVal2)
                        ,EclideanDistance));
            }
            Collections.sort(lstSimilarity,new MyEuclideanComp());
            lstSimilarity=lstSimilarity.stream().limit(10).collect(Collectors.toList());
            lstTopRecords.addAll(lstSimilarity);
            lstSimilarity.clear();
        }
        return lstTopRecords;
    }
	@Override
	 // dar in method en mozo ra ke tool vector aval ba too; vector hay file yeki bashad ra dar nazar begir
    // mitavani tool yeki ra chek koni agar andazeh bood baad pardazesh koni.
    public List<ExtractNumberSimilarity> Euclidean_Vector_VectorS(double [] vector,String filePath) throws IOException
    {
        List<ExtractNumberSimilarity> lstSimilarity=new ArrayList<>();
        List<String[]> lstValues=ReadData(filePath);
        for(String [] arrVal2: lstValues)
        {
                double sum=0.0;
                for(int i=0;i<arrVal2.length;i++)
                {
                  double tempValue= Double.parseDouble(arrVal2[i])-vector[i];
                  tempValue=Math.pow(tempValue, 2);
                  sum+=tempValue;
                }
                 double EclideanDistance=Math.sqrt(sum);
                 lstSimilarity.add(new ExtractNumberSimilarity(Arrays.toString(arrVal2),Arrays.toString(vector)
                         ,EclideanDistance));
        }
        Collections.sort(lstSimilarity,new MyEuclideanComp());
        List<ExtractNumberSimilarity> lstTopRecords=lstSimilarity.stream()
                .limit(10)
                .collect(Collectors.toList());
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
    public class MyEuclideanComp implements Comparator<ExtractNumberSimilarity>
    {

        @Override
        public int compare(ExtractNumberSimilarity o1, ExtractNumberSimilarity o2) {
            if(o1.getScore()>o2.getScore())
                return 1;
            else
                return -1;
        }
        
    }

}
