package br.com.attornatus.cadastro.records;

import java.util.List;

import br.com.attornatus.cadastro.modelo.Endereco;
import br.com.attornatus.cadastro.modelo.Pessoa;

public record DadosListaPessoa(Long id, String nome, String dataDeNascimento, List<Endereco> endereco ) {

	public DadosListaPessoa(Pessoa pessoa) {
		this(pessoa.getId(), pessoa.getNome(), pessoa.getDataDeNascimento(), pessoa.getEndereco());
	}


}
