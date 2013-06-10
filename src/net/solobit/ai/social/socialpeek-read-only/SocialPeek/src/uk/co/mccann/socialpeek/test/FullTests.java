package uk.co.mccann.socialpeek.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.junit.Test;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.SocialPeekConfiguration;
import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.DeliciousService;
import uk.co.mccann.socialpeek.service.LastFMService;
import uk.co.mccann.socialpeek.service.TechnoratiService;
import uk.co.mccann.socialpeek.service.TwitterService;
import uk.co.mccann.socialpeek.service.WeFeelFineService;


public class FullTests {

//	@Test public void singlePeekTest() {
//		
//		SocialService service = new WeFeelFineService();
//		
//		
//		SocialService tservice = new TwitterService();
//		tservice.setUsername("");
//		tservice.setPassword("");
//		
//		SocialService dservice = new DeliciousService();
//		
//		SocialService nservice = new TechnoratiService();
//		
//		
//		
//		SocialPeekConfiguration config = new SocialPeekConfiguration();
//		config.setFeedType(SocialPeek.RETURN_RSS);
//		config.registerService(service);
//		config.registerService(tservice);
//		config.registerService(dservice);
//		config.registerService(nservice);
//		
//		
//		
//		SocialPeek socialPeek = new SocialPeek(config);
//		PeekFactory peekFactory = socialPeek.getPeekingFactory();
//		
//		
//		try {
//			
//			String data = peekFactory.getPeeker(TechnoratiService.class).getMultiplePeekUsingTag("art",20);
//			//String data = peekFactory.getPeeker(TwitterService.class).getRandomPeek(50);
//			//String data = peekFactory.getPeeker(DeliciousService.class).getMultiplePeekUsingTag("thereformed",20);
//			
//			
//			//FileWriter writer = new FileWriter(new File("rssdata/wefeelfine.rss.xml"));
//			File file = new File("/usr/local/apache2/htdocs/socialpeek/feed.rss");
//			file.delete();
//		
//			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/usr/local/apache2/htdocs/socialpeek/feed.rss",true),"UTF-8");
//			
//			//osw.write(data);
//			osw.close();
//			//System.out.println(data);
//			
//		} catch (SocialPeekException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//		
//	}
	
	
	@Test public void multipleRandomPeekTest() {
		
		SocialService service = new WeFeelFineService();
		
		
		SocialService tservice = new TwitterService();
		tservice.setUsername("");
		tservice.setPassword("");
		
		SocialService dservice = new DeliciousService();
		SocialService nservice = new TechnoratiService();
		SocialService gservice = new LastFMService();
		
		
		
		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_RSS);
		//config.registerService(service);
		config.registerService(tservice);
		//config.registerService(dservice);
		//config.registerService(nservice);
		//config.registerService(gservice);
		
		
		
	
		/* set up our main engine */
		SocialPeek socialPeek = new SocialPeek(config);
		PeekFactory peekFactory = socialPeek.getPeekingFactory();
		
		/* start peeking! */
		
		try {
			
			/* completely random peek */
			
			String data = peekFactory.getPeeker().getMultiplePeekUsingTags(new String[] { "rotten"}, 5);
			List<Data> dataList = peekFactory.getPeeker().getRawMultiplePeekUsingTags(new String[]{"disgust"}, 5);
			
			
			
			File file = new File("/usr/local/apache2/htdocs/socialpeek/feed.rss");
			file.delete();
		
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/usr/local/apache2/htdocs/socialpeek/feed.rss",true),"UTF-8");
			
			osw.write(data);
			osw.close();
			System.out.println(dataList);
			
		} catch (SocialPeekException e) {
		
			e.printStackTrace();
			fail(e.getMessage());
		
		} catch (IOException e) {
		
			e.printStackTrace();
			fail(e.getMessage());
		
		}
		
	}
}
