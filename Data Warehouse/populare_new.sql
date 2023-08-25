set serveroutput on;

create or replace function f_medicamente_list (id_ret IN NUMBER) return VARCHAR2 is
    rez VARCHAR2(500) := '';
    TYPE tab_med IS TABLE OF VARCHAR2(100); 
    lista_med tab_med;
begin
    select nume_medicament
    bulk collect into lista_med
    from medicament m, reteta_medicament rm
    where id_reteta = id_ret and m.id_medicament = rm.id_medicament;
    
    for i in lista_med.first..lista_med.last loop
        rez := rez || ', ' ||lista_med(i);
    end loop;
    
    return rez;
    
    exception
       when no_data_found then
         rez := '';
       when others then
         rez := '';
end;
/

create or replace function f_med_reteta_pret(id_ret IN NUMBER) return NUMBER is
    rez NUMBER(6,2) := 0;
    TYPE tab_med IS TABLE OF NUMBER(4,2); 
    pret_med tab_med;
begin
    select pret
    bulk collect into pret_med
    from medicament m, reteta_medicament rm
    where id_reteta = id_ret and m.id_medicament = rm.id_medicament;
    
    for i in pret_med.first..pret_med.last loop
        rez := rez + pret_med(i);
    end loop;
    
    return rez;
    
    exception
       when no_data_found then
         rez := 0;
       when others then
         rez := 0;
end;
/

create or replace function f_procedura_list (id_cons IN NUMBER) return VARCHAR2 is
    rez VARCHAR2(500):= '';
    TYPE tab_proc IS TABLE OF VARCHAR2(100); 
    lista_proc tab_proc;
begin
    select nume_procedura
    bulk collect into lista_proc
    from procedura p, consult_procedura cp
    where id_consult = id_cons and p.id_procedura = cp.id_procedura;
    
    for i in lista_proc.first..lista_proc.last loop
        rez := rez || ', ' ||lista_proc(i);
    end loop;
    
    return rez;
    
    exception
       when no_data_found then
         rez := '';
       when others then
         rez := '';
end;
/

create or replace function f_categorie_pacient (id_pac IN NUMBER) return VARCHAR2 is
    rez VARCHAR2(20) := '';
    v_data DATE;
    gender VARCHAR2(1);
    varsta NUMBER(3);
begin
    select data_nastere, sex 
    into v_data, gender
    from pacient
    where id_pacient = id_pac;
    
    select trunc(months_between(TRUNC(sysdate), v_data)/12)
    into varsta
    from dual;
    
    if (varsta < 18) then 
        rez := 'copil';
    elsif (varsta < 61) and (gender = 'F') then
        rez := 'adult';
    elsif (varsta < 65) and (gender = 'M') then
        rez := 'adult';
    else rez := 'pensionar';
    end if;
    
    return rez;
    
    exception
       when no_data_found then
         rez := '';
       when others then
         rez := '';
end;
/

create or replace function f_categorie_doctor (id_spec IN NUMBER, nr_ierarhie IN NUMBER) return VARCHAR2 is
    rez VARCHAR2(20) := '';  
begin
    select nume_specializare
    into rez
    from specializare
    start with id_specializare = id_spec
    connect by id_specializare = prior categorie
    OFFSET nr_ierarhie ROWS FETCH NEXT 1 ROWS ONLY;

    return rez;
    
    exception
       when no_data_found then
         rez := '';
       when others then
         rez := '';
end;
/ 

create or replace function f_consult_pret (id_cons IN NUMBER) return NUMBER is
    rez NUMBER(5) := 0;
    pret_cons NUMBER(5);
    discount_cons NUMBER(2);
    TYPE tab_proc IS TABLE OF NUMBER(5);
    pret_proc tab_proc;
begin
    select pret_procedura
    bulk collect into pret_proc
    from procedura p, consult_procedura cp
    where id_consult = id_cons and p.id_procedura = cp.id_procedura;
    
    for i in pret_proc.first..pret_proc.last loop
        rez := rez + pret_proc(i);
    end loop;
    
    select pret, discount 
    into pret_cons, discount_cons
    from consult
    where id_consult = id_cons;
    
    rez := rez + pret_cons;
    rez := rez - discount_cons/100 * rez;
    
    return rez;
    
    exception
       when no_data_found then
         rez := 0;
       when others then
         rez := 0;
end;
/

CREATE OR REPLACE PROCEDURE ADUCERE_DATE IS
    last_time DATE;
BEGIN
    --Inserare in tabela PACIENTI
    MERGE INTO pacienti p_dw USING pacient p ON (p_dw.id_pacient = p.id_pacient)
    WHEN MATCHED THEN
        UPDATE SET 
            nume = p.nume, prenume = p.prenume, 
            sex = p.sex, 
            telefon = p.telefon, 
            email = p.email, 
            categorie_pacient = f_categorie_pacient(p.id_pacient)
    WHEN NOT MATCHED THEN
        INSERT (id_pacient, nume, prenume, sex, data_nastere, telefon, email, categorie_pacient)
        VALUES (p.id_pacient, p.nume, p.prenume, p.sex, p.data_nastere, p.telefon, p.email, f_categorie_pacient(p.id_pacient));
    DBMS_OUTPUT.PUT_LINE('Nr. PACIENTI: ' || sql%Rowcount);
    
    --Inserare in tabela SPITALE
    MERGE INTO spitale s_dw USING (
        select id_spital, nume_spital, contact, nume_judet, nume_oras, strada, cod_postal
        from spital s, adresa a, judet j, oras o 
        where s.id_adresa = a.id_adresa and a.id_oras = o.id_oras and j.id_judet = o.id_judet
    ) s ON (s_dw.id_spital = s.id_spital)
    WHEN MATCHED THEN
        UPDATE SET
            nume = s.nume_spital,
            contact = s.contact,
            judet = s.nume_judet,
            oras = s.nume_oras,
            strada = s.strada,
            cod_postal = s.cod_postal
    WHEN NOT MATCHED THEN
        INSERT (id_spital, nume, contact, judet, oras, strada, cod_postal)
        VALUES (s.id_spital, s.nume_spital, s.contact, s.nume_judet, s.nume_oras, s.strada, s.cod_postal);
    DBMS_OUTPUT.PUT_LINE('Nr. SPITALE: ' || sql%Rowcount);
    
    --Inserare in tabela DOCTORI
    MERGE INTO doctori d_dw USING (
        select id_doctor, nume, f_categorie_doctor(id_specializare, 0) AS specializare, f_categorie_doctor(id_specializare, 1) AS categorie1, f_categorie_doctor(id_specializare, 2) AS categorie2, f_categorie_doctor(id_specializare, 3) AS categorie3
        from doctor
    ) d ON (d_dw.id_doctor = d.id_doctor)
    WHEN MATCHED THEN
        UPDATE SET
            nume = d.nume,
            specializare = d.specializare,
            categorie1 = d.categorie1,
            categorie2 = d.categorie2,
            categorie3 = d.categorie3
    WHEN NOT MATCHED THEN
        INSERT (id_doctor, nume, specializare, categorie1, categorie2, categorie3)
        VALUES (d.id_doctor, d.nume, d.specializare, d.categorie1, d.categorie2, d.categorie3);
    DBMS_OUTPUT.PUT_LINE('Nr. DOCTORI: ' || sql%Rowcount);
    
    --Insert in tabela CONSULTATII
    MERGE INTO consultatii c_dw USING (
        select id_consult, denumire_diagnostic, f_procedura_list(id_consult) AS lista_proceduri, detalii, pret AS pret_consult, discount
        from consult c, diagnostic d
        where c.id_diagnostic = d.id_diagnostic
    ) c ON (c_dw.id_consult = c.id_consult)
    -- datele vechi nu ar trebui modificate
    WHEN NOT MATCHED THEN
        INSERT (id_consult, denumire_diagnostic, lista_proceduri, detalii, pret_consult, discount)
        VALUES (c.id_consult, c.denumire_diagnostic, c.lista_proceduri, c.detalii, c.pret_consult, c.discount);
    DBMS_OUTPUT.PUT_LINE('Nr. CONSULTATII: ' || sql%Rowcount);
    
    --Insert in tabela RETETE
    MERGE INTO retete r_dw USING (    
        select r.id_reteta, descriere, f_medicamente_list(r.id_reteta) AS lista_medicamente, f_med_reteta_pret(r.id_reteta) AS pret_reteta
        from reteta r, reteta_medicament rm
        where r.id_reteta = rm.id_reteta
    ) r ON (r_dw.id_reteta = r.id_reteta)
    -- datele vechi nu ar trebui modificate
    WHEN NOT MATCHED THEN
        INSERT (id_reteta, descriere, lista_medicamente, pret_reteta)
        VALUES (r.id_reteta, r.descriere, r.lista_medicamente, r.pret_reteta);
    DBMS_OUTPUT.PUT_LINE('Nr. RETETE: ' || sql%Rowcount);
    
    SELECT TRUNC(max(id_timp)) into last_time FROM TIMP;
    MERGE INTO TIMP t_dw USING (    
        SELECT 
            time AS id_timp,
            TO_CHAR(time, 'yyyy') AS an,
            CASE WHEN TO_CHAR(time, 'MM') <= 6 THEN '1' ELSE '2' END AS semestru,
            CASE WHEN TO_CHAR(time, 'MM') <= 6 THEN 1 ELSE 2 END AS semestru_nr,
            CASE WHEN TO_CHAR(time, 'MM') <= 4 THEN '1' WHEN TO_CHAR(time, 'MM') >=9 THEN '3' ELSE '2' END AS trimestru,
            CASE WHEN TO_CHAR(time, 'MM') <= 4 THEN '1' WHEN TO_CHAR(time, 'MM') >=9 THEN '3' ELSE '2' END AS trimestru_nr,
            TO_CHAR(time, 'MONTH') AS luna,
            TO_CHAR(time, 'MM') AS luna_nr,
            TO_CHAR(time, 'WW') AS saptamana_an,
            TO_CHAR(time, 'WW') AS saptamana_an_nr,
            TO_CHAR(time, 'W') AS saptamana_luna,
            TO_CHAR(time, 'W') AS saptamana_luna_nr,
            TO_CHAR(time, 'DDD') AS zi_an,
            TO_CHAR(time, 'DDD') AS zi_an_nr,
            TO_CHAR(time, 'DD') AS zi_luna,
            TO_CHAR(time, 'DD') AS zi_luna_nr,
            TO_CHAR(time, 'D') AS zi_saptamana,
            TO_CHAR(time, 'DAY') AS zi_nume,
            CASE WHEN TO_CHAR(time, 'D') = 1 OR TO_CHAR(time, 'D') = 7 THEN '1' ELSE '0' END AS weekend,
            CASE WHEN TO_CHAR(TO_DATE(TO_CHAR(time, 'yyyy') || '/03/01', 'yyyy/mm/dd'), 'DDD') = '061' THEN 1 ELSE 0 END AS an_bisect,
            time AS data 
        FROM (
            SELECT DISTINCT TRUNC(data_consult) AS time
            FROM consult
            WHERE last_time IS NULL OR data_consult > last_time
            ORDER BY time
        )
    ) t ON (t_dw.id_timp = t.id_timp)
    -- datele vechi nu ar trebui modificate
    WHEN NOT MATCHED THEN
        INSERT (id_timp, an, semestru, semestru_nr, trimestru, trimestru_nr, luna, luna_nr, saptamana_an, saptamana_an_nr, saptamana_luna, saptamana_luna_nr, zi_an, zi_an_nr, zi_luna, zi_luna_nr, zi_saptamana, zi_nume, weekend, an_bisect, data)
        VALUES (t.id_timp, t.an, t.semestru, t.semestru_nr, t.trimestru, t.trimestru_nr, t.luna, t.luna_nr, t.saptamana_an, t.saptamana_an_nr, t.saptamana_luna, t.saptamana_luna_nr, t.zi_an, t.zi_an_nr, t.zi_luna, t.zi_luna_nr, t.zi_saptamana, t.zi_nume, t.weekend, t.an_bisect, t.data);
    DBMS_OUTPUT.PUT_LINE('Nr. Timpi: ' || sql%Rowcount);
    
    --Insert in tabel COSTURI
    MERGE INTO costuri c_dw USING (
        select c.data_consult AS timp_id, c.id_doctor AS doctor_id, d.id_spital AS spital_id, c.id_consult AS consult_id, c.id_reteta AS reteta_id, c.id_pacient AS pacient_id, f_consult_pret(c.id_consult) AS cost_consult, f_med_reteta_pret(c.id_reteta) AS cost_tratament
        from consult c, doctor d
        where c.id_doctor = d.id_doctor
    ) c ON (c_dw.timp_id = c.timp_id AND c_dw.doctor_id = c.doctor_id AND c_dw.spital_id = c.spital_id AND c_dw.consult_id = c.consult_id AND c_dw.reteta_id = c.reteta_id AND c_dw.pacient_id = c.pacient_id)
    -- datele vechi nu ar trebui modificate
    WHEN NOT MATCHED THEN
        INSERT (timp_id, doctor_id, spital_id, consult_id, reteta_id, pacient_id, cost_consult, cost_tratament)
        VALUES (c.timp_id, c.doctor_id, c.spital_id, c.consult_id, c.reteta_id, c.pacient_id, c.cost_consult, c.cost_tratament);
    DBMS_OUTPUT.PUT_LINE('Nr. COSTURI: ' || sql%Rowcount);
END;
/

EXEC ADUCERE_DATE();

