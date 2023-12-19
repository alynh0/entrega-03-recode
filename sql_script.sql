-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema globaltours
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema globaltours
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `globaltours` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `globaltours` ;

-- -----------------------------------------------------
-- Table `globaltours`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `globaltours`.`cliente` (
  `id` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `globaltours`.`viagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `globaltours`.`viagem` (
  `id` INT NOT NULL,
  `origem` VARCHAR(45) NOT NULL,
  `destino` VARCHAR(45) NOT NULL,
  `data_ida` DATE NOT NULL,
  `data_volta` DATE NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `preco` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `globaltours`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `globaltours`.`reserva` (
  `id` INT NOT NULL,
  `id_viagem` INT NULL DEFAULT NULL,
  `id_cliente` INT NULL DEFAULT NULL,
  `data_reserva` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_viagem` (`id_viagem` ASC) VISIBLE,
  INDEX `id_cliente` (`id_cliente` ASC) VISIBLE,
  CONSTRAINT `reserva_ibfk_1`
    FOREIGN KEY (`id_viagem`)
    REFERENCES `globaltours`.`viagem` (`id`),
  CONSTRAINT `reserva_ibfk_2`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `globaltours`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
