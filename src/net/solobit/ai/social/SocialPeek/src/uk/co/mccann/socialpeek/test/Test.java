package uk.co.mccann.socialpeek.test;

import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import uk.co.mccann.socialpeek.SocialPeek;
import uk.co.mccann.socialpeek.SocialPeekConfiguration;
import uk.co.mccann.socialpeek.exceptions.SocialPeekException;
import uk.co.mccann.socialpeek.interfaces.PeekFactory;
import uk.co.mccann.socialpeek.model.SocialService;
import uk.co.mccann.socialpeek.service.AbstractSocialService;
import uk.co.mccann.socialpeek.service.TwitterService;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		SocialService service = new TwitterService();
		service.setUsername("");
		service.setPassword("");
		
		SocialPeekConfiguration config = new SocialPeekConfiguration();
		config.setFeedType(SocialPeek.RETURN_XML);
		config.registerService(service);
		
		SocialPeek socialPeek = new SocialPeek(config);
		SocialPeekFactory peekFactory = socialPeek.getPeekingFactory();
		
		
		try {
			
			String xml = peekFactory.getPeeker(TwitterService.class).getRandomPeek();
			System.out.println(xml);
			
			//File out = new File();
			FileWriter writer = new FileWriter("/tmp/out.xml");
			writer.write(xml);
			writer.flush();
			writer.close();
			
		
		} catch (SocialPeekException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			 fail(e.getMessage());
		}
		*/
		
		//byte[] utfBytes = new String("gammon").getBytes();
		byte[] utfBytes = new String("うごきゃーいいんだよ。動けば").getBytes();
		
		
		
		try {
			
			/*
			//System.out.println(new String(utfBytes,"UTF-16"));
			Data data = new PeekData();
			data.setBody("うごきゃーいいんだよ。動けば");
			data.setDate(Calendar.getInstance());
			data.setHeadline("うごきゃーいいんだよ。動けば");
			data.setLink("http://www.google.com");
			data.setUser("some guy");
			data.setUserProfilePhoto("http://www.yahoo.com/");
			Generator generator = new XMLGenerator();	
			
			String xml = generator.generate(data);
			System.out.println(xml);
			*/
			
			SocialService service = new TwitterService();
			service.setUsername("");
			service.setPassword("");
			
			SocialPeekConfiguration config = new SocialPeekConfiguration();
			config.setFeedType(SocialPeek.RETURN_XML);
			config.registerService(service);
			
			SocialPeek socialPeek = new SocialPeek(config);
			PeekFactory peekFactory = socialPeek.getPeekingFactory();
			
			String xml = peekFactory.getPeeker(AbstractSocialService.class).getRandomPeek(10);
			
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/Users/david/out-file.xml",true),"UTF-8");
			
			osw.write(xml);
			
			java.util.Locale[] locales = { new java.util.Locale("en", "US"), new java.util.Locale("ja","JP"), new java.util.Locale("ko","KR"), new java.util.Locale("es", "ES"),
					new java.util.Locale("it", "IT") };
					for (int x=0; x< locales.length; ++x) {
					String displayLanguage = locales[x].getDisplayLanguage(locales[x]);
					//osw.write(locales[x].toString() + ": " + displayLanguage + " : " + data.getBody() + "\n");
					
					//System.out.println();
					}
			
					osw.close();
			
		} catch (UnsupportedEncodingException e) {
			fail(e.getMessage());
		} catch (SocialPeekException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			 fail(e.getMessage());
		}
		

	}

}
