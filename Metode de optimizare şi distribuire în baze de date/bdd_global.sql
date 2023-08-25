grant all privileges to bdd_global;

--inseram o noua valoare
insert into doctor_all
values(46, 7, 20, 'Ion', 'Mihai', 'ion.mihai@gmail.com', 3000);

commit;

-- va fi inserat/sters/updatat in fragmentul corespunzator
select * from doctor_all;
select * from doctor_salarizare;
SELECT * FROM bdd.doctor_contact_buc;
select * from bdd.doctor_contact_prov@bd_prov;

update doctor_all set id_spital = 3 where id_doctor = 46;
update doctor_all set salariu = 8000 where id_doctor = 42;
delete from doctor_all where id_doctor = 45;

insert into bdd.medicament_buc_repl values (95, 'antibiotic', 10);
commit;
select * from medicament_prov_repl@bd_prov;
