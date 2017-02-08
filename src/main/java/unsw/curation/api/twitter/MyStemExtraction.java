/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unsw.curation.api.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import unsw.curation.api.twitterdomain.StemDomain;

/**
 *
 * @author Alireza
 */
public class MyStemExtraction {
    
    public List<StemDomain> ReadData(File stemFilePath) throws FileNotFoundException, IOException
    {
        List<StemDomain> lstValues=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(stemFilePath));
        String line="";
        while((line=reader.readLine())!=null)
        {
            try
            {
            String [] lineValues=line.split("\\|");
           
            String myWord1=lineValues[0].trim().toLowerCase();
            String myDerived1=lineValues[1].trim().toLowerCase();
            String myWord2=lineValues[3].trim().toLowerCase();
            String myDerived2=lineValues[4].trim().toLowerCase();
            lstValues.add(new StemDomain(myWord1,myDerived1,myWord2,myDerived2));
            }
            catch(Exception ex)
            {
                
            }
        }
       
        
       return lstValues; 
    }
}
