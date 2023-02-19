/*
 * DTO Endereco
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.records.endereco;

import java.util.List;

import br.com.attornatus.cadastro.modelo.Endereco;


public record DadosListaEndereco(Long id, String logadouro, String cep, String cidade, String numero, Boolean status) {
	
	public DadosListaEndereco(Endereco endereco) {
		this(endereco.getId(), endereco.getLogadouro(), endereco.getCep(),endereco.getCidade(),endereco.getNumero(), endereco.getStatus());
	}


}
