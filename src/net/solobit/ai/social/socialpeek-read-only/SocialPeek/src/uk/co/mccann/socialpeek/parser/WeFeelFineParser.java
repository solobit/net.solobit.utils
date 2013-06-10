package uk.co.mccann.socialpeek.parser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.xerces.parsers.DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import uk.co.mccann.socialpeek.exceptions.ParseException;
import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.model.PeekData;
import uk.co.mccann.socialpeek.service.WeFeelFineService;

/**
 * <b>WeFeelFineParser</b><br/>
 * Use the WWF API to read and parse feelings and thoughts from around the web
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
public class WeFeelFineParser extends AbstractParser {

	private String apiURL = WeFeelFineService.API_URL + "ShowFeelings?";
	private String returnFields = "display=xml&returnfields=feeling,gender,sentence,imageid,posturl,posttime,postdate,country,state,city,born";
	private String limitField ="limit=";
	private String feelingField ="feeling=";
	private String extraImagesField ="extraimages=50";
	private String postMonthField ="postmonth=" + (Calendar.getInstance().get(Calendar.MONTH) + 1);
	

	private SimpleDateFormat wwfDateFormat;

	/**
	 * Get XML Source, no longer required as Xerces will grab the XML remote
	 * automatically
	 * 
	 * @throws ParseException
	 */
	private String getXMLSource(String url) throws ParseException {

		try {

			/* open stream */
			URL urlObj = new URL(url);
			InputStream in = urlObj.openStream();

			/* wrap a reader around the stream */
			BufferedReader buff = new BufferedReader(new InputStreamReader(in));

			/* populate writer with xml data from API */
			StringWriter writer = new StringWriter();

			String xmlData;

			while ((xmlData = buff.readLine()) != null) {
				writer.append(xmlData + "\n");
			}

			return writer.toString();

		} catch (IOException e) {

			throw new ParseException("unable to retrive from WWF API: "
					+ e.getMessage());
		}

	}
	
	
	
	private String getImgPath(String postdate, String imageid, String imagesize){
	    String imgpath = "http://images.wefeelfine.org/data/images/";
	    imgpath += postdate.replace('-','/');
	    imgpath += "/"+imageid;
	    if(imagesize == "thumb") imgpath += "_thumb.jpg";
	    else imgpath += "_full.jpg";
	    return imgpath;
	}
	
	private List<Data> generateFeelings() throws ParseException {
		
		return this.parseXML(this.apiURL + this.limitField + 50 + "&" +  this.returnFields);
		
	}
	
	private List<Data> generateFeelings(int limit, String emotion) throws ParseException {
	
		List<Data> extractedData = this.parseXML(this.apiURL + this.limitField + limit + "&" + this.feelingField + emotion + "&" +  this.returnFields + "&" + this.extraImagesField + "&" + this.postMonthField);
		/* trim feelings */
		List<Data> compactedData = new ArrayList<Data>();
		Collections.shuffle(extractedData);
		if(limit > extractedData.size()) limit = extractedData.size();
		int counter = 0;
		for(Data data : extractedData){
			if(counter < limit) {
				compactedData.add(data);
				counter++;
			} else {
				break;
			}
		}
		return compactedData;
	}
	
	private List<Data> generateFeelings(int limit) throws ParseException {
		
		List<Data> extractedData = this.parseXML(this.apiURL + this.limitField + limit + "&" + this.returnFields + "&" + this.extraImagesField + "&" + this.postMonthField);
		/* trim feelings */
		List<Data> compactedData = new ArrayList<Data>();
		Collections.shuffle(extractedData);
		if(limit > extractedData.size()) limit = extractedData.size();
		int counter = 0;
		for(Data data : extractedData){
			if(counter < limit) {
				compactedData.add(data);
				counter++;
			} else {
				break;
			}
			
		}
		return compactedData;
	}
	
	private List<Data> generateFeelings(String emotion) throws ParseException {
		
		return this.parseXML(this.apiURL  + this.feelingField + emotion + "&" +  this.returnFields + "&" + this.limitField + 50 + "&" + this.extraImagesField + "&" + this.postMonthField);
		
	}
	
	private String parseDodgyCharacters(String xml) {
		
		return xml.replaceAll("&", "%amp%");
		
	}
	
	
	private List<Data> parseXML(String url) throws ParseException {
		try {
			
			DOMParser parser = new DOMParser();
			
			/* get XML source and parse instead of using URL direct, Xerces gets upset when it finds ampersands, and for some reason
			 * the WWF API allows ampersands in attributes to XML nodes!!! yikes 
			 */
			String xml = this.parseDodgyCharacters(this.getXMLSource(url));
			BufferedReader reader = new  BufferedReader(new StringReader(xml));
			InputSource source = new InputSource(reader);
			
			/* parse the data! after all that formatting */
			parser.parse(source);
			
			List<Data> dataArray = new ArrayList<Data>();
			
			/* get DOM doc from parsed remote URL */
			Document node = parser.getDocument();
			
			/* first node should always be 'feelings' */
			if (node.hasChildNodes()) {
				
				/* get feelings node, and all feeling children */
				if (node.getFirstChild().getFirstChild() != null) {
					
					/* all children */
					NodeList children = node.getFirstChild().getChildNodes();

					for (int x = 0; x < children.getLength(); x++) {
						
						NamedNodeMap map = children.item(x).getAttributes();
						
						/* make sure that there is content in the node */
						if (map != null) {
							
							Data data = new PeekData(); 
							if(map.getNamedItem("feeling")!=null) data.setHeadline(map.getNamedItem("feeling").getTextContent());
							if(map.getNamedItem("sentence")!=null)data.setBody(map.getNamedItem("sentence").getTextContent().replaceAll("%amp%;", "&"));
							if(map.getNamedItem("posturl")!=null)data.setLink(map.getNamedItem("posturl").getTextContent().replaceAll("%amp%", "&"));
							
							/* set image */
							String postDate = map.getNamedItem("postdate").getTextContent();
							String postTime = map.getNamedItem("posttime").getTextContent();
							
							if(map.getNamedItem("imageid")!=null) {
								String imageId = map.getNamedItem("imageid").getTextContent();
								data.setThumbnail(this.getImgPath(postDate, imageId, "thumb"));
								
							}
							
							/* set date */
							Calendar date = Calendar.getInstance();
							date.setTime(this.wwfDateFormat.parse(postDate));
							Calendar time = Calendar.getInstance();
							time.setTimeInMillis(Long.valueOf(postTime));
							
							/* compile complete time */
							Calendar compiledTime = Calendar.getInstance();
							compiledTime.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE));
							data.setDate(compiledTime);
							
							String location, city = null, state = null, country = null;
							if(map.getNamedItem("city")!=null) city = map.getNamedItem("city").getTextContent();
							if(map.getNamedItem("state")!=null) state = map.getNamedItem("state").getTextContent();
							if(map.getNamedItem("country")!=null) country = map.getNamedItem("country").getTextContent();
							
							/* parse location */
							location = "";
							if(city!=null) location += city;
							if(state!=null) state += ", " + state;
							if(country!=null) country += ", " + country;
							data.setLocation(location);
							
							
							/* parse user */
							String age = null, gender = null;
							if(map.getNamedItem("gender")!=null) gender = map.getNamedItem("gender").getTextContent();
							if(map.getNamedItem("age")!=null) age = map.getNamedItem("born").getTextContent();
							
							String user = "";
							if(gender!=null) {
								Integer genderValue = new Integer(gender);
								if(genderValue.equals(1)) gender= "Male";
								if(genderValue.equals(0)) gender= "Female";
								user += gender;
								if(age!=null) {
									Calendar cal = Calendar.getInstance();
									Integer thisYear = cal.get(Calendar.YEAR);
									Integer ageValue = thisYear - Integer.valueOf(age);
									user += ", " + ageValue + " years old";
								}
							}
							
							data.setUser(user);
							dataArray.add(data);
						}

					}
				} else {
					throw new ParseException("no data recovered from url: " + url);
				}
				
			} else {
				throw new ParseException("no data recovered from url: " + url);
			}
			
			return dataArray;

		} catch (Exception exp) {
			throw new ParseException("unable to parse WeFeelFine XML: " + exp.getMessage());
		}

	}

	public Data getKeywordItem(String keyword) throws ParseException {
		
		List<Data> extractedData = this.generateFeelings(keyword);
		int randomNumber = this.random.nextInt(extractedData.size());
		if(randomNumber > 0) {
			return extractedData.get(randomNumber);
		} else {
			return extractedData.get(0);
		}
	}

	public Data getKeywordItem(String[] keywords) throws ParseException {
		
		List<Data> dataCollection = new ArrayList<Data>();
		List<Data> compactedData = new ArrayList<Data>();
		
		for(String keyword : keywords) {
			
			List<Data> extractedData = this.generateFeelings(keyword);
			Collections.shuffle(extractedData);
			/* nested loop */
			for(Data dataItem : extractedData) {
				dataCollection.add(dataItem);
			}
		
		}
		
		/* shuffle it up */
		Collections.shuffle(dataCollection);
		return compactedData.get(0);
		
		
	}

	public List<Data> getLatestUserItems(int userId, int limit) throws ParseException {
		
		/* not implemented */
		return null;
	}

	public List<Data> getLatestUserItems(String userId, int limit) throws ParseException {
		
		/* not implemented */
		return null;
	}

	public Data getLatestUserItem(int userId) throws ParseException {
		
		/* not implemented */
		return null;
	}

	public Data getLatestUserItem(String userId) throws ParseException {
		
		/* not implemented */
		return null;
	}

	public List<Data> getItems(int limit) throws ParseException {
		
		List<Data> extractedData = this.generateFeelings(limit);
		Collections.shuffle(extractedData);
		
		List<Data> compactedData = extractedData;
		if(limit > extractedData.size()) limit =extractedData.size();
		for(int x = 0; x < limit; x++) {
			compactedData.add(extractedData.get(x));
		}
		
		return compactedData;
	}

	public List<Data> getKeywordItems(String keyword, int limit) throws ParseException {
		
		List<Data> extractedData = this.generateFeelings(limit, keyword);
		Collections.shuffle(extractedData);
		
		/* truncate data, we only want the limit returned */
		List<Data> compactedData = new ArrayList<Data>();
		if(limit > extractedData.size()) limit = extractedData.size(); // make sure we don't go out of bounds!
		/* now trim it up */
		for(int x = 0; x < limit; x++) {
			compactedData.add(extractedData.get(x));
		}
		
		return compactedData;
		
	}

	public List<Data> getKeywordItems(String[] keywords, int limit) throws ParseException {
		
		List<Data> dataCollection = new ArrayList<Data>();
		List<Data> compactedData = new ArrayList<Data>();
		
		/* work out ratio of of entries to catch per keyword */
		int ratio = limit / keywords.length;
		
		for(String keyword : keywords) {
			
			List<Data> extractedData = this.generateFeelings(ratio, keyword);
			Collections.shuffle(extractedData);
			/* nested loop */
			for(Data dataItem : extractedData) {
				dataCollection.add(dataItem);
			}
			
			
		
		}
		
		
		/* shuffle it up */
		Collections.shuffle(dataCollection);
		/* now trim it up */
		if(limit > dataCollection.size()) limit = dataCollection.size(); // make sure we don't go out of bounds!
		for(int x = 0; x < limit; x++) {
			compactedData.add(dataCollection.get(x));
		}
		
		return compactedData;
		
	}

	public List<Data> getUserItems(int userId, int limit) throws ParseException {
		
		/* not implemented */
		return null;
		
	}

	public List<Data> getUserItems(String userId, int limit) throws ParseException {
		
		/* not implemented */
		return null;
		
	}

	public Data getItem() throws ParseException {
	
		List<Data> extractedData = this.generateFeelings();
		return extractedData.get(this.random.nextInt(extractedData.size()-1));
	
	}

	public Data getUserItem(int userId) throws ParseException {
		
		/* not implemented */
		return null;
	}

	public Data getUserItem(String userId) throws ParseException {
		
		/* not implemented */
		return null;
	}

	@Override
	public void setUpParser() {
		
		this.random = new Random();
		this.wwfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

}
