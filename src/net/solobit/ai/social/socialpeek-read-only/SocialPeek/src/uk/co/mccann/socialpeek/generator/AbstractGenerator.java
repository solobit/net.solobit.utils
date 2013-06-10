package uk.co.mccann.socialpeek.generator;

import java.text.SimpleDateFormat;
import uk.co.mccann.socialpeek.interfaces.Generator;

/**
 * AbstractGenerator
 * All generators must subclass this class, to add a new generator, simply subclass and implement interface methods.
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

public abstract class AbstractGenerator implements Generator {
	
	protected SimpleDateFormat sdf;
	
	/**
     * Default constructor 
   	 * 
   	 * Set up the date format for all generators, using standard format time and date.
   	 * 
     * @see SimpleDateFormat
     */
	public AbstractGenerator() {
	
		this.sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	
	}
	
}
