/*
 * DTO Pessoa
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.records.pessoa;


import org.springframework.data.domain.Page;
import br.com.attornatus.cadastro.modelo.Pessoa;
import br.com.attornatus.cadastro.records.endereco.DadosListaEndereco;


public record DadosListaPessoaEnderecoPrincipal(Long id, String nome, String dataDeNascimento, Page<DadosListaEndereco> endereco) {

	public DadosListaPessoaEnderecoPrincipal(Pessoa pessoa, Page<DadosListaEndereco> endereco ) {
		this(pessoa.getId(), pessoa.getNome(), pessoa.getDataDeNascimento(), endereco);
	}





}
