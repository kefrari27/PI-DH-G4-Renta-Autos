-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_grupo4
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_grupo4
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_grupo4` DEFAULT CHARACTER SET utf8 ;
USE `db_grupo4` ;

-- -----------------------------------------------------
-- Table `db_grupo4`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_grupo4`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `descripcion` VARCHAR(255) NULL,
  `urlImagen` VARCHAR(250) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_grupo4`.`turnos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_grupo4`.`turnos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fechaHoraCheckIn` TIMESTAMP NOT NULL,
  `fechaHoraCheckOut` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_grupo4`.`marcas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_grupo4`.`marcas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_grupo4`.`modelos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_grupo4`.`modelos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_grupo4`.`autos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_grupo4`.`autos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patente` VARCHAR(10) NOT NULL,
  `idMarca` INT NOT NULL,
  `idCategoria` INT NOT NULL,
  `idModelo` INT NOT NULL,
  `año` INT NOT NULL,
  `urlImagen` VARCHAR(250) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Auto_Categoria1_idx` (`idCategoria` ASC) VISIBLE,
  INDEX `fk_Auto_Marca_idx` (`idMarca` ASC) VISIBLE,
  INDEX `fk_Auto_Modelo_idx` (`idModelo` ASC) VISIBLE,
  CONSTRAINT `fk_Auto_Marca`
    FOREIGN KEY (`idMarca`)
    REFERENCES `db_grupo4`.`marcas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Auto_Categoria`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `db_grupo4`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Auto_Modelo`
    FOREIGN KEY (`idModelo`)
    REFERENCES `db_grupo4`.`modelos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_grupo4`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_grupo4`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuarioNombre` VARCHAR(45) NOT NULL,
  `usuarioPassword` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_grupo4`.`reservas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_grupo4`.`reservas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idAuto` INT NOT NULL,
  `idTurno` INT NOT NULL,
  `idUsuario` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Reserva_Turno_idx` (`idTurno` ASC) VISIBLE,
  INDEX `fk_Reserva_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  INDEX `fk_Reserva_Auto_idx` (`idAuto` ASC) VISIBLE,
  CONSTRAINT `fk_Reserva_Turno`
    FOREIGN KEY (`idTurno`)
    REFERENCES `db_grupo4`.`turnos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Auto`
    FOREIGN KEY (`idAuto`)
    REFERENCES `db_grupo4`.`autos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `db_grupo4`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
