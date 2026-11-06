package br.fiap.joaoalves563004.web.dto;

import java.time.LocalDateTime;

public record LeituraDTO(
        Long id,
        Long trechoId,
        String trechoDescricao,
        LocalDateTime dataHora,
        Integer laminaCm,
        String status
) {
}
