DROP TABLE COSTURI;
DROP TABLE SPITALE;

--Partitionare tabela SPITALE
CREATE TABLE SPITALE (
    id_spital NUMBER(4),
    nume VARCHAR2(50) NOT NULL RELY,
    contact VARCHAR2(100),
    judet VARCHAR2(20),
    oras VARCHAR2(20) NOT NULL RELY,
    strada VARCHAR2(30),
    cod_postal VARCHAR2(6),
    CONSTRAINT spitale_pk PRIMARY KEY (id_spital) RELY
)
PARTITION BY LIST(judet) (
PARTITION judete_moldova VALUES('Botosani', 'Bacau', 'Galati', 'Iasi', 'Neamt', 'Suceava', 'Vaslui'),
PARTITION judete_muntenia VALUES('Arges', 'Braila', 'Buzau', 'Calarasi', 'Dambovita', 'Giurgiu', 'Ialomita', 'Ilfov', 'Prahova', 'Teleorman', 'Municipiul Bucuresti'),
PARTITION judete_oltenia VALUES('Dolj', 'Gorj', 'Mehedinti', 'Olt', 'Valcea'),
PARTITION judete_banat VALUES('Timis', 'Caras-Severin', 'Arad'),
PARTITION judete_transilvania VALUES('Alba', 'Bistrita-Nasaud', 'Brasov', 'Cluj', 'Sibiu', 'Mures', 'Hunedoara'),
PARTITION judete_altele VALUES(DEFAULT));

--Partitionare tabela COSTURI
CREATE TABLE COSTURI (
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
)
PARTITION BY RANGE(timp_id) (
PARTITION trimestrul_I VALUES LESS THAN(TO_DATE('01/04/2022','DD/MM/YYYY')),
PARTITION trimestrul_II VALUES LESS THAN(TO_DATE('01/07/2022','DD/MM/YYYY')),
PARTITION trimestrul_III VALUES LESS THAN(TO_DATE('01/10/2022','DD/MM/YYYY')),
PARTITION trimestrul_IV VALUES LESS THAN(TO_DATE('01/01/2023','DD/MM/YYYY')),
PARTITION rest VALUES LESS THAN (MAXVALUE));