package com.iurylemos.projetomongodb.dto;

import java.io.Serializable;

import com.iurylemos.projetomongodb.dominio.Usuario;

public class UsuarioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private String email;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario obj) {
		/*
		 * Forma automatizada já, de instanciar um UsuarioDTO
		 * a partir do Usuario
		 * 
		 * O que é que vai ser instanciar um UsuarioDTO
		 * a partir do Usuario?
		 * O campo ID do usuario DTO
		 * vai receber o obj.getId()
		 * Ou seja a gente copia os dados do usuário
		 * e atribui ao DTO
		 */
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
