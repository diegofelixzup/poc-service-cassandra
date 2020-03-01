package com.difelix.cashmanagement.api.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeEstaticoDto {
	
	private String chave;
	private String referencia;
	private double valor;
	private String status;

}
