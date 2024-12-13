-- Inserir países (evitar duplicação)
INSERT INTO Pais (nome)
SELECT 'Brasil' WHERE NOT EXISTS (SELECT 1 FROM Pais WHERE nome = 'Brasil');

INSERT INTO Pais (nome)
SELECT 'Estados Unidos' WHERE NOT EXISTS (SELECT 1 FROM Pais WHERE nome = 'Estados Unidos');

INSERT INTO Pais (nome)
SELECT 'Canadá' WHERE NOT EXISTS (SELECT 1 FROM Pais WHERE nome = 'Canadá');

INSERT INTO Pais (nome)
SELECT 'Alemanha' WHERE NOT EXISTS (SELECT 1 FROM Pais WHERE nome = 'Alemanha');

INSERT INTO Pais (nome)
SELECT 'Japão' WHERE NOT EXISTS (SELECT 1 FROM Pais WHERE nome = 'Japão');

-- Inserir cidades (evitar duplicação)
INSERT INTO Cidade (nome, pais_id)
SELECT 'São Paulo', 1 WHERE NOT EXISTS (SELECT 1 FROM Cidade WHERE nome = 'São Paulo' AND pais_id = 1);

INSERT INTO Cidade (nome, pais_id)
SELECT 'Rio de Janeiro', 1 WHERE NOT EXISTS (SELECT 1 FROM Cidade WHERE nome = 'Rio de Janeiro' AND pais_id = 1);

INSERT INTO Cidade (nome, pais_id)
SELECT 'Manaus', 1 WHERE NOT EXISTS (SELECT 1 FROM Cidade WHERE nome = 'Manaus' AND pais_id = 1);

INSERT INTO Cidade (nome, pais_id)
SELECT 'Nova York', 2 WHERE NOT EXISTS (SELECT 1 FROM Cidade WHERE nome = 'Nova York' AND pais_id = 2);

INSERT INTO Cidade (nome, pais_id)
SELECT 'Toronto', 3 WHERE NOT EXISTS (SELECT 1 FROM Cidade WHERE nome = 'Toronto' AND pais_id = 3);

INSERT INTO Cidade (nome, pais_id)
SELECT 'Berlim', 4 WHERE NOT EXISTS (SELECT 1 FROM Cidade WHERE nome = 'Berlim' AND pais_id = 4);

INSERT INTO Cidade (nome, pais_id)
SELECT 'Tóquio', 5 WHERE NOT EXISTS (SELECT 1 FROM Cidade WHERE nome = 'Tóquio' AND pais_id = 5);

-- Inserir situações de voluntário (evitar duplicação)
INSERT INTO situacao_voluntario (situacao)
SELECT 'RUIM' WHERE NOT EXISTS (SELECT 1 FROM situacao_voluntario WHERE situacao = 'RUIM');

INSERT INTO situacao_voluntario (situacao)
SELECT 'BOM' WHERE NOT EXISTS (SELECT 1 FROM situacao_voluntario WHERE situacao = 'BOM');

INSERT INTO situacao_voluntario (situacao)
SELECT 'ÓTIMO' WHERE NOT EXISTS (SELECT 1 FROM situacao_voluntario WHERE situacao = 'ÓTIMO');