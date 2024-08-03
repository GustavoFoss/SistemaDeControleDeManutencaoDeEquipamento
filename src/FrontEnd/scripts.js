let currentOrderId = 0;

document.addEventListener('DOMContentLoaded', () => {
    const orderForm = document.getElementById('orderForm');
    const ordensPendentes = document.getElementById('ordensPendentes');
    const ordemDetalhesSection = document.getElementById('ordemDetalhesSection');
    const ordemDetalhes = document.getElementById('ordemDetalhes');
    const inicioServicoForm = document.getElementById('inicioServicoForm');
    const encerrarServicoForm = document.getElementById('encerrarServicoForm');
    const acompanhamentoServicoForm = document.getElementById('acompanhamentoServicoForm');

    orderForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const cliente = {
            nome: document.getElementById('clienteNome').value,
            endereco: document.getElementById('clienteEndereco').value,
            telefone: document.getElementById('clienteTelefone').value,
            email: document.getElementById('clienteEmail').value,
        };

        const equipamento = {
            tipo: document.getElementById('equipamentoTipo').value,
            marca: document.getElementById('equipamentoMarca').value
        }

        const detalhesServico = document.getElementById('equipamentoProblema').value

        const ordemDeServico = {
            cliente,
            equipamento,
            detalhesServico
        };

        try {
            const response = await fetch('http://localhost:8080/ordens/criarOrdem', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(ordemDeServico),
            });

            if (response.ok) {
                alert('Ordem de serviço criada com sucesso!');
                orderForm.reset();
                fetchOrdensPendentes();
            } else {
                alert('Erro ao criar ordem de serviço.');
            }
        } catch (error) {
            console.error('Erro ao criar ordem de serviço', error);
        }
    });

    fetchOrdensPendentes()
});

async function showOrderDetails(orderId) {
    try {
        const response = await fetch(`http://localhost:8080/ordens/${orderId}`);
        const ordem = await response.json();

        if (orderId === currentOrderId) {
            ordemDetalhesSection.style.display = 'none'
            currentOrderId = 0
            return
        }

        ordemDetalhes.innerHTML = `
        
        <p><strong>Status:</strong> ${ordem.status}</p>
        <p><strong>Cliente:</strong> ${ordem.cliente.nome}</p>
        <p><strong>Endereço:</strong> ${ordem.cliente.endereco}</p>
        <p><strong>Telefone:</strong> ${ordem.cliente.telefone}</p>
        <p><strong>Email:</strong> ${ordem.cliente.email}</p>
        <p><strong>Equipamento:</strong> ${ordem.equipamento.tipo} (${ordem.equipamento.marca})</p>
        <p><strong>Detalhes Servico:</strong> ${ordem.detalhesServico}</p>
        <p><strong>Acompanhamento:</strong> ${ordem.acompanhamento}</p>
        
      `;

        currentOrderId = orderId;
        ordemDetalhesSection.style.display = 'block';
    } catch (error) {
        console.error('Erro ao buscar detalhes da ordem', error);
    }


    fetchOrdensPendentes();
}

async function iniciarServico(event) {
    event.preventDefault();

    try {
        const response = await fetch(`http://localhost:8080/ordens/iniciar/${currentOrderId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            alert('Serviço iniciado com sucesso!');
        } else {
            alert('Erro ao iniciar o serviço.');
        }
    } catch (error) {
        console.error('Erro ao iniciar o serviço', error);
    }

    await fetchOrdensPendentes();
}

// Função para encerrar o serviço
async function encerrarServico() {

    const descricao = document.getElementById('descricaoServico').value;

    try {
        const response = await fetch(`http://localhost:8080/ordens/finalizar/${currentOrderId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: descricao,
        });

        if (response.ok) {
            alert('Serviço encerrado com sucesso!');
            showOrderDetails(currentOrderId);
            encerrarServicoForm.reset();
        } else {
            alert('Erro ao encerrar o serviço.');
        }
    } catch (error) {
        console.error('Erro ao encerrar o serviço', error);
    }


    await fetchOrdensPendentes();
}

// Função para atualizar o acompanhamento do serviço
async function atualizarAcompanhamento() {
    const descricao = document.getElementById('acompanhamento').value;

    try {
        const response = await fetch(`http://localhost:8080/ordens/acompanhamento/${currentOrderId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: descricao,
        });

        if (response.ok) {
            alert('Acompanhamento atualizado com sucesso!');
            showOrderDetails(currentOrderId);
            acompanhamentoServicoForm.reset();
        } else {
            alert('Erro ao atualizar acompanhamento.');
        }
    } catch (error) {
        console.error('Erro ao atualizar acompanhamento', error);
    }

    await fetchOrdensPendentes();
}

async function fetchOrdensPendentes() {
    try {
        const response = await fetch('http://localhost:8080/ordens/pendentes');
        const ordens = await response.json();

        ordensPendentes.innerHTML = '';

        ordens.forEach((ordem) => {
            const li = document.createElement('li');
            li.textContent = `${ordem.cliente.nome} - ${ordem.equipamento.tipo} (${ordem.detalhesServico})`;
            li.dataset.id = ordem.id; // Adiciona o ID como um atributo data
            li.addEventListener('click', () => showOrderDetails(ordem.id));
            ordensPendentes.appendChild(li);
        });
    } catch (error) {
        console.error('Erro ao buscar ordens pendentes', error);
    }

    inicioServicoForm.addEventListener('submit', iniciarServico);
}

encerrarServicoForm.addEventListener('submit', encerrarServico);
acompanhamentoServicoForm.addEventListener('submit', atualizarAcompanhamento);
