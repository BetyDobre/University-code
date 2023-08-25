--Inseram in pacient
INSERT INTO PACIENT VALUES(1, 1, '1254173871953', 'Moldoveanu', 'Maria', 'F', TO_DATE('23-06-1994','DD-MM-YYYY'),'0723657512','moldo.maria@gmail.com');

INSERT INTO FUNCTIE VALUES(1, 'Doctor', 'Ortopedie');
INSERT INTO FUNCTIE VALUES(2, 'Specialist', 'Ortopedie');
INSERT INTO CLINICA VALUES(1, 1, 'Sanamed', null);
INSERT INTO DEPARTAMENT VALUES (1, 1, 'Ortopedie si traumatologie', 1);
INSERT INTO DEPARTAMENT VALUES (2, 1, 'Cercetare',1);
INSERT INTO PERSONAL_MEDICAL VALUES(1, 1, 1, 'Zaharescu Lucian', TO_DATE('19-01-2008', 'dd-MM-yyyy'));
INSERT INTO consult VALUES(1, 1, 1, 'Entorsa', TO_DATE(sysdate, 'dd-MM-yyyy'), 300, 'entorsa sportiva');
INSERT INTO PROCEDURA VALUES(1, 'Radiografie', 100, 's-a constatat entorsa', 'DA');
INSERT INTO PROCEDURA VALUES(2, 'Masaj', 150, 'pentru vanatai', 'NU');
INSERT INTO CONSULT_PROCEDURA VALUES(1,1);
INSERT INTO CONSULT_PROCEDURA VALUES(1,2);

INSERT INTO MEDICAMENT VALUES (1, 'BENGAY', 40, 'dimineata si seara pe glezna');
INSERT INTO CONSULT_MEDICAMENT VALUES(1,1,1);
commit;

INSERT INTO consult VALUES(2, 1, 1, 'Entorsa', TO_DATE(sysdate, 'dd-MM-yyyy'), 300, 'entorsa sportiva');
INSERT INTO CONSULT_PROCEDURA VALUES(2,1);
INSERT INTO CONSULT_PROCEDURA VALUES(2,2);

select * from consult;