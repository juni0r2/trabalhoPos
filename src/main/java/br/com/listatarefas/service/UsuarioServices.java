package br.com.listatarefas.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.listatarefas.model.AutenticacaoUsuario;
import br.com.listatarefas.model.Usuario;
import br.com.listatarefas.model.UsuarioFacebook;
import br.com.listatarefas.repository.UsuarioRepository;
import br.com.listatarefas.repository.custom.impl.UsuarioRepositoryCustom;
import br.com.listatarefas.services.exception.CamposNaoInformadosException;
import br.com.listatarefas.services.exception.EmailJaCadastrado;
import br.com.listatarefas.services.exception.EmailNaoEncontrado;
import br.com.listatarefas.services.exception.UsuarioNaoAutenticadoException;
import br.com.listatarefas.services.exception.UsuarioNaoEncontradoException;
import br.com.listatarefas.util.CriptografarSenha;
import br.com.listatarefas.util.EnviarEmail;
import br.com.listatarefas.util.LoginFacebook;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CriptografarSenha criptografiaSenha;

	@Autowired
	private UsuarioRepositoryCustom usuarioRepositoryCustom;

	@Autowired
	private EnviarEmail enviarEmail;

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public Usuario buscar(Long id) {
		Usuario usuario = usuarioRepository.findOne(id);

		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("Usuário não encontrado");
		}

		return usuario;
	}

	public Usuario salvar(Usuario usuario) {
		List<Usuario> usuarioRetorno = usuarioRepository.findAllForParameterEmail(usuario.getEmail());

		if (usuarioRetorno != null && !usuarioRetorno.isEmpty()) {
			throw new EmailJaCadastrado("Email já cadastrado no sistema.");
		}

		usuario.setId(null);
		usuario.setSenha(criptografiaSenha.criptografarSenha(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizar(Usuario user) {
		verificarExistencia(user);

		try {
			return usuarioRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new CamposNaoInformadosException("Verifique os campos informados.");
		}
	}

	private void verificarExistencia(Usuario user) {
		buscar(user.getId());
	}

	public Usuario autenticar(AutenticacaoUsuario usuario) {

		Usuario usuarioLogado = null;
		if (usuario.getToken() != null) {
			LoginFacebook loginFacebook = new LoginFacebook();
			try {
				UsuarioFacebook obterUsuarioFacebook = loginFacebook.obterUsuarioFacebook(usuario.getToken());

				usuarioLogado = new Usuario();
				usuarioLogado.setEmail(obterUsuarioFacebook.getEmail());
				usuarioLogado.setNome(obterUsuarioFacebook.getName());
				usuarioLogado.setToken(usuario.getToken());
				usuarioLogado.setSenha(criptografiaSenha.criptografarSenha(resetPassword()));
				usuarioLogado = usuarioRepository.save(usuarioLogado);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			usuarioLogado = usuarioRepository.findUserForAuthentication(usuario.getEmail(), criptografiaSenha.criptografarSenha(usuario.getSenha()));

			if (usuarioLogado == null) {
				throw new UsuarioNaoAutenticadoException("Email não existe na base; Senha não confere com o email cadastrado.");
			}
		}
		return usuarioLogado;
	}

	public Usuario resetarSenha(String email) {
		Usuario usuarioRetorno = usuarioRepository.findForParameterEmail(email);

		if (usuarioRetorno == null) {
			throw new EmailNaoEncontrado("Email não existe na base de dados.");
		}

		String newPassword = resetPassword();

		usuarioRetorno.setSenha(newPassword);
		enviarEmail.sendEmail(usuarioRetorno);

		usuarioRetorno.setSenha(criptografiaSenha.criptografarSenha(newPassword));

		return usuarioRepository.save(usuarioRetorno);
	}

	public String resetPassword() {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		System.out.println(myRandom.substring(0, 6));
		return myRandom.substring(0, 6);
	}
}
