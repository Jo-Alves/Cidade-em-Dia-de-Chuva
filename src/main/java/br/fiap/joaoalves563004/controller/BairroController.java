package br.fiap.joaoalves563004.controller;

import br.fiap.joaoalves563004.dto.BairroDTO;
import br.fiap.joaoalves563004.dto.PainelItemDTO;
import br.fiap.joaoalves563004.service.BairroService;
import br.fiap.joaoalves563004.service.PainelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/bairros")
public class BairroController {
    private final BairroService bairroService;
    private final PainelService painelService;


    @GetMapping("")
    public String listar(Model model){
        List<BairroDTO> bairroDTO = bairroService.listar();
        model.addAttribute("bairros", bairroDTO);
        return "bairros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        BairroDTO bairroDTO = new BairroDTO(null, null, null, null);
        model.addAttribute("bairroDTO", bairroDTO);
        return "bairros/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(Model model, @PathVariable Long id){
        BairroDTO bairroDTO = bairroService.buscarPorId(id);
        model.addAttribute("bairroDTO", bairroDTO);
        return "bairros/form";
    }

    @PostMapping("/salvar")
    public String salvar(BairroDTO dto,Model model, RedirectAttributes atribu){
        String mensagem;
        if (dto.id()==null){
            mensagem = "Bairro cadastrado com sucesso.";
        } else {
            mensagem = "Bairro alterado com sucesso.";
        }

        try {
            BairroDTO aux = bairroService.salvar(dto);
            atribu.addFlashAttribute("mensagem", mensagem);
            return "redirect:/bairros";
        } catch (IllegalArgumentException e){
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("bairroDTO", dto);
            return "bairros/form";
        }

    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model){
        try {
        bairroService.excluir(id);
        model.addAttribute("sucesso", "Bairro excluído com sucesso.");
        } catch (IllegalArgumentException e){
            model.addAttribute("erro", e.getMessage());
        }
        return "redirect:/bairros";
    }

    @GetMapping("/{id}/painel")
    public String painel(@PathVariable Long id, Model model){
        BairroDTO bairroDTO = bairroService.buscarPorId(id);
        List<PainelItemDTO> listaPainelItem = painelService.montarPainel(id);

        model.addAttribute("nomeBairro", bairroDTO.nome());
        model.addAttribute("listaPainel", listaPainelItem);

        List<Long> qtdStatus = painelService.contaStatus(listaPainelItem);

        model.addAttribute("qtdStatusNorm", qtdStatus.get(0));
        model.addAttribute("qtdStatusAten", qtdStatus.get(1));
        model.addAttribute("qtdStatusAler", qtdStatus.get(2));

        return "bairros/painel";
    }


}
