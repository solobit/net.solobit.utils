package uk.co.mccann.socialpeek.service;

import java.util.List;

import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.interfaces.Parser;
import uk.co.mccann.socialpeek.parser.WeFeelFineParser;

/**
 * <b>WeFeelFineService</b><br/>
 * Generate data from WeFeelFine (www.wefeelfine.org).
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
public class WeFeelFineService extends AbstractSocialService {
	
	public static String API_URL = "http://api.wefeelfine.org:8080/";
	
	public WeFeelFineService() {
		
		this(new WeFeelFineParser());
		this.parser.setSocialService(this);
		this.parser.setUpParser();
		
		/* this service does not require authentication */
		this.setRequireAuthentication(false);
	}

	public WeFeelFineService(Parser parser) {
		super(parser);
	}
	
	/* overridden methods */
	@Override
	public String getUserPeek(int userId) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
	
	}
	
	@Override
	public String getUserPeek(String userId) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
		
	}

	@Override
	public String getUserPeek(int userId, int limit) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
		
	}

	@Override
	public String getUserPeek(String userId, int limit) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
		
	}
	
	@Override
	public Data getRawDataUserPeek(int userId) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
		
	}

	@Override
	public Data getRawDataUserPeek(String userId) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
	}

	@Override
	public List<Data> getRawDataUserPeek(int userId, int limit) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
	}

	@Override
	public List<Data> getRawDataUserPeek(String userId, int limit) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
	}

	@Override
	public String getLatestUserPeek(String userId) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
		
	}

	@Override
	public String getLatestUserPeek(int userId, int limit) throws SocialPeekException {
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
		
	}

	@Override
	public String getLatestUserPeek(String userId, int limit) throws SocialPeekException {
		
		/* not implemented */
		throw new SocialPeekException("this method is not implemented wefeelfine service!");
	}
	
	
	
}
