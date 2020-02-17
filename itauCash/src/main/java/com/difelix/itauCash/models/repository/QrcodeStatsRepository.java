package com.difelix.itauCash.models.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.difelix.itauCash.models.QrcodeStats;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QrcodeStatsRepository extends ReactiveCassandraRepository<QrcodeStats, Long>{

	Flux<QrcodeStats> findByAlias(String alias);
	
	Mono<QrcodeStats> findById(UUID id);
	
	Mono<QrcodeStats> existsByAlias(String alias);
}
