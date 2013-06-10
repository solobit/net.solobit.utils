package uk.co.mccann.socialpeek;

import java.util.Random;

import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.Configurable;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;
import uk.co.mccann.socialpeek.model.SocialService;


/**
 * SocialPeekFactory
 * not to be instantiated directly - can do if you want though, I don't care.
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

public class SocialPeekFactory implements PeekFactory {
	
	private Configurable config;
	
	/**
     * Returns's the correct SocialService from the configuration's list of registered services depending on class type.
     *
     * @param config the configuration objects for this peeking factory
     * @see SocialService
     */
	public SocialPeekFactory(Configurable config) {
		this.config = config;
	}
	
	/**
     * Returns the correct SocialService from the configuration's list of registered services depending on class type.
     *
     * @return SocialService that matches class argument
     * @param peeker of SocialService you want returned. 
     * @see SocialService
     * @see SocialPeekException
     * @throws SocialPeekException
     */
	public SocialService getPeeker(Class peeker) throws SocialPeekException {
		
		for(SocialService service : this.config.getRegisteredServices()) {
			if(peeker.equals(service.getClass())) {
				return service;
			}
			
		}
		
		throw new SocialPeekException("no such service registered: " + peeker.getName());
		
	}
	
	/**
     * get a random peeker for a random service!
   	 * 
     * @throws SocialPeekException
     * @see uk.co.mccann.socialpeek.service.AbstractSocialService
     * @return random service that you want to peek into
     */
	public SocialService getPeeker() throws SocialPeekException {
		
		Random random = new Random();
		SocialService service = this.config.getRegisteredServices().get(random.nextInt(this.config.getRegisteredServices().size()));
		return service;
		
	}
	
	
}
