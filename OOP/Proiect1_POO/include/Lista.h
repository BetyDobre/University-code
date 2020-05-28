#ifndef LISTA_H
#define LISTA_H
#include "Nod.h"
#include <iostream>
using namespace std;

class Lista{
    public:
    Nod* start, *endd; //retin primul si ultimul nod al listei
    int Size; //retine lungimea listei

    Lista(); //constructorul listei
    Lista(int x); //constructorul cu un parametru al listei
    Lista(Lista &l); //constructorul de copiere
    void Insert(int x); //insereaza un nod cu valoarea x la sfarsitul listei
    int get(int i) const; // returneaza valoarea nodului de pe pozitia i in lista
    int length(); //returneaza lungimea(Size ul) listei
    void Remove(int i); //sterge nodul de pe pozitia i
    bool has(int x); //testeaza daca exista valoarea x in lista
    int Min(); //returneaza valoarea minima din lista
    int Max(); //returneaza valoarea maxima din lista
    int suma() const; //returneaza suma elementelor din lista
    Lista Reverse(); //returneaza lista inversata
    void print(); //afiseaza elementele listei
    Lista operator*(int n); //supraincarcarea operatorului * (inmultirea cu un scalar)
    bool operator<(const Lista &L1); //supraincarcarea operatorului< (testeaza daca lista 1 e mai mica ca 2)
    bool operator>(const Lista &L2); //supraincarcarea operatorului> (testeaza daca lista 2 e mai mica ca 1)
    Lista operator+(const Lista &L1); //supraincarcarea operatorului + (reuniunea a doua liste)
    int operator[](int i); //supraincarcarea operatorului [] (returneaza al i-lea element la listei)
    void operator=(const Lista &l); //supraincarcarea operatorului = (atribuire)
    friend ostream &operator<<( ostream &output, const Lista &l ); //supraincarcarea operatorului de afisare
    friend istream &operator>>( istream  &input, Lista &l ); //supraincarcarea operatorului de citire
    ~Lista(){}; //destructorul listei
};

#endif // LISTA_H
