package uk.co.mccann.socialpeek.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.SocialPeekConfiguration;
import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.LiveService;

public class LiveTest2 {

	@Test public void search() {

		SocialService service = new LiveService();
		SocialPeek.logging = true;

		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_XML);
		config.registerService(service);

		SocialPeek socialPeek = new SocialPeek(config);

		PeekFactory peekFactory = socialPeek.getPeekingFactory();

		try {
			String[] tags = {"loreal"};
			System.out.println(peekFactory.getPeeker(LiveService.class).getMultiplePeekUsingTags(tags,1));

		} catch (SocialPeekException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}