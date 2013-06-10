package uk.co.mccann.socialpeek.service;

import java.util.List;

import winterwell.jtwitter.Status;
import winterwell.jtwitter.Twitter;


public class EnhancedTwitter extends Twitter {

	public EnhancedTwitter(String name, String password) {
		super(name, password);
		
	}
	
	public List<Status> searchTwitter(String keyword) {
		String json = fetchWebPage("http://search.twitter.com/search.json?q=" + keyword.trim(), null, false);
		return Status.getStatusesFromProperty(json,"results");
		
	}

}
