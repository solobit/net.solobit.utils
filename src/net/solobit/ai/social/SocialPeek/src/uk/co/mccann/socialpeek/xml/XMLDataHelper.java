package uk.co.mccann.socialpeek.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import uk.co.mccann.socialpeek.interfaces.Data;


/**
 *	Helper Class to allow conversion from XML Strings to Data objects
 * 
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 * 
 *	@author Lewis Taylor <lewis.taylor@europe.mccann.com>
 */

public class XMLDataHelper {

	private SimpleDateFormat dateFormat;

	public XMLDataHelper(){
		dateFormat = null;
	}

	/**
	 * Sets the headline of the Data object with the
	 * headline passed in
	 * 
	 * @param data
	 * @param headline
	 * @throws ParseException
	 */
	public void setHeadline(Data data, String headline) throws ParseException{

		if (headline == null || data == null)
			data.setHeadline("not available");
		else
			data.setHeadline(headline);
	}

	/**
	 * 	Sets the body of the Data object with the
	 * 	body passed in
	 * 
	 * @param data
	 * @param body
	 * @throws ParseException
	 */
	public void setBody(Data data, String body) throws ParseException{

		if (body == null || data == null)
			data.setHeadline("");
		else
			data.setBody(body);
	}


	/**
	 * 
	 * Sets the link of the Data object with the
	 * link passed in
	 * 
	 * @param data
	 * @param link
	 * @throws ParseException
	 */
	public void setLink(Data data, String link) throws ParseException{

		if (link == null || data == null)
			data.setLink("http://socialpeek.com");
		else
			data.setLink(link);
	}

	/**
	 * Sets the user of the Data object with the
	 * user passed in
	 * 
	 * @param data
	 * @param user
	 * @throws ParseException
	 */
	public void setUser(Data data, String user) throws ParseException{

		if (user == null || data == null)
			data.setLink("not available");
		else
			data.setUser(user);
	}


	/**
	 * Sets the date of the Data object with the
	 * date passed in
	 * 
	 * @param data
	 * @param date
	 * @throws ParseException
	 */
	public void setDate(Data data, String date) throws ParseException{

		if (date == null || data == null)
			return;

		Calendar cal =  Calendar.getInstance();

		if (dateFormat!=null)
			cal.setTime(dateFormat.parse(date));

		data.setDate(cal);
	}

	/**
	 * Sets the Helpers SimpleDateFormat
	 */
	public void setDateFormat(String dateFormat){
		this.dateFormat =  new SimpleDateFormat(dateFormat);
	}

	/**
	 * Sets the Helpers SimpleDateFormat
	 */
	public void setDateFormat(SimpleDateFormat dateFormat){
		this.dateFormat = dateFormat;
	}

}
