package br.fiap.joaoalves563004.web.dto;

public record BairroDTO (
        Long id,
        String nome,
        String zona,
        Boolean possuiPiscinao
) {
}
