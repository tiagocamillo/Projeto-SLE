/*-- Inserção de Entidades Financeiras
INSERT INTO EntidadesFinanceiras (id, cnpj, nome, detalhesContato) VALUES
(1, 'CNPJ_Entidade1', 'Nome_Entidade1', 'Detalhes_Entidade1'),
(2, 'CNPJ_Entidade2', 'Nome_Entidade2', 'Detalhes_Entidade2');

-- Inserção de Clientes
INSERT INTO Cliente (id, nome, detalhesContato) VALUES
(1, 'Alice Smith', 'Detalhes_Contato_Alice'),
(2, 'Bob Johnson', 'Detalhes_Contato_Bob'),
(3, 'Eva Davis', 'Detalhes_Contato_Eva');

-- Inserção de Leilões associados às Entidades Financeiras
INSERT INTO Leilao (id, dataOcorrencia, dataVisita, local, status, instituicaoFinanceira_id) VALUES
(1, '2023-02-02', '2023-02-10', 'Local_Leilao_1', 'Ativo', 1),
(2, '2023-01-01', '2023-01-01', 'Local_Leilao_2', 'Finalizado', 2),
(3, '2023-02-03', '2023-02-15', 'Local_Leilao_3', 'Finalizado', 2);

-- Inserção de Lances de Clientes em Produtos associados a Leilões
INSERT INTO LanceCliente (id, valor, produto_id, cliente_id, dataHoraLance) VALUES
(1, 1000.0, 1, 1, '2023-02-02T10:10:10'),
(2, 1200.0, 2, 2, '2023-01-01T12:12:12'),
(3, 1500.0, 3, 3, '2023-02-03T15:15:15');

-- Inserção de Produtos (representando Carro, Moto, Tablet, etc.)
-- Insira os dados conforme sua estrutura específica para cada entidade de produto

INSERT INTO Carro (id, nome, descricao, status, tipo, quantidadeAssentos, tipoCombustivel) VALUES
(1, 'Carro X', 'Descrição Carro X', 'Disponível', 'CARRO', 5, 'Gasolina'),
(2, 'Carro Y', 'Descrição Carro Y', 'Disponível', 'CARRO', 4, 'Etanol');

INSERT INTO Moto (id, nome, descricao, status, tipo, possuiCarenagem, tipoMotocicleta) VALUES
(1, 'Moto A', 'Descrição Moto A', 'Disponível', 'MOTO', true, 'Esportiva'),
(2, 'Moto B', 'Descrição Moto B', 'Disponível', 'MOTO', false, 'Custom');

INSERT INTO Tablet (id, nome, descricao, status, tipo, tamanhoTela, sistemaOperacional) VALUES
(1, 'Tablet X', 'Descrição Tablet X', 'Disponível', 'TABLET', '10 polegadas', 'Android'),
(2, 'Tablet Y', 'Descrição Tablet Y', 'Disponível', 'TABLET', '12 polegadas', 'iOS');

-- Insira dados para outras entidades de produto (Laptop, Servidor, etc.) da mesma forma, respeitando suas colunas e dependências.*/
