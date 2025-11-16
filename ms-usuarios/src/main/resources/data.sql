-- Insere os departamentos básicos (R19)
INSERT INTO departamentos (codigo, nome, descricao) VALUES ('TI', 'Tecnologia da Informação', 'Departamento de TI') ON CONFLICT (codigo) DO NOTHING;
INSERT INTO departamentos (codigo, nome, descricao) VALUES ('RH', 'Recursos Humanos', 'Departamento de RH') ON CONFLICT (codigo) DO NOTHING;
INSERT INTO departamentos (codigo, nome, descricao) VALUES ('VEN', 'Vendas', 'Departamento de Vendas') ON CONFLICT (codigo) DO NOTHING;
INSERT INTO departamentos (codigo, nome, descricao) VALUES ('MKT', 'Marketing', 'Departamento de Marketing') ON CONFLICT (codigo) DO NOTHING;
INSERT INTO departamentos (codigo, nome, descricao) VALUES ('INST', 'Instrução', 'Departamento de Instrutores') ON CONFLICT (codigo) DO NOTHING;