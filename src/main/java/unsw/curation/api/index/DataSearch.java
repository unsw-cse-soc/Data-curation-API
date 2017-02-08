/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unsw.curation.api.index;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;


/**
 *
 * @author Alireza
 */

public class DataSearch {
	  IndexReader reader;
	  IndexSearcher indSearch;
	  Query query;
	  public DataSearch(String IndexDir) throws IOException
	  {
	      reader=DirectoryReader.open(FSDirectory.open(new File(IndexDir)));
	      indSearch=new IndexSearcher(reader);
	  }
	  public TopDocs search(String searchText, int slop) throws IOException, ParseException
	  {
		  PhraseQuery query = new PhraseQuery();
	      query.setSlop(slop);
	      String [] searchTerms=searchText.split(" ");
	      for(String searchWord:searchTerms)
	      query.add(new Term("body",searchWord.toLowerCase()));
	      return indSearch.search(query, 100); 	     
	  }
	  public Document getDocument(ScoreDoc score) throws CorruptIndexException, IOException
	  {
		  return indSearch.doc(score.doc);
	  }

}
