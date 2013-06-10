package winterwell.jtwitter;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Java wrapper for the Twitter API v0.9.3
 * <p>
 * Example usage:
<code><pre>
	// Make a Twitter object
	Twitter twitter = new Twitter("my-name","my-password");
	// Print Winterstein's status
	System.out.println(twitter.getStatus("winterstein"));
	// Set my status
	twitter.updateStatus("Messing about in Java");
</pre></code>
 * <p>
 * See {@linkplain http://thinktankmaths.co.uk/jtwitter} for more information
 * about this wrapper.
 * See
 * {@linkplain http://groups.google.com/group/twitter-development-talk/web/api-documentation}
 * for more information about the Twitter API.
 * <p>
 * Notes:
 * <ul>
 * <li> This wrapper takes care of all url-encoding/decoding.
 * <li> This wrapper will throw a runtime exception (TwitterException)
 *  if a methods fails,
 * e.g. it cannot connect to Twitter.com or you make a bad request.
 * I would like to improve error-handling, and welcome suggestions on
 * cases where more informative exceptions would be helpful.
 * </ul>
 *
 * <h4>Copyright and License</h4>
 * This code is copyright (c) ThinkTank Mathematics Ltd, 2007 except where
 * otherwise stated. It is released as
 * open-source under the LGPL license. See
 * <a href="http://www.gnu.org/licenses/lgpl.html">http://www.gnu.org/licenses/lgpl.html</a>
 * for license details. This code comes with no warranty or support.
 *
 * <h4>Change list</h4>
 * <p><i>0.9.4</i><br>
 *  - splitMessage() breaks a long string into tweet-size sections.
 * </p>
 * <p><i>0.9.3</i><br>
 *  - getDirectMessages() now retrieves all messages, not just the first 20. Thanks to Steve Jensen
 *  for spotting the bug and writing the fix.<br>
 *  - Added getRecentDirectMessages() which retrieves only the latest 20 messages.<br>
 *  - Added getDirectMessages(long sinceId) at Steve's request.
 *  </p>
 * <p><i>0.9.2</i><br>
 *  - Made Java 5 compatible.<br>
 *  - Added source field in Twitter.Status<br>
 *  - Made {@link #getUserTimeline()} authenticate.<br>
 *  Thanks to <i>Sheila Colemam</i> for suggesting these fixes.
 *  </p>
 * <p><i>0.9.1</i><br>
 * - Added Message object, changed getDirectMessages() and getReplies()
 * to use this.<br>
 * - Added {@link #destroyStatus(int)}.<br>
 * - Added {@link #destroyStatus(Status)}.<br>
 * - Fixed bug in {@link #getStatus(String)}.<br>
 * - Changed {@link #updateStatus(String)} to return a Status object.<br>
 * </p>
 *
 * @author Daniel Winterstein
 */
public class Twitter {

	
	/**
	 * Split a long message up into shorter chunks suitable for use with {@link #setStatus(String)} or
	 * {@link #sendMessage(String, String)}.
	 * @param longStatus
	 * @return longStatus broken into a list of max 140 char strings
	 */
	public List<String> splitMessage(String longStatus) {
		// Is it really long?
		if (longStatus.length() <= 140) return Collections.singletonList(longStatus);	
		// Multiple tweets for a longer post
		List<String> sections = new ArrayList<String>(4);
		StringBuilder tweet = new StringBuilder(140);
		String[] words = longStatus.split("\\s+");
		for (String w : words) {
			// messages have a max length of 140
			// plus the last bit of a long tweet tends to be hidden on twitter.com, so best to chop 'em short too
			if (tweet.length() + w.length() + 1 > 140) { 
				// Emit
				tweet.append("...");
				sections.add(tweet.toString());
				tweet = new StringBuilder(140);
				tweet.append(w);
			} else {
				if (tweet.length() != 0) tweet.append(" ");
				tweet.append(w);
			}
		}
		// Final bit
		if (tweet.length() != 0) sections.add(tweet.toString());
		return sections;
	}

	/**
	 * A Twitter message, either a direct (private) message or a public
	 * \@you message. Fields are null if unset.
	 */
	public static final class Message {

		static List<Message> getMessages(String json) throws TwitterException {
			if (json.trim().equals(""))
				return Collections.emptyList();
			try {
				List<Message> msgs = new ArrayList<Message>();
				JSONArray arr = new JSONArray(json);
				for (int i = 0; i < arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
					Message u = new Message(obj);
					msgs.add(u);
				}
				return msgs;
			} catch (JSONException e) {
				throw new TwitterException(e);
			}
		}
		private final Date createdAt;
		private final int id;
//		private final User recipient;
		private final User sender;
		private final String text;
		private final boolean isPublic;
		Message(JSONObject obj) throws JSONException, TwitterException {
			id = obj.getInt("id");
			text = obj.getString("text");
			String c = jsonGet("created_at", obj);
			createdAt = new Date(c);
			// Public?
			if (obj.has("source") && obj.getString("source").equals("web")) isPublic = true;
			else isPublic =false;
			if (obj.has("sender")) {
				// direct messages
				assert ! isPublic;
				sender = new User(obj.getJSONObject("sender"), null);
			} else if (obj.has("user")) {
				// public messages
				assert isPublic;
				sender = new User(obj.getJSONObject("user"), null);
			} else {
				// Shouldn't happen
				sender = null;
			}
//			The reicipient is always the logged in user so why bother storing this?
//			if (obj.has("recipient")) {
//				// direct messages
//				assert ! isPublic;
//				recipient = new User(obj.getJSONObject("recipient"), null);
//			} else {
//				assert isPublic;
//				recipient = null;
//			}
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public int getId() {
			return id;
		}
		// Not sure why Twitter gives us this, since it is always the authenticating user
//		public User getRecipient() {
//			return recipient;
//		}
		public User getSender() {
			return sender;
		}
		public String getText() {
			return text;
		}
		/**
		 * @return true if this was a reply posted on the web,
		 * false if it was a direct message.
		 */
		public boolean isPublic() {
			return isPublic;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	

	

	private static final int timeOutMilliSecs = 10 * 1000;


	private static final String UTF8 = "UTF-8";

	/**
	 * Create a map from a list of key, value pairs. An easy way to make small
	 * maps, basically the equivalent of {@link Arrays#asList(Object...)}.
	 */
	@SuppressWarnings("unchecked")
	private static <K, V> Map<K, V> asMap(Object... keyValuePairs) {
		assert keyValuePairs.length % 2 == 0;
		Map m = new HashMap(keyValuePairs.length / 2);
		for (int i = 0; i < keyValuePairs.length; i += 2) {
			m.put(keyValuePairs[i], keyValuePairs[i + 1]);
		}
		return m;
	}

	/**
	 * Convenience method for making Dates. Because Date is a tricksy bugger of
	 * a class.
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return date object
	 */
	public static Date getDate(int year, String month, int day) {
		try {
			Field field = GregorianCalendar.class.getField(month.toUpperCase());
			int m = field.getInt(null);
			Calendar date = new GregorianCalendar(year, m, day);
			return date.getTime();
		} catch (Exception x) {
			throw new IllegalArgumentException(x.getMessage());
		}
	}

	/**
	 * Convenience method: Finds a user with the given screen-name from the
	 * list.
	 *
	 * @param screenName
	 *            aka login name
	 * @param users
	 * @return User with the given name, or null.
	 */
	public static User getUser(String screenName, List<User> users) {
		assert screenName != null && users != null;
		for (User user : users) {
			if (screenName.equals(user.screenName))
				return user;
		}
		return null;
	}

	/** Helper method to deal with JSon-in-Java wierdness */
	public static String jsonGet(String key, JSONObject jsonObj) {
		Object val = jsonObj.opt(key);
		if (val == null)
			return null;
		return val.toString();
	}

	public static void main(String[] args) {
		System.out.println("Java interface for Twitter");
		System.out.println("--------------------------");
		System.out.println("Version 0.9.1");
		System.out.println("Released under GPL by ThinkTank Mathematics Ltd.");
		System.out.println("See source code or JavaDoc for details on how to use.");
	}

//	private Format format = Format.xml;

	/**
	 * Convert to a URI, or return null if this is badly formatted
	 */
	public static URI URI(String uri) {
		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			return null; // Bad syntax
		}
	}

	private final String name;

	private final String password;

	/**
	 * Java wrapper for the Twitter API.
	 *
	 * @param name
	 *            The name of the Twitter user. Only used by some methods. Can
	 *            be null if you avoid methods requiring authentication.
	 * @param password
	 *            The password of the Twitter user. Can be null if you avoid
	 *            methods requiring authentication.
	 */
	public Twitter(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/**
	 * Create: Befriends the user specified in the ID parameter as the
	 * authenticating user.
	 *
	 * @param username
	 *            Required. The ID or screen name of the user to befriend.
	 * @return The befriended user.
	 */
	public User befriend(String username) throws TwitterException {
		if (username == null) throw new NullPointerException();
		String page = fetchWebPage("http://twitter.com/friendships/create/"
				+ username + ".json", null, true);
		try {
			return new User(new JSONObject(page), null);
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}

	/**
	 * Destroy: Discontinues friendship with the user specified in the ID
	 * parameter as the authenticating user.
	 *
	 * @param username
	 *            The ID or screen name of the user with whom to discontinue
	 *            friendship.
	 * @return the un-friended user
	 */
	public User breakFriendship(String username) throws TwitterException {
		String page = fetchWebPage("http://twitter.com/friendships/destroy/"
				+ username + ".json", null, true);
		User user;
		try {
			user = new User(new JSONObject(page), null);
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
		return user;
	}

	/**
	 * Close a reader/writer/stream, ignoring any exceptions that result. Also
	 * flushes if there is a flush() method.
	 */
	private void close(Closeable input) {
		if (input == null)
			return;
		// Flush (annoying that this is not part of Closeable)
		try {
			Method m = input.getClass().getMethod("flush");
			m.invoke(input);
		} catch (Exception e) {
			// Ignore
		}
		// Close
		try {
			input.close();
		} catch (IOException e) {
			// Ignore
		}
	}

	private String date(Date since) {
		if (since == null)
			return null;
		// Sun, 06 Nov 1994 08:49:37 GMT
		String gmt = since.toGMTString();
		return gmt;
	}

	/**
	 * Destroys the status specified by the required ID parameter.
	 * The authenticating user must be the author of the specified status.
	 */
	public void destroyStatus(int id) throws TwitterException {
		String page = fetchWebPage(
				"http://twitter.com/statuses/destroy/"+id+".json",
				null, true);
		assert page != null;
	}

	/**
	 * Destroys the given status. Equivalent to {@link #destroyStatus(int)}.
	 * The authenticating user must be the author of the status post.
	 */
	public void destroyStatus(Status status) throws TwitterException {
		destroyStatus(status.id);
	}

	private String encode(Object x) {
		return URLEncoder.encode(String.valueOf(x));
	}

	/**
	 * Fetch a web page's contents. Note that this will change all line breaks
	 * into system line breaks!
	 * <p>
	 * Pretends to be IE6 on Windows XP, Service Pack 2
	 *
	 * @param uri
	 *            The web-page (or file) to fetch. This method can handle
	 *            permanent redirects, but not javascript or meta redirects.
	 * @param timeOutMilliSecs
	 *            The read time out in milliseconds. Zero is not allowed. Note
	 *            that this is not the timeout for the method call, but only for
	 *            the connection. The method call may take longer.
	 *
	 * @return The full text of the web page
	 *
	 * @throws TwitterException
	 *             for various reasons...<br> - If the uri is not valid. This
	 *             includes bad syntax, bad host and 404 errors.<br> - If the
	 *             website does not respond in time.<br> - If the resulting
	 *             page does not have a text MIME type (e.g. text/html is fine).<br>
	 */
	protected String fetchWebPage(String uri, Map<String, String> vars,
			boolean authenticate) throws TwitterException {
		assert timeOutMilliSecs > 0;
		if (vars != null && vars.size() != 0) {
			uri += "?";
			for (Entry<String, String> e : vars.entrySet()) {
				if (e.getValue() == null)
					continue;
				uri += encode(e.getKey()) + "=" + encode(e.getValue()) + "&";
			}
		}
		// Setup a connection
		final URLConnection connection;
		final InputStream inStream;
		try {
			connection = new URL(uri).openConnection();
		} catch (IOException ex) {
			throw new TwitterException(ex);
		}
		// Authenticate
		if (authenticate) {
			setBasicAuthentication(name, password, connection);
		}
		// pretend to be IE6 on Windows XP SP2
		// http://en.wikipedia.org/wiki/User_agent#Internet_Explorer
		connection
				.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)");
		connection.setDoInput(true);
		connection.setReadTimeout(timeOutMilliSecs);
		// Open a connection
		try {
			inStream = connection.getInputStream();
		} catch (FileNotFoundException e) { // This happens with 404s only
			throw new TwitterException("404 Error: Page not found " + uri);
		} catch (IOException e) { // All other problems
			// This happens with failed connections and 404s
			Map<String, List<String>> headers = connection.getHeaderFields();
			throw new TwitterException("Could not connect to " + uri + ":\n"
					+ e.getMessage()+headers.get(null));
		}
		// Read in the web page
		String page = toString(inStream);
		// Done
		return page;
	}

	/**
	 * Returns a list of <i>all</i> the direct messages sent to the authenticating user.
	 * @see #getRecentDirectMessages()
	 */
	public List<Message> getDirectMessages() throws TwitterException {
		// FIXME !
		return getRecentDirectMessages();
	}
	
	/** 
	 * @return
	 * the remaining number of API requests available to the authenticating user before the API limit is reached for the current hour.
	 * <i>If this is negative you should stop using Twitter with this login for a bit.</i>  
	 * Note: Calls to rate_limit_status do not count against the rate limit.
	 */
	public int getRateLimitStatus() {
		String json = fetchWebPage("http://twitter.com/account/rate_limit_status.json", null, true);
		try {
			JSONObject obj = new JSONObject(json);
			int hits = obj.getInt("remaining_hits");
			return hits;
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}
	
	/**
	 * Returns a list of the last 20 direct messages sent to the authenticating user.
	 * @see getDirectMessages()
	 */
	public List<Message> getRecentDirectMessages() throws TwitterException {
		return Message.getMessages(fetchWebPage(DIRECT_MSG, null, true));
	}

	/**
	 *
	 * Returns a list of the direct messages sent to the authenticating user.
	 * <p>
	 * Note: the Twitter API makes this available in rss if that's of
	 * interest.
	 *
	 * @param since
	 *            Narrows the resulting list of direct messages to just those
	 *            sent after the specified date.
	 */
	public List<Message> getDirectMessages(Date since) throws TwitterException {
		Map<String, String> vars = asMap("since", date(since));
		return getDirectMessages(vars);
	}
	
	/**
	 *
	 * Returns a list of the direct messages sent to the authenticating user.
	 * <p>
	 * Note: the Twitter API makes this available in rss if that's of
	 * interest.
	 *
	 * @param sinceId The Id of a direct message.
	 *            Narrows the resulting list of direct messages to just those
	 *            sent after the specified message id.
	 */
	public List<Message> getDirectMessages(long sinceId) throws TwitterException {
		Map<String, String> vars = asMap("since", Long.toString(sinceId));
		return getDirectMessages(vars);
	}
	
	private static final String DIRECT_MSG = "http://twitter.com/direct_messages.json";

	/**
 * Returns the list of direct messages that conform to the variables passed.
     * Typically used with 'since' or 'since_id' to continue fetching past pages
     * until twitter returns all the messages you are expecting.
    *
     * @param var Any options you would like to pass through to Twitter. Don't 
     *            send page, we'll take care of that for you.
     */
	private List<Message> getDirectMessages(Map<String, String> var) throws TwitterException {
		int pageno = 1;
		List<Message> msgs = new ArrayList<Message>();
		while (true) {
			List<Message> nextpage = Message.getMessages(fetchWebPage(DIRECT_MSG, var, true));
			msgs.addAll(nextpage);
			if (nextpage.size() != 20) break;
			pageno++;
			var.put("page", Integer.toString(pageno));		
		}
		return msgs;
	}

	/**
	 *
	 * Returns a list of the users currently featured on the site with their
	 * current statuses inline.
	 */
	public List<User> getFeatured() throws TwitterException {
		return User.getUsers(fetchWebPage(
				"http://twitter.com/statuses/featured.json", null, true));
	}

	/**
	 * Returns the authenticating user's followers, each with current status
	 * inline.
	 */
	public List<User> getFollowers() throws TwitterException {
		return User.getUsers(fetchWebPage(
				"http://twitter.com/statuses/followers.json", null, true));
	}

	/**
	 * Returns the (latest 100) authenticating user's friends, each with current status
	 * inline.
	 * @throws TwitterException
	 */
	public List<User> getFriends() throws TwitterException {
		String json = fetchWebPage("http://twitter.com/statuses/friends.json",
				null, true);
		return User.getUsers(json);
	}

	/**
	 *
	 * Returns the (latest 100) given user's friends, each with current status inline.
	 *
	 * @param username
	 *            The ID or screen name of the user for whom to request a list
	 *            of friends.
	 * @throws TwitterException
	 */
	public List<User> getFriends(String username) throws TwitterException {
		return User.getUsers(fetchWebPage(
				"http://twitter.com/statuses/friends/" + username + ".json",
				null, false));
	}

	/**
	 * Returns the 20 most recent statuses posted in the last 24 hours from the
	 * authenticating user and that user's friends.
	 */
	public List<Status> getFriendsTimeline() throws TwitterException{
		String json = fetchWebPage(
				"http://twitter.com/statuses/friends_timeline.json", null, true);
		return Status.getStatuses(json);
	}

	/**
	 * Returns the 20 most recent statuses posted in the last 24 hours from the
	 * user (given by id) and that user's friends.
	 *
	 * @param id
	 *            Optional. Specifies the ID or screen name of the user for whom
	 *            to return the friends_timeline.
	 * @param since
	 *            Optional. Narrows the returned results to just those statuses
	 *            created after the specified HTTP-formatted date. The same
	 *            behavior is available by setting an If-Modified-Since header
	 *            in your HTTP request.
	 *
	 */
	public List<Status> getFriendsTimeline(String id, Date since) throws TwitterException {
		Map<String, String> map = asMap("id", id, "since", date(since));
		String json = fetchWebPage(
				"http://twitter.com/statuses/friends_timeline.json", map, false);
		return Status.getStatuses(json);
	}

	/**
	 * Returns the 20 most recent statuses from non-protected users who have set
	 * a custom user icon. Does not require authentication.
	 *
	 * TODO since_id. Optional. Returns only public statuses with an ID greater
	 * than (that is, more recent than) the specified ID.
	 */
	public List<Status> getPublicTimeline() throws TwitterException{
		String json = fetchWebPage(
				"http://twitter.com/statuses/public_timeline.json", null, false);
		return Status.getStatuses(json);
	}

	/**
	 *
	 * Returns the 20 most recent replies (status updates prefixed with
	 * @username) to the authenticating user. Replies are only available to the
	 *            authenticating user; you can not request a list of replies to
	 *            another user whether public or protected.
	 */
	public List<Message> getReplies() throws TwitterException {
		String page = fetchWebPage("http://twitter.com/statuses/replies.json", null,
				true);
		return Message.getMessages(page);
	}

	/**
	 * @return The current status of the user.
	 */
	public Status getStatus() throws TwitterException {
		Map<String, String> vars = asMap("count", 1);
		String json = fetchWebPage(
				"http://twitter.com/statuses/user_timeline.json", vars, true);
		return Status.getStatuses(json).get(0);
	}

	/**
	 * Returns a single status, specified by the id parameter below. The
	 * status's author will be returned inline.
	 *
	 * @param id
	 *            The numerical ID of the status you're trying to retrieve. Ex:
	 *            http://twitter.com/statuses/show/123.xml
	 */
	public Status getStatus(int id) throws TwitterException {
		String json = fetchWebPage("http://twitter.com/statuses/show/" + id
				+ ".json", null, true);
		try {
			return new Status(new JSONObject(json), null);
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}

	/**
	 * @return The current status of the given user, as a normal String.
	 */
	public Status getStatus(String username) throws TwitterException {
		assert username != null;
		Map<String, String> vars = asMap("id", username, "count", 1);
		String json = fetchWebPage(
				"http://twitter.com/statuses/user_timeline.json", vars, false);
		return Status.getStatuses(json).get(0);

	}

	/**
	 * Returns the 20 most recent statuses posted in the last 24 hours from the
	 * authenticating user.
	 */
	public List<Status> getUserTimeline() throws TwitterException {
		String json = fetchWebPage(
				"http://twitter.com/statuses/user_timeline.json", null, true);
		return Status.getStatuses(json);
	}

	/**
	 * Returns the most recent statuses posted in the last 24 hours from the
	 * given user.
	 * <p>
	 * This method will authenticate if it can (i.e. if the Twitter object has
	 * a username and password). Authentication is needed to see the posts of
	 * a private user. 
	 *
	 * @param id
	 *            Can be null. Specifies the ID or screen name of the user for
	 *            whom to return the friends_timeline.
	 * @param count
	 *            Can be null (=20). Specifies the number of statuses to
	 *            retrieve. May not be greater than 20 for performance purposes.
	 * @param since
	 *            Can be null. Narrows the returned results to just those
	 *            statuses created after the specified date.
	 */
	public List<Status> getUserTimeline(String id, Integer count, Date since) throws TwitterException {
		if (count != null && count > 20)
			throw new IllegalArgumentException(
					"Sorry: Cannot request more than 20 updates.");
		Map<String, String> vars = asMap("id", id, "count", count, "since",
				date(since));
		// Should we authenticate?
		boolean authenticate = name != null && password != null;
		String json = fetchWebPage(
				"http://twitter.com/statuses/user_timeline.json", vars, authenticate);
		return Status.getStatuses(json);
	}

	/**
	 * Create a writer.
	 *
	 * @param out
	 * @return a buffered writer. Uses UTF8 if possible, falls back to the
	 *         default-encoding.
	 */
	private BufferedWriter getWriter(OutputStream out) {
		OutputStreamWriter writer;
		try {
			writer = new OutputStreamWriter(out, UTF8);
		} catch (UnsupportedEncodingException e) {
			writer = new OutputStreamWriter(out);
		}
		return new BufferedWriter(writer);
	}

	/**
	 * Fake a form POST.
	 *
	 * @param uri
	 *            The uri to post to.
	 * @param headers
	 *            The HTTP headers to send. These are sent as is without any
	 *            encoding.
	 * @param vars
	 *            The form variables to send. These are URL encoded before
	 *            sending.
	 * @return The response from the server.
	 */
	private String post(String uri, boolean authenticate,
			Map<String, String> vars) throws TwitterException {
		HttpURLConnection connection;
//		uri = uri.replace("format", format.toString());
		try {
			connection = (HttpURLConnection) new URL(uri).openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setReadTimeout(timeOutMilliSecs);
			if (authenticate) {
				setBasicAuthentication(name, password, connection);
			}
			StringBuilder encodedData = new StringBuilder();
			for (String key : vars.keySet()) {
				String val = encode(vars.get(key));
				encodedData.append(encode(key));
				encodedData.append('=');
				encodedData.append(val);
				encodedData.append('&');
			}
			connection.setRequestProperty("Content-Length", ""
					+ encodedData.length());
			OutputStream os = connection.getOutputStream();
			os.write(encodedData.toString().getBytes());
			close(os);
			String response = toString(connection.getInputStream());
			return response;
		} catch (IOException ex) {
			throw new TwitterException(ex);
		}
	}

	/**
	 * Sends a new direct message to the specified user from the
	 * authenticating user.
	 *
	 * @param recipient
	 *            Required. The ID or screen name of the recipient user.
	 * @param text
	 *            Required. The text of your direct message. Keep it under 140
	 *            characters!
	 * @return the sent message
	 */
	public Message sendMessage(String recipient, String text) throws TwitterException {
		if (text.length() > 140)
			throw new IllegalArgumentException("Message is too long.");
		Map<String, String> vars = asMap("user", recipient, "text", text);
		String result = post("http://twitter.com/direct_messages/new.json", true, vars);
		try {
			return new Message(new JSONObject(result));
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}


	/**
	 * Set a header for basic authentication login.
	 *
	 * @param name
	 * @param password
	 * @param connection
	 */
	private void setBasicAuthentication(String name, String password,
			URLConnection connection) {
		assert name != null && password != null : "Need name and password for this method";
		String userPassword = name + ":" + password;
		String encoding = new sun.misc.BASE64Encoder().encode(userPassword
				.getBytes());
		connection.setRequestProperty("Authorization", "Basic " + encoding);
	}

	/**
	 * Sets the authenticating user's status.
	 * <p>
	 * Identical to {@link #updateStatus(String)}, but with a Java-style
	 * name (updateStatus is the Twitter API name for this method).
	 *
	 * @param statusText
	 *            The text of your status update. Must not be more than 160
	 *            characters and should not be more than 140 characters to
	 *            ensure optimal display.
	 * @return The posted status when successful.
	 */
	public Status setStatus(String statusText) throws TwitterException {
		return updateStatus(statusText);
	}

	/**
	 * Returns information of a given user, specified by ID or screen name.
	 * <p>
	 * TODO This information includes design settings, so third party developers
	 * can theme their widgets according to a given user's preferences.
	 *
	 * @param id
	 *            The ID or screen name of a user.
	 */
	public User show(String id) throws TwitterException {
		String json = fetchWebPage("http://twitter.com/users/show/" + id
				+ ".json", null, true);
		// FIXME If you are trying to fetch data for a user who is only giving
		// updates to
		// * friends, the returned text will be "You are not authorized to see
		// this
		// * user."
		User user;
		try {
			user = new User(new JSONObject(json), null);
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
		return user;
	}

	/**
	 * Use a bufferred reader (preferably UTF-8) to extract the contents of the
	 * given stream. A convenience method for {@link #toString(Reader)}.
	 */
	private String toString(InputStream inputStream) {
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(inputStream, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(inputStream);
		}
		return toString(reader);
	}
	/**
	 * Use a bufferred reader to extract the contents of the given reader.
	 *
	 * @param reader
	 * @return The contents of this reader.
	 */
	private String toString(Reader reader) throws RuntimeException {
		try {
			// Buffer if not already buffered
			reader = reader instanceof BufferedReader ? (BufferedReader) reader
					: new BufferedReader(reader);
			StringBuilder output = new StringBuilder();
			while (true) {
				int c = reader.read();
				if (c == -1)
					break;
				output.append((char) c);
			}
			return output.toString();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} finally {
			close(reader);
		}
	}

	/**
	 * Updates the authenticating user's status.
	 *
	 * @param statusText
	 *            The text of your status update. Must not be more than 160
	 *            characters and should not be more than 140 characters to
	 *            ensure optimal display.
	 * @return The posted status when successful.
	 */
	public Status updateStatus(String statusText) throws TwitterException {
		if (statusText.length() > 160)
			throw new IllegalArgumentException(
					"Status text must be 160 characters or less");
		Map<String, String> vars = asMap("status", statusText);
		String result = post("http://twitter.com/statuses/update.json", true, vars);
		try {
			return new Status(new JSONObject(result), null);
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}

}


