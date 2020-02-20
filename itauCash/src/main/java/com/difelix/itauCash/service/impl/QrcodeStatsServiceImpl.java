package com.difelix.itauCash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.difelix.itauCash.models.QrcodeStats;
import com.difelix.itauCash.models.repository.QrcodeStatsRepository;
import com.difelix.itauCash.service.QrcodeStatsService;
import reactor.core.publisher.Mono;

@Service
public class QrcodeStatsServiceImpl implements QrcodeStatsService {

	@Autowired
	QrcodeStatsRepository repository;

	@Override
	public Mono<QrcodeStats> salvarDadosQrcodeAssincrono(QrcodeStats stats) {
		return repository.save(stats);
	}

	@Override
	public Mono<QrcodeStats> buscarQrcodePorAlias(String alias) {
		return repository.findByAlias(alias);
	}
}
