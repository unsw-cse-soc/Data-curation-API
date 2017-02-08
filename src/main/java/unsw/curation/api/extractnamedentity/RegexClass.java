/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unsw.curation.api.extractnamedentity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alireza
 */
public class RegexClass {
    
	public List<String> ReadRawData(File filePath) throws Exception 
	{
        List<String> lstData=new ArrayList<>();
        BufferedReader sr;
        String sLine;
        sr = new BufferedReader(new FileReader(filePath));
         while ((sLine = sr.readLine()) != null) {
         lstData.add(sLine);
         }
         return lstData;
	}
	
	
    private static final String  regExpression= "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,63}$";
        private static final String IPAddressPattern = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        
        /**
         * 
         * @param filePath
         * @return
         * @throws Exception 
         */
    public List<String> ExtractEmailAddressFile(File filePath) throws Exception
    {
        List<String> lstData=ReadRawData(filePath);
        List<String> lstEmailAddress=new ArrayList<>();
        Pattern patRegx=Pattern.compile(regExpression,Pattern.CASE_INSENSITIVE);
        for(String text:lstData)
        {
            String [] tokenizedText=text.split(" ");
            for(String token:tokenizedText)
            {
           if(token.endsWith(".")||token.endsWith(",")||token.endsWith("\"")||token.endsWith("\'"))
               token=token.substring(0, token.length()-1);
           if(token.startsWith(".")||token.startsWith(",")||token.startsWith("\"")||token.startsWith("\'"))
               token=token.substring(1,token.length());
            Matcher m=patRegx.matcher(token);
            if(m.find())
                lstEmailAddress.add(m.group());
            }
        }
        return lstEmailAddress;
    }
    
     /* public List<String> ExtractEmailAddressFile(List<String> lstData) throws IOException
      {
        //List<String> lstData=ReadData.ReadRawData(filePath);
        List<String> lstEmailAddress=new ArrayList<>();
        Pattern patRegx=Pattern.compile(regExpression,Pattern.CASE_INSENSITIVE);
        for(String text:lstData)
        {
            String [] tokenizedText=text.split(" ");
            for(String token:tokenizedText)
            {
           if(token.endsWith(".")||token.endsWith(",")||token.endsWith("\"")||token.endsWith("\'"))
               token=token.substring(0, token.length()-1);
           if(token.startsWith(".")||token.startsWith(",")||token.startsWith("\"")||token.startsWith("\'"))
               token=token.substring(1,token.length());
            Matcher m=patRegx.matcher(token);
            if(m.find())
                lstEmailAddress.add(m.group());
            }
        }
        return lstEmailAddress;
      }*/
    /**
     * 
     * @param tweet
     * @return
     * @throws IOException 
     */
    public List<String> ExtractEmailAddressSentence(String sentence) throws IOException
    {
        List<String> lstEmailAddress=new ArrayList<>();
        Pattern patRegx=Pattern.compile(regExpression,Pattern.CASE_INSENSITIVE);
            String [] tokenizedText=sentence.split(" ");
            for(String token:tokenizedText)
            {
           if(token.endsWith(".")||token.endsWith(",")||token.endsWith("\"")||token.endsWith("\'"))
               token=token.substring(0, token.length()-1);
           if(token.startsWith(".")||token.startsWith(",")||token.startsWith("\"")||token.startsWith("\'"))
               token=token.substring(1,token.length());
            Matcher m=patRegx.matcher(token);
            if(m.find())
                lstEmailAddress.add(m.group());
            }
        return lstEmailAddress;
    }
    /**
     * 
     * @param tweet
     * @return
     * @throws IOException 
     */
    public List<String> ExtractIPAddressSentence(String sentence) throws IOException
    {
        List<String> lstIPAddress=new ArrayList<>();
        Pattern patRegx=Pattern.compile(IPAddressPattern,Pattern.CASE_INSENSITIVE);
            String [] tokenizedText=sentence.split(" ");
            for(String token:tokenizedText)
            {
           if(token.endsWith(".")||token.endsWith(",")||token.endsWith("\"")||token.endsWith("\'"))
               token=token.substring(0, token.length()-1);
           if(token.startsWith(".")||token.startsWith(",")||token.startsWith("\"")||token.startsWith("\'"))
               token=token.substring(1,token.length());
            Matcher m=patRegx.matcher(token);
            if(m.find())
                lstIPAddress.add(m.group());
            }
        return lstIPAddress;
    }
    /**
     * 
     * @param filePath
     * @return
     * @throws Exception 
     */
    public List<String> ExtractIPAddressFile(File filePath) throws Exception
    {
       List<String> lstData=ReadRawData(filePath);
        List<String> lstIPAddress=new ArrayList<>();
        Pattern patRegx=Pattern.compile(IPAddressPattern,Pattern.CASE_INSENSITIVE);
        for(String text:lstData)
        {
            String [] tokenizedText=text.split(" ");
            for(String token:tokenizedText)
            {
           if(token.endsWith(".")||token.endsWith(",")||token.endsWith("\"")||token.endsWith("\'"))
               token=token.substring(0, token.length()-1);
           if(token.startsWith(".")||token.startsWith(",")||token.startsWith("\"")||token.startsWith("\'"))
               token=token.substring(1,token.length());
            Matcher m=patRegx.matcher(token);
            if(m.find())
                lstIPAddress.add(m.group());
            }
        }
        return lstIPAddress;
    }
    
   /* public List<String> ExtractIPAddressFile(List<String> lstData) throws IOException
    {
       //List<String> lstData=ReadData.ReadRawData(filePath);
        List<String> lstIPAddress=new ArrayList<>();
        Pattern patRegx=Pattern.compile(IPAddressPattern,Pattern.CASE_INSENSITIVE);
        for(String text:lstData)
        {
            String [] tokenizedText=text.split(" ");
            for(String token:tokenizedText)
            {
               if(token.endsWith(".")||token.endsWith(",")||token.endsWith("\"")||token.endsWith("\'"))
                   token=token.substring(0, token.length()-1);
               if(token.startsWith(".")||token.startsWith(",")||token.startsWith("\"")||token.startsWith("\'"))
                   token=token.substring(1,token.length());
                Matcher m=patRegx.matcher(token);
                if(m.find())
                    lstIPAddress.add(m.group());
            }
        }
        return lstIPAddress;
    }*/
}
