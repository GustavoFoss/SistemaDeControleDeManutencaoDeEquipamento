package com.example.sistemadecontrolemanutencaodeequipamentos.Repositories;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
