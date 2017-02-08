package unsw.curation.api.linking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonObject;

import org.apache.lucene.queryparser.flexible.standard.parser.StandardSyntaxParser;
import org.json.JSONObject;

import weka.filters.unsupervised.attribute.ReplaceMissingValues;

public class ConceptNet {

	private final String USER_AGENT = "Mozilla/5.0";
	private String FindUrl(String terms) throws Exception 
	{
		String str=terms.replaceAll(" ","_");
		String url=" http://conceptnet5.media.mit.edu/data/5.4/uri?language=en&text="+str;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		String returnVal="";
		while ((inputLine = in.readLine()) != null)
		{
			if(inputLine.length()>2)
			{
				returnVal=inputLine.split(":")[1];
			}
		}
		return returnVal;
	   }

	public void ConceptNetLookUp(String terms) throws Exception
	{
		String getString=FindUrl(terms);
		getString=getString.replace("\"", "").trim();
		String url="http://conceptnet5.media.mit.edu/data/5.4"+getString;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		String jsonText="";
		while ((inputLine = in.readLine()) != null)
		{
			jsonText+=inputLine;
		}
		in.close();
		JSONObject jsonObject=new JSONObject(jsonText);
		int objectCount=jsonObject.getJSONArray("edges").length();
		List<String> lstValues=new ArrayList<>();
		for(int i=0;i<objectCount;i++)
		{
			lstValues.add(jsonObject.getJSONArray("edges").getJSONObject(i).get("surfaceText").toString());
		}
		System.out.println(lstValues.stream().distinct().collect(Collectors.toList()));
	}
	public void ConceptNetPartOfSearch(String terms, int count) throws Exception
	{
		String getString=FindUrl(terms);
		getString=getString.replace("\"", "").trim();
		//String url="http://conceptnet5.media.mit.edu/data/5.4"+getString;
		String url="http://conceptnet5.media.mit.edu/data/5.4/search?rel=/r/PartOf&end="+getString+"&limit="+count;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		String jsonText="";
		while ((inputLine = in.readLine()) != null)
		{
			jsonText+=inputLine;
		}
		in.close();
		JSONObject jsonObject=new JSONObject(jsonText);
		int objectCount=jsonObject.getJSONArray("edges").length();
		List<String> lstValues=new ArrayList<>();
		for(int i=0;i<objectCount;i++)
		{
			lstValues.add(jsonObject.getJSONArray("edges").getJSONObject(i).get("surfaceText").toString());
		}
		System.out.println(lstValues.stream().distinct().collect(Collectors.toList()));
	}
	public void ConceptNetAssociation(String terms) throws Exception
	{
		String getString=FindUrl(terms);
		getString=getString.replace("\"", "").trim();
		//String url="http://conceptnet5.media.mit.edu/data/5.4"+getString;
		String url="http://conceptnet5.media.mit.edu/data/5.4/assoc"+getString;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		String jsonText="";
		while ((inputLine = in.readLine()) != null)
		{
			jsonText+=inputLine;
		}
		in.close();
		JSONObject jsonObject=new JSONObject(jsonText);
		int objectCount=jsonObject.getJSONArray("similar").length();
		System.out.println(objectCount);
		List<String> lstValues=new ArrayList<>();
		for(int i=0;i<objectCount;i++)
		{
			System.out.println(jsonObject.getJSONArray("similar").optString(i));
		}
	}
	public void ConceptNetAssociationSimilarity(String object1, String object2) throws Exception
	{
		String getString1=FindUrl(object1);
		getString1=getString1.replace("\"", "").trim();
		
		String getString2=FindUrl(object2);
		getString2=getString2.replace("\"", "").trim();
		//String url="http://conceptnet5.media.mit.edu/data/5.4"+getString;
		String url="http://conceptnet5.media.mit.edu/data/5.4/assoc"+getString1+"?filter="+getString2+"/.&limit=1";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		String jsonText="";
		while ((inputLine = in.readLine()) != null)
		{
			jsonText+=inputLine;
		}
		in.close();
		JSONObject jsonObject=new JSONObject(jsonText);
		int objectCount=jsonObject.getJSONArray("similar").length();
		System.out.println(objectCount);
		List<String> lstValues=new ArrayList<>();
		for(int i=0;i<objectCount;i++)
		{
			System.out.println(jsonObject.getJSONArray("similar").optString(i));
		}
	}
	public void ConceptNetAssociationWords(String [] wordList) throws Exception
	{
		String commaWord="";
		for(String word: wordList)
		{
			commaWord+=word+",";
		}
		commaWord=commaWord.substring(0,commaWord.length()-1);
		//String getString2=FindUrl(object2);
		//getString2=getString2.replace("\"", "").trim();
		//String url="http://conceptnet5.media.mit.edu/data/5.4"+getString;
		String url="http://conceptnet5.media.mit.edu/data/5.4/assoc/list/en/"+commaWord;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		String jsonText="";
		while ((inputLine = in.readLine()) != null)
		{
			jsonText+=inputLine;
		}
		in.close();
		JSONObject jsonObject=new JSONObject(jsonText);
		int objectCount=jsonObject.getJSONArray("similar").length();
		System.out.println(objectCount);
		List<String> lstValues=new ArrayList<>();
		for(int i=0;i<objectCount;i++)
		{
			System.out.println(jsonObject.getJSONArray("similar").optString(i));
		}
	}
	
}
