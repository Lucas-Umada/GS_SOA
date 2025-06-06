package com.redeluz.api.controllers;

import com.redeluz.api.DTO.OcorrenciaDTO;
import com.redeluz.api.model.Ocorrencia;
import com.redeluz.api.services.OcorrenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {
    private final OcorrenciaService service;

    @PostMapping
    public ResponseEntity<Ocorrencia> registrarOcorrencia(@RequestBody OcorrenciaDTO dto) {
        return ResponseEntity.ok(service.registrarOcorrencia(dto));
    }

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> listarOcorrencias() {
        return ResponseEntity.ok(service.listarOcorrencias());
    }
}