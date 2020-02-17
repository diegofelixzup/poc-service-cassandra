package com.difelix.itauCash.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.difelix.itauCash.models.QrcodeStats;
import com.difelix.itauCash.models.repository.QrcodeStatsRepository;
import com.difelix.itauCash.service.QrcodeStatsService;
import com.difelix.itauCash.service.exception.RegraNegocioException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QrcodeStatsServiceImpl implements QrcodeStatsService {

	@Autowired
	QrcodeStatsRepository repository;

	@Override
	public Mono<QrcodeStats> salvarDadosQrcodeAssincrono(QrcodeStats stats) {
		stats.setId(gerarChaveId());
		return repository.save(stats);
	}

	public UUID gerarChaveId() {
		return UUIDs.timeBased();
	}

	@Override
	public Flux<QrcodeStats> buscarQrcodePorAlias(String alias) {
		return repository.findByAlias(alias);
	}

	@Override
	public Mono<QrcodeStats> buscarQrcodePorId(UUID id) {
		return repository.findById(id);
	}
}
