package com.iurylemos.projetomongodb.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iurylemos.projetomongodb.dominio.Post;

@Repository
public interface PostRepositorio extends MongoRepository<Post, String> {
	//Esse MongoRepository precisa de dois dados
	//No 1º Parametro precisa do tipo da classe de dominio que
	//ele vai gerencia
	//No 2º Parametro, preciso falar o tipo de ID
	//Que no caso é STRING
	/*
	 * Com o MongoRepository
	 * O UsuarioRepositorio vai ser capaz de realizar várias operações
	 * básicas com os meus usuários. SALVAR,DELETAR, RECUPERAR
	 * Tudo isso está embutido no MongoRepository
	 */
}
