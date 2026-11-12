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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String salvar(TrechoDTO dto, Model model, RedirectAttributes atribu){
        String mensagem;
        if (dto.id()==null){
            mensagem = "Trecho cadastrado com sucesso.";
        } else {
            mensagem = "Trecho alterado com sucesso.";
        }

        try {
            TrechoDTO aux = trechoService.salvar(dto);
            atribu.addFlashAttribute("mensagem", mensagem);
            return "redirect:/trechos";
        } catch (IllegalArgumentException e){
            List<BairroDTO> bairroDTO = bairroService.listar();
            model.addAttribute("bairros", bairroDTO);
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("TrechoDTO", dto);
            return "trechos/form";
        }

    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model){
        try {
            trechoService.excluir(id);
            model.addAttribute("sucesso", "Trecho excluído com sucesso.");
        } catch (IllegalArgumentException e){
            model.addAttribute("erro", e.getMessage());
        }
        return "redirect:/trechos";
    }
}
