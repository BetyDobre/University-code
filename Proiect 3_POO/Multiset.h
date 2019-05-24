#include <iostream>
#include <list>
#include <cassert>
#include <exception>
#include <typeinfo>
using namespace std;

template <class T> class Multiset
{
    // dimensiunea hash-ului
    static const int n = 1103;
    // hashing table
    list<T>H[n];
    // functia de hash folosita
    static int hash_function(T x)
    {
        return (int)(x * 13) % n;
    }
    public:
        Multiset();
        Multiset(const Multiset&);
        void Insert(T x);
        void Erase(T x);
        int Count(T x) const;
        bool Find(T x) const;
        void Remove(T x);
        int  Different() const;
        template <class X>
        friend ostream &operator<<(ostream &out, const Multiset<X> &m);
        template <class X>
        friend istream &operator>>(istream &input, Multiset<X> &m);
        void operator=(Multiset<T> const &m);
        ~Multiset();

};

// constructor fara parametri care initializeaza un multiset gol
template <class T> Multiset<T>::Multiset()
{
    try
    {
        if (typeid(T) != typeid(int) && typeid(T) != typeid(float) && typeid(T) != typeid(double) && typeid(T) != typeid(char))
                throw bad_typeid();
    }
    catch(bad_typeid&e)
    {
        cout<<"Nu este un tip de date valid !\n\n";
    }

    for(int i = 0; i < n; i++)
            H[i].clear();
}

// constructor de copiere
template <class T> Multiset<T>::Multiset(const Multiset &m)
{
    list<int>L[m.n];
    for(int i = 0; i < m.n; i++)
        for(auto j:m.H[i])
            L[i].push_back(j);

}

// metoda pentru adaugarea unui element in multiset
template <class T>void  Multiset<T>::Insert(T x)
{
    int h = hash_function(x);
    H[h].push_back(x);
}

// metoda pentru stergerea unui elementdin multiset
template <class T>void Multiset<T>::Erase(T x)
{
    int h = hash_function(x);
    for(class list<T>::iterator i = H[h].begin(); i != H[h].end(); i++)
        if(*i == x)
        {
            H[h].erase(i);
            break;
        }
}

// metoda care intoarce numarul de aparitii al unui element
template <class T>int Multiset<T>::Count(T x) const
{
    int nr = 0, h = hash_function(x);
    for(class list<T>::const_iterator i = H[h].begin(); i != H[h].end(); i++)
        if(*i == x)
        {
            nr++;
        }

    return nr;
}

// metoda care verifica daca un element se afla in multiset
template <class T>bool Multiset<T>::Find(T x) const
{
    int h = hash_function(x);
    for(class list<T>::const_iterator i = H[h].begin(); i != H[h].end(); i++)
        if(*i == x)
        {
            return true;
        }
    return false;
}

// metoda care elimina toate aparitiile unui element din multiset
template <class T>void Multiset<T>::Remove(T x)
{
    int h = hash_function(x);
    H[h].remove(x);
}

// metoda care intoarce numarul de elemente distincte din multiset
template <class T>int Multiset<T>::Different() const
{
    int nr = 0;
    for(int i = 0 ; i < n ; i++)
        if(H[i].size() <= 1)
            nr += H[i].size();
        else
        {
            int *v = new int[H[i].size()];
            for(unsigned int j = 0; j < H[i].size(); j++)
                v[j] = 0;
            unsigned int cnt1 = 0, cnt2;
            for(class list<T>::const_iterator j = H[i].begin(); cnt1 < H[i].size() - 1 && j != H[i].end(); j++, cnt1++){
                cnt2 = cnt1 + 1; class list<T>::const_iterator l = j;
                for(class list<T>::const_iterator k = ++l; k != H[i].end(); k++, cnt2++)
                    if (*j == *k)
                        v[cnt2] = 1;
            }
            for(unsigned int j = 0; j < H[i].size(); j++)
                if(!v[j]) nr++;
        }
    return nr;
}

// supraincarcarea operatorului de atribuire
template <class T> void Multiset<T>::operator=(Multiset const &m)
{

    for(int i = 0; i < n; i++)
            this->H[i].clear();
    for(int i = 0; i < m.n; i++)
        for(class list<T>::const_iterator j = m.H[i].begin(); j!= m.H[i].end(); j++)
            this->H[i].push_back(*j);

}

// supraincarcarea operatorului de afisare
template<class T> ostream &operator<< (ostream &output, const Multiset<T> &m)
{
    for(int i = 0; i < m.n; i++)
        for(class list<T>::const_iterator j = m.H[i].begin(); j!= m.H[i].end(); j++)
            output<<*j<< " ";
     return output;
}

// supraincarcarea operatorului de citire
template<class T> istream &operator>> (istream &input, Multiset<T> &m)
{
    int i;
    T x;
    cout<<"Numarul de elemente pe care doriti sa il aiba multisetul: ";
    input>>i;
    cout<<"Dati elementele: ";
    for(int j = 0; j < i; j++)
    {
        input>>x;
        int y = m.hash_function(x);
        m.H[y].push_back(x);
    }
    return input;
}


// destructorul clasei Multiset
template <class T> Multiset<T>::~Multiset()
{
    for(int i = 0; i < n; i++)
        H[i].erase(H[i].begin(), H[i].end());
}
