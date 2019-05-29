package com.iurylemos.projetomongodb.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iurylemos.projetomongodb.dominio.Usuario;
import com.iurylemos.projetomongodb.dto.UsuarioDTO;
import com.iurylemos.projetomongodb.repositorio.UsuarioRepositorio;
import com.iurylemos.projetomongodb.servicos.excessao.ObjetoNaoEncontradoException;

@Service
public class UsuarioServico {
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
	private UsuarioRepositorio repo;
	/*
	 * Quando declarar um objeto
	 */
	
	
	
	public List<Usuario> findAll() {
		//Retornar todos os usuários do banco
		return repo.findAll();
	}
	
	public Usuario findById(String id) {
		/*
		 * Esse findOne voce passa o ID ele retorna o usuario
		 * se não tiver o ID, o metodo vai retornar nulo
		 */
		Optional<Usuario> usuario = repo.findById(id);
		
		return usuario.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
	}
	
	//Inserção.
	
	public Usuario insert(Usuario obj) {
		return repo.insert(obj);
	}
	
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	
	
	//metodo fromDTO
	/*
	 * Vai pegar um DTO e instanciar um usuário.
	 * É a mesma coisa que fizemos na classe UsuarioDTO
	 * com o contrutor dela, passando passando um Usuario
	 * Só que aqui é ao contrário, é do DTO para o usuário.
	 */
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail());
	}
}
