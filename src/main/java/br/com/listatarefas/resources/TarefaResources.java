package br.com.listatarefas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.listatarefas.model.ListaTarefas;
import br.com.listatarefas.model.Tarefa;
import br.com.listatarefas.service.ListaTarefasServices;
import br.com.listatarefas.service.TarefaServices;

@RestController
public class TarefaResources {

	@Autowired
	private ListaTarefasServices listaTarefasService;

	@Autowired
	private TarefaServices tarefaServices;

	@RequestMapping(value = "/task_lists/{id}/tasks", method = RequestMethod.POST)
	public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa, @PathVariable("id") Long id) {

		ListaTarefas lista = listaTarefasService.buscar(id);

		tarefa.setListaTarefa(lista);

		Tarefa tarefaNew = tarefaServices.salvar(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaNew);
	}

	@RequestMapping(value = "/task_lists/{task_list_id}/tasks/{task_id}", method = RequestMethod.PUT)
	public ResponseEntity<Tarefa> atualizar(@PathVariable("task_list_id") Long idListaTarefa, @PathVariable("task_id") Long idTarefa,
			@RequestBody Tarefa tarefa) {

		ListaTarefas listaTarefas = listaTarefasService.buscar(idListaTarefa);
		Tarefa tarefaNew = tarefaServices.buscar(idTarefa);
		tarefaNew.setListaTarefa(listaTarefas);
		tarefaNew.setStatus(tarefa.getStatus());
		tarefaServices.salvar(tarefaNew);

		return ResponseEntity.status(HttpStatus.OK).body(tarefaNew);
	}
}
