package uk.co.mccann.socialpeek.interfaces;

import java.util.List;

import uk.co.mccann.socialpeek.exceptions.SocialPeekException;

/**
 * Peekable
 * Services should implement this interface.
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
public interface Peekable {
	
	/**
     * Get a single random peek
   	 * 
     * @throws SocialPeekException
     * @return formatted string data produced by generator
     */
	public String getRandomPeek() throws SocialPeekException;
	
	/**
     * Get multiple peeks
     * 
   	 * @param limit limit the number of items returned.
   	 * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getRandomPeek(int limit) throws SocialPeekException;
	
	/**
     * Get a single random peek from a service user
   	 * 
   	 * @param userId the integer id of the service user (if implemented)
   	 * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getUserPeek(int userId)  throws SocialPeekException;
	
	/**
     * Get a single random peek from a service user
   	 * 
   	 * @param userId the username/user account name of the service user (if implemented)
     * @throws SocialPeekException
     */
	public String getUserPeek(String userId) throws SocialPeekException;
	
	/**
     * Get multiple items from a service user
   	 * 
   	 * @param userId the user id of the service user (if implemented)
   	 * @param limit limit the number of items returned
   	 * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getUserPeek(int userId, int limit) throws SocialPeekException;
	
	/**
     * Get multiple items from a service user
   	 * 
   	 * @param userId the user id of the service user (if implemented)
   	 * @param limit limit the number of items returned
   	 * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getUserPeek(String userId, int limit)  throws SocialPeekException;
	
	/**
     * Get the latest single posted item from a service user
   	 * 
   	 * @param userId the user id of the service user (if implemented)
   	 * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getLatestUserPeek(String userId) throws SocialPeekException;
	
	/**
     * Get multiple latest posted items from a service user
   	 * 
   	 * @param userId the user id of the service user (if implemented)
   	 * @return formatted string data produced by generator
   	 * @param limit limit the number of items returned
     * @throws SocialPeekException
     */
	public String getLatestUserPeek(int userId, int limit) throws SocialPeekException;
	
	/**
     * Get multiple latest posted items from a service user
   	 * 
   	 * @param userId the user id of the service user (if implemented)
   	 * @return formatted string data produced by generator
   	 * @param limit limit the number of items returned
     * @throws SocialPeekException
     */
	public String getLatestUserPeek(String userId, int limit)  throws SocialPeekException;
	
	
	/**
     * Get a single peek for a keyword or tag
   	 * 
   	 * @param tag the tag or keyword you want to peek into (if supported)
     * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getRandomPeekUsingTag(String tag) throws SocialPeekException;
	
	
	/**
     * Get a single peek for multiple keywords or tag
   	 * 
   	 * @param tags the String array of tags or keywords you want to peek into (if supported)
     * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getRandomPeekUsingTags(String[] tags) throws SocialPeekException;
	
	
	/**
     * Get multiple peeks for a keyword or tag
   	 * 
     * @param tag the tag or keyword you want to peek into (if supported)
     * @param limit limit the returned results 
     * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getMultiplePeekUsingTag(String tag, int limit) throws SocialPeekException;
	
	
	/**
     * Get multiple peeks for multiple keywords or tags
   	 * 
     * @param tags String array of tags or keywords you want to use (if supported)
     * @param limit limit the number of returned results
     * @return formatted string data produced by generator
     * @throws SocialPeekException
     */
	public String getMultiplePeekUsingTags(String[] tags, int limit) throws SocialPeekException;
	
	
	/**
     * Get a Data Object back for a single peek for a keyword or tag
   	 * 
   	 * @param tag the tag or keyword you want to peek into (if supported)
     * @return plain old POJO Data object
     * @throws SocialPeekException
     */
	public Data getRawRandomPeekUsingTag(String tag) throws SocialPeekException;
	
	
	/**
     * Get a Data object back for a single peek for multiple keywords or tag
   	 * 
   	 * @param tags the String array of tags or keywords you want to peek into (if supported)
     * @return plain old POJO Data object
     * @throws SocialPeekException
     */
	public Data getRawRandomPeekUsingTags(String[] tags) throws SocialPeekException;
	
	
	/**
     * Get a List filled will Data objects for multiple peeks for a keyword or tag
   	 * 
     * @param tag the tag or keyword you want to peek into (if supported)
     * @param limit limit the returned results 
     * @return plain old List filled with Data objects
     * @throws SocialPeekException
     */
	public List<Data> getRawMultiplePeekUsingTag(String tag, int limit) throws SocialPeekException;
	
	
	/**
     * Get a List filled with Data Objects for multiple peeks for multiple keywords or tags
   	 * 
     * @param tags String array of tags or keywords you want to use (if supported)
     * @param limit limit the number of returned results
     * @return plain old list filled with Data Objects
     * @throws SocialPeekException
     */
	public List<Data> getRawMultiplePeekUsingTags(String[] tags, int limit) throws SocialPeekException;
	
	
	
	/**
     * Get a single random peek without running the result through a generator
   	 * 
     * @return POJO Data object for your own manipulation
     * @throws SocialPeekException
     */
	public Data getRawDataRandomPeek() throws SocialPeekException;
	
	
	/**
     * Get multiple peeks without running the result through a generator
   	 * 
     * @return List filled with POJO Data objects for your own manipulation
     * @throws SocialPeekException
     */
	public List<Data> getRawDataRandomPeek(int limit) throws SocialPeekException;
	
	/**
     * Get single peek from a service user without running the result through a generator
   	 * 
     * @param userId the user id of the service user (if implemented)
     * @return POJO Data object for your own manipulation
     * @throws SocialPeekException
     */
	public Data getRawDataUserPeek(int userId)  throws SocialPeekException;
	
	/**
     * Get single peek from a service user without running the result through a generator
   	 * 
     * @param userId the user id of the service user (if implemented)
     * @return POJO Data object for your own manipulation
     * @throws SocialPeekException
     */
	public Data getRawDataUserPeek(String userId) throws SocialPeekException;
	
	/**
     * Get multiple peeks from a service user without running the result through a generator
   	 * 
     * @param userId the user id of the service user (if implemented)
     * @param limit limit the number of items returned
     * @return List filled with POJO Data objects for your own manipulation
     * @throws SocialPeekException
     */
	public List<Data> getRawDataUserPeek(int userId, int limit) throws SocialPeekException;
	
	/**
     * Get multiple peeks from a service user without running the result through a generator
   	 * 
     * @param userId the user id of the service user (if implemented)
     * @param limit limit the number of items returned
     * @return List filled with POJO Data objects for your own manipulation
     * @throws SocialPeekException
     */
	public List<Data> getRawDataUserPeek(String userId, int limit)  throws SocialPeekException;

}
