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
CREATE SCHEMA IF NOT EXISTS `iinspection_board` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
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
  `facultycol` VARCHAR(45) NULL DEFAULT NULL,
  `name_ua` VARCHAR(100) NOT NULL,
  `description_ua` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `name_ua_UNIQUE` (`name_ua` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
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
  `idn` BIGINT NOT NULL,
  `block` TINYINT NOT NULL DEFAULT '0',
  `user_role_id` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `idn_UNIQUE` (`idn` ASC) VISIBLE,
  INDEX `fk_user_user_role_idx` (`user_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_user_role`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `iinspection_board`.`user_role` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`admission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`admission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `faculty_id` INT NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `is_approved` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `user_id`, `faculty_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_user_has_faculty_faculty1_idx` (`faculty_id` ASC) VISIBLE,
  INDEX `fk_user_has_faculty_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_faculty_faculty1`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `iinspection_board`.`faculty` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_faculty_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `iinspection_board`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 69
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iinspection_board`.`subject_exam`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iinspection_board`.`subject_exam` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(300) NOT NULL,
  `name_ua` VARCHAR(45) NOT NULL,
  `description_ua` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `subject_examcol_UNIQUE` (`name_ua` ASC) VISIBLE)
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
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_exam_has_faculty_subject_exam1`
    FOREIGN KEY (`subject_exam_id`)
    REFERENCES `iinspection_board`.`subject_exam` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
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
  `name_ua` VARCHAR(45) NULL DEFAULT NULL,
  `surname_ua` VARCHAR(45) NULL DEFAULT NULL,
  `patronymic_ua` VARCHAR(45) NULL DEFAULT NULL,
  `city_ua` VARCHAR(45) NULL DEFAULT NULL,
  `region_ua` VARCHAR(45) NULL DEFAULT NULL,
  `school_name_ua` VARCHAR(45) NULL DEFAULT NULL,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
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
  CONSTRAINT `fk_user_has_subject_exam_subject_exam1`
    FOREIGN KEY (`subject_exam_id`)
    REFERENCES `iinspection_board`.`subject_exam` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_subject_exam_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `iinspection_board`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;



INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (1,"Math exam","External Independent Testing", "Екзамен з Математики", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (2,"Ukrainian language and literature exam","External Independent Testing", "Екзамен з Української мови та літератури", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (3,"Physics exam","External Independent Testing" , "Екзамен з Фізики", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (4,"Spanish language exam","External Independent Testing" , "Екзамен з Іспанської мови", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (5,"German language exam","External Independent Testing", "Екзамен з Німецької мови", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (6,"French language exam","External Independent Testing", "Екзамен з Французької мови", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (7,"English language exam","External Independent Testing", "Екзамен з Англійської мови", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (8,"History of Ukraine exam","External Independent Testing" , "Екзамен з Історії України", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (9,"Biology exam","External Independent Testing", "Екзамен з Біології", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (10,"Geography exam","External Independent Testing", "Екзамен з Географії", "Екзамен Зовнішнього Незалежного Оцінювання");
INSERT INTO subject_exam(id, name, description, name_ua, description_ua) VALUES (11,"Chemistry exam","External Independent Testing", "Екзамен з Хімії", "Екзамен Зовнішнього Незалежного Оцінювання");


INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (1, "Faculty of Information Technology", 25 , 50 , "Faculty of Information Technology","Факультет Інформаційних Технологій","Факультет Інформаційних Технологій");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (2, "Faculty of Physics", 15 , 30 , "Faculty of Physics","Факультет Фізики","Факультет Фізики");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (3, "Faculty of Chemistry", 15 , 30 , "Faculty of Chemistry","Факультет Хімії","Факультет Хімії");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (4, "Faculty of History", 40 , 100 , "Faculty of History","Факультет Історії","Факультет Історії");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (5, "Faculty of Construction", 25 , 50 , "Faculty of Construction","Факультет Будівництва","Факультет Будівництва");
INSERT INTO faculty(id, name, budget_amount , total_amount , description, name_ua, description_ua) VALUES (6, "Faculty of Finance and Economics", 25 , 50 , "Faculty of Finance and Economics","Факультет Економіки та фінансів","Факультет Економіки та фінансів");


INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 1);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 1);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (7 , 1);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 2);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 2);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (3 , 2);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 3);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 3);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (11 , 3);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (8 , 4);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 4);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (7 , 4);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 5);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 5);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (10 , 5);

INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (1 , 6);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (2 , 6);
INSERT INTO faculty_exam_demends (subject_exam_id , faculty_id) VALUES (7 , 6);


INSERT INTO user_role (id , name) VALUES (1 , "user");
INSERT INTO user_role (id , name) VALUES (2 , "admin");


INSERT INTO user (id , email, idn, block, user_role_id, password) VALUES (1 , "test@gmail.com", 111111, 0, 2, 1111);


INSERT INTO user_details(user_id, name, surname, patronymic, city, region, school_name, average_certificate_point, name_ua, surname_ua, patronymic_ua, city_ua, region_ua, school_name_ua) VALUES (1, "test", "test","test","test","test","test",200, "test", "test","test","test","test","test");


INSERT INTO admission (id, user_id, faculty_id, date, is_approved) VALUES (1, 1, 1, "2020-09-23", 0);


INSERT INTO users_results(user_id, subject_exam_id, result, date_of_exam) VALUES (1, 1, 200,"2020-09-23");

INSERT INTO users_results (user_id, subject_exam_id, result, date_of_exam) VALUES (1, 2, 200,"2020-09-23");

INSERT INTO users_results (user_id, subject_exam_id, result, date_of_exam) VALUES (1, 7, 200,"2020-09-23");

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
