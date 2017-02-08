/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unsw.curation.api.tfidf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alireza
 */
public class ReadDataSentence {
    
    static String Pattern="(http:|https:|ftp)[:-_?\\a-zA-Z\\d.*//]+";
    public static List<String> ReadPreProcessedData(String filePath) throws FileNotFoundException, IOException{
        List<String> lstData=new ArrayList<>();
        BufferedReader sr;
        String sLine;
        sr = new BufferedReader(new FileReader(filePath));
         while ((sLine = sr.readLine()) != null) {
        	 if(sLine.split(" ").length<1)
         	   {
         		continue;
         	   }
             sLine=sLine.replaceAll(Pattern, "");
             String [] arrSLine=sLine.split(" ");
             String Line="";
             for(String str:arrSLine)
             {
                     str=str.replace("'","");
                     str=str.replace("(","");
                     str=str.replace(")","");
                     str=str.replace("!","");
                     str=str.replace("[","");
                     str=str.replace("]","");
                     str=str.replace("{","");
                     str=str.replace("}","");
                     str=str.replace("\"","");
                     str=str.replace("?","");
                     str=str.replace(".","");
                     Line+=str+" ";
             }
             
         lstData.add(Line.trim());
         }
         return lstData;
    }
    public static List<String> ReadRawData(String filePath) throws FileNotFoundException, IOException
    {
        List<String> lstData=new ArrayList<>();
        BufferedReader sr;
        String sLine;
        sr = new BufferedReader(new FileReader(filePath));
         while ((sLine = sr.readLine()) != null) {
        	 if(sLine.split(" ").length<1)
         	   {
         		continue;
         	   }
         lstData.add(sLine);
         }
         return lstData;
    }
    public static void ReadFromMySql()
    {
        
    }
}
