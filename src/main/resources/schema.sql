DROP TABLE IF EXISTS videos;
DROP TABLE IF EXISTS `categorias`;

CREATE TABLE `categorias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cor` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `videos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `titulo` varchar(150) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `categoria_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_categoria_id` (`categoria_id`),
  CONSTRAINT `fk_categoria_id` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
);
