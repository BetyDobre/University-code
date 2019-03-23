#include <iostream>
#include "Lista.h"
#include "Nod.h"
#include <cassert>
using namespace std;

int main()
{
    Lista l(1); //creez o lista care contine elementul 1
    l.Insert(2); //inserez elementul 2
    l.Insert(3);//inserez elementul 3
    cout<<l<<'\n';
    l.Remove(1); //sterg elementul de pe pozitia 1
    cout<<l<<'\n';
    cout<<"Lista are elementul 2 ? "<< l.has(2)<<'\n';
    cout<<"Lista are elementul 1 ? "<<l.has(1)<<'\n';
    cout<<"Elementul minim din lista: "<<l.Min()<<'\n';
    cout<<"Elementul maxim din lista: "<< l.Max()<<'\n';
    cout<<"Suma elementelor din lista: "<<l.suma()<<'\n';
    cout<<"Lungimea listei: "<<l.length()<<'\n';
    Lista L;
    L = l.Reverse();
    cout<<"Lista initiala: ";
    cout<<l<<'\n';
    cout<<"Lista inversata: " ;
    cout<<L<<'\n';
    Lista R;
    R = l + L;
    cout<< "Reuniunea listelor este: ";
    cout<<R<<'\n';
    int n = l[1];
    cout<<"Elementul de pe pozitia 1 este: "<<n<<'\n';
    if (L > l)
        cout<<"True"<<'\n';
    else cout<<"False"<<'\n';

    Lista a;
    a = L * 2;
    cout<<"Lista inmultita cu 2: ";
    cout<<a;

    assert((L.Min()) == 1);
    ///assert((L.Min()) == 2);
    assert((L.Max() == 3));
    ///assert((L.Max()) == 5);
    assert((L.suma() == 4));
    ///assert((L.suma()) == 6);


    L.Insert(4);
    assert(L.Size == 3);
    ///assert(L.Size != 3);
    assert(L.has(4) == 1);
    ///assert(L.has(5) == 1);

    L.Remove(2);
    assert(L.Size == 2);
    ///assert(L.Size == 3);
    assert(L.has(4) == 0);
    ///assert(L.has(9) == 1);


    return 0;
}
