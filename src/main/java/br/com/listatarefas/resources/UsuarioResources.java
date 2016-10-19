package br.com.listatarefas.resources;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.listatarefas.model.AutenticacaoUsuario;
import br.com.listatarefas.model.Usuario;
import br.com.listatarefas.service.UsuarioServices;

@EnableOAuth2Sso
@RestController
public class UsuarioResources {

	@Autowired
	private UsuarioServices usuarioServices;

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	@RequestMapping(value = "/create_user", method = RequestMethod.POST)
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario user) {
		Usuario userSalvo = usuarioServices.salvar(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userSalvo);
	}

	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario user, @PathVariable("user_id") Long id) {
		Usuario userBD = usuarioServices.buscar(id);
		user.setId(userBD.getId());
		Usuario userSave = usuarioServices.atualizar(user);

		return ResponseEntity.status(HttpStatus.OK).body(userSave);
	}

	@RequestMapping(value = "/user_authenticate", method = RequestMethod.POST)
	public ResponseEntity<Usuario> autenticarUsuario(@RequestBody AutenticacaoUsuario usuario) {

		Usuario usuarioAutenticado = usuarioServices.autenticar(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioAutenticado);
	}

	@RequestMapping(value = "/users/forgot_password", method = RequestMethod.POST)
	public ResponseEntity<Void> resetPassword(@RequestBody Usuario user) {
		usuarioServices.resetarSenha(user.getEmail());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value = "/search/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("user_id") Long id) {
		Usuario user = usuarioServices.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioServices.listar());
	}
}
