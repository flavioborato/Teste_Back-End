package br.com.attornatus.cadastro.modelo;

import br.com.attornatus.cadastro.records.DadosAtualizaEndereco;
import br.com.attornatus.cadastro.records.DadosCadastroEndereco;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="Endereco")
@Table(name="enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logadouro;
	private String cep;
	private String cidade;
	private String numero;
	private Boolean status;

	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;
	
	
	public Endereco(DadosCadastroEndereco dadosCadastraEndereco) {
		this.logadouro = dadosCadastraEndereco.logadouro();
		this.cep = dadosCadastraEndereco.cep();
		this.cidade = dadosCadastraEndereco.cidade();
		this.numero = dadosCadastraEndereco.numero();
		this.status = dadosCadastraEndereco.status();
	}


	public void atualizarInformacoes(@Valid DadosAtualizaEndereco dadosAtualizaEndereco) {
		if(dadosAtualizaEndereco.logadouro() != null) {
			this.logadouro = dadosAtualizaEndereco.logadouro();
		}
		if(dadosAtualizaEndereco.cep() != null) {
			this.cep = dadosAtualizaEndereco.cep();
		}
		if(dadosAtualizaEndereco.cidade() != null) {
			this.cidade = dadosAtualizaEndereco.cidade();
		}
		if(dadosAtualizaEndereco.numero() != null) {
			this.numero = dadosAtualizaEndereco.numero();
		}
		this.status = dadosAtualizaEndereco.status();
		
	}
}
