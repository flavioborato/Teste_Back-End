package br.com.attornatus.cadastro.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPessoa(
		
		@NotBlank
		String nome, 
		@NotBlank
		@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
		String dataDeNascimento) {

}
