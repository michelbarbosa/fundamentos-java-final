package edu.fiap.domain;

import java.util.List;

import twitter4j.Status;

public class OrdenadosPorAutor {
	
	public final List<Status> listaStatus;
	public final String primeiroNome;
	public final String ultimoNome;
	
	public OrdenadosPorAutor(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
		String[] autores = getAutoresOrdenados(listaStatus);
		if(autores.length == 0) {
			primeiroNome = null;
			ultimoNome = null;
			return;
		}
		primeiroNome = autores[0];
		ultimoNome = autores[autores.length - 1];
	}
	
	private static String[] getAutoresOrdenados(List<Status> listaStatus) {
		return listaStatus.stream()
				.map(tweet -> tweet.getUser().getName())
				.sorted()
				.toArray(String[]::new);
	}
	
	public String escrevaTweet(String handle) {
		if(primeiroNome == null) {
			return String.format("%s - 4. nenhum tweet", handle);
		}
		return String.format("%s - 4. primeiro nome: %s; último nome: %s", handle, primeiroNome, ultimoNome);
	}
}
