package br.com.listatarefas.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.listatarefas.services.exception.CamposNaoInformadosException;
import br.com.listatarefas.services.exception.EmailJaCadastrado;
import br.com.listatarefas.services.exception.EmailNaoEncontrado;
import br.com.listatarefas.services.exception.FalhaDeComunicaoComEmail;
import br.com.listatarefas.services.exception.ListaTaredasNaoEncontradoException;
import br.com.listatarefas.services.exception.TokenFacebookException;
import br.com.listatarefas.services.exception.UsuarioNaoAutenticadoException;
import br.com.listatarefas.services.exception.UsuarioNaoEncontradoException;
import br.com.listatarefas.util.DetalhesErro;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(404l);
		detalhesErro.setTitulo("O usuario não pode ser encontrado.");
		detalhesErro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}

	@ExceptionHandler(ListaTaredasNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleListaTarefaNaoEncontradoException(ListaTaredasNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(404l);
		detalhesErro.setTitulo("Lista de Tarefas não encontrada para este usuário.");
		detalhesErro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}

	@ExceptionHandler(UsuarioNaoAutenticadoException.class)
	public ResponseEntity<DetalhesErro> handleUsuarioNaoAutenticadoException(UsuarioNaoAutenticadoException e,
			HttpServletRequest request) {

		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(401l);
		detalhesErro.setTitulo("Email não existe na base; Senha não confere com o email cadastrado.");
		detalhesErro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}

	@ExceptionHandler(EmailNaoEncontrado.class)
	public ResponseEntity<DetalhesErro> handleEmailNaoEncontradoException(EmailNaoEncontrado e,
			HttpServletRequest request) {

		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(401l);
		detalhesErro.setTitulo("Email não existe na base; Senha não confere com o email cadastrado.");
		detalhesErro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}

	@ExceptionHandler(FalhaDeComunicaoComEmail.class)
	public ResponseEntity<DetalhesErro> handleFalhaDeComunicaoComEmailException(FalhaDeComunicaoComEmail e,
			HttpServletRequest request) {

		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(401l);
		detalhesErro.setTitulo("Falha ao enviar email.");
		detalhesErro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}

	@ExceptionHandler(EmailJaCadastrado.class)
	public ResponseEntity<DetalhesErro> handleEmailJaCadastradoException(EmailJaCadastrado e,
			HttpServletRequest request) {

		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(401l);
		detalhesErro.setTitulo("Email já cadastrado no sistema.");
		detalhesErro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}

	@ExceptionHandler(TokenFacebookException.class)
	public ResponseEntity<DetalhesErro> handleTokenFacebookException(TokenFacebookException e,
			HttpServletRequest request) {

		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(404l);
		detalhesErro.setTitulo("Erro ao autenticar com Facebook.");
		detalhesErro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}

	
	@ExceptionHandler(CamposNaoInformadosException.class)
	public ResponseEntity<DetalhesErro> handleCamposNaoInformadosException(CamposNaoInformadosException e,
			HttpServletRequest request) {

		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(404l);
		detalhesErro.setTitulo("Verifique se todos os campos obrigatórios foram informados.");
		detalhesErro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}
}
