/*
 * Classe de Pessoa
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.modelo;

import java.util.List;

import br.com.attornatus.cadastro.records.pessoa.DadosAtualizaPessoa;
import br.com.attornatus.cadastro.records.pessoa.DadosCadastroPessoa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Pessoa")
@Table(name="pessoas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String dataDeNascimento;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY )
	private List<Endereco>  endereco;
	

	public Pessoa(@Valid DadosCadastroPessoa dadosCadastraPessoa) {
		this.nome = dadosCadastraPessoa.nome();
		this.dataDeNascimento = dadosCadastraPessoa.dataDeNascimento();	
	}


	public void atualizarInformacoes(@Valid DadosAtualizaPessoa dadosAtualizaPessoa) {
		if(dadosAtualizaPessoa.nome() != null) {
			this.nome = dadosAtualizaPessoa.nome();
		}
		if(dadosAtualizaPessoa.dataDeNascimento() != null) {
			this.dataDeNascimento = dadosAtualizaPessoa.dataDeNascimento();
		}		
	}





	

}
