-- Tabela Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL
);

-- Tabela Perfil
CREATE TABLE IF NOT EXISTS Perfil (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL UNIQUE
);

-- Tabela Funcionalidade
CREATE TABLE IF NOT EXISTS Funcionalidade (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL UNIQUE,
    horasEstimadas INTEGER NOT NULL,
    plataforma TEXT NOT NULL CHECK (plataforma IN ('WEB/BACKEND', 'IOS', 'ANDROID'))
);

-- Tabela CustoAdicional
CREATE TABLE IF NOT EXISTS CustoAdicional (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descricao TEXT NOT NULL,
    valor REAL NOT NULL
);

-- Tabela NivelUI
CREATE TABLE IF NOT EXISTS NivelUI (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL UNIQUE,
    percentual REAL NOT NULL
);

-- Tabela Projeto
CREATE TABLE IF NOT EXISTS Projeto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    dataCriacao TEXT NOT NULL,
    status TEXT NOT NULL,
    compartilhado BOOLEAN NOT NULL,
    nivelUIId INTEGER NOT NULL,
    percentualImpostos REAL NOT NULL,
    percentualLucro REAL NOT NULL,
    FOREIGN KEY (nivelUIId) REFERENCES NivelUI(id) ON DELETE RESTRICT
);

-- TabelaProjetoUsuarioCompartilhado (Relação muitos-para-muitos entre Projeto e Usuario)
CREATE TABLE IF NOT EXISTS ProjetoUsuarioCompartilhado(
    projetoId INTEGER NOT NULL,
    usuarioId INTEGER NOT NULL,
    isCriador BOOLEAN NOT NULL,
    PRIMARY KEY(projetoId, usuarioId),
    FOREIGN KEY (projetoId) REFERENCES Projeto(id),
    FOREIGN KEY (usuarioId) REFERENCES Usuario(id)
);

-- Tabela ProjetoPerfil (Relação muitos-para-muitos entre Projeto e Perfil)
CREATE TABLE IF NOT EXISTS ProjetoPerfil (
    projetoId INTEGER NOT NULL,
    perfilId INTEGER NOT NULL,
    PRIMARY KEY (projetoId, perfilId),
    FOREIGN KEY (projetoId) REFERENCES Projeto(id) ON DELETE CASCADE,
    FOREIGN KEY (perfilId) REFERENCES Perfil(id) ON DELETE CASCADE
);

-- Tabela ProjetoFuncionalidade (Relação muitos-para-muitos entre Projeto e Funcionalidade)
CREATE TABLE IF NOT EXISTS ProjetoFuncionalidade (
    projetoId INTEGER NOT NULL,
    funcionalidadeId INTEGER NOT NULL,
    PRIMARY KEY (projetoId, funcionalidadeId),
    FOREIGN KEY (projetoId) REFERENCES Projeto(id) ON DELETE CASCADE,
    FOREIGN KEY (funcionalidadeId) REFERENCES Funcionalidade(id) ON DELETE CASCADE
);

-- Tabela ProjetoCustoAdicional (Relação muitos-para-muitos entre Projeto e CustoAdicional)
CREATE TABLE IF NOT EXISTS ProjetoCustoAdicional (
    projetoId INTEGER NOT NULL,
    custoAdicionalId INTEGER NOT NULL,
    PRIMARY KEY (projetoId, custoAdicionalId),
    FOREIGN KEY (projetoId) REFERENCES Projeto(id) ON DELETE CASCADE,
    FOREIGN KEY (custoAdicionalId) REFERENCES CustoAdicional(id) ON DELETE CASCADE
);