package br.fiap.joaoalves563004.mapper;

import br.fiap.joaoalves563004.domain.Leitura;
import br.fiap.joaoalves563004.domain.Trecho;
import br.fiap.joaoalves563004.web.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {

    public Leitura toEntity (LeituraDTO dto, Trecho trecho, String status){
        Leitura leitura = new Leitura();
        leitura.setId(dto.id());
        leitura.setDataHora(dto.dataHora());
        leitura.setLaminaCm(dto.laminaCm());
        leitura.setStatus(status);
        leitura.setTrecho(trecho);
        return leitura;
    }

    public LeituraDTO toDTO(Leitura leitura){
        return new LeituraDTO(
                leitura.getId(),
                leitura.getTrecho().getId(),
                leitura.getTrecho().getLogradouro()+" ("+leitura.getTrecho().getBairro().getNome()+")",
                leitura.getDataHora(),
                leitura.getLaminaCm(),
                leitura.getStatus()
        );
    }
}
