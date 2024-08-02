package com.example.sistemadecontrolemanutencaodeequipamentos.Service;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Cliente;
import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Equipamento;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.ClienteRepository;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public Equipamento criarEquipamento(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }
}
