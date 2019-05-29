package com.iurylemos.projetomongodb.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iurylemos.projetomongodb.dominio.Usuario;
import com.iurylemos.projetomongodb.dto.UsuarioDTO;
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
	
	//Troquei no argumento do metodo abaixo o Usuario para UsuarioDTO
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll() {
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
		//Convertendo uma lista de Usuario para lista de UsuarioDTO
		/*
		 * Criei uma lista de DTO, e botei a lista.stream
		 * stream é uma coleção compátivel
		 * map = pega cada objeto, que chamei de x para representar a lista
		 * e para cada objeto desse que vai ser um usuário 
		 * eu vou retornar um new UsuarioDTO
		 * passsando o X como argumento
		 * . map x que representa a lista original 
		 * e para voltar o stream para uma lista
		 * eu utilizo o collect..
		 */
		List<UsuarioDTO> listDto = list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	//Metodo para retornar por ID
	//Além do valor, tem metodo que retorna que é o GET
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> findById(@PathVariable String id) {
		//Para dizer que o ID que está no parametro acima
		//Tem que casar com o ID recebido na URL
		//Tenho que utilizar a anotação @PathVariable
		
		
		Usuario obj = servico.findById(id);
		
		//minha resposta vai ser meu objeto convertido para UsuarioDTO
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UsuarioDTO objDto) {
		Usuario obj = servico.fromDTO(objDto);
		obj = servico.insert(obj);
	/*
	 * Vou retornar uma resposta vázia
	 * porém nessa resposta eu vou colocar um cabeçalho
	 * com a URL com o novo recurso criado.
	 * E isso vai pegar o novo endereço do objeto que eu INSERIR
	 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//Created retorna o 201 que é codigo HTTP quando cria um novo RECURSO
		return ResponseEntity.created(uri).build();
	}
	
	//Delete
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		//Como vai vim pela URL recebo pelo PathVariable
		servico.delete(id);
		//Não retorna nada, então é erro 204 que é o noContent
		return ResponseEntity.noContent().build();
	}
}
