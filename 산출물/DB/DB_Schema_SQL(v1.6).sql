-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema chabit
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chabit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chabit` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `chabit` ;

-- -----------------------------------------------------
-- Table `chabit`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`category` (
  `category_id` BIGINT NOT NULL,
  `category_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE INDEX `CATEGORY_NAME_UNIQUE` (`category_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`user` (
  `user_id` BIGINT NOT NULL,
  `user_email` VARCHAR(255) NOT NULL,
  `user_password` VARCHAR(50) NOT NULL,
  `user_phone` VARCHAR(50) NULL DEFAULT NULL,
  `user_points` INT NULL DEFAULT '1000',
  `user_role` VARCHAR(45) NOT NULL DEFAULT 'USER',
  `user_joindate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_name` VARCHAR(45) NULL DEFAULT NULL,
  `user_nickname` VARCHAR(45) NULL DEFAULT NULL,
  `user_image` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `USER_EMAIL_UNIQUE` (`user_email` ASC) VISIBLE,
  UNIQUE INDEX `USER_NICKNAME_UNIQUE` (`user_nickname` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`challenge` (
  `challenge_id` BIGINT NOT NULL,
  `challenge_name` VARCHAR(255) NOT NULL,
  `challenge_desc` TEXT NULL DEFAULT NULL,
  `challenge_owner` BIGINT NOT NULL,
  `category_id` BIGINT NULL DEFAULT NULL,
  `challenge_startdate` DATETIME NOT NULL,
  `challenge_enddate` DATETIME NOT NULL,
  `challenge_thumbnail` TEXT NULL DEFAULT NULL,
  `challenge_point` INT NULL DEFAULT '100',
  `challenge_usercount` INT NULL DEFAULT '1',
  `auth_way` VARCHAR(255) NULL DEFAULT NULL,
  `auth_frequency` INT NOT NULL,
  `auth_starttime` TIME NULL DEFAULT '00:00:00',
  `auth_endtime` TIME NULL DEFAULT '23:59:59',
  `auth_holiday` TINYINT NOT NULL,
  `auth_example` TEXT NULL DEFAULT NULL,
  `challenge_ongoing` VARCHAR(45) NULL DEFAULT 'READY',
  PRIMARY KEY (`challenge_id`),
  INDEX `CHALLENGE_USER_FK_idx` (`challenge_owner` ASC) VISIBLE,
  INDEX `CHALLENGE_CATEGORY_FK_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `CHALLENGE_CATEGORY_FK`
    FOREIGN KEY (`category_id`)
    REFERENCES `chabit`.`category` (`category_id`),
  CONSTRAINT `CHALLENGE_USER_FK`
    FOREIGN KEY (`challenge_owner`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`hashtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`hashtag` (
  `hashtag_id` BIGINT NOT NULL,
  `hashtag_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`hashtag_id`),
  UNIQUE INDEX `HASHTAG_NAME_UNIQUE` (`hashtag_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`challenge_hashtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`challenge_hashtag` (
  `challenge_id` BIGINT NOT NULL,
  `hashtag_id` BIGINT NOT NULL,
  PRIMARY KEY (`challenge_id`, `hashtag_id`),
  INDEX `CHALLENGE_HASHTAG_CHALLENGE_FK_idx` (`challenge_id` ASC) VISIBLE,
  INDEX `CHALLENGE_HASHTAG_HASHTAG_FK_idx` (`hashtag_id` ASC) VISIBLE,
  CONSTRAINT `challenge_hashtag_challenge_fk`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `chabit`.`challenge` (`challenge_id`),
  CONSTRAINT `challenge_hashtag_hashtag_fk`
    FOREIGN KEY (`hashtag_id`)
    REFERENCES `chabit`.`hashtag` (`hashtag_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`review` (
  `review_id` BIGINT NOT NULL,
  `review_content` TEXT NOT NULL,
  `review_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `cool_count` INT NULL DEFAULT '0',
  `comment_count` INT NULL DEFAULT '0',
  `user_id` BIGINT NULL DEFAULT NULL,
  `challenge_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `REVIEW_USER_FK_idx` (`user_id` ASC) VISIBLE,
  INDEX `REVIEW_CHALLENGE_FK_idx` (`challenge_id` ASC) VISIBLE,
  CONSTRAINT `REVIEW_CHALLENGE_FK`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `chabit`.`challenge` (`challenge_id`),
  CONSTRAINT `REVIEW_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`cool`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`cool` (
  `cool_id` BIGINT NOT NULL,
  `review_id` BIGINT NULL DEFAULT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`cool_id`),
  INDEX `COOL_REVIEW_FK_idx` (`review_id` ASC) VISIBLE,
  INDEX `COOL_USER_FK_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `COOL_REVIEW_FK`
    FOREIGN KEY (`review_id`)
    REFERENCES `chabit`.`review` (`review_id`),
  CONSTRAINT `COOL_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`follow` (
  `user_id` BIGINT NOT NULL,
  `following_id` BIGINT NOT NULL,
  `follow_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `following_id`),
  INDEX `USER_FOLLOWED_FK_idx` (`following_id` ASC) VISIBLE,
  INDEX `user_follow_fk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_follow_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`),
  CONSTRAINT `user_following_fk`
    FOREIGN KEY (`following_id`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`level` (
  `level` VARCHAR(255) NOT NULL,
  `level_min_point` INT NOT NULL,
  `level_max_point` INT NOT NULL,
  PRIMARY KEY (`level`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`point_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`point_history` (
  `history_id` BIGINT NOT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  `challenge_id` BIGINT NULL DEFAULT NULL,
  `point_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `point_change` INT NOT NULL,
  PRIMARY KEY (`history_id`),
  INDEX `POINT_HISTORY_USER_FK_idx` (`user_id` ASC) VISIBLE,
  INDEX `POINT_HISTORY_CHALLENGE_FK_idx` (`challenge_id` ASC) VISIBLE,
  CONSTRAINT `POINT_HISTORY_CHALLENGE_FK`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `chabit`.`challenge` (`challenge_id`),
  CONSTRAINT `POINT_HISTORY_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`proof`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`proof` (
  `proof_id` BIGINT NOT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  `challenge_id` BIGINT NULL DEFAULT NULL,
  `proof_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `proof_image` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`proof_id`),
  INDEX `PROOF_USER_FK_idx` (`user_id` ASC) VISIBLE,
  INDEX `PROOF_CHALLENGE_FK_idx` (`challenge_id` ASC) VISIBLE,
  CONSTRAINT `PROOF_CHALLENGE_FK`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `chabit`.`challenge` (`challenge_id`),
  CONSTRAINT `PROOF_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`review_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`review_comment` (
  `review_comment_id` BIGINT NOT NULL,
  `review_id` BIGINT NULL DEFAULT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  `comment_content` TEXT NOT NULL,
  `parent_comment_id` BIGINT NULL DEFAULT NULL,
  `comment_order` INT NULL DEFAULT NULL,
  PRIMARY KEY (`review_comment_id`),
  INDEX `REVIEW_COMMENT_REVIEW_FK_idx` (`review_id` ASC) VISIBLE,
  INDEX `REVIEW_COMMENT_USER_FK_idx` (`user_id` ASC) VISIBLE,
  INDEX `REVIEW_COMMENT_REVIEW_COMMENT_FK_idx` (`parent_comment_id` ASC) VISIBLE,
  CONSTRAINT `REVIEW_COMMENT_REVIEW_COMMENT_FK`
    FOREIGN KEY (`parent_comment_id`)
    REFERENCES `chabit`.`review_comment` (`review_comment_id`),
  CONSTRAINT `REVIEW_COMMENT_REVIEW_FK`
    FOREIGN KEY (`review_id`)
    REFERENCES `chabit`.`review` (`review_id`),
  CONSTRAINT `REVIEW_COMMENT_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`review_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`review_image` (
  `review_image_id` BIGINT NOT NULL,
  `review_id` BIGINT NULL DEFAULT NULL,
  `review_image` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`review_image_id`),
  INDEX `REVIEW_IMAGE_REVIEW_FK_idx` (`review_id` ASC) VISIBLE,
  CONSTRAINT `REVIEW_IMAGE_REVIEW_FK`
    FOREIGN KEY (`review_id`)
    REFERENCES `chabit`.`review` (`review_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`user_challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`user_challenge` (
  `user_id` BIGINT NOT NULL,
  `challenge_id` BIGINT NOT NULL,
  `user_challenge_result` VARCHAR(45) NULL DEFAULT 'READY',
  PRIMARY KEY (`user_id`, `challenge_id`),
  INDEX `USER_CHALLNEGE_USER_FK_idx` (`user_id` ASC) VISIBLE,
  INDEX `USER_CHALLENGE_CHALLNEGE_FK_idx` (`challenge_id` ASC) VISIBLE,
  CONSTRAINT `USER_CHALLENGE_CHALLNEGE_FK`
    FOREIGN KEY (`challenge_id`)
    REFERENCES `chabit`.`challenge` (`challenge_id`),
  CONSTRAINT `USER_CHALLNEGE_USER_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`user_hashtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`user_hashtag` (
  `user_id` BIGINT NOT NULL,
  `hashtag_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `hashtag_id`),
  INDEX `USER_HASHTAG_USER_FK_idx` (`user_id` ASC) VISIBLE,
  INDEX `USER_HASHTAG_HASHTAG_FK_idx` (`hashtag_id` ASC) VISIBLE,
  CONSTRAINT `user_hashtag_hashtag_fk`
    FOREIGN KEY (`hashtag_id`)
    REFERENCES `chabit`.`hashtag` (`hashtag_id`),
  CONSTRAINT `user_hashtag_user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `chabit`.`proof_example`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`proof_example` (
  `proof_example_id` BIGINT NOT NULL,
  `proof_example_img` TEXT NOT NULL,
  PRIMARY KEY (`proof_example_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chabit`.`badge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`badge` (
  `badge_id` BIGINT NOT NULL,
  `badge_name` VARCHAR(255) NOT NULL,
  `badge_type` VARCHAR(45) NOT NULL,
  `category_id` BIGINT NULL,
  PRIMARY KEY (`badge_id`),
  INDEX `badge_category_category_id_fk_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `badge_category_category_id_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `chabit`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chabit`.`user_badge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`user_badge` (
  `user_id` BIGINT NOT NULL,
  `badge_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `badge_id`),
  INDEX `user_badge_badge_badge_id_fk_idx` (`badge_id` ASC) VISIBLE,
  CONSTRAINT `user_badge_user_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_badge_badge_badge_id_fk`
    FOREIGN KEY (`badge_id`)
    REFERENCES `chabit`.`badge` (`badge_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chabit`.`user_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chabit`.`user_category` (
  `user_id` BIGINT NOT NULL,
  `category_id` BIGINT NOT NULL,
  `success_count` INT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`, `category_id`),
  INDEX `user_category_category_category_id_fk_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `user_category_user_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `chabit`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_category_category_category_id_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `chabit`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
