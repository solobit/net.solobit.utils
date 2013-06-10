package uk.co.mccann.socialpeek.interfaces;

import java.util.List;

import uk.co.mccann.socialpeek.exceptions.NoResultsException;
import uk.co.mccann.socialpeek.exceptions.ParseException;
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

public interface Parser {
	
	/**
     * Sets the social service for a parser
   	 * 
     * @param	service	the SocialService to be used for the parser
     * @see SocialService
     */
	public void setSocialService(SocialService service);
	
	/**
     * Sets the parser up
   	 */
	public void setUpParser();
	
	/**
     * Get a single PeekData Object
     * 
     * @return single Data object 
     * @throws ParseException
   	 */
	public Data getItem() throws ParseException, NoResultsException;
	
	/**
     * Get a multiple PeekData Objects
     * 
     * @param limit
     * @return a List filled with Data objects 
     * @throws ParseException
   	 */
	public List<Data> getItems(int limit) throws ParseException, NoResultsException;
	
	/**
	 * Get a single PeekData object from a service user depending on a tag or a keyword
	 * 
	 * This method may not be implemented by all services, should not be called if so.
	 * 
	 * @param keyword name of the tag you want to use.
	 * @return Single Data Object
	 * @throws ParseException
	 */
	public Data getKeywordItem(String keyword)  throws ParseException, NoResultsException;

	/**
	 * Get a single PeekData object from a service user depending multiple tags or keywords
	 * 
	 * This method may not be implemented by all services, should not be called if so.
	 * 
	 * @param keywords name of the tag you want to use.
	 * @return Single Data Object
	 * @throws ParseException
	 */
	public Data getKeywordItem(String[] keywords)  throws ParseException, NoResultsException;

	/**
	 * Get multiple PeekData object from a service filtered by a tag or a keyword
	 * 
	 * This method may not be implemented by all services, should not be called if so.
	 * 
	 * @param keyword name of the tag you want to use.
	 * @param limit limits the number of results
	 * @return List of Data objects 
	 * @throws ParseException
	 */
	public List<Data> getKeywordItems(String keyword, int limit)  throws ParseException, NoResultsException;

	/**
	 * Get multiple PeekData object from a service filtered by multiple tags or keywords
	 * 
	 * This method may not be implemented by all services, should not be called if so.
	 * 
	 * @param keywords an array of the keywords you want to use.
	 * @param limit limits the number of results
	 * @return List of Data objects 
	 * @throws ParseException
	 */
	public List<Data> getKeywordItems(String[] keywords, int limit)  throws ParseException, NoResultsException;

	/**
     * Get a single PeekData object from a service user
     * 
     * This method may not be implemented by all services, should not be called if so.
     * 
     * @param userId integer value of the user's ID (if the service uses integer based id's)
     * @return Data object 
     * @throws ParseException
   	 */
	public Data getUserItem(int userId)  throws ParseException, NoResultsException;
	
	/**
     * Get a single PeekData object from a service user
     * 
     * This method may not be implemented by all services, should not be called if so.
     * 
     * @param userId String value of the user's ID (if the service uses String based id's)
     * @return Data object 
     * @throws ParseException
   	 */
	public Data getUserItem(String userId)  throws ParseException, NoResultsException;
	
	/**
	 * Get multiple PeekData objects from a service user
	 * 
	 * This method may not be implemented by all services, should not be called if so.
	 * 
	 * @param userId integer value of the user's ID (if the service uses integer based id's)
	 * @param limit limit the returned results
	 * @return Single Data Object
	 * @throws ParseException
	 */
	public List<Data> getUserItems(int userId, int limit)  throws ParseException, NoResultsException;

	/**
	 * Get multiple PeekData objects from a service user
	 * 
	 * This method may not be implemented by all services, should not be called if so.
	 * 
	 * @param userId String value of the user's ID (if the service uses String based id's)
	 * @param limit limit the returned results
	 * @return List of Data objects 
	 * @throws ParseException
	 */
	public List<Data> getUserItems(String userId, int limit)  throws ParseException, NoResultsException;

	/**
     * Get latest single PeekData object from a service user
     * 
     * This method may not be implemented by all services, should not be called if so.
     * 
     * @param userId integer value of the user's ID (if the service uses integer based id's)
     * @return Data object 
     * @throws ParseException
   	 */
	public Data getLatestUserItem(int userId)  throws ParseException, NoResultsException;
	
	/**
     * Get latest single PeekData object from a service user
     * 
     * This method may not be implemented by all services, should not be called if so.
     * 
     * @param userId String value of the user's ID (if the service uses String based id's)
     * @return Data object 
     * @throws ParseException
   	 */
	public Data getLatestUserItem(String userId)  throws ParseException, NoResultsException;	
	
	/**
     * Get latest multiple PeekData objects from a service user 
     * 
     * This method may not be implemented by all services, should not be called if so.
     * 
     * @param userId integer value of the user's ID (if the service uses integer based id's)
     * @param limit limit the returned results
     * @return List of Data objects 
     * @throws ParseException
   	 */
	public List<Data> getLatestUserItems(int userId, int limit)  throws ParseException, NoResultsException;
	
	/**
     * Get latest multiple PeekData objects from a service user
     * 
     * This method may not be implemented by all services, should not be called if so.
     * 
     * @param userId String value of the user's ID (if the service uses String based id's)
     * @param limit limit the returned results
     * @return List of Data objects 
     * @throws ParseException
   	 */
	public List<Data> getLatestUserItems(String userId, int limit)  throws ParseException, NoResultsException;
		
}
