package br.fiap.joaoalves563004.web.dto;

public record TrechoDTO(
        Long id,
        String logradouro,
        String tipoCalcada,
        Integer altitude,
        Long bairroId,
        String bairroNome
) {
}
