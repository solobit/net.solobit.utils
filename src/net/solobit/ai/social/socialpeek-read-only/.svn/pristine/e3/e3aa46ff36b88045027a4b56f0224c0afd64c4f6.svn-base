package winterwell.jtwitter;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A Twitter user. Fields are null if unset.
 *
 * @author daniel
 */
public final class User {
	static List<User> getUsers(String json) throws TwitterException {
		if (json.trim().equals(""))
			return Collections.emptyList();
		try {
			List<User> users = new ArrayList<User>();
			JSONArray arr = new JSONArray(json);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i);
				User u = new User(obj, null);
				users.add(u);
			}
			return users;
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}

	public final String description;
	public final int id;
	public final String location;
	/** The display name, e.g. "Daniel Winterstein" */
	public final String name;
	public final URI profileImageUrl;
	public final boolean protectedUser;
	/** The login name, e.g. "winterstein" */
	public final String screenName;
	public final Status status;
	public final URI website;

	User(JSONObject obj, Status status) throws TwitterException {
		try {
			id = obj.getInt("id");
			name = Twitter.jsonGet("name", obj);
			screenName = Twitter.jsonGet("screen_name", obj);
			location = Twitter.jsonGet("location", obj);
			description = Twitter.jsonGet("description", obj);
			String img = Twitter.jsonGet("profile_image_url", obj);
			profileImageUrl = img == null ? null : Twitter.URI(img);
			String url = Twitter.jsonGet("url", obj);
			website = url == null ? null : Twitter.URI(url);
			protectedUser = obj.getBoolean("protected");
			if (status == null) {
				JSONObject s = obj.optJSONObject("status");
				this.status = s == null ? null : new Status(s, this);
			} else {
				this.status = status;
			}
		} catch (JSONException e) {
			throw new TwitterException(e);
		}
	}


	public String getDescription() {
		return description;
	}


	public int getId() {
		return id;
	}


	public String getLocation() {
		return location;
	}


	/** The display name, e.g. "Daniel Winterstein" */
	public String getName() {
		return name;
	}

	public URI getProfileImageUrl() {
		return profileImageUrl;
	}


	/** The login name, e.g. "winterstein" */
	public String getScreenName() {
		return screenName;
	}


	public Status getStatus() {
		return status;
	}

	public URI getWebsite() {
		return website;
	}


	public boolean isProtectedUser() {
		return protectedUser;
	}



	@Override
	public String toString() {
		return name;
	}
}