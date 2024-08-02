package com.example.sistemadecontrolemanutencaodeequipamentos.Service;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Cliente;
import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.OrdemDeServico;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.ClienteRepository;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.OrdemDeServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
