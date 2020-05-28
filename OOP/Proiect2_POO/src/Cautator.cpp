#include "Cautator.h"

// constructorul default al clasei de baza
Cautator::Cautator()
{
    //ctor
}

// constructorul cu parametri pentru clasa Cautator
Cautator::Cautator(int linie, int coloana): poz_linie(linie), poz_coloana(coloana)
{
    //ctor
}


// returneaza printr-o variabila de tip bool daca un jucator mai este ramas in joc sau nu
bool Cautator::getBlocat()
{
    return blocat;
}

// seteaza variabila de tip bool "blocat" cu 1
void Cautator::setBlocat()
{
    blocat = 1;
}

// constructorul cu parametri al cautatorului de tip 1
caut_tip1::caut_tip1(int linie, int coloana) : Cautator(linie, coloana)
{
    blocat = 0; //jucatorul mai este in joc sau nu
}

// constructorul cu parametri al cautatorului de tip 2
caut_tip2::caut_tip2(int linie, int coloana) : Cautator(linie, coloana)
{
    blocat = 0;
}

// constructorul cu parametri al cautatorului de tip 3
caut_tip3::caut_tip3(int linie, int coloana) : Cautator(linie, coloana)
{
    blocat = 0; //jucatorul mai este in joc sau nu
}

// constructorul cu parametri al cautatorului de tip 4
caut_tip4::caut_tip4(int linie, int coloana) : Cautator(linie, coloana)
{
    blocat = 0; //jucatorul mai este in joc sau nu
}

// constructorul de copiere pentru cautatorul 1
caut_tip1::caut_tip1(const caut_tip1 &C)
{
    poz_coloana = C.poz_coloana;
    poz_linie = C.poz_linie;
    blocat = C.blocat;
}

// constructorul de copiere pentru cautatorul 2
caut_tip2::caut_tip2(const caut_tip2 &C)
{
    poz_coloana = C.poz_coloana;
    poz_linie = C.poz_linie;
    blocat = C.blocat;
}

// constructorul de copiere pentru cautatorul 3
caut_tip3::caut_tip3(const caut_tip3 &C)
{
    poz_coloana = C.poz_coloana;
    poz_linie = C.poz_linie;
    blocat = C.blocat;
}

// constructorul de copiere pentru cautatorul 4
caut_tip4::caut_tip4(const caut_tip4 &C)
{
    poz_coloana = C.poz_coloana;
    poz_linie = C.poz_linie;
    blocat = C.blocat;
}
// destructorul cautatorului de tip 1
caut_tip1::~caut_tip1()
{

}

// destructorul cautatorului de tip 2
caut_tip2::~caut_tip2()
{

}

// destructorul cautatorului de tip 3
caut_tip3::~caut_tip3()
{

}

// destructorul cautatorului de tip 4
caut_tip4::~caut_tip4()
{

}


// se testeaza fiecare mod de deplasare, iar in cazul in care acesta este posibil se memoreaza pozitia de dinainte de deplasare si pozitia de dupa deplasare
bool Cautator::deplasare_stanga(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J)
{
    if (poz_coloana - 1 >= 0 && poz_coloana - 1 < J.dim_harta)
    {
        poz1 = poz_linie;
        poz2 = poz_coloana;
        new_poz1 = poz_linie;
        new_poz2 = poz_coloana;
        return 1;
    }
    return 0;
}

bool Cautator::deplasare_dreapta(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J)
{
    if (poz_coloana + 1 < J.dim_harta && poz_coloana + 1 >= 0)
    {
        poz1 = poz_linie;
        poz2 = poz_coloana;
        new_poz1 = poz_linie;
        new_poz2 = poz_coloana + 1;
        return 1;
    }
    return 0;
}

bool Cautator::deplasare_sus(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J)
{
    if (poz_linie - 1 >= 0 && poz_linie - 1 < J.dim_harta)
    {
        poz1 = poz_linie;
        poz2 = poz_coloana;
        new_poz1 = poz_linie - 1;
        new_poz2 = poz_coloana;
        return 1;
    }
    return 0;
}

bool Cautator::deplasare_jos(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J)
{
    if (poz_linie + 1 >= 0 && poz_linie + 1 < J.dim_harta)
    {
        poz1 = poz_linie;
        poz2 = poz_coloana;
        new_poz1 = poz_linie + 1;
        new_poz2 = poz_coloana;
        return 1;
    }
    return 0;
}

bool Cautator::deplasare_stanga_sus(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J)
{
    if (poz_linie - 1 >= 0 && poz_coloana - 1 >= 0 && poz_linie - 1 < J.dim_harta && poz_coloana - 1 < J.dim_harta)
    {
        poz1 = poz_linie;
        poz2 = poz_coloana;
        new_poz1 = poz_linie - 1;
        new_poz2 = poz_coloana - 1;
        return 1;
    }
    return 0;
}

bool Cautator::deplasare_dreapta_sus(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J)
{
    if (poz_linie - 1 >= 0 && poz_coloana + 1 < J.dim_harta && poz_linie - 1 < J.dim_harta && poz_coloana + 1 >= 0)
    {
        poz1 = poz_linie;
        poz2 = poz_coloana;
        new_poz1 = poz_linie - 1;
        new_poz2 = poz_coloana + 1;
        return 1;
    }
    return 0;
}

bool Cautator::deplasare_stanga_jos(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J)
{
    if (poz_linie + 1 < J.dim_harta && poz_coloana - 1 >= 0 && poz_linie + 1 >= 0 && poz_coloana - 1 < J.dim_harta)
    {
        poz1 = poz_linie;
        poz2 = poz_coloana;
        new_poz1 = poz_linie + 1;
        new_poz2 = poz_coloana - 1;
        return 1;
    }
    return 0;
}

bool Cautator::deplasare_dreapta_jos(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J)
{
    if (poz_linie + 1 < J.dim_harta && poz_coloana + 1 < J.dim_harta && poz_linie + 1 >=0 && poz_coloana +1 >= 0)
    {
        poz1 = poz_linie;
        poz2 = poz_coloana;
        new_poz1 = poz_linie + 1;
        new_poz2 = poz_coloana + 1;
        return 1;
    }
    return 0;
}

// deplaseaza cautatorul de tip 1 in functie de tasta apasata de jucator
bool caut_tip1::miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J)
{
    int n;

    cout<<"Legenda pentru deplasarea jucatorului 1: "<<endl;
    cout<<"1:stanga jos, 2:jos, 3:dreapta jos, 4:stanga, 6:dreapta, 7:stanga sus, 8:sus, 9:dreapta sus"<<endl;
    cout<<"Atentie ! Daca dati o miscare care nu se poate realiza, cautatorul se va bloca."<<endl;
    cout<<"Apasati tasta dorita pentru a deplasa jucatorul 1 sau tasta 0 pentru a incheia jocul: "<<endl<<endl;
    cin>>n;
    if(n == 0)
    {
        exit(0);
    }
    if(n == 3)
    if(deplasare_dreapta_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(n == 2)
    if(deplasare_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(n == 4)
    if(deplasare_stanga(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(n == 6)
    if(deplasare_dreapta(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(n == 8)
    if(deplasare_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(n == 7)
    if(deplasare_stanga_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(n == 1)
    if(deplasare_stanga_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(n == 9)
    if(deplasare_dreapta_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

     blocat = 1;
     return 0;
}

// deplaseaza cautatorul i pe harta si ii actualizeaza pozitia
bool caut_tip2::miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J)
{
    if(deplasare_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_stanga_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_stanga(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_dreapta(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_stanga_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_dreapta_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_dreapta_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
     blocat = 1;
     return 0;
}

bool caut_tip3::miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J)
{
    if(deplasare_stanga_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(deplasare_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_stanga(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_dreapta(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_stanga_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_dreapta_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_dreapta_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
     blocat = 1;
     return 0;
}

bool caut_tip4::miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J)
{
    if(deplasare_dreapta_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_dreapta(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_stanga(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_stanga_sus(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
    if(deplasare_stanga_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }

    if(deplasare_dreapta_jos(poz1, poz2, new_poz1, new_poz2, J) != 0)
        if(J.verificare_poz(new_poz1, new_poz2) != 0)
        {
            poz_linie = new_poz1;
            poz_coloana = new_poz2;
            return 1;
        }
     blocat = 1;
     return 0;
}
