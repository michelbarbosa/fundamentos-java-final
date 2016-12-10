package edu.fiap.domain;

import static edu.fiap.util.DateUtil.toLocalDate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import twitter4j.Status;

public class QuantidadePorDia {
	
	public final List<Status> listaStatus;
	public final HashMap<LocalDate, Integer> porDia;
	
	public QuantidadePorDia(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
		this.porDia = getStatusPorDia(listaStatus);
	}
	
	private static HashMap<LocalDate, Integer> getStatusPorDia(List<Status> tweets) {
		HashMap<LocalDate, Integer> tweetsPorDia = new HashMap<>();
		for(Status tweet : tweets) {
			LocalDate data = toLocalDate(tweet.getCreatedAt());
			if(!tweetsPorDia.containsKey(data)) {
				tweetsPorDia.put(data, 1);
				continue;
			}
			tweetsPorDia.put(data, tweetsPorDia.get(data) + 1);
		}
		return tweetsPorDia;
	}
}
