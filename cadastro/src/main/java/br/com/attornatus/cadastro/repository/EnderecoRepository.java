/*
 * Repository de Endereço
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.attornatus.cadastro.modelo.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
	

	List<Endereco> findAllByPessoaId (Long id);
	
	Page<Endereco> getAllByPessoaId (Long id,Pageable paginacao);
	
	
	@Query(value = "SELECT * FROM enderecos e WHERE e.status= :status and e.pessoa_id= :id", nativeQuery = true)
	Page<Endereco> buscaPessoaEnderecoPrincipal(Long id, Boolean status, Pageable pageable);
	
}
