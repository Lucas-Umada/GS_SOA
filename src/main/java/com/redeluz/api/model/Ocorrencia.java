package com.redeluz.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ocorrencias")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private Integer tempoInterrupcao; 

    @Column(nullable = false)
    private String impactos;

    @Column(nullable = false)
    private LocalDateTime dataRegistro = LocalDateTime.now();

    @Column
    private boolean sincronizada;
    
    @Column
    private String dispositivoOrigem; 
    
    @Column
    private LocalDateTime dataColeta;

    @Column
    private String cep; 
}