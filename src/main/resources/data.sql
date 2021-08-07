INSERT INTO usuarios(nome, email, senha) VALUE ('Admin','admin@email.com', '$2a$10$jy2AoIfsLJKNocPgky.IKOYldkyky2t5x/6zhUYHEt3V1ywA34NIK');

INSERT INTO categorias(cor, titulo) VALUES ('#000000', 'LIVRE');
INSERT INTO categorias(cor, titulo) VALUES ('#FFFFFFF', 'PROGRAMAÇÃO');
INSERT INTO categorias(cor, titulo) VALUES ('#CCCCCC', 'COMIDA');

INSERT INTO videos(descricao, titulo, url, categoria_id) VALUES ('Domain Driven Design, o que ? Por que foi criada e qual objetivo dessa linguagem dentro da programao?', 'Domain Driven Design com Alberto Sousa, o Dev Eficiente', 'https://www.youtube.com/watch?v=n40Z1c9Ryog', 1);
INSERT INTO videos(descricao, titulo, url, categoria_id) VALUES ('Do básico ao avançado com Mysql', 'Mysql', 'https://www.meusite.com.br/mysql', 1);
INSERT INTO videos(descricao, titulo, url, categoria_id) VALUES ('Primeiros passos com docker', 'Docker', 'https://www.meusite.com.br/docker', 1);
