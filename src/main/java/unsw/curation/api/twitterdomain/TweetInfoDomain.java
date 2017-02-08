package unsw.curation.api.twitterdomain;

import java.util.Date;
import java.util.List;

public class TweetInfoDomain {

	    public String location;
	    public String text;
	    public String source;
	    public long tweetID;
	    public long inReplyToUserID;
	    public Date createDate;
	    public String language;
	    public long userID;
            public String name;
	    public String description;
	    public String userName;
	    public int favouriteCount;
	    public int friendsCount;
	    public int followerCount;
            
            public String country;
            public String streetAddress;
            
            public String timeZone;
            public String url;
            public Date profileAge;
            public String ProfileImageUrl;
            
            public int retweetCount;
            public boolean isPossiblySensetive;
            
            public boolean geoEnabled;
            
            public boolean isRetweeted;
            
            public String countryCode;
            public String geometryType;
            
            public void setName(String name)
            {
                this.name=name;
            }
            public String getName()
            {
                return name;
            }
            
            public List<TweetInfoDomain> tweets;
            public void setTweets(List<TweetInfoDomain> tweets)
            {
                this.tweets=tweets;
            }
            public List<TweetInfoDomain> getTweets()
            {
                return this.tweets;
            }
            public void SetGeometryType(String geometryType)
            {
                this.geometryType=geometryType;
            }
            public String getGeometryType()
            {
                return this.geometryType;
            }
            
            public void setCountryCode(String countryCode)
            {
                this.countryCode=countryCode;
            }
            public String getCountryCode()
            {
                return this.countryCode;
            }
            
            public void setIsRetweeted(boolean isRetweeted)
            {
                this.isRetweeted=isRetweeted;
            }
            public boolean getIsRetweeted()
            {
                return this.isRetweeted;
            }
            
            public void setGeoEnabled(boolean geoEnabled)
            {
                this.geoEnabled=geoEnabled;
            }
            public boolean getGeoEnabled()
            {
                return geoEnabled;
            }
            
            public void setRetweetCount(int retweetCount)
            {
                this.retweetCount=retweetCount;
            }
            public int getRetweetCount()
            {
                return this.retweetCount;
            }
            
            public void setIsPossiblySensetive(boolean isPossiblySensetive)
            {
                this.isPossiblySensetive=isPossiblySensetive;
            }
            public boolean getIsPossiblySensetive()
            {
                return this.isPossiblySensetive;
            }
            
            public void setTimeZone(String timeZone)
            {
                this.timeZone=timeZone;
            }
            public String getTimeZone()
            {
                return timeZone;
            }
            
            public void setUrl(String url)
            {
                this.url=url;
            }
            public String getUrl()
            {
                return this.url;
            }
            
            public void setProfileAge(Date profileAge)
            {
                this.profileAge=profileAge;
            }
            public Date getProfileAge()
            {
                return profileAge;
            }
            
            public void setProfileImageUrl(String profileImageUrl)
            {
                this.ProfileImageUrl=profileImageUrl;
            }
            public String getProfileImageUrl()
            {
                return this.ProfileImageUrl;
            }
            
            public void setCountry(String country)
            {
                this.country=country;
            }
            public String getCountry()
            {
                return country;
            }
            
            public void setStreetAddress(String streetAddress)
            {
                this.streetAddress=streetAddress;
            }
            public String getStreetAddress()
            {
                return streetAddress;
            }
	    
	    public void setFavouriteCount(int favouriteCount)
	    {
	    	this.favouriteCount=favouriteCount;
	    }
	    public int getFavouriteCount()
	    {
	    	return this.favouriteCount;
	    }
	    
	    public void setFriendsCount(int friendsCount)
	    {
	    	this.friendsCount=friendsCount;
	    }
	    public int getFollowerCount()
	    {
	    	return this.friendsCount;
	    }
	    
	    public void setFollowerCount(int followerCount)
	    {
	    	this.followerCount=followerCount;
	    }
	    public int getfriendsCount()
	    {
	    	return this.followerCount;
	    }
	    
	    public void setUserName(String userName)
	    {
	    	this.userName=userName;
	    }
	    public String getUserName()
	    {
	    	return this.userName;
	    }
	    
	    public void setLocation(String location)
	    {
	    	this.location=location;
	    }
	    public String getLocation()
	    {
	    	return this.location;
	    }
	    
	    public void setText(String text)
	    {
	    	this.text=text;
	    }
	    public String getText()
	    {
	    	return this.text;
	    }
	    
	    public void setSource(String source)
	    {
	    	this.source=source;
	    }
	    public String getSource()
	    {
	    	return this.source;
	    }
	    
	    public void setDescription(String description)
	    {
	    	this.description=description;
	    }
	    public String getDescription()
	    {
	    	return this.description;
	    }
	    
	    public void setTweetID(long tweetID)
	    {
	    	this.tweetID=tweetID;
	    }
	    public long getTweetID()
	    {
	    	return this.tweetID;
	    }
	    
	    public void setCreateDate(Date createDate)
	    {
	    	this.createDate=createDate;
	    }
	    public Date getCreateDate()
	    {
	    	return this.createDate;
	    }
	    
	    public void setInReplyToUserID(long InReplyToUserID)
	    {
	    	this.inReplyToUserID=InReplyToUserID;
	    }
	    public long getInReplyToUserID()
	    {
	    	return this.inReplyToUserID;
	    }
	    
	    public void setUserID(long userID)
	    {
	    	this.userID=userID;
	    }
	    public long getUserID()
	    {
	    	return this.userID;
	    }
	    
	    public void setLanguage(String language)
	    {
	    	this.language=language;
	    }
	    public String getLanguage()
	    {
	    	return this.language;
	    }
	    
	    public TweetInfoDomain(){}
	    
	 /*   public TweetInfoDomain(TweetInfoDomain tweetDomain)
	    {
	    	
	    }*/
	    
	    public TweetInfoDomain(String geometryType,
                    String countryCode,
                    boolean isRetweeted,
                    int retweetCount,
                    boolean isPossiblySensetive,
                    String timeZone,
                    String url,
                    Date profileAge,
                    String profileImageUrl,
                    boolean geoEnabled,
                    String location,
                    String text, 
                    String source, 
                    long tweetID,
	            long inReplyToUserID,
                    Date createDate,
                    String language,
                    long userID, 
                    String description,
	            String userName,
                    int favouriteCount,
                    int friendsCount,
                    int followerCount,
                    String name,
                    String country)//, String country, String streetAddress )
	    {
                this.name=name;
                this.geometryType=geometryType;
                this.countryCode=countryCode;
                this.isRetweeted=isRetweeted;
                this.retweetCount=retweetCount;
                this.isPossiblySensetive=isPossiblySensetive;
                this.timeZone=timeZone;
                this.url=url;
                this.ProfileImageUrl=profileImageUrl;
                this.profileAge=profileAge;
                this.geoEnabled=geoEnabled;
	        this.location=location;
	        this.text=text;
	        this.source=source;
	        this.tweetID=tweetID;
	        this.inReplyToUserID=inReplyToUserID;
	        this.createDate=createDate;
	        this.language=language;
	        this.userID=userID;
	        this.description=description;
	        this.userName=userName;
	        this.favouriteCount=favouriteCount;
	        this.friendsCount=friendsCount;
	        this.followerCount=followerCount;    
                this.country=country;
               // this.streetAddress=streetAddress;
	    }
}
