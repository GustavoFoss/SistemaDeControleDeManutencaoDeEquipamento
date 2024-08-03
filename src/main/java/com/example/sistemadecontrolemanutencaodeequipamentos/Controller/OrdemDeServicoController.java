package com.example.sistemadecontrolemanutencaodeequipamentos.Controller;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.OrdemDeServico;
import com.example.sistemadecontrolemanutencaodeequipamentos.OrdemDeServicoDto;
import com.example.sistemadecontrolemanutencaodeequipamentos.Service.OrdemDeServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/ordens")
public class OrdemDeServicoController {
    @Autowired
    private OrdemDeServicoService ordemDeServicoService;

    @PostMapping("/criarOrdem")
    public OrdemDeServico criarOrdemDeServico(@RequestBody OrdemDeServico ordemDeServico) {
        return ordemDeServicoService.criarOrdemDeServico(ordemDeServico);
    }

    @GetMapping("/pendentes")
    public List<OrdemDeServicoDto> listarOrdensPendentes() {
        return ordemDeServicoService.listarOrdensPendentes();
    }

    @GetMapping("/{id}")
    public OrdemDeServicoDto buscarOrdemPorId(@PathVariable Long id) {
        return ordemDeServicoService.buscarOrdemDeServicoPorId(id);
    }

    @PostMapping("/iniciar/{id}")
    public OrdemDeServicoDto iniciarServico(@PathVariable Long id) {
        return ordemDeServicoService.iniciarServico(id);
    }

    @PostMapping("/finalizar/{id}")
    public OrdemDeServicoDto finalizarServico(@PathVariable Long id, @RequestBody String detalhesServico) {
        return ordemDeServicoService.finalizarServico(id, detalhesServico);
    }

    @PostMapping("/acompanhamento/{id}")
    public OrdemDeServicoDto atualizarAcompanhamento(@PathVariable Long id, @RequestBody String descricao) {
        return ordemDeServicoService.atualizarAcompanhamento(id, descricao);
    }
}
