package uk.co.mccann.socialpeek.parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.exceptions.ParseException;
import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.model.LastFMRecentTrack;
import uk.co.mccann.socialpeek.model.LastFMTrack;
import uk.co.mccann.socialpeek.model.LastFMUser;
import uk.co.mccann.socialpeek.model.PeekData;
import uk.co.mccann.socialpeek.service.LastFMService;


/**
* <b>LastFMParser</b><br/>
* extract and process data from audio scrobbler web services
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
public class LastFMParser extends AbstractParser {
	
	private SimpleDateFormat lastfmDateFormat;
	
	private LastFMTrack extractTrackFromChart() throws ParseException {
	
		List<LastFMTrack> trackList = extractTrackFromChart(50);
		if(trackList.size()>0) {
			/* return random track */
			return trackList.get(this.random.nextInt(trackList.size()-1));
		} else {
			return null;
		}
		
	}
	
	private List<LastFMTrack> extractTrackFromTag(String tag, int limit) throws ParseException {
		
		try {
			DOMParser parser = new DOMParser();
			
			if(SocialPeek.logging) {
				this.logger.info("loading xml from LastFM : " + LastFMService.TAG_API + tag.replace(" ", "+") + "/toptracks.xml");
			}
			
			/* parse the data! after all that formatting */
			parser.parse(LastFMService.TAG_API + tag.replace(" ", "+") + "/toptracks.xml");
			
			List<LastFMTrack> trackArray = new ArrayList<LastFMTrack>();
			
			/* get DOM doc from parsed remote URL */
			Document node = parser.getDocument();
			
			/* first node should always be 'tag' */
			if (node.hasChildNodes()) {
				
				/* get feelings node, and all feeling children */
				if (node.getFirstChild().getFirstChild() != null) {
					
					/* all children */
					NodeList children = node.getFirstChild().getChildNodes();
					
					for (int x = 0; x < children.getLength(); x++) {
						
						LastFMTrack trackObject = new LastFMTrack();
						
						/* track */
						Node track = children.item(x);
						
						/* attributes */
						NamedNodeMap map = track.getAttributes();
						if(map !=null) {
							
							if(SocialPeek.logging) {
								this.logger.info("track name '" + map.getNamedItem("name").getTextContent() + "'");
							}
							
							trackObject.setName(map.getNamedItem("name").getTextContent());
						}
						
						/* look at the track */
						if(track.hasChildNodes()) {
							
							NodeList trackNodes = track.getChildNodes();
							
							for (int y = 0; y < trackNodes.getLength(); y++) {
								
								Node trackItem = trackNodes.item(y);
								if(!trackItem.getNodeName().equals("#text")) {
									
									parseTrackItem(trackArray, trackObject, trackNodes, y);
								
								}
							}
						}
						
					}

				}
			
			}
			
			/* shuffle charts */
			Collections.shuffle(trackArray);
			
			/* truncate list to limit size */
			List<LastFMTrack> compactedTrackList = new ArrayList<LastFMTrack>();
			if(limit > trackArray.size()) limit = trackArray.size();
			int counter = 1;
			for(LastFMTrack track : trackArray) {
				if(counter < limit) {
					compactedTrackList.add(track);
					counter++;
				} else {
					break;
				}
			}
			return compactedTrackList;
			
		
		} catch (Exception exp) {
			if(SocialPeek.logging) {
				this.logger.error("error parsing xml from LastFM : " + exp.getLocalizedMessage());
			}
			throw new ParseException("unable to parse LastFM XML: " + exp.getMessage());
		}
	}
	
	
	private List<LastFMTrack> extractTrackFromChart(int limit) throws ParseException {
		
		try {
			DOMParser parser = new DOMParser();
			/* parse the data! after all that formatting */
			
			if(SocialPeek.logging) {
				this.logger.info("[loading xml from LastFM : " + LastFMService.CURRENT_CHART);
			}
			
			parser.parse(LastFMService.CURRENT_CHART);
			
			List<LastFMTrack> trackArray = new ArrayList<LastFMTrack>();
			
			/* get DOM doc from parsed remote URL */
			Document node = parser.getDocument();
			
			/* first node should always be 'toptracks' */
			if (node.hasChildNodes()) {
				
				/* get feelings node, and all feeling children */
				if (node.getFirstChild().getFirstChild() != null) {
					
					/* all children */
					NodeList children = node.getFirstChild().getChildNodes();
					
					for (int x = 0; x < children.getLength(); x++) {
						
						LastFMTrack trackObject = new LastFMTrack();
						
						/* track */
						Node track = children.item(x);
						
						/* attributes */
						NamedNodeMap map = track.getAttributes();
						if(map !=null) {
							//System.out.println("Track Name: " + map.getNamedItem("name").getTextContent());
							
							if(SocialPeek.logging) {
								this.logger.info("track name '" + map.getNamedItem("name").getTextContent() + "'");
							}
							
							trackObject.setName(map.getNamedItem("name").getTextContent());
						}
						
						/* look at the track */
						if(track.hasChildNodes()) {
							
							NodeList trackNodes = track.getChildNodes();
							
							for (int y = 0; y < trackNodes.getLength(); y++) {
								
								parseTrackItem(trackArray, trackObject, trackNodes, y);
							}
						}
						
					}

				}
			
			}
			
			/* shuffle charts */
			Collections.shuffle(trackArray);
			
			/* truncate list to limit size */
			List<LastFMTrack> compactedTrackList = new ArrayList<LastFMTrack>();
			if(limit > trackArray.size()) limit = trackArray.size();
			int counter = 1;
			for(LastFMTrack track : trackArray) {
				if(counter < limit) {
					compactedTrackList.add(track);
					counter++;
				} else {
					break;
				}
			}
			
			if(SocialPeek.logging) {
				this.logger.info("[compiled '" + compactedTrackList.size() + "' tracks]");
			}
			
			return compactedTrackList;
			
		
		} catch (Exception exp) {
			if(SocialPeek.logging) {
				this.logger.error("error parsing xml from LastFM : " + exp.getStackTrace()[0]);
			}
			throw new ParseException("unable to parse LastFM XML: " + exp.getMessage());
		}
	}

	private void parseTrackItem(List<LastFMTrack> trackArray, LastFMTrack trackObject, NodeList trackNodes, int y) {
		
		Node trackItem = trackNodes.item(y);
		if(!trackItem.getNodeName().equals("#text")) {
			
			if(trackItem.getNodeName().equals("artist")) {
				NamedNodeMap artistmap = trackItem.getAttributes();
				trackObject.setArtist(artistmap.getNamedItem("name").getTextContent());
				
				if(SocialPeek.logging) {
					this.logger.info("\t artist: " + artistmap.getNamedItem("name").getTextContent());
				}
		
			} else {
				if(trackItem.getNodeName().equals("url")) { 
					if(trackItem.getChildNodes().item(0)!=null) {
						trackObject.setUrl(trackItem.getChildNodes().item(0).getNodeValue());
						if(SocialPeek.logging) {
							this.logger.info("\t url: " + trackItem.getChildNodes().item(0).getNodeValue());
						}
					}
				}
				if(trackItem.getNodeName().equals("thumbnail")) {
					if(trackItem.getChildNodes().item(0)!=null) {
						trackObject.setThumbnail(trackItem.getChildNodes().item(0).getNodeValue());
						if(SocialPeek.logging) {
							this.logger.info("\t thumbnail: " + trackItem.getChildNodes().item(0).getNodeValue());
						}
					}
				}
				if(trackItem.getNodeName().equals("image")) {
					if(trackItem.getChildNodes().item(0)!=null) {
						trackObject.setImage(trackItem.getChildNodes().item(0).getNodeValue());
						if(SocialPeek.logging) {
							this.logger.info("\t image: " + trackItem.getChildNodes().item(0).getNodeValue());
						}
					}
				}
			}
			trackArray.add(trackObject);
		}
	}
	
	private LastFMUser extractFanFromArtist(LastFMTrack track) throws ParseException {
		
		List<LastFMUser> userList = this.extractFanFromArtist(track, 50);
		if(userList.size()>0) {
			/* return random user */
			return userList.get(this.random.nextInt(userList.size()-1));
		} else {
			return null;
		}
	}
		
	
	private List<LastFMUser> extractFanFromArtist(LastFMTrack track, int limit) throws ParseException {
		
		try {
			
			DOMParser parser = new DOMParser();
			
			
			/* parse the data! after all that formatting */
			parser.parse(LastFMService.ARTIST_API + track.getArtist().replace(" ", "+") + "/fans.xml");
			
			List<LastFMUser> userArray = new ArrayList<LastFMUser>();
			
			/* get DOM doc from parsed remote URL */
			Document node = parser.getDocument();
			
			/* first node should always be 'fans' */
			if (node.hasChildNodes()) {
				
				/* get feelings node, and all feeling children */
				if (node.getFirstChild().getFirstChild() != null) {
					
					/* all children */
					NodeList children = node.getFirstChild().getChildNodes();
					
					for (int x = 0; x < children.getLength(); x++) {
						
						LastFMUser userObject = new LastFMUser();
						
						/* fans */
						Node fans = children.item(x);
						
						/* attributes */
						NamedNodeMap map = fans.getAttributes();
						if(map !=null) {
							//System.out.println("User Name: " + map.getNamedItem("username").getTextContent());
							userObject.setUsername(map.getNamedItem("username").getTextContent());
						}
						
						/* look at the user */
						if(fans.hasChildNodes()) {
							
							NodeList userNodes = fans.getChildNodes();
							
							for (int y = 0; y < userNodes.getLength(); y++) {
								
								Node userItem = userNodes.item(y);
								if(!userItem.getNodeName().equals("#text")) {
									if(userItem.getNodeName().equals("image")) 
										if(userItem.getChildNodes().item(0)!=null) {
											userObject.setImage(userItem.getChildNodes().item(0).getNodeValue());
										}
									userArray.add(userObject);
								}
							}
						}
						
					}

				}
			
			}
			
			/* shuffle charts */
			Collections.shuffle(userArray);
			
			/* truncate list to limit size */
			List<LastFMUser> compactedUserList = new ArrayList<LastFMUser>();
			if(limit > userArray.size()) limit = userArray.size();
			int counter = 0;
			for(LastFMUser user : userArray) {
				if(counter < limit) {
					compactedUserList.add(user);
					counter++;
				} else {
					break;
				}
			}
			return compactedUserList;
			
			
		
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new ParseException("unable to parse LastFM XML: " + exp.getMessage());
		}
		
		
	}
	
	private LastFMRecentTrack extractRecentPlayFromFan(LastFMUser user) throws ParseException {
		List<LastFMRecentTrack> tracks = this.extractRecentPlayFromFan(user, 10);
		if(tracks.size() > 0){ 
			return tracks.get(0); // returns most recent play
		} else {
			return null;
		}
		
	}
	
	
	private List<LastFMRecentTrack> extractRecentPlayFromFan(LastFMUser user, int limit) throws ParseException {
		
		try {
			
			DOMParser parser = new DOMParser();
			
			if(SocialPeek.logging) {
				this.logger.info("[loading xml from LastFM : " + LastFMService.USER_API + user.getUsername().replace(" ", "+") + "/recenttracks.xml");
			}
			
			/* parse the data! after all that formatting */
			parser.parse(LastFMService.USER_API + user.getUsername().replace(" ", "+") + "/recenttracks.xml");
			
			List<LastFMRecentTrack> trackArray = new ArrayList<LastFMRecentTrack>();
			
			/* get DOM doc from parsed remote URL */
			Document node = parser.getDocument();
			
			
			
			/* attributes */
			NamedNodeMap mainmap = node.getFirstChild().getAttributes();
			
			
			/* first node should always be 'recenttracks' */
			if (node.hasChildNodes()) {
				
				/* get feelings node, and all feeling children */
				if (node.getFirstChild().getFirstChild() != null) {
					
					/* all children */
					NodeList children = node.getFirstChild().getChildNodes();
					
					for (int x = 0; x < children.getLength(); x++) {
						
						LastFMRecentTrack trackObject = new LastFMRecentTrack();
						if(mainmap !=null) {
							trackObject.setUsername(mainmap.getNamedItem("user").getTextContent());
						}
						
						/* track */
						Node track = children.item(x);
						
						/* look at the track */
						if(track.hasChildNodes()) {
							
							NodeList trackNodes = track.getChildNodes();
							
							for (int y = 0; y < trackNodes.getLength(); y++) {
								
								Node trackItem = trackNodes.item(y);
								if(!trackItem.getNodeName().equals("#text")) {
									if(trackItem.hasChildNodes()) {
									if(trackItem.getNodeName().equals("artist")) trackObject.setArtist(trackItem.getChildNodes().item(0).getNodeValue());
									if(trackItem.getNodeName().equals("name")) trackObject.setName(trackItem.getChildNodes().item(0).getNodeValue());
									if(trackItem.getNodeName().equals("url")) trackObject.setUrl(trackItem.getChildNodes().item(0).getNodeValue());
									if(trackItem.getNodeName().equals("album")) trackObject.setAlbum(trackItem.getChildNodes().item(0).getNodeValue());
									if(trackItem.getNodeName().equals("date")) trackObject.setPlayed(this.lastfmDateFormat.parse(trackItem.getChildNodes().item(0).getNodeValue()));
									
									}
								}
							}
							trackArray.add(trackObject);
						}
						
					}

				}
			
			}
			
			/* truncate list to limit size */
			List<LastFMRecentTrack> compactedTrackList = new ArrayList<LastFMRecentTrack>();
			if(limit > trackArray.size()) limit = trackArray.size();
			int counter = 0;
			for(LastFMRecentTrack track : trackArray) {
				if(counter < limit) {
					compactedTrackList.add(track);
					counter++;
				} else {
					break;
				}
			}
			return compactedTrackList;
			
		
		} catch (Exception exp) {
			throw new ParseException("unable to parse LastFM XML: " + exp.getMessage());
		}
		
		
	}
	
	private LastFMUser getFanProfile(String fan) throws ParseException {
		
		try {
			
			DOMParser parser = new DOMParser();
			/* parse the data! after all that formatting */
			parser.parse(LastFMService.USER_API + fan.replace(" ", "+") + "/profile.xml");
			
			/* get DOM doc from parsed remote URL */
			Document node = parser.getDocument();
			
			LastFMUser userObject = new LastFMUser();
			userObject.setUsername(fan);
			
			/* first node should always be 'fans' */
			if (node.hasChildNodes()) {
				
				/* get feelings node, and all feeling children */
				if (node.getFirstChild().getFirstChild() != null) {
					
					/* all children */
					NodeList children = node.getFirstChild().getChildNodes();
					
					for (int x = 0; x < children.getLength(); x++) {
						
						Node userItem = children.item(x);
						if(!userItem.getNodeName().equals("#text")) {
							if(userItem.getNodeName().equals("url")) userObject.setUrl(userItem.getChildNodes().item(0).getNodeValue());
							if(userItem.getNodeName().equals("realname")) userObject.setRealname(userItem.getChildNodes().item(0).getNodeValue());
							if(userItem.getNodeName().equals("age")) userObject.setAge(new Integer(userItem.getChildNodes().item(0).getNodeValue()));
							if(userItem.getNodeName().equals("gender")) userObject.setGender(userItem.getChildNodes().item(0).getNodeValue());
							if(userItem.getNodeName().equals("country")) userObject.setCountry(userItem.getChildNodes().item(0).getNodeValue());
							if(userItem.getNodeName().equals("avatar")) userObject.setImage(userItem.getChildNodes().item(0).getNodeValue());
							
						}
					}
				}
			}
			
			return userObject;
			
		} catch (Exception exp) {
			throw new ParseException("unable to parse LastFM XML: " + exp.getMessage());
		}
	}
	
	public Data getKeywordItem(String keyword) throws ParseException {
		
		/* get a single track from the charts */
		List<LastFMTrack> tracks = extractTrackFromTag(keyword, 50);
		List<Data> dataArray = new ArrayList<Data>();
		/* iterate over multiple items, use the limit to work out just how many artists to use */
		
		for(LastFMTrack track : tracks) {
			
				Data data = new PeekData();
				data.setHeadline(track.getName());
				String body = "'<a href=\"" + track.getUrl() + "\">" + track.getName() + "</a>', by <a href=\"http://www.last.fm/music/" + track.getArtist().replaceAll(" ","+") + "\">" + track.getArtist() + "</a> is a really popular '" + keyword + "' tune.";
				data.setBody(body);
				Calendar trackCal = Calendar.getInstance();
				data.setDate(trackCal);
				data.setLink(track.getUrl());
				data.setThumbnail(track.getThumbnail());
				dataArray.add(data);
			
		}
		return dataArray.get(this.random.nextInt(dataArray.size()-1));
		
		
	}

	public Data getKeywordItem(String[] keywords) throws ParseException {
		
		List<Data> keywordData = this.getKeywordItems(keywords, 50);
		if(keywordData.size() > 0) {
			return keywordData.get(this.random.nextInt(keywordData.size()-1));
		} else {
			return null;
		}
		
	}

	public List<Data> getLatestUserItems(int userId, int limit) throws ParseException {
		
		return this.getLatestUserItems(String.valueOf(userId), limit);
	}

	public List<Data> getLatestUserItems(String userId, int limit) throws ParseException {
		
		/* get user profile */
		LastFMUser userProfile = this.getFanProfile(userId);
		List<LastFMRecentTrack> trackList = this.extractRecentPlayFromFan(userProfile, limit);
		
		System.out.println("size of data array " + trackList.size());
		
		List<Data> dataList = new ArrayList<Data>();
		for(LastFMRecentTrack track : trackList) {
			dataList.add(this.compileFanDataObject(track, userProfile));
		}
		
		return dataList;
	}

	public Data getLatestUserItem(int userId) throws ParseException {
		
		return this.getLatestUserItem(String.valueOf(userId));
	}

	public Data getLatestUserItem(String userId) throws ParseException {
		
		/* get a single track from the charts */
		LastFMUser fan = this.getFanProfile(userId);
		LastFMRecentTrack recentTrack = extractRecentPlayFromFan(fan);
		
		return this.compileFanDataObject(recentTrack, fan);
		
	}

	public List<Data> getItems(int limit) throws ParseException {
		
		/* get a single track from the charts */
		List<LastFMTrack> tracks = extractTrackFromChart(limit);
		List<Data> dataArray = new ArrayList<Data>();
		int counter = 0;
		/* iterate over multiple items, use the limit to work out just how many artists to use */
		
		for(LastFMTrack track : tracks) {
			if(counter < limit) {
			
			LastFMUser fan = extractFanFromArtist(track); // get a random ran from each artist
			LastFMRecentTrack recentTrack = extractRecentPlayFromFan(fan);
			
			if(recentTrack!=null) {
				dataArray.add(this.compileFanDataObject(recentTrack, fan));
			} 
			counter++;
			} else { break; }
		}
		return dataArray;
		
	}

	public List<Data> getKeywordItems(String keyword, int limit) throws ParseException {
		
		/* get a single track from the charts */
		List<LastFMTrack> tracks = extractTrackFromTag(keyword, limit);
		List<Data> dataArray = new ArrayList<Data>();
		
		/* iterate over multiple items, use the limit to work out just how many artists to use */
		for(LastFMTrack track : tracks) {
			
				Data data = new PeekData();
				data.setHeadline(track.getName());
				String body = "'<a href=\"" + track.getUrl() + "\">" + track.getName() + "</a>', by <a href=\"http://www.last.fm/music/" + track.getArtist().replaceAll(" ","+") + "\">" + track.getArtist() + "</a> is a really popular '" + keyword + "' tune.";
				data.setBody(body);
				Calendar trackCal = Calendar.getInstance();
				data.setDate(trackCal);
				data.setLink(track.getUrl());
				data.setThumbnail(track.getThumbnail());
				dataArray.add(data);
			
		}
		return dataArray;
		
	}

	public List<Data> getKeywordItems(String[] keywords, int limit) throws ParseException {
		
		/* get a single track from the charts */
		List<Data> dataArray = new ArrayList<Data>();
		
		/* work out ratio of of entries to catch per keyword */
		int ratio = limit / keywords.length;
		
		/* for each keyword, grab ratio of data */
		for(String keyword : keywords) {
			
			List<LastFMTrack> tracks = extractTrackFromTag(keyword, ratio);
			
			/* iterate over multiple items, use the limit to work out just how many artists to use */
			for(LastFMTrack track : tracks) {
			
				Data data = new PeekData();
				data.setHeadline(track.getName());
				String body = "'<a href=\"" + track.getUrl() + "\">" + track.getName() + "</a>', by <a href=\"http://www.last.fm/music/" + track.getArtist().replaceAll(" ","+") + "\">" + track.getArtist() + "</a> is a really popular '" + keyword + "' tune.";
				data.setBody(body);
				Calendar trackCal = Calendar.getInstance();
				data.setDate(trackCal);
				data.setLink(track.getUrl());
				data.setThumbnail(track.getThumbnail());
				dataArray.add(data);
			
			}
		}
		
		/* mix it all up a little */
		Collections.shuffle(dataArray);
		
		return dataArray;
		
	}

	public List<Data> getUserItems(int userId, int limit) throws ParseException {
		return this.getLatestUserItems(String.valueOf(userId), limit);
	}

	public List<Data> getUserItems(String userId, int limit) throws ParseException {
		
		List<Data> dataArray = this.getLatestUserItems(userId, limit);
		Collections.shuffle(dataArray);
		return dataArray;
	}

	public Data getItem() throws ParseException {
		
		/* get a single track from the charts */
		LastFMTrack track = extractTrackFromChart();
		LastFMUser fan = extractFanFromArtist(track);
		LastFMRecentTrack recentTrack = extractRecentPlayFromFan(fan);
		
		while(recentTrack==null) {
			fan = extractFanFromArtist(track);
			recentTrack = extractRecentPlayFromFan(fan);
		}
		return this.compileFanDataObject(recentTrack, fan);
	}
	
	private Data compileFanDataObject(LastFMRecentTrack recentTrack, LastFMUser fan) {
		
		Data data = new PeekData();
		data.setHeadline(recentTrack.getName());
		String body = "<a href=\"http://www.last.fm/user/" + fan.getUsername().replaceAll(" ","+") + "\">" + fan.getUsername() + "</a> was listening to '<a href=\"" + recentTrack.getUrl() + "\">" + recentTrack.getName() + "</a>', by <a href=\"http://www.last.fm/music/" + recentTrack.getArtist().replaceAll(" ","+") + "\">" + recentTrack.getArtist() + "</a>";
		body += " on " + this.lastfmDateFormat.format(recentTrack.getPlayed());
		data.setBody(body);
		Calendar trackCal = Calendar.getInstance();
		trackCal.setTime(recentTrack.getPlayed());
		data.setDate(trackCal);
		data.setLink(recentTrack.getUrl());
		data.setUser(fan.getUsername());
		data.setThumbnail(fan.getImage());
		return data;
	}
		
	public Data getUserItem(int userId) throws ParseException {
		
		return this.getUserItem(String.valueOf(userId));
		
	}

	public Data getUserItem(String userId) throws ParseException {
		
		/* get a single track from the charts */
		
		LastFMUser fan = this.getFanProfile(userId);
		LastFMRecentTrack recentTrack = extractRecentPlayFromFan(fan);
		
		return this.compileFanDataObject(recentTrack, fan);
	
	}

	public void setUpParser() {
		super.setUpParser();
		this.logger = Logger.getLogger(LastFMParser.class);
		this.lastfmDateFormat = new SimpleDateFormat("dd MMM yyyy',' kk:mm");
	
	}

}
