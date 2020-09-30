
-- exclui as sequ�ncias
DROP SEQUENCE SQ_T_XCAVE_LOGIN_RH;
DROP SEQUENCE SQ_T_XCAVE_DEPARTAMENTO;
DROP SEQUENCE SQ_T_XCAVE_MATRICULA;
DROP SEQUENCE SQ_T_XCAVE_DEPENDENTE;
DROP SEQUENCE SQ_T_XCAVE_DOCUMENTO;

-- limpa os registros das tabelas
TRUNCATE TABLE T_XCAVE_DOCUMENTO;
TRUNCATE TABLE T_XCAVE_CONTA_BANCO;
TRUNCATE TABLE T_XCAVE_DADOS;
TRUNCATE TABLE T_XCAVE_DEPENDENTE;
TRUNCATE TABLE T_XCAVE_COLABORADOR;
TRUNCATE TABLE T_XCAVE_LOGIN_RH;
TRUNCATE TABLE T_XCAVE_DEPARTAMENTO;

-- cria sequ�ncia para o c�digo do login do RH
CREATE SEQUENCE SQ_T_XCAVE_LOGIN_RH MAXVALUE 99999 NOCACHE;

-- cria sequ�ncia para o c�digo do departamento
CREATE SEQUENCE SQ_T_XCAVE_DEPARTAMENTO MAXVALUE 9999 NOCACHE;

-- cria sequ�ncia para a matr�cula do colaborador
CREATE SEQUENCE SQ_T_XCAVE_MATRICULA MAXVALUE 999999 MINVALUE 1000 NOCACHE;

-- cria sequ�ncia para o c�digo do documento
CREATE SEQUENCE SQ_T_XCAVE_DOCUMENTO MAXVALUE 999999 NOCACHE;

-- cria sequ�ncia para o c�digo do dependente
CREATE SEQUENCE SQ_T_XCAVE_DEPENDENTE MAXVALUE 999999 NOCACHE;

-- insere login do funcion�rio do RH
INSERT INTO T_XCAVE_LOGIN_RH (CD_LOGIN_RH,NM_USUARIO,NM_SENHA) VALUES (SQ_T_XCAVE_LOGIN_RH.NEXTVAL,'usuario','fiap123');

-- insere departamento
INSERT INTO T_XCAVE_DEPARTAMENTO (CD_DEPARTAMENTO,NM_DEPARTAMENTO) VALUES (SQ_T_XCAVE_DEPARTAMENTO.NEXTVAL,'ADMINISTRATIVO');
INSERT INTO T_XCAVE_DEPARTAMENTO (CD_DEPARTAMENTO,NM_DEPARTAMENTO) VALUES (SQ_T_XCAVE_DEPARTAMENTO.NEXTVAL,'FINANCEIRO');
INSERT INTO T_XCAVE_DEPARTAMENTO (CD_DEPARTAMENTO,NM_DEPARTAMENTO) VALUES (SQ_T_XCAVE_DEPARTAMENTO.NEXTVAL,'COMERCIAL');
INSERT INTO T_XCAVE_DEPARTAMENTO (CD_DEPARTAMENTO,NM_DEPARTAMENTO) VALUES (SQ_T_XCAVE_DEPARTAMENTO.NEXTVAL,'TECNOLOGIA');

-- commit das informa��es
COMMIT;