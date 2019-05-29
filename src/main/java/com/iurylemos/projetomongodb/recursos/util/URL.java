package com.iurylemos.projetomongodb.recursos.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	//Statico para não precisar instanciar um objeto.
	public static String decodificarParametro(String text) {
		//1º Parametro qual o texto que quero decodificar
		//2º Qual vai ser o padrão.
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//Caso ocorra algum erro, retorne vázio.
			return "";
		}
	}
}
