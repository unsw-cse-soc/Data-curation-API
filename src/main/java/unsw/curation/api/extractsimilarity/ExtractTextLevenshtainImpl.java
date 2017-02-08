package unsw.curation.api.extractsimilarity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import unsw.curation.api.domain.ExtractTextSimilarity;
import unsw.curation.api.domain.abstraction.ITextLevenshtainSimilarity;



public class ExtractTextLevenshtainImpl implements ITextLevenshtainSimilarity {

	public int Leveneshtain_Word_Word(String word1, String word2)
    {
      word1 = word1.toLowerCase();
      word2 = word2.toLowerCase();
      int[] costs = new int[word2.length() + 1];
      for (int j = 0; j < costs.length; j++)
          costs[j] = j;
      for (int i = 1; i <= word1.length(); i++)
      {
          costs[0] = i;
          int nw = i - 1;
          for (int j = 1; j <= word2.length(); j++)
          {
              int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                      word1.charAt(i - 1) == word2.charAt(j - 1) ? nw : nw + 1);
              nw = costs[j];
              costs[j] = cj;
          }
      }
      return costs[word2.length()];
    }
   public List<ExtractTextSimilarity> Leveneshtain_Word_Document(String word1, String filePath) throws IOException
    {
      List<ExtractTextSimilarity> lstVal=new ArrayList<>();
      word1 = word1.toLowerCase();
      List<String> lstValues=ReadData(filePath);
      for(String b:lstValues)
      {
          int[] costs = new int[b.length() + 1];
          for (int j = 0; j < costs.length; j++)
              costs[j] = j;
          for (int i = 1; i <= word1.length(); i++)
          {
              costs[0] = i;
              int nw = i - 1;
              for (int j = 1; j <= b.length(); j++)
              {
                  int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                          word1.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                  nw = costs[j];
                  costs[j] = cj;
              }
          }
          lstVal.add(new ExtractTextSimilarity(word1, b, costs[b.length()]));
      }
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        Collections.sort(lstVal,new myLeveneshteinComp());
        List<ExtractTextSimilarity> lstTopRecords=lstVal.stream()
                .limit(10)
                .collect(Collectors.toList());
        return lstTopRecords;
    }
   
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
    public class myLeveneshteinComp implements Comparator<ExtractTextSimilarity>
    {
        @Override
        public int compare(ExtractTextSimilarity o1, ExtractTextSimilarity o2)
        {
            if(o1.getSimilarity() > o2.getSimilarity())
              return 1;
          else
              return -1;
        }   
    }
}
