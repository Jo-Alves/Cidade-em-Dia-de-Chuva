package br.fiap.joaoalves563004.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BairroDTO (
        Long id,
        @NotBlank @Size(max = 80) String nome,
        @NotBlank @Size(max = 10) String zona,
        Boolean possuiPiscinao
) {
}
