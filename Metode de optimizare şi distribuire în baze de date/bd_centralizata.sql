DROP TABLE CONSULT_PROCEDURA;
DROP TABLE RETETA_MEDICAMENT;
DROP TABLE MEDICAMENT;
ALTER TABLE CONSULT DROP CONSTRAINT CONSULT_fK_RETETA;
DROP TABLE RETETA;
DROP TABLE CONSULT;
DROP TABLE PACIENT;
DROP TABLE DOCTOR;
DROP TABLE PROCEDURA;
DROP TABLE SPECIALIZARE;
DROP TABLE DIAGNOSTIC;
DROP TABLE SPITAL;
DROP TABLE ADRESA;
DROP TABLE ORAS;
DROP TABLE JUDET;

--Crearea tabelului JUDET:
CREATE TABLE JUDET(
    id_judet NUMBER(4),
    nume_judet VARCHAR(20) NOT NULL,
    CONSTRAINT judet_pk PRIMARY KEY (id_judet)
);

--CREAREA TABELULUI ORAS:
CREATE TABLE ORAS(
    id_oras NUMBER(4),
    id_judet Number(4),
    nume_oras VARCHAR2(20) NOT NULL,
    CONSTRAINT oras_pk PRIMARY KEY (id_oras),
    CONSTRAINT oras_fk_judet FOREIGN KEY(id_judet) REFERENCES JUDET(id_judet) ON DELETE CASCADE
);

--Crearea tabelului ADRESA:
CREATE TABLE ADRESA(
    id_adresa NUMBER(4),
    id_oras NUMBER(4),
    strada VARCHAR2(30),
    cod_postal VARCHAR2(6),
    CONSTRAINT adresa_pk PRIMARY KEY (id_adresa),
    CONSTRAINT adresa_fk_oras FOREIGN KEY(id_oras) REFERENCES ORAS(id_oras) ON DELETE CASCADE
);

--Crearea tabelului PACIENT:
CREATE TABLE PACIENT(
    id_pacient NUMBER(4),
    nume VARCHAR2(30) NOT NULL,
    prenume VARCHAR2(50) NOT NULL,
    sex VARCHAR2(1),
    data_nastere DATE NOT NULL,
    telefon VARCHAR2(100) NOT NULL,
    email VARCHAR2(100),
    CONSTRAINT pacient_pk PRIMARY KEY (id_pacient),
    CONSTRAINT pacient_phone CHECK (LENGTHB(telefon) = 10)
);

--Crearea tabelului SPITAL
CREATE TABLE SPITAL(
    id_spital NUMBER(4),
    id_adresa NUMBER(4),
    nume_spital VARCHAR2(50) NOT NULL,
    contact VARCHAR2(100),
    CONSTRAINT spital_pk PRIMARY KEY (id_spital),
    CONSTRAINT spital_fk_adr FOREIGN KEY(id_adresa) REFERENCES ADRESA(id_adresa) ON DELETE CASCADE
);

--Crearea tabelului MEDICAMENT
CREATE TABLE MEDICAMENT(
    id_medicament NUMBER(4),
    nume_medicament VARCHAR2(50) NOT NULL,
    pret NUMBER(4,2) DEFAULT 0,
    CONSTRAINT medicament_pk PRIMARY KEY (id_medicament)
); 

--Crearea tabelului SPECIALIZARE
CREATE TABLE SPECIALIZARE(
    id_specializare NUMBER(4),
    nume_specializare VARCHAR2(100) NOT NULL,
    categorie number(4),
    CONSTRAINT specializare_pk PRIMARY KEY (id_specializare)
);

--Crearea tabelului DOCTOR
CREATE TABLE DOCTOR(
    id_doctor NUMBER(4),
    id_spital NUMBER(4),
    id_specializare NUMBER(4),
    nume VARCHAR2(50) NOT NULL,
    prenume VARCHAR2(50) NOT NULL,
    data_angajare DATE,
    salariu NUMBER(6),
    email VARCHAR2(50),
    CONSTRAINT doctor_pk PRIMARY KEY (id_doctor),
    CONSTRAINT doctor_spital_fk FOREIGN KEY (id_spital) REFERENCES SPITAL(id_spital) ON DELETE CASCADE,
    CONSTRAINT doctor_specializare_fk FOREIGN KEY (id_specializare) REFERENCES SPECIALIZARE(id_specializare) ON DELETE CASCADE
); 

--Crearea tabelului DIAGNOSTIC
CREATE TABLE DIAGNOSTIC(
    id_diagnostic NUMBER(4),
    denumire_diagnostic VARCHAR2(100),
    CONSTRAINT diagnostic_pk PRIMARY KEY (id_diagnostic)
);

--Crearea tabelului RETETA
CREATE TABLE RETETA(
    id_reteta NUMBER(4),
    data_reteta DATE DEFAULT SYSDATE,
    descriere VARCHAR2(200),
    CONSTRAINT reteta_pk PRIMARY KEY (id_reteta)
);

--Crearea tabelului RETETA_MEDICAMENT:
CREATE TABLE RETETA_MEDICAMENT(
    id_reteta NUMBER(4),
    id_medicament NUMBER(4),
    CONSTRAINT reteta_med_pk_ PRIMARY KEY(id_reteta, id_medicament),
    CONSTRAINT medicament_fk FOREIGN KEY (id_medicament) REFERENCES MEDICAMENT(id_medicament) ON DELETE CASCADE,
    CONSTRAINT reteta_fk FOREIGN KEY (id_reteta) REFERENCES RETETA(id_reteta) ON DELETE CASCADE
);

--Crearea tabelului PROCEDURA:
CREATE TABLE PROCEDURA(
    id_procedura NUMBER(5),
    nume_procedura VARCHAR2(100) NOT NULL,
    pret_procedura NUMBER(5) DEFAULT 0 NOT NULL,
    informatii VARCHAR2(200),
    CONSTRAINT procedura_pk PRIMARY KEY (id_procedura)
);

--Crearea tabelului CONSULT:
CREATE TABLE CONSULT(
    id_consult NUMBER(4),
    id_pacient NUMBER(4),
    id_reteta NUMBER(4),
    id_diagnostic NUMBER(4),
    id_doctor NUMBER(4),
    pret NUMBER(5),
    data_consult DATE NOT NULL,
    detalii VARCHAR2(200),
    discount NUMBER(3) DEFAULT 0,
    CONSTRAINT consult_pk PRIMARY KEY (id_consult),
    CONSTRAINT consult_fk_pacient FOREIGN KEY (id_pacient) REFERENCES PACIENT(id_pacient) ON DELETE CASCADE,
    CONSTRAINT consult_fk_doctor FOREIGN KEY (id_doctor) REFERENCES DOCTOR(id_doctor) ON DELETE CASCADE,
    CONSTRAINT consult_fk_reteta FOREIGN KEY (id_reteta) REFERENCES RETETA(id_reteta) ON DELETE CASCADE,
    CONSTRAINT consult_fk_diagnostic FOREIGN KEY (id_diagnostic) REFERENCES DIAGNOSTIC(id_diagnostic) ON DELETE CASCADE,
    CONSTRAINT consult_c_discount CHECK (discount = 0 or discount = 25 or discount = 50 or discount = 75)
);

--Crearea tabelului CONSULT_PROCEDURA:
CREATE TABLE CONSULT_PROCEDURA(
    id_consult NUMBER(4),
    id_procedura NUMBER(4),
    CONSTRAINT consult_fk_cp FOREIGN KEY (id_consult) REFERENCES CONSULT(id_consult) ON DELETE CASCADE,
    CONSTRAINT procedura_fk_cp FOREIGN KEY (id_procedura) REFERENCES PROCEDURA(id_procedura) ON DELETE CASCADE,
    CONSTRAINT consult_procedura_pk PRIMARY KEY (id_consult, id_procedura)
);