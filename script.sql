 
CREATE TABLE dados_grandes (
	id INT NOT NULL AUTO_INCREMENT,
	coluna1 VARCHAR(100),
	coluna2 VARCHAR(100),
	coluna3 VARCHAR(100),
	data_criacao DATETIME NULL DEFAULT NULL,
	data_alteracao DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
 	 INDEX `data_criacao` (`data_criacao` ASC)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DELIMITER $$
DROP PROCEDURE prepare_data;
CREATE PROCEDURE prepare_data()
BEGIN
	DECLARE i INT DEFAULT 10;	
	WHILE i < 10000 DO
		INSERT INTO dados_grandes (coluna1, coluna2, coluna3, data_criacao, data_alteracao) 
						   VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam cursus turpis vitae tempus.',
						   		   'Sed in eros et elit ullamcorper tincidunt. ',
						   		   'Cras vulputate aliquet convallis. Mauris at bibendum nibh. Nullam ornare at magna a vulputate.',
						   		   now(),
						   		   now());
						   		   IF i % 100 THEN
						   		   COMMIT;
						   		   END IF;
		SET i = i +1;
	END WHILE;
END $$
DELIMITER ;

CALL prepare_data();