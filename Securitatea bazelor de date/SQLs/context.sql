--6. Aplica?iile pe baza de date ?i securitatea datelor
--Contextul aplicatiei
--Context care permite consulturilor sa se realizeze doar intre orele 8 si 20 si nu duminica
drop context aplicatie_medical_ctx;
create context aplicatie_medical_ctx using proced_aplicatie_ctx;

create or replace procedure proced_aplicatie_ctx is
  v_ora number(3);
  v_zi_sapt VARCHAR2(20);
begin
  select to_number(to_char(sysdate, 'hh24'))
  into v_ora
  from dual;
  
  select to_char(to_char(sysdate, 'DAY'))
  into v_zi_sapt
  from dual;
  
  dbms_output.put_line('Ora curenta: ' || v_ora);
  dbms_output.put_line('Ziua curenta: ' || v_zi_sapt);
  
  if v_ora < 8 or v_ora > 20 or lower(v_zi_sapt) = 'sunday' then
    dbms_output.put_line('Sunteti in afara orelor de program.');
    dbms_session.set_context('aplicatie_medical_ctx', 'ora_ziua_potrivita', 'nu');
  else 
    dbms_session.set_context('aplicatie_medical_ctx', 'ora_ziua_potrivita', 'da');
  end if;
end;
/

exec proced_aplicatie_ctx;

create or replace trigger tr_insert_consult
before insert on consult
for each row
declare
  v_poate varchar2(4);
begin
  v_poate := sys_context('aplicatie_medical_ctx', 'ora_ziua_potrivita');
  if (v_poate = 'nu') then
    dbms_output.put_line ('Nu aveti voie sa efectuati consulturi in afara orelor de program');
    RAISE_APPLICATION_ERROR(-20000,'Incercare consult in afara orelor de program');
  end if;
end;
/

create or replace trigger tr_after_logon
after logon on database
declare
  v_user varchar2(30);
begin
  v_user := sys_context('userenv', 'session_user');
  
  if lower(v_user) like '%admin%' or lower(v_user) like '%doctor%' then
    proced_aplicatie_ctx;
  end if;
end;
/

--SQL INJECTION
CREATE OR REPLACE PROCEDURE adresa_pacient(p_numeuser IN VARCHAR2, p_prenumeuser IN VARCHAR2, p_cnp IN VARCHAR2) AS
    v_oras VARCHAR2(100);
    v_judet VARCHAR2(100);
    v_strada VARCHAR2(100);
BEGIN
    EXECUTE IMMEDIATE
    'SELECT judet, oras, strada 
    FROM PACIENT p, ADRESA a
    WHERE p.id_adresa = a.id_adresa AND nume='''|| p_numeuser || ''' AND PRENUME= ''' || p_prenumeuser || ''' AND CNP = '''|| p_cnp
    INTO v_judet, v_oras, v_strada;

    dbms_output.put_line('Judet: ' || v_judet);
    dbms_output.put_line('Oras: ' || v_oras);
    dbms_output.put_line('Strada: ' || v_strada);
END;
/

select * from pacient;
begin
    adresa_pacient('Moldoveanu', 'Maria''--', '');
end;
/

CREATE OR REPLACE PROCEDURE adresa_pacient_safe(p_numeuser IN VARCHAR2, p_prenumeuser IN VARCHAR2, p_cnp IN VARCHAR2) AS
    v_oras VARCHAR2(100);
    v_judet VARCHAR2(100);
    v_strada VARCHAR2(100);
BEGIN
    SELECT judet, oras, strada 
    INTO v_judet, v_oras, v_strada
    FROM PACIENT p, ADRESA a
    WHERE p.id_adresa = a.id_adresa AND nume= p_numeuser AND prenume = p_prenumeuser  AND cnp = p_cnp;

    dbms_output.put_line('Judet: ' || v_judet);
    dbms_output.put_line('Oras: ' || v_oras);
    dbms_output.put_line('Strada: ' || v_strada);
END;
/

begin
    adresa_pacient_safe('Moldoveanu', 'Maria''--', '');
end;
/

