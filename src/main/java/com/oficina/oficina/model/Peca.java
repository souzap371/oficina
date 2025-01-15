package com.oficina.oficina.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome da peça não pode ser nulo.")
    private String nome;

    @PositiveOrZero(message = "A quantidade deve ser zero ou positiva.")
    private int quantidade;

    @Positive(message = "O preço deve ser positivo.")
    private Double preco;

    @NotNull(message = "A data de validade não pode ser nula.")
    private LocalDate dataValidade;

    // Construtor padrão
    public Peca() {
    }

    // Construtor parametrizado
    public Peca(String nome, int quantidade, Double preco, LocalDate dataValidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.dataValidade = dataValidade;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    // equals e hashCode (baseado no ID)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peca peca = (Peca) o;
        return id != null && id.equals(peca.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
