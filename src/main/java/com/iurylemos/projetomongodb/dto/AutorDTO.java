package com.iurylemos.projetomongodb.dto;

import java.io.Serializable;

import com.iurylemos.projetomongodb.dominio.Usuario;

public class AutorDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	
	public AutorDTO() {
		
	}
	/*
	 * Criar o contrustor para receber o usuário
	 * 
	 */
	public AutorDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
