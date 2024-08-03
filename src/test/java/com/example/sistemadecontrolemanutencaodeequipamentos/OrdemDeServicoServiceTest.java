package com.example.sistemadecontrolemanutencaodeequipamentos;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Cliente;
import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Equipamento;
import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.OrdemDeServico;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.ClienteRepository;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.EquipamentoRepository;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.OrdemDeServicoRepository;
import com.example.sistemadecontrolemanutencaodeequipamentos.Service.ClienteService;
import com.example.sistemadecontrolemanutencaodeequipamentos.Service.OrdemDeServicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrdemDeServicoServiceTest {

    @Mock
    private OrdemDeServicoRepository ordemDeServicoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EquipamentoRepository equipamentoRepository;

    @InjectMocks
    private OrdemDeServicoService ordemDeServicoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarOrdemDeServico() {
        // Arrange
        OrdemDeServico ordemDeServico = new OrdemDeServico();
        Cliente cliente = new Cliente();
        cliente.setNome("teste");
        ordemDeServico.setCliente(cliente);
        ordemDeServico.setEquipamento(new Equipamento());

        when(ordemDeServicoRepository.save(any(OrdemDeServico.class))).thenReturn(ordemDeServico);
        when(equipamentoRepository.save(any(Equipamento.class))).thenReturn(new Equipamento());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Act
        OrdemDeServico result = ordemDeServicoService.criarOrdemDeServico(ordemDeServico);

        // Assert
        assertEquals(ordemDeServico.getCliente().getNome(),result.getCliente().getNome());
    }
}
