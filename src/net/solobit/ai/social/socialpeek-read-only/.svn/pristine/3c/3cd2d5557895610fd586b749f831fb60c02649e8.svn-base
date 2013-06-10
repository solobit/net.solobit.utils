package uk.co.mccann.socialpeek.test;

import org.junit.Test;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.SocialPeekConfiguration;
import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.DeliciousService;
import uk.co.mccann.socialpeek.service.TwitterService;
import uk.co.mccann.socialpeek.service.WeFeelFineService;
import static org.junit.Assert.fail;


public class WWFTests {
	@Test public void singlePeekTest() {
		
		SocialService service = new WeFeelFineService();
		service.setUsername("empty");
		service.setPassword("empty");
		
		
		SocialService tservice = new TwitterService();
		tservice.setUsername("");
		tservice.setPassword("");
		
		SocialService dservice = new DeliciousService();
		dservice.setUsername("test");
		dservice.setPassword("test");
		
		
		
		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_RSS);
		config.registerService(service);
		config.registerService(tservice);
		config.registerService(dservice);
		
		
		/* set up our main engine */
		SocialPeek socialPeek = new SocialPeek(config);
		PeekFactory peekFactory = socialPeek.getPeekingFactory();
		
		/* start peeking! */
		
		try {
			
			String data = peekFactory.getPeeker(WeFeelFineService.class).getMultiplePeekUsingTags(new String[] {"erotic","drunk"},10);
			//String data = peekFactory.getPeeker(TwitterService.class).getRandomPeek(50);
			//String data = peekFactory.getPeeker(DeliciousService.class).getMultiplePeekUsingTag("thereformed",20);
			
			
			//FileWriter writer = new FileWriter(new File("rssdata/wefeelfine.rss.xml"));
			//File file = new File("/usr/local/apache2/htdocs/socialpeek/feed.rss");
			//file.delete();
		
			//OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/usr/local/apache2/htdocs/socialpeek/feed.rss",true),"UTF-8");
			
			//osw.write(data);
			//osw.close();
			System.out.println(data);
			
		} catch (SocialPeekException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} 
		
	}
}
