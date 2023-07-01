-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema projektni_ip_projekat
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema projektni_ip_projekat
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projektni_ip_projekat` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `projektni_ip_projekat` ;

-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`USERS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `user_name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `avatar` LONGBLOB NULL,
  `mail` VARCHAR(100) NOT NULL,
  `is_activated` TINYINT NOT NULL,
  `status` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`CATEGORIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`CATEGORIES` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `CATEGORIES_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CATEGORIES_CATEGORIES1_idx` (`CATEGORIES_id` ASC) VISIBLE,
  CONSTRAINT `fk_CATEGORIES_CATEGORIES1`
    FOREIGN KEY (`CATEGORIES_id`)
    REFERENCES `projektni_ip_projekat`.`CATEGORIES` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`PRODUCTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`PRODUCTS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `price` DECIMAL NOT NULL,
  `type` ENUM('NEW', 'SECOND_HAND') NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `contact` VARCHAR(45) NOT NULL,
  `creation_date` DATE NOT NULL,
  `is_active` TINYINT NOT NULL,
  `is_sold` TINYINT NOT NULL,
  `is_finished` TINYINT NOT NULL,
  `seller` INT NOT NULL,
  `buyer` INT NULL,
  `CATEGORIES_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PRODUCT_USER1_idx` (`seller` ASC) VISIBLE,
  INDEX `fk_PRODUCT_USER2_idx` (`buyer` ASC) VISIBLE,
  INDEX `fk_PRODUCTS_CATEGORIES1_idx` (`CATEGORIES_id` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCT_USER1`
    FOREIGN KEY (`seller`)
    REFERENCES `projektni_ip_projekat`.`USERS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCT_USER2`
    FOREIGN KEY (`buyer`)
    REFERENCES `projektni_ip_projekat`.`USERS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCTS_CATEGORIES1`
    FOREIGN KEY (`CATEGORIES_id`)
    REFERENCES `projektni_ip_projekat`.`CATEGORIES` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`COMMENTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`COMMENTS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(500) NOT NULL,
  `PRODUCT_id` INT NOT NULL,
  `USER_id` INT NOT NULL,
  `creation_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_COMMENT_PRODUCT_idx` (`PRODUCT_id` ASC) VISIBLE,
  INDEX `fk_COMMENT_USER1_idx` (`USER_id` ASC) VISIBLE,
  CONSTRAINT `fk_COMMENT_PRODUCT`
    FOREIGN KEY (`PRODUCT_id`)
    REFERENCES `projektni_ip_projekat`.`PRODUCTS` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COMMENT_USER1`
    FOREIGN KEY (`USER_id`)
    REFERENCES `projektni_ip_projekat`.`USERS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`IMAGES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`IMAGES` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `img` LONGBLOB NOT NULL,
  `PRODUCT_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_IMAGE_PRODUCT1_idx` (`PRODUCT_id` ASC) VISIBLE,
  CONSTRAINT `fk_IMAGE_PRODUCT1`
    FOREIGN KEY (`PRODUCT_id`)
    REFERENCES `projektni_ip_projekat`.`PRODUCTS` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`ADMINS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`ADMINS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `is_admin` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`ATTRIBUTES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`ATTRIBUTES` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `CATEGORY_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `type` ENUM('number', 'string', 'boolean') NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CATEGORY_ATTRIBUTES_CATEGORY1_idx` (`CATEGORY_id` ASC) VISIBLE,
  CONSTRAINT `fk_CATEGORY_ATTRIBUTES_CATEGORY1`
    FOREIGN KEY (`CATEGORY_id`)
    REFERENCES `projektni_ip_projekat`.`CATEGORIES` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`PRODUCT_ATTRIBUTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`PRODUCT_ATTRIBUTE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `PRODUCT_id` INT NOT NULL,
  `ATTRIBUTE_id` INT NOT NULL,
  `attribute` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PRODUCT_has_ATTRIBUTE_ATTRIBUTE1_idx` (`ATTRIBUTE_id` ASC) VISIBLE,
  INDEX `fk_PRODUCT_has_ATTRIBUTE_PRODUCT1_idx` (`PRODUCT_id` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCT_has_ATTRIBUTE_PRODUCT1`
    FOREIGN KEY (`PRODUCT_id`)
    REFERENCES `projektni_ip_projekat`.`PRODUCTS` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCT_has_ATTRIBUTE_ATTRIBUTE1`
    FOREIGN KEY (`ATTRIBUTE_id`)
    REFERENCES `projektni_ip_projekat`.`ATTRIBUTES` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`MESSAGES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`MESSAGES` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `status` TINYINT NOT NULL,
  `USER_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_USER_SUPPORT_USER1_idx` (`USER_id` ASC) VISIBLE,
  CONSTRAINT `fk_USER_SUPPORT_USER1`
    FOREIGN KEY (`USER_id`)
    REFERENCES `projektni_ip_projekat`.`USERS` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projektni_ip_projekat`.`STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektni_ip_projekat`.`STATISTICS` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
