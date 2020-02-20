package com.difelix.itauCash.service;

import com.difelix.itauCash.models.QrcodeStats;

import reactor.core.publisher.Mono;

public interface QrcodeStatsService {
	
	Mono<QrcodeStats> salvarDadosQrcodeAssincrono(QrcodeStats stats);
	
	Mono<QrcodeStats> buscarQrcodePorAlias(String alias);

}
