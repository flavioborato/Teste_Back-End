/*
 * Controle de Requisição das Pessoas
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.attornatus.cadastro.modelo.Endereco;
import br.com.attornatus.cadastro.modelo.Pessoa;
import br.com.attornatus.cadastro.records.endereco.DadosListaEndereco;
import br.com.attornatus.cadastro.records.pessoa.DadosAtualizaPessoa;
import br.com.attornatus.cadastro.records.pessoa.DadosCadastroPessoa;
import br.com.attornatus.cadastro.records.pessoa.DadosListaCompletaPessoa;
import br.com.attornatus.cadastro.records.pessoa.DadosListaPessoaEnderecoPrincipal;
import br.com.attornatus.cadastro.records.pessoa.DadosListaTodasPessoas;
import br.com.attornatus.cadastro.repository.EnderecoRepository;
import br.com.attornatus.cadastro.repository.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	/*
	 * Lista completa com endereco de pessoa por ID informado
	 * */
	@GetMapping("/{id}")
	@Cacheable(value = "lista")
	public ResponseEntity listaPorId(@PathVariable Long id,@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
		var idValido = 	pessoaRepository.findById(id);
		if(idValido.isPresent()) {
			var endereco = enderecoRepository.getAllByPessoaId(id,pageable).map(DadosListaEndereco::new);
			var pessoa = pessoaRepository.getReferenceById(id);
			return ResponseEntity.ok(new DadosListaCompletaPessoa(pessoa , endereco));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/*
	 * Lista endereco de pessoa por ID informado e Status true(Endereco Principal)
	 * */
	@GetMapping("/{id}/{status}")
	@Cacheable(value = "lista")
	public ResponseEntity listaPorId(@PathVariable Long id,@PathVariable Boolean status,@PageableDefault Pageable pageable){
		var idValido = 	pessoaRepository.findById(id);
		if(idValido.isPresent()) {
			var endereco = enderecoRepository.buscaPessoaEnderecoPrincipal(id, status, pageable).map(DadosListaEndereco::new);
			var pessoa = pessoaRepository.getReferenceById(id);
			return ResponseEntity.ok(new DadosListaPessoaEnderecoPrincipal(pessoa , endereco));
			
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/*
	 * Lista todas as pessoas (sem o endereço) 
	 * */
	@GetMapping
	@Cacheable(value = "lista")
	public ResponseEntity<Page<DadosListaTodasPessoas>> listaGeral(@PageableDefault(size = 20, sort = {"nome"}) Pageable pageable) {
		var page = pessoaRepository.findAll(pageable).map(DadosListaTodasPessoas::new);
		return ResponseEntity.ok(page);
	}
	
	/*
	 * Cadastra nova Pessoa
	 * */
	@PostMapping
	@Transactional
	@CacheEvict(value = "lista", allEntries = true)
	public ResponseEntity cadastroPessoa (@RequestBody @Valid DadosCadastroPessoa dadosCadastraPessoa, UriComponentsBuilder uriBuilder) {
		var pessoa = new Pessoa(dadosCadastraPessoa);
		pessoaRepository.save(pessoa);
		
		var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListaTodasPessoas(pessoa));
	}
	
	/*
	 * Altera Pessoa Cadastrada
	 * */
	@PutMapping
	@Transactional
	@CacheEvict(value = "lista", allEntries = true)
	public ResponseEntity atualizar(@RequestBody @Valid  DadosAtualizaPessoa dadosAtualizaPessoa) {
		var idValido = 	pessoaRepository.findById(dadosAtualizaPessoa.id());
		if(idValido.isPresent()) {
			System.out.println("Entrou na funcao");
		var pessoaAtualiza = pessoaRepository.getReferenceById(dadosAtualizaPessoa.id());
			pessoaAtualiza.atualizarInformacoes(dadosAtualizaPessoa);
			return ResponseEntity.ok(new DadosCadastroPessoa(pessoaAtualiza));
		}
		return ResponseEntity.notFound().build();
	}
	
	/*
	 * Deleta Pessoa por ID informado
	 * */
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "lista", allEntries = true)
	public ResponseEntity deletar(@PathVariable Long id) {
		var pessoa = pessoaRepository.findById(id);			
		if (pessoa.isPresent()) {
			var enderecos = enderecoRepository.findAllByPessoaId(id);
			enderecoRepository.deleteAll(enderecos);
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();
			
	}
		
}
