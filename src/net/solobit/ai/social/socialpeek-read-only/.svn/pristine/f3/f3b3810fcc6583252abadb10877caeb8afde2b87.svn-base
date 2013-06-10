package uk.co.mccann.socialpeek.rss;

import java.net.MalformedURLException;
import java.net.URL;

import com.sun.cnpi.rss.elements.Channel;
import com.sun.cnpi.rss.elements.Rss;
import com.sun.cnpi.rss.parser.RssParser;
import com.sun.cnpi.rss.parser.RssParserFactory;

/**
 * <b>RSSReader</b><br/>
 * Parses an RSS Feed and writes to file
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
* otherwise stated. It is released as
* open-source under the Creative Commons NC-SA license. See
* <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
* for license details. This code comes with no warranty or support.
 *
 * @author Lewis Taylor <lewis.taylor@europe.mccann.com>
 */
public class RSSReader {
	
	private URL url;
	
	public RSSReader(){
		super();
		url = null;
	}
	
	/**
	 * Sets the URL of the RSSReader to be
	 * used when parsing later
	 * 
	 * @param url - url to fetch RSS feed
	 */
	public void setURL(String url){
		try {
			setURL(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the URL of the RSSReader to be
	 * used when parsing later
	 * 
	 * @param url - url to fetch RSS feed
	 */
	public void setURL(URL url){
		this.url = url;
	}
	
	/**
	 * Fetches a feed from a stored location and parses it
	 * before returning the RSS Feed as an object
	 * 
	 * @return a Channel onject containing the RSS Data
	 * @throws Exception
	 */
	public Channel parseFeed() throws Exception {

	    
	    if (url==null)
	    	return new Channel();
		
		System.out.println();
		System.out.println();
		System.out.println("URL to Query: " + url);
		System.out.println();
		System.out.println();
	

	    RssParser parser = RssParserFactory.createDefault();
	    	
	    Rss rss = parser.parse(url);
	 
	    return rss.getChannel();
	}

}
