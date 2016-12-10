package edu.fiap.io;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public final class Poster {
	private static final Twitter twitter = TwitterFactory.getSingleton();
	
	public static void publique(String[] tweets) throws TwitterException {
		System.out.println("Iniciando publicações");
		for(String tweet : tweets) {
			System.out.println("Publicando: ".concat(tweet));
			twitter.updateStatus(tweet);
		}
		System.out.println("Publicações finalizadas");
	}
}
