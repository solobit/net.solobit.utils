//package uk.co.mccann.socialpeek.parser;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//import org.apache.commons.httpclient.HttpURL;
//
//import uk.co.mccann.socialpeek.exceptions.ParseException;
//import uk.co.mccann.socialpeek.interfaces.Data;
//import uk.co.mccann.socialpeek.model.PeekData;
//import uk.co.mccann.socialpeek.service.TechnoratiService;
//
///**
//* <b>TechnoratiParser</b><br/>
//* Use technorati RSS feeds to generate data
//*
//* <h4>Copyright and License</h4>
//* This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
//* otherwise stated. It is released as
//* open-source under the Creative Commons NC-SA license. See
//* <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
//* for license details. This code comes with no warranty or support.
//* @author Dave Shanley <david.shanley@europe.mccann.com>
//*/
//public class TechnoratiBackup extends AbstractParser {
//	
//	private SimpleDateFormat technoratiDateFormat;
//	private FeedReader reader;
//	private ChannelFeed channel;
//	private long expireLengthMillis = 1800000; // 30  minutes;.
//	
//	private boolean checkRSSCachedFileValid(File file) {
//		
//		if(file.exists()) {
//			
//			long time = System.currentTimeMillis();
//			if(file.lastModified() > (time - this.expireLengthMillis)) {
//				
//				return true;
//				
//			} else {
//				
//				return false;
//			}
//			
//		} else {
//			
//			return false;
//		}
//		
//	}
//	
//	private void createRSSCachedFile(File file, ChannelFeed feed) throws ParseException {
//	
//		try {
//			
//			FeedWriter w = new FeedWriter(file);
//			w.writeChannel(feed);
//		
//		} catch (YarfrawException e) {
//		
//			throw new ParseException("unable to write file: " + e.getMessage());
//		}
//		
//		
//	}
//	
//	private Data compileTechnoratiData(ItemEntry rssItem) throws java.text.ParseException {
//		
//		this.setUpParser(); // re-set things!
//		
//		Data data = new PeekData();
//		
//		data.setBody(rssItem.getDescriptionOrSummaryText());
//		
//		/* create a new calendar object */
//		Calendar pubDate = Calendar.getInstance();
//		pubDate.setTime(this.technoratiDateFormat.parse(rssItem.getPubDate()));
//		
//		data.setDate(pubDate);
//		data.setHeadline(rssItem.getTitleText());
//		data.setLink(rssItem.getLinks().get(0).getHref()); // only take the first link
//		//data.setUser(rssItem.getAuthorOrCreator().get(0).getEmailOrText()); // only take the first creator
//		data.setBody(rssItem.getDescriptionOrSummaryText());
//		
//		return data;
//		
//	}
//	
//	
//	public Data getKeywordItem(String keyword) throws ParseException {
//		try {
//			
//			/* check cache file */
//			File cachedRSSFile = new File(getSocialService().getConfiguration().getRSSCacheLocation() + "technorati.rss.key."+ keyword.toLowerCase() + ".xml");
//			
//			if(!this.checkRSSCachedFileValid(cachedRSSFile)) {
//			
//				/* set up a new RSS reader from source */
//				this.reader = new FeedReader(new HttpURL(TechnoratiService.TECHNORATI_URL + "tag/" + keyword));
//				this.channel = this.reader.readChannel();   
//				
//				/* write file to cache */
//				this.createRSSCachedFile(cachedRSSFile, this.channel);
//			
//			} else {
//				
//				/* set up a new RSS reader from file */
//				this.reader = new FeedReader(cachedRSSFile);
//				this.channel = this.reader.readChannel();   
//				
//			}
//				
//			/* get a list of RSS items and then shuffle them up for a random peek! */
//			List<ItemEntry> items = this.channel.getItems();
//			
//			/* shuffle it up for some randomness */
//			Collections.shuffle(items);
//			
//			return this.compileTechnoratiData(items.get(this.random.nextInt(items.size()-1)));
//			
//			
//		} catch (Exception exp) {
//			throw new ParseException("unable to parse technorati RSS data: " + exp.getMessage());
//			
//		}
//		
//	}
//	
//
//	public Data getKeywordItem(String[] keywords) throws ParseException {
//
//		List<Data> data = this.getKeywordItems(keywords, 20);
//		return data.get(this.random.nextInt(data.size()-1));
//	}
//
//	public List<Data> getLatestUserItems(int userId, int limit) throws ParseException {
//		/* not implemented */
//		return null;
//	}
//
//	public List<Data> getLatestUserItems(String userId, int limit) throws ParseException {
//		/* not implemented */
//		return null;
//	}
//
//	public Data getLatestUserItem(int userId) throws ParseException {
//		/* not implemented */
//		return null;
//	}
//
//	public Data getLatestUserItem(String userId) throws ParseException {
//		
//		/* not implemented */
//		return null;
//	}
//
//	public List<Data> getItems(int limit) throws ParseException {
//		
//		try {
//			
//			List<Data> extractedData = new ArrayList<Data>();
//			
//			/* check cache file */
//			File cachedRSSFile = new File(getSocialService().getConfiguration().getRSSCacheLocation() + "technorati.rss.recent.xml");
//			
//			if(!this.checkRSSCachedFileValid(cachedRSSFile)) {
//			
//				/* set up a new RSS reader from source */
//				this.reader = new FeedReader(new HttpURL(TechnoratiService.TECHNORATI_URL + "frontpage"));
//				this.channel = this.reader.readChannel();   
//				
//				/* write file to cache */
//				this.createRSSCachedFile(cachedRSSFile, this.channel);
//			
//			} else {
//				
//				/* set up a new RSS reader from file */
//				this.reader = new FeedReader(cachedRSSFile);
//				this.channel = this.reader.readChannel();   
//				
//			}
//			
//		
//			/* get a list of RSS items and then shuffle them up for a random peek! */
//			List<ItemEntry> items = this.channel.getItems();
//			
//			for(ItemEntry item : items) {
//			
//				/* compile item */
//				extractedData.add(this.compileTechnoratiData(item));
//		
//			}
//			
//			/* shuffle it up for some randomness */
//			Collections.shuffle(extractedData);
//			
//			List<Data> compactedData = new ArrayList<Data>();
//			
//			/* now trim it up */
//			if(limit > extractedData.size()) limit = extractedData.size(); // make sure we don't go out of bounds!
//			for(int x = 0; x < limit; x++) {
//				compactedData.add(extractedData.get(x));
//			}
//			
//			return compactedData;
//			
//			
//		} catch (Exception exp) {
//			
//			throw new ParseException("unable to parse technorati RSS data: " + exp.getMessage());
//			
//		}
//		
//		
//	}
//
//	public List<Data> getKeywordItems(String keyword, int limit) throws ParseException {
//		
//
//		try {
//			
//			List<Data> extractedData = new ArrayList<Data>();
//			
//			/* check cache file */
//			File cachedRSSFile = new File(getSocialService().getConfiguration().getRSSCacheLocation() + "technorati.rss.key."+ keyword.toLowerCase() + ".xml");
//			
//			if(!this.checkRSSCachedFileValid(cachedRSSFile)) {
//			
//				/* set up a new RSS reader from source */
//				this.reader = new FeedReader(new HttpURL(TechnoratiService.TECHNORATI_URL + "tag/" + keyword));
//				this.channel = this.reader.readChannel();   
//				
//				/* write file to cache */
//				this.createRSSCachedFile(cachedRSSFile, this.channel);
//			
//			} else {
//				
//				/* set up a new RSS reader from file */
//				this.reader = new FeedReader(cachedRSSFile);
//				this.channel = this.reader.readChannel();   
//				
//			}
//			
//			
//			/* get a list of RSS items and then shuffle them up for a random peek! */
//			List<ItemEntry> items = this.channel.getItems();
//			
//			for(ItemEntry item : items) {
//			
//				/* compile item */
//				extractedData.add(this.compileTechnoratiData(item));
//		
//			}
//			
//			/* shuffle it up for some randomness */
//			Collections.shuffle(extractedData);
//			
//			List<Data> compactedData = new ArrayList<Data>();
//			
//			/* now trim it up */
//			if(limit > extractedData.size()) limit = extractedData.size(); // make sure we don't go out of bounds!
//			for(int x = 0; x < limit; x++) {
//				compactedData.add(extractedData.get(x));
//			}
//			
//			return compactedData;
//			
//			
//		} catch (Exception exp) {
//			throw new ParseException("unable to parse technorati RSS data: " + exp.getMessage());
//			
//		}
//		
//		
//	}
//
//	public List<Data> getKeywordItems(String[] keywords, int limit) throws ParseException {
//		
//		try {
//			
//			/* first we need to work out the ratio of keywords to limited returns */
//			int ratio = limit  / keywords.length;
//			
//			List<Data> extractedData = new ArrayList<Data>();
//			
//			for(int x = 0; x < keywords.length; x++) {
//			
//				/* check cache file */
//				File cachedRSSFile = new File(getSocialService().getConfiguration().getRSSCacheLocation() + "technorati.rss.key."+ keywords[x] + ".xml");
//				
//				if(!this.checkRSSCachedFileValid(cachedRSSFile)) {
//				
//					/* set up a new RSS reader from source */
//					this.reader = new FeedReader(new HttpURL(TechnoratiService.TECHNORATI_URL + "tag/" + keywords[x]));
//					this.channel = this.reader.readChannel();   
//					
//					/* write file to cache */
//					this.createRSSCachedFile(cachedRSSFile, this.channel);
//				
//				} else {
//					
//					/* set up a new RSS reader from file */
//					this.reader = new FeedReader(cachedRSSFile);
//					this.channel = this.reader.readChannel();   
//					
//				}
//				
//				/* get a list of RSS items and then shuffle them up for a random peek! */
//				List<ItemEntry> items = this.channel.getItems();
//				
//				/* shuffle them all up for good measure! */
//				Collections.shuffle(items);
//				
//				int counter = 0;
//				for(ItemEntry item : items) {
//					if(counter < ratio) {
//						/* compile item */
//						extractedData.add(this.compileTechnoratiData(item));
//					} else {
//						break;
//					}
//					counter++;
//				}
//			}
//			
//			Collections.shuffle(extractedData);
//			
//			List<Data> compactedData = new ArrayList<Data>();
//			
//			/* now trim it up */
//			if(limit > extractedData.size()) limit = extractedData.size(); // make sure we don't go out of bounds!
//			for(int x = 0; x < limit; x++) {
//				compactedData.add(extractedData.get(x));
//			}
//			
//			return compactedData;
//			
//			
//		} catch (Exception exp) {
//			
//			throw new ParseException("unable to parse technorati RSS data: " + exp.getMessage());
//			
//		}
//		
//	}
//
//	public List<Data> getUserItems(int userId, int limit) throws ParseException {
//		/* not implemented */
//		return null;
//	}
//
//	public List<Data> getUserItems(String userId, int limit) throws ParseException {
//		/* not implemented */
//		return null;
//	}
//
//	public Data getItem() throws ParseException {
//		
//		try {
//			
//			/* check cache file */
//			File cachedRSSFile = new File(getSocialService().getConfiguration().getRSSCacheLocation() + "technorati.rss.recent.xml");
//			
//			if(!this.checkRSSCachedFileValid(cachedRSSFile)) {
//			
//				/* set up a new RSS reader from source */
//				this.reader = new FeedReader(new HttpURL(TechnoratiService.TECHNORATI_URL + "frontpage"));
//				this.channel = this.reader.readChannel();   
//				
//				/* write file to cache */
//				this.createRSSCachedFile(cachedRSSFile, this.channel);
//			
//			} else {
//				
//				/* set up a new RSS reader from file */
//				this.reader = new FeedReader(cachedRSSFile);
//				this.channel = this.reader.readChannel();   
//				
//			}
//		
//			/* get a list of RSS items and then shuffle them up for a random peek! */
//			List<ItemEntry> items = this.channel.getItems();
//			
//			/* shuffle up the items */
//			Collections.shuffle(items);
//			
//			/* get a random item back from the shuffled list */
//			ItemEntry rssItem = items.get(this.random.nextInt(items.size()-1));
//			
//			return this.compileTechnoratiData(rssItem);
//			
//		} catch (Exception exp) {
//			
//			throw new ParseException("unable to parse technorati RSS data: " + exp.getMessage());
//			
//		}
//		
//	}
//
//	public Data getUserItem(int userId) throws ParseException {
//		/* not implemented */
//		return null;
//	}
//
//	public Data getUserItem(String userId) throws ParseException {
//		/* not implemented */
//		return null;
//	}
//
//	public void setUpParser() {
//		
//		this.random = new Random();
//		this.technoratiDateFormat = new SimpleDateFormat("EEE',' dd MMM yyyy kk:mm:ss z");
//	
//	}
//
//}

	/*
	private String doCacheInspection(String suffix){

		File file = new File(getSocialService().getConfiguration().getRSSCacheLocation() + xmlKey + suffix + ".xml");

		if(file.exists()) {

			long time = System.currentTimeMillis();

			if(file.lastModified() > (time - expireLengthMillis)) 
				return file.getAbsolutePath();
			else 
				return null;
		} else {
			return null;
		}

	}
	*/
