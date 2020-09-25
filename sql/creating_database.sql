-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema iinspection_board
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `iinspection_board` ;

-- -----------------------------------------------------
-- Schema iinspection_board
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iinspection_board` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `iinspection_board` ;

-- -----------------------------------------------------
-- Table `iinspection_board`.`faculty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`faculty` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `budget_amount` INT NULL DEFAULT '0',
  `total_amount` INT NOT NULL,
  `description` VARCHAR(400) NOT NULL,
  `logo_url` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `idn` INT NOT NULL,
  `block` TINYINT NOT NULL DEFAULT '0',
  `user_role_id` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `idn_UNIQUE` (`idn` ASC) VISIBLE,
  INDEX `fk_user_user_role_idx` (`user_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_user_role`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `iinspection_board`.`user_role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`admission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`admission` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `faculty_id` INT NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `is_approved` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `user_id`, `faculty_id`),
  INDEX `fk_user_has_faculty_faculty1_idx` (`faculty_id` ASC) VISIBLE,
  INDEX `fk_user_has_faculty_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_faculty_faculty1`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `iinspection_board`.`faculty` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_user_has_faculty_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `iinspection_board`.`user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`subject_exam`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`subject_exam` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`faculty_exam_demends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`faculty_exam_demends` (
  `subject_exam_id` INT NOT NULL,
  `faculty_id` INT NOT NULL,
  PRIMARY KEY (`subject_exam_id`, `faculty_id`),
  INDEX `fk_subject_exam_has_faculty_faculty1_idx` (`faculty_id` ASC) VISIBLE,
  INDEX `fk_subject_exam_has_faculty_subject_exam1_idx` (`subject_exam_id` ASC) VISIBLE,
  CONSTRAINT `fk_subject_exam_has_faculty_faculty1`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `iinspection_board`.`faculty` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_subject_exam_has_faculty_subject_exam1`
    FOREIGN KEY (`subject_exam_id`)
    REFERENCES `iinspection_board`.`subject_exam` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`user_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`user_details` (
  `user_id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `patronymic` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `region` VARCHAR(45) NULL DEFAULT NULL,
  `school_name` VARCHAR(45) NULL DEFAULT NULL,
  `document_url` VARCHAR(300) NULL DEFAULT NULL,
  `average_certificate_point` INT NOT NULL,
  INDEX `fk_user_details_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_details_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `iinspection_board`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`users_results`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`users_results` (
  `user_id` INT NOT NULL,
  `subject_exam_id` INT NOT NULL,
  `result` INT NOT NULL,
  `date_of_exam` DATE NOT NULL,
  PRIMARY KEY (`user_id`, `subject_exam_id`),
  INDEX `fk_user_has_subject_exam_subject_exam1_idx` (`subject_exam_id` ASC) VISIBLE,
  INDEX `fk_user_has_subject_exam_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_subject_exam_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `iinspection_board`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_subject_exam_subject_exam1`
    FOREIGN KEY (`subject_exam_id`)
    REFERENCES `iinspection_board`.`subject_exam` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
