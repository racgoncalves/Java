DROP TABLE t_xcave_colaborador CASCADE CONSTRAINTS;

DROP TABLE t_xcave_dependente CASCADE CONSTRAINTS;

DROP TABLE t_xcave_documento CASCADE CONSTRAINTS;

DROP TABLE t_xcave_login CASCADE CONSTRAINTS;

CREATE TABLE t_xcave_colaborador (
    cd_matricula       NUMBER(6) NOT NULL,
    nm_colaborador     VARCHAR2(60) NOT NULL,
    nr_cpf             CHAR(11) NOT NULL,
    nr_pis             CHAR(11) NOT NULL,
    ds_sexo            VARCHAR2(15) NOT NULL,
    nm_nacionalidade   VARCHAR2(60) NOT NULL,
    nm_naturalidade    VARCHAR2(60) NOT NULL,
    dt_nascimento      DATE NOT NULL,
    ds_est_civil       VARCHAR2(15) NOT NULL,
    qt_filhos          NUMBER(2) NOT NULL,
    nm_etnia           VARCHAR2(15) NOT NULL,
    tp_camiseta        VARCHAR2(2) NOT NULL,
    nr_agencia         CHAR(4) NOT NULL,
    nr_digito_agencia  CHAR(1) NOT NULL,
    nr_conta           VARCHAR2(7) NOT NULL,
    nr_digito_conta    VARCHAR2(1) NOT NULL,
    nm_orient_sexual   VARCHAR2(60),
    nm_religiao        VARCHAR2(60)
);

ALTER TABLE t_xcave_colaborador
    ADD CONSTRAINT ck_xcave_dados_camiseta CHECK ( tp_camiseta IN (
        'P',
        'PP',
        'M',
        'G',
        'GG'
    ) );

ALTER TABLE t_xcave_colaborador
    ADD CONSTRAINT ck_xcave_dados_est_civil CHECK ( ds_est_civil IN (
        'SOLTEIRO',
        'CASADO',
        'DIVORCIADO',
        'VIÚVO'
    ) );

ALTER TABLE t_xcave_colaborador
    ADD CONSTRAINT ck_xcave_dados_etnia CHECK ( nm_etnia IN (
        'BRANCO',
        'NEGRO',
        'PARDO',
        'INDÍGENA',
        'NÃO INFORMADO'
    ) );

ALTER TABLE t_xcave_colaborador
    ADD CONSTRAINT ck_xcave_dados_sexo CHECK ( ds_sexo IN (
        'FEMININO',
        'MASCULINO'
    ) );

ALTER TABLE t_xcave_colaborador ADD CONSTRAINT pk_xcave_colaborador PRIMARY KEY ( cd_matricula );

ALTER TABLE t_xcave_colaborador ADD CONSTRAINT un_xcave_colab_cpf UNIQUE ( nr_cpf );

ALTER TABLE t_xcave_colaborador ADD CONSTRAINT un_xcave_colab_pis UNIQUE ( nr_pis );

CREATE TABLE t_xcave_dependente (
    cd_matricula   NUMBER(6) NOT NULL,
    cd_dependente  NUMBER(6) NOT NULL,
    tp_dependente  VARCHAR2(15) NOT NULL,
    nm_dependente  VARCHAR2(60) NOT NULL,
    nr_cpf         CHAR(11) NOT NULL,
    ds_sexo        VARCHAR2(15) NOT NULL,
    dt_nascimento  DATE NOT NULL,
    nm_documento   VARCHAR2(60) NOT NULL,
    nm_arquivo     VARCHAR2(60) NOT NULL,
    bt_arquivo     BLOB NOT NULL
);

ALTER TABLE t_xcave_dependente
    ADD CONSTRAINT ck_xcave_dep_doc CHECK ( nm_documento IN (
        'CERTIDÃO DE CASAMENTO',
        'CERTIDÃO DE NASCIMENTO',
        'RG'
    ) );

ALTER TABLE t_xcave_dependente
    ADD CONSTRAINT ck_xcave_dep_sexo CHECK ( ds_sexo IN (
        'FEMININO',
        'MASCULINO'
    ) );

ALTER TABLE t_xcave_dependente
    ADD CONSTRAINT ck_xcave_dep_tipo CHECK ( tp_dependente IN (
        'CÔNJUGE',
        'FILHO',
        'GENITOR'
    ) );

ALTER TABLE t_xcave_dependente ADD CONSTRAINT pk_xcave_dependente PRIMARY KEY ( cd_dependente,
                                                                                cd_matricula );

ALTER TABLE t_xcave_dependente ADD CONSTRAINT un_xcave_dep_cpf UNIQUE ( nr_cpf );

CREATE TABLE t_xcave_documento (
    cd_matricula  NUMBER(6) NOT NULL,
    cd_documento  NUMBER(6) NOT NULL,
    nm_documento  VARCHAR2(60) NOT NULL,
    nm_arquivo    VARCHAR2(60) NOT NULL,
    bt_arquivo    BLOB NOT NULL
);

ALTER TABLE t_xcave_documento
    ADD CONSTRAINT ck_xcave_doc_nome CHECK ( nm_documento IN (
        'RG',
        'CARTEIRA DE TRABALHO',
        'COMPROVANTE DE RESIDÊNCIA',
        'TÍTULO DE ELEITOR',
        'COMPROVANTE DE ESCOLARIDADE',
        'CERTIFICADO DE RESERVISTA'
    ) );

ALTER TABLE t_xcave_documento ADD CONSTRAINT pk_xcave_documento PRIMARY KEY ( cd_documento,
                                                                              cd_matricula );

CREATE TABLE t_xcave_login (
    cd_matricula  NUMBER(6) NOT NULL,
    nm_email      VARCHAR2(60) NOT NULL,
    nm_senha      VARCHAR2(60) NOT NULL,
    nm_apelido    VARCHAR2(60) NOT NULL,
    st_cadastro   NUMBER(1) NOT NULL
);

ALTER TABLE t_xcave_login
    ADD CONSTRAINT ck_xcave_colab_cadastro CHECK ( st_cadastro IN (
        0,
        1
    ) );

ALTER TABLE t_xcave_login ADD CONSTRAINT pk_xcave_login PRIMARY KEY ( cd_matricula );

ALTER TABLE t_xcave_login ADD CONSTRAINT un_xcave_login_email UNIQUE ( nm_email );

ALTER TABLE t_xcave_colaborador
    ADD CONSTRAINT fk_xcave_colab_login FOREIGN KEY ( cd_matricula )
        REFERENCES t_xcave_login ( cd_matricula )
            ON DELETE CASCADE;

ALTER TABLE t_xcave_dependente
    ADD CONSTRAINT fk_xcave_dep_login FOREIGN KEY ( cd_matricula )
        REFERENCES t_xcave_colaborador ( cd_matricula )
            ON DELETE CASCADE;

ALTER TABLE t_xcave_documento
    ADD CONSTRAINT fk_xcave_doc_login FOREIGN KEY ( cd_matricula )
        REFERENCES t_xcave_colaborador ( cd_matricula )
            ON DELETE CASCADE;

-- exclui as sequências
DROP SEQUENCE SQ_XCAVE_MATRICULA;
DROP SEQUENCE SQ_XCAVE_DOCUMENTO;
DROP SEQUENCE SQ_XCAVE_DEPENDENTE;

-- limpa os registros das tabelas
TRUNCATE TABLE T_XCAVE_DOCUMENTO;
TRUNCATE TABLE T_XCAVE_DEPENDENTE;
TRUNCATE TABLE T_XCAVE_COLABORADOR;
TRUNCATE TABLE T_XCAVE_LOGIN;

-- cria sequência para a matrícula
CREATE SEQUENCE SQ_XCAVE_MATRICULA MAXVALUE 999999 MINVALUE 1000 NOCACHE;

-- cria sequência para o código do documento
CREATE SEQUENCE SQ_XCAVE_DOCUMENTO MAXVALUE 999999 NOCACHE;

-- cria sequência para o código do dependente
CREATE SEQUENCE SQ_XCAVE_DEPENDENTE MAXVALUE 999999 NOCACHE;

-- insere login do administrador
INSERT INTO T_XCAVE_LOGIN (CD_MATRICULA,NM_EMAIL,NM_SENHA,NM_APELIDO,ST_CADASTRO) VALUES (SQ_XCAVE_MATRICULA.NEXTVAL,'admin@fiap.com.br','fiap123','ADMIN',1);

-- insere login do novo colaborador para cadastro
INSERT INTO T_XCAVE_LOGIN (CD_MATRICULA,NM_EMAIL,NM_SENHA,NM_APELIDO,ST_CADASTRO) VALUES (SQ_XCAVE_MATRICULA.NEXTVAL,'joao@fiap.com.br','joao123','JOAO',0);
INSERT INTO T_XCAVE_LOGIN (CD_MATRICULA,NM_EMAIL,NM_SENHA,NM_APELIDO,ST_CADASTRO) VALUES (SQ_XCAVE_MATRICULA.NEXTVAL,'maria@fiap.com.br','maria123','MARIA',0);
INSERT INTO T_XCAVE_LOGIN (CD_MATRICULA,NM_EMAIL,NM_SENHA,NM_APELIDO,ST_CADASTRO) VALUES (SQ_XCAVE_MATRICULA.NEXTVAL,'pedro@fiap.com.br','pedro123','PEDRO',0);

-- commit das informações
COMMIT;