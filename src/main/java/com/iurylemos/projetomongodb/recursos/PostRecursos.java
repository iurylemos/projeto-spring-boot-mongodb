package com.iurylemos.projetomongodb.recursos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iurylemos.projetomongodb.dominio.Post;
import com.iurylemos.projetomongodb.recursos.util.URL;
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
	
	//Criando ENDPOINT para fazer a busca.
	@RequestMapping(value= "/buscartitulo", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitulo(@RequestParam(value="text", defaultValue="") String texto) {
		/*
		 * Vai ser uma List de Post como retorno
		 * O meu critério de busca vai ser passado como um parametro
		 * com esse interrogação aqui ? e não como uma variavel de url que tem a barra
		 * no parametro vai ser um
		 * @RequestParam(value="text") isso é para o ENDPOINT
		 * identificar o nome depois do ? que é o text=
		 * e tenho que colocar do lado o defaultValue
		 * OU SEJA SE O PARAMETRO do text NÃO FOR INFORMADO
		 * VOU COLOCAR UMA STRING VÁZIA NELE pelo DefaultValue
		 */
		
		//Decodificar o parametro
		texto = URL.decodificarParametro(texto);
		List<Post> list = servico.findByTitulo(texto);
		//minha resposta vai ser meu objeto convertido para Post
		return ResponseEntity.ok().body(list);
	}
	
	//ENDPOINT
	@RequestMapping(value= "/pesquisacompleta", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> pesquisaCompleta(
			@RequestParam(value="text", defaultValue="") String texto,
			@RequestParam(value="minData", defaultValue="") String minData,
			@RequestParam(value="maxData", defaultValue="") String maxData)
		{
		
		//Decodificar o parametro
		texto = URL.decodificarParametro(texto);
		//O padrão vai ser o 0L na data minima que é 1º de janeiro de 1970
		Date min = URL.converterData(minData, new Date(0L));
		//O padrão aqui vai ser a data atual.
		Date max = URL.converterData(maxData, new Date());
		List<Post> list = servico.pesquisaCompleta(texto, min, max);
		//minha resposta vai ser meu objeto convertido para Post
		return ResponseEntity.ok().body(list);
	}
	
}
