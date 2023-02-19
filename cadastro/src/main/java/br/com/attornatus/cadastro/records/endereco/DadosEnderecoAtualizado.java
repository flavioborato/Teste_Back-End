/*
 * DTO Endereco
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.records.endereco;

import br.com.attornatus.cadastro.modelo.Endereco;

public record DadosEnderecoAtualizado(
		
		String logadouro,
		String cep,
		String cidade,
		String numero,
		Boolean status
		
		) {

	public DadosEnderecoAtualizado(Endereco enderecoAtualiza) {
		this(enderecoAtualiza.getLogadouro(),enderecoAtualiza.getCep(),
				enderecoAtualiza.getCidade(),enderecoAtualiza.getNumero(),
				enderecoAtualiza.getStatus());
	}

	

}
