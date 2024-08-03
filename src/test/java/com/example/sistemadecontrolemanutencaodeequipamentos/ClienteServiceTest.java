package com.example.sistemadecontrolemanutencaodeequipamentos;

import com.example.sistemadecontrolemanutencaodeequipamentos.Entities.Cliente;
import com.example.sistemadecontrolemanutencaodeequipamentos.Repositories.ClienteRepository;
import com.example.sistemadecontrolemanutencaodeequipamentos.Service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarCliente() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNome("Teste Cliente");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Act
        Cliente result = clienteService.criarCliente(cliente);

        // Assert
        assertEquals(cliente.getNome(), result.getNome());
    }
}
