package uk.co.mccann.socialpeek.interfaces;

import java.util.Calendar;

/**
 * <b>Data</b><br/>
 * Interface for PeekData objects.
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
public interface Data {
	
	/**
     *  Set the headline
     */
	public void setHeadline(String headline);
	
	/**
     *  Set the body or description information
     */
	public void setBody(String body);
	
	/**
     *  Set the date the item was created or posted
     */
	public void setDate(Calendar date);
	
	/**
     *  Set the http URL of the source of the information, or the link it refers to.
     */
	public void setLink(String link);
	
	/**
     *  Set the name or username of the user who originally submitted the information
     */
	public void setUser(String user);
	
	/**
     *  Set URL of image related to data object - user photo, flickr image etc..
     */
	public void setThumbnail(String photo);
	
	/**
     *  Set the location of the user / place of the post
     */ 
	public void setLocation(String location);
	
	/**
     *  Get the headline
     */
	public String getHeadline();
	
	/**
     *  Get the body or description information
     */
	public String getBody();
	
	/**
     *  Get the http URL of the source of the information, or the link it refers to.
     */
	public Calendar getDate();
	
	/**
     *  Get the name or username of the user who originally submitted the information
     */
	public String getLink();
	
	/**
     *  Get the name or username of the user who originally submitted the information
     */
	public String getUser();
	
	/**
     * Get the http URL of a photo of the user who posted the link.
     */
	public String getThumbnail();
	
	/**
     *  Get the location of the user / place of the post
     */ 
	public String getLocation();
	

}
