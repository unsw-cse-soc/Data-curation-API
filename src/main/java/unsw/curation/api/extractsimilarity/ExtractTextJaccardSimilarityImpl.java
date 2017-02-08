package unsw.curation.api.extractsimilarity;

import java.io.BufferedReader;
import java.io.File;
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

import unsw.curation.api.domain.ExtractTextSimilarity;
import unsw.curation.api.domain.abstraction.ITextJaccardSimilarity;



public class ExtractTextJaccardSimilarityImpl implements ITextJaccardSimilarity
{
	@Override
	public double Jaccard_Word_Word(String word1, String word2)
    {
        String [] arrWord1=word1.toLowerCase().split("");
        String [] arrWord2=word2.toLowerCase().split("");
        HashSet<String> lstUnion=new HashSet<>();
        lstUnion.addAll(Arrays.asList(arrWord1));
        lstUnion.addAll(Arrays.asList(arrWord2));
        HashSet<String> lstIntersect=new HashSet<>();
        lstIntersect.addAll(Arrays.asList(arrWord1));
        lstIntersect.retainAll(Arrays.asList(arrWord2));
        double lstUnoinSize=(double)lstUnion.size();
        double lstIntersectSize=(double)lstIntersect.size();
        double JaccardSimilarity=lstIntersectSize/lstUnoinSize;
        return JaccardSimilarity;
    }
	@Override
    public List<ExtractTextSimilarity> Jaccard_Word_Document(String word, String filePath) throws IOException
    {
        String [] arrWord=word.toLowerCase().split("");
        List<String> lstVal=ReadData(filePath);
        List<ExtractTextSimilarity> lstSimilarity=new ArrayList<>();
        HashSet<String> lstUniqueVal=new HashSet<>();
        lstUniqueVal.addAll(lstVal);
        for(String str:lstUniqueVal)
        {
            HashSet<String> lstUnion=new HashSet<>();
            HashSet<String> lstIntersect=new HashSet<>();
            String [] arrStr=str.toLowerCase().split("");
            lstUnion.addAll(Arrays.asList(arrWord));
            lstUnion.addAll(Arrays.asList(arrStr));
            lstIntersect.addAll(Arrays.asList(arrWord));
            lstIntersect.retainAll(Arrays.asList(arrStr));
            double lstUnoinSize=(double)lstUnion.size();
            double lstIntersectSize=(double)lstIntersect.size();
            double JaccardSimilarity=lstIntersectSize/lstUnoinSize;
            lstSimilarity.add(new ExtractTextSimilarity(word,str,JaccardSimilarity));
        }
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        Collections.sort(lstSimilarity,new MyStringJaccardComp());
        List<ExtractTextSimilarity> lstTopRecords=lstSimilarity.stream()
                .limit(20)
                .collect(Collectors.toList());
        return lstTopRecords;
    }
    @Override
    public double Jaccard_Document_Document(String file1,String file2) throws IOException
    {
        List<String> lstWords1=ReadData(file1);
        List<String> lstWords2=ReadData(file2);
        HashSet<String> lstUniqueWords=new HashSet<>();
        HashSet<String> lstIntersectWords=new HashSet<>();
        lstUniqueWords.addAll(lstWords1);
        lstUniqueWords.addAll(lstWords2);
        lstIntersectWords.addAll(lstWords1);
        lstIntersectWords.retainAll(lstWords2);
        double lstIntersectSize=lstIntersectWords.size();
        double lstUniqueWordsSize=lstUniqueWords.size();
        double JaccardSimilarity=lstIntersectSize/lstUniqueWordsSize;
        return JaccardSimilarity;
    }
   /* @Override
    public List<ExtractTextSimilarity> Jaccard_Document_DocumentS(File filePath, String directoryPath) throws IOException
    {
        
        List<ExtractTextSimilarity> lstSimilarity=new ArrayList<>();
        List<String> lstWords1=ReadData(filePath.getName());
        HashSet<String> lstUniqueWords1=new HashSet<>();
        lstUniqueWords1.addAll(lstWords1);
        File[] files=new File(directoryPath).listFiles();
        for(File file:files)
        {
            List<String> lstWords2=ReadData(file.getPath());
            lstUniqueWords1.addAll(lstWords2);
            HashSet<String> lstIntersectWords=new HashSet<>();
            lstIntersectWords.addAll(lstWords1);
            lstIntersectWords.retainAll(lstWords2);
            double lstUniqueWordsSize=lstUniqueWords1.size();
            double lstIntersectSize=lstIntersectWords.size();
            double JaccardSimilarity=lstIntersectSize/lstUniqueWordsSize;
            lstSimilarity.add(new ExtractTextSimilarity(filePath.getName(),file.getName()
                    ,JaccardSimilarity));    
        }
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        Collections.sort(lstSimilarity,new MyStringJaccardComp());
        List<ExtractTextSimilarity> lstTopRecords=lstSimilarity.stream()
                .limit(10)
                .collect(Collectors.toList());
        return lstTopRecords;
    }*/
    private List<String> ReadData(String FilePath) throws FileNotFoundException, IOException
    {
        List<String> lstValues=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(FilePath));
        String line="";
        while((line=reader.readLine())!=null)
        {
            String [] arrLine=line.split(" ");
            for(String str: arrLine)
            {
                str=str.toLowerCase();
                str=str.trim();
                lstValues.add(str);
            }
        }
        return lstValues;
    }
    public class MyStringJaccardComp implements Comparator<ExtractTextSimilarity>
    {
        @Override
        public int compare(ExtractTextSimilarity o1, ExtractTextSimilarity o2) {
          if(o1.getSimilarity() <o2.getSimilarity())
              return 1;
          else
              return -1;
        }
    }
}
