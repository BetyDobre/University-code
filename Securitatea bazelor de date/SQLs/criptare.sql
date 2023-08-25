set serveroutput on;

--2. Criptarea datelor
drop sequence secv_id_cheie;
create sequence secv_id_cheie start with 1 increment by 1;
--cnp, telefon, email, adresa
DROP TABLE TABEL_CHEI;
CREATE TABLE TABEL_CHEI(
    id_cheie NUMBER,
    cheie RAW(16) NOT NULL,
    id_inregistrare NUMBER(4) NOT NULL,
    nume_tabel VARCHAR2(30) NOT NULL
);

--Procedura criptare date 
CREATE OR REPLACE PROCEDURE ENCRYPT_DATA(id_cautat IN NUMBER, info IN VARCHAR2, tabel IN VARCHAR2, rezultat OUT VARCHAR2) AS
    raw_cheie raw(16);
    raw_info raw(200);
    
    mod_operare pls_integer;
    
    contor INTEGER := 0;
BEGIN
    select count(*) into contor from system.tabel_chei where id_inregistrare = id_cautat and lower(nume_tabel) = lower(tabel);
    
    if contor > 0 then
        select cheie into raw_cheie
        from system.tabel_chei
        where id_inregistrare = id_cautat;
    else 
        raw_cheie := dbms_crypto.randombytes(16);
        insert into system.tabel_chei values (secv_id_cheie.nextval, raw_cheie,  id_cautat, tabel);
    end if;
    
    mod_operare := dbms_crypto.encrypt_aes128 + dbms_crypto.pad_pkcs5 + dbms_crypto.chain_cbc;
    raw_info := utl_i18n.string_to_raw(info, 'AL32UTF8');
    
    rezultat:= dbms_crypto.encrypt(raw_info, mod_operare, raw_cheie);
END;
/

--Creare trigger criptare pacienti
CREATE OR REPLACE TRIGGER encrypt_data_pacienti 
BEFORE INSERT ON PACIENT
FOR EACH ROW
DECLARE
    encrypted_data VARCHAR2(200);
BEGIN
    ENCRYPT_DATA(:NEW.id_pacient, :NEW.cnp, 'pacient', encrypted_data);
    :NEW.cnp := encrypted_data;
    
    ENCRYPT_DATA(:NEW.id_pacient, :NEW.telefon, 'pacient',encrypted_data);
    :NEW.telefon := encrypted_data;
    
    ENCRYPT_DATA(:NEW.id_pacient, :NEW.email, 'pacient', encrypted_data);
    :NEW.email := encrypted_data;
END;
/

--Creare trigger CONSULT 
CREATE OR REPLACE TRIGGER encrypt_data_consult 
BEFORE INSERT ON CONSULT
FOR EACH ROW
DECLARE
    encrypted_data VARCHAR2(200);
BEGIN
    ENCRYPT_DATA(:NEW.id_consult, :NEW.diagnostic, 'consult', encrypted_data);
    :NEW.diagnostic := encrypted_data;
END;
/

--Inseram in pacient
INSERT INTO PACIENT VALUES(11, 1, '123456789098', 'Dinu', 'Ionut', 'M', TO_DATE('19-04-2000','DD-MM-YYYY'),'0735271864','ionutd@gmail.com');

select * from pacient;
select * from tabel_chei where nume_tabel = 'pacient' and id_inregistrare = 11;

delete from pacient where id_pacient = 11;
delete from tabel_chei where id_inregistrare = 11;

--Procedura decriptarea datelor
CREATE OR REPLACE PROCEDURE DECRYPT_DATA(id_cautat IN NUMBER, tabel IN VARCHAR2)
AS
    raw_cheie raw(16);
    mod_operare PLS_INTEGER;
    cnp_criptat VARCHAR2(100);
    telefon_criptat VARCHAR2(100);
    email_criptat VARCHAR2(100);
    diagnostic_criptat VARCHAR2(100);
    rezultat_cnp raw(200);
    rezultat_telefon raw(200);
    rezultat_email raw(200);
    rezultat_diagnostic raw(200);
BEGIN
    select cheie
    into raw_cheie
    from system.tabel_chei 
    where id_inregistrare = id_cautat and lower(nume_tabel) = lower(tabel);
    
    DBMS_OUTPUT.PUT_LINE('Cheie ' || raw_cheie);
    
    mod_operare := dbms_crypto.encrypt_aes128 + dbms_crypto.pad_pkcs5 + dbms_crypto.chain_cbc;
    
    if (lower(tabel) = 'pacient') then
        select cnp, telefon, email 
        into cnp_criptat, telefon_criptat, email_criptat 
        from system.pacient where id_pacient = id_cautat;
    

        rezultat_cnp := dbms_crypto.decrypt(cnp_criptat, mod_operare, raw_cheie);
        rezultat_telefon := dbms_crypto.decrypt(telefon_criptat, mod_operare, raw_cheie);
        rezultat_email := dbms_crypto.decrypt(email_criptat, mod_operare, raw_cheie);
    
        DBMS_OUTPUT.PUT_LINE('CNP: ' || utl_i18n.raw_to_char(rezultat_cnp, 'AL32UTF8'));
        DBMS_OUTPUT.PUT_LINE('TELEFON: ' || utl_i18n.raw_to_char(rezultat_telefon, 'AL32UTF8'));
        DBMS_OUTPUT.PUT_LINE('EMAIL: ' || utl_i18n.raw_to_char(rezultat_email, 'AL32UTF8'));
    elsif (lower(tabel) = 'consult') then
            select diagnostic 
            into diagnostic_criptat 
            from system.consult where id_consult = id_cautat;
        
            rezultat_diagnostic := dbms_crypto.decrypt(diagnostic_criptat, mod_operare, raw_cheie);
        
            DBMS_OUTPUT.PUT_LINE('Diagnostic: ' || utl_i18n.raw_to_char(rezultat_diagnostic, 'AL32UTF8'));
    else
        DBMS_OUTPUT.PUT_LINE('Nu exista rezultate pentru acest tabel');
    end if;
END;
/


INSERT INTO PACIENT VALUES(10, 1, '123456789098', 'Dinu', 'Ionut', 'M', TO_DATE('19-04-2000','DD-MM-YYYY'),'0735271864','ionutd@gmail.com');
begin
    decrypt_data(10, 'pacient');
end;
/

