package br.com.attornatus.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.cadastro.modelo.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

}
