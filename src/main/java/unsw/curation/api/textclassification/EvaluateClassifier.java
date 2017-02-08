package unsw.curation.api.textclassification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class EvaluateClassifier {

	/*public List<String> ComputePrecision(File actualValues, File predictedValues) throws IOException
	{
		List<String> lstActualFile=ReadTestData(actualValues);
		List<String> lstPredictedFile=ReadTestData(predictedValues);
		List<String> lstPrecisionValues=new ArrayList<>();
		if(lstActualFile.size()!=lstPredictedFile.size())
			System.err.println("The Length of Actual and Predicted Vectors are not Similar");
		List<String> lstCategories=lstActualFile
				.stream()
				.distinct()
				.collect(Collectors.toList());
		for(String category: lstCategories)
		{
			double truePositive=0;
			double falsePositive=0;
			category=category.toLowerCase().trim();
		   for(int i=0; i< lstPredictedFile.size();i++)
		   {
			 if(lstActualFile.get(i).equals(category)&& lstPredictedFile.get(i).equals(category))
			 {
				 truePositive++;
			 }
			 if(!lstActualFile.get(i).equals(category)&& lstPredictedFile.get(i).equals(category))
			 {
				 falsePositive++;
			 }
		   }
		   double percision=truePositive/(falsePositive+truePositive);
		   lstPrecisionValues.add("Precision: "+category+" is: "+String.valueOf(percision));
		}
		return lstPrecisionValues;
	}*/
	
	public double ComputeAccuracy(File actualValues, File predictedValues) throws IOException
	{
		List<String> lstActualFile=ReadTestData(actualValues);
		List<String> lstPredictedFile=ReadTestData(predictedValues);
		
		if(lstActualFile.size()!=lstPredictedFile.size())
			System.err.println("The Length of Actual and Predicted Vectors are not Similar");
		double positiveRate=0;
		for(int i=0;i<lstActualFile.size();i++)
		{
			if(lstActualFile.get(i).equals(lstPredictedFile.get(i)))
			{
				positiveRate++;
			}
		}
	    double accuracy=positiveRate/(double)lstActualFile.size();
	    return accuracy;
	}
	
	public List<String> ComputeRecall(File actualValues, File predictedValues) throws IOException
	{
		List<String> lstActualFile=ReadTestData(actualValues);
		List<String> lstPredictedFile=ReadTestData(predictedValues);
		List<String> lstRecall=new ArrayList<>();
		List<String> lstCategories=lstActualFile
				.stream()
				.distinct()
				.collect(Collectors.toList());
		for(String category: lstCategories)
		{
			double truePositive=0;
			double falseNegative=0;
			category=category.toLowerCase().trim();
		   for(int i=0; i< lstPredictedFile.size();i++)
		   {
			 if(lstActualFile.get(i).equals(category)&& lstPredictedFile.get(i).equals(category))
			 {
				 truePositive++;
			 }
			 if(lstActualFile.get(i).equals(category)&& !lstPredictedFile.get(i).equals(category))
			 {
				 falseNegative++;
			 }
		   }
		   double recall=truePositive/(falseNegative+truePositive);
		   lstRecall.add("Recall: "+category+" is: "+String.valueOf(recall));
		}
		return lstRecall;
	}
	
	private List<String> ReadTestData(File inputLabels) throws IOException
	{
		List<String> lstLabels=new ArrayList<>();
		BufferedReader reader=new BufferedReader(new FileReader(inputLabels));
		String line="";
		while((line=reader.readLine())!=null)
		{
			String [] arrLine=line.split(",");
			String label=arrLine[arrLine.length-1];
			lstLabels.add(label.trim().toLowerCase());
		}
		return lstLabels;
	}
}
