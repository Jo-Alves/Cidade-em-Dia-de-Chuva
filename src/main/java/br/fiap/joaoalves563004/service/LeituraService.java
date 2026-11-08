package br.fiap.joaoalves563004.service;

import br.fiap.joaoalves563004.domain.Leitura;
import br.fiap.joaoalves563004.domain.Trecho;
import br.fiap.joaoalves563004.mapper.LeituraMapper;
import br.fiap.joaoalves563004.repository.LeituraRepository;
import br.fiap.joaoalves563004.dto.LeituraDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LeituraService {
    private final LeituraRepository repo;
    private final LeituraMapper mapper;
    private final TrechoService trechoService;


    public List<LeituraDTO> listar() {
        return repo.findAllByOrderByDataHoraDesc().stream()
                .map(teitura -> mapper.toDTO(teitura))
                .toList();
    }

    public LeituraDTO buscarPorId(Long id) {
        Leitura leitura = repo.findById(id).orElseThrow();
        return mapper.toDTO(leitura);
    }

    @Transactional
    public LeituraDTO salvar(LeituraDTO dto) {
        validarDados(dto);
        Trecho trecho = trechoService.buscarEntidade(dto.trechoId());
        Leitura leitura = mapper.toEntity(dto, trecho, calcularStatus(dto.laminaCm()));
        return mapper.toDTO(repo.save(leitura));
    }

    public String calcularStatus(int laminaCm) {

        if (laminaCm < (10 / 2)) {
            return "NORMAL";
        } else if (laminaCm < 10) {
            return "ATENCAO";
        } else {
            return "ALERTA";
        }

    }

    @Transactional
    public void excluir(Long id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Leitura não existe");
        }

    }

    private void validarDados(LeituraDTO dto) {
        LocalDateTime agora = LocalDateTime.now();
        if (dto.dataHora() == null) {
            throw new IllegalArgumentException("Data e hora são obrigatório.");
        } else if ( dto.dataHora().isAfter(agora)) {
            throw new IllegalArgumentException("A data e hora não podem estar no futuro.");
        }

        if (dto.laminaCm() == null) {
            throw new IllegalArgumentException("Lamina é obrigatório.");
        } else if ( dto.laminaCm() < 0 || dto.laminaCm() > 300) {
            throw new IllegalArgumentException("A lâmina deve estar entre 0 e 300 centímetros.");
        }

    }

}
