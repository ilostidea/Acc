SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `acc`.`SysUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`SysUser` (
  `ID` BIGINT NOT NULL,
  `Mobile` VARCHAR(16) NOT NULL,
  `Email` VARCHAR(32) NOT NULL,
  `Passwd` VARCHAR(64) NULL,
  `RealName` VARCHAR(32) NULL,
  `NickName` VARCHAR(32) NULL,
  `Photo` VARCHAR(256) NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`SysLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`SysLog` (
  `ID` BIGINT NOT NULL,
  `IP` VARCHAR(16) NOT NULL,
  `Cookie` VARCHAR(256) NULL,
  `UserID` BIGINT NULL,
  `Log` VARCHAR(100) NOT NULL,
  `EntityName` VARCHAR(64) NOT NULL,
  `Operate` VARCHAR(32) NOT NULL,
  `Args` TEXT NULL,
  `OprtTime` TIMESTAMP NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_SysLog_User_idx` (`UserID` ASC),
  CONSTRAINT `fk_SysLog_User`
    FOREIGN KEY (`UserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`UserSetting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`UserSetting` (
  `ID` BIGINT NOT NULL,
  `UserID` BIGINT NOT NULL,
  `ParamName` VARCHAR(64) NOT NULL,
  `ParamValue` VARCHAR(64) NOT NULL,
  `Description` VARCHAR(64) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_UserSetting_User1_idx` (`UserID` ASC),
  CONSTRAINT `fk_UserSetting_User1`
    FOREIGN KEY (`UserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Course` (
  `ID` BIGINT NOT NULL COMMENT '		',
  `SeqNo` INT NOT NULL,
  `Name` VARCHAR(50) NOT NULL COMMENT '	',
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Chapter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Chapter` (
  `ID` BIGINT NOT NULL,
  `CourseID` BIGINT NOT NULL,
  `SeqNo` INT NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Chapter_Course1_idx` (`CourseID` ASC),
  CONSTRAINT `fk_Chapter_Course1`
    FOREIGN KEY (`CourseID`)
    REFERENCES `acc`.`Course` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Exercise` (
  `ID` BIGINT NOT NULL COMMENT '	',
  `ChapterID` BIGINT NOT NULL,
  `Type` INT NOT NULL COMMENT '1:SC: 单项选择题\n2:MC:多项选择题\n3:TF:判断题\n4:CA:计算题\n6:QS：问答题\n7:AN：分析题\n8:SY：综合题',
  `ProfessionalLevel` INT NOT NULL COMMENT '1:J:初级会计\n2:M:中级会计\n3:H:高级会计\n4:R:注册会计',
  `Title` VARCHAR(2000) NOT NULL COMMENT '	',
  `Answer` VARCHAR(2000) NULL COMMENT 'For choice title or questions that have only one subquestion.',
  `Year` INT(4) NULL,
  `IsSimulation` TINYINT(1) NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Exercise_Chapter1_idx` (`ChapterID` ASC),
  CONSTRAINT `fk_Exercise_Chapter1`
    FOREIGN KEY (`ChapterID`)
    REFERENCES `acc`.`Chapter` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`ExerciseQuestion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`ExerciseQuestion` (
  `ID` BIGINT NOT NULL,
  `ExerciseID` BIGINT NOT NULL,
  `SeqNo` INT NOT NULL,
  `Question` VARCHAR(2000) NOT NULL,
  `Answer` VARCHAR(2000) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ExerciseQuestion_Exercise1_idx` (`ExerciseID` ASC),
  CONSTRAINT `fk_ExerciseQuestion_Exercise1`
    FOREIGN KEY (`ExerciseID`)
    REFERENCES `acc`.`Exercise` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'For non-choice questions, there are several sub-questions. ';


-- -----------------------------------------------------
-- Table `acc`.`ExcerciseChoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`ExcerciseChoice` (
  `ID` BIGINT NOT NULL,
  `ExerciseID` BIGINT NOT NULL,
  `SeqNo` INT NOT NULL,
  `Choice` VARCHAR(500) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ExcerciseChoice_Exercise1_idx` (`ExerciseID` ASC),
  CONSTRAINT `fk_ExcerciseChoice_Exercise1`
    FOREIGN KEY (`ExerciseID`)
    REFERENCES `acc`.`Exercise` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Doubt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Doubt` (
  `ID` BIGINT NOT NULL,
  `UserID` BIGINT NOT NULL,
  `ExerciseID` BIGINT NOT NULL,
  `Question` VARCHAR(1000) NULL,
  `IsAccused` TINYINT(1) NOT NULL,
  `IsAnonymous` TINYINT(1) NOT NULL,
  `Status` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Doubt_SysUser1_idx` (`UserID` ASC),
  INDEX `fk_Doubt_Exercise1_idx` (`ExerciseID` ASC),
  CONSTRAINT `fk_Doubt_SysUser1`
    FOREIGN KEY (`UserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Doubt_Exercise1`
    FOREIGN KEY (`ExerciseID`)
    REFERENCES `acc`.`Exercise` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`DoubtAnswer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`DoubtAnswer` (
  `ID` BIGINT NOT NULL,
  `UserID` BIGINT NOT NULL,
  `DoubtID` BIGINT NOT NULL,
  `Content` VARCHAR(1000) NULL,
  `IsAnonymous` TINYINT(1) NOT NULL,
  `IsAccused` TINYINT(1) NOT NULL,
  `Status` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_DoubtAnswer_SysUser1_idx` (`UserID` ASC),
  INDEX `fk_DoubtAnswer_Doubt1_idx` (`DoubtID` ASC),
  CONSTRAINT `fk_DoubtAnswer_SysUser1`
    FOREIGN KEY (`UserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DoubtAnswer_Doubt1`
    FOREIGN KEY (`DoubtID`)
    REFERENCES `acc`.`Doubt` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`ExcerciseCollected`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`ExcerciseCollected` (
  `ID` VARCHAR(45) NOT NULL,
  `UserID` BIGINT NOT NULL,
  `ExerciseID` BIGINT NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  INDEX `fk_ExcerciseCollected_SysUser1_idx` (`UserID` ASC),
  INDEX `fk_ExcerciseCollected_Exercise1_idx` (`ExerciseID` ASC),
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_ExcerciseCollected_SysUser1`
    FOREIGN KEY (`UserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExcerciseCollected_Exercise1`
    FOREIGN KEY (`ExerciseID`)
    REFERENCES `acc`.`Exercise` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`ExerciseScore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`ExerciseScore` (
  `ID` BIGINT NOT NULL,
  `ChapterID` BIGINT NOT NULL,
  `Score` INT NOT NULL,
  `Type` INT NOT NULL COMMENT '1:SC: 单项选择题\n2:MC:多项选择题\n3:TF:判断题\n4:CA:计算题\n6:QS：问答题\n7:AN：分析题\n8:SY：综合题',
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ExerciseScore_Chapter1_idx` (`ChapterID` ASC),
  CONSTRAINT `fk_ExerciseScore_Chapter1`
    FOREIGN KEY (`ChapterID`)
    REFERENCES `acc`.`Chapter` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Question` (
  `ID` BIGINT NOT NULL,
  `UserID` BIGINT NOT NULL,
  `Title` VARCHAR(200) NOT NULL,
  `Question` TEXT NOT NULL,
  `Tag` VARCHAR(500) NULL,
  `IsAnonymous` TINYINT(1) NOT NULL,
  `ApproveCount` INT NOT NULL,
  `DisapproveCount` INT NOT NULL,
  `AnswerCount` INT NOT NULL,
  `CollectedCount` INT NOT NULL,
  `IsAccused` TINYINT(1) NOT NULL,
  `Status` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Question_SysUser1_idx` (`UserID` ASC),
  CONSTRAINT `fk_Question_SysUser1`
    FOREIGN KEY (`UserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Answer` (
  `ID` BIGINT NOT NULL,
  `QuestionID` BIGINT NOT NULL,
  `UserID` BIGINT NOT NULL,
  `Answer` TEXT NULL,
  `IsAnonymous` TINYINT(1) NOT NULL,
  `ApproveCount` INT NOT NULL,
  `DisapproveCount` INT NOT NULL,
  `PumpCount` INT NOT NULL,
  `CollectedCount` INT NOT NULL,
  `IsAccused` TINYINT(1) NOT NULL,
  `Status` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Answer_Question1_idx` (`QuestionID` ASC),
  INDEX `fk_Answer_SysUser1_idx` (`UserID` ASC),
  CONSTRAINT `fk_Answer_Question1`
    FOREIGN KEY (`QuestionID`)
    REFERENCES `acc`.`Question` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answer_SysUser1`
    FOREIGN KEY (`UserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Pump`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Pump` (
  `ID` BIGINT NOT NULL,
  `AnswerID` BIGINT NOT NULL,
  `UserID` BIGINT NOT NULL,
  `Content` VARCHAR(2000) NULL,
  `IsAnonymous` TINYINT(1) NOT NULL,
  `IsAccused` TINYINT(1) NOT NULL,
  `Status` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Pump_Answer1_idx` (`AnswerID` ASC),
  INDEX `fk_Pump_SysUser1_idx` (`UserID` ASC),
  CONSTRAINT `fk_Pump_Answer1`
    FOREIGN KEY (`AnswerID`)
    REFERENCES `acc`.`Answer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pump_SysUser1`
    FOREIGN KEY (`UserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`QuestionCollected`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`QuestionCollected` (
  `ID` BIGINT NOT NULL,
  `QuestionID` BIGINT NOT NULL,
  `SysUserID` BIGINT NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_QuestionCollected_Question1_idx` (`QuestionID` ASC),
  INDEX `fk_QuestionCollected_SysUser1_idx` (`SysUserID` ASC),
  UNIQUE INDEX `uk_CompositeID` (`SysUserID` ASC, `QuestionID` ASC),
  CONSTRAINT `fk_QuestionCollected_Question1`
    FOREIGN KEY (`QuestionID`)
    REFERENCES `acc`.`Question` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_QuestionCollected_SysUser1`
    FOREIGN KEY (`SysUserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`AnswerCollected`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`AnswerCollected` (
  `ID` BIGINT NOT NULL,
  `AnswerID` BIGINT NOT NULL,
  `SysUserID` BIGINT NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_AnswerCollected_Answer1_idx` (`AnswerID` ASC),
  INDEX `fk_AnswerCollected_SysUser1_idx` (`SysUserID` ASC),
  UNIQUE INDEX `uk_CompositeID` (`SysUserID` ASC, `AnswerID` ASC),
  CONSTRAINT `fk_AnswerCollected_Answer1`
    FOREIGN KEY (`AnswerID`)
    REFERENCES `acc`.`Answer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AnswerCollected_SysUser1`
    FOREIGN KEY (`SysUserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`AccountingStandard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`AccountingStandard` (
  `ID` BIGINT NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `Abbr` VARCHAR(20) NOT NULL,
  `Code` VARCHAR(20) NOT NULL,
  `ExeYear` INT(4) NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`FinancialReport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`FinancialReport` (
  `ID` BIGINT NOT NULL,
  `AccountingStandardID` BIGINT NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `Report` TEXT NOT NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_FinancialReport_AccountingStandard1_idx` (`AccountingStandardID` ASC),
  CONSTRAINT `fk_FinancialReport_AccountingStandard1`
    FOREIGN KEY (`AccountingStandardID`)
    REFERENCES `acc`.`AccountingStandard` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`COA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`COA` (
  `ID` BIGINT NOT NULL,
  `AccountingStandardID` BIGINT NOT NULL,
  `Code` VARCHAR(20) NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  `ElementCode` VARCHAR(20) NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_COA_AccountingStandard1_idx` (`AccountingStandardID` ASC),
  CONSTRAINT `fk_COA_AccountingStandard1`
    FOREIGN KEY (`AccountingStandardID`)
    REFERENCES `acc`.`AccountingStandard` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`AccUsage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`AccUsage` (
  `ID` BIGINT NOT NULL,
  `COAID` BIGINT NOT NULL,
  `Usages` VARCHAR(5000) NOT NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Usage_COA1_idx` (`COAID` ASC),
  CONSTRAINT `fk_Usage_COA1`
    FOREIGN KEY (`COAID`)
    REFERENCES `acc`.`COA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`GeneralPrinciple`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`GeneralPrinciple` (
  `ID` BIGINT NOT NULL,
  `AccountingStandardID` BIGINT NOT NULL,
  `Title` VARCHAR(50) NULL,
  `GeneralPrinciple` TEXT NOT NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Principle_AccountingStandard1_idx` (`AccountingStandardID` ASC),
  CONSTRAINT `fk_Principle_AccountingStandard1`
    FOREIGN KEY (`AccountingStandardID`)
    REFERENCES `acc`.`AccountingStandard` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`SpecificStandard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`SpecificStandard` (
  `ID` BIGINT NOT NULL,
  `AccountingStandardID` BIGINT NOT NULL,
  `Title` VARCHAR(50) NULL,
  `Specifics` TEXT NOT NULL,
  `Status` TINYINT(1) NOT NULL,
  `IsPreface` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_SpecificStandard_AccountingStandard1_idx` (`AccountingStandardID` ASC),
  CONSTRAINT `fk_SpecificStandard_AccountingStandard1`
    FOREIGN KEY (`AccountingStandardID`)
    REFERENCES `acc`.`AccountingStandard` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Policy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Policy` (
  `ID` BIGINT NOT NULL,
  `Type` INT NOT NULL COMMENT '1:Policy\n2:Explain\n3:Notice',
  `PublishDate` DATE NOT NULL,
  `Title` VARCHAR(200) NOT NULL,
  `Content` TEXT NOT NULL,
  `Status` TINYINT(1) NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Comment` (
  `ID` BIGINT NOT NULL,
  `PolicyID` BIGINT NOT NULL,
  `Answer` VARCHAR(2000) NULL,
  `IsAnonymous` TINYINT(1) NOT NULL,
  `ApproveCount` INT NULL,
  `DisapproveCount` INT NULL,
  `IsAccused` TINYINT(1) NOT NULL,
  `Status` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Comment_Policy1_idx` (`PolicyID` ASC),
  CONSTRAINT `fk_Comment_Policy1`
    FOREIGN KEY (`PolicyID`)
    REFERENCES `acc`.`Policy` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Rejoinder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Rejoinder` (
  `ID` BIGINT NOT NULL,
  `CommentID` BIGINT NOT NULL,
  `Content` VARCHAR(2000) NULL,
  `IsAnonymous` TINYINT(1) NOT NULL,
  `IsAccused` TINYINT(1) NOT NULL,
  `Status` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Rejoinder_Comment1_idx` (`CommentID` ASC),
  CONSTRAINT `fk_Rejoinder_Comment1`
    FOREIGN KEY (`CommentID`)
    REFERENCES `acc`.`Comment` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`PolicyCollected`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`PolicyCollected` (
  `ID` BIGINT NOT NULL,
  `PolicyID` BIGINT NOT NULL,
  `SysUserID` BIGINT NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PolicyCollected_Policy1_idx` (`PolicyID` ASC),
  INDEX `fk_PolicyCollected_SysUser1_idx` (`SysUserID` ASC),
  CONSTRAINT `fk_PolicyCollected_Policy1`
    FOREIGN KEY (`PolicyID`)
    REFERENCES `acc`.`Policy` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PolicyCollected_SysUser1`
    FOREIGN KEY (`SysUserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`Message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`Message` (
  `ID` BIGINT NOT NULL,
  `Type` INT NOT NULL COMMENT '1:system\n2:personal',
  `Content` VARCHAR(2000) NOT NULL,
  `Receiver` BIGINT NOT NULL,
  `Sender` BIGINT NOT NULL,
  `HasRead` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Message_SysUser1_idx` (`Receiver` ASC),
  INDEX `fk_Message_SysUser2_idx` (`Sender` ASC),
  CONSTRAINT `fk_Message_SysUser1`
    FOREIGN KEY (`Receiver`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Message_SysUser2`
    FOREIGN KEY (`Sender`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`AccElement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`AccElement` (
  `ID` BIGINT NOT NULL,
  `AccountingStandardID` BIGINT NOT NULL,
  `Name` VARCHAR(20) NULL,
  `Code` VARCHAR(20) NOT NULL,
  `Status` TINYINT(1) NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_AccElement_AccountingStandard1_idx` (`AccountingStandardID` ASC),
  CONSTRAINT `fk_AccElement_AccountingStandard1`
    FOREIGN KEY (`AccountingStandardID`)
    REFERENCES `acc`.`AccountingStandard` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`QuestionApproved`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`QuestionApproved` (
  `ID` BIGINT NOT NULL,
  `SysUserID` BIGINT NOT NULL,
  `QuestionID` BIGINT NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_QuestionApproved_Question1_idx` (`QuestionID` ASC),
  INDEX `fk_QuestionApproved_SysUser1_idx` (`SysUserID` ASC),
  UNIQUE INDEX `uk_CompositeID` (`SysUserID` ASC, `QuestionID` ASC),
  CONSTRAINT `fk_QuestionApproved_Question`
    FOREIGN KEY (`QuestionID`)
    REFERENCES `acc`.`Question` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_QuestionApproved_SysUser`
    FOREIGN KEY (`SysUserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`QuestionDisapproved`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`QuestionDisapproved` (
  `ID` BIGINT NOT NULL,
  `SysUserID` BIGINT NOT NULL,
  `QuestionID` BIGINT NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_QuestionApproved_Question1_idx` (`QuestionID` ASC),
  INDEX `fk_QuestionApproved_SysUser1_idx` (`SysUserID` ASC),
  UNIQUE INDEX `uk_CompositeID` (`SysUserID` ASC, `QuestionID` ASC),
  CONSTRAINT `fk_QuestionDispproved_Question`
    FOREIGN KEY (`QuestionID`)
    REFERENCES `acc`.`Question` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_QuestionDisapproved_SysUser`
    FOREIGN KEY (`SysUserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`AnswerApproved`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`AnswerApproved` (
  `ID` BIGINT NOT NULL,
  `SysUserID` BIGINT NOT NULL,
  `AnswerID` BIGINT NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  INDEX `fk_AnswerApproved_Answer1_idx` (`AnswerID` ASC),
  INDEX `fk_AnswerApproved_SysUser1_idx` (`SysUserID` ASC),
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `uk_CompositeID` (`SysUserID` ASC, `AnswerID` ASC),
  CONSTRAINT `fk_AnswerApproved_Answer`
    FOREIGN KEY (`AnswerID`)
    REFERENCES `acc`.`Answer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AnswerApproved_SysUser`
    FOREIGN KEY (`SysUserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acc`.`AnswerDispproved`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `acc`.`AnswerDisapproved` (
  `ID` BIGINT NOT NULL,
  `SysUserID` BIGINT NOT NULL,
  `AnswerID` BIGINT NOT NULL,
  `CreateTime` TIMESTAMP NULL,
  `Creator` BIGINT NULL,
  `ModifyTime` TIMESTAMP NULL,
  `Modifier` BIGINT NULL,
  INDEX `fk_AnswerApproved_Answer1_idx` (`AnswerID` ASC),
  INDEX `fk_AnswerApproved_SysUser1_idx` (`SysUserID` ASC),
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `uk_CompositeID` (`SysUserID` ASC, `AnswerID` ASC),
  CONSTRAINT `fk_AnswerDisapproved_Answer`
    FOREIGN KEY (`AnswerID`)
    REFERENCES `acc`.`Answer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AnswerDisapproved_SysUser`
    FOREIGN KEY (`SysUserID`)
    REFERENCES `acc`.`SysUser` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
