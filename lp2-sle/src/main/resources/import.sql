-- Inserção de dados na tabela EntidadesFinanceiras
INSERT INTO EntidadesFinanceiras (idEntidadeFinanceira, nome, cnpj) VALUES
(1, 'Nome Entidade Financeira 1', 'CNPJ Entidade Financeira 1'),
(2, 'Nome Entidade Financeira 2', 'CNPJ Entidade Financeira 2');

INSERT INTO Leilao (IDLeilao, DataInicio, DataFim, Status, IDEntidadeFinanceiraAssociada) VALUES
(1, '2023-02-02', '2023-02-10', 'Ativo', 1),
(2, '2023-01-01', '2023-01-01', 'Finalizado', 2),
(3, '2023-02-03', '2023-02-15', 'Finalizado', 2);