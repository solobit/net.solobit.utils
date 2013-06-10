package uk.co.mccann.socialpeek.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Test;

import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.generator.RSSGenerator;
import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.interfaces.Generator;
import uk.co.mccann.socialpeek.model.PeekData;


public class RSSTest extends TestCase{
	
	@Test public void testParser() {
		
		try {
			Data data = new PeekData();
			data.setBody("Gibby poo");
			data.setDate(Calendar.getInstance());
			data.setHeadline("mincer");
			data.setLink("http://www.google.com");
			data.setUser("some guy");
			data.setThumbnail("http://www.yahoo.com/");
			Generator generator = new RSSGenerator();	
			System.out.println(generator.generate(data));
		} catch (SocialPeekException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed: " + e.getMessage());
		}
		
		
		
	}

}
