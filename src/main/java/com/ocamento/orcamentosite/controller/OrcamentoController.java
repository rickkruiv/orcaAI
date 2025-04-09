package com.ocamento.orcamentosite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.ocamento.orcamentosite.model.Orcamento;
import com.ocamento.orcamentosite.service.OrcamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( "/orcamento" )
@CrossOrigin( origins = "*" )
public class OrcamentoController {

    @Autowired
    private OrcamentoService service;

    @PostMapping
    public ResponseEntity<?> criar( @Valid @RequestBody Orcamento orcamento ) {
        return ResponseEntity.ok( service.salvar( orcamento ) );
    }

    @GetMapping
    public List<Orcamento> listar( @RequestParam( required = false ) String status ) {
        return ( status != null && !status.isEmpty() ) ? service.listarPorStatus( status ) : service.listarOrcamentos();
    }

    @GetMapping("/paginado")
    public Page<Orcamento> listarPaginado(
            @RequestParam( defaultValue = "0" ) int page,
            @RequestParam( defaultValue = "10") int size,
            @RequestParam( required = false ) String status
    ) {

        if ( status != null && !status.isEmpty() ) {
            return new PageImpl<>( service.listarPorStatus( status ) );
        }
        return service.listarPaginado( page, size );
    }

    @PutMapping( "/{id}/status" )
    public Orcamento atualizarStatus( @PathVariable Long id, @RequestParam String novoStatus ) {
        return service.atualizarStatus( id, novoStatus );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrcamento( @PathVariable Long id ) {
        return service.deletarOrcamento( id ) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
