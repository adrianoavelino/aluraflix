DELETE FROM videos WHERE id > 0;
DELETE FROM categorias WHERE id > 0;
DELETE FROM usuarios WHERE id > 0;

INSERT INTO usuarios(nome, email, senha) VALUES ('Admin','admin@email.com', '$2a$10$jy2AoIfsLJKNocPgky.IKOYldkyky2t5x/6zhUYHEt3V1ywA34NIK');

INSERT INTO categorias(id, cor, titulo) VALUES (1, '#000000', 'LIVRE');
INSERT INTO categorias(id, cor, titulo) VALUES (2, '#FFFFFFF', 'PROGRAMAÇÃO');
INSERT INTO categorias(id, cor, titulo) VALUES (3, '#CCCCCC', 'COMIDA');

INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (1, 'Descricao 1', 'Título 1', 'http://www.site.com.br/abc1', 1);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (2, 'Descricao 2', 'Título 2', 'http://www.site.com.br/abc2', 2);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (3, 'Descricao 3', 'Título 3', 'http://www.site.com.br/abc3', 1);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (4, 'Descricao 4', 'Título 4', 'http://www.site.com.br/abc4', 3);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (5, 'Descricao 5', 'Título 5', 'http://www.site.com.br/abc5', 1);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (6, 'Descricao 6', 'Título 6', 'http://www.site.com.br/abc6', 1);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (7, 'Descricao 7', 'Título 7', 'http://www.site.com.br/abc7', 2);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (8, 'Descricao 8', 'Título 8', 'http://www.site.com.br/abc8', 1);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (9, 'Descricao 9', 'Título 9', 'http://www.site.com.br/abc9', 3);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (10, 'Descricao 10', 'Título 10', 'http://www.site.com.br/abc10', 1);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (11, 'Descricao 11', 'Título 11', 'http://www.site.com.br/abc11', 1);
