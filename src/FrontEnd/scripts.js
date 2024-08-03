document.addEventListener('DOMContentLoaded', () => {
    const orderForm = document.getElementById('orderForm');
    const ordensPendentes = document.getElementById('ordensPendentes');
    const ordemDetalhesSection = document.getElementById('ordemDetalhesSection');
    const ordemDetalhes = document.getElementById('ordemDetalhes');
    const inicioServicoForm = document.getElementById('inicioServicoForm');
    const encerrarServicoForm = document.getElementById('encerrarServicoForm');
    let currentOrderId = null;

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
    }


    async function showOrderDetails(orderId) {
        try {
            const response = await fetch(`http://localhost:8080/ordens/${orderId}`);
            const ordem = await response.json();

            ordemDetalhes.innerHTML = `
        <p><strong>Cliente:</strong> ${ordem.cliente.nome}</p>
        <p><strong>Endereço:</strong> ${ordem.cliente.endereco}</p>
        <p><strong>Telefone:</strong> ${ordem.cliente.telefone}</p>
        <p><strong>Email:</strong> ${ordem.cliente.email}</p>
        <p><strong>Equipamento:</strong> ${ordem.equipamento.tipo} (${ordem.equipamento.marca})</p>
        <p><strong>Problema:</strong> ${ordem.detalhesServico}</p>
        <p><strong>Acompanhemento:</strong> ${ordem.acompanhamento}</p>
      `;

            currentOrderId = orderId;
            ordemDetalhesSection.style.display = 'block';
        } catch (error) {
            console.error('Erro ao buscar detalhes da ordem', error);
        }
    }

    inicioServicoForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        if (!currentOrderId) {
            alert('Nenhuma ordem selecionada.');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/ordens/${currentOrderId}/iniciarServico`, {
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
    });

    encerrarServicoForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        if (!currentOrderId) {
            alert('Nenhuma ordem selecionada.');
            return;
        }

        const descricao = document.getElementById('descricaoServico').value;

        try {
            const response = await fetch(`http://localhost:8080/ordens/${currentOrderId}/encerrarServico`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ descricao }),
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
    });

    fetchOrdensPendentes();
});
