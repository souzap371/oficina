package com.oficina.oficina.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class OrdemDeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCriacao;
    private String descricao;
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente; // Este campo já representa o relacionamento

    @OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL)
    private List<Orcamento> orcamentos;

    // Construtor padrão
    public OrdemDeServico() {
    }

    // Construtor parametrizado
    public OrdemDeServico(LocalDate dataCriacao, String descricao, Double valorTotal, Cliente cliente) {
        this.dataCriacao = dataCriacao;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
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
}
