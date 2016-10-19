package br.com.listatarefas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.listatarefas.model.ListaTarefas;
import br.com.listatarefas.service.ListaTarefasServices;

@RestController
public class ListaTarefaResources {

	@Autowired
	private ListaTarefasServices listaTarefasServices;

	@RequestMapping(value = "/task_lists/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<List<ListaTarefas>> listarListasTarefas(@PathVariable("user_id") Long id) {
		List<ListaTarefas> listasDeTarefas = listaTarefasServices.listarTarefas(id);

		return ResponseEntity.status(HttpStatus.OK).body(listasDeTarefas);
	}

	@RequestMapping(value = "/create_task_lists/{user_id}", method = RequestMethod.POST)
	public ResponseEntity<ListaTarefas> adicionarTarefa(@PathVariable("user_id") Long id,
			@RequestBody ListaTarefas listaTarefas) {
		ListaTarefas listaSalva = listaTarefasServices.salvar(id, listaTarefas);

		return ResponseEntity.status(HttpStatus.CREATED).body(listaSalva);
	}

	@RequestMapping(value = "/update_task_list/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ListaTarefas> atualizar(@RequestBody ListaTarefas lista, @PathVariable("id") Long id) {
		lista.setId(id);
		ListaTarefas listaTarefasSalva = listaTarefasServices.atualizar(lista);

		return ResponseEntity.status(HttpStatus.OK).body(listaTarefasSalva);
	}

	@RequestMapping(value = "/remove_task_list/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delelar(@PathVariable("id") Long id) {
		try {
			listaTarefasServices.deletar(id);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
