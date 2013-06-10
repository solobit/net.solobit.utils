package uk.co.mccann.socialpeek.generator;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.interfaces.Generator;


/**
 * GeneratorFactory
 * Instantiate correct data generator depending on feed type. Should not be instantiated directly, however again - so do if you want.
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

public class GeneratorFactory {
	
	private int feedType;
	
	/**
     * Constructor sets the feed type to create a generator for.
     *
     * @param feedType int defined in SocialPeek class, mapped to SocialPeek.RETURN_RSS, SocialPeek.RETURN_XML and SocialPeek.RETURN_JSON 
     * @see uk.co.mccann.socialpeek#RETURN_JSON
     * @see uk.co.mccann.socialpeek#RETURN_XML
     * @see uk.co.mccann.socialpeek#RETURN_RSS
     */
	public GeneratorFactory(int feedType) {
		
		this.feedType = feedType;
	
	}
	
	/**
     * Get a generator for the user defined feed type
     *
     * @return depending on what feed type the user has requested in the configuration, returns the appropriate generator 
     * @see XMLGenerator
     * @see JSONGenerator
     * @see RSSGenerator
     */
	public Generator getGenerator() {
		
		if(this.feedType == SocialPeek.RETURN_XML) {
			
			return new XMLGenerator();
			
		} else if(this.feedType == SocialPeek.RETURN_JSON) {
			
			return new JSONGenerator();
		
		} else if(this.feedType == SocialPeek.RETURN_RSS) {
			
			return new RSSGenerator();	
			
		} else {
			
			return null;
		}
		
	}
	
	
	
}
