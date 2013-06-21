
SET CHARACTER SET utf8;

#CREATE DATABASE situris;
DROP DATABASE IF EXISTS bikes4u;
CREATE DATABASE bikes4u CHARACTER SET = utf8;
USE bikes4u;

--
-- Base de Dados: 'bikes4u'

--
-- Estrutura da tabela 'Clientes'
--

CREATE TABLE Clientes (
  idCliente INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nomeCliente varchar(50) UNIQUE NOT NULL,
  password varchar(150) NOT NULL,
  nome varchar(50) NOT NULL,
  email varchar(50) UNIQUE NOT NULL,
  cartaoCredito varchar(16) UNIQUE NOT NULL,
  PRIMARY KEY (idCliente)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Categorias'
--

CREATE TABLE Categorias (
  idCategoria INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome varchar(50) UNIQUE NOT NULL,
  precoAluguer double NOT NULL,
  PRIMARY KEY (idCategoria)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Estados'
--

CREATE TABLE Estados (
  idEstado INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome varchar(50) UNIQUE NOT NULL,
  PRIMARY KEY (idEstado)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'Funcionarios'
--

CREATE TABLE Funcionarios (
  idFuncionario INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nomeFuncionario varchar(50) UNIQUE NOT NULL,
  password varchar(150) NOT NULL,
  nome varchar(50) NOT NULL,
  email varchar(50) UNIQUE NOT NULL,
  PRIMARY KEY (idFuncionario)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'StockBicicletas'
--

CREATE TABLE StockBicicletas (
  idBicicleta INT UNSIGNED NOT NULL AUTO_INCREMENT,
  designacao varchar(50) NOT NULL,
  nova binary(1) NOT NULL,
  precoVenda double NOT NULL,
  idCategoria INT UNSIGNED DEFAULT NULL,
  idEstado INT UNSIGNED DEFAULT NULL,
  imagem varchar(50) DEFAULT NULL,
  FOREIGN KEY (idCategoria) REFERENCES Categorias(idCategoria),
  FOREIGN KEY (idEstado) REFERENCES Estados(idEstado),
  PRIMARY KEY (idBicicleta)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'ReservaAluguers'
--

CREATE TABLE ReservaAluguers (
  idReservaAluguer INT UNSIGNED NOT NULL AUTO_INCREMENT,
  dataInicio datetime NOT NULL,
  dataFim datetime NOT NULL,
  preco double NOT NULL,
  idCliente INT unsigned DEFAULT NULL,
  FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente),
  PRIMARY KEY (idReservaAluguer)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'RegistoManutencaos'
--

CREATE TABLE RegistoManutencaos (
  idManutencao INT UNSIGNED NOT NULL AUTO_INCREMENT,
  descricaoAvaria varchar(50) NOT NULL,
  dataInicio timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  dataFim date NOT NULL,
  precoPosManutencao double NOT NULL,
  idBicicleta INT UNSIGNED DEFAULT NULL,
  idFuncionario INT UNSIGNED DEFAULT NULL,
  terminado BINARY( 1 ) NOT NULL DEFAULT  '0',
  FOREIGN KEY (idBicicleta) REFERENCES StockBicicletas(idBicicleta),
  FOREIGN KEY (idFuncionario) REFERENCES Funcionarios(idFuncionario),
  PRIMARY KEY (idManutencao)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'ReservaCategorias'
--

CREATE TABLE ReservaCategorias (
  idReservaAluguer INT UNSIGNED DEFAULT NULL,
  idCategoria INT UNSIGNED DEFAULT NULL,
  quantidade int(10) NOT NULL,
  FOREIGN KEY (idReservaAluguer) REFERENCES ReservaAluguers(idReservaAluguer),
  FOREIGN KEY (idCategoria) REFERENCES Categorias(idCategoria),
  PRIMARY KEY (idReservaAluguer, idCategoria)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'RegistoAluguers'
--

CREATE TABLE RegistoAluguers (
  idRegistoAluguer INT UNSIGNED NOT NULL AUTO_INCREMENT,
  data timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  idFuncionario INT UNSIGNED DEFAULT NULL,
  idReservaAluguer INT UNSIGNED DEFAULT NULL,
  concluido BINARY( 1 ) NOT NULL DEFAULT '0',
  FOREIGN KEY (idFuncionario) REFERENCES Funcionarios(idFuncionario),
  FOREIGN KEY (idReservaAluguer) REFERENCES ReservaAluguers(idReservaAluguer),
  PRIMARY KEY (idRegistoAluguer)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'RegistoVendas'
--

CREATE TABLE RegistoVendas (
  idRegistoVenda INT UNSIGNED NOT NULL AUTO_INCREMENT,
  data timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  idBicicleta INT UNSIGNED DEFAULT NULL,
  idFuncionario INT UNSIGNED DEFAULT NULL,
  idCliente INT UNSIGNED DEFAULT NULL,
  FOREIGN KEY (idBicicleta) REFERENCES StockBicicletas(idBicicleta),
  FOREIGN KEY (idFuncionario) REFERENCES Funcionarios(idFuncionario),
  FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente),
  PRIMARY KEY (idRegistoVenda)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'RegistoAluguerBicicletas
--

CREATE TABLE RegistoAluguerBicicletas (
  idRegistoAluguer INT UNSIGNED DEFAULT NULL,
  idBicicleta INT UNSIGNED DEFAULT NULL,
  FOREIGN KEY (idRegistoAluguer) REFERENCES RegistoAluguers(idRegistoAluguer),
  FOREIGN KEY (idBicicleta) REFERENCES StockBicicletas(idBicicleta),
  PRIMARY KEY (idRegistoAluguer, idBicicleta)
) ENGINE=InnoDB;

--
-- Estrutura da tabela 'ReservaBicicletas
--
/*
CREATE TABLE ReservaBicicletas (
  idReservaAluguer INT UNSIGNED DEFAULT NULL,
  idCategoria INT UNSIGNED DEFAULT NULL,
  idBicicleta INT UNSIGNED DEFAULT NULL,
  FOREIGN KEY (idReservaAluguer) REFERENCES ReservaCategorias(idReservaAluguer),
  FOREIGN KEY (idCategoria) REFERENCES ReservaCategorias(idCategoria),
  FOREIGN KEY (idBicicleta) REFERENCES StockBicicletas(idBicicleta),
  PRIMARY KEY (idReservaAluguer, idCategoria, idBicicleta)
) ENGINE=InnoDB;
*/

-- INSERTS --------
-- pass e igual ao username
INSERT INTO Clientes (idCliente,nomeCliente,password,nome,email,cartaoCredito) VALUES 
(1,'ana','72019bbac0b3dac88beac9ddfef0ca808919104f','Ana Sampaio','ana','123'),
(2,'andre','bc9800b9d52a24cce72a73dd528afed53f10e5fc','Andre Pimenta','andre','1234'),
(3,'andreia','c2efe46de2297a51be8b3abf1b6e91714fb2108d','Andreia','andreia','12345'),
(4,'cedric','240633aa59d25638de9800ef43a88ad2e208d24d','Cedric','cedric','123456'),
(5,'rafael','2d8d596a0b97569f9226a8c33ed9c6dbc8d88120','Rafael','rafael','1234567');

INSERT INTO Categorias (idCategoria, nome, precoAluguer) VALUES
(1,'Bicicleta de Estrada',30.0),
(2,'Bicicleta de Turismo',20.0),
(3,'Bicicleta de Cidade',25.0),
(4,'Bicicleta de Cross',40.0),
(5,'Bicicleta Utilitária',15.0),
(6,'Bicicleta de Montanha',45.0),
(7,'Bicicleta BMX',50.0);

INSERT INTO Estados (idEstado, nome) VALUES
(1,'Alugada'),
(2,'Vendida'),
(3,'Em Manutenção'),
(4,'Disponível');

INSERT INTO Funcionarios(idFuncionario, nomeFuncionario, password, nome, email) VALUES
(1,'helena','08fdadc2b4982085d4a63e53a6773f291d52c8ec','helena','helena'),
(2,'marina','79b333c96ec99512a3bf72653b23c7ed8a52dc42','marina','marina');

INSERT INTO StockBicicletas(idBicicleta, designacao, nova, precovenda, idCategoria, idEstado) VALUES
(1,'MX5',1,250.0,1,4),
(2,'BTT3',1,235.0,6,4),
(3,'City Cruiser',1,125.0,3,1),
(4,'Bicla',0,50.0,2,2),
(5,'Famela',0,35.0,5,3),
(6,'Rodinhas',0,80.0,4,4);

INSERT INTO ReservaAluguers(idReservaAluguer,dataInicio,dataFim,preco,idCliente) VALUES
(1,'2012-08-08','2012-09-09',30.0,1),
(2,'2012-05-05','2012-05-07',20.0,2),
(3,'2012-07-15','2012-07-20',25.0,3),
(4,'2012-03-10','2012-03-12',40.0,4);

INSERT INTO RegistoManutencaos (idManutencao, descricaoAvaria, dataInicio, dataFim, precoPosManutencao, 
              idBicicleta, idFuncionario) VALUES
(1,'Uma roda furada', '2012-07-01', '2012-08-01', 50.0, 3, 1);

INSERT INTO ReservaCategorias(idReservaAluguer,idCategoria,quantidade) VALUES
(1,1,1),
(2,2,1),
(3,3,1),
(4,4,1);

INSERT INTO RegistoAluguers(idRegistoAluguer,idFuncionario,idReservaAluguer) VALUES
(1,1,1),
(2,1,2),
(3,2,3),
(4,1,4);

INSERT INTO RegistoVendas (idRegistoVenda, idBicicleta, idFuncionario, idCliente) VALUES
(1,4,2,4);

INSERT INTO RegistoAluguerBicicletas (idRegistoAluguer, idBicicleta) VALUES
(1,1),
(2,4),
(3,5),
(4,6);
/*
INSERT INTO ReservaBicicletas (idReservaAluguer, idCategoria, idBicicleta) VALUES
(1,1,1),
(2,2,4),
(3,3,5),
(4,4,6);*/

