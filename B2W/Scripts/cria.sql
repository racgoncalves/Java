-- Gerado por Oracle SQL Developer Data Modeler 19.4.0.350.1424
--   em:        2020-09-21 16:50:22 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE t_xcave_colaborador (
    cd_matricula    NUMBER(5) NOT NULL,
    nm_colaborador  VARCHAR2(60) NOT NULL,
    ds_email        VARCHAR2(35) NOT NULL,
    vl_salario      NUMBER(7, 2) NOT NULL,
    st_cadastro     NUMBER(1) NOT NULL,
    cd_login_rh     NUMBER(5)
);

ALTER TABLE t_xcave_colaborador ADD CONSTRAINT t_xcave_colaborador_pk PRIMARY KEY ( cd_matricula );

CREATE TABLE t_xcave_conta_banco (
    cd_matricula       NUMBER(5) NOT NULL,
    nr_agencia         NUMBER(4) NOT NULL,
    nr_digito_agencia  NUMBER(1) NOT NULL,
    nr_conta           NUMBER(7) NOT NULL,
    nr_digito_conta    NUMBER(1) NOT NULL
);

ALTER TABLE t_xcave_conta_banco ADD CONSTRAINT t_xcave_conta_banco_pk PRIMARY KEY ( cd_matricula );

CREATE TABLE t_xcave_dados (
    cd_matricula      NUMBER(5) NOT NULL,
    nr_cpf            VARCHAR2(14) NOT NULL,
    nr_pis            VARCHAR2(14) NOT NULL,
    ds_sexo           VARCHAR2(15) NOT NULL,
    nm_nacionalidade  VARCHAR2(35) NOT NULL,
    nm_naturalidade   VARCHAR2(35) NOT NULL,
    dt_nascimento     DATE NOT NULL,
    ds_est_civil      VARCHAR2(15) NOT NULL,
    qt_filhos         NUMBER(2) NOT NULL,
    nm_etnia          VARCHAR2(15) NOT NULL,
    tp_camisa         CHAR(2) NOT NULL,
    nm_orient_sexual  VARCHAR2(35),
    nm_religiao       VARCHAR2(35)
);

ALTER TABLE t_xcave_dados ADD CONSTRAINT t_xcave_dados_pk PRIMARY KEY ( cd_matricula );

CREATE TABLE t_xcave_dependente (
    cd_matricula        NUMBER(5) NOT NULL,
    cd_dependente       NUMBER(3) NOT NULL,
    tp_dependente       VARCHAR2(15) NOT NULL,
    im_cert_casamento   BLOB,
    im_cert_nascimento  BLOB,
    im_rg_genitor       BLOB
);

ALTER TABLE t_xcave_dependente ADD CONSTRAINT t_xcave_dependente_pk PRIMARY KEY ( cd_dependente,
                                                                                  cd_matricula );

CREATE TABLE t_xcave_documentos (
    cd_matricula          NUMBER(5) NOT NULL,
    im_rg                 BLOB NOT NULL,
    im_carteira_trabalho  BLOB NOT NULL,
    im_comp_residencia    BLOB NOT NULL,
    im_titulo_eleitor     BLOB NOT NULL,
    im_comp_escolaridade  BLOB NOT NULL,
    im_reservista         BLOB
);

ALTER TABLE t_xcave_documentos ADD CONSTRAINT t_xcave_documentos_pk PRIMARY KEY ( cd_matricula );

CREATE TABLE t_xcave_login_rh (
    cd_login_rh  NUMBER(5) NOT NULL,
    nm_usuario   VARCHAR2(20) NOT NULL,
    nm_senha     VARCHAR2(20) NOT NULL
);

ALTER TABLE t_xcave_login_rh ADD CONSTRAINT t_xcave_login_rh_pk PRIMARY KEY ( cd_login_rh );

ALTER TABLE t_xcave_dados
    ADD CONSTRAINT t_xcave_colaborador_fk FOREIGN KEY ( cd_matricula )
        REFERENCES t_xcave_colaborador ( cd_matricula );

ALTER TABLE t_xcave_conta_banco
    ADD CONSTRAINT t_xcave_colaborador_fkv1 FOREIGN KEY ( cd_matricula )
        REFERENCES t_xcave_colaborador ( cd_matricula );

ALTER TABLE t_xcave_dependente
    ADD CONSTRAINT t_xcave_colaborador_fkv2 FOREIGN KEY ( cd_matricula )
        REFERENCES t_xcave_colaborador ( cd_matricula );

ALTER TABLE t_xcave_documentos
    ADD CONSTRAINT t_xcave_colaborador_fkv3 FOREIGN KEY ( cd_matricula )
        REFERENCES t_xcave_colaborador ( cd_matricula );

ALTER TABLE t_xcave_colaborador
    ADD CONSTRAINT t_xcave_login_rh_fk FOREIGN KEY ( cd_login_rh )
        REFERENCES t_xcave_login_rh ( cd_login_rh );


-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             6
-- CREATE INDEX                             0
-- ALTER TABLE                             11
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