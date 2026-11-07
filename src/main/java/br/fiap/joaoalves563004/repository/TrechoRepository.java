package br.fiap.joaoalves563004.repository;

import br.fiap.joaoalves563004.domain.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrechoRepository extends JpaRepository<Trecho, Long> {
    List<Trecho> findAllByOrderByBairroNomeAscLogradouroAsc();

    List<Trecho> findByBairroIdOrderByLogradouroAsc(Long bairroId);

    boolean existsByBairroId(Long bairroId);
}
