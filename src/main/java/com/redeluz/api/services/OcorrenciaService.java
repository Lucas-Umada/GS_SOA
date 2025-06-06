package com.redeluz.api.services;

import com.redeluz.api.DTO.OcorrenciaDTO;
import com.redeluz.api.model.Ocorrencia;
import com.redeluz.api.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OcorrenciaService {
    private final OcorrenciaRepository repository;

    public Ocorrencia registrarOcorrencia(OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setLocalizacao(dto.getLocalizacao());
        ocorrencia.setTempoInterrupcao(dto.getTempoInterrupcao());
        ocorrencia.setImpactos(dto.getImpactos());
        
        ocorrencia.setDispositivoOrigem(dto.getDispositivoOrigem());
        ocorrencia.setDataColeta(dto.getDataColeta());
        
        return repository.save(ocorrencia);
    }

 
    public List<Ocorrencia> listarOcorrencias() {
        return repository.findAll();
    }

    public String sincronizarEmLote(List<OcorrenciaDTO> dtos) {
        dtos.forEach(this::registrarOcorrencia);
        return "Sincronizadas " + dtos.size() + " ocorrências";
    }

    public List<Ocorrencia> salvarLote(List<OcorrenciaDTO> dtos) {
    return dtos.stream().map(this::registrarOcorrencia).toList();
}
}