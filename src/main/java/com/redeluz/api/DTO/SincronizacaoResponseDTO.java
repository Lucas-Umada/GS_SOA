package com.redeluz.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SincronizacaoResponseDTO {
    private boolean sincronizado;
    private List<OcorrenciaDTO> ocorrencias;
    private StatusEnergiaDTO status;
}
