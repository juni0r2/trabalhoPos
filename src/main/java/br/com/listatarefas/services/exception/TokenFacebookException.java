package br.com.listatarefas.services.exception;

public class TokenFacebookException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7237729936767997053L;

	public TokenFacebookException(String mensagem) {
		super(mensagem);
	}

	public TokenFacebookException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
