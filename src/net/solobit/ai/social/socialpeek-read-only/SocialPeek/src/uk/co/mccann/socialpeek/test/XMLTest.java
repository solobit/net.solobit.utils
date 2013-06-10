package uk.co.mccann.socialpeek.test;


import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.generator.XMLGenerator;
import uk.co.mccann.socialpeek.interfaces.Data;
import uk.co.mccann.socialpeek.interfaces.Generator;
import uk.co.mccann.socialpeek.model.PeekData;


public class XMLTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test public void xmlTest() {
		
		Data data = new PeekData();
		data.setBody(" 頼れる仲間はみんな目が死んでる");
		data.setDate(Calendar.getInstance());
		data.setHeadline("頼れる仲間はみんな目が死んでる");
		data.setLink("http://www.google.com");
		data.setUser("some guy");
		data.setThumbnail("http://www.yahoo.com/");
		Generator generator = new XMLGenerator();
		
		try {
			
			String xml = generator.generate(data);
			System.out.println(xml);
			
			
		
		} catch (SocialPeekException e) {
			fail(e.getMessage());
		}		
	    
	}
	

}
