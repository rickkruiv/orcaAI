package com.ocamento.orcamentosite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
        Orcamento orcamento = buscarOrcamento( id );
        orcamento.setStatus( novoStatus );
        return repository.save( orcamento );
    }

    public boolean deletarOrcamento( Long id ) {
        
        if ( repository.existsById( id ) ) {
            repository.deleteById( id );
            return true;
        }

        return false;
    }

    public List<Orcamento> listarPorStatus( String status ) {
        return repository.findByStatus( status );
    }

    public Orcamento buscarOrcamento( Long id ) {
        return repository.findById( id )
        .orElseThrow( () -> new RuntimeException( "Orcamento não encontrado" ) );
    }

    public Page <Orcamento> listarPaginado( int page, int size ) {
        return repository.findAll( PageRequest.of( page, size ) );
    }
}
