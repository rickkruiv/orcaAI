package com.ocamento.orcamentosite.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Orcamento {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private String nome;
    private String email;
    private String tipoServico;

    @Column( length = 1000 )
    private String descricao;

    private String status = "Recebido";
}
