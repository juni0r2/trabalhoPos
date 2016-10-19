package br.com.listatarefas.services.exception;

public class ListaTaredasNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1351903637961596987L;

	public ListaTaredasNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ListaTaredasNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
