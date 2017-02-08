package unsw.curation.api.url;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Alireza
 */
public class GetHTMLFile {
    
    private Document docPub;
    /**
     * 
     * @param FilePath
     * @throws IOException 
     */
    public void ExtractLocal(String FilePath) throws IOException
    {
       docPub=Jsoup.parse(new File(FilePath), "UTF-8");
    }
    /**
     * 
     * @return
     * @throws IOException 
     */
    public String L_ExtractTitle() throws IOException
    {
        if(docPub==null)
         {
           throw new IOException("No Page To Parse");
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
    public List<String> L_ExtractHeadings() throws IOException
    {
        List<String> lstHeadings=new ArrayList<>();
         if(docPub==null)
         {
           throw new IOException("No Page To Parse");
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
    public List<String> L_ExtractHrefText() throws IOException
    {
        List<String> lstHref=new ArrayList<>();
        if(docPub==null)
         {
           throw new IOException("No Page To Parse");
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
    public List<String> L_ExtractParagraphes() throws IOException
    {
        List<String> lstParagraphes=new ArrayList<>();
         if(docPub==null)
         {
           throw new IOException("No Page To Parse");
         }
        Elements Paragraphes=docPub.select("p");
        for(Element Paragraph:Paragraphes)
        {
            if(Paragraph.text().length()>1)
            lstParagraphes.add(Paragraph.text());
        }
        return lstParagraphes;
    }
    /**
     * 
     * @param Position
     * @return
     * @throws IOException 
     */
    public String L_ExtractParagraphByPosition(int Position) throws IOException
    {
        if(docPub==null)
        {
           throw new IOException("No Page To Parse");
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
    public List<String> L_ExtractImageALTtext()
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
    public List<String> L_ExtractListTexts()
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
