package com.oficina.oficina.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCriacao;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "ordem_de_servico_id", nullable = false)
    private OrdemDeServico ordemDeServico;

    // Construtor padrão
    public Orcamento() {
    }

    // Construtor parametrizado
    public Orcamento(LocalDate dataCriacao, Double valor, OrdemDeServico ordemDeServico) {
        this.dataCriacao = dataCriacao;
        this.valor = valor;
        this.ordemDeServico = ordemDeServico;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public OrdemDeServico getOrdemDeServico() {
        return ordemDeServico;
    }

    public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
        this.ordemDeServico = ordemDeServico;
    }

    public void setAprovado(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAprovado'");
    }
}




// package com.oficina.oficina.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;

// @Entity
// public class Orcamento {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private OrdemDeServico ordemDeServico;

//     private Double valorTotal;
//     private String descricao;
//     private boolean aprovado;



//     public Long getId() {
//         return id;
//     }
//     public void setId(Long id) {
//         this.id = id;
//     }
//     public OrdemDeServico getOrdemDeServico() {
//         return ordemDeServico;
//     }
//     public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
//         this.ordemDeServico = ordemDeServico;
//     }
//     public Double getValorTotal() {
//         return valorTotal;
//     }
//     public void setValorTotal(Double valorTotal) {
//         this.valorTotal = valorTotal;
//     }
//     public String getDescricao() {
//         return descricao;
//     }
//     public void setDescricao(String descricao) {
//         this.descricao = descricao;
//     }
//     public boolean isAprovado() {
//         return aprovado;
//     }
//     public void setAprovado(boolean aprovado) {
//         this.aprovado = aprovado;
//     }

//     // Getters, Setters e Construtores

    
// }