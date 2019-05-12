-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 05-Maio-2019 às 20:48
-- Versão do servidor: 10.1.34-MariaDB-0ubuntu0.18.04.1
-- PHP Version: 7.0.4-7ubuntu2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `estoque`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `autorizacao`
--

CREATE TABLE `usuario` (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(20) NOT NULL,
  senha VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UNI_USUARIO_NOME (nome)
);

CREATE TABLE autorizacao (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UNI_AUT_NOME (nome)
);

CREATE TABLE usuario_autorizacao (
  usuario_id BIGINT UNSIGNED NOT NULL,
  autorizacao_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (usuario_id, autorizacao_id),
  FOREIGN KEY AUT_USUARIO_FK (usuario_id) REFERENCES usuario (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY AUT_AUTORIZACAO_FK (autorizacao_id) REFERENCES autorizacao (id) ON DELETE RESTRICT ON UPDATE CASCADE
);
-- --------------------------------------------------------

--
-- Estrutura da tabela `prateleira`
--

CREATE TABLE `prateleira` (
  `id` bigint(20) NOT NULL,
  `data_entrada` date DEFAULT NULL,
  `data_fabricacao` date DEFAULT NULL,
  `data_nota_fiscal` date DEFAULT NULL,
  `nota_fiscal` bigint(20) NOT NULL,
  `numero_lote` bigint(20) NOT NULL,
  `id_produto` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL,
  `fornecedor` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `periodo_validade` int(11) NOT NULL,
  `temp_armazemnagem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `uau_usuario_autorizacao`
--

CREATE TABLE `uau_usuario_autorizacao` (
  `aut_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `uau_usuario_autorizacao`
--

INSERT INTO `uau_usuario_autorizacao` (`aut_id`, `user_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--


--
-- Indexes for dumped tables
--

--
-- Indexes for table `autorizacao`
--
ALTER TABLE `autorizacao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prateleira`
--
ALTER TABLE `prateleira`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autorizacao`
--
ALTER TABLE `autorizacao`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `prateleira`
--
ALTER TABLE `prateleira`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

INSERT INTO usuario(nome, senha) VALUES('admin', CONCAT('{MD5}', MD5('admin')))
INSERT INTO autorizacao(nome) VALUES('ROLE_USUARIO');
INSERT INTO AUT_AUTORIZACAO(AUT_NOME) VALUES('ROLE_ADMIN');

INSERT INTO UAU_USUARIO_AUTORIZACAO(USR_ID, AUT_ID) VALUES(
(SELECT id FROM usuario WHERE nome = 'admin'),
(SELECT id FROM autorizacao WHERE nome = 'ROLE_ADMIN'));

CREATE USER IF NOT EXISTS 'newuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON estoque.* TO 'newuser'@'localhost';
