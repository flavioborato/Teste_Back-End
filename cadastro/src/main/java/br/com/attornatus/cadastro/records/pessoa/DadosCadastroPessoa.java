/*
 * DTO Pessoa
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.records.pessoa;

import br.com.attornatus.cadastro.modelo.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPessoa(
		
		@NotBlank
		String nome, 
		@NotBlank
		@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
		String dataDeNascimento) {

	public DadosCadastroPessoa(Pessoa pessoaAtualiza) {
		this(pessoaAtualiza.getNome(),pessoaAtualiza.getDataDeNascimento());
	}

}
