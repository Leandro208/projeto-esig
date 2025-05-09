CREATE TABLE pessoa_salario_consolidado (
    id_pessoa BIGINT PRIMARY KEY,
    id_cargo BIGINT NOT NULL,
    salario NUMERIC(15, 2) NOT NULL,
    CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
    CONSTRAINT fk_cargo FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo)
);
