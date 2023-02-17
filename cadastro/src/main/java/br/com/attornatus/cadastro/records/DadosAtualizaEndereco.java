package br.com.attornatus.cadastro.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizaEndereco(
		
		Long id,
		String logadouro,
		String cep,
		String cidade,
		String numero,
		Boolean status) {

}
