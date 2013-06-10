package uk.co.mccann.socialpeek.generator;

import java.io.StringWriter;
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
 * RSSGenerator
 * Generate valid RSS 2.0 data from PeekData object(s) ready to be parsed by RSS readers or apps.
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

public class RSSGenerator extends AbstractGenerator {
	
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
	public RSSGenerator() {
		super();
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
     * Generate RSS feed using single PeekData Object
   	 * 
   	 * Will read, parse and build RSS string using single item, RSS uses Yahoo! Media RSS Module http://search.yahoo.com/mrss/
   	 * 
     * @param	dataIn	the PeekData object you want to build into RSS data,
     * @return the valid JSON String
     * @see Data
     * @see uk.co.mccann.socialpeek.model.PeekData
     * @throws SocialPeekException 
     */
	public String generate(Data dataIn) throws SocialPeekException {
		
		try {
			
			this.createDocument();
			
			/* create root element, use the yahoo media RSS module */
			this.rootElement = this.document.createElement("rss");
			this.rootElement.setAttribute("xmlns:media", "http://search.yahoo.com/mrss/");
			this.rootElement.setAttribute("version", "2.0");
			
			this.document.appendChild(this.rootElement);
			Element channel = this.createChannel();
			Element post = this.createPeekElement(dataIn);
			channel.appendChild(post);

			/* add channel to root */
			this.rootElement.appendChild(channel);
				
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
     * Creates RSS Channel
   	 *
   	 * @return channel DOM Element
     * @see Element
     */
	private Element createChannel() {
		
		Element channel = this.document.createElement("channel");
		
		/* rss channel elements */
		Element title, link, image, imageUrl, imageTitle, imageHeight, imageWidth, imageLink, description, author ;
		Text titleText, linkText, imageUrlText, imageTitleText, imageHeightText, imageWidthText, imageLinkText,descriptionText, authorText;
		
		title = this.document.createElement("title");
		titleText = this.document.createTextNode(SocialPeek.APPLICATION_NAME);
		title.appendChild(titleText);
		channel.appendChild(title);
		
		description = this.document.createElement("description");
		descriptionText = this.document.createTextNode(SocialPeek.APPLICATION_INFO);
		description.appendChild(descriptionText);
		channel.appendChild(description);
		
		link = this.document.createElement("link");
		linkText = this.document.createTextNode(SocialPeek.APPLICATION_LINK);
		link.appendChild(linkText);
		channel.appendChild(link);
		
		author = this.document.createElement("author");
		authorText = this.document.createTextNode(SocialPeek.AUTHOR_EMAIL);
		author.appendChild(authorText);
		//channel.appendChild(author); 
		
		image = this.document.createElement("image");
		imageUrl = this.document.createElement("url");
		imageUrlText = this.document.createTextNode(SocialPeek.APPLICATION_LOGO);
		imageUrl.appendChild(imageUrlText);
		image.appendChild(imageUrl);
		
		imageLink = this.document.createElement("link");
		imageLinkText = this.document.createTextNode(SocialPeek.APPLICATION_LINK);
		imageLink.appendChild(imageLinkText);
		image.appendChild(imageLink);
		
		imageTitle = this.document.createElement("title");
		imageTitleText = this.document.createTextNode(SocialPeek.APPLICATION_NAME);
		imageTitle.appendChild(imageTitleText);
		image.appendChild(imageTitle);
		
		imageWidth = this.document.createElement("width");
		imageWidthText = this.document.createTextNode(SocialPeek.APPLICATION_LOGO_WIDTH);
		imageWidth.appendChild(imageWidthText);
		image.appendChild(imageWidth);
		
		imageHeight = this.document.createElement("height");
		imageHeightText = this.document.createTextNode(SocialPeek.APPLICATION_LOGO_HEIGHT);
		imageHeight.appendChild(imageHeightText);
		image.appendChild(imageHeight);
		
		channel.appendChild(image);
		
		return channel;
		
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

		Element post = this.document.createElement("item");
		
		/* create post elements */
		Element title, mediaTitle, description, link, pubdate, thumbnail, author, guid;
		Text titleText, mediaTitleText, descriptionText, linkText, pubdateText, authorText, guidText;
		if(data.getHeadline()!=null) {
			title = this.document.createElement("title");
			titleText = this.document.createTextNode(data.getHeadline());
			title.appendChild(titleText);
			post.appendChild(title);
		}
		
		if(data.getBody()!=null) {
			
			/* generate a nice xhtml document to display our content in */
			
			/* summary */
			StringWriter writer = new StringWriter();
			if(data.getThumbnail()!=null) writer.append("<img align=\"right\" src=\"" + data.getThumbnail() + "\" alt=\"thumbnail\" /><br/>");
			writer.append("<p>" + data.getBody() + "</p>");
			if(data.getUser()!=null) writer.append("<p>Author: <strong>" + data.getUser() +"</strong><br/>");
			if(data.getLocation() != null && data.getLocation().length() > 0) {
				writer.append("<p>Location: <strong>" + data.getLocation() +"</strong><br/>");
			}
			if(data.getDate()!=null) writer.append("Posted: <strong>" + this.sdf.format(data.getDate().getTime()) +"</strong></p>");
			
			description = this.document.createElement("description");
			descriptionText = this.document.createCDATASection(writer.toString());
			description.appendChild(descriptionText);
			post.appendChild(description);
		}
		if(data.getLink()!=null) {
			link = this.document.createElement("link");
			linkText = this.document.createTextNode(data.getLink());
			link.appendChild(linkText);
			post.appendChild(link);
		}
		
		if(data.getLink()!=null) {
			guid = this.document.createElement("guid");
			guidText = this.document.createTextNode(data.getLink());
			guid.appendChild(guidText);
			post.appendChild(guid);
		}
		
		pubdate = this.document.createElement("pubDate");
		pubdateText = this.document.createTextNode(this.sdf.format(data.getDate().getTime()));
		pubdate.appendChild(pubdateText);
		post.appendChild(pubdate);
		
		
		if(data.getUser()!=null && data.getUser().length() > 0) {
			author = this.document.createElement("media:credit");
			authorText = this.document.createTextNode(data.getUser());
			author.appendChild(authorText);
			post.appendChild(author);
		}
		
		if(data.getThumbnail()!=null) {
			thumbnail = this.document.createElement("media:thumbnail");
			thumbnail.setAttribute("url", data.getThumbnail());
			thumbnail.setAttribute("width", "90");
			thumbnail.setAttribute("height", "120");
			post.appendChild(thumbnail);
		}
		if(data.getHeadline() !=null) {
			mediaTitle = this.document.createElement("media:title");
				mediaTitleText = this.document.createTextNode(data.getHeadline());
			mediaTitle.appendChild(mediaTitleText);
			post.appendChild(mediaTitle);
		}
		return post;

	}
	
	
	/**
     * Generate RSS from multiple PeekData Objects.
   	 * 
   	 * Will read, parse and build RSS string using multiple items, RSS uses Yahoo! Media RSS Module http://search.yahoo.com/mrss/
   	 * 
     * @param	dataIn	the PeekData object you want to build into RSS data,
     * @return the valid JSON String
     * @see Data
     * @see uk.co.mccann.socialpeek.model.PeekData
     * @throws SocialPeekException
     * 
     */
	public String generate(List<Data> dataIn) throws SocialPeekException {
		try {
			
			this.createDocument();
			
			/* create root element, use the yahoo media RSS module */
			this.rootElement = this.document.createElement("rss");
			this.rootElement.setAttribute("xmlns:media", "http://search.yahoo.com/mrss/");
			this.rootElement.setAttribute("version", "2.0");
			
			
			this.document.appendChild(this.rootElement);
			Element channel = this.createChannel();
			
			for(Data data : dataIn) {
				
				Element post = this.createPeekElement(data);
				channel.appendChild(post);
				
			}
			
			this.rootElement.appendChild(channel);
				
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
