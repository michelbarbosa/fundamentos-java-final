package edu.fiap.domain;

import java.util.List;

import twitter4j.Status;

public class SmallAnalytics {
	
	public final List<Status> listaStatus;
	public final TweetsPorDia tweetsPorDia;
	public final RetweetsPorDia retweetsPorDia;
	public final FavoritacoesPorDia favoritacoesPorDia;
	public final OrdenadosPorAutor ordenadosPorAutor;
	public final OrdenadosPorData ordenadosPorData;
	
	public SmallAnalytics(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
		this.tweetsPorDia = new TweetsPorDia(listaStatus);
		this.retweetsPorDia = new RetweetsPorDia(listaStatus);
		this.favoritacoesPorDia = new FavoritacoesPorDia(listaStatus);
		this.ordenadosPorAutor = new OrdenadosPorAutor(listaStatus);
		this.ordenadosPorData = new OrdenadosPorData(listaStatus);
	}
	
	public String[] escrevaTweets(String handle) {
		return new String[] {
				this.tweetsPorDia.escrevaTweet(handle),
				this.retweetsPorDia.escrevaTweet(handle),
				this.favoritacoesPorDia.escrevaTweet(handle),
				this.ordenadosPorAutor.escrevaTweet(handle),
				this.ordenadosPorData.escrevaTweet(handle)
		};
	}
}
