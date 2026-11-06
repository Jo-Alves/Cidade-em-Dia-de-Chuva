package br.fiap.joaoalves563004.repository;

import br.fiap.joaoalves563004.domain.Trecho;

import java.util.List;

public interface TrechoRepository {
    List<Trecho> findAllByOrderByBairroNomeAscLogradouroAsc();

    List<Trecho> findByBairroIdOrderByLogradouroAsc();

    boolean existsByBairroId(Long bairroId);
}
