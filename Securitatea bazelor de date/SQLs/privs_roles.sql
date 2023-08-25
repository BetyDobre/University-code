--5. Privilegii si roluri
--Privilegii adminstrator
grant select, insert, update, delete on clinica to administrator;
grant insert, update, delete on adresa to administrator;
grant insert, update on procedura to administrator;
grant insert, update on medicament to administrator;
grant select, insert, update, delete on personal_medical to administrator;
grant select, insert, update, delete on departament to administrator;
grant insert, update, delete on functie to administrator;
grant create any table to administrator;

--Privilegii secretare
grant select, insert, update, delete on pacient to secretara1;
grant select on clinica to secretara1;
grant select, insert, update on adresa to secretara1;
grant select on consult to secretara1;
grant select on departament to secretara1;
grant select on functie to secretara1;

grant select, insert, update, delete on pacient to secretara2;
grant select on clinica to secretara2;
grant select, insert, update on adresa to secretara2;
grant select on consult to secretara2;
grant select on departament to secretara2;
grant select on functie to secretara2;

--Privilegii contabil
grant select on procedura to contabil;
grant select on consult_procedura to contabil;
grant select on medicament to contabil;
grant select on consult_medicament to contabil;

--Privilegii pentru pacienti, creare rol pacient
create role rol_pacient;
grant select any table to rol_pacient;

grant rol_pacient to pacient1;
grant rol_pacient to pacient2;
grant rol_pacient to pacient3;
grant rol_pacient to pacient4;
grant rol_pacient to pacient5;
grant rol_pacient to pacient6;
grant rol_pacient to pacient7;
grant rol_pacient to pacient8;
grant rol_pacient to pacient9;
grant rol_pacient to pacient10;

--Creare rol pentru asistente
create role rol_asistenta;
grant select on pacient to rol_asistenta;
grant select on clinica to rol_asistenta;
grant select on adresa to rol_asistenta;
grant select on consult to rol_asistenta;
grant select on procedura to rol_asistenta;
grant select, insert, update on consult_procedura to rol_asistenta;
grant select on medicament to rol_asistenta;
grant select on consult_medicament to rol_asistenta;
grant select on personal_medical to rol_asistenta;
grant select on departament to rol_asistenta;
grant select on functie to rol_asistenta;

grant rol_asistenta to asistenta1;
grant rol_asistenta to asistenta2;

--Creare rol pentru doctori
create role rol_doctor;
grant select on pacient to rol_doctor;
grant select on clinica to rol_doctor;
grant select on adresa to rol_doctor;
grant select, insert, update on consult to rol_doctor;
grant select on procedura to rol_doctor;
grant select, insert, update on consult_procedura to rol_doctor;
grant select on medicament to rol_doctor;
grant select, insert, update on consult_medicament to rol_doctor;
grant select on personal_medical to rol_doctor;
grant select on departament to rol_doctor;
grant select on functie to rol_doctor;

grant rol_doctor to doctor1;
grant rol_doctor to doctor2;
grant rol_doctor to doctor3;
grant rol_doctor to doctor4;

grant execute on decrypt_data to secretara1;
grant execute on decrypt_data to secretara2;
grant execute on decrypt_data to rol_doctor;

--Privilegii obiect
--Procedura pentru a calcula pretul unui consult din ziua respectiva pentru un anumit pacient la sfarsitul acestuia
CREATE OR REPLACE FUNCTION cost_consult (id_pac IN NUMBER) RETURN NUMBER AS 
    p_consult NUMBER(7,2) := 0;
    id_con NUMBER(4);
    p_proc NUMBER(7,2) := 0;
    suma NUMBER := 0;
BEGIN
    select pret_consult, id_consult
    into p_consult, id_con
    from system.consult
    where id_pacient = id_pac and data_consult like sysdate;

    select sum(pret)
    into p_proc
    from system.consult_procedura cp, system.procedura p
    where id_consult = id_con and cp.id_procedura = p.id_procedura;
    
    suma := p_proc + p_consult;
    return suma;
END;
/

grant execute on cost_consult to secretara1;
grant execute on cost_consult to secretara2;

update consult set data_consult = sysdate where id_pacient = 1 and id_consult = 1;
declare 
    suma NUMBER;
begin
    suma := cost_consult(1);
    dbms_output.put_line(suma);
end;
/

--Ierarhii de privilegii
--Presupunem ca la un moment dat o asistenta a avut voie sa realizeze un consult in lipsa unui doctor, dar acest drept i-a fost revocat
grant insert on consult to asistenta1;
grant insert on consult to rol_asistenta;
grant rol_asistenta to asistenta1;
revoke insert on consult from rol_asistenta;