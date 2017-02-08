package unsw.curation.api.textclassification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import unsw.curation.api.classify.TextClassifier;
import unsw.curation.api.domain.Classification;

public class TextClassifierImpl extends TextClassifier {

	public void TKnn(File trainFile, File testFile, File result) throws Exception
	{
		   long fileNumber=ThreadLocalRandom.current().nextInt(1, 9999999);
		   String current = System.getProperty("user.dir");
           File Textdir= new File(current+"\\TextClassification");
            if(!Textdir.exists())
               Textdir.mkdir();
            
		   ExtractClassificationTextKNNImpl knn=new ExtractClassificationTextKNNImpl();
		   knn.LoadDataset(trainFile);
		   knn.EvaluateKNN();
		   knn.LearnKNN();
		   knn.SaveModel(Textdir+"\\Knn"+fileNumber+".dat");
		   
		   LoadTestData(testFile);
		   loadModel(Textdir+"\\Knn"+fileNumber+".dat");
		   Predict(result.getAbsolutePath());
	}
	public void TNaiveBayes(File trainFile, File testFile, File result) throws Exception
	{
		long fileNumber=ThreadLocalRandom.current().nextInt(1, 9999999);
		String current = System.getProperty("user.dir");
        File Textdir= new File(current+"\\TextClassification");
         if(!Textdir.exists())
            Textdir.mkdir();
         ExtractClassificationTextNaiveBaysImpl naiveBayes=new ExtractClassificationTextNaiveBaysImpl();
         naiveBayes.LoadDataset(trainFile);
         naiveBayes.EvaluateNaiveBays();
         naiveBayes.LearnNaiveBays();
         naiveBayes.SaveModel(Textdir+"\\NaiveBayes"+fileNumber+".dat");
         
         LoadTestData(testFile);
		 loadModel(Textdir+"\\NaiveBayes"+fileNumber+".dat");
		 Predict(result.getAbsolutePath());
	}
	public void TLogisticRegression(File trainFile, File testFile, File result) throws Exception
	{
		long fileNumber=ThreadLocalRandom.current().nextInt(1, 9999999);
		String current = System.getProperty("user.dir");
        File Textdir= new File(current+"\\TextClassification");
         if(!Textdir.exists())
            Textdir.mkdir();
         ExtractClassificationTextLogisticRegressionImpl glm=new ExtractClassificationTextLogisticRegressionImpl();
         glm.LoadDataset(trainFile);
         glm.EvaluateLogisticRegression();
         glm.LearnLogisticRegression();
         glm.SaveModel(Textdir+"\\Logistic"+fileNumber+".dat");
         
         LoadTestData(testFile);
         loadModel(Textdir+"\\Logistic"+fileNumber+".dat");
         Predict(result.getAbsolutePath());
	}
	public void TDecisionTree(File trainFile, File testFile, File result) throws Exception
	{
		long fileNumber=ThreadLocalRandom.current().nextInt(1, 9999999);
		String current = System.getProperty("user.dir");
        File Textdir= new File(current+"\\TextClassification");
         if(!Textdir.exists())
            Textdir.mkdir();
         ExtractClassificationTextDecisionTreeImpl j48=new ExtractClassificationTextDecisionTreeImpl();
         j48.LoadDataset(trainFile);
         j48.EvaluateDecisionTree();
         j48.LearnDecisionTree();
         j48.SaveModel(Textdir+"\\DecisionTree"+fileNumber+".dat");
         
         LoadTestData(testFile);
         loadModel(Textdir+"\\DecisionTree"+fileNumber+".dat");
         Predict(result.getAbsolutePath());
	}
	public void TRandomForest(File trainFile, File testFile, File result) throws Exception
	{
		long fileNumber=ThreadLocalRandom.current().nextInt(1, 9999999);
		String current = System.getProperty("user.dir");
        File Textdir= new File(current+"\\TextClassification");
         if(!Textdir.exists())
            Textdir.mkdir();
        ExtractClassificationTextRandomForestImpl rf=new ExtractClassificationTextRandomForestImpl();
        rf.LoadDataset(trainFile);
        rf.EvaluateRandomForest();
        rf.LearnRandomForest();
        rf.SaveModel(Textdir+"\\RandomForest"+fileNumber+".dat");
        
        LoadTestData(testFile);
        loadModel(Textdir+"\\RandomForest"+fileNumber+".dat");
        Predict(result.getAbsolutePath());
	}
	
	public void TSvm(File trainFile, File testFile, File result) throws Exception
	{
		long fileNumber=ThreadLocalRandom.current().nextInt(1, 9999999);
		String current = System.getProperty("user.dir");
        File Textdir= new File(current+"\\TextClassification");
         if(!Textdir.exists())
            Textdir.mkdir();
         
         ExtractClassificationTextSVMImpl svm=new ExtractClassificationTextSVMImpl();
         svm.LoadDataset(trainFile);
         svm.EvaluateSVM();
         svm.LearnSVM();
         svm.SaveModel(Textdir+"\\SVM"+fileNumber+".dat");
         
         LoadTestData(testFile);
         loadModel(Textdir+"\\SVM"+fileNumber+".dat");
         Predict(result.getAbsolutePath());
	}
	public void TNeuralNetwork(File trainFile, File testFile, File result) throws Exception
	{
		long fileNumber=ThreadLocalRandom.current().nextInt(1, 9999999);
		String current = System.getProperty("user.dir");
        File Textdir= new File(current+"\\TextClassification");
         if(!Textdir.exists())
            Textdir.mkdir();
         ExtractClassificationTextNeuralNetworkImpl neural =new ExtractClassificationTextNeuralNetworkImpl();
         neural.LoadDataset(trainFile);
         neural.EvaluateNeuralNetwork();
         neural.LearnNeuralNetwork();
         neural.SaveModel(Textdir+"\\Neural"+fileNumber+".dat");
         
         LoadTestData(testFile);
         loadModel(Textdir+"\\Neural"+fileNumber+".dat");
         Predict(result.getAbsolutePath());
         
	}
}
