package com.crud.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.empresa.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
