# GRUPA 242, DOBRE MIHAELA BEATRICE

import timeit
from math import sqrt

""" definirea problemei """

# clasa care retine numele copiilor(configuratia clasei) si pozitia curenta a mesajului
class Nod_configuratie:
    def __init__(self, copii, mesaj):
        self.copii = copii
        self.poz_mesaj = pozitii_in_banci[mesaj]

    def __repr__(self):
        return f"{self.copii[self.poz_mesaj[0]][self.poz_mesaj[1]]}" # numele copilului unde se afla mesajul

    def __eq__(self, x):
        return self.poz_mesaj == x.poz_mesaj    # suprascrierea operatorului de egalitate


class Nod:
    def __init__(self, configuratie):
        self.info = configuratie        # configuratia curenta a unui Nod(configuratia clasei si pozitia mesajului)
        self.h = self.fct_h()
        self.drum = ""                  # semnul pentru afisare

    def fct_h(self):
        if euristica_aleasa == 1:
            # distanta euclidiana
            """
            Am ales ca euristica distanta euclidiana, deoarece reprezinta distanta intre doua puncte mergand pe diagonala, 
            deci nu supraestimeaza numarul de mutari optim in rezolvarea problemei.
            """
            return sqrt((self.info.poz_mesaj[0] - configuratie_finala.poz_mesaj[0]) ** 2 + (self.info.poz_mesaj[1] - configuratie_finala.poz_mesaj[1]) ** 2)

            #f ul si h-ul au fluctuatii, dar in principiu scade

        if euristica_aleasa == 2:
            # distanta Manhattan
            """
            Am ales distanta Manhattan, deoarece un elev poate transmite mesajul doar colegului de banca(stanga/dreapta lui) sau colegului din fata/spate
            (poate face doar mutari pe orizontala/verticala, dar nu si pe diagonala). Distanca Manhattan dintre doua puncte este adesea folosita in astfel de
            situatii si ea se calculeaza ca fiind suma diferentelor absolute a coordonatelor lor carteziene.
            Aceasta euristica este admisibila, deoarece ea reprezinta scenariul unui traseu in care nu exista locuri libere sau copii certati, deci numarul optim
            de pasi in rezolvarea problemei este mai mare sau egal decat lungimea descrisa.
            """
            return abs(self.info.poz_mesaj[0] - configuratie_finala.poz_mesaj[0]) + abs(self.info.poz_mesaj[1] - configuratie_finala.poz_mesaj[1])

            #h-ul scade aproximativ, dar f-ul creste
        if euristica_aleasa == 3:
            # distanta neadmisibila
            """
            Aceasta euristica este neadmisibila, deoarece supraestimeaza costul de la nodul curent la la nodul scop.
            """
            if self.info.poz_mesaj[0] == configuratie_finala.poz_mesaj[0] and self.info.poz_mesaj[1] == configuratie_finala.poz_mesaj[1]:
                return 0
            return columns * rows + (0 if self.info.poz_mesaj[1] % 2 == 0 else - (configuratie_finala.poz_mesaj[1] * self.info.poz_mesaj[1]))


    def __str__ (self):
        return "{} {}".format(self.drum, self.info)

    def __repr__ (self):
        return f"({self.info}, h={self.h})"

class Arc:
    def __init__(self, capat, varf, cost):
        self.capat = capat	# de unde pleaca muchia
        self.varf = varf	# unde ajunge muchia
        self.cost = 1	    # orice mesaj transmis are costul 1

class Problema:
    def __init__(self):
        self.noduri = [
            Nod(configuratie_initiala),
            # Nod(configuratie_finala)
        ]
        self.arce = []
        self.nod_start = self.noduri[0]
        self.nod_scop = configuratie_finala


    def cauta_nod_nume(self, info):
        """Stiind doar informatia "info" a unui nod,
        trebuie sa returnati fie obiectul de tip Nod care are acea informatie,
        fie None, daca nu exista niciun nod cu acea informatie."""
        ### TO DO ... DONE
        for nod in self.noduri:
            if nod.info == info:
                return nod
        return None

""" Sfarsit definire problema """



""" Clase folosite in algoritmul A* """

class NodParcurgere:
    """O clasa care cuprinde informatiile asociate unui nod din listele open/closed
        Cuprinde o referinta catre nodul in sine (din graf)
        dar are ca proprietati si valorile specifice algoritmului A* (f si g).
        Se presupune ca h este proprietate a nodului din graf

    """

    problema=None	# atribut al clasei (se suprascrie jos in __main__)


    def __init__(self, nod_graf, parinte=None, g=0, f=None):
        self.nod_graf = nod_graf  	# obiect de tip Nod
        self.parinte = parinte  	# obiect de tip NodParcurgere
        self.g = g  	# costul drumului de la radacina pana la nodul curent
        if f is None :
            self.f = self.g + self.nod_graf.h
        else:
            self.f = f

    def drum_arbore(self):
        """
            Functie care calculeaza drumul asociat unui nod din arborele de cautare.
            Functia merge din parinte in parinte pana ajunge la radacina
        """
        nod_c = self
        drum = [nod_c.nod_graf]
        while nod_c.parinte is not None:
            drum = [nod_c.parinte] + drum
            nod_c = nod_c.parinte
        return drum


    def contine_in_drum(self, nod):
        """
            Functie care verifica daca nodul "nod" se afla in drumul dintre radacina si nodul curent (self).
            Verificarea se face mergand din parinte in parinte pana la radacina
            Se compara doar informatiile nodurilor (proprietatea info)
            Returnati True sau False.

            "nod" este obiect de tip Nod (are atributul "nod.info")
            "self" este obiect de tip NodParcurgere (are "self.nod_graf.info")
        """
        ### TO DO ... DONE
        nod_c = self
        while nod_c.parinte is not None :
            if nod.info == nod_c.nod_graf.info:
                return True
            nod_c = nod_c.parinte
        return False


    #se modifica in functie de problema
    def expandeaza(self):
        """Pentru nodul curent (self) parinte, trebuie sa gasiti toti succesorii (fiii)
        si sa returnati o lista de tupluri (nod_fiu, cost_muchie_tata_fiu),
        sau lista vida, daca nu exista niciunul.
        (Fiecare tuplu contine un obiect de tip Nod si un numar.)
        """
        ### TO DO ... DONE
        l_succesori = []

        # aflam pozitia curenta a mesajului
        row_curent, column_curent = self.nod_graf.info.poz_mesaj[0], self.nod_graf.info.poz_mesaj[1]

        #costul pentru fiecare mutare va fi 1
        cost = 1

        miscari = [[0, 1], [1, 0], [0, -1], [-1, 0]]        # mutarile posibile (drepta, fata, stanga, spate)

        for miscare in miscari:
            # pozitia noua a mesajului
            poz_mesaj_nou = [row_curent + miscare[0], column_curent + miscare[1]]
            # verificam daca aceasta nu iese din limitele configuratiei clasei
            if (poz_mesaj_nou[0] >= 0) and (poz_mesaj_nou[0] < rows) and (poz_mesaj_nou[1] >= 0) and (poz_mesaj_nou[1] < columns):
                copil1 = copii[row_curent][column_curent]
                copil2 = copii[poz_mesaj_nou[0]][poz_mesaj_nou[1]]
                # verificam daca se poate trimite mesajul
                if poate_trimite(copil1, copil2):
                    if self.parinte is not None:
                        poz_copil1, poz_copil2 = self.parinte.nod_graf.info.poz_mesaj, self.nod_graf.info.poz_mesaj
                        if poz_copil1[0] < poz_copil2[0]:
                            self.nod_graf.drum = "v"
                        if poz_copil1[0] > poz_copil2[0]:
                            self.nod_graf.drum ="^"
                        if poz_copil1[1] > poz_copil2[1]:
                            if poz_copil1[1] % 2 == 1:
                                self.nod_graf.drum = "<"
                            else:
                                self.nod_graf.drum="<<"
                        if poz_copil1[1] < poz_copil2[1]:
                            if poz_copil1[1] % 2 == 1:
                                self.nod_graf.drum = ">>"
                            else:
                                self.nod_graf.drum = ">"

                    configuratie_noua = Nod_configuratie(copii, copil2)
                    succesor = Nod(configuratie_noua)
                    l_succesori.append((succesor, cost))

        return l_succesori

    #se modifica in functie de problema
    def test_scop(self):
        # descriem si metoda de afisare pentru nodul scop
        if self.nod_graf.info == self.problema.nod_scop:
            if self.parinte != None:
                poz_copil1, poz_copil2 = self.parinte.nod_graf.info.poz_mesaj, self.nod_graf.info.poz_mesaj
                if poz_copil1[0] < poz_copil2[0]:
                    self.nod_graf.drum = "v"
                if poz_copil1[0] > poz_copil2[0]:
                    self.nod_graf.drum = "^"
                if poz_copil1[1] > poz_copil2[1]:
                    if poz_copil1[1] % 2 == 1:
                        self.nod_graf.drum = "<"
                    else:
                        self.nod_graf.drum = "<<"
                if poz_copil1[1] < poz_copil2[1]:
                    if poz_copil1[1] % 2 == 1:
                        self.nod_graf.drum = ">>"
                    else:
                        self.nod_graf.drum = ">"

        return self.nod_graf.info == self.problema.nod_scop

    def __str__(self):
        parinte = self.parinte if self.parinte is None else self.parinte.nod_graf.info.poz_mesaj
        if parinte is None:
            return f"{self.nod_graf.info}"
        else:
            return "{} {} ".format(self.nod_graf.drum, self.nod_graf.info)
            # return "{} {} f:{} h:{},".format(self.nod_graf.drum, self.nod_graf.info, self.f, self.nod_graf.h)


def poate_trimite(copil1, copil2):
    # verificam ca locul sa nu fie liber si copiii sa nu fie certati
    if copil2 == "liber":
        return False
    for pereche in copii_suparati:
        if (copil1 == pereche[0] and copil2 == pereche[1]) or (copil1 == pereche[1] and copil2 == pereche[0]):
            return False

    # luam pozitiile copiilor in banci
    poz_copil1, poz_copil2 = pozitii_in_banci[copil1], pozitii_in_banci[copil2]

    # ordonam copiii crescator dupa coloana
    if poz_copil1[1] > poz_copil2[1]:
        poz_copil1, poz_copil2 = poz_copil2, poz_copil1

    # verificam daca copii se afla pe coloane diferite(daca se intampla acest lucru, intotdeauna primul copil va fi pe o coloana impara, iar celalalt pe una para)
    if (poz_copil1[1] % 2 == 1 and poz_copil2[1] % 2 == 0):
        # verificam daca copiii sunt pe acelasi rand
        if poz_copil1[0] != poz_copil2[0]:
            return False
        # verificam daca se afla in ultimele doua randuri pentru a putea trimite mesajul
        elif poz_copil1[0] + 2 < rows:
            return False
    # daca nu se afla pe coloane diferite, verificam daca sunt unul in spatele celuilalt sau daca sunt colegi de banca(se afla pe acelasi rand)
    elif poz_copil1[0] != poz_copil2[0] and poz_copil1[1] != poz_copil2[1]:
        return False

    return True

""" Algoritmul A* """


def str_info_noduri(l):
    """
        o functie folosita strict in afisari - poate fi modificata in functie de problema
    """
    sir = "["
    for x in l:
        sir += str(x) + " "
    sir += "]"
    return sir


def afis_succesori_cost(l):
    """
        o functie folosita strict in afisari - poate fi modificata in functie de problema
    """
    sir = ""
    for (x, cost) in l:
        sir += "\nnod: "+str(x)+", cost arc:"+ str(cost)
    return sir


def in_lista(l, nod):
    """
        lista "l" contine obiecte de tip NodParcurgere
        "nod" este de tip Nod
    """
    for i in range(len(l)):
        if l[i].nod_graf.info == nod.info:
            return l[i]
    return None

def a_star():
    """
        Functia care implementeaza algoritmul A-star
    """
    ### TO DO ... DONE
    start_time = timeit.default_timer()
    rad_arbore = NodParcurgere(NodParcurgere.problema.nod_start)
    open_list = [rad_arbore]  # open_list va contine elemente de tip NodParcurgere
    closed = []  # closed va contine elemente de tip NodParcurgere
    gasit = False

    while len(open_list) > 0 :
        # print(str_info_noduri(open_list))	# afisam lista open
        # print(str_info_noduri(closed))
        # print("\n")

        nod_curent = open_list.pop(0)	# scoatem primul element din lista open
        closed.append(nod_curent)	# si il adaugam la finalul listei closed

        #testez daca nodul extras din lista open este nod scop (si daca da, ies din bucla while)
        if nod_curent.test_scop():
            gasit = True
            break

        l_succesori = nod_curent.expandeaza()	# contine tupluri de tip (Nod, numar)
        for (nod_succesor, cost_succesor) in l_succesori:
            # "nod_curent" este tatal, "nod_succesor" este fiul curent

            # daca fiul nu e in drumul dintre radacina si tatal sau (adica nu se creeaza un circuit)
            if (not nod_curent.contine_in_drum(nod_succesor)):

                # calculez valorile g si f pentru "nod_succesor" (fiul)
                g_succesor = nod_curent.g + cost_succesor # g-ul tatalui + cost muchie(tata, fiu)
                f_succesor = g_succesor + nod_succesor.h # g-ul fiului + h-ul fiului

                #verific daca "nod_succesor" se afla in closed
                # (si il si sterg, returnand nodul sters in nod_parcg_vechi
                nod_parcg_vechi = in_lista(closed, nod_succesor)

                if nod_parcg_vechi is not None:	# "nod_succesor" e in closed
                    # daca f-ul calculat pentru drumul actual este mai bun (mai mic) decat
                    # 	   f-ul pentru drumul gasit anterior (f-ul nodului aflat in lista closed)
                    # atunci actualizez parintele, g si f
                    # si apoi voi adauga "nod_nou" in lista open
                    if (f_succesor < nod_parcg_vechi.f):
                        closed.remove(nod_parcg_vechi)	# scot nodul din lista closed
                        nod_parcg_vechi.parinte = nod_curent # actualizez parintele
                        nod_parcg_vechi.g = g_succesor	# actualizez g
                        nod_parcg_vechi.f = f_succesor	# actualizez f
                        nod_nou = nod_parcg_vechi	# setez "nod_nou", care va fi adaugat apoi in open

                else :
                    # daca nu e in closed, verific daca "nod_succesor" se afla in open_list
                    nod_parcg_vechi = in_lista(open_list, nod_succesor)

                    if nod_parcg_vechi is not None:	# "nod_succesor" e in open
                        # daca f-ul calculat pentru drumul actual este mai bun (mai mic) decat
                        # 	   f-ul pentru drumul gasit anterior (f-ul nodului aflat in lista open)
                        # atunci scot nodul din lista open
                        # 		(pentru ca modificarea valorilor f si g imi va strica sortarea listei open)
                        # actualizez parintele, g si f
                        # si apoi voi adauga "nod_nou" in lista open (la noua pozitie corecta in sortare)
                        if (f_succesor < nod_parcg_vechi.f):
                            open_list.remove(nod_parcg_vechi)
                            nod_parcg_vechi.parinte = nod_curent
                            nod_parcg_vechi.g = g_succesor
                            nod_parcg_vechi.f = f_succesor
                            nod_nou = nod_parcg_vechi

                    else: # cand "nod_succesor" nu e nici in closed, nici in open
                        nod_nou = NodParcurgere(nod_graf=nod_succesor, parinte=nod_curent, g=g_succesor)
                        # se calculeaza f automat in constructor

                if nod_nou:
                    # inserare in lista sortata crescator dupa f
                    # (si pentru f-uri egale descrescator dupa g)
                    i=0
                    while i < len(open_list):
                        if open_list[i].f < nod_nou.f:
                            i += 1
                        else:
                            while i < len(open_list) and open_list[i].f == nod_nou.f and open_list[i].g > nod_nou.g:
                                i += 1
                            break

                    open_list.insert(i, nod_nou)


    nume_fisier_scriere = str("output_" + str(file_index) + ".txt")
    output_file = open(nume_fisier_scriere, "a")
    if euristica_aleasa == 1:
        output_file.write("\nEuristica euclidiana")
    elif euristica_aleasa == 2:
        output_file.write("\nEuristica Manhattan")
    elif euristica_aleasa == 3:
        output_file.write("\nEuristica inadmisibila")
    output_file.write("\n------------------ Concluzie -----------------------\n")
    if len(open_list) == 0 and not gasit:
        output_file.write("Lista open e vida, nu avem drum de la nodul start la nodul scop")
    else:
        output_file.write("Drum de cost minim: \n" + str_info_noduri(nod_curent.drum_arbore()))
    output_file.write("\nTimp de executie: ")
    output_file.write(str(timeit.default_timer() - start_time) + "\n")
    output_file.close()

def citire_date(fisier):
    copii_suparati = []
    pozitii_in_banci = {}
    copii = []
    rows = 0
    columns = 0

    with open(fisier) as fp:
        line = fp.readline().split()
        columns = len(line)
        while line:
            if line[0] == "suparati":
                break
            for i in range(len(line)):
                pozitii_in_banci[line[i]] = (rows, i)
            copii.append(line)
            line = fp.readline().split()
            rows += 1
        line = fp.readline().split()
        while line:
            if line[0] == "mesaj:":
                break
            copii_suparati.append(line)
            line = fp.readline().split()
        mesaj_start = line[1]
        mesaj_stop = line[3]

    return rows, columns, mesaj_start, mesaj_stop, copii_suparati, copii, pozitii_in_banci

def afis_copii(copii):
    for row in copii:
        print(row)

if __name__ == "__main__":
    index_list = ['1', '2', '3', '4']
    raspuns_valid = False
    while not raspuns_valid:
        file_index = input("Numar fisier intrare: 1(fara solutii), 2(starea initiala este si finala), 3(drum de lungime 3-5), 4(drum de lungime>5): ")
        if file_index in index_list:
            raspuns_valid = True
        else:
            print("Nu ati ales un index valid")

    raspuns_valid = False
    while not raspuns_valid:
        euristica_aleasa = int(input("Alegeti euristina: \n1: Euclidiana\n2: Manhattan\n3. Inadmisibila"))
        if euristica_aleasa in [1,2,3]:
            raspuns_valid = True
        else:
            print("Nu ati ales un index valid")

    rows, columns, mesaj_start, mesaj_stop, copii_suparati, copii, pozitii_in_banci = citire_date("input_" + file_index + ".txt")

    configuratie_initiala = Nod_configuratie(copii, mesaj_start)
    configuratie_finala = Nod_configuratie(copii, mesaj_stop)

    problema = Problema()
    NodParcurgere.problema = problema
    a_star()
