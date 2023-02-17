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
import br.com.attornatus.cadastro.modelo.Pessoa;
import br.com.attornatus.cadastro.records.DadosAtualizaPessoa;
import br.com.attornatus.cadastro.records.DadosCadastroPessoa;
import br.com.attornatus.cadastro.records.DadosListaPessoa;
import br.com.attornatus.cadastro.repository.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping("/{id}")
	@Cacheable(value = "lista")
	public ResponseEntity listaPorId(@PathVariable Long id){
		var idValido = 	pessoaRepository.findById(id);
		if(idValido.isPresent()) {
			var pessoa = pessoaRepository.getReferenceById(id);
			return ResponseEntity.ok(new DadosListaPessoa(pessoa));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	@Cacheable(value = "lista")
	public ResponseEntity<Page<DadosListaPessoa>> listaGeral(@PageableDefault(size = 20, sort = {"nome"}) Pageable pageable) {
		var page = pessoaRepository.findAll(pageable).map(DadosListaPessoa::new);
		return ResponseEntity.ok(page);
	}
	
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "lista", allEntries = true)
	public ResponseEntity cadastroPessoa (@RequestBody @Valid DadosCadastroPessoa dadosCadastraPessoa, UriComponentsBuilder uriBuilder) {
		var pessoa = new Pessoa(dadosCadastraPessoa);
		pessoaRepository.save(pessoa);
		
		var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListaPessoa(pessoa));
	}
	
	
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
	
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "lista", allEntries = true)
	public ResponseEntity deletar(@PathVariable Long id) {
		var pessoa = pessoaRepository.findById(id);				
		if (pessoa.isPresent()) {
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();
			
	}
	
	
	
}
