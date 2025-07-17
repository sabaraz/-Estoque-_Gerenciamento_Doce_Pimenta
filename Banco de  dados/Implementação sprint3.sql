CREATE DATABASE doce_pimenta 
DEFAULT CHARACTER SET utf8mb4;

USE doce_pimenta;

-- Tabela de fornecedores
CREATE TABLE Fornecedor (
    id_fornecedor INT AUTO_INCREMENT PRIMARY KEY,
    nome_empresa VARCHAR(100), -- é not null
    nome_fornecedor VARCHAR(100), -- é not null
    telefone VARCHAR(20),
    cnpj VARCHAR(20) UNIQUE,
    entregador VARCHAR(100)); -- remover o entregador

-- Tabela de produtos
CREATE TABLE Produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50),
    preco_venda_padrao DECIMAL(10,2),
    tipo_produto VARCHAR(30) NOT NULL,
    id_fornecedor INT, -- é not null
    sexo VARCHAR(20),
    modelo VARCHAR(100),
    tamanho VARCHAR(10),
    linha VARCHAR(50),
    FOREIGN KEY (id_fornecedor) REFERENCES Fornecedor(id_fornecedor));

-- Tabela de entrada de estoque
CREATE TABLE EntradaEstoque (
    id_entrada INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
	qtde_aquisicao INT NOT NULL,
    data_aquisicao DATE NOT NULL,
    custo_unitario_aquisicao DECIMAL(10,2),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto));
    -- Add  em Entrada_Estoque tipo varchar(30)
    
-- Tabela de vendas
CREATE TABLE Venda (
    id_venda INT AUTO_INCREMENT PRIMARY KEY,
    data_venda DATETIME DEFAULT CURRENT_TIMESTAMP,
    valor_total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    forma_pagamento ENUM('Dinheiro', 'Cartão Crédito', 'Cartão Débito', 'Pix') NOT NULL DEFAULT 'Dinheiro',
    desconto DECIMAL(10,2) DEFAULT 0.00,
    acrescimo DECIMAL(10,2) DEFAULT 0.00,
    impostos DECIMAL(10,2) DEFAULT 0.00);
    -- Add em Venda tipo varchar(30)

-- Itens da venda (produtos vendidos)
CREATE TABLE ItemVenda (
    id_item_venda INT AUTO_INCREMENT PRIMARY KEY,
    id_venda INT NOT NULL,
    id_produto INT NOT NULL,
    qtde_vendida INT NOT NULL,
    preco_unitario_venda DECIMAL(10,2) NOT NULL,
    subtotal_item DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_venda) REFERENCES Venda(id_venda),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto));

-- Histórico de movimentações de estoque
CREATE TABLE HistoricoEstoque (
    id_historico INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    tipo_movimentacao ENUM('Entrada', 'Saída') NOT NULL,
    quantidade INT NOT NULL,
    data_movimentacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_origem INT, -- pode ser id_entrada ou id_venda
    origem ENUM('EntradaEstoque', 'Venda') NOT NULL,
    observacao VARCHAR(255), 
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto));
    -- Add  em Historico_Estoque tipo varchar(30)
    -- add em origem enum(baixa manual)
    
-- Tabela de entregadores
CREATE TABLE Entregador (
    id_entregador INT AUTO_INCREMENT PRIMARY KEY,
    id_fornecedor INT NOT NULL,
    nome VARCHAR (100),
    telefone VARCHAR(20),
    FOREIGN KEY (id_fornecedor) REFERENCES Fornecedor(id_fornecedor));
    
-- TABELA DE USUÁRIOS (Login e Perfil)
CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil ENUM('Administrador', 'Funcionario') NOT NULL,
    ativo BOOLEAN DEFAULT TRUE);
    
-- TABELA DE FUNCIONÁRIOS (Apenas se perfil = Funcionario)
CREATE TABLE Funcionario (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50),
    telefone VARCHAR(20),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario));

ALTER TABLE HistoricoEstoque
MODIFY COLUMN origem ENUM('EntradaEstoque', 'Venda', 'BaixaManual') NOT NULL;

ALTER TABLE Fornecedor
	MODIFY COLUMN nome_empresa VARCHAR(100) NOT NULL;

ALTER TABLE Fornecedor
	MODIFY COLUMN nome_fornecedor VARCHAR(100) NOT NULL;

-- Constraint para garantir unicidade por fornecedor
ALTER TABLE Produto
	ADD CONSTRAINT uq_produto_unico
	UNIQUE (modelo, tamanho, marca, id_fornecedor);

-- VIEW  saldo de estoque (subqueries isoladas)
CREATE OR REPLACE VIEW view_saldo_estoque AS
SELECT 
    p.id_produto,
    p.modelo,
    p.tamanho,
    p.marca,
    IFNULL((SELECT SUM(e.qtde_aquisicao) FROM EntradaEstoque e WHERE e.id_produto = p.id_produto), 0) AS total_entradas,
    IFNULL((SELECT SUM(iv.qtde_vendida) FROM ItemVenda iv WHERE iv.id_produto = p.id_produto), 0) AS total_saidas,
    IFNULL((SELECT SUM(e.qtde_aquisicao) FROM EntradaEstoque e WHERE e.id_produto = p.id_produto), 0)
    - IFNULL((SELECT SUM(iv.qtde_vendida) FROM ItemVenda iv WHERE iv.id_produto = p.id_produto), 0) AS saldo_estoque
FROM Produto p;

-- Sugestão de uso no backend:
-- SELECT * FROM view_saldo_estoque;

ALTER TABLE Produto 
	DROP FOREIGN KEY Produto_ibfk_1;

ALTER TABLE Produto 
	MODIFY COLUMN id_fornecedor INT NOT NULL;

ALTER TABLE Produto 
	ADD CONSTRAINT fk_produto_fornecedor 
	FOREIGN KEY (id_fornecedor) REFERENCES Fornecedor(id_fornecedor);

ALTER TABLE EntradaEstoque
	ADD COLUMN tipo VARCHAR(30);

ALTER TABLE Venda
	ADD COLUMN tipo VARCHAR(30);

ALTER TABLE HistoricoEstoque
	ADD COLUMN tipo VARCHAR(30);

ALTER TABLE Fornecedor 
	DROP COLUMN entregador;
    
CREATE OR REPLACE VIEW view_baixas_estoque AS
SELECT
    h.id_historico,
    p.modelo,
    p.marca,
    h.quantidade,
    h.data_movimentacao,
    h.tipo,
    h.observacao
FROM HistoricoEstoque h
JOIN Produto p ON p.id_produto = h.id_produto
WHERE h.origem = 'BaixaManual';

    
