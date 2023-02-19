/*
 * Projeto : Cadastro de Pessoas
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class CadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroApplication.class, args);
	}

}
