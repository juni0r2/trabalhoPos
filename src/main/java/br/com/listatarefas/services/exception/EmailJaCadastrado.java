package br.com.listatarefas.services.exception;

public class EmailJaCadastrado extends RuntimeException {

	private static final long serialVersionUID = -4372661156310545653L;

	public EmailJaCadastrado(String mensagem) {
		super(mensagem);
	}

	public EmailJaCadastrado(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
