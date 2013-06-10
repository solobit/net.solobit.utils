package uk.co.mccann.socialpeek.rss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.model.PeekData;

import com.sun.cnpi.rss.elements.Author;
import com.sun.cnpi.rss.elements.Item;


/**
 * 
 * Provides Methods to convert RSS Items to Data objects
 * Differing services require different methods construct
 * the Data objects
 * 
 *  <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 * 
 * @author Lewis Taylor - lewis.taylor@europe.mccann.com
 *
 */
public class RSSHelper {

	// The data format of the service used.
	private SimpleDateFormat dateFormat;


	public RSSHelper(){
		dateFormat = null;
	}

	public RSSHelper(String dateFormat){
		setDateFormat(dateFormat);
	}

	public RSSHelper(SimpleDateFormat dateFormat){
		setDateFormat(dateFormat);
	}

	/**
	 * Sets the format of the date to allow
	 * parsing of the date element in the 
	 * RSS item.
	 * 
	 * @param dateFormat
	 */
	public void setDateFormat(String dateFormat){

		this.dateFormat = dateFormat==null ? null : new SimpleDateFormat(dateFormat);
	}

	/**
	 * Sets the format of the date to allow
	 * parsing of the date element in the 
	 * RSS item.
	 * 
	 * @param dateFormat
	 */
	public void setDateFormat(SimpleDateFormat dateFormat){

		this.dateFormat = dateFormat;
	}


	/**
	 * Converts an RSS Item into a Data item for use
	 * with SocialPeek
	 * 
	 * @param item - item to be converted
	 * @return the converted data item
	 */
	public Data convertToData(Item item){

		List<Item> items = new ArrayList<Item>();
		items.add(item);

		// Return the first item in the returned list
		return convertToData(items).get(0);
	}

	/**
	 * Converts a list of RSS Items into a Data item
	 * for use with SocialPeek
	 * 
	 * @param items - items to be converted
	 * @return the converted data items
	 */
	public List<Data> convertToData(List<Item> items){

		List<Data> dataItems = new ArrayList<Data>();


		// Map each rss item to a data object
		for (Item rssItem : items){

			Data data = new PeekData();

			// Set Title
			if(rssItem.getTitle()!=null && rssItem.getTitle().getText()!=null) 
				data.setHeadline(rssItem.getTitle().getText());
			else 
				data.setHeadline("not available");


			// Set Description
			if(rssItem.getDescription()!=null && !rssItem.getDescription().equals("null")){
				rssItem.getDescription().setText(rssItem.getDescription().getText().replace("\n", " "));
				data.setBody(rssItem.getDescription().getText());
			}
			else 
				data.setBody("");


			// Set Link
			if(rssItem.getLink()!= null && rssItem.getLink().getText()!= null) 
				data.setLink(rssItem.getLink().getText()); 
			else 
				data.setLink("http://socialpeek.com");

			// Set User
			if(rssItem.getAuthor()!=null && rssItem.getAuthor().getText()!=null) 
				data.setUser(rssItem.getAuthor().getText()); // only take the first creator
			else 
				data.setUser("not available");


			// Set Date
			Calendar cal = Calendar.getInstance();


			try {
				if (dateFormat!=null && rssItem.getPubDate()!=null && rssItem.getPubDate().getText()!=null)
					cal.setTime(dateFormat.parse(rssItem.getPubDate().getText()));
			} catch (ParseException e) {
				cal.setTime(Calendar.getInstance().getTime());
			}

			data.setDate(cal);


			dataItems.add(data);

		}

		return dataItems;

	}


	/**
	 * Converts an RSS Item into a Data item for use
	 * with SocialPeek
	 * 
	 * @param item - item to be converted
	 * @return the converted data item
	 */
	public Data convertBlinkxToData(Item item){

		List<Item> items = new ArrayList<Item>();
		items.add(item);

		// Return the first item in the returned list
		return convertBlinkxToData(items).get(0);
	}

	/**
	 * Converts a list of RSS Items into a Data item
	 * for use with SocialPeek
	 * 
	 * @param items - items to be converted
	 * @return the converted data items
	 */
	public List<Data> convertBlinkxToData(List<Item> items){

		return getDataListWithEnclosure(items);
	}


	/**
	 * Converts an RSS Item into a Data item for use
	 * with SocialPeek
	 * 
	 * @param item - items to be converted
	 * @return the converted data item
	 */
	public Data convertFlickrToData(Item item){

		List<Item> items = new ArrayList<Item>();
		items.add(item);

		// Return the first item in the returned list
		return convertFlickrToData(items).get(0);
	}

	/**
	 * Converts a list of RSS Items into a Data item
	 * for use with SocialPeek
	 * 
	 * @param items - items to be converted
	 * @return the converted data items
	 */
	public List<Data> convertFlickrToData(List<Item> items){

		// Change RSS items author from attribute to node text
		for (Item item : items){
			Author author = new Author();
			String userSuffix = item.getLink().getText().replace("http://www.flickr.com/photos/", "");
			userSuffix = userSuffix.replaceAll("/.*/", "");
			author.setText(userSuffix);
			item.setAuthor(author);
		}
		
		return getDataListWithEnclosure(items);
	}


	/**
	 * Converts a list of RSS Items into a Data item
	 * for use with SocialPeek. Extracts enclosure
	 * property to use with thumbnail field
	 * 
	 * @param items - items to be converted
	 * @return the converted data items
	 */
	private List<Data> getDataListWithEnclosure(List<Item> items){

		List<Data> dataItems = new ArrayList<Data>();


		// Map each rss item to a data object
		for (Item rssItem : items){

			Data data = convertToData(rssItem);

			// Set photo
			if(rssItem.getEnclosure()!=null)
				data.setThumbnail(rssItem.getEnclosure().getAttribute("url"));
			else
				data.setThumbnail("not available");

			// Remove the HTML from the description
			data.setBody(data.getBody().replaceAll("<.*?>", ""));

			dataItems.add(data);
		}


		return dataItems;

	}


	/**
	 * Converts an RSS Item into a Data item for use
	 * with SocialPeek
	 * 
	 * @param item - items to be converted
	 * @return the converted data item
	 */
	public Data covertYouTubeToData(Item item){

		List<Item> items = new ArrayList<Item>();
		items.add(item);

		// Return the first item in the returned list
		return convertYouTubeToData(items).get(0);
	}

	/**
	 * Converts a list of RSS Items into a Data item
	 * for use with SocialPeek
	 * 
	 * @param items - items to be converted
	 * @return the converted data items
	 */
	public List<Data> convertYouTubeToData(List<Item> items){

		List<Data> dataItems = new ArrayList<Data>();


		// Map each rss item to a data object
		for (Item rssItem : items){

			Data data = convertToData(rssItem);


			// Extract thumbnail data from description as YouTube is shit!
			String description = data.getBody();


			String[] result = description.split("<img alt=\"\" src=\"");

			String thumbnail = null;
			if (result[1]!=null){
				int end = 0;
				end = result[1].indexOf("\">");
				thumbnail = result[1].substring(0, end);
			}

			data.setThumbnail(thumbnail);

			// Also do the same with the user
			String[] userResult = description.split("user=");

			String user = null;
			if (userResult.length>1){
				if (userResult[1]!=null){
					int end = 0;
					end = userResult[1].indexOf("\">");
					user = userResult[1].substring(0, end);
				}
			}

			data.setUser(user);

			// Remove the HTML from the description
			data.setBody(description.replaceAll("<.*?>", ""));

			dataItems.add(data);
		}

		return dataItems;
	}

	/**
	 * Converts an RSS Item into a Data item for use
	 * with SocialPeek
	 * 
	 * @param item - items to be converted
	 * @return the converted data item
	 */
	public Data covertTruveoToData(Item item){

		List<Item> items = new ArrayList<Item>();
		items.add(item);

		// Return the first item in the returned list
		return convertTruveoToData(items).get(0);
	}

	/**
	 * Converts a list of RSS Items into a Data item
	 * for use with SocialPeek
	 * 
	 * @param items - items to be converted
	 * @return the converted data items
	 */
	public List<Data> convertTruveoToData(List<Item> items){

		List<Data> dataItems = new ArrayList<Data>();


		// Map each rss item to a data object
		for (Item rssItem : items){

			Data data = convertToData(rssItem);


			// Extract thumbnail data from description as YouTube is shit!
			String description = data.getBody();


			String[] result = description.split("<img src=\"");

			String thumbnail = null;
			if(result.length>1){
				if (result[1]!=null){
					int end = 0;
					end = result[1].indexOf("\">");
					thumbnail = result[1].substring(0, end);
				}
			}

			data.setThumbnail(thumbnail);

			// Remove the HTML from the description
			data.setBody(description.replaceAll("<.*?>", ""));

			dataItems.add(data);
		}

		return dataItems;
	}


}
