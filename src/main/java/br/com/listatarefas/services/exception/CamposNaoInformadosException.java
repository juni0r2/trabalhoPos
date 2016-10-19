package br.com.listatarefas.services.exception;

public class CamposNaoInformadosException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6994092250980847827L;

	public CamposNaoInformadosException(String mensagem) {
		super(mensagem);
	}

	public CamposNaoInformadosException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
