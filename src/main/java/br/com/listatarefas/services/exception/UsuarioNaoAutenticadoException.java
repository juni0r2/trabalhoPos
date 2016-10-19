package br.com.listatarefas.services.exception;

public class UsuarioNaoAutenticadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoAutenticadoException(String mensagem) {
		super(mensagem);
	}

	public UsuarioNaoAutenticadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
