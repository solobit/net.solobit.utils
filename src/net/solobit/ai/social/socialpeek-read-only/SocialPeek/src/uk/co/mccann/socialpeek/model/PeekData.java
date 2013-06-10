package uk.co.mccann.socialpeek.model;

import java.io.StringWriter;
import java.util.Calendar;

import uk.co.mccann.socialpeek.interfaces.Data;

/**
 * <b>PeekData</b><br/>
 * JavaBean to contain parsed and processed information from service or RSS feed.
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
public class PeekData implements Data {
	
	private String headline,body,link,thumbnail,user,location;
	private Calendar date;
	
	
	public PeekData(){
		
//		headline = "not available";
//		body = "";
//		link = "";
//		thumbnail = "";
//		user = "";
//		location = "";
//		date = Calendar.getInstance();
		
	}
	
	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#getBody()
     */
	public String getBody() {
		return this.body;
	}

	/**
      *  @see uk.co.mccann.socialpeek.interfaces.Data#getDate()
     */
	public Calendar getDate() {
		return this.date;
	}
	
	/**
      *  @see uk.co.mccann.socialpeek.interfaces.Data#getHeadline()
     */
	public String getHeadline() {
		return this.headline;
	}

	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#getLink()
    */
	public String getLink() {
		return this.link;
	}
	
	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#getUser()
    */
	public String getUser() {
		return this.user;
	}
	
	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#getThumbnail()
    */
	public String getThumbnail() {
		return this.thumbnail;
	}
	
	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#getLocation()
    */
	public String getLocation() {
		return this.location;
	}
	
	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#setBody(String body)
    */
	public void setBody(String body) {
		this.body = body;
	}

	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#setDate(Calendar date)
    */
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#setHeadline(String headline)
    */
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#setLink(String link)
    */
	public void setLink(String link) {
		this.link = link;

	}

	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#setUser(String user)
    */
	public void setUser(String user) {
		this.user = user;

	}
	
	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#setThumbnail(String photo)
    */
	public void setThumbnail(String photo) {
		this.thumbnail = photo;

	}
	
	/**
     *  @see uk.co.mccann.socialpeek.interfaces.Data#setLocation(String location)
    */
	public void setLocation(String location) {
		this.location = location;

	}
	
	@Override
	public String toString() {
		
		StringWriter writer = new StringWriter();
		writer.append("_headline : " + this.getHeadline());
		writer.append("\n_link : " + this.getLink());
		writer.append("\n_user : " + this.getUser());
		writer.append("\n_date : " + this.getDate().getTime());
		writer.append("\n_Thumbnail : " + this.getThumbnail());
		writer.append("\n_body : " + this.getBody());
		writer.append("\n_location : " + this.getLocation());
		
		return writer.toString();
		
	}

}
