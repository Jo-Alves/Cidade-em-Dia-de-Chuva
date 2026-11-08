package br.fiap.joaoalves563004.controller;

import br.fiap.joaoalves563004.dto.LeituraDTO;
import br.fiap.joaoalves563004.dto.TrechoDTO;
import br.fiap.joaoalves563004.service.LeituraService;
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
@RequestMapping("/leituras")
@AllArgsConstructor
public class LeituraController {

    private final LeituraService leituraService;
    private final TrechoService trechoService;

    @GetMapping("")
    public String lista(Model model){
        List<LeituraDTO> leituraDTO = leituraService.listar();
        model.addAttribute("leituras", leituraDTO);
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        LeituraDTO leituraDTO = new LeituraDTO(null, null, null, null, null, null);
        List<TrechoDTO> trechoDTO = trechoService.listar();
        model.addAttribute("leituraDTO", leituraDTO);
        model.addAttribute("trechos", trechoDTO);
        return "leituras/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model){
        LeituraDTO leituraDTO = leituraService.buscarPorId(id);
        List<TrechoDTO> trechoDTO = trechoService.listar();
        model.addAttribute("leituraDTO", leituraDTO);
        model.addAttribute("trechos", trechoDTO);

        return "leituras/form";
    }

    @PostMapping("/salvar")
    public String salvar(LeituraDTO dto){
        LeituraDTO aux = leituraService.salvar(dto);
        return "redirect:/leituras";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model){

        try {
            leituraService.excluir(id);
        } catch (IllegalArgumentException e){
            model.addAttribute("erro", e.getMessage());
        }

        return "redirect:/leituras";
    }
}
