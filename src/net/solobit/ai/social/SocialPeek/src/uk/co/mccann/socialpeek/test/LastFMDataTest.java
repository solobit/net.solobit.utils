package uk.co.mccann.socialpeek.test;
import static org.junit.Assert.fail;

import org.junit.Test;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.SocialPeekConfiguration;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.LastFMService;

public class LastFMDataTest {
	
	@Test public void singleTest() {
		try {
			
			SocialService service = new LastFMService();
			
			SocialPeekConfiguration config = new SocialPeekConfiguration();
			config.setFeedType(SocialPeek.RETURN_RSS);
			
			config.registerService(service);
			
			SocialPeek socialPeek = new SocialPeek(config);
			SocialPeek.logging = true;
			
			PeekFactory peekFactory = socialPeek.getPeekingFactory();
		
			String data = peekFactory.getPeeker(LastFMService.class).getRandomPeek();
			
			System.out.print(data);
			
			
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed");
		}
	}

}
