package com.iurylemos.projetomongodb.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.iurylemos.projetomongodb.dominio.Post;

@Repository
public interface PostRepositorio extends MongoRepository<Post, String> {
	
	//Essa consulta query dentro tem que ter um JASON
	//Ou seja a consulta.
	//<field>: { $regex: /pattern/, $options: '<options>'} }
	//No lugar do field é o titulo
	// no lugar do pattern que é a expressão regular
	//vai ser o texto que veio como parametro
	//e para isso eu coloco o ?0
	//ou seja vai ser o primeiro parametro que vier no metodo
	//no lugar do options vai ser o i
	// que ignora os maisculas e minusculas.
	@Query("{'titulo': { $regex: ?0, $options: 'i'} }")
	List<Post> procurarTitulo(String texto);
	//Esse metodo vai me retornar uma lista de post.
	//Buscando pelo Titutlo do Post
	List<Post> findByTituloContainingIgnoreCase(String texto);
	
	//Especificando a consulta com o @Query
	//Vou ter que buscar o texto
	//OU no titulo, ou no corpo ou nos comentários.
	//Depois do Ou, vou ter que pesquisar 
	//a data Minima E a dataMaxima
	//No primeiro field, esse $gte
	//faz a comparação se o data é maior do que o parametro
	//no caso coloquei ?1 para identificar que é a posição 2
	//no caso a data minima
	//No 2º eu testo se a data é menor igual a data maxima
	//NO 3º vou testar o texto se ele se encontra
	@Query("{ $and: [ { data: {$gte: ?1}}, {data: {$lte: ?2} } , { $or: [ {'titulo': { $regex: ?0, $options: 'i'} }, {'corpo': { $regex: ?0, $options: 'i'} }, {'comentarios.texto': { $regex: ?0, $options: 'i'} } ] } ] }")
	List<Post> pesquisaCompleta(String texto, Date minData, Date maxData);
}
