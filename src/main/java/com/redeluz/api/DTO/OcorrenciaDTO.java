package com.redeluz.api.DTO;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaDTO {
    private String localizacao;
    private Integer tempoInterrupcao;
    private String impactos;
    private String dispositivoOrigem; 
    private LocalDateTime dataColeta; 
    private String cep;
}

