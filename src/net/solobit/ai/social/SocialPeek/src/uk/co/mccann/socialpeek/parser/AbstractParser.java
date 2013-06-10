package uk.co.mccann.socialpeek.parser;

import java.util.Random;

import org.apache.log4j.Logger;

import uk.co.mccann.socialpeek.interfaces.Parser;
import uk.co.mccann.socialpeek.model.SocialService;

/**
* <b>AbstractParser</b><br/>
* All parsers for services need to extend this class
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
public abstract class AbstractParser implements Parser {
	
	protected SocialService service;
	protected Random random;
	protected Logger logger;
	

	public void setUpParser() {
		
		this.random = new Random();
		
	}

	public AbstractParser() {}
	
	/**
     * Called by subclasses to set the SocialService for the parser
   	 * @param service
     * @see SocialService
     */
	public AbstractParser(SocialService service) {
	
		this.service = service;
	}
	
	/**
     * Set the SocialService for the parser
   	 * @param service
     * @see SocialService
     */
	public void setSocialService(SocialService service) {
		
		this.service = service;
	}
	
	/**
     * Get the SocialService for the parser
   	 * @return the SocialService for the parser
     * @see SocialService
   	 */
	protected SocialService getSocialService() {
		
		return this.service;
	}
	

}
