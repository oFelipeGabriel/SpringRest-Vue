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

CREATE TABLE `autorizacao` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `autorizacao`
--

INSERT INTO `autorizacao` (`id`, `nome`) VALUES
(1, 'admin');

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

INSERT INTO `usuario` (`id`, `nome`, `senha`, `username`) VALUES
(1, 'admin', 'admin', 'admin');

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
