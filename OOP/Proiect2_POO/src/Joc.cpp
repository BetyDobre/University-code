#include "Joc.h"
#include "Cautator.h"
#include<iostream>
#include<time.h>
#include<cstdlib>
#include <exception>
using namespace std;

// constructorul care primeste ca parametru dimensiunea hartii si construieste harta
Joc::Joc(int dim)
{
    dim_harta = dim;
    int i, j;

    // alocarea dinamica a hartii
    try
    {   Harta = new int*[dim_harta];
        for (i = 0; i < dim_harta; i++)
            Harta[i] = new int[dim_harta];
    }
    catch (bad_alloc &a)
    {
        cout<<"Nu exista destula memorie !" ;
        return;
    }

    // se initializeaza harta cu 0 initial
    for(i = 0; i < dim_harta; i++)
        for (j = 0; j < dim_harta; j++)
            Harta[i][j] = 0;
    comori_gasite = 0;
    win = 0;
    for(i = 0; i <= 3; i++)
        status[i] = 0;


    // plasarea cautatorilor pe harta, in colturi
    Harta[0][0] = 1;
    Harta[0][dim_harta - 1] = 2;
    Harta[dim_harta - 1][dim_harta - 1] = 3;
    Harta[dim_harta - 1][0] = 4;

    // plasarea comorilor random pe harta
    srand(time(NULL)); // pentru a genera alte pozitii la fiecare rulare a programului
    int n = 0;
    while (n < 3)
    {
        i = rand()%dim_harta;
        j = rand()%dim_harta;
        // se testeaza conditiile ca si comorile sa nu fie puse in colturile hartii
        if(((i != 0) && (j != 0)) && ((i != 0) && (j != dim_harta - 1)) && ((i != dim_harta - 1) && (j != dim_harta - 1)) && ((i != dim_harta - 1) && (j != 0)) && (Harta[i][j] == 0))
        {
             Harta[i][j] = 5;
             n++;
        }
    }

    // initializarea fiecarui cautator
    c[0] = new caut_tip1(0,0);
    c[1] = new caut_tip2(0, dim_harta - 1);
    c[2] = new caut_tip3(dim_harta - 1, dim_harta - 1);
    c[3] = new caut_tip4(dim_harta - 1, 0);
}

// testeaza daca cautatorul se poate deplasa pe o anumita pozitie(daca pozitia este libera sau pe ea se afla o comoara)
bool Joc::verificare_poz(int poz1, int poz2)
{
    if (Harta[poz1][poz2] == 0 || Harta[poz1][poz2] == 5)
        return 1;
    return 0;
}

// destructorul clasei Joc
Joc::~Joc()
{
    for (int i = 0; i < dim_harta; i++)
        delete[] Harta[i];
    delete[] Harta;
}

// constructorul de copiere pentru clasa Joc
Joc::Joc(const Joc &J)
{
    Harta = new int*[dim_harta];
    for (int i = 0 ; i < dim_harta; i++)
        Harta[i] = new int[dim_harta];
    for (int i = 0; i < dim_harta; i++)
        for (int j = 0; j < dim_harta; j++)
            Harta[i][j] = J.Harta[i][j];
}


// afiseaza pe ecran Harta
void Joc::afisare_harta()
{
    for(int i = 0; i < dim_harta; i++)
    {
        for(int j = 0; j < dim_harta; j++)

            if(Harta[i][j] == 0)
                cout<<"- ";
            else cout<<Harta[i][j]<<" ";
        cout<<endl;
    }
}

// testeaza conditiile pentru ca jocul sa ia sfarsit
bool Joc::sfarsitJoc()
{
    if(comori_gasite == 3)
    {
        cout<<"Jocul a luat sfarsit! "<<endl;
        return 1;
    }

    int nr = 0;
    for(int i = 0; i <= 3; i++)
       if(c[i]->getBlocat() == 1)
            nr++;
    if(nr + comori_gasite == 4) // in cazul in care fiecare cautator s a blocat sau a gasit o comoara
    {
        cout<<"Jocul a luat sfarsit! "<<endl;
        return 1;
    }
    return 0;
}

void Joc::deplasare_runda(int i, Cautator *c, Joc &J, bool &win)
{
    int poz1, poz2, new_poz1, new_poz2;

    // daca statusul cautatorului este 1 inseamna ca a gasit primul comoara si a castigat
    if (J.status[i] == 1)
    {
        cout<<"Jucatorul "<<i<<" a castigat !"<<endl;
        return;
    }

    if (c->getBlocat() == 1)
    {
        cout<<"Jucatorul "<<i<<" s-a blocat"<<endl;
        return;
    }

    // daca statusul cautatorului este 2 inseamna ca acesta a gasit o comoara
    if(J.status[i] == 2)
    {
        cout<<"Jucatorul "<<i<<" a gasit o comoara !"<<endl;
        return;
    }

    // deplasarea cautatorului
    int ok = c->miscare(i, poz1, poz2, new_poz1, new_poz2, J);
    if (ok == 0)
    {
        c->setBlocat();
        cout<<"Jucatorul "<<i<<" s-a blocat"<<endl;
        return;
    }

    // caz in care cautatorul s-a deplasat pe o pozitie libera pe harta
    if(J.Harta[new_poz1][new_poz2] == 0)
    {
        if(poz1 == new_poz1 && poz2 < new_poz2)
            cout<<"Jucatorul "<<i<<" s-a deplasat la dreapta, pe linia "<<new_poz1<<" si coloana "<<new_poz2<<endl;
        if(poz1 == new_poz1 && poz2 > new_poz2)
            cout<<"Jucatorul "<<i<<" s-a deplasat la stanga, pe linia "<<new_poz1<< " si coloana "<<new_poz2<<endl;
        if(poz1 > new_poz1 && poz2 == new_poz2)
            cout<<"Jucatorul "<<i<<" s-a deplasat in sus, pe linia "<<new_poz1<<" si coloana "<<new_poz2<<endl;
        if(poz1 < new_poz1 && poz2 == new_poz2)
           cout<<"Jucatorul "<<i<<" s-a deplasat in sus, pe linia "<<new_poz1<<" si coloana "<<new_poz2<<endl;
        if(poz1 > new_poz1 && poz2 > new_poz2)
            cout<<"Jucatorul "<<i<<" s-a deplasat in in stanga sus, pe linia "<<new_poz1<<" si coloana "<<new_poz2<<endl;
        if(poz1 < new_poz1 && poz2 < new_poz2)
            cout<<"Jucatorul "<<i<<" s-a deplasat in dreapta jos, pe linia "<<new_poz1<<" si coloana "<<new_poz2<<endl;
        if(poz1 < new_poz1 && poz2 > new_poz2)
            cout<<"Jucatorul "<<i<<" s-a deplasat in stanga jos, pe linia "<<new_poz1<<" si coloana "<<new_poz2<<endl;
        if(poz1 > new_poz1 && poz2 < new_poz2)
            cout<<"Jucatorul "<<i<<" s-a deplasat in dreapta sus, pe linia "<<new_poz1<<" si coloana "<<new_poz2<<endl;
        J.Harta[new_poz1][new_poz2] = i;
        return;
    }

    // caz in care cautatorul s-a deplasat pe o pozitie unde se afla o comoara
    if(J.Harta[new_poz1][new_poz2] == 5)
    {
        if(J.win == 0)
        {
            J.status[i] = 1;
            J.win = 1;
            cout<<"Jucatorul "<<i<<" a castigat !"<<endl;
        }
        else
        {
            J.status[i] = 2;
            cout<<"Jucatorul "<<i<<" a gasit si el o comoara pe linia "<<new_poz1<<" si coloana "<<new_poz2<<" !"<<endl;
        }

        J.comori_gasite++;
        J.Harta[new_poz1][new_poz2] = i;
        return;
    }

}

// desfasurarea rundei pentru fiecare din cei 4 cautatori
void Joc::runda()
{
    for (int i = 0; i < 4; i++)
        deplasare_runda(i+1, c[i], *this, win);
}
