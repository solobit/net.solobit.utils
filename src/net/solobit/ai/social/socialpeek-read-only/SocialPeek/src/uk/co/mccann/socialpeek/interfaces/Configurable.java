package uk.co.mccann.socialpeek.interfaces;

import java.util.Calendar;
import java.util.List;
import uk.co.mccann.socialpeek.model.SocialService;

/**
 * Configurable
 * Configuation classes implementation
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
public interface Configurable {

	public void setFeedType(int returnType);
	
	public int getFeedType();
	
	public void registerService(SocialService service);
	
	public void setHistoricalStartPoint(Calendar cal);
	
	public Calendar getHistoricalStartPoint();
	
	public void setHistoricalEndPoint(Calendar cal);
	
	public Calendar getHistoricalEndPoint();
	
	public String getRSSCacheLocation();
	
	public void setRSSCacheLocation(String rssCacheLocation);

	public List<SocialService> getRegisteredServices();
	
}
