INSERT INTO categorias(id, cor, titulo) VALUES (1, '#000000', 'LIVRE');
INSERT INTO categorias(id, cor, titulo) VALUES (2, '#FFFFFFF', 'PROGRAMAÇÃO');
INSERT INTO categorias(id, cor, titulo) VALUES (3, '#CCCCCC', 'COMIDA');

INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (1, 'Domain Driven Design, o que ? Por que foi criada e qual objetivo dessa linguagem dentro da programao?', 'Domain Driven Design com Alberto Sousa, o Dev Eficiente', 'https://www.youtube.com/watch?v=n40Z1c9Ryog', 1);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (2, 'Do básico ao avançado com Mysql', 'Mysql', 'https://www.meusite.com.br/mysql', 1);
INSERT INTO videos(id, descricao, titulo, url, categoria_id) VALUES (3, 'Primeiros passos com docker', 'Docker', 'https://www.meusite.com.br/docker', 1);
