package com.iurylemos.projetomongodb.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iurylemos.projetomongodb.dominio.Post;

@Repository
public interface PostRepositorio extends MongoRepository<Post, String> {
	
	//Esse metodo vai me retornar uma lista de post.
	//Buscando pelo Titutlo do Post
	List<Post> findByTituloContainingIgnoreCase(String texto);
}
