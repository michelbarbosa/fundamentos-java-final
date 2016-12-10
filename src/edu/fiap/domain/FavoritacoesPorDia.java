package edu.fiap.domain;

import static edu.fiap.util.DateUtil.toLocalDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import twitter4j.Status;

public class FavoritacoesPorDia {
	
	public final List<Status> listaStatus;
	public final HashMap<LocalDate, Long> favoritacoes;
	
	public FavoritacoesPorDia(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
		this.favoritacoes = getFavoritacoes(listaStatus);
	}
	
	private static HashMap<LocalDate, Long> getFavoritacoes(List<Status> listaStatus) {
		HashMap<LocalDate, Long> favoritacoes = new HashMap<>();
		for (Status status : listaStatus) {
			LocalDate dia = toLocalDate(status.getCreatedAt());
			if(!favoritacoes.containsKey(dia)) {
				favoritacoes.put(dia, (long) status.getFavoriteCount());
				continue;
			}
			favoritacoes.put(dia, favoritacoes.get(dia) + status.getFavoriteCount());
		}
		return favoritacoes;
	}
	
	public String escrevaTweet(String handle) {
		if(this.favoritacoes.isEmpty()) {
			return handle.concat(" - 3. nenhum tweet encontrado");
		}
		StringBuilder builder = new StringBuilder(handle.concat(" - 3. favoritações por dia"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for(LocalDate dia : this.favoritacoes.keySet()) {
			String linha = String.format("\n%s: %d", dia.format(formatter), this.favoritacoes.get(dia));
			builder.append(linha);
		}
		return builder.toString();
	}
}
