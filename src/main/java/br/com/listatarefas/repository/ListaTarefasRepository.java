package br.com.listatarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.listatarefas.model.ListaTarefas;

public interface ListaTarefasRepository extends JpaRepository<ListaTarefas, Long>{

}
