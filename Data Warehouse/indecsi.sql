drop index ind_bmp_doctori_titulatura;
drop index ind_bmp_pacienti_cat;
drop index ind_bmp_consult_disc;

CREATE BITMAP INDEX ind_bmp_doctori_titulatura
ON doctori(categorie2);

analyze index ind_bmp_doctori_titulatura compute statistics;

CREATE BITMAP INDEX ind_bmp_pacienti_cat
ON pacienti(categorie_pacient);

analyze index ind_bmp_pacienti_cat compute statistics;

CREATE BITMAP INDEX ind_bmp_consult_disc
ON consultatii(discount);

analyze index ind_bmp_consult_disc compute statistics;

------------------------------
CREATE BITMAP INDEX ind_bmp_join_tit_f_costuri
ON costuri(categorie2) 
FROM   costuri c, doctori d
WHERE  c.doctor_id = d.id_doctor local;
drop index ind_bmp_join_tit_f_costuri;

CREATE BITMAP INDEX ind_bmp_join_pens_f_costuri
ON costuri(categorie_pacient) 
FROM   costuri c, pacienti d
WHERE  c.pacient_id = d.id_pacient local;
drop index ind_bmp_join_pens_f_costuri;



EXPLAIN PLAN 
SET STATEMENT_ID = 's1_index_cerere' FOR
select
    (select count (*) from doctori where lower(categorie2) = 'rezident') nr_doctori,
    (select avg(pret_consult) from consultatii where discount > 25) pret_mediu,
    (select 
        sum (cost_tratament) 
        from pacienti p, costuri c, timp t
        where categorie_pacient = 'pensionar' 
        and c.pacient_id = p.id_pacient
        and c.timp_id = t.id_timp
        and t.luna = 12
        and t.an = 2021) cost
from dual;

SELECT plan_table_output 
FROM
table(dbms_xplan.display('plan_table','s1_index_cerere','serial'));