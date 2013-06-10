package winterwell.jtwitter;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import winterwell.jtwitter.Twitter.Message;
import winterwell.jtwitter.Status;
import winterwell.jtwitter.User;

/**
 * Unit tests for JTwitter.
 * These only provide partial testing -- sorry.
 * 
 *
 * @author daniel
 */
public class TwitterTest
//extends TestCase // Commented out to remove the JUnit dependency
{

	public static void main(String[] args) {
		TwitterTest tt = new TwitterTest();
		Method[] meths = TwitterTest.class.getMethods();
		for(Method m : meths) {
			if ( ! m.getName().startsWith("test")
				|| m.getParameterTypes().length != 0) continue;
			try {
				m.invoke(tt);
				System.out.println(m.getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				System.out.println("TEST FAILED: "+m.getName());
				System.out.println("\t"+e.getCause());
			}
		}
	}


	/**
	 * Test method for {@link thinktank.twitter.Twitter#updateStatus(java.lang.String)}.
	 */
	public void testUpdateStatus() {
		Twitter tw = new Twitter("jtwit", "password");
		String s = "Experimenting (http://winterwell.com at "+new Date().toString()+")";
		Status s2a = tw.updateStatus(s);
		Status s2b = tw.getStatus();
		assert s2b.text.equals(s) : s2b.text;
		assert s2a.id == s2b.id;
		// Now test delete
		tw.destroyStatus(s2a.id);
		Status s1 = tw.getStatus();
		assert s1.id < s2a.id;
	}

	public void testGetRateLimitStats() {
		Twitter tw = new Twitter("jtwit", "password");
		int i = tw.getRateLimitStatus();
		if (i<1) return;
		tw.setStatus("One less");
		int i2 = tw.getRateLimitStatus();
		assert i - 1 == i2;
	}
	
	public void testDestroyStatusBad() {
		Twitter tw = new Twitter("jtwit", "password");
		Status hs = tw.getStatus("winterstein");
		try {
			tw.destroyStatus(hs);
			assert false;
		} catch (Exception ex) {
			// OK
		}
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getPublicTimeline()}.
	 */
	public void testGetPublicTimeline() {
		Twitter tw = new Twitter(null, null);
		List<Status> pt = tw.getPublicTimeline();
		assert pt.size() > 5;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getFriendsTimeline()}.
	 */
	public void testGetFriendsTimeline() {
		Twitter tw = new Twitter("jtwit", "password");
		List<Status> ft = tw.getFriendsTimeline();
		assert ft.size() > 0;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getFriendsTimeline(java.lang.String, java.util.Date)}.
	 */
	public void testGetFriendsTimelineStringDate() {
		Twitter tw = new Twitter("jtwit", "password");
		List<Status> ft = tw.getFriendsTimeline("winterstein", Twitter.getDate(2007, "JUNE",19));
		System.out.println(ft);
		assert ft.size() > 0;
	}


	/**
	 * Test method for {@link thinktank.twitter.Twitter#getUserTimeline()}.
	 */
	public void testGetUserTimeline() {
		Twitter tw = new Twitter("jtwit", "password");
		List<Status> ut = tw.getUserTimeline();
		assert ut.size() > 0;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getUserTimeline(java.lang.String, java.lang.Integer, java.util.Date)}.
	 */
	public void testGetUserTimelineStringIntegerDate() {
		Twitter tw = new Twitter("jtwit", "password");
		List<Status> me3 = tw.getUserTimeline(null, 3, null);
		List<Status> n3 = tw.getUserTimeline("narrator", 3, null);
		assert n3.size() == 3;
		List<Status> meNone = tw.getUserTimeline(null, 10, new Date());
		assert meNone.size() == 0;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getStatus(int)}.
	 */
	public void testGetStatusInt() {
		Twitter tw = new Twitter("jtwit", "password");
		Status s = tw.getStatus();
		Status s2 = tw.getStatus(s.id);
		assert s2.text.equals(s.text);
	}
	/**
	 * Test method for {@link thinktank.twitter.Twitter#getStatus(int)}.
	 */
	public void testGetStatus() {
		Twitter tw = new Twitter("jtwit","password");
		Status s = tw.getStatus();
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getReplies()}.
	 */
	public void testGetReplies() {
		Twitter tw = new Twitter("jtwit","password");
		List<Message> r = tw.getReplies();
		System.out.println("Replies "+r);
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getFriends()}.
	 */
	public void testGetFriends() {
		Twitter tw = new Twitter("jtwit", "password");
		List<User> friends = tw.getFriends();
		assert friends != null;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getFriends(java.lang.String)}.
	 */
	public void testGetFriendsString() {
		Twitter tw = new Twitter("jtwit", "password");
		List<User> friends = tw.getFriends("winterstein");
		assert friends != null;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getFollowers()}.
	 */
	public void testGetFollowers() {
		Twitter tw = new Twitter("jtwit", "password");
		List<User> f = tw.getFollowers();
		assert f.size() > 0;
		assert Twitter.getUser("winterstein", f) != null;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getFeatured()}.
	 */
	public void testGetFeatured() {
		Twitter tw = new Twitter("jtwit", "password");
		List<User> f = tw.getFeatured();
		assert f.size() > 0;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#show(java.lang.String)}.
	 */
	public void testShow() {
		Twitter tw = new Twitter("jtwit", "password");
		User show = tw.show("jtwit");
		assert show != null;
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getDirectMessages()}.
	 */
	public void testGetDirectMessages() {
		Twitter tw = new Twitter("jtwit", "password");
		List<Message> msgs = tw.getDirectMessages();
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#getDirectMessages(java.util.Date)}.
	 */
	public void testGetDirectMessagesDate() {
		Twitter tw = new Twitter("jtwit", "password");
		List<Message> dm = tw.getDirectMessages(new Date());
		System.out.println(""+dm);
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#sendMessage(java.lang.String, java.lang.String)}.
	 */
	public void testSendMessage() {
		Twitter tw = new Twitter("jtwit", "password");
		Message sent = tw.sendMessage("winterstein", "Please ignore this message");
		System.out.println(""+sent);
	}

	/**
	 * Test method for {@link thinktank.twitter.Twitter#befriend(java.lang.String)}.
	 */
	public void testBefriend() {
		Twitter tw = new Twitter("jtwit", "password");
		List<User> friends = tw.getFriends();
		if (Twitter.getUser("winterstein", friends)==null) tw.befriend("winterstein");
		friends = tw.getFriends();
		User winterstein = Twitter.getUser("winterstein", friends);
		assert winterstein != null;

		User h = tw.breakFriendship("winterstein");

		friends = tw.getFriends();
		winterstein = Twitter.getUser("winterstein", friends);
		assert winterstein == null;

		tw.befriend("winterstein");

		friends = tw.getFriends();
		winterstein = Twitter.getUser("winterstein", friends);
		assert winterstein != null;
		System.out.println(h.screenName);
	}


}
