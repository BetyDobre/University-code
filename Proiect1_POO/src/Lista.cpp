#include "Lista.h"
#include<cstring>
#define MIN_INT 999999999
#define MAX_INT -999999999
#include <iostream>
using namespace std;

Lista::Lista()
{
    start = endd = NULL;
    Size = 0;
}

Lista::Lista(int x)
{
	start = new Nod;
    start->setInfo(x);
    endd = start;
    Size = 1;
}

Lista::Lista(Lista& l)
{
	start = endd = NULL;
	Size = 0;
	Nod* p = l.start;
	while (p != NULL)
    {
		Insert(p->getInfo());
		p = p->getNext();
	}
}

void Lista::print() {
	Nod *p = start;
	while (p != NULL) {
		cout << p->getInfo() << " ";
		p = p->getNext();
	}
	cout <<endl;
}  //se parcurge lista si se afiseaza pe rand informatia din fiecare nod

void Lista::Insert(int x)
{
    Nod *p;
    p = new Nod;
    p->setInfo(x);
    if(start == NULL)
    {
        start = p;
        endd = p;
    }   //cazul in care lista este vida
    else{
        endd->setNext(*p);
        endd = p;
        endd->setNext(*start);
    }
    ++Size;
}

void Lista::Remove(int i)
{
    if(start == NULL)
        return; //lista vida
    if(i < 0 || i >= Size)
        return; //pozitie incorecta
    if(i == 0){
        if(Size < 2){
            delete start;
            start = endd = NULL;
        }
        else{
            Nod *p = start;
            start = start->getNext();
            delete p;
            endd->setNext(*start);
        }
        --Size;
    }
    else if(i == Size - 1){
        Nod *p = start;
        delete endd;
        for(int j = 0; j < Size - 2; j++ )
            p = p->getNext();
        endd = p;
        endd->setNext(*start);
        --Size;
    }
    else{
        int j = 0;
        Nod * p = start, *q;
        for(; p != NULL && j < i; p = p->getNext(), j++)
            q = p;
        if (i == j)
        {
            Nod *r = p->getNext();
            q->setNext(*r);
            delete p;
        }
        --Size;
    }
}

bool Lista::has(int x)
{
    Nod *p = start;
    for (int i = 0; i < Size; i++)
    {
        if (p->getInfo() == x)
            return true;
        p = p->getNext();
    } //se parcurege lista si se cauta elementul x;
    return false;
}

int Lista::length()
{
    return Size;
}

Lista Lista::Reverse()
{
    Lista L;
    int i;
    for(i = Size - 1; i >= 0; i--)
        L.Insert(get(i));
    return L;
}

int Lista::get(int i) const
{
    Nod *p = start;
    if (i < 0 && i > Size)
        return MAX_INT; //pozitie incorecta data
    if (p != NULL)
        for(int j = 0; j < i; j++)
        {
            p = p->getNext();
        }
    return p->getInfo();
}

int Lista::Min()
{
    int minim = MIN_INT;
    for(int i = 0; i < Size; i++)
        if(get(i) < minim)
          minim = get(i);
    return minim;
}

int Lista::Max()
{
    int maxim = MAX_INT;
    for(int i = 0; i < Size; i++)
        if(get(i) > maxim)
          maxim = get(i);
    return maxim;

}

int Lista::suma() const
{
    int s = 0;
    Nod * p = start;
    for(int i = 0; i < Size; i++)
    {
        s = s + p->getInfo();
        p = p->getNext();
    }
    return s;
}

Lista Lista::operator*(int n)
{
    Lista l;
    Nod *p = this->start;
    for(int i = 0; i < this->Size; i++)
    {
        l.Insert(p->getInfo() * n);
        p = p->getNext();
    }
    return l;
}

bool Lista::operator<(const Lista &L1)
{
    if (L1.suma() > this->suma())
        return true;
    return false;
}

bool Lista::operator>(const Lista &L1)
{
    if (L1.suma() < this->suma())
        return true;
    return false;
}

Lista Lista::operator+(const Lista &L1)
{
    Lista L;
    for(int i = 0; i < this->Size; i++)
    {
        int x = this->get(i);
        L.Insert(x);
    }
    for(int i = 0; i < L1.Size; i++)
    {
        int x = L1.get(i);
        L.Insert(x);
    }
    return L;
}

int Lista::operator[](int i)
{
    if (i >= Size)
        return 0;
    return get(i);
}

void Lista::operator=(const Lista &l)
{
    Nod *p = this->start;
    if(this->Size != 0)
    {
        Nod *q;
        for(int i = 0; i < this->Size; i++)
        {
            q = p->getNext();
            delete p;
            p = q;
        }
        delete q;
    }
    this->start = this->endd = NULL;
    this->Size = 0;
    p = l.start;
    for(int i = 0; i < l.Size; i++)
    {
        this->Insert(p->getInfo());
        p = p->getNext();
    }
}
ostream &operator<<( ostream &output, const Lista &l )
{

    Nod *p = l.start;
    for (int i = 0; i < l.Size; i++)
    {
        cout<<p->getInfo()<<" ";
        p = p->getNext();
    }
    return output;
}

istream &operator>>( istream  &input, Lista &l )
{
    int x;
    input >> l.Size;
    Nod *start = new Nod;
    cin>>x;
    start->setInfo(x);
    Nod *p = start->getNext();
    for(int i = 1; i < l.Size; i++)
    {
        cin>>x;
        p->setInfo(x);
        p->getNext();
    }
    return input;
}
