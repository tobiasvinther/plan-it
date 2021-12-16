-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema planit
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `planit` ;

-- -----------------------------------------------------
-- Schema planit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `planit` DEFAULT CHARACTER SET utf8 ;
USE `planit` ;

-- -----------------------------------------------------
-- Table `planit`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planit`.`users` ;

CREATE TABLE IF NOT EXISTS `planit`.`users` (
  `userid` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NULL DEFAULT NULL,
  `useremail` VARCHAR(60) NULL DEFAULT NULL,
  `userpassword` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `planit`.`projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planit`.`projects` ;

CREATE TABLE IF NOT EXISTS `planit`.`projects` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT 'New Project',
  `deadline` DATE NULL DEFAULT NULL,
  `project_owner` INT(11) NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_owner_idx` (`project_owner` ASC) VISIBLE,
  CONSTRAINT `fk_project_owner`
    FOREIGN KEY (`project_owner`)
    REFERENCES `planit`.`users` (`userid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `planit`.`subprojects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planit`.`subprojects` ;

CREATE TABLE IF NOT EXISTS `planit`.`subprojects` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NULL DEFAULT 'New Subproject',
  `deadline` DATE NULL DEFAULT NULL,
  `subproject_owner` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_subproject_owner_idx` (`subproject_owner` ASC) VISIBLE,
  CONSTRAINT `fk_subproject_owner`
    FOREIGN KEY (`subproject_owner`)
    REFERENCES `planit`.`projects` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `planit`.`tasks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planit`.`tasks` ;

CREATE TABLE IF NOT EXISTS `planit`.`tasks` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT 'New Task',
  `description` VARCHAR(300) NULL DEFAULT NULL,
  `hours` INT(11) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `deadline` DATE NULL DEFAULT NULL,
  `task_owner` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_task_owner_idx` (`task_owner` ASC) VISIBLE,
  CONSTRAINT `fk_task_owner`
    FOREIGN KEY (`task_owner`)
    REFERENCES `planit`.`subprojects` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

USE `planit`;

DELIMITER $$

USE `planit`$$
DROP TRIGGER IF EXISTS `planit`.`projects_BEFORE_DELETE` $$
USE `planit`$$
CREATE
DEFINER=`root`@`%`
TRIGGER `planit`.`projects_BEFORE_DELETE`
BEFORE DELETE ON `planit`.`projects`
FOR EACH ROW
BEGIN

END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
