package br.com.listatarefas.services.exception;

public class EmailNaoEncontrado extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6092565407164153594L;

	public EmailNaoEncontrado(String mensagem) {
		super(mensagem);
	}

	public EmailNaoEncontrado(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
