package com.ocamento.orcamentosite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocamento.orcamentosite.model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    List<Orcamento> findByStatus( String status );
}
