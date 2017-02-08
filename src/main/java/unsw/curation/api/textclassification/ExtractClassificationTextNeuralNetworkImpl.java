package unsw.curation.api.textclassification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import unsw.curation.api.domain.Classification;
import unsw.curation.api.domain.abstraction.IClassificationTextNeuralNetwork;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class ExtractClassificationTextNeuralNetworkImpl implements IClassificationTextNeuralNetwork 
{

	Instances trainedData;
    StringToWordVector filter;
    FilteredClassifier classifier;
    Classification cls=new Classification();
    
	@Override
	public void LoadDataset(File arffFileName) throws IOException 
	{
		BufferedReader bReader=new BufferedReader(
                new FileReader(arffFileName));
        ArffLoader.ArffReader arff=new ArffLoader.ArffReader(bReader);
        trainedData=arff.getData();
        bReader.close();
	}

	@Override
	public List<Classification> EvaluateNeuralNetwork() throws Exception
	{
		List<Classification> lstEvaluationDetail=new ArrayList<>();
		trainedData.setClassIndex(trainedData.numAttributes()-1);
        filter=new StringToWordVector();
        classifier=new FilteredClassifier();
        classifier.setFilter(filter);
        classifier.setClassifier(new MultilayerPerceptron());
        Evaluation eval=new Evaluation(trainedData);
        eval.crossValidateModel(classifier, trainedData, 4, new Random(1));
        /*try
        {
        for(int i=0;i<10000;i++)
        {
        	cls.setPrecision(eval.precision(i));
        	cls.setRecall(eval.recall(i));
        	cls.setAuc(eval.areaUnderPRC(i));
        	cls.setFMeasure(eval.fMeasure(i));
        	cls.setFn(eval.falseNegativeRate(i));
        	cls.setFp(eval.falsePositiveRate(i));
        	cls.setTn(eval.trueNegativeRate(i));
        	cls.setTp(eval.truePositiveRate(i));
        	cls.setMeanAbsoluteError(eval.meanAbsoluteError());
        	cls.setRelativeAbsoluteError(eval.relativeAbsoluteError());
        	cls.setCorrect(eval.correct());
        	cls.setKappa(eval.kappa());
        	cls.setNumInstances(eval.numInstances());
        	cls.setInCorrect(eval.incorrect());
        	lstEvaluationDetail.add(new Classification(cls.getPrecision(),
        			cls.getRecall(),
        			cls.getAuc(),
        			cls.getCorrect(),
        			cls.getInCorrect(),
        			cls.getErrorRate(),
        			cls.getFn(),
        			cls.getFp(),
        			cls.getTn(),
        			cls.getTp(),
        			cls.getKappa(),
        			cls.getMeanAbsoluteError(),
        			cls.getNumInstances(),
        			cls.getRelativeAbsoluteError(),
        			cls.getFMeasure()));
        }
        }
        catch(Exception ex)
        {
        	
        }*/
        return lstEvaluationDetail;
	}

	@Override
	public void LearnNeuralNetwork() throws Exception 
	{
		   trainedData.setClassIndex(trainedData.numAttributes()-1);
	        filter=new StringToWordVector();
	        classifier=new FilteredClassifier();
	        classifier.setFilter(filter);
	        classifier.setClassifier(new MultilayerPerceptron());
	        classifier.buildClassifier(trainedData);
	}

	@Override
	public void SaveModel(String modelName) throws FileNotFoundException, IOException 
	{
		ObjectOutputStream output=new ObjectOutputStream(
                new FileOutputStream(modelName));
        output.writeObject(classifier);
        output.close();
	}

}
