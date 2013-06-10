package uk.co.mccann.socialpeek.service;

import uk.co.mccann.socialpeek.interfaces.Parser;
import uk.co.mccann.socialpeek.parser.DiggParser;

/**
 * <b>DeliciousService</b><br/>
 * All services should subclass this class.
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
* otherwise stated. It is released as
* open-source under the Creative Commons NC-SA license. See
* <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
* for license details. This code comes with no warranty or support.
 *
 * @author Lewis Taylor <lewis.taylory@europe.mccann.com>
 */
public class DiggService extends AbstractSocialService {

	public static final String ALL_URL ="http://services.digg.com/stories/diggs?type=rss&appkey=http%3A%2F%2Fperiscope.mccann.co.uk";
//	public static final String USER_URL = "http://services.digg.com/user/USER/diggs?type=rss&appkey=http%3A%2F%2Fperiscope.mccann.co.uk";
	public static final String KEYWORD_URL = "http://digg.com/rss_search?search=KEYWORD&area=all&type=all&section=all";
	
	public static final String LIMIT = "&count=";
	
	
	/**
     *  Set up service and configure parser.
     */
	public DiggService() {
		
		this(new DiggParser());
		this.parser.setSocialService(this);
		this.parser.setUpParser();
		
		/* this service does not require authentication */
		this.setRequireAuthentication(false);
	
	}
	
	public DiggService(Parser parser) {
		super(parser);
	}
	
	/* override anything that needs it */
	
}
