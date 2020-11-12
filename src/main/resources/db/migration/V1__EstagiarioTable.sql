CREATE TABLE estagiario(
    id UUID NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(70),
    telefone VARCHAR(15),
    foto TEXT,
    comprovanteMatricula TEXT,
    interesses VARCHAR(500),
    dominios VARCHAR(500)
)