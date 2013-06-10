package uk.co.mccann.socialpeek.generator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.mccann.socialpeek.exceptions.ParseException;
import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.Data;

/**
 * JSONGenerator
 * Generate valid JSON data from PeekData object(s) ready to be parsed by JSON friendly apps.
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

public class JSONGenerator extends AbstractGenerator {
	
	/**
     * Default constructor 
   	 * 
   	 * call's super from AbstractGenerator to set up SimpleDateFormat property.
   	 * 
     * @see AbstractGenerator
     */
	public JSONGenerator() {
		super();
	}
	
	
	/**
     * Generate JSON data from single PeekData Object
   	 * 
   	 * Will read, parse and build JSON string using single item, JSON based on SocialPeek XML XSD.
   	 * 
     * @param dataIn the PeekData object you want to build into JSON data,
     * @return the valid JSON String
     * @see Data
     * @see uk.co.mccann.socialpeek.model.PeekData
     * @throws SocialPeekException
     * 
     */
	public String generate(Data dataIn) throws SocialPeekException {
		
		try {
			/* construct a JSON Object */
			JSONObject jsonObject = new JSONObject();
			
			/* create a good old fashioned collection */
			Map<String,Object> dataCollection = new HashMap<String,Object>();
			dataCollection.put("headline",dataIn.getHeadline());
			dataCollection.put("body",dataIn.getBody());
			dataCollection.put("link",dataIn.getLink());
			dataCollection.put("date",this.sdf.format(dataIn.getDate().getTime()));
			
			/* check for a photo, if so, add it as an attribute*/
			if(dataIn.getThumbnail()!=null) {
				dataCollection.put("user_photo",dataIn.getThumbnail());
			}
			/* check for a user */
			if(dataIn.getUser()!=null) {
				dataCollection.put("user",dataIn.getUser());
			}
			
			/* check for a location */
			if(dataIn.getLocation()!=null) {
				dataCollection.put("location",dataIn.getLocation());
			}
			
			/* add to our collection */
			jsonObject.put("post",dataCollection);
			return jsonObject.toString();
			
			
		} catch (JSONException e) {
			
			/* problem building JSONObject */
			throw new SocialPeekException("unable to build JSON Object : " + e.getMessage());
		}
		
	}
	
	/**
     * Generate JSON from multiple PeekData Objects.
   	 * 
   	 * Will read, parse and build JSON string using multiple items, JSON based on SocialPeek XML XSD.
   	 * 
     * @param dataIn the List of Data objects you want to build into JSON data,
     * @return the valid JSON String
     * @see Data
     * @see uk.co.mccann.socialpeek.model.PeekData
     * @throws ParseException
     * 
     */
	public String generate(List<Data> dataIn) throws SocialPeekException {
		try {
			
			/* iterate through posts */
			List<JSONObject> dataArray = new ArrayList<JSONObject>();
			
			for(Data data : dataIn) {
			
				/* construct a JSON Object */
				JSONObject jsonObject = new JSONObject();
			
				/* create a good old fashioned collection */
				Map<String,String> dataCollection = new HashMap<String,String>();
				if(data.getHeadline()!=null) {
					dataCollection.put("headline",data.getHeadline());
				}
				if(data.getBody()!=null) {
					dataCollection.put("body",data.getBody());
				}
				if(data.getLink()!=null) {
					dataCollection.put("link",data.getLink());
				}
				if(data.getLocation()!=null) {
					dataCollection.put("location",data.getLocation());
				}
				if(data.getDate()!=null) {
					dataCollection.put("date",this.sdf.format(data.getDate().getTime()));
				}
				/* check for a photo, if so, add it as an attribute*/
				if(data.getThumbnail()!=null) {
					dataCollection.put("user_photo",data.getThumbnail());
				}
			
				/* add to our collection */
				jsonObject.put("post",dataCollection);
				dataArray.add(jsonObject);
			
			}
			
			/* construct one big jucy array */
			JSONArray array = new JSONArray(dataArray);
			return array.toString();
			
			
		} catch (JSONException e) {
			
			/* problem building JSONObject */
			throw new SocialPeekException("unable to build JSON Object : " + e.getMessage());
		}
	}

}
