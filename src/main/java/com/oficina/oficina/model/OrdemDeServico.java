// package com.oficina.oficina.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.util.List;

// @Entity
// public class OrdemDeServico {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private LocalDate dataCriacao;
//     private String descricao;
//     private Double valorTotal;

//     @ManyToOne
//     @JoinColumn(name = "cliente_id")
//     private Cliente cliente; // Este campo já representa o relacionamento

//     @OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL)
//     private List<Orcamento> orcamentos;

//     // Construtor padrão
//     public OrdemDeServico() {
//     }

//     // Construtor parametrizado
//     public OrdemDeServico(LocalDate dataCriacao, String descricao, Double valorTotal, Cliente cliente) {
//         this.dataCriacao = dataCriacao;
//         this.descricao = descricao;
//         this.valorTotal = valorTotal;
//         this.cliente = cliente;
//     }

//     // Getters e Setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public LocalDate getDataCriacao() {
//         return dataCriacao;
//     }

//     public void setDataCriacao(LocalDate dataCriacao) {
//         this.dataCriacao = dataCriacao;
//     }

//     public String getDescricao() {
//         return descricao;
//     }

//     public void setDescricao(String descricao) {
//         this.descricao = descricao;
//     }

//     public Double getValorTotal() {
//         return valorTotal;
//     }

//     public void setValorTotal(Double valorTotal) {
//         this.valorTotal = valorTotal;
//     }

//     public Cliente getCliente() {
//         return cliente;
//     }

//     public void setCliente(Cliente cliente) {
//         this.cliente = cliente;
//     }

//     public List<Orcamento> getOrcamentos() {
//         return orcamentos;
//     }

//     public void setOrcamentos(List<Orcamento> orcamentos) {
//         this.orcamentos = orcamentos;
//     }
// }



package com.oficina.oficina.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;



@Entity
public class OrdemDeServico {

    public enum StatusOrdem {
        PENDENTE, EM_ANDAMENTO, CONCLUIDO
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrdem status;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDate dataCriacao;

    private String descricao;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orcamento> orcamentos;

    // Construtor padrão
    public OrdemDeServico() {
    }

    // Construtor parametrizado
    public OrdemDeServico(String descricao, BigDecimal valorTotal, Cliente cliente) {
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public StatusOrdem getStatus() {
        return status;
    }

    public void setStatus(StatusOrdem status) {
        this.status = status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    // toString, equals e hashCode para facilitar depuração e comparação
    @Override
    public String toString() {
        return "OrdemDeServico{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", descricao='" + descricao + '\'' +
                ", valorTotal=" + valorTotal +
                ", cliente=" + (cliente != null ? cliente.getId() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemDeServico that = (OrdemDeServico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
