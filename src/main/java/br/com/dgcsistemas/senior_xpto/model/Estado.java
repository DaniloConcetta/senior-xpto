package br.com.dgcsistemas.senior_xpto.model;

import java.io.Serializable;

public class Estado implements Serializable {
	


	private static final long serialVersionUID = 1L;
	private String nome;
	private Long quantidadeCidade;
	
	public Estado(String nome, Long quantidadeCidade) {
		super();
		this.nome = nome;
		this.quantidadeCidade = quantidadeCidade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getQuantidadeCidade() {
		return quantidadeCidade;
	}
	public void setQuantidadeCidade(Long quantidadeCidade) {
		this.quantidadeCidade = quantidadeCidade;
	}


	
	

}
