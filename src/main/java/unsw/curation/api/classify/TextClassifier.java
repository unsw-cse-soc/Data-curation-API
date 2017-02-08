package unsw.curation.api.classify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class TextClassifier {

    private Instances test;
    private FilteredClassifier classifier;
    List<String> lstLoadData=new ArrayList<>();
    public List<String> lstClassLabel=new ArrayList<>();
    
    public void LoadTestData(File ArffFileName) throws FileNotFoundException, IOException
    {
        BufferedReader bTestReader=new BufferedReader(
                new FileReader(ArffFileName));
        ArffLoader.ArffReader myarff=new ArffLoader.ArffReader(bTestReader);
        test=myarff.getData();
        bTestReader.close();
    }
    public void loadModel(String ModelName) throws FileNotFoundException, IOException, ClassNotFoundException {
		
	ObjectInputStream in = new ObjectInputStream(new FileInputStream(ModelName));
        Object tmp = in.readObject();			
        classifier = (FilteredClassifier) tmp;
        in.close();
 	System.out.println("Model loaded: " + ModelName);
     
	}
    
	public void Predict(String OutPutFileName) throws Exception
    {
        List<String> lstLabels=new ArrayList<>();
        test.setClassIndex(test.numAttributes()-1);
        int numOfAttribute=test.numAttributes();
        String createData;
        for (int i = 0; i < test.numInstances(); i++) {
           String FinalVal="";
           double predict = classifier.classifyInstance(test.instance(i));
           for(int k=0;k<numOfAttribute-1;k++)  {
            createData= test.instance(i).stringValue(k);
           FinalVal+= createData+ ",";
           }
           lstLabels.add(FinalVal+test.classAttribute().value((int) predict));
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(OutPutFileName))) {
                for(String label:lstLabels)
                {
                    writer.write(label);
                    writer.newLine();
                }
                writer.flush();
            }
         }
    }
}
