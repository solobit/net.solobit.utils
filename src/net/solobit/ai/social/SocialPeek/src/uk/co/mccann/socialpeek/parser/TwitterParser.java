package uk.co.mccann.socialpeek.parser;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;



import org.apache.log4j.Logger;
import winterwell.jtwitter.TwitterException;
import winterwell.jtwitter.Status;
import winterwell.jtwitter.User;
import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.exceptions.ParseException;
import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.model.PeekData;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.EnhancedTwitter;
import uk.co.mccann.socialpeek.service.TwitterService;


/**
 * <b>TwitterParser</b><br/>
 * Uses ThinkTank's jTwitter library to collect and process twitter information
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
* otherwise stated. It is released as
* open-source under the Creative Commons NC-SA license. See
* <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
* for license details. This code comes with no warranty or support.
 *
 * @author Dave Shanley <david.shanley@europe.mccann.com>
 */
public class TwitterParser extends AbstractParser  {
	
	EnhancedTwitter twitter;
	
	/**
     *  Default constructor, calls super constructor from AbstractParser
     *  @param service set's the social service for the parser
     */
	public TwitterParser(SocialService service) {
		super(service);
	}
	
	public TwitterParser() { }
	
	/**
     *  Configures the parser for any properties that need initializing
     *  
     */
	@Override
	public void setUpParser() {
		super.setUpParser();
		
		/* create a new twitter engine, using thinktank's jTwitter lib */
		this.twitter = new EnhancedTwitter(this.getSocialService().getUsername(),this.getSocialService().getPassword());
		this.logger = Logger.getLogger(TwitterParser.class);
	}
	
	private Data compileTwitterData(Status status) {
	
		
		Data twitterUser = new PeekData();
		twitterUser.setUser(status.getUser().getName());
		twitterUser.setLink(TwitterService.TWITTER_URL + status.getUser().getScreenName() + "/statuses/" + status.getId());
			
		/* set up calendar */
		Calendar cal = Calendar.getInstance();
		cal.setTime(status.getCreatedAt());
		twitterUser.setDate(cal);
			
		try {
		       
			byte[] utf8Bytes = status.getText().getBytes("UTF8");
		        
			/* convert to UTF-8*/
		    String newBody = new String(utf8Bytes, "UTF-8");
		        
		    twitterUser.setBody("said '<a href=\"" + twitterUser.getLink() + "\"><strong>" + status.getUser().getScreenName() + "</strong></a>'");
		    twitterUser.setHeadline(newBody);
		    
		    /* check to see if there is a photo attached to the user */
			if(status.getUser().getProfileImageUrl()!=null) {
				twitterUser.setThumbnail(status.getUser().getProfileImageUrl().toString());
			}
			if(status.getUser().getLocation()!=null) {
				twitterUser.setLocation(status.getUser().getLocation());
			}
				
			return twitterUser;
		    	
		} catch(UnsupportedEncodingException e) {
		    	
			return null;
		    	 
		}
	
	}
	
	public List<Data> getItems(int limit) throws ParseException {
		
		
		
		/* implementation code! */
		try {
			
			List<Data> extractedData = new ArrayList<Data>();
			
			/* get featured users */
			List<User> featuredUsers = this.twitter.getFeatured(); 
			
			/* iterate through all the users and add their posts to the extracted data (we will shuffle it all up later) */
			Collections.shuffle(featuredUsers);
			
			for(User user : featuredUsers) {
				
				Status status = user.getStatus();
				
				extractedData.add(this.compileTwitterData(status));
				
			}
			
			/* grab the public timeline as well so we have a truely random view of what is going on. */
			List<Status> publicUsers = this.twitter.getPublicTimeline();
			
			/* iterate through all the users and add their posts to the extracted data (we will shuffle it all up later) */
			Collections.shuffle(publicUsers);
			
			for(Status status : publicUsers) {
				
				extractedData.add(this.compileTwitterData(status));
				
			}
			
			/* shuffle it up for some randomness */
			Collections.shuffle(extractedData);
			
			List<Data> compactedData = new ArrayList<Data>();
			
			/* now trim it up */
			if(limit > extractedData.size()) limit = extractedData.size(); // make sure we don't go out of bounds!
			for(int x = 0; x < limit; x++) {
				compactedData.add(extractedData.get(x));
			}
			return compactedData;
		
		} catch (TwitterException e) {
			
			throw new ParseException("twitter parsing failed : " + e.getMessage());
		}
	}

	public Data getItem() throws ParseException {
		
		List<Data> extractedData = this.getItems(20);
		
		return extractedData.get(this.random.nextInt(extractedData.size()-1));
	}

	
	public Data getKeywordItem(String keyword) throws ParseException {
		
		if(SocialPeek.logging) {
			this.logger.info("searching twitter with keyword: " + keyword);
		}
		
		List<Status> searchResults = this.twitter.searchTwitter(keyword);
		
		if(SocialPeek.logging) {
			this.logger.info("search results : " + searchResults.size());
		}
		
		Data userItem = this.compileTwitterData(searchResults.get(this.random.nextInt(searchResults.size()-1)));
		return userItem;
		
	}
	
	
	public List<Data> getKeywordItems(String keyword, int limit) throws ParseException {
		
		if(SocialPeek.logging) {
			this.logger.info("searching twitter with keyword: " + keyword);
		}
		
		if(SocialPeek.logging) {
			this.logger.info("searching twitter with keyword: " + keyword);
		}
		
		List<Status> searchResults = this.twitter.searchTwitter(keyword);
		
		if(SocialPeek.logging) {
			this.logger.info("search results : " + searchResults.size());
		}
		
		/* shuffle it up for some randomness */
		Collections.shuffle(searchResults);
		
		List<Data> compactedData = new ArrayList<Data>();
		
		/* now trim it up */
		if(limit > searchResults.size()) limit = searchResults.size(); // make sure we don't go out of bounds!
		for(int x = 0; x < limit; x++) {
			compactedData.add(this.compileTwitterData(searchResults.get(x)));
		}
		return compactedData;
		
	}

	public List<Data> getKeywordItems(String[] keywords, int limit) throws ParseException {
		
		if(SocialPeek.logging) {
			for(String keyword: keywords)
				this.logger.info("searching twitter with keyword: " + keyword);
		}
		
		if(SocialPeek.logging) {
			for(String keyword: keywords)
				this.logger.info("searching twitter with keyword: " + keyword);
		}
		
		List<Status> resultArray = new ArrayList<Status>();
		
		for(String keyword : keywords) {
			List<Status> searchResults = this.twitter.searchTwitter(keyword);
			resultArray.addAll(searchResults);
		}
			
		if(SocialPeek.logging) {
			this.logger.info("search results : " + resultArray.size());
		}
		
		/* shuffle it up for some randomness */
		Collections.shuffle(resultArray);
		
		List<Data> compactedData = new ArrayList<Data>();
		
		/* now trim it up */
		if(limit > resultArray.size()) limit = resultArray.size(); // make sure we don't go out of bounds!
		for(int x = 0; x < limit; x++) {
			compactedData.add(this.compileTwitterData(resultArray.get(x)));
		}
		return compactedData;
	}

	public List<Data> getUserItems(int userId, int limit) throws ParseException {
		
		/* use string based overloaded method */
		return this.getUserItems(String.valueOf(userId), limit);
		
	}

	public List<Data> getUserItems(String userId, int limit) throws ParseException {
		
		
		/* implementation code! */
		try {
			
			List<Data> extractedData = new ArrayList<Data>();
			List<Status> list = this.twitter.getUserTimeline(userId, limit, null);
			
			/* counter */
			int counter = 0;
			
			for(Status status : list) {
				if(counter < limit) {
					
					extractedData.add(this.compileTwitterData(status));
				
				}
			}
			
			return extractedData;
		
		} catch (TwitterException e) {
			
			/* check for a user not found message */
			if(e.getMessage().contains("404")) {
				throw new ParseException("twitter parsing failed : username '" + userId + "' does not exist!");
			} else {		
				throw new ParseException("twitter parsing failed : " + e.getMessage());
			}
		}
		
	}

	public Data getUserItem(int userId) throws ParseException {
		List<Data> extractedData = this.getUserItems(String.valueOf(userId), 20);
		
		return extractedData.get(this.random.nextInt(extractedData.size()-1));
	}

	public Data getUserItem(String userId) throws ParseException {
		List<Data> extractedData = this.getUserItems(userId, 20);
		
		return extractedData.get(this.random.nextInt(extractedData.size()-1));
	}

	public List<Data> getLatestUserItems(int userId, int limit) throws ParseException {
		return this.getUserItems(String.valueOf(userId), limit);
	}

	public List<Data> getLatestUserItems(String userId, int limit) throws ParseException {
		return this.getUserItems(String.valueOf(userId), limit);
	}

	public Data getLatestUserItem(int userId) throws ParseException {
		
		String user = String.valueOf(userId);	
		return this.getUserItem(user);
		
	}

	public Data getLatestUserItem(String userId) throws ParseException {
		/* implementation code! */
		
		try {
			
			Status userStatus = this.twitter.getStatus(userId);
			Data userItem = this.compileTwitterData(userStatus);
			
			return userItem;
			
		} catch (TwitterException e) {
			
			throw new ParseException("twitter parsing failed : " + e.getMessage());
		}
	}

	public Data getKeywordItem(String[] keywords) throws ParseException {
		
		if(SocialPeek.logging) {
			for(String keyword: keywords)
				this.logger.info("searching twitter with keyword: " + keyword);
		}
		
		if(SocialPeek.logging) {
			for(String keyword: keywords)
				this.logger.info("searching twitter with keyword: " + keyword);
		}
		
		List<Status> resultArray = new ArrayList<Status>();
		
		for(String keyword : keywords) {
			List<Status> searchResults = this.twitter.searchTwitter(keyword);
			resultArray.addAll(searchResults);
		}
			
		if(SocialPeek.logging) {
			this.logger.info("search results : " + resultArray.size());
		}
		
		/* shuffle it up for some randomness */
		Collections.shuffle(resultArray);
		
		Data userItem = this.compileTwitterData(resultArray.get(this.random.nextInt(resultArray.size()-1)));
		return userItem;
		
		
	}

	
}
