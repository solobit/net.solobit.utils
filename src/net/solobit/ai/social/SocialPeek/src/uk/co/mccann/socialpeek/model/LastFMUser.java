package uk.co.mccann.socialpeek.model;

import java.io.StringWriter;

/**
* <b>LastFMUser</b><br/>
* Store information about a User on LastFM
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
public class LastFMUser {

	private String username;
	private String realname;
	private Integer age;
	private String gender;
	private String country;
	private String image;
	private String url;
	
	/**
     *  Get the url of the user
     *  @return the url of the users profile
     */
	public String getUrl() {
		return url;
	}
	
	/**
     *  Set the url of the user
     *  @param url the profile url of the user
     */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
     *  Get the username of the user
     *  @return username of the user
     */
	public String getUsername() {
		return username;
	}
	
	/**
     *  Set the username of the user
     *  @param username of the user
     */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
     *  Get the image URL of the user
     *  @return the image URL of the user
     */
	public String getImage() {
		return image;
	}
	
	/**
     *  Set the image URL of the user
     *  @param image URL of the user
     */
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		
		StringWriter writer = new StringWriter();
		writer.append("[User Name: " + getUsername())
			  .append("| Realname: " + getRealname())
			  .append("| Url: " + getUrl())
			  .append("| Age: " + getAge())
			  .append("| Gender: " + getGender())
			  .append("| Country: " + getCountry())
			  .append("| Image: " + getImage());
		
		return writer.toString();
	}

	/**
     *  Get the real name of the user
     *  @return the real name of the user
     */
	public String getRealname() {
		return realname;
	}

	/**
     *  Set the real name of the user
     *  @param realname of the user
     */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
     *  Get the image URL of the user
     *  @return the image URL of the user
     */
	public Integer getAge() {
		return age;
	}
	
	/**
     *  Set the age of the user
     *  @param age of the user
     */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
     *  Get the gender of the user
     *  @return the gender (m or f)
     */
	public String getGender() {
		return gender;
	}
	
	/**
     *  Set the Gender of the user
     *  @param gender of the user
     */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
     *  Get the country of the user
     *  @return the country of the user
     */
	public String getCountry() {
		return country;
	}

	/**
     *  Set the country of the users location
     *  @param country set the users country location
     */
	public void setCountry(String country) {
		this.country = country;
	}
	
}
