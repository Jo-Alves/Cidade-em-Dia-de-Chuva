package br.fiap.joaoalves563004.repository;

import br.fiap.joaoalves563004.domain.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findAllByOrderByNomeAsc();
}
