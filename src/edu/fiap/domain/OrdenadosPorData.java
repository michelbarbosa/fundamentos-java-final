package edu.fiap.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import edu.fiap.util.DateUtil;
import twitter4j.Status;

public class OrdenadosPorData {
	public final List<Status> listaStatus;
	public final LocalDateTime maisAntiga;
	public final LocalDateTime maisRecente;
	
	public OrdenadosPorData(List<Status> listaStatus) {
		this.listaStatus = listaStatus;
		Date[] datas = getDatasOrdenadas(listaStatus);
		if(datas.length == 0) {
			maisAntiga = null;
			maisRecente = null;
			return;
		}
		maisAntiga = DateUtil.toLocalDateTime(datas[0]);
		maisRecente = DateUtil.toLocalDateTime(datas[datas.length - 1]);
	}
	
	private static Date[] getDatasOrdenadas(List<Status> listaStatus) {
		return listaStatus.stream()
				.map(tweet -> tweet.getCreatedAt())
				.sorted()
				.toArray(Date[]::new);
	}
	
	public String escrevaTweet(String handle) {
		if(maisAntiga == null) {
			return String.format("%s - 5. nenhum tweet", handle);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return String.format("%s - 5. data mais antiga: %s; data mais recente: %s", handle, maisAntiga.format(formatter), maisRecente.format(formatter));
	}
}
