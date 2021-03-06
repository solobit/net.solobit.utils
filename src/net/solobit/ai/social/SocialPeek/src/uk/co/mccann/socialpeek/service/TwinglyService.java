package uk.co.mccann.socialpeek.service;

import uk.co.mccann.socialpeek.interfaces.Parser;
import uk.co.mccann.socialpeek.parser.TwinglyParser;

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
public class TwinglyService extends AbstractSocialService {

	
	/**
     *  Set up service and configure parser.
     */
	public TwinglyService() {
		
		this(new TwinglyParser());
		this.parser.setSocialService(this);
		this.parser.setUpParser();
		
		/* this service does not require authentication */
		this.setRequireAuthentication(false);
	
	}
	
	public TwinglyService(Parser parser) {
		super(parser);
	}
	
	/* override anything that needs it */
	
}

