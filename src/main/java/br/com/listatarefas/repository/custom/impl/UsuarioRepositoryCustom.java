package br.com.listatarefas.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.listatarefas.model.Usuario;

@Repository
public class UsuarioRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	public Usuario findForParameter(Usuario user) {
		StringBuilder jpql = new StringBuilder();

		jpql.append("SELECT u FROM User u WHERE 1=1 ");

		if (user.getEmail() != null)
			jpql.append(" AND u.email =:email ");

		if (user.getSenha() != null)
			jpql.append(" AND u.senha =:senha");

		Query q = em.createQuery(jpql.toString());

		if (user.getEmail() != null)
			q.setParameter("email", user.getEmail());

		if (user.getSenha() != null)
			q.setParameter("senha", user.getSenha());

		Usuario userNew = null;
		try {
			userNew = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return userNew;
	}

}
