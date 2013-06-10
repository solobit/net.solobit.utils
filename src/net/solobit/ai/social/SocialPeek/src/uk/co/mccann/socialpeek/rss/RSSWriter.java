package uk.co.mccann.socialpeek.rss;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import com.sun.cnpi.rss.elements.Author;
import com.sun.cnpi.rss.elements.BasicElement;
import com.sun.cnpi.rss.elements.Category;
import com.sun.cnpi.rss.elements.Channel;
import com.sun.cnpi.rss.elements.Comments;
import com.sun.cnpi.rss.elements.Description;
import com.sun.cnpi.rss.elements.Enclosure;
import com.sun.cnpi.rss.elements.Guid;
import com.sun.cnpi.rss.elements.Image;
import com.sun.cnpi.rss.elements.Item;
import com.sun.cnpi.rss.elements.Link;
import com.sun.cnpi.rss.elements.PubDate;
import com.sun.cnpi.rss.elements.Source;
import com.sun.cnpi.rss.elements.Title;


/**
 *  Provides methods to take an RSS object and write it to XML.
 * 
 * <h4>Copyright and License</h4>
 * This code is copyright (c) McCann Erickson Advertising Ltd, 2008 except where
 * otherwise stated. It is released as
 * open-source under the Creative Commons NC-SA license. See
 * <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/">http://creativecommons.org/licenses/by-nc-sa/2.5/</a>
 * for license details. This code comes with no warranty or support.
 * 
 * @author Lewis Taylor <lewis.taylor@europe.mccann.com>
 */
public class RSSWriter {

	private DocumentBuilderFactory dbf;
	private DocumentBuilder docBuilder;
	private Document document;
	private Element rootElement;

	/**
	 * Sets the writer up in preparation for XML write
	 */
	private void setupWriter(){
		/* create instance of document builder factory */
		dbf = DocumentBuilderFactory.newInstance();

		/* create a builder */
		try {
			docBuilder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* create new DOM document */
		document = docBuilder.newDocument();
	}

	
	/**
	 * Creates an XML document from an RSS Channel and writes
	 * it to file
	 * 
	 * @param file - the file to write the XML to
	 * @param channelFeed - the RSS channel to write to file
	 */
	public void structureRSS(File file, Channel channelFeed){

		setupWriter();

		// <rss>
		this.rootElement = this.document.createElement("rss");
		this.rootElement.setAttribute("version", "2.0");
		document.appendChild(rootElement);

		// <channel>
		Element channel = document.createElement("channel");
		rootElement.appendChild(channel);

		// Channel Elements
		BasicElement title, link, description, language, copyright, managingEditor, webMaster, pubDate, lastBuildDate,
		generator, docs, cloud, ttl, rating, textInput, skipHours, skipDays;

		List<BasicElement> categories;
		List<Item> items;

		Image image;

		title = channelFeed.getTitle();
		link = channelFeed.getLink();
		description = channelFeed.getDescription();
		language = channelFeed.getLanguage();
		copyright = channelFeed.getCopyright();
		managingEditor = channelFeed.getManagingEditor();
		webMaster = channelFeed.getWebMaster();
		pubDate = channelFeed.getPubDate();
		lastBuildDate = channelFeed.getLastBuildDate();
		categories = (List<BasicElement>) channelFeed.getCategories();
		generator = channelFeed.getGenerator();
		docs = channelFeed.getDocs();
		cloud = channelFeed.getCloud();
		ttl = channelFeed.getTtl();
		image = channelFeed.getImage();
		rating =channelFeed.getRating();
		textInput = channelFeed.getTextInput();
		skipHours = channelFeed.getSkipHours();
		skipDays = channelFeed.getSkipDays();
		items = (List<Item>) channelFeed.getItems();

		// Required Elements
		Element titleNode = document.createElement("title");
		titleNode.setTextContent(title.getText());
		channel.appendChild(titleNode);

		Element linkNode = document.createElement("link");
		linkNode.setTextContent(link.getText());
		channel.appendChild(linkNode);

		Element descriptionNode = document.createElement("description");
		descriptionNode.setTextContent(description.getText());
		channel.appendChild(descriptionNode);

		// Optional Elements
		if (language!=null){
			Element languageNode = document.createElement("language");
			languageNode.setTextContent(language.getText());
			channel.appendChild(languageNode);
		}

		if (copyright!=null){
			Element copyrightNode = document.createElement("copyright");
			copyrightNode.setTextContent(copyright.getText());
			channel.appendChild(copyrightNode);
		}

		if (managingEditor!=null){
			Element managingEditorNode = document.createElement("managingEditor");
			managingEditorNode.setTextContent(managingEditor.getText());
			channel.appendChild(managingEditorNode);
		}

		if (webMaster!=null){
			Element webMasterNode = document.createElement("webMaster");
			webMasterNode.setTextContent(webMaster.getText());
			channel.appendChild(webMasterNode);
		}

		if (pubDate!=null){
			Element pubDateNode = document.createElement("pubDate");
			pubDateNode.setTextContent(pubDate.getText());
			channel.appendChild(pubDateNode);
		}

		if (lastBuildDate!=null){
			Element lastBuildDateNode = document.createElement("lastBuildDate");
			lastBuildDateNode.setTextContent(lastBuildDate.getText());
			channel.appendChild(lastBuildDateNode);
		}

		if (categories!=null && categories.size()>0){
			for (BasicElement category : categories){
				Element categoryNode = document.createElement("category");
				categoryNode.setTextContent(category.getText());

				String domain = category.getAttribute("domain");
				if (domain!=null)
					categoryNode.setAttribute("domain", domain);
				channel.appendChild(categoryNode);
			}
		}

		if (generator!=null){
			Element generatorNode = document.createElement("generator");
			generatorNode.setTextContent(generator.getText());
			channel.appendChild(generatorNode);
		}

		if (docs!=null){
			Element docsNode = document.createElement("docs");
			docsNode.setTextContent(docs.getText());
			channel.appendChild(docsNode);
		}

		if (cloud!=null){

			Element cloudNode = document.createElement("cloud");
			cloudNode.setTextContent(cloud.getText());

			String domain, port, path, registerProcedure, protocol;

			if ((domain = cloud.getAttribute("domain"))!=null)
				cloudNode.setAttribute("domain", domain);
			if ((port = cloud.getAttribute("port"))!=null)
				cloudNode.setAttribute("port", port);
			if ((path = cloud.getAttribute("path"))!=null)
				cloudNode.setAttribute("path", path);
			if ((registerProcedure = cloud.getAttribute("registerProcedure"))!=null)
				cloudNode.setAttribute("registerProcedure", registerProcedure);
			if ((protocol = cloud.getAttribute("protocol"))!=null)
				cloudNode.setAttribute("protocol", protocol);

			channel.appendChild(cloudNode);
		}


		// TODO sort ttl problem
		if (ttl!=null){
			Element ttlNode = document.createElement("ttl");
			ttlNode.setTextContent(ttl.getText());
			channel.appendChild(ttlNode);
		}


		if (image!=null){
			Element imageNode = document.createElement("image");
			//			imageNode.setTextContent(image.getText());

			// Subelements

			BasicElement imageUrl, imageTitle, imageLink, imageWidth, imageHeight, imageDescription;

			imageUrl = image.getUrl();
			imageTitle = image.getTitle();
			imageLink = image.getLink();

			Element imageUrlNode = document.createElement("url");
			imageUrlNode.setTextContent(imageUrl.getText());
			imageNode.appendChild(imageUrlNode);

			Element imageTitleNode = document.createElement("title");
			imageTitleNode.setTextContent(imageTitle.getText());
			imageNode.appendChild(imageTitleNode);

			Element imageLinkNode = document.createElement("link");
			imageLinkNode.setTextContent(imageLink.getText());
			imageNode.appendChild(imageLinkNode);


			// TODO problems with optional tags

			if ((imageWidth = image.getWidth())!=null){
				Element imageWidthNode = document.createElement("width");
				imageWidthNode.setTextContent(imageWidth.getText());
				imageNode.appendChild(imageWidthNode);
			}

			if ((imageHeight = image.getHeight())!=null){
				Element imageHeightNode = document.createElement("height");
				imageHeightNode.setTextContent(imageHeight.getText());
				imageNode.appendChild(imageHeightNode);
			}

			if ((imageDescription = image.getDescription())!=null){
				Element imageDescriptionNode = document.createElement("description");
				imageDescriptionNode.setTextContent(imageDescription.getText());
				imageNode.appendChild(imageDescriptionNode);
			}

			channel.appendChild(imageNode);
		}


		if (rating!=null){
			Element ratingNode = document.createElement("rating");
			ratingNode.setTextContent(rating.getText());
			channel.appendChild(ratingNode);
		}

		if (textInput!=null){
			Element textInputNode = document.createElement("textInput");
			//textInputNode.setTextContent(textInput.getText());
			channel.appendChild(textInputNode);

			// Subelements

			// TODO More problems with RSSParser
			//			BasicElement textTitle, textDescription, textName, textLink;
			//
			//			imageUrl = textInput
			//			imageTitle = image.getTitle();
			//			imageLink = image.getLink();
		}

		if (skipHours!=null){
			Element skipHoursNode = document.createElement("skipHours");
			channel.appendChild(skipHoursNode);

			String[] hours = skipHours.getText().split("\\s+");


			for (int i = 1; i < hours.length; i++){
				Element hourNode = document.createElement("hour");
				hourNode.setTextContent(hours[i]);
				skipHoursNode.appendChild(hourNode);
			}
		}

		if (skipDays!=null){
			Element skipDaysNode = document.createElement("skipDays");
			channel.appendChild(skipDaysNode);

			String[] days = skipDays.getText().split("\\s+");

			for (int i = 1; i < days.length; i++){
				Element dayNode = document.createElement("day");
				dayNode.setTextContent(days[i]);
				skipDaysNode.appendChild(dayNode);
			}
		}

		// Structure Items
		structureItems(channel, items);
		formDocument();
	}


	/**
	 * Structures the list of items into an XML element and appends
	 * them to the given channel element
	 * 
	 * @param channel - the channel element to append the items to
	 * @param items - the list of items to structure
	 */
	public Element structureItems(Element channel, List<Item> items){

		for (Item item : items){

			Element itemNode = document.createElement("item");

			Title title = item.getTitle();
			Link link = item.getLink();
			Description description = item.getDescription();
			Author author = item.getAuthor();
			Comments comments = item.getComments();
			Enclosure enclosure = item.getEnclosure();
			Guid guid = item.getGuid();
			PubDate pubDate = item.getPubDate();
			Source source = item.getSource();
			List<Category> categories = (List<Category>) item.getCategories();

			if (title!=null){
				Element titleNode = document.createElement("title");
				titleNode.setTextContent(title.getText());
				itemNode.appendChild(titleNode);
			}

			if (link!=null){
				Element linkNode = document.createElement("link");
				linkNode.setTextContent(link.getText());
				itemNode.appendChild(linkNode);
			}

			if (description!=null){
				Element descriptionNode = document.createElement("description");
				descriptionNode.setTextContent(description.getText());
				itemNode.appendChild(descriptionNode);
			}

			if (author!=null){
				Element authorNode = document.createElement("author");
				authorNode.setTextContent(author.getText());
				itemNode.appendChild(authorNode);
			}

			if (categories!=null && categories.size()>0){
				for (Category category : categories){
					Element categoryNode = document.createElement("category");
					categoryNode.setTextContent(category.getText());

					String domain = category.getAttribute("domain");
					if (domain!=null)
						categoryNode.setAttribute("domain", domain);
					itemNode.appendChild(categoryNode);
				}
			}

			if (comments!=null){
				Element commentsNode = document.createElement("comments");
				commentsNode.setTextContent(comments.getText());
				itemNode.appendChild(commentsNode);
			}

			if (enclosure!=null){
				Element enclosureNode = document.createElement("enclosure");
				itemNode.appendChild(enclosureNode);

				String url = enclosure.getAttribute("url");
				String length = enclosure.getAttribute("length");
				String type = enclosure.getAttribute("type");

				enclosureNode.setAttribute("url", url);
				enclosureNode.setAttribute("length", length);
				enclosureNode.setAttribute("type", type);
			}

			if (guid!=null){
				Element guidNode = document.createElement("guid");
				guidNode.setTextContent(guid.getText());
				itemNode.appendChild(guidNode);
			}

			if (pubDate!=null){
				Element pubDateNode = document.createElement("pubDate");
				pubDateNode.setTextContent(pubDate.getText());
				itemNode.appendChild(pubDateNode);
			}

			if (source!=null){
				Element sourceNode = document.createElement("source");
				sourceNode.setTextContent(source.getText());
				itemNode.appendChild(sourceNode);
			}

			channel.appendChild(itemNode);
		}
		return channel;
	}

	/**
	 * Writes the document to file
	 */
	public void formDocument(){
		/* create a DOM implementation */
		DOMImplementation impl = this.document.getImplementation();
		DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS","3.0");

		/* create a writer and tell it to format the output so it's pretty! */
		LSSerializer writer = implLS.createLSSerializer();
		writer.getDomConfig().setParameter("format-pretty-print", true);
		writer.getDomConfig().setParameter("xml-declaration", false);

		/* create a serialized version of the document */ 
		String xmlSerialized = writer.writeToString(this.document.getDocumentElement());

		System.out.println(xmlSerialized);
	}

}
