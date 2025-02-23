package com.example.sistemadecontrolemanutencaodeequipamentos.Service;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.OrdemDeServico;
import com.example.sistemadecontrolemanutencaodeequipamentos.OrdemDeServicoDto;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.ClienteRepository;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.EquipamentoRepository;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.OrdemDeServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdemDeServicoService {

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public OrdemDeServico criarOrdemDeServico(OrdemDeServico ordemDeServico) {
        clienteRepository.save(ordemDeServico.getCliente());
        equipamentoRepository.save(ordemDeServico.getEquipamento());
        ordemDeServico.setDataCriacao(LocalDateTime.now());
        ordemDeServico.setStatus("Pendente");
        return ordemDeServicoRepository.save(ordemDeServico);
    }

    public List<OrdemDeServicoDto> listarOrdensPendentes() {
        List<OrdemDeServico> ordens =  ordemDeServicoRepository.findAll();
        return ordens.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    public OrdemDeServicoDto buscarOrdemDeServicoPorId(Long id) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(id).orElse(null);
        return convertToDTO(ordem);

    }

    public OrdemDeServicoDto iniciarServico(Long ordemId) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(ordemId).orElse(null);
        ordem.setDataInicio(LocalDateTime.now());
        ordem.setAcompanhamento("");
        ordem.setStatus("Em Andamento");
        return convertToDTO(ordemDeServicoRepository.save(ordem));
    }

    public OrdemDeServicoDto finalizarServico(Long ordemId, String detalhesServico) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(ordemId).orElse(null);
        ordem.setDataFim(LocalDateTime.now());
        ordem.setDetalhesServico(detalhesServico);
        ordem.setAcompanhamento("");
        ordem.setStatus("Finalizado");
        return convertToDTO(ordemDeServicoRepository.save(ordem));
    }

    public OrdemDeServicoDto atualizarAcompanhamento(Long ordemId, String descricao) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(ordemId).orElse(null);
        ordem.setDataFim(LocalDateTime.now());
        ordem.setAcompanhamento(descricao);
        ordem.setStatus("Em Espera");
        return convertToDTO(ordemDeServicoRepository.save(ordem));
    }

    private OrdemDeServicoDto convertToDTO(OrdemDeServico ordem) {
        OrdemDeServicoDto dto = new OrdemDeServicoDto();
        dto.setId(ordem.getId());
        dto.setCliente(ordem.getCliente());
        dto.setEquipamento(ordem.getEquipamento());
        dto.setDataCriacao(ordem.getDataCriacao());
        dto.setDataFim(ordem.getDataFim());
        dto.setDataInicio(ordem.getDataInicio());
        dto.setStatus(ordem.getStatus());
        dto.setDetalhesServico(ordem.getDetalhesServico());
        dto.setAcompanhamento(ordem.getAcompanhamento());
        return dto;
    }
}
