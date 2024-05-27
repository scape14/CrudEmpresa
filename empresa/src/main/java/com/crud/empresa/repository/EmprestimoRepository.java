package com.crud.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.empresa.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

}
