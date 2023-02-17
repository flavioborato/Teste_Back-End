package br.com.attornatus.cadastro.records;

import br.com.attornatus.cadastro.modelo.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
		
		@NotBlank
		String logadouro,
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		String cep,
		@NotBlank
		String cidade,
		@NotBlank
		@Pattern(regexp = "^\\d{1,6}")
		String numero,
		Boolean status
	
		
		) {

	public DadosCadastroEndereco(Endereco enderecoAtualiza) {
		this(enderecoAtualiza.getLogadouro(),enderecoAtualiza.getCep(),enderecoAtualiza.getCidade(),enderecoAtualiza.getNumero(),enderecoAtualiza.getStatus());
	}

}
