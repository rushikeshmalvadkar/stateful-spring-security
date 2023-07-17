
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(45) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

-- For NoOpsPasswordEncoder
INSERT INTO `security-db`.`users` (`username`, `password`, `role`, `enabled`) VALUES ('abc', 'abc123', 'CUSTOMER', '1');
INSERT INTO `security-db`.`users` (`username`, `password`, `role`, `enabled`) VALUES ('def', 'def123', 'ADMIN', '1');

-- For BCryptPasswordEncoder
UPDATE `security-db`.`users` SET `password` = '$2a$10$6VMYx4n3Abllu1r4ojC5juIgLigDCMBILSKt6fVHS7SBdH5nuDU7a' WHERE (`user_id` = '1');
UPDATE `security-db`.`users` SET `password` = '$2a$10$LUyv6KFaqHDX7GIGx764nOqDJWgWPocN8iuxuI1MxRtlbek5KmbN.' WHERE (`user_id` = '2');
