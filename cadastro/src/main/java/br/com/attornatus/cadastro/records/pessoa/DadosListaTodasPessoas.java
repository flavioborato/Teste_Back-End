/*
 * DTO Pessoa
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.records.pessoa;


import br.com.attornatus.cadastro.modelo.Pessoa;

public record DadosListaTodasPessoas(Long id, String nome, String dataDeNascimento ) {

	public DadosListaTodasPessoas(Pessoa pessoa) {
		this(pessoa.getId(), pessoa.getNome(), pessoa.getDataDeNascimento());
	}


}
