/*
 * Repository de Pessoa
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Data Ultima Revisão ; 19/02/2023
 * 
 * */

package br.com.attornatus.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.cadastro.modelo.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long>{



}
