package uk.co.mccann.socialpeek.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.SocialPeekConfiguration;
import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.generator.JSONGenerator;
import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.interfaces.Generator;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;
import uk.co.mccann.socialpeek.model.PeekData;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.TwitterService;

public class JSONParserTest {
	
	@Test public void arrayTest() {
		
		List<String> list = new ArrayList<String>();
		list.add("Test1");
		list.add("Test2");
		list.add("Test3");
		
		JSONArray array = new JSONArray(list);
		System.out.println(array.toString());
	
	}
	
	
	@Test public void objectTest() {
		
		List<String> list = new ArrayList<String>();
		list.add("Test1");
		list.add("Test2");
		list.add("Test3");
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray(list);
		try {
			object.put("mctest", array);
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed : " + exp);
		}
		System.out.println(object.toString());
	
	}
	
	@Test public void parseTest() {
		
		Data data = new PeekData();
		data.setBody("body");
		data.setDate(Calendar.getInstance());
		data.setHeadline("headline");
		data.setLink("http://www.google.com");
		data.setUser("some guy");
		data.setThumbnail("http://www.yahoo.com/");
		
		
		
		Generator generator = new JSONGenerator();	
		
		try {
			
			System.out.println(generator.generate(data));
			
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed : " + exp);
		}
		
	}
	
		
	@Test public void parseTestMultiple() {
		
		Data data;
		List<Data> dataArray = new ArrayList<Data>();
		
		for(int x = 0; x < 5; x++) {
			 data = new PeekData();
			data.setBody("body-" + x);
			data.setDate(Calendar.getInstance());
			data.setHeadline("headline-" + x);
			data.setLink("http://www.google.com-" + x);
			data.setUser("some guy-" + x);
			data.setThumbnail("http://www.yahoo.com/-" + x);
			dataArray.add(data);
		}
		
		Generator generator = new JSONGenerator();	
		
		try {
			
			System.out.println(generator.generate(dataArray));
			
		} catch (Exception exp) {
			exp.printStackTrace();
			fail("failed : " + exp);
		}
		
	}
	
	@Test public void fullTestSingle() {
		
		SocialService service = new TwitterService();
		service.setUsername("");
		service.setPassword("");
		
		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_JSON);
		config.registerService(service);
		
		/* set up our main engine */
		SocialPeek socialPeek = new SocialPeek(config);
		PeekFactory peekFactory = socialPeek.getPeekingFactory();
		
		/* start peeking! */
		
		try {
		
			System.out.println(peekFactory.getPeeker(TwitterService.class).getUserPeek("shanman"));
		
		} catch (SocialPeekException e) {
			fail(e.getMessage());
		}
		
	}	
	
	
	
	
	

}
