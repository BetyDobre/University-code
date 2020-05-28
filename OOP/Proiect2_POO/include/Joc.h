#ifndef JOC_H
#define JOC_H
#include "Cautator.h"

class Cautator;

class Joc
{
    protected:
        int **Harta;
        int dim_harta, comori_gasite, status[5];
        bool win;
        Cautator *c[4];
        friend class Cautator;
        friend class caut_tip1;
        friend class caut_tip2;
        friend class caut_tip3;
        friend class caut_tip4;
    public:
        Joc(int);
        virtual ~Joc();
        Joc(const Joc &J);
        void afisare_harta();
        bool verificare_poz(int, int);
        bool sfarsitJoc();
        void deplasare_runda(int i, Cautator *c, Joc &J, bool &win);
        void runda();

};

#endif // JOC_H
