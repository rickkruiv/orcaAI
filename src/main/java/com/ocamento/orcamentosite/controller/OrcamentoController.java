package com.ocamento.orcamentosite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocamento.orcamentosite.model.Orcamento;
import com.ocamento.orcamentosite.service.OrcamentoService;

@RestController
@RequestMapping( "/orcamento" )
@CrossOrigin( origins = "*" )
public class OrcamentoController {

    @Autowired
    private OrcamentoService service;

    @PostMapping
    public Orcamento criar( @RequestBody Orcamento orcamento ) {
        return service.salvar( orcamento );
    }

    @GetMapping
    public List<Orcamento> listar() {
        return service.listarOrcamentos();
    }

    @PutMapping( "/{id}/status" )
    public Orcamento atualizarStatus( @PathVariable Long id, @RequestParam String novoStatus ) {
        return service.atualizarStatus( id, novoStatus );
    } 
    
}
