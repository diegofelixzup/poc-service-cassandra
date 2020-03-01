package com.difelix.cashmanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.difelix.cashmanagement.models.QrcodeEstatico;

public interface QrcodeEstaticoRepository extends CassandraRepository<QrcodeEstatico, UUID> {

	@AllowFiltering
	List<QrcodeEstatico> findByChaveAutenticacao(String chaveAutenticacao);
}
