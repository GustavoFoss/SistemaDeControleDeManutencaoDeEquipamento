package com.example.sistemadecontrolemanutencaodeequipamentos.Controller;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.OrdemDeServico;
import com.example.sistemadecontrolemanutencaodeequipamentos.Service.OrdemDeServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens")
public class OrdemDeServicoController {
    @Autowired
    private OrdemDeServicoService ordemDeServicoService;

    @PostMapping("/criarOrdem")
    public OrdemDeServico criarOrdemDeServico(@RequestBody OrdemDeServico ordemDeServico) {
        return ordemDeServicoService.criarOrdemDeServico(ordemDeServico);
    }

    @GetMapping("/pendentes")
    public List<OrdemDeServico> listarOrdensPendentes() {
        return ordemDeServicoService.listarOrdensPendentes();
    }

    @PutMapping("/iniciar/{id}")
    public OrdemDeServico iniciarServico(@PathVariable Long id) {
        return ordemDeServicoService.iniciarServico(id);
    }

    @PutMapping("/finalizar/{id}")
    public OrdemDeServico finalizarServico(@PathVariable Long id, @RequestBody String detalhesServico) {
        return ordemDeServicoService.finalizarServico(id, detalhesServico);
    }
}
