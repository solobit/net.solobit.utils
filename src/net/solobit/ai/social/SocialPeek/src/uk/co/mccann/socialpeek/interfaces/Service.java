package uk.co.mccann.socialpeek.interfaces;

import java.util.Calendar;
import uk.co.mccann.socialpeek.generator.GeneratorFactory;

/**
 * <b>Service</b><br/>
 * Extended by SocialService
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 *
 * @author Dave Shanley <david.shanley@europe.mccann.com>
 * @see uk.co.mccann.socialpeek.model.SocialService
 */
public interface Service {
	
	/**
     * Register a generatorFactory
   	 *
   	 * @param factory the generator factory to be registered
     * @see GeneratorFactory
     */
	public void registerGeneratorFactory(GeneratorFactory factory);
	
	/**
     * Register a configuration
   	 *
   	 * @param configuration the configuration object to be registered
     * @see Configurable
     */
	public void registerConfiguration(Configurable configuration);
	
	/**
     * Get the object configuration
   	 *
   	 * @return the configuration registered for this service.
     * @see Configurable
     */
	public Configurable getConfiguration();
	
	/**
     * Get the API key for this service (if required)
     */
	public String getAPIKey();
	
	/**
     * Get the password for this service;
     */
	public String getPassword();
	
	/**
     * Get the username for this service;
     */
	public String getUsername();
	
	/**
     * Set the API Key for this service;
     */
	public void setAPIKey(String key);
	
	/**
     * Set the password for this service;
     */
	public void setPassword(String password);
	
	/**
     * Set the username for this service;
     */
	public void setUsername(String username);
	
	/**
     * Get the 'up until' calendar object for the service<br/>
     * If implemented by the service, this will return items up until the historical end point.
     * @return the calendar date
     */
	public Calendar getHistoricalEndPoint();
	
	/**
     * Get the 'start from' calendar object for the service<br/>
     * If implemented by the service, this will return items up from the historical start point
     * @return the calendar date
     */
	public Calendar getHistoricalStartPoint();
	
}
