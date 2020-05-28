#include "Nod.h"
#include <iostream>
using namespace std;

Nod::Nod()
{
    info = 0;
    next = NULL;
}
Nod::Nod(int x)
{
    info = x;
    next = NULL;
}

Nod::Nod(int i, Nod* n)
{
	info = i;
	next = n;
}

Nod::~Nod()
{
   // delete next;
}

void Nod::setInfo(int x)
{
    info = x;
}

int Nod::getInfo()
{
    return info;
}

void Nod::setNext(Nod &p)
{
    next = &p;
}

Nod* Nod::getNext()
{
    return next;
}
