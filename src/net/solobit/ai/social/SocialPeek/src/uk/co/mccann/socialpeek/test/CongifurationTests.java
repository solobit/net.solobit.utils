package uk.co.mccann.socialpeek.test;

import org.junit.Before;
import org.junit.Test;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.SocialPeekConfiguration;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.TwitterService;

public class CongifurationTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test public void config() {
		
		SocialService service = new TwitterService();
		service.setUsername("");
		service.setPassword("");
		
		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_XML);
		config.registerService(service);
		
		/* set up our main engine */
		//SocialPeek socialPeek = new SocialPeek(config);
		//PeekFactory peekFactory = socialPeek.getPeekingFactory();
		
	}
	
}
