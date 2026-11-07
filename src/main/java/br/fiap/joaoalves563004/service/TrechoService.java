package br.fiap.joaoalves563004.service;

import br.fiap.joaoalves563004.domain.Bairro;
import br.fiap.joaoalves563004.domain.Trecho;
import br.fiap.joaoalves563004.mapper.TrechoMapper;
import br.fiap.joaoalves563004.repository.LeituraRepository;
import br.fiap.joaoalves563004.repository.TrechoRepository;
import br.fiap.joaoalves563004.dto.TrechoDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrechoService {
    private final TrechoRepository repo;
    private final TrechoMapper mapper;
    private final BairroService bairroService;
    private final LeituraRepository leituraRepository;

    public List<TrechoDTO> listar(){
        return repo.findAllByOrderByBairroNomeAscLogradouroAsc().stream()
                .map(trecho -> mapper.toDTO(trecho))
                .toList();
    }

    public TrechoDTO buscarPorId (Long id){
        Trecho trecho = repo.findById(id).orElseThrow();
        return mapper.toDTO(trecho);
    }

    @Transactional
    public TrechoDTO salvar (TrechoDTO dto){
        Bairro bairro = bairroService.buscarEntidade(dto.bairroId());
        Trecho trecho = mapper.toEntity(dto, bairro);
        return mapper.toDTO(repo.save(trecho));
    }

    @Transactional
    public void excluir (Long id){

        if (repo.existsById(id)){

            leituraRepository.deleteByTrechoId(id);
            repo.deleteById(id);

        } else {
            throw new IllegalArgumentException("Trecho não existe");
        }


    }

    public  Trecho buscarEntidade(Long id){
        return repo.findById(id).orElseThrow();
    }
}
