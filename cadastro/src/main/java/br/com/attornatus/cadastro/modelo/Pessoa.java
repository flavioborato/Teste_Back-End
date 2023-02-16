package br.com.attornatus.cadastro.modelo;

import java.util.List;

import br.com.attornatus.cadastro.records.DadosCadastroPessoa;
import br.com.attornatus.cadastro.records.DadosListaPessoa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	

	public Pessoa(DadosCadastroPessoa dadosCadastraPessoa) {
		this.nome = dadosCadastraPessoa.nome();
		this.dataDeNascimento = dadosCadastraPessoa.dataDeNascimento();	
	}
	

}
