-- SERVER1 - fisier 4 rulat ca BDD
--O19C -> bucharest

DROP TABLE CONSULT_PROCEDURA_BUC;
DROP TABLE RETETA_MEDICAMENT_buc;
DROP TABLE MEDICAMENT_buc_repl;
DROP TABLE RETETA_buc;
DROP TABLE CONSULT_buc;
DROP TABLE PACIENT_buc_repl;
DROP TABLE DOCTOR_contact_buc;
DROP TABLE PROCEDURA_buc_repl;
DROP TABLE SPECIALIZARE_buc_repl;
DROP TABLE DIAGNOSTIC_buc_repl;
DROP TABLE SPITAL_buc;
DROP TABLE ADRESA_buc;
DROP TABLE ORAS_buc;
DROP TABLE JUDET_buc;

DROP PUBLIC DATABASE LINK bd_prov;
CREATE PUBLIC DATABASE LINK bd_prov
CONNECT TO BDD
IDENTIFIED BY distribuit
USING 'bdd_prov';

SELECT * FROM ALL_DB_LINKS;

--FRAGMENTARE ORIZONTALA
--Creare table JUDET Bucuresti
create table judet_buc as
select * from bdd_centralizat.judet
where lower(nume_judet) = 'bucuresti';

select * from judet_buc;

alter table judet_buc
add constraint pk_judet_buc
primary key (id_judet);

--Verificare corectitudine
--Completitudine
select * from bdd_centralizat.judet
minus
(select * from judet_buc
union all
select * from judet_prov@bd_prov); --gol, corect
--Reconstructie
--a inclus in b
select * from bdd_centralizat.judet
minus
(select * from judet_buc
union all
select * from judet_prov@bd_prov);
--b inclus in a
(select * from judet_buc
union all
select * from judet_prov@bd_prov)
minus
select * from bdd_centralizat.judet; --gol, corect
--Disjunctie
select * from judet_buc
intersect
select * from judet_prov@bd_prov; --gol, corect

--Create tabel ORAS Bucuresti
create table oras_buc as 
select a.*
from BDD_CENTRALIZAT.oras a
where exists (select 1 from judet_buc b where b.id_judet = a.id_judet);

select * from oras_buc;

alter table oras_buc
add constraint pk_oras_buc
primary key (id_oras);

alter table oras_buc
add constraint fk_oras_buc
foreign key (id_judet)references judet_buc(id_judet);

--Verificare corectitudine
--Completitudine
select * 
from bdd_centralizat.oras
minus
(select o.* 
from oras_buc o, judet_buc j
where j.id_judet = o.id_judet
union all
select o.* 
from oras_prov@bd_prov o, judet_prov@bd_prov j
where o.id_judet = j.id_judet); -- gol, corect
--Reconstructie
--a inclus in b
select * from bdd_centralizat.oras
minus
(select * from oras_buc
union all
select * from oras_prov@bd_prov); --gol, corect
--b inclus in a
(select * from oras_buc
union all
select * from oras_prov@bd_prov)
minus
select * from bdd_centralizat.oras; --gol,corect
--Disjunctie
select * from oras_buc
intersect
select * from oras_prov@bd_prov; --gol,corect

--Creare tabel ADRESA Bucuresti
create table adresa_buc
as select a.*
from bdd_centralizat.adresa a
where  EXISTS (select 1 from oras_buc b where a.id_oras = b.id_oras);

select * from adresa_buc;

alter table adresa_buc
add constraint pk_adresa_buc
primary key (id_adresa);

alter table adresa_buc
add constraint fk_adresa_buc
foreign key (id_oras) references oras_buc(id_oras);

--Verificare corectitudine
--Completitudine
select * 
from bdd_centralizat.adresa
minus
(select a.* 
from adresa_buc a, oras_buc o
where a.id_oras = o.id_oras
union all
select a.* 
from adresa_prov@bd_prov a, oras_prov@bd_prov o
where a.id_oras = o.id_oras); -- gol, corect
--Reconstructie
--a inclus in b
select * from bdd_centralizat.adresa
minus
(select * from adresa_buc
union all
select * from adresa_prov@bd_prov); --gol, corect
--b inclus in a
(select * from adresa_buc
union all
select * from adresa_prov@bd_prov)
minus
select * from bdd_centralizat.adresa; --gol,corect
--Disjunctie
select * from adresa_buc
intersect
select * from adresa_prov@bd_prov; --gol,corect

--Creare tabel SPITAL Bucuresti
create table spital_buc
as select a.*
from bdd_centralizat.spital a
where  EXISTS (select 1 from adresa_buc b where a.id_adresa = b.id_adresa);

select * from spital_buc;

alter table spital_buc
add constraint pk_spital_buc
primary key (id_spital);

alter table spital_buc
add constraint fk_spital_adresa_buc
foreign key (id_adresa) references adresa_buc(id_adresa);

--Verificare corectitudine
--Completitudine
select * 
from bdd_centralizat.spital
minus
(select s.* 
from spital_buc s, adresa_buc a
where s.id_adresa = a.id_adresa
union all
select s.* 
from spital_prov@bd_prov s, adresa_prov@bd_prov a
where s.id_adresa = a.id_adresa); -- gol, corect
--Reconstructie
--a inclus in b
select * from bdd_centralizat.spital
minus
(select * from spital_buc
union all
select * from spital_prov@bd_prov); --gol, corect
--b inclus in a
(select * from spital_buc
union all
select * from spital_prov@bd_prov)
minus
select * from bdd_centralizat.spital; --gol,corect
--Disjunctie
select * from spital_buc
intersect
select * from spital_prov@bd_prov; --gol,corect

--FRAGMENTARE VERTICALA pe tabelul DOCTOR + fragmentare orizontala pentru Bucuresti
create table doctor_contact_buc
as select id_doctor, id_spital, id_specializare, nume, prenume, email
from bdd_centralizat.doctor a
where EXISTS (select 1 from spital_buc b where a.id_spital = b.id_spital);

select * from doctor_contact_buc;

alter table doctor_contact_buc
add constraint pk_doctor_contact_buc
primary key (id_doctor);

alter table doctor_contact_buc
add constraint fk_doctor_contact_spital_buc
foreign key (id_spital) references spital_buc(id_spital);

--Verificare corectitudine
--Completitudine
select id_doctor, id_spital, id_specializare, nume, prenume, email
from bdd_centralizat.doctor
minus
(select d.* 
from doctor_contact_buc d, spital_buc s
where d.id_spital = s.id_spital
union all
select d.* 
from doctor_contact_prov@bd_prov d, spital_prov@bd_prov s
where s.id_spital = d.id_spital); -- gol, corect
--Reconstructie
--a inclus in b
select id_doctor, id_spital, id_specializare, nume, prenume, email from bdd_centralizat.doctor
minus
(select * from doctor_contact_buc
union all
select * from doctor_contact_prov@bd_prov); --gol, corect
--b inclus in a
(select * from doctor_contact_buc
union all
select * from doctor_contact_prov@bd_prov)
minus
select id_doctor, id_spital, id_specializare, nume, prenume, email from bdd_centralizat.doctor; --gol,corect
--Disjunctie
select * from doctor_contact_buc
intersect
select * from doctor_contact_prov@bd_prov; --gol,corect


--Creare tabel CONSULT Bucuresti
create table consult_buc
as select a.*
from bdd_centralizat.consult a
where exists (select 1 from doctor_contact_buc b where a.id_doctor = b.id_doctor);

select * from consult_buc;

alter table consult_buc
add constraint pk_consult_buc
primary key (id_consult);

alter table consult_buc
add constraint fk_consult_doctor_buc
foreign key (id_doctor) references doctor_contact_buc(id_doctor);

--Verificare corectitudine
--Completitudine
select * 
from bdd_centralizat.consult
minus
(select a.* 
from consult_buc a, doctor_contact_buc b
where a.id_doctor = b.id_doctor
union all
select a.* 
from consult_prov@bd_prov a, doctor_contact_prov@bd_prov b
where a.id_doctor = b.id_doctor); -- gol, corect
--Reconstructie
--a inclus in b
select * from bdd_centralizat.consult
minus
(select * from consult_buc
union all
select * from consult_prov@bd_prov); --gol, corect
--b inclus in a
(select * from consult_buc
union all
select * from consult_prov@bd_prov)
minus
select * from bdd_centralizat.consult; --gol,corect
--Disjunctie
select * from consult_buc
intersect
select * from consult_prov@bd_prov; --gol,corect

--Creare tabel CONSULT_PROCEDURA Bucuresti
create table consult_procedura_buc
as select a.*
from bdd_centralizat.consult_procedura a
where exists (select 1 from consult_buc b where a.id_consult = b.id_consult);

select * from consult_procedura_buc;

alter table consult_procedura_buc
add constraint pk_consult_procedura_buc
primary key (id_consult, id_procedura);

alter table consult_procedura_buc
add constraint fk_consult_procedura_consult_buc
foreign key (id_consult) references consult_buc(id_consult);

--Verificare corectitudine
--Completitudine
select * 
from bdd_centralizat.consult_procedura
minus
(select a.* 
from consult_procedura_buc a, consult_buc b
where a.id_consult = b.id_consult
union all
select a.* 
from consult_procedura_prov@bd_prov a, consult_prov@bd_prov b
where a.id_consult = b.id_consult); -- gol, corect
--Reconstructie
--a inclus in b
select * from bdd_centralizat.consult_procedura
minus
(select * from consult_procedura_buc
union all
select * from consult_procedura_prov@bd_prov); --gol, corect
--b inclus in a
(select * from consult_procedura_buc
union all
select * from consult_procedura_prov@bd_prov)
minus
select * from bdd_centralizat.consult_procedura; --gol,corect
--Disjunctie
select * from consult_procedura_buc
intersect
select * from consult_procedura_prov@bd_prov; --gol,corect

--Creare tabel RETETA Bucuresti
create table reteta_buc
as select a.*
from bdd_centralizat.reteta a
where exists (select 1 from consult_buc b where a.id_reteta = b.id_reteta);

select * from reteta_buc;

alter table reteta_buc
add constraint pk_reteta_buc
primary key (id_reteta);

alter table consult_buc
add constraint fk_consult_reteta_buc
foreign key (id_reteta) references reteta_buc(id_reteta);

--Verificare corectitudine
--Completitudine
select * 
from bdd_centralizat.reteta
minus
(select a.* 
from reteta_buc a, consult_buc b
where a.id_reteta = b.id_reteta
union all
select a.* 
from reteta_prov@bd_prov a, consult_prov@bd_prov b
where a.id_reteta = b.id_reteta); -- gol, corect
--Reconstructie
--a inclus in b
select * from bdd_centralizat.reteta
minus
(select * from reteta_buc
union all
select * from reteta_prov@bd_prov); --gol, corect
--b inclus in a
(select * from reteta_buc
union all
select * from reteta_prov@bd_prov)
minus
select * from bdd_centralizat.reteta; --gol,corect
--Disjunctie
select * from reteta_buc
intersect
select * from reteta_prov@bd_prov; --gol,corect


--Creare tabel RETETA_MEDICAMENT Bucuresti
create table reteta_medicament_buc
as select a.*
from bdd_centralizat.reteta_medicament a
where exists (select 1 from reteta_buc b where a.id_reteta = b.id_reteta);

select * from reteta_medicament_buc;

alter table reteta_medicament_buc
add constraint pk_reteta_medicament_buc
primary key (id_reteta, id_medicament);

alter table reteta_medicament_buc
add constraint fk_reteta_medicament_reteta_buc
foreign key (id_reteta) references reteta_buc(id_reteta);

--Verificare corectitudine
--Completitudine
select * 
from bdd_centralizat.reteta_medicament
minus
(select a.* 
from reteta_medicament_buc a, reteta_buc b
where a.id_reteta = b.id_reteta
union all
select a.* 
from reteta_medicament_prov@bd_prov a, reteta_prov@bd_prov b
where a.id_reteta = b.id_reteta); -- gol, corect
--Reconstructie
--a inclus in b
select * from bdd_centralizat.reteta_medicament
minus
(select * from reteta_medicament_buc
union all
select * from reteta_medicament_prov@bd_prov); --gol, corect
--b inclus in a
(select * from reteta_medicament_buc
union all
select * from reteta_medicament_prov@bd_prov)
minus
select * from bdd_centralizat.reteta_medicament; --gol,corect
--Disjunctie
select * from reteta_medicament_buc
intersect
select * from reteta_medicament_prov@bd_prov; --gol,corect

--REPLICARE
--Replicare tabel SPECIALIZARE pe Bucuresti
create table specializare_buc_repl as 
select * from BDD_CENTRALIZAT.specializare;

alter table specializare_buc_repl
add constraint pk_specializare_buc_repl
primary key (id_specializare);

alter table doctor_contact_buc
add constraint fk_doctor_contact_specializare_buc
foreign key (id_specializare) references specializare_buc_repl(id_specializare);

--Replicare tabel DIAGNOSTIC pe Bucuresti
create table diagnostic_buc_repl as
select * from BDD_CENTRALIZAT.diagnostic;

alter table diagnostic_buc_repl 
add constraint pk_diagnostic_buc_repl 
primary key (id_diagnostic);

alter table consult_buc
add constraint fk_consult_diagnostic_buc
foreign key (id_diagnostic) references diagnostic_buc_repl(id_diagnostic);

--Replicare tabel MEDICAMENT pe Bucuresti
create table medicament_buc_repl as
select * from BDD_CENTRALIZAT.medicament;

alter table medicament_buc_repl 
add constraint pk_medicament_buc_repl 
primary key (id_medicament);

alter table reteta_medicament_buc
add constraint fk_reteta_medicament_medicament_buc
foreign key (id_medicament) references medicament_buc_repl(id_medicament);

--Replicare tabel PROCEDURA pe Bucuresti
create table procedura_buc_repl as
select * from BDD_CENTRALIZAT.procedura;

alter table procedura_buc_repl
add constraint pk_procedura_buc_repl
primary key (id_procedura);

alter table consult_procedura_buc
add constraint fk_consult_procedura_procedura_buc
foreign key (id_procedura) references procedura_buc_repl(id_procedura);

--Replicare tabel PACIENT pe Bucuresti
create table pacient_buc_repl as
select * from BDD_CENTRALIZAT.pacient;

alter table pacient_buc_repl
add constraint pk_pacient_buc_repl
primary key (id_pacient);

alter table consult_buc
add constraint fk_consult_pacient_buc
foreign key (id_pacient) references pacient_buc_repl(id_pacient);
