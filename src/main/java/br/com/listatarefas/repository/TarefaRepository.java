package br.com.listatarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.listatarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
