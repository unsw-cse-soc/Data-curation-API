package unsw.curation.api.extractsimilarity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import unsw.curation.api.domain.ExtractNumberSimilarity;
import unsw.curation.api.domain.abstraction.INumberDiceSimilarity;




public class ExtractNumberDiceSimilarityImpl implements INumberDiceSimilarity{

	@Override
	public double Dice_Vector_Vector(double[] number1, double[] number2) {
		List<String> lstarr1=new ArrayList<>();
        List<String> lstarr2=new ArrayList<>();
        for(double num:number1)
        {
            lstarr1.add(String.valueOf(num));
        }
        for(double num:number2)
        {
            lstarr2.add(String.valueOf(num));
        }
        List<String> lstUnique=new ArrayList<>();
        lstUnique.addAll(lstarr1);
        lstUnique.addAll(lstarr2);
        HashSet<String> lstIntersect=new HashSet<>();
        lstIntersect.addAll(lstarr1);
        lstIntersect.retainAll(lstarr2);
        double intersectSize=lstIntersect.size();
        double uniqueSize=lstUnique.size();
        double DiceSimlarity=(2*intersectSize)/uniqueSize;
        return DiceSimlarity;
	}

	@Override
	public List<ExtractNumberSimilarity> Dice_Vector_VectorS(String filePath) throws IOException {
		List<ExtractNumberSimilarity> lstValues=new ArrayList<>();
        List<ExtractNumberSimilarity> lstTopRecords=new ArrayList<>();
        List<String[]> lstarr=ReadData(filePath);
        for(String [] arrItem:lstarr)
        {
            List<Double> lstUniqueItems=new ArrayList<>();
            HashSet<Double> lstIntersect=new HashSet<>();
            for(String arrIte:arrItem)
            {
                lstUniqueItems.add(Double.parseDouble(arrIte));
                lstIntersect.add(Double.parseDouble(arrIte));
            }
            List<String []> lstTempData=new ArrayList<>();
            lstTempData.addAll(lstarr);
            int arrItemIndex=lstTempData.indexOf(arrItem);
            lstTempData.remove(arrItemIndex);
            for(String [] secArrItem:lstTempData)
            {
                List<Double> lstforSecArrItem=new ArrayList<>();
                 for(String arrIt: secArrItem)
                 {
                    lstUniqueItems.add(Double.parseDouble(arrIt));
                    
                    lstforSecArrItem.add(Double.parseDouble(arrIt));
                 }
                lstIntersect.retainAll(lstforSecArrItem);
                double intersectSize=lstIntersect.size();
                double uniqueSize=lstUniqueItems.size();
                double DiceSimlarity=intersectSize/uniqueSize;
                lstValues.add(new ExtractNumberSimilarity(Arrays.toString(arrItem),Arrays.toString(secArrItem)
                        ,DiceSimlarity));
            }
          Collections.sort(lstValues,new MyDiceComp());
            lstValues=lstValues.stream().limit(10).collect(Collectors.toList());
            lstTopRecords.addAll(lstValues);
            lstValues.clear();
        }
        return lstTopRecords;
	}

	@Override
	public List<ExtractNumberSimilarity> Dice_Vector_VectorS(Double[] vector, String filePath) throws IOException {
		List<ExtractNumberSimilarity> lstValues=new ArrayList<>();
        List<String[]> lstarr=ReadData(filePath);
        for(String [] arrItem:lstarr)
        {
            List<Double> lstUniqueItems=new ArrayList<>();
            HashSet<Double> lstIntersect=new HashSet<>();
            for(String dblVal:arrItem)
            {
                    lstUniqueItems.add(Double.parseDouble(dblVal));
                    lstIntersect.add(Double.parseDouble(dblVal));
            }
            lstUniqueItems.addAll(Arrays.asList(vector));
            lstIntersect.retainAll(Arrays.asList(vector));
            double intersectSize=lstIntersect.size();
            double uniqueSize=lstUniqueItems.size();
            double DiceSimlarity=(2*intersectSize)/uniqueSize;
            lstValues.add(new ExtractNumberSimilarity(Arrays.toString(arrItem),Arrays.toString(vector)
                    ,DiceSimlarity));
        }
        Collections.sort(lstValues,new MyDiceComp());
        List<ExtractNumberSimilarity> lstTopRecords=lstValues.stream()
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
	public class MyDiceComp implements Comparator<ExtractNumberSimilarity>
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
