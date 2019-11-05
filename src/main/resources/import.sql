-- Cozinha
insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

-- Estado
insert into estado ( id, nome, sigla) values (1, 'Acre', 'AC');
insert into estado ( id, nome, sigla) values (2, 'Alagoas', 'AL');
insert into estado ( id, nome, sigla) values (3, 'Amapá', 'AP');
insert into estado ( id, nome, sigla) values (4, 'Amazonas', 'AM');
insert into estado ( id, nome, sigla) values (5, 'Bahia', 'BA');
insert into estado ( id, nome, sigla) values (6, 'Ceará', 'CE');
insert into estado ( id, nome, sigla) values (7, 'Distrito Federal', 'DF');
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
insert into cidade (id, nome, estado_id) values (1,'Alta Floresta D''Oeste', 22);
insert into cidade (id, nome, estado_id) values (2,'Ariquemes', 22);
insert into cidade (id, nome, estado_id) values (3,'Cabixi', 22);
insert into cidade (id, nome, estado_id) values (4,'Cacoal', 22);
insert into cidade (id, nome, estado_id) values (5,'Cerejeiras', 22);
insert into cidade (id, nome, estado_id) values (6,'Colorado do Oeste', 22);
insert into cidade (id, nome, estado_id) values (7,'Corumbiara', 22);
insert into cidade (id, nome, estado_id) values (8,'Costa Marques', 22);
insert into cidade (id, nome, estado_id) values (9,'Espigão D''Oeste', 22);
insert into cidade (id, nome, estado_id) values (10,'Guajará-Mirim', 22);
insert into cidade (id, nome, estado_id) values (11,'Santa Bárbara do Tugúrio', 13);
insert into cidade (id, nome, estado_id) values (12,'Santa Cruz de Minas', 13);
insert into cidade (id, nome, estado_id) values (13,'Santa Cruz de Salinas', 13);
insert into cidade (id, nome, estado_id) values (14,'Santa Cruz do Escalvado', 13);
insert into cidade (id, nome, estado_id) values (15,'Santa Efigênia de Minas', 13);
insert into cidade (id, nome, estado_id) values (16,'Santa Fé de Minas', 13);
insert into cidade (id, nome, estado_id) values (17,'Santa Helena de Minas', 13);
insert into cidade (id, nome, estado_id) values (18,'Santa Juliana', 13);
insert into cidade (id, nome, estado_id) values (19,'Santa Luzia', 13);
insert into cidade (id, nome, estado_id) values (20,'Santa Margarida', 13);
insert into cidade (id, nome, estado_id) values (21,'Santa Maria de Itabira', 13);
insert into cidade (id, nome, estado_id) values (22,'Santa Maria do Salto', 13);
insert into cidade (id, nome, estado_id) values (23,'Santa Maria do Suaçuí', 13);
insert into cidade (id, nome, estado_id) values (24,'Santana da Vargem', 13);
insert into cidade (id, nome, estado_id) values (25,'Santana de Cataguases', 13);

-- Restaurante
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (3, 'Tuk Tuk Comida Indiana', 15, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');

-- FormaPagamento
insert into forma_pagamento (id, descricao) values (1, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'Cartao');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

-- Permissao
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

-- restaurante_forma_pagamento
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
