package br.com.listatarefas.services.exception;

public class UsuarioNaoEncontradoException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1389032981289067208L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(String mensagem, Throwable causa){
		super(mensagem, causa);
	}
}
