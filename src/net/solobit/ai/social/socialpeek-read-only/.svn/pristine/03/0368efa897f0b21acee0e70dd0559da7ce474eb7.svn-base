package winterwell.jtwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A Twitter status post. .toString() returns the status text.
 * <p>
 * Notes: This is a finalised data object. It has no methods but
 * exposes it's fields. If you want to change your status, use
 * {@link Twitter#updateStatus(String)} and
 * {@link Twitter#destroyStatus(Status)}.
 */
public class Status {
	public static List<Status> getStatuses(String json) throws TwitterException {
		if (json.trim().equals(""))
			return Collections.emptyList();
		try {
			List<Status> users = new ArrayList<Status>();
			JSONArray arr = new JSONArray(json);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i);
				Status u = new Status(obj, null);
				users.add(u);
			}
			return users;
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}
	
	/* added by dave shanley to cope with twitter search results */
	public static List<Status> getStatusesFromProperty(String json, String property) throws TwitterException {
		if (json.trim().equals(""))
			return Collections.emptyList();
		try {
			List<Status> users = new ArrayList<Status>();
			JSONObject resultobj = new JSONObject(json);
			JSONArray arr = resultobj.getJSONArray(property);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i);
				Status u = new Status(obj, null, true);
				users.add(u);
			}
			return users;
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}

	public final Date createdAt;
	public final int id;
	/** The actual status text. */
	public final String text;

	public final User user;
	
	/**
	 * E.g. "web" vs. "im"
	 */
	public final String source;

	Status(JSONObject object, User user) throws TwitterException {
		try {
			id = object.getInt("id");
			text = Twitter.jsonGet("text", object);
			String c = Twitter.jsonGet("created_at", object);
			createdAt = new Date(c);
			source = Twitter.jsonGet("source", object);
			this.user = user == null ? new User(object
					.getJSONObject("user"), this) : user;
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}
	
	
	/* new constructor to accept serch results */
	Status(JSONObject object, User user, boolean search) throws TwitterException {
		try {
			id = object.getInt("id");
			text = Twitter.jsonGet("text", object);
			String c = Twitter.jsonGet("created_at", object);
			createdAt = new Date(c);
			source = Twitter.jsonGet("source", object);
			
			/* do a little jiggery pokery to create a proper search result by creating a new JSON object from the results (added by dave) */
			JSONObject userJson = new JSONObject();
			userJson.put("id", Twitter.jsonGet("from_user_id", object));
			userJson.put("name", Twitter.jsonGet("from_user", object));
			userJson.put("screen_name", Twitter.jsonGet("from_user", object));
			userJson.put("profile_image_url", Twitter.jsonGet("profile_Image_url", object));
			userJson.put("protected", false);
			userJson.put("status", Twitter.jsonGet("text", object));
			
			this.user = user == null ? new User(userJson, this) : user;
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return The Twitter id for this post. This is used by some
	 * API methods.
	 */
	public int getId() {
		return id;
	}
	/** The actual status text. This is also returned by {@link #toString()}*/
	public String getText() {
		return text;
	}
	public User getUser() {
		return user;
	}

	/**
	 * @return The text of this status. E.g. "Kicking fommil's arse at
	 *         Civilisation."
	 */

	@Override
	public String toString() {
		return text;
	}
}