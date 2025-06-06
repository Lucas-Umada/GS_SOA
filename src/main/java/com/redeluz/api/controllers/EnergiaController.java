package com.redeluz.api.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redeluz.api.DTO.EnderecoDTO;
import com.redeluz.api.DTO.OcorrenciaDTO;
import com.redeluz.api.DTO.SincronizacaoRequestDTO;
import com.redeluz.api.DTO.SincronizacaoResponseDTO;
import com.redeluz.api.DTO.StatusEnergiaDTO;
import com.redeluz.api.model.Ocorrencia;
import com.redeluz.api.services.OcorrenciaService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/energia")
@RequiredArgsConstructor

public class EnergiaController {

    private final OcorrenciaService ocorrenciaService;

    @GetMapping("/status/{cep}")
    public ResponseEntity<?> consultarStatusEnergia(@PathVariable String cep) {
        try {
            if (cep == null || cep.length() != 8 || !cep.matches("\\d+")) {
                return ResponseEntity.badRequest().body("CEP inválido. Deve conter 8 dígitos.");
            }
            
            String respostaApi = consultarApiEnergia(cep);
  
            return ResponseEntity.ok(processarResposta(respostaApi));
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao consultar status de energia: " + e.getMessage());
        }
    }

    @PostMapping("/sincronizar")
    public ResponseEntity<?> sincronizarOcorrencias(@RequestBody SincronizacaoRequestDTO request) {
        try {
            if (request.getCep() == null || request.getCep().length() != 8 || !request.getCep().matches("\\d+")) {
                return ResponseEntity.badRequest().body("CEP inválido. Deve conter 8 dígitos.");
            }

            List<Ocorrencia> salvas = ocorrenciaService.salvarLote(request.getOcorrencias());

            String respostaApi = consultarApiEnergia(request.getCep());
            Object status = processarResposta(respostaApi);
            if (status instanceof String) {
                return ResponseEntity.internalServerError().body(status);
            }

            List<OcorrenciaDTO> dtoList = salvas.stream().map(o -> new OcorrenciaDTO(
                o.getLocalizacao(),
                o.getTempoInterrupcao(),
                o.getImpactos(),
                o.getDispositivoOrigem(),
                o.getDataColeta(),
                o.getCep()
            )).collect(Collectors.toList());

            SincronizacaoResponseDTO response = new SincronizacaoResponseDTO(true, dtoList, (StatusEnergiaDTO) status);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao sincronizar: " + e.getMessage());
        }
    }

    public String consultarApiEnergia(String cep) {
    
        String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/";
    
        return new RestTemplate().getForEntity(apiUrl, String.class).getBody();
    }

    private Object processarResposta(String respostaApi) {
       try {
        ObjectMapper mapper = new ObjectMapper();
        EnderecoDTO endereco = mapper.readValue(respostaApi, EnderecoDTO.class);

        String statusMock = obterStatusPorRegiao(endereco.getRegiao());

        return new StatusEnergiaDTO(
            endereco.getBairro(),
            endereco.getLocalidade(),
            statusMock
        );
    } catch (Exception e) {
        return "Erro ao processar resposta da API: " + e.getMessage();
     }
    }

    private String obterStatusPorRegiao(String regiao) {
    if (regiao == null) return "Região não informada";
    
    switch (regiao.toLowerCase()) {
        case "sudeste":
            return "Funcionando normalmente";
        case "nordeste":
            return "Intermitência detectada";
        case "sul":
            return "Energia instável";
        case "norte":
            return "Manutenção programada";
        case "centro-oeste":
            return "Sem fornecimento";
        default:
            return "Status desconhecido";
        }
    }

    

}
