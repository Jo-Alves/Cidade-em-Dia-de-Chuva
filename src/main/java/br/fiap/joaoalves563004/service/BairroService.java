package br.fiap.joaoalves563004.service;

import br.fiap.joaoalves563004.domain.Bairro;
import br.fiap.joaoalves563004.mapper.BairroMapper;
import br.fiap.joaoalves563004.repository.BairroRepository;
import br.fiap.joaoalves563004.repository.TrechoRepository;
import br.fiap.joaoalves563004.dto.BairroDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BairroService {
    private final BairroRepository repo;
    private final BairroMapper mapper;
    private final TrechoRepository trechoRepository;

    public List<BairroDTO> listar() {
        return repo.findAllByOrderByNomeAsc().stream()
                .map(bairro -> mapper.toDTO(bairro))
                .toList();
    }

    public BairroDTO buscarPorId(Long id) {
        Bairro bairro = repo.findById(id).orElseThrow();
        return mapper.toDTO(bairro);
    }

    @Transactional
    public BairroDTO salvar(BairroDTO dto) {
        validarDados(dto);
        Bairro bairro = mapper.toEntity(dto);
        return mapper.toDTO(repo.save(bairro));
    }

    @Transactional
    public void excluir(Long id) {
        if (repo.existsById(id)) {

            if (trechoRepository.existsByBairroId(id)) {
                throw new IllegalArgumentException("Bairro não pode ser excluído porque há um trecho associado a ele.");
            } else {
                repo.deleteById(id);
            }
        } else {
            throw new IllegalArgumentException("Bairro não existe");
        }
    }

    public Bairro buscarEntidade(Long id) {
        return repo.findById(id).orElseThrow();
    }

    private void validarDados(BairroDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new IllegalArgumentException("Nome do bairro é obrigatório");
        } else if (dto.nome().length() > 80) {
            throw new IllegalArgumentException("Nome do bairro deve ter no máximo 80 caracteres");
        }

        List<String> zonas =List.of("Centro", "Norte", "Sul", "Leste", "Oeste");
        if (dto.zona() == null || dto.zona().isBlank()) {
            throw new IllegalArgumentException("Zona do bairro é obrigatório");
        } else if (!zonas.contains(dto.zona())) {
            throw new IllegalArgumentException("Zona inválida");
        }


    }
}
