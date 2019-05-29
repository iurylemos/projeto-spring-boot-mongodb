package com.iurylemos.projetomongodb.recursos.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
	
	//Metodo para tratar datas recebidas
	//No 2º parametro é caso ocorra uma falha
	//Eu retorne uma data padrão.
	public static Date converterData(String textData, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Abaixo é polêmica
		//Pois muitos utilizam para buscar o horário da máquina que enviou.
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textData);
		} catch (ParseException e) {
			return defaultValue;
		}
		
	}
}
