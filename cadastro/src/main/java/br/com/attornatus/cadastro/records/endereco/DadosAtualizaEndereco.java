/*
 * DTO Endereco
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.records.endereco;

public record DadosAtualizaEndereco(
		
		Long id,
		String logadouro,
		String cep,
		String cidade,
		String numero,
		Boolean status) {

}
