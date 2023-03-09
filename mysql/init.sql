
create table Curso(
	codigo int not null AUTO_INCREMENT,
    nome varchar(100) not null,
    descricao varchar(4000) not null,
    duracao int not null,
    PRIMARY KEY(codigo)
);

create table Funcionario(
	codigo int not null AUTO_INCREMENT,
    nome varchar(200) not null,
    cpf char(11) not null,
    nascimento date not null,
    cargo varchar(200) not null,
    admissao date not null,
    status bit not null,
    PRIMARY KEY(codigo)
);

create table Turma(
	codigo int not null AUTO_INCREMENT,
    inicio date not null,
    fim date not null,
    local varchar(200) not null,
    curso_id int not null,
    PRIMARY KEY(codigo),
    FOREIGN KEY (curso_id) REFERENCES Curso(codigo) ON DELETE CASCADE
);

create table TurmaParticipante(
	codigo int not null AUTO_INCREMENT,
    funcionario_id int not null,
    turma_id int not null,
    PRIMARY KEY(codigo),
    FOREIGN KEY (turma_id) REFERENCES Turma(codigo) ON DELETE CASCADE,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(codigo)
);