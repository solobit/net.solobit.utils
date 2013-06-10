package uk.co.mccann.socialpeek;


import uk.co.mccann.socialpeek.interfaces.Configurable;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;

/**
 * SocialPeek
 * generate realtime data from the internet from multiple UGC sources.
 * <p>
 * Example usage:
	<code><pre>
	// create a twitter service (you can create your own service, or use the predefined services / rss feeds
	SocialService service = new TwitterService();
	service.setUsername("username");
	service.setPassword("password");
	
	// create a configuration	
	SocialPeekConfiguration config = new SocialPeekConfiguration();
	
	// what data to you want back? supports RSS, JSON or vanilla XML
	config.setFeedType(SocialPeek.RETURN_RSS);
	config.registerService(service);
		
	// create a new SocialPeek Engine
	SocialPeek socialPeek = new SocialPeek(config);
	
	// get peeker (required to view data)
	SocialPeekFactory peekFactory = socialPeek.getPeekingFactory();
	
	// print out five random posts from service	
	System.out.println(peekFactory.getPeeker(TwitterService.class).getRandomPeek(5));
	</pre></code>
 
 * <p>
 * See http://mccann.co.uk/socialpeek for more information.
 * <p>
 * Notes:
 * <ul>
 * <li> Services can be created by subclassing AbstractSocialService
 * <li> Each service requires a parser (this is the implementation of each service)
 * you can create your own parsers by subclassing AbstractPaser
 * <li> All peeking methods (from the generator) will throw a SocialPeekException
 * </ul>
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 *
 *
 * @author Dave Shanley <david.shanley@europe.mccann.com>
 */
public class SocialPeek {
	
	public final static int RETURN_XML = 1;
	public final static int RETURN_JSON = 2;
	public final static int RETURN_RSS = 3;
	public final static String AUTHOR = "Dave Shanley";
	public final static String AUTHOR_EMAIL = "david.shanley@europe.mccann.com";
	public final static String VERSION = "1.2.2";
	public final static String APPLICATION_NAME = "SocialPeek";
	public final static String APPLICATION_INFO = APPLICATION_NAME + VERSION + ", take a look at the internet - right now!";
	public final static String APPLICATION_LINK = "http://socialpeek.com";
	public final static String APPLICATION_LOGO ="http://showreel.mccanndev.co.uk/socialpeek-logo.gif";
	public final static String APPLICATION_LOGO_WIDTH ="120";
	public final static String APPLICATION_LOGO_HEIGHT ="64";
	public static boolean logging = false;
	
	private Configurable config;
	
	/**
     * Create a new instance of SocialPeek with a specific configuration
     *
     * @param config configuration file with registered services and feed type
     * @see Configurable
     */
	public SocialPeek(Configurable config) {
		this.config = config;
	}
	
	/**
     * Create a new instance of SocialPeek with a specific configuration
     *
     * @return peeking factory that generates content from specific services or RSS feeds registered in the configuration for this instance
     */
	public PeekFactory getPeekingFactory() {
		
		return new SocialPeekFactory(this.config);
		
	}

	/**
	 * Set Logging;
	 * @param logging
	 */public static void setLogging(boolean logging) {
		SocialPeek.logging = logging;
	}
	

}
