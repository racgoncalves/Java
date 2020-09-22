
-- cria sequ�ncia para a matr�cula do colaborador
CREATE SEQUENCE SQ_T_XCAVE_MATRICULA INCREMENT BY 1 START WITH 1000 NOCACHE;

-- insere login do funcion�rio do RH
INSERT INTO T_XCAVE_LOGIN_RH (CD_LOGIN_RH,NM_USUARIO,NM_SENHA) VALUES (1234,'usuario','fiap123');
COMMIT;

-- limpa os registros das tabelas
TRUNCATE TABLE T_XCAVE_DOCUMENTOS;
TRUNCATE TABLE T_XCAVE_CONTA_BANCO;
TRUNCATE TABLE T_XCAVE_DADOS;
TRUNCATE TABLE T_XCAVE_DEPENDENTE;
TRUNCATE TABLE T_XCAVE_COLABORADOR;
TRUNCATE TABLE T_XCAVE_LOGIN_RH;

-- retorna a situa��o do cadastro do colaborador para 0
UPDATE T_XCAVE_COLABORADOR SET ST_CADASTRO = 0 WHERE CD_MATRICULA = 1000;
COMMIT;