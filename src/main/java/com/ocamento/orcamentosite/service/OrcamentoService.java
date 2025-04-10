package com.ocamento.orcamentosite.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ocamento.orcamentosite.model.Orcamento;
import com.ocamento.orcamentosite.repository.OrcamentoRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

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

    public String exportarOrcamentos() {
        List<Orcamento> orcamentos = repository.findAll();  
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append( "ID, NOME, EMAIL, T. SERVICO, DESCRICAO, STATUS\n" );

        for( Orcamento orcamento : orcamentos ) {
            stringBuilder.append(orcamento.getId()).append( "," )
            .append( orcamento.getNome() ).append("," )
            .append( orcamento.getEmail() ).append("," )
            .append( orcamento.getTipoServico() ).append( "," )
            .append( orcamento.getDescricao() ).append( "," )
            .append( orcamento.getStatus() ).append( "\n" );
        }
        
        return stringBuilder.toString();
    }

    public void importarOrcamentos( MultipartFile file ) throws IOException, CsvValidationException, NumberFormatException {

        try( CSVReader reader = new CSVReader( new InputStreamReader( file.getInputStream() ) ) ) {
            String[] linha;
            boolean primeira = true;
    
            while( ( linha = reader.readNext() ) != null ) {
                if ( primeira ) {
                    primeira = false;
                    continue;
                }
    
                if ( linha.length < 6 ) continue;
    
                Long   id          = Long.parseLong( linha[0].trim() );
                String nome        = linha[1].trim();
                String email       = linha[2].trim();
                String tipoServico = linha[3].trim();
                String descricao   = linha[4].trim();
                String status      = linha[5].trim();
    
                Optional<Orcamento> existe = repository.findById( id );
                
                if ( existe.isPresent() ) {
                    Orcamento orcamento = existe.get();
                    orcamento.setStatus( status );
                    repository.save( orcamento );
                } else {
                    Orcamento orcamento = new Orcamento();
                    orcamento.setNome( nome );
                    orcamento.setEmail( email );
                    orcamento.setTipoServico( tipoServico );
                    orcamento.setDescricao( descricao );
                    orcamento.setStatus( status );
                    repository.save( orcamento );
                }
            }
        }
    }
}
