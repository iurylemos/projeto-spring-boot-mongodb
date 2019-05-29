package com.iurylemos.projetomongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.iurylemos.projetomongodb.dominio.Post;
import com.iurylemos.projetomongodb.dominio.Usuario;
import com.iurylemos.projetomongodb.dto.AutorDTO;
import com.iurylemos.projetomongodb.repositorio.PostRepositorio;
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
	
	@Autowired
	private PostRepositorio postRepositorio;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Passando o horário de londres
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		/* Nesse metodo vamos intanciar os objetos
		 * E amarzenar no banco de dados. */
		
		usuarioRepositorio.deleteAll(); //Limpar no mongodb
		postRepositorio.deleteAll();
		
		
		Usuario maria = new Usuario(null, "Maria Brown", "maria@gmail.com");
		Usuario alex = new Usuario(null, "Alex Green", "alex@gmail.com");
		Usuario bob = new Usuario(null, "Bob Grey", "bob@gmail.com");
		
		usuarioRepositorio.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo, Abraços!", new AutorDTO(maria));
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje!", new AutorDTO(maria));
		//Salvar na coleção.
		//Dentro do salve coloco uma lista direto
		//Arrays.aslist, já vai aceitar vamos argumentos e criar uma lista para mim
		
		postRepositorio.saveAll(Arrays.asList(post1, post2));
		
		//Adicionando os posts ao usuário da maria.
		maria.getPost().addAll(Arrays.asList(post1, post2));
		usuarioRepositorio.save(maria);
	
	}

}
