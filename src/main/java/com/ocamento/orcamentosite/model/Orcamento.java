package com.ocamento.orcamentosite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Orcamento {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Tipo de serviço é obrigatório")
    private String tipoServico;

    @NotBlank(message = "Descrição é obrigatória")
    @Column( length = 1000 )
    private String descricao;

    private String status = "Recebido";
}
