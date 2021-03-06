package com.iurylemos.projetomongodb.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//Dizer que essa classe corresponde a uma coleção do MONGODB
@Document(collection="usuario") 
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id //Dizendo que é o ID
	private String id;
	private String nome;
	private String email;
	
	//List de post.
	//lazy = true = não carregar automaticamente os posts
	//quando pesquisar por um usuário do banco
	//não quero que venha os post deles
	//e sim os dados básicos para encontra-lo
	//DBRef dizendo que é outra coleção do DBMongo
	@DBRef(lazy = true)
	private List<Post> post = new ArrayList<>();
	
	//Serializable = Objetos convertidos em bytes
	//Para ser trafegado em redes e arquivos.
	public Usuario() {
		
	}
	
	public Usuario(String id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
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
	
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
