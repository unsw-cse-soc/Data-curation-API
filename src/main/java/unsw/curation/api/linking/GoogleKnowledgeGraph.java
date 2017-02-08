package unsw.curation.api.linking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class GoogleKnowledgeGraph {
	
	private final String USER_AGENT = "Mozilla/5.0";
	public void ParseGoogleKnowledgeGraph(List<String>lstEntity, String outputFileName) throws Exception {

		BufferedWriter writer=new BufferedWriter(new FileWriter(outputFileName));
		for(String str:lstEntity)
		{
		str=str.trim();
		if(str.contains(" "))
			str=str.replace(" ", "+");
		
		String url = "https://kgsearch.googleapis.com/v1/entities:search?query="+str+"&key=AIzaSyA6u_gvGgeBjUx5ThGhc2hvg-MiIfuYBkk&limit=1&indent=True";
		//https://www.wikidata.org/w/api.php?action=wbsearchentities&search=lionel messi&language=en&format=json
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			//System.out.println("Fetching Data From Wikidata");
			System.out.println(inputLine);
			writer.write(inputLine);
			writer.newLine();
		}
		in.close();
		}
		writer.close();
	}
}
