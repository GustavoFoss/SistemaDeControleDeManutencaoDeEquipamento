package com.example.sistemadecontrolemanutencaodeequipamentos.Repositories;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.OrdemDeServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Long> {
    List<OrdemDeServico> findByStatus(String status);
}

