/*
 * DTO Endereco
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.records.endereco;

import br.com.attornatus.cadastro.modelo.Endereco;
import br.com.attornatus.cadastro.modelo.Pessoa;
import br.com.attornatus.cadastro.repository.PessoaRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class DadosCadastroEndereco{

	@NotBlank
	private String logadouro;
	@NotBlank
	@Pattern(regexp = "\\d{8}")
	private String cep;
	@NotBlank
	private String cidade;
	@NotBlank
	@Pattern(regexp = "^\\d{1,6}")
	private String numero;
	private Boolean status;
	private Long pessoa_id;
	
	public String getLogadouro() {
		return logadouro;
	}

	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getPessoa_id() {
		return pessoa_id;
	}

	public void setPessoa_id(Long pessoa_id) {
		this.pessoa_id = pessoa_id;
	}

	public Endereco converter(PessoaRepository pessoaRepository) {
		Pessoa pessoa = pessoaRepository.getReferenceById(pessoa_id);
		return new Endereco( logadouro , cep, cidade, numero, status, pessoa);
	}




	
	

}
