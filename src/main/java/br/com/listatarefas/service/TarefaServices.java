package br.com.listatarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.listatarefas.model.Tarefa;
import br.com.listatarefas.repository.TarefaRepository;

@Service
public class TarefaServices {

	@Autowired
	private TarefaRepository tarefaRepository;

	public Tarefa salvar(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}

	public Tarefa buscar(Long id) {
		return tarefaRepository.findOne(id);
	}
}
