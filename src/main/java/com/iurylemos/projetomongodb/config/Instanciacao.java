package com.iurylemos.projetomongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.iurylemos.projetomongodb.dominio.Usuario;
import com.iurylemos.projetomongodb.repositorio.UsuarioRepositorio;

//Para o SPRING entender que é uma configuração 
@Configuration
public class Instanciacao implements CommandLineRunner {
	
	/*
	 	Injetar aqui dentro o nosso UsuarioRepositorio
	 	Para fazer a conexão com o Banco de Dados
	 */
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public void run(String... args) throws Exception {
		/* Nesse metodo vamos intanciar os objetos
		 * E amarzenar no banco de dados. */
		
		usuarioRepositorio.deleteAll(); //Limpar lá no mongodb
		
		
		Usuario maria = new Usuario(null, "Maria Brown", "maria@gmail.com");
		Usuario alex = new Usuario(null, "Alex Green", "alex@gmail.com");
		Usuario bob = new Usuario(null, "Bob Grey", "bob@gmail.com");
		
		//Salvar na coleção.
		//Dentro do salve coloco uma lista direto
		//Arrays.aslist, já vai aceitar vamos argumentos e criar uma lista para mim
		usuarioRepositorio.saveAll(Arrays.asList(maria, alex, bob));
		
		
		
	}

}
