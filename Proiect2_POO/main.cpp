#include <iostream>
#include "Cautator.h"
#include "Joc.h"
using namespace std;

int main()
{
    // dimensiunea pe care o va avea harta
    int dim;
    cout<<"Bine ati venit !"<<endl;
    cout<<"Legenda hartii: cautatorii sunt marcati cu cifre de la 1 la 4, comorile cu cifra 5"<<endl;
    cout<<"Puteti deplasa cautatorul cu numarul 1 dupa cum doriti, iar daca acesta castiga jocul, gaseste o comoara sau se blocheaza jocul se va desfasura automat."<<endl<<endl;
    cout<<"Dati dimensiunea pe care doriti sa o aiba harta: " ;
    try
    {
        cin>>dim;
        if (dim < 15) throw dim;
    }
    catch(int e)
    {
        cout<<"Dimensiunea hartii trebuie sa fie mai mare sau egala cu 15 !";
        return 0;
    }

    Joc J(dim);
    J.afisare_harta();
    while(J.sfarsitJoc() == 0)
    {
        J.runda();
        J.afisare_harta();
    }

    return 0;
}
