package uk.co.mccann.socialpeek.generator;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.Data;

/**
 * XMLGenerator
 * Generate valid XML data from PeekData object(s) ready to be parsed by what ever.
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
public class XMLGenerator extends AbstractGenerator {
	
	/* XML variables */
	private DocumentBuilderFactory dbf;
	private DocumentBuilder docBuilder;
	private Document document;
	private Element rootElement;
	
	/**
     * Default constructor 
   	 * 
   	 * call's super from AbstractGenerator to set up SimpleDateFormat property.
   	 * 
     * @see AbstractGenerator
     */
	public XMLGenerator() {
		super();
	}
	
	/**
     * Generate XML feed using single PeekData Object
   	 * 
   	 * Will read, parse and build XML string using single item
   	 * 
     * @param	dataIn	the PeekData object you want to build into XML data
     * @return the valid XML String
     * @see Data
     * @see uk.co.mccann.socialpeek.model.PeekData
     * @throws SocialPeekException
     */
	public String generate(Data dataIn) throws SocialPeekException {
		
		
		try {
			
			this.createDocument();
			
			//create the root element 
			this.rootElement = this.document.createElement("socialpeek");
			this.document.appendChild(this.rootElement);
			
			Element post = this.createPeekElement(dataIn);
			this.rootElement.appendChild(post);
				
			/* create a DOM implementation */
			DOMImplementation impl = this.document.getImplementation();
			DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS","3.0");
			
			/* create a writer and tell it to format the output so it's pretty! */
			LSSerializer writer = implLS.createLSSerializer();
			writer.getDomConfig().setParameter("format-pretty-print", true);
			writer.getDomConfig().setParameter("xml-declaration", false);
			
			/* create a serialized version of the document */ 
			String xmlSerialized = writer.writeToString(this.document.getDocumentElement());
			
			return xmlSerialized;

		} catch (Exception e) {
			throw new SocialPeekException("unable to perform XML generation: " + e);
		} 
		
	}

	/**
     * Creates a DOM document ready for Elements.
   	 *
     * @see Document
     */
	private void createDocument() throws ParserConfigurationException {
		
		/* create instance of document builder factory */
		this.dbf = DocumentBuilderFactory.newInstance();
		
		/* create a builder */
		this.docBuilder = this.dbf.newDocumentBuilder();

		/* create new DOM document */
		this.document = this.docBuilder.newDocument();
	
	}
	
	/**
     * Creates RSS Item for DOM
   	 *
   	 * @param data PeekData Object to be generated into the DOM.
   	 * @return channel DOM Element
     * @see Element
     * 
     */
	private Element createPeekElement(Data data){
		
		Element post = this.document.createElement("post");
		post.setAttribute("source", data.getLink());
		
		
		/* create post elements */
		Element headline, body, link, date, user, photo, location;
		Text headlineText, bodyText, linkText, dateText, userText, photoText, locationText;
		
		if(data.getHeadline()!=null && data.getHeadline().length() > 0) {
			headline = this.document.createElement("headline");
			headlineText = this.document.createCDATASection(data.getHeadline());
			headline.appendChild(headlineText);
			post.appendChild(headline);
		}
		
		if(data.getBody()!=null && data.getBody().length() > 0) {
			body = this.document.createElement("body");
			bodyText = this.document.createCDATASection(data.getBody());
			body.appendChild(bodyText);
			post.appendChild(body);
		}
		
		if(data.getLink()!=null && data.getLink().length() > 0) {
			link = this.document.createElement("link");
			linkText = this.document.createTextNode(data.getLink());
			link.appendChild(linkText);
			post.appendChild(link);
		}
		
		if(data.getDate()!=null) {
			date = this.document.createElement("date");
			dateText = this.document.createTextNode(this.sdf.format(data.getDate().getTime()));
			date.appendChild(dateText);
			post.appendChild(date);
		}	
			
		if(data.getUser()!=null) {
			user = this.document.createElement("user");
			userText = this.document.createTextNode(data.getUser());
			user.appendChild(userText);
			post.appendChild(user);
		}
		
		if(data.getLocation()!=null && data.getLocation().length() > 0) {
			location = this.document.createElement("location");
			locationText = this.document.createTextNode(data.getLocation());
			location.appendChild(locationText);
			post.appendChild(location);
		}
		
		if(data.getThumbnail()!=null && data.getThumbnail().length() > 0) {
			photo = this.document.createElement("user_photo");
			photoText = this.document.createTextNode(data.getThumbnail());
			photo.appendChild(photoText);
			post.appendChild(photo);
		}
		
		return post;

	}
	
	/**
     * Generate XML feed using multiple PeekData Objects
   	 * 
   	 * Will read, parse and build XML string using single item
   	 * 
     * @param	dataIn	the PeekData object you want to build into XML data,
     * @return the valid XML String
     * @see Data
     * @see uk.co.mccann.socialpeek.model.PeekData
     * @throws SocialPeekException
     * 
     */
	public String generate(List<Data> dataIn) throws SocialPeekException {
		
		try {
			
			this.createDocument();
			
			/* create root element */
			this.rootElement = this.document.createElement("socialpeek");
			this.rootElement.setAttribute("generator", SocialPeek.VERSION);
			this.rootElement.setAttribute("author", SocialPeek.AUTHOR);
			this.document.appendChild(this.rootElement);
			
			for(Data data : dataIn) {
				
				Element post = this.createPeekElement(data);
				this.rootElement.appendChild(post);
				
			}
			
			/* create a DOM implementation */
			DOMImplementation impl = this.document.getImplementation();
			DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS","3.0");
			
			/* create a writer and tell it to format the output so it's pretty! */
			LSSerializer writer = implLS.createLSSerializer();
			writer.getDomConfig().setParameter("format-pretty-print", true);
			writer.getDomConfig().setParameter("xml-declaration", false);
			
			/* create a serialized version of the document */ 
			String xmlSerialized = writer.writeToString(this.document.getDocumentElement());
			
			return xmlSerialized;

		} catch (Exception e) {
			throw new SocialPeekException("unable to perform XML generation: " + e);
		} 
		
	}

	
	
}
