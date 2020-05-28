#ifndef NOD_H
#define NOD_H
#include <iostream>
using namespace std;

class  Nod{
    int info;
    Nod* next;
public:
    Nod(); //constructorul fara parametri
    Nod(int x); //constructor cu un parametru
    Nod(int i, Nod* n); //constructor de copiere
    void setInfo(int x); //seteaza valoarea unui nod
    int getInfo(); //returneaza informatia dintr-un nod
    void setNext(Nod &);  //seteaza nodul urmator
    Nod* getNext(); //returneaza nodul urmator
    ~Nod(); //destructorul
    };

#endif // NOD_H
