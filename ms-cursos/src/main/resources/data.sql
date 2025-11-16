-- Insere as categorias básicas (R12)
INSERT INTO categorias (codigo, nome, descricao, cor_hex) VALUES ('TECH', 'Tecnologia', 'Cursos de tecnologia', '#0000FF') ON CONFLICT (codigo) DO NOTHING;
INSERT INTO categorias (codigo, nome, descricao, cor_hex) VALUES ('LIDER', 'Liderança', 'Cursos de liderança', '#FFFF00') ON CONFLICT (codigo) DO NOTHING;
INSERT INTO categorias (codigo, nome, descricao, cor_hex) VALUES ('VENDAS', 'Técnicas de Vendas', 'Cursos de vendas', '#00FF00') ON CONFLICT (codigo) DO NOTHING;
INSERT INTO categorias (codigo, nome, descricao, cor_hex) VALUES ('COMP', 'Comportamental', 'Cursos comportamentais', '#FF0000') ON CONFLICT (codigo) DO NOTHING;