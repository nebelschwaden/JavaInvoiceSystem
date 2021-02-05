SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`PERSONA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PERSONA` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(45) NULL,
  `contraseña` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `apellidoP` VARCHAR(45) NULL,
  `apellidoM` VARCHAR(45) NULL,
  `cedula` VARCHAR(10) NULL,
  `pasaporte` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NULL,
  `numeroTarjeta` VARCHAR(45) NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CLIENTE` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Persona_idPersona` INT NOT NULL,
  PRIMARY KEY (`idCliente`, `Persona_idPersona`),
  INDEX `fk_Cliente_Persona_idx` (`Persona_idPersona` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Persona`
    FOREIGN KEY (`Persona_idPersona`)
    REFERENCES `mydb`.`PERSONA` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ADMINISTRADOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ADMINISTRADOR` (
  `idAdministrador` INT NOT NULL AUTO_INCREMENT,
  `Persona_idPersona` INT NOT NULL,
  PRIMARY KEY (`idAdministrador`, `Persona_idPersona`),
  INDEX `fk_Administrador_Persona_idx` (`Persona_idPersona` ASC) VISIBLE,
  CONSTRAINT `fk_Administrador_Persona`
    FOREIGN KEY (`Persona_idPersona`)
    REFERENCES `mydb`.`PERSONA` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`FACTURA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`FACTURA` (
  `idFactura` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `Cliente_idCliente` INT NOT NULL,
  PRIMARY KEY (`idFactura`, `Cliente_idCliente`),
  INDEX `fk_Factura_Cliente1_idx` (`Cliente_idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_Factura_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `mydb`.`CLIENTE` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EVENTOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`EVENTOS` (
  `idEventos` INT NOT NULL AUTO_INCREMENT,
  `lugar` VARCHAR(45) NULL,
  `equipo1` VARCHAR(45) NULL,
  `equipo2` VARCHAR(45) NULL,
  `fecha` DATE NULL,
  `hora` TIME NULL,
  PRIMARY KEY (`idEventos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LOCALIDAD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`LOCALIDAD` (
  `idLocalidad` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `precio` FLOAT NULL,
  `numeroAsientos` INT NULL,
  `Eventos_idEventos` INT NOT NULL,
  PRIMARY KEY (`idLocalidad`, `Eventos_idEventos`),
  INDEX `fk_Localidad_Eventos1_idx` (`Eventos_idEventos` ASC) VISIBLE,
  CONSTRAINT `fk_Localidad_Eventos1`
    FOREIGN KEY (`Eventos_idEventos`)
    REFERENCES `mydb`.`EVENTOS` (`idEventos`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ASIENTOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ASIENTOS` (
  `idAsientos` INT NOT NULL AUTO_INCREMENT,
  `Localidad_idLocalidad` INT NOT NULL,
  `puesto` INT NULL,
  PRIMARY KEY (`idAsientos`, `Localidad_idLocalidad`),
  INDEX `fk_Asientos_Localidad1_idx` (`Localidad_idLocalidad` ASC) VISIBLE,
  CONSTRAINT `fk_Asientos_Localidad1`
    FOREIGN KEY (`Localidad_idLocalidad`)
    REFERENCES `mydb`.`LOCALIDAD` (`idLocalidad`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`BOLETO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BOLETO` (
  `idBoleto` INT NOT NULL AUTO_INCREMENT,
  `Cliente_idCliente` INT NOT NULL,
  `Factura_idFactura` INT NOT NULL,
  `Asientos_idAsientos` INT NOT NULL,
  PRIMARY KEY (`idBoleto`, `Cliente_idCliente`, `Factura_idFactura`, `Asientos_idAsientos`),
  INDEX `fk_Boleto_Cliente1_idx` (`Cliente_idCliente` ASC) VISIBLE,
  INDEX `fk_Boleto_Factura1_idx` (`Factura_idFactura` ASC) VISIBLE,
  INDEX `fk_Boleto_Asientos1_idx` (`Asientos_idAsientos` ASC) VISIBLE,
  CONSTRAINT `fk_Boleto_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `mydb`.`CLIENTE` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Boleto_Factura1`
    FOREIGN KEY (`Factura_idFactura`)
    REFERENCES `mydb`.`FACTURA` (`idFactura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Boleto_Asientos1`
    FOREIGN KEY (`Asientos_idAsientos`)
    REFERENCES `mydb`.`ASIENTOS` (`idAsientos`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
             
BEGIN;
			--Default username and password
INSERT INTO Persona VALUES (0,'cliente','cliente','John','Doe','Carlson','1700000000','','2676998','john@gmail.com','234234234');
INSERT INTO Cliente VALUES ( 0,last_insert_id());
COMMIT;
BEGIN;
			--Default username and password
INSERT INTO Persona VALUES (0,'administrador','administrador','Doe','John','','','AAAABBB00000','2676998','doe@gmail.com','');
INSERT INTO Administrador VALUES ( 0,last_insert_id());
COMMIT;
