package uk.co.mccann.socialpeek.service;

import uk.co.mccann.socialpeek.interfaces.Parser;
import uk.co.mccann.socialpeek.parser.LastFMParser;


/**
 * <b>LastFMService</b><br/>
 * All services should subclass this class.
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
public class LastFMService extends AbstractSocialService {

	public static String API_URL = "http://ws.audioscrobbler.com/1.0/";	
	public static String UK_LOCATION = "United+Kingdom";
	public static String US_LOCATION = "United+States";
	
	public static String CURRENT_CHART = API_URL + "place/" + UK_LOCATION + "/toptracks.xml";
	public static String ARTIST_API = API_URL + "artist/";
	public static String USER_API = API_URL + "user/";
	public static String TAG_API = API_URL + "tag/";
	
	public LastFMService() {
		
		this(new LastFMParser());
		this.parser.setSocialService(this);
		this.parser.setUpParser();
		
		/* this service does not require authentication */
		this.setRequireAuthentication(false);
	}

	public LastFMService(Parser parser) {
		super(parser);
	}
	
}
