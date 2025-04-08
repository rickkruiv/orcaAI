package com.ocamento.orcamentosite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocamento.orcamentosite.model.Orcamento;
import com.ocamento.orcamentosite.repository.OrcamentoRepository;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;

    public Orcamento salvar( Orcamento  orcamento ) {
        return repository.save( orcamento );
    }

    public List<Orcamento> listarOrcamentos() {
        return repository.findAll();
    }

    public Orcamento atualizarStatus( Long id, String novoStatus ) {
        Orcamento orcamento = repository.findById( id )
                                .orElseThrow( () -> new RuntimeException( "Orcamento não encontrado" ) );
        orcamento.setStatus( novoStatus );
        return repository.save( orcamento );
    }
}
