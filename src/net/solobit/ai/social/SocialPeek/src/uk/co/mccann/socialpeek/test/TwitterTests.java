package uk.co.mccann.socialpeek.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.SocialPeekConfiguration;
import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.TwitterService;


public class TwitterTests {
	
	/*
	@Test public void singlePeek() {
		
		SocialService service = new TwitterService();
		
		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_RSS);
		config.registerService(service);
		
		SocialPeek socialPeek = new SocialPeek(config);
		PeekFactory peekFactory = socialPeek.getPeekingFactory();
		
		
		try {
			String[] keywords = new String[]{"hot","sick","anger","social","thinking","sex","hungry","good","happy"};
		
			System.out.println(peekFactory.getPeeker(TwitterService.class).getRandomPeekUsingTags(keywords));
		
		} catch (SocialPeekException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}
	@Test public void userPeek() {
		
		SocialService service = new TwitterService();
		service.setUsername("");
		service.setPassword("");
		
		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_RSS);
		config.registerService(service);
		
		SocialPeek socialPeek = new SocialPeek(config);
		PeekFactory peekFactory = socialPeek.getPeekingFactory();
		
		
		try {
		
			System.out.println(peekFactory.getPeeker(TwitterService.class).getRandomPeek(5));
		
		} catch (SocialPeekException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	*/
	
	@Test public void search() {
		
		SocialService service = new TwitterService();
		service.setUsername("");
		service.setPassword("");
		SocialPeek.logging = true;
		
		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_XML);
		config.registerService(service);
		
		SocialPeek socialPeek = new SocialPeek(config);
		
		PeekFactory peekFactory = socialPeek.getPeekingFactory();
		
		
		try {
		
			System.out.println(peekFactory.getPeeker(TwitterService.class).getRandomPeekUsingTag("puppy"));
		
		} catch (SocialPeekException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	
}
