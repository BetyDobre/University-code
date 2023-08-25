set serveroutput on;

--3. Auditarea activit??ilor asupra bazei de date
--Auditare standard
show con_name;
alter session set container = cdb$root;
show parameter audit_trail;

alter system set audit_trail = db,extended scope = spfile;
show parameter audit_trail;

delete from sys.aud$ where obj$name in ('consult', 'consult_procedura', 'consult_procedura', 'medicament', 'pacient', 'adresa', 'functie', 'departament', 'personal_medical', 'procedura', 'clinica');

-- alter pluggable database orclpdb open; din sys
alter session set container = orclpdb;

audit select table;

select oras from adresa
where id_adresa = 1;

select obj$name, sqltext, userid, ntimestamp# 
from SYS.aud$
where lower(obj$name) = 'adresa'
order by ntimestamp# desc;

audit select, insert, update, delete on pacient;

select object_name, object_type, owner, sel, ins, upd, del
from dba_obj_audit_opts
where lower(object_name) = 'pacient';

noaudit select table;
--din sys:
--alter pluggable database orclpdb close immediate;
--alter pluggable database orclpdb open;


--Triggeri de auditare
--a) Trigger ca atunci cand se modifica pretul unei proceduri sa fie anuntati pacientii
drop sequence secv_aud_proc;
create sequence secv_aud_proc start with 1 increment by 1;

DROP TABLE TAB_AUDIT_PROC_PRET;
CREATE TABLE TAB_AUDIT_PROC_PRET(
    id_secv NUMBER(4) PRIMARY KEY,
    user_ VARCHAR2(20),
    session_ NUMBER(10),
    host_ VARCHAR2(100),
    timp DATE,
    nume_proc VARCHAR2(100),
    pret_nou_proc NUMBER(7,2),
    pret_vechi NUMBER(7,2)
);

CREATE OR REPLACE TRIGGER T_AUDIT_PRET_PROC
AFTER UPDATE OF pret ON procedura
FOR EACH ROW
BEGIN 
    insert into tab_audit_proc_pret
    values(secv_aud_proc.nextval,sys_context('userenv', 'session_user'), sys_context('userenv', 'sessionid'), sys_context('userenv','host'), sysdate, :NEW.nume_procedura, :NEW.pret, :OLD.pret);
END;
/

UPDATE PROCEDURA
SET pret = 200
where id_procedura = 1;

SELECT * FROM TAB_AUDIT_PROC_PRET;

--b) Trigger pentru schimbarea tratamentului pacientului
drop sequence secv_aud_med;
create sequence secv_aud_med start with 1 increment by 1;

drop table TAB_AUDIT_MEDICAMENTE;
CREATE TABLE TAB_AUDIT_MEDICAMENTE(
    id_secv NUMBER(4) PRIMARY KEY,
    user_ VARCHAR2(20),
    session_ NUMBER(10),
    host_ VARCHAR2(100),
    timp DATE,
    nume_med VARCHAR2(50),
    cant_veche NUMBER(5),
    cant_noua NUMBER(5),
    id_pac NUMBER(4),
    id_doc NUMBER(4)
);

CREATE OR REPLACE TRIGGER t_audit_medicamente_cant_upd
AFTER UPDATE ON consult_medicament
FOR EACH ROW
DECLARE
    id_pac NUMBER(4);
    id_doc NUMBER(4);
    nume_med VARCHAR2(50);
BEGIN
    select id_pacient, id_personal, nume_medicament
    into id_pac, id_doc, nume_med
    from consult c, medicament m
    where c.id_consult = :NEW.id_consult and m.id_medicament = :NEW.id_medicament;
    
    insert into tab_audit_medicamente
    values(secv_aud_med.nextval, sys_context('userenv', 'session_user'), sys_context('userenv', 'sessionid'), sys_context('userenv','host'), sysdate, nume_med, :NEW.cantitate, :OLD.cantitate, id_pac, id_doc);
END;
/

UPDATE CONSULT_MEDICAMENT
SET cantitate = 2
WHERE id_consult = 1 and id_medicament = 1;

select * from tab_audit_medicamente;

--c) Trigger ca atunci cand un doctor isi schimba functia verificam daca si-a schimbat si departamentul 
drop sequence secv_aud_doct;
create sequence secv_aud_doct start with 1 increment by 1;
drop table TAB_AUDIT_PERS_MEDICAL;
CREATE TABLE TAB_AUDIT_PERS_MEDICAL(
    id_secv NUMBER(4) PRIMARY KEY,
    user_ VARCHAR2(20),
    session_ NUMBER(10),
    host_ VARCHAR2(100),
    timp DATE,
    nume_pers VARCHAR2(50),
    functie_veche NUMBER(4),
    functie_noua NUMBER(4),
    id_depart NUMBER(4),
    id_depart_nou NUMBER(4)
);

CREATE OR REPLACE TRIGGER t_audit_pers_medical
AFTER UPDATE OF id_functie ON personal_medical
FOR EACH ROW
BEGIN
    if (:NEW.id_departament = :OLD.id_departament) then
        insert into TAB_AUDIT_PERS_MEDICAL(id_secv, user_, session_, host_, timp, nume_pers, functie_veche, functie_noua, id_depart)
        values(secv_aud_med.nextval, sys_context('userenv', 'session_user'), sys_context('userenv', 'sessionid'), sys_context('userenv','host'), sysdate, :NEW.nume, :OLD.id_functie, :NEW.id_functie, :OLD.id_departament);
    else 
        insert into TAB_AUDIT_PERS_MEDICAL
        values(secv_aud_med.nextval, sys_context('userenv', 'session_user'), sys_context('userenv', 'sessionid'), sys_context('userenv','host'), sysdate, :NEW.nume, :OLD.id_functie, :NEW.id_functie, :OLD.id_departament, :NEW.id_departament);
    end if;
END;
/

UPDATE PERSONAL_MEDICAL
SET id_functie = 2
WHERE id_personal = 1;

UPDATE PERSONAL_MEDICAL
SET id_functie = 2, id_departament = 1
WHERE id_personal = 1;

select * from tab_audit_pers_medical;

delete from tab_audit_pers_medical;

--Politici de auditare
--Crearea unei politici de auditare pe tabelul PACIENT
begin
DBMS_FGA.DROP_POLICY(
   object_schema  => 'system', 
   object_name    =>'PACIENT', 
   policy_name    =>'policy_pacient');
end;
/

create or replace procedure proc_audit_alert(object_schema varchar2, object_name varchar2, 
                             policy_name varchar2)
as
begin
  dbms_output.put_line('Incercare modificare informatii pacient');
end;
/

CREATE OR REPLACE PROCEDURE proc_audit_pac as 
BEGIN
    dbms_fga.add_policy(
        object_schema => 'system',
        object_name => 'PACIENT',
        policy_name => 'policy_pacient',
        enable => false,
        statement_types => 'UPDATE',
        handler_module => 'PROC_AUDIT_ALERT');
END;
/

execute proc_audit_pac;

select object_name, object_schema, policy_name, enabled 
from all_audit_policies;

begin
 dbms_fga.enable_policy(object_schema => 'SYSTEM',
 object_name => 'PACIENT',
 policy_name => 'policy_pacient');
end;
/

update pacient
set nume = 'Popescu'
where id_pacient = 1;

select db_user, userhost, policy_name, timestamp, sql_text 
from dba_fga_audit_trail; 