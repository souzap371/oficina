package com.oficina.oficina.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private String ano;
    private String placa;

    @ManyToOne
    private Cliente cliente;
}
