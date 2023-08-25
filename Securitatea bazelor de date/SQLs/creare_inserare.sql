--Crearea tabelului ADRESA:
CREATE TABLE ADRESA(
    id_adresa NUMBER(4),
    judet VARCHAR2(20),
    oras VARCHAR2(20) NOT NULL,
    strada VARCHAR2(30),
    cod_postal VARCHAR2(6),
    CONSTRAINT adresa_pk PRIMARY KEY (id_adresa)
);

INSERT INTO ADRESA VALUES(1, 'Prahova', 'Ploiesti', 'Aleea Codrului', '102753');
INSERT INTO ADRESA VALUES(2, 'Bucuresti', 'Bucuresti', 'Calea Plevnei 132', '010825');
INSERT INTO ADRESA VALUES(3, 'Brasov', 'Brasov', 'Str. Sperantei', '242052');
INSERT INTO ADRESA VALUES(4, 'Bucuresti', 'Bucuresti', 'Splaiul Independentei', '050098');
INSERT INTO ADRESA VALUES(5, 'Prahova', 'Ploiesti', 'Bd. Republicii', '100265');

--Crearea tabelului PACIENT:
CREATE TABLE PACIENT(
    id_pacient NUMBER(4),
    id_adresa NUMBER(4),
    cnp VARCHAR2(100) NOT NULL,
    nume VARCHAR2(30) NOT NULL,
    prenume VARCHAR2(50) NOT NULL,
    sex VARCHAR2(1) CHECK (sex ='F' OR sex = 'M'),
    data_nastere DATE NOT NULL,
    telefon VARCHAR2(100) NOT NULL,
    email VARCHAR2(100),
    CONSTRAINT pacient_pk PRIMARY KEY (id_pacient),
    CONSTRAINT pacient_fk_adr FOREIGN KEY(id_adresa) REFERENCES ADRESA(id_adresa)
);

INSERT INTO PACIENT VALUES(1, 1, '2254173871953', 'Moldoveanu', 'Maria', 'F', TO_DATE('23-06-1994','DD-MM-YYYY'),'0723657512','moldo.maria@gmail.com');
INSERT INTO PACIENT VALUES(2, 2, '2736478901255','Olaru', 'Andra', 'F', TO_DATE('11-05-1998', 'dd-MM-yyyy'), '0762619120', 'andrao@gmail.com');
INSERT INTO PACIENT VALUES(3, 3, '1234567890987','Popescu', 'Ion', 'M', TO_DATE('23-11-1970', 'dd-MM-yyyy'), '0734271957', 'ionion@yahoo.com');

--Crearea tabelului CLINICA
CREATE TABLE CLINICA(
    id_clinica NUMBER(4),
    id_adresa NUMBER(4),
    nume VARCHAR2(50) NOT NULL,
    contact VARCHAR2(100),
    CONSTRAINT clinica_pk PRIMARY KEY (id_clinica),
    CONSTRAINT clinica_fk_adr FOREIGN KEY(id_adresa) REFERENCES ADRESA(id_adresa)
);

INSERT INTO CLINICA VALUES(1, 4, 'Sanamed', 'sanamed@yahoo.ro');
INSERT INTO CLINICA VALUES(2, 5, 'Spitalul Universitar', '021 318 0523');

--Crearea tabelului MEDICAMENT:
CREATE TABLE MEDICAMENT(
    id_medicament NUMBER(4),
    nume_medicament VARCHAR2(50) NOT NULL,
    pret_medicament NUMBER(4,2) DEFAULT 0,
    instructiuni_administrare VARCHAR2(100),
    CONSTRAINT medicament_pk PRIMARY KEY (id_medicament)
); 

INSERT INTO MEDICAMENT VALUES(1, 'Bengay', 11 , 'la nevoie');
INSERT INTO MEDICAMENT VALUES(2, 'Nurofen', 25, 'la 8 ore');
INSERT INTO MEDICAMENT VALUES(3, 'Aspirina', 20, 'la durere');
INSERT INTO MEDICAMENT VALUES(4, 'Algocalmin', 15, 'de 2 ori pe zi');
INSERT INTO MEDICAMENT VALUES(5, 'Paracetamol', 3, 'la fiecare 6 ore');
INSERT INTO MEDICAMENT VALUES(6, 'Antibiotic', 40, 'la 12 ore');
INSERT INTO MEDICAMENT VALUES(7, 'Antiinflamatoare', 30, 'de 3 ori pe zi');
INSERT INTO MEDICAMENT VALUES(8, 'Aspenter', 12, 'in fiecare zi dimineata');

--Crearea tabelului FUNCTIE
CREATE TABLE FUNCTIE(
    id_functie NUMBER(4),
    denumire_functie VARCHAR2(50) NOT NULL,
    specializare VARCHAR2(50),
    CONSTRAINT functie_pk PRIMARY KEY (id_functie)
);

INSERT INTO FUNCTIE VALUES(1, 'Doctor', 'Ortopedie');
INSERT INTO FUNCTIE VALUES(2, 'Specialist', 'Ortopedie');
INSERT INTO FUNCTIE VALUES(3, 'Doctor', 'Cardiologie');
INSERT INTO FUNCTIE VALUES(4, 'Asistent', 'Ortopedie');
INSERT INTO FUNCTIE VALUES(5, 'Doctor', 'Pediatrie');
INSERT INTO FUNCTIE VALUES(6, 'Medic rezident', 'Oftalmologie');

--Crearea tabelului DEPARTAMENT
CREATE TABLE DEPARTAMENT(
    id_departament NUMBER(4),
    id_clinica NUMBER(4),
    nume_departament VARCHAR2(200) NOT NULL,
    id_manager_departament NUMBER(4),
    CONSTRAINT departament_pk PRIMARY KEY (id_departament),
    CONSTRAINT departament_fk_clinica FOREIGN KEY(id_clinica) REFERENCES CLINICA(id_clinica)
);

INSERT INTO DEPARTAMENT VALUES (1, 1, 'Ortopedie si traumatologie', 1);
INSERT INTO DEPARTAMENT VALUES (2, 1, 'Cercetare', 1);
INSERT INTO DEPARTAMENT VALUES (3, 2, 'Cardiologie', 3);
INSERT INTO DEPARTAMENT VALUES (4, 2, 'Cercetare', 3);

--Crearea tabelului PERSONAL_MEDICAL
CREATE TABLE PERSONAL_MEDICAL(
    id_personal NUMBER(4),
    id_departament NUMBER(4),
    id_functie NUMBER(4),
    nume VARCHAR2(50) NOT NULL,
    data_angajare DATE,
    CONSTRAINT personal_pk PRIMARY KEY (id_personal),
    CONSTRAINT personal_job_fk FOREIGN KEY (id_functie) REFERENCES FUNCTIE(id_functie),
    CONSTRAINT personal_departament_fk FOREIGN KEY (id_departament) REFERENCES DEPARTAMENT(id_departament)
); 

ALTER TABLE DEPARTAMENT
ADD CONSTRAINT depart_fk_manager FOREIGN KEY(id_manager_departament)REFERENCES PERSONAL_MEDICAL(id_personal);
ALTER TABLE DEPARTAMENT
DISABLE CONSTRAINT depart_fk_manager;

INSERT INTO PERSONAL_MEDICAL VALUES(1, 1, 1, 'Zaharescu Lucian', TO_DATE('19-01-2008', 'dd-MM-yyyy'));
INSERT INTO PERSONAL_MEDICAL VALUES(2, 1, 4, 'Marin Iulia', TO_DATE('12-09-2018', 'dd-MM-yyyy'));
INSERT INTO PERSONAL_MEDICAL VALUES(3, 3, 3, 'Albu Marian', TO_DATE('23-02-2004', 'dd-MM-yyyy'));
INSERT INTO PERSONAL_MEDICAL VALUES(4, 4, 6, 'Miron Ilinca', TO_DATE('02-06-2020', 'dd-MM-yyyy'));

ALTER TABLE DEPARTAMENT
ENABLE CONSTRAINT depart_fk_manager;

--Crearea tabelului CONSULT:
CREATE TABLE CONSULT(
    id_consult NUMBER(4),
    id_personal NUMBER(4),
    id_pacient NUMBER(4),
    diagnostic VARCHAR2(100),
    data_consult DATE NOT NULL,
    pret_consult NUMBER(5),
    detalii VARCHAR2(200),
    CONSTRAINT consult_pk PRIMARY KEY (id_consult),
    CONSTRAINT consult_fk_pacient FOREIGN KEY (id_pacient) REFERENCES PACIENT(id_pacient),
    CONSTRAINT consult_fk_personal FOREIGN KEY (id_personal) REFERENCES PERSONAL_MEDICAL(id_personal)
);

INSERT INTO CONSULT VALUES(1, 1, 1, 'Entorsa', TO_DATE(sysdate, 'dd-MM-yyyy'), 300, 'entorsa sportiva');
INSERT INTO CONSULT VALUES(2, 3, 3, 'Infarct', TO_DATE(sysdate, 'dd-MM-yyyy'), 200, 'infarct miocardic in urma cu 6 luni');
INSERT INTO CONSULT VALUES(3, 4, 3, 'Miopie', TO_DATE(sysdate, 'dd-MM-yyyy'), 100, 'miopie pe ambii ochi cu dioptrie -1.5');

--Crearea tabelului CONSULT_MEDICAMENT:
CREATE TABLE CONSULT_MEDICAMENT(
    id_consult NUMBER(4),
    id_medicament NUMBER(4),
    cantitate NUMBER(5) DEFAULT 1,
    CONSTRAINT consult_med_pk_ PRIMARY KEY(id_consult, id_medicament),
    CONSTRAINT medicament_fk FOREIGN KEY (id_medicament) REFERENCES MEDICAMENT(id_medicament),
    CONSTRAINT consult_fk FOREIGN KEY (id_consult) REFERENCES CONSULT(id_consult)
);

INSERT INTO CONSULT_MEDICAMENT VALUES(1,1,1);
INSERT INTO CONSULT_MEDICAMENT VALUES(2,8,5);
INSERT INTO CONSULT_MEDICAMENT VALUES(3,7,1);

--Crearea tabelului PROCEDURA:
CREATE TABLE PROCEDURA(
    id_procedura NUMBER(5),
    nume_procedura VARCHAR2(100) NOT NULL,
    pret NUMBER(7,2) NOT NULL,
    detalii VARCHAR2(200),
    invaziv VARCHAR2(2) CHECK (invaziv ='DA' OR invaziv = 'NU'),
    CONSTRAINT procedura_pk PRIMARY KEY (id_procedura)
);

INSERT INTO PROCEDURA VALUES(1, 'Radiografie', 100, 's-a constatat entorsa', 'DA');
INSERT INTO PROCEDURA VALUES(2, 'Masaj', 150, 'pentru vanatai', 'NU');
INSERT INTO PROCEDURA VALUES(3, 'EKG', 300, 'variatiile batailor inimiii', 'NU');
INSERT INTO PROCEDURA VALUES(4, 'Aparat oftalmologic', 0, 'pentru a testa vederea', 'NU');

--Crearea tabelului CONSULT_PROCEDURA:
CREATE TABLE CONSULT_PROCEDURA(
    id_consult NUMBER(4),
    id_procedura NUMBER(4),
    CONSTRAINT consult_fk_cp FOREIGN KEY (id_consult) REFERENCES CONSULT(id_consult),
    CONSTRAINT procedura_fk_cp FOREIGN KEY (id_procedura) REFERENCES PROCEDURA(id_procedura),
    CONSTRAINT consult_procedura_pk PRIMARY KEY (id_consult, id_procedura)
);

INSERT INTO CONSULT_PROCEDURA VALUES(1,1);
INSERT INTO CONSULT_PROCEDURA VALUES(1,2);
INSERT INTO CONSULT_PROCEDURA VALUES(2,3);
INSERT INTO CONSULT_PROCEDURA VALUES(3,4);

commit;