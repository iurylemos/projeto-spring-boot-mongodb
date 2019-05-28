package com.iurylemos.projetomongodb.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iurylemos.projetomongodb.dominio.Usuario;
import com.iurylemos.projetomongodb.servicos.UsuarioServico;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioRecursos {
//Para falar que essa classe vai ser um recursos Rest
//Antes da classe eu tenho que colocar @RestController
//Caminho do ENDPOINT é requestMapping
//Abre o parentese e diz o caminho
//Geralmente todos colocam o nome do recurso no plural.
	
	/*
	 * Fica bem claro as camadas o controlador
	 * RestController acessa o Servico
	 * E o Servico por sua vez acessa o Repositório
	 */
	@Autowired
	private UsuarioServico servico;

	
	/*Qual o metodo HTTP que eu vou utilizar
	 * Para esse ENDPOINT
	 * vai ser o GET, por que o GET
	 * é para obter informações no padrão REST
	 * 
	 *   No metodo abaixo
	 *   Eu vou retornar em ResponseEntity é um objeto Sofisticado
	 *   do SPRING
	 *   Esse objeto encapsula todo uma estrutura necessária
	 *   para a gente retornar respostas HTTP
	 *   já com possíveis cabeçalhos e possíveis erros
	 *   e assim em diante
	 *   */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		//Usuario maria = new Usuario("1", "Maria Brown", "maria@gmail.com");
		//Usuario alex = new Usuario("2", "Alex Green", "alex@gmail.com");
		//Criar uma nova lista de usuário
		//List é apenas uma interface e interface não se instancia
		//Para instanciar tempos que colocar uma implementação
		//Que no caso é o array list
		//List<Usuario> list = new ArrayList<>();
		/*
		 * No lugar de instanciar os objetos da classe
		 * Dessa forma abaixo eu vou lá no Servico
		 * e busco as informações do banco
		 * E depois eu devolvo essa lista na resposta da Requisição.
		 */
		List<Usuario> list = servico.findAll();
		//Macete para colocar os objetos na lista.
		//list.addAll(Arrays.asList(maria, alex));
		//Ou seja vou retornar ok, que o corpo da minha resposta é uma list
		return ResponseEntity.ok().body(list);
	}
}
