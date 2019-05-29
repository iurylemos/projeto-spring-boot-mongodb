package com.iurylemos.projetomongodb.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iurylemos.projetomongodb.dominio.Post;
import com.iurylemos.projetomongodb.servicos.PostServico;

@RestController
@RequestMapping(value="/posts")
public class PostRecursos {

	@Autowired
	private PostServico servico;


	
	//Metodo para retornar por ID
	//Além do valor, tem metodo que retorna que é o GET
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		//Para dizer que o ID que está no parametro acima
		//Tem que casar com o ID recebido na URL
		//Tenho que utilizar a anotação @PathVariable
		
		
		Post obj = servico.findById(id);
		
		//minha resposta vai ser meu objeto convertido para Post
		return ResponseEntity.ok().body(obj);
	}
	
}
