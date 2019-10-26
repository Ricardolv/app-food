-- Cozinha
insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

-- Restaurante
insert into restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_codigo) values ('Thai Gourmet', 10, false, false, null, null, 1);
insert into restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_codigo) values ('Thai Delivery', 9.50, false, false, null, null, 1);
insert into restaurante (nome, taxa_frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_codigo) values ('Tuk Tuk Comida Indiana', 15, false, false, null, null, 2);

-- FormaPagamento
insert into forma_pagamento (id, descricao) values (1, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'Cartao');

-- Permissao
insert into permissao (id, nome, descricao) values (1, 'Persistencia', 'Cozinha');
insert into permissao (id, nome, descricao) values (2, 'Persistencia', 'Restaurante');

--
insert into  ( id, nome, sigla) values (1, 'Acre', 'AC');
insert into  ( id, nome, sigla) values (2, 'Alagoas', 'AL');
insert into  ( id, nome, sigla) values (3, 'Amapá', 'AP');
insert into  ( id, nome, sigla) values (4, 'Amazonas', 'AM');
insert into  ( id, nome, sigla) values (5, 'Bahia', 'BA');
insert into  ( id, nome, sigla) values (6, 'Ceará', 'CE');
insert into  ( id, nome, sigla) values (7, 'Distrito Federal', 'DF');
insert into estado ( id, nome, sigla) values (8, 'Espírito Santo', 'ES');
insert into estado ( id, nome, sigla) values (9, 'Goiás', 'GO');
insert into estado ( id, nome, sigla) values (10, 'Maranhão', 'MA');
insert into estado ( id, nome, sigla) values (11, 'Mato Grosso', 'MT');
insert into estado ( id, nome, sigla) values (12, 'Mato Grosso do Sul', 'MS');
insert into estado ( id, nome, sigla) values (13, 'Minas Gerais', 'MG');
insert into estado ( id, nome, sigla) values (14, 'Pará', 'PA');
insert into estado ( id, nome, sigla) values (15, 'Paraíba', 'PB');
insert into estado ( id, nome, sigla) values (16, 'Paraná', 'PR');
insert into estado ( id, nome, sigla) values (17, 'Pernambuco', 'PE');
insert into estado ( id, nome, sigla) values (18, 'Piauí', 'PI');
insert into estado ( id, nome, sigla) values (19, 'Rio de Janeiro', 'RJ');
insert into estado ( id, nome, sigla) values (20, 'Rio Grande do Norte', 'RN');
insert into estado ( id, nome, sigla) values (21, 'Rio Grande do Sul', 'RS');
insert into estado ( id, nome, sigla) values (22, 'Rondônia', 'RO');
insert into estado ( id, nome, sigla) values (23, 'Roraima', 'RR');
insert into estado ( id, nome, sigla) values (24, 'Santa Catarina', 'SC');
insert into estado ( id, nome, sigla) values (25, 'São Paulo', 'SP');
insert into estado ( id, nome, sigla) values (26, 'Sergipe', 'SE');
insert into estado ( id, nome, sigla) values (27, 'Tocantins', 'TO');

-- Cidade
insert into cidade (id, nome, estado_codigo) values (1,'Alta Floresta D''Oeste', 22);
insert into cidade (id, nome, estado_codigo) values (2,'Ariquemes', 22);
insert into cidade (id, nome, estado_codigo) values (3,'Cabixi', 22);
insert into cidade (id, nome, estado_codigo) values (4,'Cacoal', 22);
insert into cidade (id, nome, estado_codigo) values (5,'Cerejeiras', 22);
insert into cidade (id, nome, estado_codigo) values (6,'Colorado do Oeste', 22);
insert into cidade (id, nome, estado_codigo) values (7,'Corumbiara', 22);
insert into cidade (id, nome, estado_codigo) values (8,'Costa Marques', 22);
insert into cidade (id, nome, estado_codigo) values (9,'Espigão D''Oeste', 22);
insert into cidade (id, nome, estado_codigo) values (10,'Guajará-Mirim', 22);
insert into cidade (id, nome, estado_codigo) values (11,'Santa Bárbara do Tugúrio', 13);
insert into cidade (id, nome, estado_codigo) values (12,'Santa Cruz de Minas', 13);
insert into cidade (id, nome, estado_codigo) values (13,'Santa Cruz de Salinas', 13);
insert into cidade (id, nome, estado_codigo) values (14,'Santa Cruz do Escalvado', 13);
insert into cidade (id, nome, estado_codigo) values (15,'Santa Efigênia de Minas', 13);
insert into cidade (id, nome, estado_codigo) values (16,'Santa Fé de Minas', 13);
insert into cidade (id, nome, estado_codigo) values (17,'Santa Helena de Minas', 13);
insert into cidade (id, nome, estado_codigo) values (18,'Santa Juliana', 13);
insert into cidade (id, nome, estado_codigo) values (19,'Santa Luzia', 13);
insert into cidade (id, nome, estado_codigo) values (20,'Santa Margarida', 13);
insert into cidade (id, nome, estado_codigo) values (21,'Santa Maria de Itabira', 13);
insert into cidade (id, nome, estado_codigo) values (22,'Santa Maria do Salto', 13);
insert into cidade (id, nome, estado_codigo) values (23,'Santa Maria do Suaçuí', 13);
insert into cidade (id, nome, estado_codigo) values (24,'Santana da Vargem', 13);
insert into cidade (id, nome, estado_codigo) values (25,'Santana de Cataguases', 13);
