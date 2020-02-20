package com.difelix.itauCash.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.difelix.itauCash.models.QrcodeStats;
import com.difelix.itauCash.resources.dto.QrcodeStatsDto;
import com.difelix.itauCash.service.QrcodeStatsService;
import com.difelix.itauCash.service.exception.RegraNegocioException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/qrcode")
public class QrcodeStatsResources {
	
	@Autowired
	QrcodeStatsService service;
	
	@PostMapping("/salvar")
	public ResponseEntity salvarDadosQrcode(@RequestBody QrcodeStatsDto dto) {
		try {
		     QrcodeStats stats = converterDto(dto);
		     Mono<QrcodeStats> statusSalvo = service.salvarDadosQrcodeAssincrono(stats);
		     return new ResponseEntity(statusSalvo, HttpStatus.CREATED); 
		} catch (RegraNegocioException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@GetMapping("/buscar/alias")
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<QrcodeStats> buscarQrcodePorAlias(@RequestParam String alias) {
		return service.buscarQrcodePorAlias(alias);
	}
	
	public QrcodeStats converterDto(QrcodeStatsDto dto) {
		QrcodeStats stats = new QrcodeStats();
		
		stats.setUrl(dto.getUrl());
		stats.setAlias(dto.getAlias());
		stats.setValue(dto.getValue());
		
		return stats;
	}
}
