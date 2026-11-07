package br.fiap.joaoalves563004.mapper;

import br.fiap.joaoalves563004.domain.Bairro;
import br.fiap.joaoalves563004.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {
    public Bairro toEntity(BairroDTO dto) {
        Bairro bairro = new Bairro();
        bairro.setId(dto.id());
        bairro.setNome(dto.nome());
        bairro.setZona(dto.zona());
        bairro.setPossuiPiscinao(dto.possuiPiscinao());
        return bairro;
    }

    public BairroDTO toDTO(Bairro bairro) {
        return new BairroDTO(
                bairro.getId(),
                bairro.getNome(),
                bairro.getZona(),
                bairro.getPossuiPiscinao()
        );
    }

}
