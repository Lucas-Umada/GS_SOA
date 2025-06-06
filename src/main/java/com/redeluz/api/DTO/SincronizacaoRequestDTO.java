package com.redeluz.api.DTO;

import lombok.Data;
import java.util.List;

@Data
public class SincronizacaoRequestDTO {
    private String cep;
    private List<OcorrenciaDTO> ocorrencias;
}