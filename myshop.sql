-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 14, 2023 alle 16:59
-- Versione del server: 10.4.27-MariaDB
-- Versione PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myshop`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzini`
--

CREATE TABLE `magazzini` (
  `id` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `venditore` tinyint(1) NOT NULL,
  `tempiDiConsegna` int(11) DEFAULT NULL,
  `sogliaScontoNPezzi` int(11) DEFAULT NULL,
  `scontoNPezzi` double DEFAULT NULL,
  `sogliaPerScontoTotale` double DEFAULT NULL,
  `scontoSuTotale` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `magazzini`
--

INSERT INTO `magazzini` (`id`, `nome`, `password`, `venditore`, `tempiDiConsegna`, `sogliaScontoNPezzi`, `scontoNPezzi`, `sogliaPerScontoTotale`, `scontoSuTotale`) VALUES
(1, 'Andrea', '123', 1, 5, 10, 4.5, 1500, 2),
(2, 'AndreaNeri', '124', 1, 6, 5, 10, 0, 0),
(3, 'AndreaNNri', '124', 1, 0, 0, 0, 0, 0),
(4, 'Davide', 'Muratore', 0, 0, 0, 0, 0, 0),
(5, 'Saix', '858', 1, 2, 40, 3, 7000, 2),
(6, 'Adriana', 'cucu', 0, 0, 0, 0, 0, 0),
(7, 'Fede', 'fede', 1, 5, 4, 1, 20000, 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzinodiadriana`
--

CREATE TABLE `magazzinodiadriana` (
  `id` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `quantità` int(11) NOT NULL,
  `scontato` tinyint(1) NOT NULL,
  `prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `magazzinodiadriana`
--

INSERT INTO `magazzinodiadriana` (`id`, `nome`, `quantità`, `scontato`, `prezzo`) VALUES
(1, 'Davide', 52, 0, 2000),
(2, 'roberto', 22, 0, 69.99);

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzinodiandrea`
--

CREATE TABLE `magazzinodiandrea` (
  `id` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `quantità` int(11) NOT NULL,
  `scontato` tinyint(1) NOT NULL,
  `prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `magazzinodiandrea`
--

INSERT INTO `magazzinodiandrea` (`id`, `nome`, `quantità`, `scontato`, `prezzo`) VALUES
(1, 'display benq 17 pollici', 62, 0, 128.6),
(2, 'roberto', 0, 0, 69.99);

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzinodiandreaneri`
--

CREATE TABLE `magazzinodiandreaneri` (
  `id` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `quantità` int(11) NOT NULL,
  `scontato` tinyint(1) NOT NULL,
  `prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `magazzinodiandreaneri`
--

INSERT INTO `magazzinodiandreaneri` (`id`, `nome`, `quantità`, `scontato`, `prezzo`) VALUES
(1, 'display benq 17 pollici', 50, 0, 28.6),
(3, 'roberto', 15, 0, 69.99);

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzinodiandreannri`
--

CREATE TABLE `magazzinodiandreannri` (
  `id` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `quantità` int(11) NOT NULL,
  `scontato` tinyint(1) NOT NULL,
  `prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `magazzinodiandreannri`
--

INSERT INTO `magazzinodiandreannri` (`id`, `nome`, `quantità`, `scontato`, `prezzo`) VALUES
(1, 'display benq 17 pollici', 48, 0, 1260.58),

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzinodidavide`
--

CREATE TABLE `magazzinodidavide` (
  `id` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `quantità` int(11) NOT NULL,
  `scontato` tinyint(1) NOT NULL,
  `prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `magazzinodidavide`
--

INSERT INTO `magazzinodidavide` (`id`, `nome`, `quantità`, `scontato`, `prezzo`) VALUES
(1, 'Davide', 64, 0, 0),
(2, 'Davide', 42, 0, 2000),
(4, 'Davide', 41, 0, 2000),
(5, 'roberto', 19, 0, 69.99);

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzinodifede`
--

CREATE TABLE `magazzinodifede` (
  `id` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `quantità` int(11) NOT NULL,
  `scontato` tinyint(1) NOT NULL,
  `prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `magazzinodisaix`
--

CREATE TABLE `magazzinodisaix` (
  `id` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `quantità` int(11) NOT NULL,
  `scontato` tinyint(1) NOT NULL,
  `prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `magazzinodisaix`
--

INSERT INTO `magazzinodisaix` (`id`, `nome`, `quantità`, `scontato`, `prezzo`) VALUES
(1, 'Davide', 1, 0, 2000),

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `magazzini`
--
ALTER TABLE `magazzini`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `magazzinodiadriana`
--
ALTER TABLE `magazzinodiadriana`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `magazzinodiandrea`
--
ALTER TABLE `magazzinodiandrea`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `magazzinodiandreaneri`
--
ALTER TABLE `magazzinodiandreaneri`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `magazzinodiandreannri`
--
ALTER TABLE `magazzinodiandreannri`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `magazzinodidavide`
--
ALTER TABLE `magazzinodidavide`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `magazzinodifede`
--
ALTER TABLE `magazzinodifede`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `magazzinodisaix`
--
ALTER TABLE `magazzinodisaix`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `magazzini`
--
ALTER TABLE `magazzini`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `magazzinodiadriana`
--
ALTER TABLE `magazzinodiadriana`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `magazzinodiandrea`
--
ALTER TABLE `magazzinodiandrea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `magazzinodiandreaneri`
--
ALTER TABLE `magazzinodiandreaneri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `magazzinodiandreannri`
--
ALTER TABLE `magazzinodiandreannri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `magazzinodidavide`
--
ALTER TABLE `magazzinodidavide`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `magazzinodifede`
--
ALTER TABLE `magazzinodifede`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `magazzinodisaix`
--
ALTER TABLE `magazzinodisaix`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
