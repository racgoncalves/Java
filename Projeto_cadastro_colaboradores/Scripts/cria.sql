-- Gerado por Oracle SQL Developer Data Modeler 19.4.0.350.1424
--   em:        2020-10-03 14:00:42 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



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



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                             20
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0