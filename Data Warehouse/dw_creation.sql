ALTER TABLE costuri DROP CONSTRAINT costuri_pacienti_fK;
ALTER TABLE costuri DROP CONSTRAINT costuri_spitale_fK;
ALTER TABLE costuri DROP CONSTRAINT costuri_doctori_fK;
ALTER TABLE costuri DROP CONSTRAINT costuri_retete_fK;
ALTER TABLE costuri DROP CONSTRAINT costuri_consultatii_fK;

DROP TABLE PACIENTI;
DROP TABLE SPITALE;
DROP TABLE DOCTORI;
DROP TABLE RETETE;
DROP TABLE CONSULTATII;
DROP TABLE COSTURI;
DROP TABLE TIMP;

--Crearea tabelului PACIENTi:
CREATE TABLE PACIENTI(
    id_pacient NUMBER(4),
    nume VARCHAR2(100) NOT NULL RELY,
    prenume VARCHAR2(100) NOT NULL RELY,
    sex VARCHAR2(1) CHECK (sex ='F' OR sex = 'M') ENABLE VALIDATE,
    data_nastere DATE NOT NULL RELY,
    telefon VARCHAR2(10) NOT NULL RELY,
    email VARCHAR2(30),
    categorie_pacient VARCHAR2(30) NOT NULL RELY,
    CONSTRAINT pacienti_pk PRIMARY KEY (id_pacient) RELY,
    CONSTRAINT pacienti_phone CHECK (LENGTHB(telefon) = 10) ENABLE NOVALIDATE
);

--Crearea tabelului SPITALE:
CREATE TABLE SPITALE(
    id_spital NUMBER(4),
    nume VARCHAR2(50) NOT NULL RELY,
    contact VARCHAR2(100),
    judet VARCHAR2(20),
    oras VARCHAR2(20) NOT NULL RELY,
    strada VARCHAR2(30),
    cod_postal VARCHAR2(6),
    CONSTRAINT spitale_pk PRIMARY KEY (id_spital) RELY
);

--Crearea tabelului DOCTORI
CREATE TABLE DOCTORI(
    id_doctor NUMBER(5),
    nume VARCHAR2(50) NOT NULL RELY,
    specializare VARCHAR2(100) NOT NULL RELY,
    categorie1 VARCHAR2(20),
    categorie2 VARCHAR2(20),
    categorie3 VARCHAR2(20),
    CONSTRAINT doctori_pk PRIMARY KEY (id_doctor) RELY
);

--Crearea tabelului RETETE:
CREATE TABLE RETETE(
    id_reteta NUMBER(5),
    descriere VARCHAR2(200),
    lista_medicamente VARCHAR2(300),
    pret_reteta NUMBER(5) DEFAULT 0,
    CONSTRAINT retete_pk PRIMARY KEY (id_reteta) RELY
); 

--Crearea tabelului CONSULTATII:
CREATE TABLE CONSULTATII(
    id_consult NUMBER(4),
    denumire_diagnostic VARCHAR2(100),
    lista_proceduri VARCHAR2(200),
    detalii VARCHAR2(200),
    pret_consult NUMBER(5) NOT NULL RELY,
    discount NUMBER(2) DEFAULT 0,
    CONSTRAINT consultatii_pk PRIMARY KEY (id_consult) RELY,
    CONSTRAINT discount_value CHECK (discount = 0 or discount = 25 or discount = 50 or discount = 75) RELY
);

--Crearea tabelei de TIMP
CREATE TABLE TIMP(
    ID_TIMP DATE, 
	AN NUMBER(4,0), 
	SEMESTRU VARCHAR2(10 BYTE), 
    SEMESTRU_NR NUMBER(2),
	TRIMESTRU VARCHAR2(10 BYTE), 
    TRIMESTRU_NR NUMBER(2),
	LUNA VARCHAR2(10 BYTE), 
    LUNA_NR NUMBER(2),
	SAPTAMANA_AN VARCHAR2(10 BYTE), 
    SAPTAMANA_AN_NR NUMBER(2), 
	SAPTAMANA_LUNA VARCHAR2(10 BYTE), 
    SAPTAMANA_luna_NR NUMBER(2), 
	ZI_AN VARCHAR2(10 BYTE),
    ZI_AN_NR NUMBER(2), 
	ZI_LUNA VARCHAR2(10 BYTE),
    ZI_LUNA_NR NUMBER(2), 
	ZI_SAPTAMANA NUMBER(4,0), 
	ZI_NUME VARCHAR2(30 BYTE), 
	LUNA_NUME VARCHAR2(30 BYTE), 
	WEEKEND NUMBER(4,0), 
	AN_BISECT NUMBER(4,0), 
	DATA DATE, 
    CONSTRAINT PK_TIMP PRIMARY KEY (ID_TIMP) ENABLE VALIDATE
);

CREATE TABLE COSTURI(
    id NUMBER(4),
    timp_id DATE,
    doctor_id NUMBER(4),
    spital_id NUMBER(4),
    consult_id NUMBER(4),
    reteta_id NUMBER(4),
    pacient_id NUMBER(4),
    cost_consult NUMBER(5),
    cost_tratament NUMBER(5),
    CONSTRAINT costuri_pk PRIMARY KEY (id) ENABLE VALIDATE,
    CONSTRAINT costuri_timp_fk FOREIGN KEY (timp_id) REFERENCES timp(id_timp) DISABLE NOVALIDATE,
    CONSTRAINT costuri_doctori_fk FOREIGN KEY (doctor_id) REFERENCES doctori(id_doctor) ENABLE NOVALIDATE,
    CONSTRAINT costuri_spitale_fk FOREIGN KEY (spital_id) REFERENCES spitale(id_spital) ENABLE NOVALIDATE,
    CONSTRAINT costuri_consultatii_fk FOREIGN KEY (consult_id) REFERENCES consultatii(id_consult) ENABLE NOVALIDATE,
    CONSTRAINT costuri_retete_fk FOREIGN KEY (reteta_id) REFERENCES retete(id_reteta) ENABLE NOVALIDATE,
    CONSTRAINT costuri_pacienti_fk FOREIGN KEY (pacient_id) REFERENCES pacienti(id_pacient) ENABLE NOVALIDATE
);

-- alter pluggable database orclpdb open; din sys
alter session set container = orclpdb;
create user admin_dw identified by admin_dw;
grant create session to admin_dw;
grant connect to admin_dw;
grant create table to admin_dw;
alter user admin_dw quota unlimited on users;

grant select, insert, update, delete on pacienti to admin_dw;
grant select, insert, update, delete on spitale to admin_dw;
grant select, insert, update, delete on doctori to admin_dw;
grant select, insert, update, delete on retete to admin_dw;
grant select, insert, update, delete on consultatii to admin_dw;
grant select, insert, update, delete on timp to admin_dw;
grant select, insert, update, delete on costuri to admin_dw;
alter session set container = cdb$root;