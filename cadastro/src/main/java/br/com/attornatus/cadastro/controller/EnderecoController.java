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
import br.com.attornatus.cadastro.records.DadosAtualizaEndereco;
import br.com.attornatus.cadastro.records.DadosCadastroEndereco;
import br.com.attornatus.cadastro.records.DadosListaEndereco;
import br.com.attornatus.cadastro.records.DadosListaPessoa;
import br.com.attornatus.cadastro.repository.EnderecoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping("/{id}")
	@Cacheable(value = "listas")
	public ResponseEntity listaPorId(@PathVariable Long id){
		var idValido = 	enderecoRepository.findById(id);
			if(idValido.isPresent()) {
			var endereco = enderecoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosListaEndereco(endereco));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	@Cacheable(value = "listas")
	public ResponseEntity<Page<DadosListaEndereco>> listaGeral(@PageableDefault(size = 20, sort = {"cep"}) Pageable pageable) {
		var page = enderecoRepository.findAll(pageable).map(DadosListaEndereco ::new);
		return ResponseEntity.ok(page);
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listas", allEntries = true)
	public ResponseEntity cadastroEndereco (@RequestBody @Valid DadosCadastroEndereco  dadosCadastraEndereco, UriComponentsBuilder uriBuilder) {
		var endereco = new Endereco(dadosCadastraEndereco);
		enderecoRepository.save(endereco);
		
		var uri = uriBuilder.path("/enderecos/{id}").buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListaEndereco (endereco));
	}
	
	
	@PutMapping
	@Transactional
	@CacheEvict(value = "listas", allEntries = true)
	public ResponseEntity atualizar(@RequestBody @Valid  DadosAtualizaEndereco dadosAtualizaEndereco ) {
		var idValido = 	enderecoRepository.findById(dadosAtualizaEndereco.id());
		if(idValido.isPresent()) {	
			var enderecoAtualiza = enderecoRepository.getReferenceById(dadosAtualizaEndereco .id());
			enderecoAtualiza.atualizarInformacoes(dadosAtualizaEndereco );
			return ResponseEntity.ok(new DadosCadastroEndereco(enderecoAtualiza));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listas", allEntries = true)
	public ResponseEntity deletar(@PathVariable Long id) {
		var endereco = enderecoRepository.findById(id);				
		if (endereco.isPresent()) {
			enderecoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();
			
	}
	
}
