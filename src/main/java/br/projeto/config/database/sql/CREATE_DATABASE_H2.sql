-- Tabela Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Tabela Perfil
CREATE TABLE IF NOT EXISTS Perfil (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

-- Tabela Funcionalidade
CREATE TABLE IF NOT EXISTS Funcionalidade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    horasEstimadas INT NOT NULL,
    plataforma VARCHAR(255) NOT NULL CHECK (plataforma IN ('WEB/BACKEND', 'IOS', 'ANDROID'))
);

-- Tabela CustoAdicional
CREATE TABLE IF NOT EXISTS CustoAdicional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL
);

-- Tabela NivelUI
CREATE TABLE IF NOT EXISTS NivelUI (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    percentual DECIMAL(5, 2) NOT NULL
);

-- Tabela Estimativa 
CREATE TABLE IF NOT EXISTS Estimativa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    custoTotal DECIMAL(10, 2) NOT NULL,
    tempoTotal INT NOT NULL,
    precoFinal DECIMAL(10, 2) NOT NULL
);

-- Tabela Projeto
CREATE TABLE IF NOT EXISTS Projeto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    dataCriacao VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    compartilhado BOOLEAN NOT NULL,
    nivelUIId INT NOT NULL,
    estimativaId INT NOT NULL,
    percentualImpostos DECIMAL(5, 2) NOT NULL,
    percentualLucro DECIMAL(5, 2) NOT NULL,
    FOREIGN KEY (nivelUIId) REFERENCES NivelUI(id) ON DELETE RESTRICT,
    FOREIGN KEY (estimativaId) REFERENCES Estimativa(id) ON DELETE CASCADE
);

-- Tabela Estimativa (duplicada no seu SQL original, removi a duplicação)
CREATE TABLE IF NOT EXISTS Estimativa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    projetoId INT NOT NULL,
    custoTotal DECIMAL(10, 2) NOT NULL,
    tempoTotal INT NOT NULL,
    precoFinal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (projetoId) REFERENCES Projeto(id) ON DELETE CASCADE
);

-- Tabela ProjetoUsuarioCompartilhado (Relação muitos-para-muitos entre Projeto e Usuario)
CREATE TABLE IF NOT EXISTS ProjetoUsuarioCompartilhado (
    projetoId INT NOT NULL,
    usuarioId INT NOT NULL,
    isCriador BOOLEAN NOT NULL,
    PRIMARY KEY(projetoId, usuarioId),
    FOREIGN KEY (projetoId) REFERENCES Projeto(id),
    FOREIGN KEY (usuarioId) REFERENCES Usuario(id)
);

-- Tabela ProjetoPerfil (Relação muitos-para-muitos entre Projeto e Perfil)
CREATE TABLE IF NOT EXISTS ProjetoPerfil (
    projetoId INT NOT NULL,
    perfilId INT NOT NULL,
    PRIMARY KEY (projetoId, perfilId),
    FOREIGN KEY (projetoId) REFERENCES Projeto(id) ON DELETE CASCADE,
    FOREIGN KEY (perfilId) REFERENCES Perfil(id) ON DELETE CASCADE
);

-- Tabela ProjetoFuncionalidade (Relação muitos-para-muitos entre Projeto e Funcionalidade)
CREATE TABLE IF NOT EXISTS ProjetoFuncionalidade (
    projetoId INT NOT NULL,
    funcionalidadeId INT NOT NULL,
    PRIMARY KEY (projetoId, funcionalidadeId),
    FOREIGN KEY (projetoId) REFERENCES Projeto(id) ON DELETE CASCADE,
    FOREIGN KEY (funcionalidadeId) REFERENCES Funcionalidade(id) ON DELETE CASCADE
);

-- Tabela ProjetoCustoAdicional (Relação muitos-para-muitos entre Projeto e CustoAdicional)
CREATE TABLE IF NOT EXISTS ProjetoCustoAdicional (
    projetoId INT NOT NULL,
    custoAdicionalId INT NOT NULL,
    PRIMARY KEY (projetoId, custoAdicionalId),
    FOREIGN KEY (projetoId) REFERENCES Projeto(id) ON DELETE CASCADE,
    FOREIGN KEY (custoAdicionalId) REFERENCES CustoAdicional(id) ON DELETE CASCADE
);
