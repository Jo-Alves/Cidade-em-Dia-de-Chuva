package br.fiap.joaoalves563004.mapper;

import br.fiap.joaoalves563004.domain.Bairro;
import br.fiap.joaoalves563004.domain.Trecho;
import br.fiap.joaoalves563004.web.dto.TrechoDTO;
import org.springframework.stereotype.Component;

@Component
public class TrechoMapper {
    public Trecho toEntity (TrechoDTO dto, Bairro bairro){
        Trecho trecho = new Trecho();
        trecho.setId(dto.id());
        trecho.setBairro(bairro);
        trecho.setAltitude(dto.altitude());
        trecho.setLogradouro(dto.logradouro());
        trecho.setTipoCalcada(dto.tipoCalcada());
        return trecho;
    }

    public TrechoDTO toDTO (Trecho trecho){
        return new TrechoDTO(
                trecho.getId(),
                trecho.getLogradouro(),
                trecho.getTipoCalcada(),
                trecho.getAltitude(),
                trecho.getBairro().getId(),
                trecho.getBairro().getNome()
        );
    }
}
