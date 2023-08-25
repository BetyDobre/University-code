--4. b)Crearea userilor din baza de date
alter session set container = ORCLPDB;
--Crearea adminului
create user administrator identified by administrator password expire;
grant create session to administrator;

--Crearea pacientilor
create user pacient1 identified by pacient1 password expire;
grant create session to pacient1;

create user pacient2 identified by pacient2 password expire;
grant create session to pacient2;

create user pacient3 identified by pacient3 password expire;
grant create session to pacient3;

create user pacient4 identified by pacient4 password expire;
grant create session to pacient4;

create user pacient5 identified by pacient5 password expire;
grant create session to pacient5;

create user pacient6 identified by pacient6 password expire;
grant create session to pacient6;

create user pacient7 identified by pacient7 password expire;
grant create session to pacient7;

create user pacient8 identified by pacient8 password expire;
grant create session to pacient8;

create user pacient9 identified by pacient9 password expire;
grant create session to pacient9;

create user pacient10 identified by pacient10 password expire;
grant create session to pacient10;

--Createa doctorilor
create user doctor1 identified by doctor1 password expire;
grant create session to doctor1;

create user doctor2 identified by doctor2 password expire;
grant create session to doctor2;

create user doctor3 identified by doctor3 password expire;
grant create session to doctor3;

create user doctor4 identified by doctor4 password expire;
grant create session to doctor4;

--Crearea asistentelor medicale
create user asistenta1 identified by asistenta1 password expire;
grant create session to asistenta1;

create user asistenta2 identified by asistenta2 password expire;
grant create session to asistenta2;

--Creare secretarelor
create user secretara1 identified by secretara1 password expire;
grant create session to secretara1;
--grant execute on dbms_crypto to secretara1;

create user secretara2 identified by secretara2 password expire;
grant create session to secretara2;
--grant execute on dbms_crypto to secretara2;

--Crearea personalului financiar
create user contabil identified by contabil password expire;
grant create session to contabil;

-- Vizualizarea user-urilor
SELECT USERNAME, AUTHENTICATION_TYPE, ACCOUNT_STATUS, CREATED,
EXPIRY_DATE
FROM DBA_USERS
ORDER BY CREATED desc;

--Alocare cote spatiu de stocare 
SELECT tablespace_name, ROUND(SUM(bytes) / 1048576) TotalSpace
FROM dba_data_files
GROUP BY tablespace_name;

alter user administrator quota unlimited on users;
alter user doctor1 quota 10M on users;
alter user doctor2 quota 10M on users;
alter user doctor3 quota 10M on users;
alter user doctor4 quota 10M on users;
alter user pacient1 quota 5M on users;
alter user pacient2 quota 5M on users;
alter user pacient3 quota 5M on users;
alter user pacient4 quota 5M on users;
alter user pacient5 quota 5M on users;
alter user pacient6 quota 5M on users;
alter user pacient7 quota 5M on users;
alter user pacient8 quota 5M on users;
alter user pacient9 quota 5M on users;
alter user pacient10 quota 5M on users;
alter user secretara1 quota 0M on users;
alter user secretara2 quota 0M on users;
alter user contabil quota 0M on users;

select * from dba_ts_quotas;

--Creare profiluri utilizatori pentru doctori si pacienti
create profile profil_doctor limit
     cpu_per_call 6000
     sessions_per_user 1
     password_life_time 14
     failed_login_attempts 3;

create profile profil_pacient limit
     cpu_per_call 6000
     sessions_per_user 1
     password_life_time 14
     failed_login_attempts 3;
     
     
 select * from dba_profiles
 where lower(profile) LIKE 'profil_%';
