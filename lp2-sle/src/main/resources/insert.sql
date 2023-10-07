-- Drop das tabelas (se existirem)
DROP TABLE IF EXISTS LanceCliente;
DROP TABLE IF EXISTS Leilao;
DROP TABLE IF EXISTS EntidadesFinanceiras;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Carro;
DROP TABLE IF EXISTS Moto;
DROP TABLE IF EXISTS Onibus;
DROP TABLE IF EXISTS Veiculo;
DROP TABLE IF EXISTS ItemLeilao;
DROP TABLE IF EXISTS Dispositivo;
DROP TABLE IF EXISTS Desktop;
DROP TABLE IF EXISTS Impressora;
DROP TABLE IF EXISTS Laptop;
DROP TABLE IF EXISTS Scanner;
DROP TABLE IF EXISTS Servidor;
DROP TABLE IF EXISTS Tablet;
DROP TABLE IF EXISTS Van;

-- Criação das tabelas

-- Tabela ItemLeilao
CREATE TABLE ItemLeilao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255),
    Descricao VARCHAR(255),
    Condicao VARCHAR(255),
    HistoricoReparo VARCHAR(255)
);

-- Tabela Dispositivo
CREATE TABLE Dispositivo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255),
    Descricao VARCHAR(255),
    Marca VARCHAR(255),
    Modelo VARCHAR(255),
    Dimensoes VARCHAR(255),
    Condicao VARCHAR(255),
    Especificacoes VARCHAR(255),
    HistoricoReparo VARCHAR(255),
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES ItemLeilao(id)
);

-- Tabela EntidadesFinanceiras
CREATE TABLE EntidadesFinanceiras (
    idEntidadeFinanceira BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cnpj VARCHAR(255) UNIQUE
);

-- Tabela Cliente
CREATE TABLE Cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255),
    CPFCNPJ VARCHAR(255) UNIQUE
);

-- Tabela Leilao
CREATE TABLE Leilao (
    IDLeilao BIGINT AUTO_INCREMENT PRIMARY KEY,
    DataInicio TIMESTAMP,
    DataFim TIMESTAMP,
    Status VARCHAR(20),
    IDEntidadeFinanceiraAssociada BIGINT,
    FOREIGN KEY (IDEntidadeFinanceiraAssociada) REFERENCES EntidadesFinanceiras(idEntidadeFinanceira)
);

-- Tabela LanceCliente
CREATE TABLE LanceCliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT,
    id_leilao BIGINT,
    valorLance DOUBLE,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id),
    FOREIGN KEY (id_leilao) REFERENCES Leilao(IDLeilao)
);

-- Tabela Veiculo
CREATE TABLE Veiculo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255),
    Descricao VARCHAR(255),
    Tipo VARCHAR(255),
    Marca VARCHAR(255),
    Ano VARCHAR(255),
    Acessorios VARCHAR(255),
    Condicao VARCHAR(255),
    HistoricoReparo VARCHAR(255),
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES ItemLeilao(id)
);

-- Tabela Carro
CREATE TABLE Carro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numeroPortas INT,
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Veiculo(id)
);

-- Tabela Desktop
CREATE TABLE Desktop (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Processador VARCHAR(255),
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Dispositivo(id)
);

-- Tabela Impressora
CREATE TABLE Impressora (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    TipoImpressao VARCHAR(255),
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Dispositivo(id)
);

-- Tabela Laptop
CREATE TABLE Laptop (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    TamanhoTela DOUBLE,
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Dispositivo(id)
);

-- Tabela Moto
CREATE TABLE Moto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cilindrada INT,
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Veiculo(id)
);

-- Tabela Onibus
CREATE TABLE Onibus (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    capacidadePassageiros INT,
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Veiculo(id)
);

-- Tabela Scanner
CREATE TABLE Scanner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ResolucaoDigitalizacao INT,
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Dispositivo(id)
);

-- Tabela Servidor
CREATE TABLE Servidor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Processador VARCHAR(255),
    CapacidadeArmazenamento BIGINT,
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Dispositivo(id)
);

-- Tabela Tablet
CREATE TABLE Tablet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    TamanhoTela DOUBLE,
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Dispositivo(id)
);

-- Tabela Van
CREATE TABLE Van (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    capacidadePassageiros INT,
    tipo_item VARCHAR(255),
    FOREIGN KEY (tipo_item) REFERENCES Veiculo(id)
);

-- Inserção de dados de teste

-- Inserir dados de teste para ItemLeilao
INSERT INTO ItemLeilao (Nome, Descricao, Condicao, HistoricoReparo) VALUES
('Item 1', 'Descrição 1', 'Condicao 1', 'Histórico 1'),
('Item 2', 'Descrição 2', 'Condicao 2', 'Histórico 2');

SELECT * FROM ItemLeilao;

-- Inserir dados de teste para Dispositivo
INSERT INTO Dispositivo (Nome, Descricao, Marca, Modelo, Dimensoes, Condicao, Especificacoes, HistoricoReparo, tipo_item) VALUES
('Dispositivo 1', 'Descrição 1', 'Marca 1', 'Modelo 1', 'Dimensoes 1', 'Condicao 1', 'Especificacoes 1', 'Histórico 1', 'desktop'),
('Dispositivo 2', 'Descrição 2', 'Marca 2', 'Modelo 2', 'Dimensoes 2', 'Condicao 2', 'Especificacoes 2', 'Histórico 2', 'impressora');

-- Inserir dados de teste para EntidadesFinanceiras
INSERT INTO EntidadesFinanceiras (nome, cnpj) VALUES
('Entidade 1', 'CNPJ 1'),
('Entidade 2', 'CNPJ 2');

-- Inserir dados de teste para Cliente
INSERT INTO Cliente (Nome, CPFCNPJ) VALUES
('Cliente 1', 'CPF/CNPJ 1'),
('Cliente 2', 'CPF/CNPJ 2');

-- Inserir dados de teste para Leilao
INSERT INTO Leilao (DataInicio, DataFim, Status, IDEntidadeFinanceiraAssociada) VALUES
('2023-01-01 00:00:00', '2023-01-10 00:00:00', 'Aberto', 1),
('2023-02-01 00:00:00', '2023-02-10 00:00:00', 'Fechado', 2);

-- Inserir dados de teste para LanceCliente
INSERT INTO LanceCliente (id_cliente, id_leilao, valorLance) VALUES
(1, 1, 100.00),
(2, 2, 200.00);

-- Inserir dados de teste para Veiculo
INSERT INTO Veiculo (Nome, Descricao, Tipo, Marca, Ano, Acessorios, Condicao, HistoricoReparo, tipo_item) VALUES
('Veiculo 1', 'Descrição 1', 'Carro', 'Marca 1', 'Ano 1', 'Acessorios 1', 'Condicao 1', 'Histórico 1', 'carro'),
('Veiculo 2', 'Descrição 2', 'Moto', 'Marca 2', 'Ano 2', 'Acessorios 2', 'Condicao 2', 'Histórico 2', 'moto');

-- Inserir dados de teste para Carro
INSERT INTO Carro (numeroPortas, tipo_item) VALUES
(4, 'carro'),
(2, 'carro');

-- Inserir dados de teste para Desktop
INSERT INTO Desktop (Processador, tipo_item) VALUES
('Intel i5', 'desktop'),
('AMD Ryzen', 'desktop');

-- Inserir dados de teste para Impressora
INSERT INTO Impressora (TipoImpressao, tipo_item) VALUES
('Laser', 'impressora'),
('Jato de Tinta', 'impressora');

-- Inserir dados de teste para Laptop
INSERT INTO Laptop (TamanhoTela, tipo_item) VALUES
(15.6, 'laptop'),
(14.0, 'laptop');

-- Inserir dados de teste para Moto
INSERT INTO Moto (cilindrada, tipo_item) VALUES
(250, 'moto'),
(500, 'moto');

-- Inserir dados de teste para Onibus
INSERT INTO Onibus (capacidadePassageiros, tipo_item) VALUES
(50, 'onibus'),
(30, 'onibus');

-- Inserir dados de teste para Scanner
INSERT INTO Scanner (ResolucaoDigitalizacao, tipo_item) VALUES
(1200, 'scanner'),
(2400, 'scanner');

-- Inserir dados de teste para Servidor
INSERT INTO Servidor (Processador, CapacidadeArmazenamento, tipo_item) VALUES
('Xeon', 1000, 'servidor'),
('Opteron', 2000, 'servidor');

-- Inserir dados de teste para Tablet
INSERT INTO Tablet (TamanhoTela, tipo_item) VALUES
(10.1, 'tablet'),
(7.0, 'tablet');

-- Inserir dados de teste para Van
INSERT INTO Van (capacidadePassageiros, tipo_item) VALUES
(15, 'van'),
(12, 'van');
