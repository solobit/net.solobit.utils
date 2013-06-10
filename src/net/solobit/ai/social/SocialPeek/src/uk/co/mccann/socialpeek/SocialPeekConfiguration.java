package uk.co.mccann.socialpeek;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import uk.co.mccann.socialpeek.generator.GeneratorFactory;
import uk.co.mccann.socialpeek.interfaces.Configurable;
import uk.co.mccann.socialpeek.model.SocialService;

/**
 * SocialPeekConfiguration
 * configuration required for each instance of SocialPeek, contains registered services and return data type
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

public class SocialPeekConfiguration implements Configurable {
	
	private int feedType;
	private List<SocialService> services;
	private GeneratorFactory generatorFactory;
	private Calendar historicalStartPoint;
	private Calendar historicalEndPoint;
	private String rssCacheLocation;
	
	/**
     * Create a new instance of SocialPeek with a specific configuration
     */
	public SocialPeekConfiguration() {
		
		this.services = new ArrayList<SocialService>();
		
		/* create a default historical start of a year ago and ends today
		 * the user can override this by setting custom dates
		 */
		this.setHistoricalEndPoint(Calendar.getInstance());
		Calendar startPoint = Calendar.getInstance();
		startPoint.add(Calendar.YEAR, -1);
		this.setHistoricalStartPoint(startPoint);
		this.rssCacheLocation = "rssdata/"; // default location.
		
	}
	
	/**
     * Get feed type to generate, XML, JSON or RSS
     *
     * @return integer defined in SocialPeek class, mapped to SocialPeek.RETURN_RSS, SocialPeek.RETURN_XML and SocialPeek.RETURN_JSON 
     * @see SocialPeek
     */
	public int getFeedType() {
		return this.getFeedType();
	}

	/**
     * Set the feed type to generate XML, JSON or RSS
     *
     * @see SocialPeek
     */
	public void setFeedType(int feedType) {
		this.feedType = feedType;
	}

	/**
     * Set the feed type to generate XML, JSON or RSS
     *
     * @see SocialPeek
     * @param service SocialService to register
     */
	public void registerService(SocialService service) {
		this.generatorFactory = new GeneratorFactory(feedType);
		service.registerGeneratorFactory(this.generatorFactory);
		service.registerConfiguration(this);
		this.services.add(service);
		
	}
	
	/**
     * Set the social service you want the generator to process, this can be any of the following:
     * <p>
     * <b>TwitterService.class</b> : Get information from Twitter
     * <b>DeliciousService.class</b> : Get information from del.ico.us
     * <b>DiggService.class</b> : Get information from Digg
     * <b>TechnoratiService.class</b> : Get information from Technorati
     * <b>WeFeelFineService.class</b> : Get information from We Feel Fine
     * <b>RSSService.class</b> : Get information from a user defined RSS feed.
     * <b>RandomService.class</b> : Get information from a random service
     * 
     * @param serviceType the class of the service
     */
	public void setServiceType(Class<SocialService> serviceType) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Returns a list of registered services in this configuration
     *
     * @return List of services registered
     * @see SocialService
     */
	public List<SocialService> getRegisteredServices() {
		
		return this.services;
	}
	
	/**
     * Returns a Calendar object of the maximum 'up to' date you want information returned (if available from service)
     *
     * @return Calendar object of historial end point
     * @see Calendar
     */
	public Calendar getHistoricalEndPoint() {
		return this.historicalEndPoint;
	}
	
	/**
     * Returns a Calendar object of the 'from' date you want information returned (if available from service)
     *
     * @return Calendar object of historial start point
     * @see Calendar
     */
	public Calendar getHistoricalStartPoint() {
		return this.historicalStartPoint;
	}
	
	/**
     * Set the 'up to' date you want information returned (if available from service)
     *
     * @param cal Calendar object of historial end point
     * @see Calendar
     */
	public void setHistoricalEndPoint(Calendar cal) {
		this.historicalEndPoint = cal;
		
	}
	
	/**
     * Set the 'up to' date you want information returned (if available from service)
     *
     * @param cal Calendar object of historial end point
     * @see Calendar
     */
	public void setHistoricalStartPoint(Calendar cal) {
		this.historicalStartPoint = cal;
		
	}

	/**
     * Set the location of the cached RSS files
     *
     * @return the cached file location;
     */
	public String getRSSCacheLocation() {
		return this.rssCacheLocation;
	}
	
	/**
     * get the location of the cached RSS files
     *
     * @param rssCacheLocation
     */
	public void setRSSCacheLocation(String rssCacheLocation) {
		this.rssCacheLocation = rssCacheLocation;
	}

}
