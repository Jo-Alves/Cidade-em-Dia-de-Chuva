package br.fiap.joaoalves563004.repository;

import br.fiap.joaoalves563004.domain.Bairro;

import java.util.List;

public interface BairroRepository {
    List<Bairro> findAllByOrderByNomeAsc();
}
