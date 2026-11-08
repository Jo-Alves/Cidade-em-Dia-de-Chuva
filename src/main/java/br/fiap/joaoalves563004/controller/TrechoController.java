package br.fiap.joaoalves563004.controller;

import br.fiap.joaoalves563004.dto.BairroDTO;
import br.fiap.joaoalves563004.dto.TrechoDTO;
import br.fiap.joaoalves563004.service.BairroService;
import br.fiap.joaoalves563004.service.TrechoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/trechos")
public class TrechoController {
    private final TrechoService trechoService;
    private final BairroService bairroService;

    @GetMapping("")
    public String listar(Model model){
        List<TrechoDTO> trecho =  trechoService.listar();
        model.addAttribute("trechos", trecho);
        return "trechos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        TrechoDTO trechoDTO = new TrechoDTO(null, null, null, null, null, null);
        List<BairroDTO> bairroDTO = bairroService.listar();
        model.addAttribute("trechoDTO", trechoDTO);
        model.addAttribute("bairros", bairroDTO);
        return "trechos/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model){
        TrechoDTO trechoDTO = trechoService.buscarPorId(id);
        List<BairroDTO> bairroDTO = bairroService.listar();
        model.addAttribute("trechoDTO", trechoDTO);
        model.addAttribute("bairros", bairroDTO);
        return "trechos/form";
    }

    @PostMapping("/salvar")
    public String salvar(TrechoDTO dto){
        TrechoDTO aux = trechoService.salvar(dto);
        return "redirect:/trechos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model){
        try {
            trechoService.excluir(id);
        } catch (IllegalArgumentException e){
            model.addAttribute("erro", e.getMessage());
        }
        return "redirect:/trechos";
    }
}
