package unsw.curation.api.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;

import unsw.curation.api.twitterdomain.TweetInfoDomain;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetInfo {

    
	ConfigurationBuilder cb;
	int counter=0;
	int maxCount=0;
        XmlGenerator xg;
        int endTime=0;
	public TweetInfo(int MaxCount, File englishStopwordsFile, String OAuthConsumerKey,
			String OAUTHConsumerSecret,
			String OAUTHAccessToken,
			String OAUTHAccessTokenSecret) throws XMLStreamException, IOException
	{
		this.maxCount=MaxCount;
              //endTime=LocalTime.now().getMinute()+2;
              xg=new XmlGenerator(englishStopwordsFile);
	      cb=new ConfigurationBuilder();
	      cb.setDebugEnabled(true);
	      cb.setOAuthConsumerKey("yJfMtMvwBo8ygMXW6C53nIiai");
	      cb.setOAuthConsumerSecret("YaO21MifLQVBlPTPCkgL7iBEk3CrtpsViaVE4B6Dsriss3Z1QM");
	      cb.setOAuthAccessToken("704646632747331584-LNRXY9JmLaAxbPyrIknf1eh6Dxc6ggF");
	      cb.setOAuthAccessTokenSecret("gGmwmnmpbkO3eZmBpXjRnuF5sMqGWhxASu49RFm9H5Saz");
	}
	
/*	public TweetInfo(int MaxCount, File englishStopWordsFile,String OAuthConsumerKey,
			String OAUTHConsumerSecret,
			String OAUTHAccessToken,
			String OAUTHAccessTokenSecret) throws XMLStreamException, IOException
	{
              xg=new XmlGenerator(englishStopWordsFile);
              this.MaxCount=MaxCount;
	      cb=new ConfigurationBuilder();
              cb.setDebugEnabled(true);
              cb.setOAuthConsumerKey("yJfMtMvwBo8ygMXW6C53nIiai");
              cb.setOAuthConsumerSecret("YaO21MifLQVBlPTPCkgL7iBEk3CrtpsViaVE4B6Dsriss3Z1QM");
              cb.setOAuthAccessToken("704646632747331584-LNRXY9JmLaAxbPyrIknf1eh6Dxc6ggF");
              cb.setOAuthAccessTokenSecret("gGmwmnmpbkO3eZmBpXjRnuF5sMqGWhxASu49RFm9H5Saz");
	      
	}*/
       /* private boolean CityNames(String getLocation) throws IOException
        {
            List<String> lstCity=new ArrayList<>();
            BufferedReader reader= new BufferedReader(new FileReader("AUCity.txt"));
            String line="";
            while((line=reader.readLine())!=null)
            {
                lstCity.add(line);
            }
            boolean exists=lstCity.contains(getLocation);
            return exists;
        }*/
        List<TweetInfoDomain> lstInfoDomain=new ArrayList<>();
        
        public List<TweetInfoDomain> Printddd()
        {
           return lstInfoDomain;
        }
	public void StartStreaming() throws FileNotFoundException, XMLStreamException, IOException
	{
                TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		TweetInfoDomain tweetDomain=new TweetInfoDomain();
		StatusListener myListener=new StatusListener() {
			
			@Override
			public void onException(Exception arg0) {
			}
			@Override
			public void onTrackLimitationNotice(int arg0) {			
			}
			@Override
			public void onStatus(Status status) {
                            if(status.getLang().equals("en"))
                            {
                                
                tweetDomain.setName(status.getUser().getScreenName());
				tweetDomain.setText(status.getText());
				System.out.println(status.getText());
				tweetDomain.setTweetID(status.getId());
				tweetDomain.setDescription(status.getUser().getDescription());
				System.out.println(status.getUser().getDescription());
				tweetDomain.setCreateDate(status.getCreatedAt());
				tweetDomain.setFavouriteCount(status.getUser().getFavouritesCount());
				tweetDomain.setFollowerCount(status.getUser().getFollowersCount());
				tweetDomain.setLocation(status.getUser().getLocation());
				tweetDomain.setLanguage(status.getLang());
				tweetDomain.setSource(status.getSource());
				tweetDomain.setUserID(status.getUser().getId());
				tweetDomain.setFriendsCount(status.getUser().getFriendsCount());
				tweetDomain.setInReplyToUserID(status.getInReplyToUserId());
				tweetDomain.setUserName(status.getUser().getName());
                                tweetDomain.setIsRetweeted(status.isRetweeted());
                                //status.getUser().getName();
//                                tweetDomain.setCountryCode(status.getPlace().getCountryCode());
//                                tweetDomain.SetGeometryType(status.getPlace().getGeometryType());
//                                tweetDomain.setCountry(status.getPlace().getCountry());
                                tweetDomain.setRetweetCount(status.getRetweetCount());
                                tweetDomain.setIsPossiblySensetive(status.isPossiblySensitive());
                                tweetDomain.setTimeZone(status.getUser().getTimeZone());
                                tweetDomain.setUrl(status.getUser().getURL());
                                tweetDomain.setProfileAge(status.getUser().getCreatedAt());
                                tweetDomain.setProfileImageUrl(status.getUser().getProfileImageURL());
                                tweetDomain.setGeoEnabled(status.getUser().isGeoEnabled());
                                
			lstInfoDomain.add(new TweetInfoDomain(tweetDomain.getGeometryType(),
                                tweetDomain.getCountryCode(),
                                tweetDomain.getIsRetweeted(),
                                tweetDomain.getRetweetCount(),
                                tweetDomain.getIsPossiblySensetive(),
                                tweetDomain.getTimeZone(),
                                tweetDomain.getUrl(),
                                tweetDomain.getProfileAge(),
                                tweetDomain.getProfileImageUrl(),
                                tweetDomain.getGeoEnabled(),
                                tweetDomain.getLocation(),
					tweetDomain.getText(),
					tweetDomain.getSource(),
					tweetDomain.getTweetID(),
					tweetDomain.getInReplyToUserID(),
					tweetDomain.getCreateDate(),
					tweetDomain.getLanguage(),
					tweetDomain.getUserID(),
					tweetDomain.getDescription(),
					tweetDomain.getUserName(),
					tweetDomain.getFavouriteCount(),
					tweetDomain.getfriendsCount(),
					tweetDomain.getFollowerCount(),
                                        tweetDomain.getName(),
                                        tweetDomain.getCountry()
					));
                        System.out.println(counter++);
//                        int startTime=LocalTime.now().getMinute();
//                        if(String.valueOf(startTime).equals(String.valueOf(endTime)))
//                         {
//                         	twitterStream.shutdown();
//            	//ReturnStreamingValue();
//           
//                            
//                            System.out.println("Shut downed...");
//                            System.out.println("after Shut Downed..."+lstInfoDomain.size()); 
//                            Printddd();
//                        }

                      } 
			  if(counter>maxCount)
			  {
                                    try {
                                        int co=0;
                                        xg.StartXML();
                                        for(TweetInfoDomain tf:lstInfoDomain)
				         {
                                            //if(tf.getLanguage().equalsIgnoreCase("en"))
					    xg.AddToXml(tf);
                                             //System.out.println(co);
				         }
                                         xg.CloseXMLFile();                                    
                                    } catch (XMLStreamException ex) {
                                        Logger.getLogger(TweetInfo.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(TweetInfo.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (Exception ex) {
                                        Logger.getLogger(TweetInfo.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                  System.exit(0);
			  }
                          
                        }
			@Override
			public void onStallWarning(StallWarning arg0) {
			}
			@Override
			public void onScrubGeo(long arg0, long arg1) {
			}
			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
			}
		};

        twitterStream.addListener(myListener);
        twitterStream.sample();
   

        
	}
        public List<TweetInfoDomain> Printlst()
        {
//            for(TweetInfoDomain tweet: lst)
//            {
//                System.out.println(tweet.text);
//            }
            return lstInfoDomainWithKey;
        }
        List<TweetInfoDomain> lstInfoDomainWithKey=new ArrayList<>();
	public void StartStreamingByKeyword(String [] arrKeys) throws XMLStreamException, FileNotFoundException, IOException
	{
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		TweetInfoDomain tweetDomain=new TweetInfoDomain();
		
		StatusListener myListener=new StatusListener() {
			@Override
			public void onException(Exception arg0) {
			}
			
			@Override
			public void onTrackLimitationNotice(int arg0) {	
			}
			
			@Override
			public void onStatus(Status status) {
                            try
                            {
                                
                                
				tweetDomain.setText(status.getText());
				tweetDomain.setTweetID(status.getId());
				tweetDomain.setDescription(status.getUser().getDescription());
				tweetDomain.setCreateDate(status.getCreatedAt());
				tweetDomain.setFavouriteCount(status.getUser().getFavouritesCount());
				tweetDomain.setFollowerCount(status.getUser().getFollowersCount());
				tweetDomain.setLocation(status.getUser().getLocation());
				tweetDomain.setLanguage(status.getLang());
				tweetDomain.setSource(status.getSource());
				tweetDomain.setUserID(status.getUser().getId());
				tweetDomain.setFriendsCount(status.getUser().getFriendsCount());
				tweetDomain.setInReplyToUserID(status.getInReplyToUserId());
				tweetDomain.setUserName(status.getUser().getName());
                                tweetDomain.setIsRetweeted(status.isRetweeted());
//                                tweetDomain.setCountryCode(status.getPlace().getCountryCode());
//                                tweetDomain.SetGeometryType(status.getPlace().getGeometryType());
//                                tweetDomain.setCountry(status.getPlace().getCountry());
                                tweetDomain.setRetweetCount(status.getRetweetCount());
                                tweetDomain.setIsPossiblySensetive(status.isPossiblySensitive());
                                tweetDomain.setTimeZone(status.getUser().getTimeZone());
                                tweetDomain.setUrl(status.getUser().getURL());
                                tweetDomain.setProfileAge(status.getUser().getCreatedAt());
                                tweetDomain.setProfileImageUrl(status.getUser().getProfileImageURL());
                                tweetDomain.setGeoEnabled(status.getUser().isGeoEnabled());
                                
			lstInfoDomainWithKey.add(new TweetInfoDomain(tweetDomain.getGeometryType(),
                                tweetDomain.getCountryCode(),
                                tweetDomain.getIsRetweeted(),
                                tweetDomain.getRetweetCount(),
                                tweetDomain.getIsPossiblySensetive(),
                                tweetDomain.getTimeZone(),
                                tweetDomain.getUrl(),
                                tweetDomain.getProfileAge(),
                                tweetDomain.getProfileImageUrl(),
                                tweetDomain.getGeoEnabled(),
                                tweetDomain.getLocation(),
					tweetDomain.getText(),
					tweetDomain.getSource(),
					tweetDomain.getTweetID(),
					tweetDomain.getInReplyToUserID(),
					tweetDomain.getCreateDate(),
					tweetDomain.getLanguage(),
					tweetDomain.getUserID(),
					tweetDomain.getDescription(),
					tweetDomain.getUserName(),
					tweetDomain.getFavouriteCount(),
					tweetDomain.getfriendsCount(),
					tweetDomain.getFollowerCount(),
                                        tweetDomain.getName(),
                                        tweetDomain.getCountry()
					));
                        System.out.println(counter++);
                       // System.out.println(counter++);
//                        int startTime=LocalTime.now().getMinute();
//                        if(String.valueOf(startTime).equals(String.valueOf(endTime)))
//                         {
//                         	twitterStream.shutdown();
//            	//ReturnStreamingValue();
//           
//                            
//                            System.out.println("Shut downed...");
//                            System.out.println("after Shut Downed..."+lstInfoDomain.size()); 
//                            Printddd();
//                        }
                            }
                            catch(Exception ex){}
			  if(counter>maxCount)
			  {
                                    try {
                                        xg.StartXML();
                                        for(TweetInfoDomain tf:lstInfoDomainWithKey)
				         {
                                             //if(tf.getLocation()!=null)
                                             {
                                              //   if(CityNames(tf.getLocation().toLowerCase()))
                                                 {
                                                //     System.out.println("AUSTRALIA-AUSTRALIA-AUSTRALIA-AUSTRALIA-AUSTRALIA-AUSTRALIA");
                                                       xg.AddToXml(tf);     
                                                 }
                                             }
				         }
                                         xg.CloseXMLFile();
                                    } catch (XMLStreamException ex) {
                                        Logger.getLogger(TweetInfo.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(TweetInfo.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (Exception ex) {
                                        Logger.getLogger(TweetInfo.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				  
                                  System.exit(0);
			  }
			}
			@Override
			public void onStallWarning(StallWarning arg0) {
			}
			
			@Override
			public void onScrubGeo(long arg0, long arg1) {
			}
			
			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
			}
		};
        FilterQuery fq = new FilterQuery();     
        fq.track(arrKeys);
        fq.language("en");
        twitterStream.addListener(myListener);
        twitterStream.filter(fq);
	}
}
