package uk.co.mccann.socialpeek.model;

import java.io.StringWriter;
import java.util.Date;

/**
* <b>LastFMRecentTrack</b><br/>
* Store information about a users recent track play on LastFM
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
public class LastFMRecentTrack {
	
	private String name;
	private String username;
	private String artist;
	private String album;
	private Date played;
	private String url;
	
	
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
	
	
	@Override
	public String toString() {
		
		StringWriter writer = new StringWriter();
		writer.append("[Track Name: " + getName())
			  .append("| Artist: " + getArtist())
			  .append("| URL: " + getUrl())
			  .append("| Album: " + getAlbum())
			  .append("| User: " + getUsername())
			  .append("| Played: " + getPlayed());
		
		
		return writer.toString();
	}

	public String getUsername() {
		return username;
	}

	/**
     *  Set the username of the person who recently played the track
     *  @param username of the user
     */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
     *  Get the name of the album that the track belongs to
     *  @return name of the album
     */
	public String getAlbum() {
		return album;
	}

	/**
     *  Set the album that the track belongs to
     *  @param album the track belongs to
     */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
     *  Get the date the track was played
     *  @return date the track was played
     */
	public Date getPlayed() {
		return played;
	}

	/**
     *  Set the date of when the track was played
     *  @param played date the track was played
     */
	public void setPlayed(Date played) {
		this.played = played;
	}
	
}
