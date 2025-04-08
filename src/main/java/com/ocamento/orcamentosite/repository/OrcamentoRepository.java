package com.ocamento.orcamentosite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocamento.orcamentosite.model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

}
