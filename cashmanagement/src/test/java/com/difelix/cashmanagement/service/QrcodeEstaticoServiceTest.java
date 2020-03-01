package com.difelix.cashmanagement.service;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.datastax.driver.core.utils.UUIDs;
import com.difelix.cashmanagement.exceptions.QrcodeValoresException;
import com.difelix.cashmanagement.models.QrcodeEstatico;
import com.difelix.cashmanagement.repository.QrcodeEstaticoRepository;
import com.difelix.cashmanagement.service.impl.QrcodeEstaticoServiceImpl;

@RunWith(SpringRunner.class)
public class QrcodeEstaticoServiceTest {

	@MockBean
	QrcodeEstaticoRepository repository;

	@SpyBean
	QrcodeEstaticoServiceImpl service;

	@Test
	public void cadastrarQrcodeComSucesso() {
		QrcodeEstatico qrcode = QrcodeEstatico.builder().chaveAutenticacao("teste").referencia("teste").valor(10.00)
				.build();

		Mockito.doNothing().when(service).validarQrcode(Mockito.any(QrcodeEstatico.class));
		Mockito.when(repository.save(Mockito.any(QrcodeEstatico.class))).thenReturn(qrcode);

		QrcodeEstatico qrcodeSalvo = service.salvarDadosQrcode(qrcode);

		Assert.assertNotNull(qrcode.getId());
		Assert.assertEquals("ATIVO", qrcode.getStatus());
		Assert.assertEquals("teste", qrcode.getChaveAutenticacao());
		Assert.assertEquals("teste", qrcode.getReferencia());
	}

	@Test
	public void cadastrarQrcodeComChaveAutenticacaoNula() {
		QrcodeEstatico qrcode = QrcodeEstatico.builder().valor(10.00).referencia("teste").build();

		Throwable exception = Assertions.catchThrowable(() -> service.salvarDadosQrcode(qrcode));
		Assertions.assertThat(exception).isInstanceOf(QrcodeValoresException.class)
				.hasMessage("Chave de autenticação não pode ser nula ou vazia");
	}
	
	@Test
	public void buscarUmQrcodeComChaveAutenticacaoValida() {
		final String chaveAutenticacao = "qrcode2";
		List<QrcodeEstatico> qrcodesCadastrados = carregarListaParaTestes();
		
		Mockito.when(repository.findByChaveAutenticacao(Mockito.anyString())).thenReturn(qrcodesCadastrados);
		
		List<QrcodeEstatico> resultadoBusca = service.listarTodosQrcodesDeUmaChave(chaveAutenticacao);
		
		Assert.assertEquals(2, resultadoBusca.size());
	}
	
	@Test
	public void buscarUmQrcodeComChaveAutenticacaoNula() {
		Throwable exception = Assertions.catchThrowable(() -> service.listarTodosQrcodesDeUmaChave(null));
		Assertions.assertThat(exception).isInstanceOf(NullPointerException.class);
	}
	
	public List<QrcodeEstatico> carregarListaParaTestes() {
		QrcodeEstatico qrcode2 = QrcodeEstatico.builder().id(UUIDs.timeBased()).chaveAutenticacao("qrcode2").valor(20.0).status("INATIVO").build();
		QrcodeEstatico qrcode3 = QrcodeEstatico.builder().id(UUIDs.timeBased()).chaveAutenticacao("qrcode2").valor(30.0).status("ATIVO").build();
		
		return Arrays.asList(qrcode2, qrcode3);
	}

}
