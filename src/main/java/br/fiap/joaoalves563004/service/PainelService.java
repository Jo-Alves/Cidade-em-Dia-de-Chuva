package br.fiap.joaoalves563004.service;

import br.fiap.joaoalves563004.domain.Leitura;
import br.fiap.joaoalves563004.domain.Trecho;
import br.fiap.joaoalves563004.repository.LeituraRepository;
import br.fiap.joaoalves563004.repository.TrechoRepository;
import br.fiap.joaoalves563004.dto.PainelItemDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PainelService {
    private final TrechoRepository trechoRepository;
    private final LeituraRepository leituraRepository;

    public List<PainelItemDTO> montarPainel(Long bairroId) {
        List<PainelItemDTO> listaPainelItemDTO = new ArrayList<>();
        List<Trecho> listaTrecho = trechoRepository.findByBairroIdOrderByLogradouroAsc(bairroId);


        for (Trecho t : listaTrecho) {
            Optional<Leitura> leitura = leituraRepository.findFirstByTrechoIdOrderByDataHoraDesc(t.getId());

            Integer ultimaLamina = null;
            String ultimoStatus = null;

            if (leitura.isPresent()) {
                ultimaLamina = leitura.get().getLaminaCm();
                ultimoStatus = leitura.get().getStatus();
            }

            PainelItemDTO painelItemDTO = new PainelItemDTO(
                    t.getLogradouro(),
                    t.getTipoCalcada(),
                    t.getAltitude(),
                    ultimaLamina,
                    ultimoStatus
            );

            listaPainelItemDTO.add(painelItemDTO);

        }

        return listaPainelItemDTO;
    }

    public List<Long> contaStatus(List<PainelItemDTO> listaPainelItemDTO){
        Long totalNorm = listaPainelItemDTO.stream()
                .filter(s -> "NORMAL".equalsIgnoreCase(s.ultimoStatus()))
                .count();

        Long totalAten = listaPainelItemDTO.stream()
                .filter(s -> "ATENCAO".equalsIgnoreCase(s.ultimoStatus()))
                .count();

        Long totalAler = listaPainelItemDTO.stream()
                .filter(s -> "ALERTA".equalsIgnoreCase(s.ultimoStatus()))
                .count();

        List<Long> totalStatus = new ArrayList<>();
        totalStatus.add(0, totalNorm);
        totalStatus.add(1, totalAten);
        totalStatus.add(2, totalAler);

        return totalStatus;
    }
}
