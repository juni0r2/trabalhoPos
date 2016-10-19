package br.com.listatarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.listatarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u where u.email = :email AND u.senha = :senha") 
    Usuario findUserForAuthentication(@Param("email") String email, @Param("senha") String senha);
	
	@Query("SELECT u FROM Usuario u WHERE u.email =:email")
	Usuario findForParameterEmail(@Param("email") String email);

	@Query("SELECT u FROM Usuario u WHERE u.email =:email")
	List<Usuario> findAllForParameterEmail(@Param("email") String email);
}
