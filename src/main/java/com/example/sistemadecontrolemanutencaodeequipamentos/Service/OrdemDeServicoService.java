package com.example.sistemadecontrolemanutencaodeequipamentos.Service;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.OrdemDeServico;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.OrdemDeServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdemDeServicoService {

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    public OrdemDeServico criarOrdemDeServico(OrdemDeServico ordemDeServico) {
        ordemDeServico.setDataCriacao(LocalDateTime.now());
        ordemDeServico.setStatus("Pendente");
        return ordemDeServicoRepository.save(ordemDeServico);
    }

    public List<OrdemDeServico> listarOrdensPendentes() {
        return ordemDeServicoRepository.findByStatus("Pendente");
    }

    public OrdemDeServico iniciarServico(Long ordemId) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(ordemId).orElseThrow();
        ordem.setDataInicio(LocalDateTime.now());
        ordem.setStatus("Em Andamento");
        return ordemDeServicoRepository.save(ordem);
    }

    public OrdemDeServico finalizarServico(Long ordemId, String detalhesServico) {
        OrdemDeServico ordem = ordemDeServicoRepository.findById(ordemId).orElseThrow();
        ordem.setDataFim(LocalDateTime.now());
        ordem.setDetalhesServico(detalhesServico);
        ordem.setStatus("Finalizado");
        return ordemDeServicoRepository.save(ordem);
    }
}
