-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 25 Sty 2021, 21:00
-- Wersja serwera: 10.4.17-MariaDB
-- Wersja PHP: 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `warsztat_samochodowy`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa`
--

CREATE TABLE `naprawa` (
  `id_naprawy` int(11) NOT NULL,
  `koszt_naprawy` int(11) NOT NULL,
  `data_naprawy` date NOT NULL,
  `id_wlasciciela` int(11) NOT NULL,
  `id_uslugi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `naprawa`
--

INSERT INTO `naprawa` (`id_naprawy`, `koszt_naprawy`, `data_naprawy`, `id_wlasciciela`, `id_uslugi`) VALUES
(1, 220, '2020-12-16', 1, 2),
(2, 400, '2020-12-20', 3, 4),
(3, 2000, '2020-12-06', 4, 3),
(4, 50, '2020-11-13', 2, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `usluga`
--

CREATE TABLE `usluga` (
  `id_uslugi` int(11) NOT NULL,
  `nazwa_uslugi` text NOT NULL,
  `rodzaj_uslugi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `usluga`
--

INSERT INTO `usluga` (`id_uslugi`, `nazwa_uslugi`, `rodzaj_uslugi`) VALUES
(1, 'Wymiana kół na zimowe', 'Wymiana'),
(2, 'Naprawa alternatora', 'Naprawa'),
(3, 'Wymiana oleju', 'Wymiana'),
(4, 'Naprawa skrzyni biegów', 'Naprawa'),
(5, 'Wymiana airbag', 'Wymiana');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `id_uzytkownika` int(11) NOT NULL,
  `nazwa_uzytkownika` text NOT NULL,
  `haslo_uzytkownika` text NOT NULL,
  `email_uzytkownika` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`id_uzytkownika`, `nazwa_uzytkownika`, `haslo_uzytkownika`, `email_uzytkownika`) VALUES
(1, 'root', 'root', 'root@gmail.com');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wlasciciel`
--

CREATE TABLE `wlasciciel` (
  `id_wlasciciela` int(11) NOT NULL,
  `imie_wlasciciela` text NOT NULL,
  `nazwisko_wlasciciela` text NOT NULL,
  `marka_samochodu_wlasciciela` text NOT NULL,
  `model_samochodu_wlasciciela` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `wlasciciel`
--

INSERT INTO `wlasciciel` (`id_wlasciciela`, `imie_wlasciciela`, `nazwisko_wlasciciela`, `marka_samochodu_wlasciciela`, `model_samochodu_wlasciciela`) VALUES
(1, 'Damian', 'Herczyk', 'Volkswagen', 'Polo'),
(2, 'Damian', 'Kata', 'Fiat', 'Polo'),
(3, 'Krystian', 'Strawiński', 'BMW', 'E46'),
(4, 'Agnieszka', 'Strojewska', 'Audi', 'TT');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  ADD PRIMARY KEY (`id_naprawy`),
  ADD KEY `id_uslugi` (`id_uslugi`),
  ADD KEY `id_wlasciciela` (`id_wlasciciela`);

--
-- Indeksy dla tabeli `usluga`
--
ALTER TABLE `usluga`
  ADD PRIMARY KEY (`id_uslugi`);

--
-- Indeksy dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`id_uzytkownika`);

--
-- Indeksy dla tabeli `wlasciciel`
--
ALTER TABLE `wlasciciel`
  ADD PRIMARY KEY (`id_wlasciciela`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  MODIFY `id_naprawy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `usluga`
--
ALTER TABLE `usluga`
  MODIFY `id_uslugi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `id_uzytkownika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `wlasciciel`
--
ALTER TABLE `wlasciciel`
  MODIFY `id_wlasciciela` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  ADD CONSTRAINT `naprawa_ibfk_1` FOREIGN KEY (`id_uslugi`) REFERENCES `usluga` (`id_uslugi`),
  ADD CONSTRAINT `naprawa_ibfk_2` FOREIGN KEY (`id_wlasciciela`) REFERENCES `wlasciciel` (`id_wlasciciela`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
