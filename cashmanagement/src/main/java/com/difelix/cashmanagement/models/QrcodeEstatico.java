package com.difelix.cashmanagement.models;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class QrcodeEstatico {
	
	@PrimaryKey
	private UUID id;
	
	private String chaveAutenticacao;
	
	private String referencia;
	
	private String status;
	
	private Double valor;
	
}
