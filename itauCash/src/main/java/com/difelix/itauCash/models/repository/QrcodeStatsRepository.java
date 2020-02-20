package com.difelix.itauCash.models.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.difelix.itauCash.models.QrcodeStats;

import reactor.core.publisher.Mono;

public interface QrcodeStatsRepository extends ReactiveCassandraRepository<QrcodeStats, String>{

	Mono<QrcodeStats> findByAlias(String alias);
	
	Mono<Boolean> existsByAlias(String alias);
}
