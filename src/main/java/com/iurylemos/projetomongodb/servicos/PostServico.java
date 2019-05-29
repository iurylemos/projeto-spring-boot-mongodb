package com.iurylemos.projetomongodb.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iurylemos.projetomongodb.dominio.Post;
import com.iurylemos.projetomongodb.repositorio.PostRepositorio;
import com.iurylemos.projetomongodb.servicos.excessao.ObjetoNaoEncontradoException;

@Service
public class PostServico {
	/*
	  EM 1º LUGAR para dizer pro SPRING
	  que essa classe vai ser um servico
	  que vai ser injetável em outras classe
	  Eu tenho que colocar o @Service
	  
	  
	  
	 */
	
	//Para instanciar automaticamente um objeto aqui no Servico
	//Se utiliza o @Autowired
	//Isso é um mecanismo de injeção de depedencia automatico do SPRING
	@Autowired
	private PostRepositorio repo;
	/*
	 * Quando declarar um objeto
	 */
	
	
	
	
	public Post findById(String id) {
		/*
		 * Esse findOne voce passa o ID ele retorna o usuario
		 * se não tiver o ID, o metodo vai retornar nulo
		 */
		Optional<Post> usuario = repo.findById(id);
		
		return usuario.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
	}
	
	//Metodo de busca
	//Criei esse metodo na interface PostRepositorio.
	
	public List<Post> findByTitulo(String texto) {
		return repo.findByTituloContainingIgnoreCase(texto);
	}

}
