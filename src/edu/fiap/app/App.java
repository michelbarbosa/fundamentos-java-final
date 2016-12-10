package edu.fiap.app;

import java.time.LocalDate;

import edu.fiap.domain.SmallAnalytics;
import edu.fiap.io.Buscador;
import edu.fiap.io.Poster;
import twitter4j.TwitterException;

public class App {
	public static void main(String[] args) {
		try {
			LocalDate since = LocalDate.now().minusDays(7);
			SmallAnalytics sa = Buscador.busque("#java", since);
			Poster.publique(sa.escrevaTweets("@michelpf"));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
