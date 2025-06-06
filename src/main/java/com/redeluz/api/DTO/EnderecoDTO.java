package com.redeluz.api.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class EnderecoDTO {
    private String bairro;
    private String localidade;
    private String regiao;
}
