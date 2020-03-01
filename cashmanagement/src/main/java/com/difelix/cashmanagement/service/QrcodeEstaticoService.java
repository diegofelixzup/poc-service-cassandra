package com.difelix.cashmanagement.service;

import java.util.List;
import java.util.UUID;

import com.difelix.cashmanagement.models.QrcodeEstatico;

public interface QrcodeEstaticoService {

	Iterable<QrcodeEstatico> encontrarTodosQrcode();
	
	QrcodeEstatico salvarDadosQrcode(QrcodeEstatico qrcode);
	
	List<QrcodeEstatico> listarTodosQrcodesDeUmaChave(String chave);
	
	QrcodeEstatico atualizarQrcode(UUID id, QrcodeEstatico qrcode);
	
	List<QrcodeEstatico> listarQrcodePorStatus(String chaveAutenticacao, String status);
	
	void cancelarQrcode(UUID id);
}
