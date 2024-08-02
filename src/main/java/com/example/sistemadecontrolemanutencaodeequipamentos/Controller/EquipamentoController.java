package com.example.sistemadecontrolemanutencaodeequipamentos.Controller;


import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Cliente;
import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Equipamento;
import com.example.sistemadecontrolemanutencaodeequipamentos.Service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping("/criarEquipamento")
    public Equipamento criarCliente(@RequestBody Equipamento equipamento) {
        return equipamentoService.criarEquipamento(equipamento);
    }

}
