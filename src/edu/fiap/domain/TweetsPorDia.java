package edu.fiap.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import twitter4j.Status;

public class TweetsPorDia {
	
	public final List<Status> listaStatus;
	public final QuantidadePorDia tweets;
	
	public TweetsPorDia(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
		this.tweets = new QuantidadePorDia(getTweets(listaStatus));
	}
	
	private static List<Status> getTweets(List<Status> listaStatus) {
		return listaStatus.stream()
				.filter(tweet -> !tweet.isRetweet())
				.collect(Collectors.toList());
	}
	
	public String escrevaTweet(String handle) {
		if(this.tweets.porDia.isEmpty()) {
			return handle.concat(" - 1. nenhum tweet encontrado");
		}
		StringBuilder builder = new StringBuilder(handle.concat(" - 1. tweets por dia"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for(LocalDate dia : this.tweets.porDia.keySet()) {
			String linha = String.format("\n%s: %d", dia.format(formatter), this.tweets.porDia.get(dia));
			builder.append(linha);
		}
		return builder.toString();
	}
}
