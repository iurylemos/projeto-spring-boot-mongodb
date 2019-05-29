package com.iurylemos.projetomongodb.dto;

import java.io.Serializable;
import java.util.Date;

public class ComentarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String textoComentario;
	private Date data;
	private AutorDTO autor;
	
	public ComentarioDTO() {
		
	}

	public ComentarioDTO(String textoComentario, Date data, AutorDTO autor) {
		super();
		this.textoComentario = textoComentario;
		this.data = data;
		this.autor = autor;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public AutorDTO getAutor() {
		return autor;
	}

	public void setAutor(AutorDTO autor) {
		this.autor = autor;
	}

}
