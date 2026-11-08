package br.fiap.joaoalves563004.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record LeituraDTO(
        Long id,
        @NotNull Long trechoId,
        String trechoDescricao,
        @NotNull @PastOrPresent LocalDateTime dataHora,
        @NotNull @Min(0) @Max(300) Integer laminaCm,
        String status
) {
}
