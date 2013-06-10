package uk.co.mccann.socialpeek.model;

import java.io.StringWriter;

/**
* <b>LastFMTrack</b><br/>
* Store information about a Track on LastFM
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
public class LastFMTrack {
	
	private String name;
	private String artist;
	private String url;
	private String thumbnail;
	private String image;
	
	
	/**
     *  Get the name of the track
     *  @return name of the track
     */
	public String getName() {
		return name;
	}
	
	/**
     *  Set the name of the track
     *  @param name is the name of the track
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     *  Get the name of artist of who produced the track
     *  @return name of the artist
     */
	public String getArtist() {
		return artist;
	}
	
	/**
     *  Set the name of artist of the track
     *  @param artist is the name of the artist
     */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
     *  Get the URL on lastfm of the track
     *  @return url of the track
     */
	public String getUrl() {
		return url;
	}
	
	/**
     *  Set the name lastfm URL of the track
     *  @param url is the url of the track
     */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
     *  Get the thumbnail image URL of the track
     *  @return thumbnail image URL of the track
     */
	public String getThumbnail() {
		return thumbnail;
	}
	
	/**
     *  Set the name thumbnail image URL of the track
     *  @param thumbnail is the image thumb URL of the track
     */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	/**
     *  Get the full size image URL of the track
     *  @return full size image URL of the track
     */
	public String getImage() {
		return image;
	}
	
	/**
     *  Set the name thumbnail image of the track
     *  @param image is the full size image URL
     */
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		
		StringWriter writer = new StringWriter();
		writer.append("[Track Name: " + getName())
			  .append("| Artist: " + getArtist())
			  .append("| URL: " + getUrl())
			  .append("| Thumbnail: " + getThumbnail())
			  .append("| Image: " + getThumbnail());
		
		return writer.toString();
	}
	
}
