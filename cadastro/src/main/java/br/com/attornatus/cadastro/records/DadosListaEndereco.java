package br.com.attornatus.cadastro.records;

import br.com.attornatus.cadastro.modelo.Endereco;

public record DadosListaEndereco(Long id, String logadouro, String cep, String cidade, String numero, Boolean status) {
	
	public DadosListaEndereco(Endereco endereco) {
		this(endereco.getId(), endereco.getLogadouro(), endereco.getCep(),endereco.getCidade(),endereco.getNumero(), endereco.getStatus());
	}
}
