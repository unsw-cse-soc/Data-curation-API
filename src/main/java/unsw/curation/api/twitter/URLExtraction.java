package unsw.curation.api.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import unsw.curation.api.twitterdomain.UrlDomain;
/**
 *
 * @author Alireza
 */
public class URLExtraction {
    
    private Document docPub;
    UrlDomain urlDomain;

    public URLExtraction() {
        urlDomain=new UrlDomain();
//                System.setProperty("http.proxyHost", "127.0.0.1");
//                System.setProperty("http.proxyPort", "8580");
//                System.setProperty("https.proxyHost", "127.0.0.1");
//                System.setProperty("https.proxyPort", "8580");
    }
    
    
    public void Extract(String Url) throws IOException
    {
//                System.setProperty("http.proxyHost", "127.0.0.1");
//                System.setProperty("http.proxyPort", "8580");
//                System.setProperty("https.proxyHost", "127.0.0.1");
//                System.setProperty("https.proxyPort", "8580");
//        URL url = new URL(Url);
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8580)); 
//        HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
//        uc.connect();
//        String line = null;
//        StringBuffer tmp = new StringBuffer();
//        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//        while ((line = in.readLine()) != null) {
//          tmp.append(line);
//        }
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080)); // or whatever your proxy is
//        HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
         docPub=Jsoup.connect(Url).timeout(10000).get();    
      //  docPub=Jsoup.parse(String.valueOf(tmp));
    }
    
    private String O_ExtractTitle() throws IOException
    {
        if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        String Title=docPub.title();
        return Title;
    }

     
    private List<String> ExtractHyperLink(String Sentence)
    {
     List<String> Links=new ArrayList<>();
     String [] splitedStr=Sentence.split(" ");
     for(String st:splitedStr){
        Pattern p=Pattern.compile("(http:|https:|ftp)[:-_?\\a-zA-Z\\d.*//]+");
        Matcher m=p.matcher(st);
        while(m.find())
        {
           Links.add(m.group());
        }
     }
     return Links;
    }
    
    List<String>lstTweetLinkList=new ArrayList<>();
    public List<String> ExtractLinkInfo(String tweet)
    {
        lstTweetLinkList=ExtractHyperLink(tweet);
        List<String> lstPargraphList=new ArrayList<>();
        if(lstTweetLinkList.size()>0)
        {
                for(String pageLink:lstTweetLinkList)
                {
                    try
                    {
                    List<String> lsttemp=new ArrayList<>();
                    Extract(pageLink);
                    lsttemp=O_ExtractParagraphes();
                    lstPargraphList.addAll(lsttemp);
                    }
                    catch(Exception ex)
                    {
                        System.err.println(ex.getMessage());
                    }
                }
        }
          return lstPargraphList;
    }
    
    private List<String> O_ExtractParagraphes() throws IOException
    {
        List<String> lstParagraphes=new ArrayList<>();
         if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        Elements Paragraphes=docPub.select("p");
        for(Element Paragraph:Paragraphes)
        {
            if(Paragraph.text().length()>1)
            lstParagraphes.add(Paragraph.text());
        }
        System.out.println("Downloading Page Content...");
        return lstParagraphes;
    }
    
    List<String>lstTweetTitle=new ArrayList<>();
    public List<String> ExtractTitle(String tweet)
    {
        lstTweetTitle=ExtractHyperLink(tweet);
        List<String> lstTitleList=new ArrayList<>();
        if(lstTweetTitle.size()>0)
        {
                for(String pageLink:lstTweetTitle)
                {
                    try
                    {
                    String title="";
                    Extract(pageLink);
                    title=O_ExtractTitle();
                    lstTitleList.add(title);
                    }
                    catch(Exception ex)
                    {
                        System.err.println(ex.getMessage());
                    }
                }
        }
          return lstTitleList;
    }
}
