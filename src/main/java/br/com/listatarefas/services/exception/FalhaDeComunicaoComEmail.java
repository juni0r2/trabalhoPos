package br.com.listatarefas.services.exception;

public class FalhaDeComunicaoComEmail extends RuntimeException {

	private static final long serialVersionUID = -7361538841293301172L;

	public FalhaDeComunicaoComEmail(String mensagem) {
		super(mensagem);
	}

	public FalhaDeComunicaoComEmail(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
