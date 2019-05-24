#include <iostream>
#include <list>
#include <cassert>
#include <exception>
#include <typeinfo>
#include "Multiset.h"
using namespace std;


int main()
{
    cout<<"Exemplu multiset de int \n"<<endl;
    Multiset<int> m;
    // inserez elementul 1 de 2 ori
    m.Insert(1);
    m.Insert(1);
    // inserez elementul 2 de doua ori
    m.Insert(2);
    m.Insert(2);
    // inserez elementul 3
    m.Insert(3);
    // sterg toate aparitiile ale elementului 1
    m.Remove(1);
    cout<<"Multisetul este: "<<m<<endl;
    cout<<"Numarul de 2 din multiset: "<<m.Count(2)<<endl;
    assert(m.Count(2) == 2);
    ///assert(m.Count(2) == 0);
    cout<<"Se gaseste 2 in multiset ? "<<m.Find(2)<<endl;
    assert(m.Find(2) == 1);
    /// assert(m.Find(1) == 1);
    cout<<"Se gaseste 1 in multiset? "<<m.Find(1)<<endl;
    assert(m.Find(1) == 0);
    /// assert(m.Find(1) == 1)
    cout<<"Numarul de elemente diferite din multiset: "<<m.Different()<<endl;
    assert(m.Different() == 2);
    /// assert(m.Different() != 2);
    Multiset<int> a;
    // verificarea supraincarcarii operatorului de atribuire
    a = m;
    cout<<"Copia multisetului m: "<<a<<endl;
    m.Erase(2);
    cout<<"Multisetul fara prima aparitie a lui 2: "<<m<<endl<<endl;

    cout<<"Exemplu multiset de string\n";
    Multiset<string> s;

    cout<<"Exemplu multiset de char\n\n";
    Multiset<char> l;
    // verificarea supraincarcariii operatorului de citire
    cin>>l;
    // verificarea supraincarcarii operatorului de afisare
    cout<<l<<endl;
    cout<<"\n\nPentru a incheia programul tastati 0"<<endl;
    cout<<"Pentru clasa de tip INT tastati 1"<<endl;
    cout<<"Pentru clasa de tip DOUBLE tastati 2"<<endl;
    cout<<"Pentru clasa de tip FLOAT tastati 3"<<endl;
    cout<<"Pentru clasa de tip CHAR tastati 4"<<endl;
    int comanda;
    cin>>comanda;
    switch(comanda)
    {
        case 0:
            return 0;
        case 1:
            {
                Multiset<int> m;
                cout<<"Numarul de elemente care doriti sa fie inserate in multiset: ";
                int x, nr;
                cin>>x;
                cout<<"Dati numerele: ";
                for(int i = 0; i < x; i++)
                {
                    cin>>nr;
                    m.Insert(nr);
                }
                cout<<"Multisetul: "<<m<<endl;
                cout<<"Doriti sa aflati cate elemente distincte se afla in multiset? 1-DA 2-NU";
                do{
                try
                {   cin>>x;
                    if(x != 1 && x != 2)
                        throw x;
                }
                catch(int e)
                       { cout<<"Trebuie sa tastati 1 sau 2\n";
                            }
                }while (x != 1 && x != 2);
                if(x == 1) cout<<"Sunt "<<m.Different()<<" numere distincte in multiset\n";
                cout<<"Dati  un element pe care doriti sa il cautati in multiset: ";
                cin>>x;
                if(m.Find(x)) cout<<"Elementul "<<x<<" se afla in multiset"<<endl;
                else cout<<"Elementul "<<x<<"nu se afla in multiset"<<endl;
                cout<<"Dati un element caruia doriti sa ii numarati aparitiile in multiset: ";
                cin>>x;
                cout<<"Numarul "<<x<<" apare de "<<m.Count(x)<<" ori\n";
                cout<<"Dati un element pe care doriti sa il stergeti din multiset: ";
                cin>>x;
                m.Erase(x);
                cout<<"Multisetul dupa stergere: "<<m<<endl;
                cout<<"Dati un element caruia doriti sa ii stergeti toate aparitiile: ";
                cin>>x;
                m.Remove(x);
                cout<<"Multisetul dupa stergere: "<<m<<endl;
                break;

            }
        case 2:
            {
                Multiset<double> m;
                cout<<"Numarul de elemente care doriti sa fie inserate in multiset: ";
                double x;
                int nr;
                cin>>nr;
                cout<<"Dati numerele: ";
                for(int i = 0; i < nr; i++)
                {
                    cin>>x;
                    m.Insert(x);
                }
                cout<<"Multisetul: "<<m<<endl;
                cout<<"Doriti sa aflati cate elemente distincte se afla in multiset? 1-DA 2-NU";
                int a;
                do{
                try
                {   cin>>a;
                    if(a != 1 && a != 2)
                        throw a;
                }
                catch(int e)
                       {
                           cout<<"Trebuie sa tastati 1 sau 2\n";}
                }while(a != 1 && a != 2);
                if(a == 1) cout<<"Sunt "<<m.Different()<<" numere distincte in multiset\n";
                cout<<"Dati un element pe care doriti sa il cautati in multiset: ";
                cin>>x;
                if(m.Find(x)) cout<<"Elementul "<<x<<" se afla in multiset"<<endl;
                else cout<<"Elementul "<<x<<"nu se afla in multiset"<<endl;
                cout<<"Dati un element caruia doriti sa ii numarati aparitiile in multiset: ";
                cin>>x;
                cout<<"Numarul "<<x<<" apare de "<<m.Count(x)<<" ori\n";
                cout<<"Dati un element pe care doriti sa il stergeti din multiset: ";
                cin>>x;
                m.Erase(x);
                cout<<"Multisetul dupa stergere: "<<m<<endl;
                cout<<"Dati un element caruia doriti sa ii stergeti toate aparitiile: ";
                cin>>x;
                m.Remove(x);
                cout<<"Multisetul dupa stergere: "<<m<<endl;
                break;
            }
        case 3:
            {
                Multiset<float> m;
                cout<<"Numarul de elemente care doriti sa fie inserate in multiset: ";
                float x;
                int nr;
                cin>>nr;
                cout<<"Dati numerele: ";
                for(int i = 0; i < nr; i++)
                {
                    cin>>x;
                    m.Insert(x);
                }
                cout<<"Multisetul: "<<m<<endl;
                cout<<"Doriti sa aflati cate elemente distincte se afla in multiset? 1-DA 2-NU";
                int a;
                do{
                try
                {   cin>>a;
                    if(a != 1 && a != 2)
                        throw a;
                }
                catch(int e)
                       {
                           cout<<"Trebuie sa tastati 1 sau 2\n";}
                }while(a != 1 && a != 2);
                if(a == 1) cout<<"Sunt "<<m.Different()<<" numere distincte in multiset\n";
                cout<<"Dati un element pe care doriti sa il cautati in multiset: ";
                cin>>x;
                if(m.Find(x)) cout<<"Elementul "<<x<<" se afla in multiset"<<endl;
                else cout<<"Elementul "<<x<<"nu se afla in multiset"<<endl;
                cout<<"Dati un element caruia doriti sa ii numarati aparitiile in multiset: ";
                cin>>x;
                cout<<"Numarul "<<x<<" apare de "<<m.Count(x)<<" ori\n";
                cout<<"Dati un element pe care doriti sa il stergeti din multiset: ";
                cin>>x;
                m.Erase(x);
                cout<<"Multisetul dupa stergere: "<<m<<endl;
                cout<<"Dati un element caruia doriti sa ii stergeti toate aparitiile: ";
                cin>>x;
                m.Remove(x);
                cout<<"Multisetul dupa stergere: "<<m<<endl;
                break;
            }
        case 4:
            {
                Multiset<char> m;
                cout<<"Numarul de elemente care doriti sa fie inserate in multiset: ";
                char x;
                int nr;
                cin>>nr;
                cout<<"Dati caractere: ";
                for(int i = 0; i < nr; i++)
                {
                    cin>>x;
                    m.Insert(x);
                }
                cout<<"Multisetul: "<<m<<endl;
                cout<<"Doriti sa aflati cate elemente distincte se afla in multiset? 1-DA 2-NU";
                int a;
                do{
                try
                {   cin>>a;
                    if(a != 1 && a != 2)
                        throw a;
                }
                catch(int e)
                       { cout<<"Trebuie sa tastati 1 sau 2\n";}
                }while(a != 1 || a != 2);
                if(a == 1) cout<<"Sunt "<<m.Different()<<" caractere distincte in multiset\n";
                cout<<"Dati un element pe care doriti sa il cautati in multiset: ";
                cin>>x;
                if(m.Find(x)) cout<<"Caracterul "<<x<<" se afla in multiset"<<endl;
                else cout<<"Elementul "<<x<<"nu se afla in multiset"<<endl;
                cout<<"Dati un element caruia doriti sa ii numarati aparitiile in multiset: ";
                cin>>x;
                cout<<"Caracterul "<<x<<" apare de "<<m.Count(x)<<" ori\n";
                cout<<"Dati un element pe care doriti sa il stergeti din multiset: ";
                cin>>x;
                m.Erase(x);
                cout<<"Multisetul dupa stergere: "<<m<<endl;
                cout<<"Dati un element caruia doriti sa ii stergeti toate aparitiile: ";
                cin>>x;
                m.Remove(x);
                cout<<"Multisetul dupa stergere: "<<m<<endl;
                break;
            }
    }
    return 0;
}
