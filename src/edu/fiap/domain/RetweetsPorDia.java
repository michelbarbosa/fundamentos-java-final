package edu.fiap.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import twitter4j.Status;

public class RetweetsPorDia {
	
	public final List<Status> listaStatus;
	public final QuantidadePorDia retweets;
	
	public RetweetsPorDia(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
		this.retweets = new QuantidadePorDia(getRetweets(listaStatus));
	}
	
	private static List<Status> getRetweets(List<Status> listaStatus) {
		return listaStatus.stream()
				.filter(tweet -> tweet.isRetweet())
				.collect(Collectors.toList());
	}
	
	public String escrevaTweet(String handle) {
		if(this.retweets.porDia.isEmpty()) {
			return handle.concat(" - 2. nenhum retweet encontrado");
		}
		StringBuilder builder = new StringBuilder(handle.concat(" - 2. retweets por dia"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for(LocalDate dia : this.retweets.porDia.keySet()) {
			String linha = String.format("\n%s: %d", dia.format(formatter), this.retweets.porDia.get(dia));
			builder.append(linha);
		}
		return builder.toString();
	}
}
