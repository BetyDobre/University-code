drop dimension adresa_dim;
CREATE DIMENSION adresa_dim
LEVEL spital IS (spitale.id_spital)
LEVEL cod_postal IS (spitale.cod_postal)
LEVEL strada IS (spitale.strada)
LEVEL oras IS (spitale.oras)
LEVEL judet is (spitale.judet)
HIERARCHY h_adresa (spital
                    CHILD OF cod_postal 
                    CHILD OF strada
                    CHILD OF oras
                    CHILD OF judet)
ATTRIBUTE spital DETERMINES(spitale.nume, spitale.contact);

EXECUTE DBMS_DIMENSION.VALIDATE_DIMENSION ('adresa_dim',FALSE, TRUE, 'st_id');
select * from dimension_exceptions where statement_id = 'st_id';

drop dimension doctori_dim;
CREATE DIMENSION doctori_dim 
LEVEL doctor IS (doctori.id_doctor)
LEVEL specializare IS (doctori.specializare)
LEVEL titulatura IS (doctori.categorie1)
LEVEL subdomeniu IS (doctori.categorie2)
LEVEL domeniu IS (doctori.categorie3)
HIERARCHY h_doctor (doctor
                    CHILD OF specializare
                    CHILD OF titulatura
                    CHILD OF subdomeniu
                    CHILD OF domeniu);

EXECUTE DBMS_DIMENSION.VALIDATE_DIMENSION ('doctori_dim',FALSE, TRUE, 'st_id2');
select * from dimension_exceptions where statement_id = 'st_id2';

