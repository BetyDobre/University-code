-- SERVER2 - fisier 3 rulat ca DBB
-- ORCL2 -> provincie

DROP TABLE CONSULT_PROCEDURA_prov;
DROP TABLE RETETA_MEDICAMENT_prov;
DROP TABLE MEDICAMENT_prov_repl;
DROP TABLE RETETA_prov;
DROP TABLE CONSULT_prov;
DROP TABLE PACIENT_prov_repl;
DROP TABLE DOCTOR_contact_prov;
DROP TABLE PROCEDURA_prov_repl;
DROP TABLE SPECIALIZARE_prov_repl;
DROP TABLE DIAGNOSTIC_prov_repl;
DROP TABLE SPITAL_prov;
DROP TABLE ADRESA_prov;
DROP TABLE ORAS_prov;
DROP TABLE JUDEt_prov;

DROP PUBLIC DATABASE LINK bd_buc;
CREATE PUBLIC DATABASE LINK bd_buc
CONNECT TO BDD
IDENTIFIED BY distribuit
USING 'BDD_BUC';

SELECT * FROM ALL_DB_LINKS;

--Create tabel JUDET provincie
create table judet_prov as 
select * from bdd_centralizat.judet@bd_buc
where lower(nume_judet) <> 'bucuresti';

select * from judet_prov;

alter table judet_prov
add constraint pk_judet_prov primary key(id_judet);

--Create tabel ORAS provincie
create table oras_prov as 
select a.*
from BDD_CENTRALIZAT.oras@bd_buc a
where exists (select 1 from judet_prov b where b.id_judet = a.id_judet);

select * from oras_prov;

alter table oras_prov
add constraint pk_oras_prov
primary key (id_oras);

alter table oras_prov
add constraint fk_oras_judet_prov
foreign key (id_judet)references judet_prov(id_judet);

--Creare tabel ADRESA provincie
create table adresa_prov
as select a.*
from bdd_centralizat.adresa@bd_buc a
where  EXISTS (select 1 from oras_prov b where a.id_oras = b.id_oras);

select * from adresa_prov;

alter table adresa_prov
add constraint pk_adresa_prov
primary key (id_adresa);

alter table adresa_prov
add constraint fk_adresa_oras_prov
foreign key (id_oras) references oras_prov(id_oras);

--Creare tabel SPITAL provincie
create table spital_prov
as select a.*
from bdd_centralizat.spital@bd_buc a
where  EXISTS (select 1 from adresa_prov b where a.id_adresa = b.id_adresa);

select * from spital_prov;

alter table spital_prov
add constraint pk_spital_prov
primary key (id_spital);

alter table spital_prov
add constraint fk_spital_adresa_prov
foreign key (id_adresa) references adresa_prov(id_adresa);

--Fragmentare verticala pe tabelul DOCTOR + fragmentare orizontala pentru provincie
create table doctor_contact_prov
as select id_doctor, id_spital, id_specializare, nume, prenume, email
from bdd_centralizat.doctor@bd_buc a
where EXISTS (select 1 from spital_prov b where a.id_spital = b.id_spital);

select * from doctor_contact_prov;

alter table doctor_contact_prov
add constraint pk_doctor_contact_prov
primary key (id_doctor);

alter table doctor_contact_prov
add constraint fk_doctor_contact_spital_prov
foreign key (id_spital) references spital_prov(id_spital);

--Creare tabel CONSULT provincie
create table consult_prov
as select a.*
from bdd_centralizat.consult@bd_buc a
where exists (select 1 from doctor_contact_prov b where a.id_doctor = b.id_doctor);

select * from consult_prov;

alter table consult_prov
add constraint pk_consult_prov
primary key (id_consult);

alter table consult_prov
add constraint fk_consult_doctor_prov
foreign key (id_doctor) references doctor_contact_prov(id_doctor);

--Creare tabel CONSULT_PROCEDURA provincie
create table consult_procedura_prov
as select a.*
from bdd_centralizat.consult_procedura@bd_buc a
where exists (select 1 from consult_prov b where a.id_consult = b.id_consult);

select * from consult_procedura_prov;

alter table consult_procedura_prov
add constraint pk_consult_procedura_prov
primary key (id_consult, id_procedura);

alter table consult_procedura_prov
add constraint fk_consult_procedura_consult_prov
foreign key (id_consult) references consult_prov(id_consult);

--Creare tabel RETETA provincie
create table reteta_prov
as select a.*
from bdd_centralizat.reteta@bd_buc a
where exists (select 1 from consult_prov b where a.id_reteta = b.id_reteta);

select * from reteta_prov;

alter table reteta_prov
add constraint pk_reteta_prov
primary key (id_reteta);

alter table consult_prov
add constraint fk_consult_reteta_prov
foreign key (id_reteta) references reteta_prov(id_reteta);

--Creare tabel RETETA_MEDICAMENT provincie
create table reteta_medicament_prov
as select a.*
from bdd_centralizat.reteta_medicament@bd_buc a
where exists (select 1 from reteta_prov b where a.id_reteta = b.id_reteta);

select * from reteta_medicament_prov;

alter table reteta_medicament_prov
add constraint pk_reteta_medicament_prov
primary key (id_reteta, id_medicament);

alter table reteta_medicament_prov
add constraint fk_reteta_medicament_reteta_prov
foreign key (id_reteta) references reteta_prov(id_reteta);



--REPLICARE
--Replicare tabel SPECIALIZARE in provincie
create table specializare_prov_repl as 
select * from BDD_CENTRALIZAT.specializare@bd_buc;

alter table specializare_prov_repl
add constraint pk_specializare_prov_repl
primary key (id_specializare);

alter table doctor_contact_prov
add constraint fk_doctor_contact_specializare_prov
foreign key (id_specializare) references specializare_prov_repl(id_specializare);

--Replicare tabel DIAGNOSTIC in provincie
create table diagnostic_prov_repl as
select * from BDD_CENTRALIZAT.diagnostic@bd_buc;

alter table diagnostic_prov_repl 
add constraint pk_diagnostic_prov_repl 
primary key (id_diagnostic);

alter table consult_prov
add constraint fk_consult_diagnostic_prov
foreign key (id_diagnostic) references diagnostic_prov_repl(id_diagnostic);

--Replicare tabel MEDICAMENT in provincie
create table medicament_prov_repl as
select * from BDD_CENTRALIZAT.medicament@bd_buc;

alter table medicament_prov_repl 
add constraint pk_medicament_prov_repl 
primary key (id_medicament);

alter table reteta_medicament_prov
add constraint fk_reteta_medicament_medicament_prov
foreign key (id_medicament) references medicament_prov_repl(id_medicament);

--Replicare tabel PROCEDURA in provincie
create table procedura_prov_repl as
select * from BDD_CENTRALIZAT.procedura@bd_buc;

alter table procedura_prov_repl
add constraint pk_procedura_prov_repl
primary key (id_procedura);

alter table consult_procedura_prov
add constraint fk_consult_procedura_procedura_prov
foreign key (id_procedura) references procedura_prov_repl(id_procedura);

--Replicare tabel PACIENT in provincie
create table pacient_prov_repl as
select * from BDD_CENTRALIZAT.pacient@bd_buc;

alter table pacient_prov_repl
add constraint pk_pacient_prov_repl
primary key (id_pacient);

alter table consult_prov
add constraint fk_consult_pacient_prov
foreign key (id_pacient) references pacient_prov_repl(id_pacient);