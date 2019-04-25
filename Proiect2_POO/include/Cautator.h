#ifndef CAUTATOR_H
#define CAUTATOR_H
#include "Joc.h"
#include<iostream>
using namespace std;

class Joc;

class Cautator
{
    protected:
        int  poz_linie, poz_coloana, blocat;

    public:
        Cautator();
        Cautator(int linie, int coloana);
        virtual ~Cautator(){};
        bool getBlocat();
        void setBlocat();
        bool deplasare_stanga(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J);
        bool deplasare_dreapta(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J);
        bool deplasare_sus(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J);
        bool deplasare_jos(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J);
        bool deplasare_stanga_sus(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J);
        bool deplasare_stanga_jos(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J);
        bool deplasare_dreapta_sus(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J);
        bool deplasare_dreapta_jos(int &poz1, int &poz2, int &new_poz1, int &new_poz2, const Joc &J);
        virtual bool miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J) = 0;
};

class caut_tip1 : public Cautator
{
    public:
        caut_tip1();
        virtual ~caut_tip1();
        caut_tip1(const caut_tip1 &C);
        caut_tip1(int linie, int coloana);
        bool miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J);

};

class caut_tip2 : public Cautator
{
    public:
        caut_tip2();
        virtual ~caut_tip2();
        caut_tip2(const caut_tip2 &C);
        caut_tip2(int linie, int coloana);
        bool miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J);

};

class caut_tip3 : public Cautator
{
    public:
        caut_tip3();
        virtual ~caut_tip3();
        caut_tip3(const caut_tip3 &C);
        caut_tip3(int linie, int coloana);
        bool miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J);

};

class caut_tip4 : public Cautator
{
    public:
        caut_tip4();
        virtual ~caut_tip4();
        caut_tip4(const caut_tip4 &C);
        caut_tip4(int linie, int coloana);
        bool miscare(int i, int &poz1, int &poz2, int &new_poz1, int &new_poz2, Joc &J);

};

#endif // CAUTATOR_H
