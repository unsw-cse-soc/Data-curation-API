
package unsw.curation.api.url;



import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
/**
 *
 * @author Alireza
 */
public class GetURL {
    
    private Document docPub;
    /**
     * 
     * @param Url
     * @throws IOException 
     */
    public void Extract(String Url) throws IOException
    {
         docPub=Jsoup.connect(Url).timeout(10000).get();
         
    }
    /**
     * 
     * @return
     * @throws IOException 
     */
    public String O_ExtractTitle() throws IOException
    {
        if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        String Title=docPub.title();
        return Title;
    }
    /**
     * 
     * @param Url
     * @return
     * @throws IOException 
     */
    public List<String> O_ExtractH1() throws IOException
    {
        List<String>lstH1=new ArrayList<>();
        if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        Elements H1=docPub.select("H1");
        for(Element H:H1)
        {
            lstH1.add(H.text());
        }
        return lstH1;
    }
    
    public List<String> O_ExtractH3() throws IOException
    {
        List<String>lstH3=new ArrayList<>();
        if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        Elements H3=docPub.select("H3");
        System.out.println("Downloading H3");
        for(Element H:H3)
        {
            lstH3.add(H.text());
        }
        return lstH3;
    }
    
    public List<String> O_ExtractH4() throws IOException
    {
        List<String>lstH4=new ArrayList<>();
        if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        Elements H4=docPub.select("H4");
        for(Element H:H4)
        {
            lstH4.add(H.text());
        }
        return lstH4;
    }
    
    public List<String> O_ExtractH2() throws IOException
    {
        List<String>lstH2=new ArrayList<>();
        if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        Elements H2=docPub.select("H2");
        for(Element H:H2)
        {
            lstH2.add(H.text());
        }
        return lstH2;
    }
    
    
    
    public List<String> O_ExtractHeadings() throws IOException
    {
        List<String> lstHeadings=new ArrayList<>();
         if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        Elements H1=docPub.select("h1");
        Elements H2=docPub.select("h2");
        Elements H3=docPub.select("H3");
        Elements H4=docPub.select("H4");
        for(Element H:H1)
        {
            lstHeadings.add(H.text());
        }
        for(Element H:H2)
        {
            lstHeadings.add(H.text());
        }
        for(Element H:H3)
        {
            lstHeadings.add(H.text());
        }
        for(Element H:H4)
        {
            lstHeadings.add(H.text());
        }
        return lstHeadings;
    }
    /**
     * 
     * @return
     * @throws IOException 
     */
    public List<String> O_ExtractHrefText() throws IOException
    {
        List<String> lstHref=new ArrayList<>();
        if(docPub==null)
         {
           throw new IOException("No Page To Download");
         }
        Elements Hrefs=docPub.select("a[href]");
        for(Element Href:Hrefs)
        {
            lstHref.add(Href.text());
        }
        return lstHref;        
    }
    /**
     * 
     * @return
     * @throws IOException 
     */
    public List<String> O_ExtractParagraphes() throws IOException
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
    /**
     * 
     * @param Position
     * @return
     * @throws IOException 
     */
    public String O_ExtractParagraphByPosition(int Position) throws IOException
    {
        if(docPub==null)
        {
           throw new IOException("No Page To Download");
        }
        try{
        List<String> lstParagraphes=new ArrayList<>();
        Elements Paragraphes=docPub.select("p");
        for(Element Paragraph:Paragraphes)
        {
            if(Paragraph.text().length()>1)
            lstParagraphes.add(Paragraph.text());
        }
        
        if(lstParagraphes.size()<Position)
        {
            System.out.println(String.format("There are %s Paragraphes in the Current Document ",
                    lstParagraphes.size()));
           return "Incorrect Paragraph Position";
        }
        return lstParagraphes.get(Position-1);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    /**
     * 
     * @return 
     */
    public List<String> O_ExtractImageALTtext()
    {
        List<String> lstImage=new ArrayList<>();
         Elements src=docPub.select("img[src]");
	 for(Element el:src)
	 {
             if(el.attr("alt").length()>1)
               lstImage.add(el.attr("alt"));
	 }
        return lstImage;
    }
    /**
     * 
     * @return 
     */
    public List<String> O_ExtractListTexts()
    {
        List<String> lstUl=new ArrayList<>();
        Elements Ul=docPub.select("ul");
        for(Element e:Ul)
        {
            lstUl.add(e.text());
        }
        return lstUl;
    }
//    public void SaveResult(List<String> LstData,String Path) throws IOException
//    {
//      BufferedWriter buf=new BufferedWriter(new FileWriter(Path));
//      for(String Values:LstData)
//      {
//          buf.write(Values);
//          buf.newLine();
//      }
//      buf.close();
//    }
//    public void SaveResult(String Values,String Path) throws IOException
//    {
//      BufferedWriter buf=new BufferedWriter(new FileWriter(Path));
//          buf.write(Values);
//          buf.newLine();
//         buf.close();
//    }
}
