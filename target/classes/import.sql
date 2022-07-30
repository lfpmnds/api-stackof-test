INSERT INTO tb_user (username, password) VALUES ('maria_dasilva', '$2a$10$DSUGLyJ6j3ps7hRYKH9YduP1IPrBgqeVugz.aXvVO3/ZKjuylG33a');
INSERT INTO tb_user (username, password) VALUES ('joao@email.com', '$2a$10$DSUGLyJ6j3ps7hRYKH9YduP1IPrBgqeVugz.aXvVO3/ZKjuylG33a');
INSERT INTO tb_user (username, password) VALUES ('carlosJose2', '$2a$10$DSUGLyJ6j3ps7hRYKH9YduP1IPrBgqeVugz.aXvVO3/ZKjuylG33a');

INSERT INTO tb_question (body, author_id) VALUES ('Não estou conseguindo conectar o banco H2 ao meu projeto. Alguém poderia me ajudar?', 3);
INSERT INTO tb_question (body, author_id) VALUES ('Como remover a senha do Spring Security que é gerada assim que rodo a minha aplicação?', 1);
INSERT INTO tb_question (body, author_id) VALUES ('Como gerar um script simples para povoar meu banco de dados SQL?', 2);
INSERT INTO tb_question (body, author_id) VALUES ('Como dar um findBy usando chave estrangeira?', 2);

INSERT INTO tb_answer (body, author_id, question_id) VALUES ('Você pode tentar verificar o arquivo application.properties', 2, 1);
INSERT INTO tb_answer (body, author_id, question_id) VALUES ('Olhe a documentação do Spring Security', 3, 2);
INSERT INTO tb_answer (body, author_id, question_id) VALUES ('Crie um arquivo import.sql na pasta resources com os dados que gostaria de inserir', 3, 3);
INSERT INTO tb_answer (body, author_id, question_id) VALUES ('Especifique se a dúvida é sobre como escrever o script ou se há algum lugar que faça isso para você', 1, 3);
INSERT INTO tb_answer (body, author_id, question_id) VALUES ('Dê uma olhada na annotation @Query do Spring JPA, acho que pode ajudar...', 1, 4);
