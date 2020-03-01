package com.difelix.cashmanagement.api.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.difelix.cashmanagement.api.resources.dto.QrcodeEstaticoDto;
import com.difelix.cashmanagement.exceptions.QrcodeValoresException;
import com.difelix.cashmanagement.models.QrcodeEstatico;
import com.difelix.cashmanagement.service.QrcodeEstaticoService;

@RestController
@RequestMapping("/qrcode/estatico")
public class QrcodeEstaticoResources {
	
	@Autowired
	QrcodeEstaticoService service;
	
	@PostMapping("/salvar")
	public ResponseEntity salvarQrcodeEstatico(@RequestBody QrcodeEstaticoDto dto) {
		try {
			QrcodeEstatico qrcode = converterDto(dto);
			qrcode = service.salvarDadosQrcode(qrcode);
			return new ResponseEntity(qrcode, HttpStatus.CREATED);
		} catch (QrcodeValoresException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/buscar/chave")
	public ResponseEntity buscarQrcodePorChave(@RequestParam String chave) {
		List<QrcodeEstatico> qrcodes = service.listarTodosQrcodesDeUmaChave(chave);
		return ResponseEntity.ok(qrcodes);
	}
	
	@GetMapping("/buscar/status")
	public ResponseEntity buscarQrcodePorStatus(@RequestParam String chave, @RequestParam String status) {
		try {
		   List<QrcodeEstatico> qrcodes = service.listarQrcodePorStatus(chave, status);
		   return ResponseEntity.ok(qrcodes);
		} catch (QrcodeValoresException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	@GetMapping("/buscar/todos")
	public ResponseEntity buscarTodosQrcode() {
		Iterable<QrcodeEstatico> qrcodes = service.encontrarTodosQrcode();
		return ResponseEntity.ok(qrcodes);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity atualizarQrcode(@PathVariable UUID id, @RequestBody QrcodeEstaticoDto dto) {
		try {
			QrcodeEstatico qrcode = converterDto(dto);
			qrcode = service.atualizarQrcode(id, qrcode);
			return ResponseEntity.ok(qrcode);
		} catch (QrcodeValoresException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/cancelar/{id}")
	public ResponseEntity cancelarQrcode(@PathVariable UUID id) {
		service.cancelarQrcode(id);
		return ResponseEntity.ok().body("Qrcode Cancelado");
	}
	
	public QrcodeEstatico converterDto(QrcodeEstaticoDto dto) {
		QrcodeEstatico qrcode = new QrcodeEstatico();
		
		qrcode.setChaveAutenticacao(dto.getChave());
		qrcode.setReferencia(dto.getReferencia());
		qrcode.setValor(dto.getValor());
		
		if (dto.getStatus() != null) {
			qrcode.setStatus(dto.getStatus());
		}
		
		return qrcode;
	}

}
