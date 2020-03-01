package com.difelix.cashmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.difelix.cashmanagement.enums.QrcodeStatus;
import com.difelix.cashmanagement.exceptions.QrcodeValoresException;
import com.difelix.cashmanagement.models.QrcodeEstatico;
import com.difelix.cashmanagement.repository.QrcodeEstaticoRepository;
import com.difelix.cashmanagement.service.QrcodeEstaticoService;

@Service
public class QrcodeEstaticoServiceImpl implements QrcodeEstaticoService {
	
	@Autowired
	private QrcodeEstaticoRepository repository;

	@Override
	public QrcodeEstatico salvarDadosQrcode(QrcodeEstatico qrcode) {
		validarQrcode(qrcode);
		
		qrcode.setId(gerarChavePrimaria());
		qrcode.setStatus(QrcodeStatus.ATIVO.name());
		
		return repository.save(qrcode);
	}
	
	@Override
	public List<QrcodeEstatico> listarTodosQrcodesDeUmaChave(String chave) {
		Objects.requireNonNull(chave);
		return repository.findByChaveAutenticacao(chave);
	}
	
	@Override
	public List<QrcodeEstatico> listarQrcodePorStatus(String chaveAutenticacao, String status) {
		Objects.requireNonNull(status);
		Objects.requireNonNull(chaveAutenticacao);
		validarStatus(status);
		
		List<QrcodeEstatico> qrcodes = repository.findByChaveAutenticacao(chaveAutenticacao);
		return qrcodes.stream()
				.filter(qrcode -> qrcode.getStatus().equals(status))
				.collect(Collectors.toList());
	}
	
	@Override
	public Iterable<QrcodeEstatico> encontrarTodosQrcode() {
		return repository.findAll();
	}
	
	@Override
	public QrcodeEstatico atualizarQrcode(UUID id, QrcodeEstatico qrcode) {
		Objects.requireNonNull(id);
		
		if (repository.existsById(id)) {
			validarStatus(qrcode.getStatus());
			qrcode.setId(id);
			
			return repository.save(qrcode);
		} else {
			throw new QrcodeValoresException("Não foi encontrado qrcode com esse id");
		}
	}
	
	@Override
	public void cancelarQrcode(UUID id) {
		repository.findById(id).ifPresent(qrcode -> {
			qrcode.setStatus(QrcodeStatus.CANCELADO.name());
			repository.save(qrcode);
		});	
	}
	
	public void validarQrcode(QrcodeEstatico qrcode) {		
		if (qrcode.getChaveAutenticacao() == null || qrcode.getChaveAutenticacao().isEmpty()) {
			throw new QrcodeValoresException("Chave de autenticação não pode ser nula ou vazia");
		}
		
		if (qrcode.getValor() == null) {
			throw new QrcodeValoresException("Valor não pode ser nulo");
		}
	}
	
	public UUID gerarChavePrimaria() {
		return UUIDs.timeBased();
	}
	
	public void validarStatus(String status) {
		if (status == null) {
			throw new QrcodeValoresException("Status não pode ser nulo");
		}
		
		if (!status.equals(QrcodeStatus.ATIVO.name()) &&
			!status.equals(QrcodeStatus.INATIVO.name())) {
			throw new QrcodeValoresException("Status só pode receber ATIVO ou INATIVO.");
		}
	}

}
