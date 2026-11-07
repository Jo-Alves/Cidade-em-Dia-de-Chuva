package br.fiap.joaoalves563004.dto;

public record PainelItemDTO(
        String logradouro,
        String tipoCalcada,
        Integer altitude,
        Integer ultimaLamina,
        String ultimoStatus
) {
}
