package br.com.listatarefas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.listatarefas.model.ListaTarefas;
import br.com.listatarefas.model.Usuario;
import br.com.listatarefas.repository.ListaTarefasRepository;
import br.com.listatarefas.services.exception.ListaTaredasNaoEncontradoException;

@Service
public class ListaTarefasServices {

	@Autowired
	private ListaTarefasRepository listaTarefasRepository;

	@Autowired
	private UsuarioServices userServices;

	public ListaTarefas salvar(Long id, ListaTarefas listaTarefas) {
		Usuario user = userServices.buscar(id);

		listaTarefas.setUsuario(user);
		return listaTarefasRepository.save(listaTarefas);
	}

	public List<ListaTarefas> listarTarefas(Long id) {
		Usuario user = userServices.buscar(id);

		return user.getTarefas();
	}
	
	public ListaTarefas buscar(Long id) {
		ListaTarefas lista = listaTarefasRepository.findOne(id);

		if (lista == null) {
			throw new ListaTaredasNaoEncontradoException("Lista de Tarefas não encontrada.");
		}

		return lista;
	}
	
	public ListaTarefas atualizar(ListaTarefas lista) {
		verificarExistencia(lista.getId());
		return listaTarefasRepository.save(lista);
	}

	private void verificarExistencia(Long idlista) {
		buscar(idlista);
	}
	
	public void deletar(Long id){
		verificarExistencia(id);
		listaTarefasRepository.delete(id);
	}
}
