package com.iurylemos.projetomongodb.recursos.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iurylemos.projetomongodb.servicos.excessao.ObjetoNaoEncontradoException;

//Tratar possíveis erros nas minhas requisições.
@ControllerAdvice
public class RecursosExceptionHandler {
	/*
	 * Manipulador de erros.
	 * 
	 * Metodo particular
	 * tem que ter o ExceptionHandler para funcionar
	 * e identificar quando ocorrer a excessao
	 * que está no parametro
	 * ele vai fazer o tratamento abaixo.
	 */

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(ObjetoNaoEncontradoException e, HttpServletRequest request) {
		//Aqui dentro vai ter um tratamento para a excessao
		HttpStatus status = HttpStatus.NOT_FOUND;
		//System.currentTimeMillis() = Pega o estante atual do sistema
		StandardError erro = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		//status para que eu possa controlar manualmente qual o código
		// de status que a minha requisição vai retornar
		return ResponseEntity.status(status).body(erro);
	}
}
