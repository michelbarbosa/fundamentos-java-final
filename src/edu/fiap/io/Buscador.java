package edu.fiap.io;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import edu.fiap.domain.SmallAnalytics;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public final class Buscador {
	private static final Twitter twitter = TwitterFactory.getSingleton();
	
	public static final SmallAnalytics busque(String hashtag, LocalDate since) throws TwitterException {
		ArrayList<Status> tweets = new ArrayList<>();
		Query query = new Query(hashtag);
		query.count(100);
		query.setSince(since.format(DateTimeFormatter.ISO_DATE));
		System.out.println("Buscando tweets...");
		QueryResult result = twitter.search(query);
		while(result.hasNext()) {
			tweets.addAll(result.getTweets());
			result = twitter.search(result.nextQuery());
		}
		System.out.println("Busca finalizada...");
		return new SmallAnalytics(tweets);
	}
}
