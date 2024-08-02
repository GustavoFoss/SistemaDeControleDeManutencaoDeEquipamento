package com.example.sistemadecontrolemanutencaodeequipamentos.Repositories;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> { }