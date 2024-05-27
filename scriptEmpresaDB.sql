create database empresa;

use empresa;

CREATE TABLE Pessoa (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    identificador VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL,
    tipo_identificador VARCHAR(50) NOT NULL,
    valor_min_mensal FLOAT NOT NULL,
    valor_max_emprestimo FLOAT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Emprestimo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_pessoa BIGINT NOT NULL,
    valor_emprestimo FLOAT NOT NULL,
    numero_parcelas INT NOT NULL,
    status_pagamento VARCHAR(50) NOT NULL,
    data_criacao DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id)
);

DROP TABLE PESSOA;
DROP TABLE EMPRESTIMO;

SELECT * FROM PESSOA;
SELECT * FROM EMPRESTIMO;