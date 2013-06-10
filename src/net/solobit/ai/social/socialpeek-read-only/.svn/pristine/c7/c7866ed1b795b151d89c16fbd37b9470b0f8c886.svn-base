package uk.co.mccann.socialpeek.test;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;


import org.junit.Test;
public class DateTest {

		@Test public void dateTest() {
			
			try {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				
				/* try second publication date type */
				System.out.println("date is: " + sdf.parse("2008-04-01T09:06:29+01:00"));	
				
			} catch (Exception exp) {
				exp.printStackTrace();
				fail("failed");
				
			}
			
			
			
		}
	
}
