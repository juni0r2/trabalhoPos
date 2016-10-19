package br.com.listatarefas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_TAREFA")
public class Tarefa implements Serializable {

	private static final long serialVersionUID = -30876912312890997L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private Boolean status;

	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "LISTA_ID")
	@JsonIgnore
	private ListaTarefas listaTarefa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ListaTarefas getListaTarefa() {
		return listaTarefa;
	}

	public void setListaTarefa(ListaTarefas listaTarefa) {
		this.listaTarefa = listaTarefa;
	}

}
