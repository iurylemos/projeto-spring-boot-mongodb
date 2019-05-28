package com.iurylemos.projetomongodb.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iurylemos.projetomongodb.dominio.Usuario;
import com.iurylemos.projetomongodb.repositorio.UsuarioRepositorio;

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

}
