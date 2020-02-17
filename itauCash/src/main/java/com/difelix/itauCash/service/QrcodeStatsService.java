package com.difelix.itauCash.service;

import java.util.UUID;

import com.difelix.itauCash.models.QrcodeStats;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QrcodeStatsService {
	
	Mono<QrcodeStats> salvarDadosQrcodeAssincrono(QrcodeStats stats);
	
	Flux<QrcodeStats> buscarQrcodePorAlias(String alias);
	
	Mono<QrcodeStats> buscarQrcodePorId(UUID id);

}
