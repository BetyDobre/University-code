import time
import copy

class Joc:
    """
    Clasa care defineste jocul. Se va schimba de la un joc la altul.
    """
    NR_LINII = 8
    NR_COLOANE = 8
    JMIN = None
    JMAX = None
    GOL = '_'
    SIMBOLURI_JOC = ['a', 'n']

    def __init__(self, tabla=None):
        if tabla != None:
            self.matr = tabla
        else:
            self.matr = [['_', 'a', '_', 'a', '_', 'a', '_', 'a'],
                         ['a', '_', 'a', '_', 'a', '_', 'a', '_'],
                         ['_', 'a', '_', 'a', '_', 'a', '_', 'a'],
                         ['_', '_', '_', '_', '_', '_', '_', '_'],
                         ['_', '_', '_', '_', '_', '_', '_', '_'],
                         ['n', '_', 'n', '_', 'n', '_', 'n', '_'],
                         ['_', 'n', '_', 'n', '_', 'n', '_', 'n'],
                         ['n', '_', 'n', '_', 'n', '_', 'n', '_']]

    # functie recursiva pentru cazul in care se pot efectua mai multe mutari deodata(cand se ia o piesa a adversarului)
    def mutare_multipla(self, i, j, jucator, l_mutari, tabla):
        juc_opus = Joc.JMIN if jucator == Joc.JMAX else Joc.JMAX

        # daca jucatorul e JMAX si nu e rege se poate deplasa doar in sus(stanga, dreapta)
        if jucator == 'n':
            ok = False
            if i - 1 >= 0 and j + 1 < Joc.NR_COLOANE and i - 2 >= 0 and j + 2 < Joc.NR_COLOANE:
                # verificam daca poate lua o piesa
                ok = (tabla[i - 1][j + 1] == juc_opus and tabla[i - 2][j + 2] == Joc.GOL) or (tabla[i - 1][j - 1] == juc_opus and tabla[i - 2][j - 2] == Joc.GOL)
            if not ok :
                # verificam daca trebuie facut rege(a ajuns in capatul celalalt al tablei)
                if i == 0:
                    tabla[i][j] = jucator.upper()
                else:
                    tabla[i][j] = jucator
                l_mutari.append(Joc(tabla))
            else:
                # dreapta
                if i - 1 >= 0 and j + 1 < Joc.NR_COLOANE and i - 2 >= 0 and j + 2 < Joc.NR_COLOANE:
                    if tabla[i - 1][j + 1].lower() == juc_opus.lower() and tabla[i - 2][j + 2] == Joc.GOL:
                        tabla_noua = copy.deepcopy(tabla)
                        tabla_noua[i - 1][j + 1] = Joc.GOL
                        tabla_noua[i][j] = Joc.GOL
                        self.mutare_multipla(i - 2, j + 2, jucator, l_mutari, tabla_noua)
                # stanga
                if i - 1 >= 0 and j - 1 >= 0 and i - 2 >= 0 and j - 2 >= 0:
                    if tabla[i - 1][j - 1].lower() == juc_opus.lower() and tabla[i - 2][j - 2] == Joc.GOL:
                        tabla_noua = copy.deepcopy(tabla)
                        tabla_noua[i - 1][j - 1] = Joc.GOL
                        tabla_noua[i][j] = Joc.GOL
                        self.mutare_multipla(i - 2, j - 2, jucator, l_mutari, tabla_noua)

        # daca jucatorul e JMIN si nu e rege se poate deplasa doar in jos(stanga, dreapta)
        if jucator == 'a':
            ok = False
            if i + 1 < Joc.NR_LINII and j + 1 < Joc.NR_COLOANE and  i + 2 < Joc.NR_LINII and j + 2 < Joc.NR_COLOANE:
                # verificam daca poate lua o piesa
                ok = (tabla[i + 1][j + 1] == juc_opus and tabla[i + 2][j + 2] == Joc.GOL) or (tabla[i + 1][j - 1] == juc_opus and tabla[i + 2][j - 2] == Joc.GOL)
            if not ok:
                # verificam daca trebuie facut rege
                if i == Joc.NR_LINII - 1:
                    tabla[i][j] = jucator.upper()
                else:
                    tabla[i][j] = jucator
                l_mutari.append(Joc(tabla))
            else:
                #dreapta
                if i + 1 < Joc.NR_LINII and j + 1 < Joc.NR_COLOANE and  i + 2 < Joc.NR_LINII and j + 2 < Joc.NR_COLOANE:
                    if tabla[i + 1][j + 1].lower() == juc_opus.lower() and tabla[i + 2][j + 2] == Joc.GOL:
                        tabla_noua = copy.deepcopy(tabla)
                        tabla_noua[i + 1][j + 1] = Joc.GOL
                        tabla_noua[i][j] = Joc.GOL
                        self.mutare_multipla(i + 2, j + 2, jucator, l_mutari, tabla_noua)

                #stanga
                if i + 1 < Joc.NR_LINII and j - 1 >= 0 and  i + 2 < Joc.NR_LINII and j - 2 >= 0 :
                    if tabla[i + 1][j - 1].lower() == juc_opus.lower() and tabla[i + 2][j - 2] == Joc.GOL:
                        tabla_noua = copy.deepcopy(tabla)
                        tabla_noua[i + 1][j - 1] = Joc.GOL
                        tabla_noua[i][j] = Joc.GOL
                        self.mutare_multipla(i + 2, j - 2, jucator, l_mutari, tabla_noua)

    # functie recursiva pentru cazul in care jucatorul este rege si poate efectua mai multe mutari deodata(captureaza piese)
    def mutare_multipla_rege(self, i, j, jucator, l_mutari, tabla):
        juc_opus = Joc.JMIN if jucator == Joc.JMAX else Joc.JMAX

        # testam daca poate captura piese ca si rege
        ok = False
        if (i - 2 >= 0 and j + 2 < Joc.NR_COLOANE):
            ok = ok or (self.matr[i - 1][j + 1].upper() == juc_opus.upper() and self.matr[i - 2][j + 2] == Joc.GOL)
        if (i - 2 >= 0 and j - 2 >= 0):
            ok = ok or (self.matr[i - 1][j - 1].upper() == juc_opus.upper() and self.matr[i - 2][j - 2] == Joc.GOL)
        if (i + 2 < Joc.NR_LINII and j + 2 < Joc.NR_COLOANE):
            ok = ok or (self.matr[i + 1][j + 1].upper() == juc_opus.upper() and self.matr[i + 2][j + 2] == Joc.GOL)
        if (i + 2 < Joc.NR_LINII and j - 2 >= 0):
            ok = ok or (self.matr[i + 1][j - 1].upper() == juc_opus.upper() and self.matr[i + 2][j - 2] == Joc.GOL)

        if not ok :
            if i == 0 or i == Joc.NR_LINII - 1:
                tabla[i][j] = jucator.upper()
            else:
                tabla[i][j] = jucator
            l_mutari.append(Joc(tabla))

        else:
            #stanga sus
            if i - 2 >= 0 and j - 2 >= 0 :
                if tabla[i - 1][j - 1].lower() == juc_opus.lower() and tabla[i - 2][j - 2] == Joc.GOL:
                    tabla_noua = copy.deepcopy(tabla)
                    tabla_noua[i - 1][j - 1] = Joc.GOL
                    tabla_noua[i][j] = Joc.GOL
                    self.mutare_multipla(i - 2, j - 2, jucator,l_mutari, tabla_noua)

            #dreapta sus
            if i - 2 >= 0 and j + 2 < Joc.NR_COLOANE:
                if tabla[i - 1][j + 1].lower() == juc_opus.lower() and tabla[i - 2][j + 2] == Joc.GOL:
                    tabla_noua = copy.deepcopy(tabla)
                    tabla_noua[i - 1][j + 1] = Joc.GOL
                    tabla_noua[i][j] = Joc.GOL
                    self.mutare_multipla(i - 2, j + 2, jucator,l_mutari, tabla_noua)

            #stanga jos
            if  i + 2 < Joc.NR_LINII and j - 2 >= 0:
                if tabla[i + 1][j - 1].lower() == juc_opus.lower() and tabla[i + 2][j - 2] == Joc.GOL:
                    tabla_noua = copy.deepcopy(tabla)
                    tabla_noua[i + 1][j - 1] = Joc.GOL
                    tabla_noua[i][j] = Joc.GOL
                    self.mutare_multipla(i + 2, j - 2, jucator,l_mutari, tabla_noua)

            #dreapta jos
            if i + 2 < Joc.NR_LINII and j + 2 < Joc.NR_COLOANE:
                if tabla[i + 1][j + 1].lower() == juc_opus.lower() and tabla[i + 2][j + 2] == Joc.GOL:
                    tabla_noua = copy.deepcopy(tabla)
                    tabla_noua[i + 1][j + 1] = Joc.GOL
                    tabla_noua[i][j] = Joc.GOL
                    self.mutare_multipla(i + 2, j + 2, jucator,l_mutari, tabla_noua)


    def verifica_mutare(self, i, j, jucator, l_mutari):
        juc_opus = Joc.JMIN if jucator == Joc.JMAX else Joc.JMAX

        if jucator == 'n':
            # daca este gol in stanga sus
            if i - 1 >= 0 and j - 1 >=0:
                if self.matr[i - 1][j - 1] == Joc.GOL:
                    tabla_noua = copy.deepcopy(self.matr)
                    tabla_noua[i][j] = Joc.GOL
                    if i - 1 == 0:
                        tabla_noua[i - 1][j - 1] = jucator.upper()
                    else:
                        tabla_noua[i - 1][j - 1] = jucator
                    l_mutari.append(Joc(tabla_noua))
            # daca este gol in dreapta sus
            if i - 1 >= 0 and j + 1 < Joc.NR_COLOANE:
                if self.matr[i - 1][j + 1] == Joc.GOL:
                    tabla_noua = copy.deepcopy(self.matr)
                    tabla_noua[i][j] = Joc.GOL
                    if i - 1 == 0:
                        tabla_noua[i - 1][j + 1] = jucator.upper()
                    else:
                        tabla_noua[i - 1][j + 1] = jucator
                    l_mutari.append(Joc(tabla_noua))

            # daca jucatorul poate captura o piesa atunci vedem daca poate captura mai multe deodata
            ok = False
            if (i - 2 >= 0 and j - 2 >= 0):
                ok = (self.matr[i - 1][j - 1].lower() == juc_opus.lower() and self.matr[i - 2][j - 2] == Joc.GOL)
            if (i - 2 >= 0 and j + 2 < Joc.NR_COLOANE):
                ok = ok or (self.matr[i - 1][j + 1].upper() == juc_opus.upper() and self.matr[i - 2][j + 2] == Joc.GOL)

            if ok :
                tabla_noua = copy.deepcopy(self.matr)
                self.mutare_multipla(i, j, jucator, l_mutari, tabla_noua)

        if jucator == 'a':
            # daca este liber stanga jos
            if i + 1 < Joc.NR_LINII and j - 1 >= 0:
                if self.matr[i + 1][j - 1] == Joc.GOL:
                    tabla_noua = copy.deepcopy(self.matr)
                    tabla_noua[i][j] = Joc.GOL
                    if i + 1 == Joc.NR_LINII - 1:
                        tabla_noua[i + 1][j - 1] = jucator.upper()
                    else:
                        tabla_noua[i + 1][j - 1] = jucator
                    l_mutari.append(Joc(tabla_noua))

            # daca este liber dreapta jos
            if i + 1 < Joc.NR_LINII and j + 1 < Joc.NR_COLOANE:
                if self.matr[i + 1][j + 1] == Joc.GOL:
                    tabla_noua = copy.deepcopy(self.matr)
                    tabla_noua[i][j] = Joc.GOL
                    if i + 1 == Joc.NR_LINII - 1:
                        tabla_noua[i + 1][j + 1] = jucator.upper()
                    else:
                        tabla_noua[i + 1][j + 1] = jucator
                    l_mutari.append(Joc(tabla_noua))

            ok = False
            if (i + 2 < Joc.NR_LINII and j - 2 >= 0):
                # daca jucatorul poate captura o piesa atunci vedem daca poate captura mai multe deodata
                ok = (self.matr[i + 1][j - 1].lower() == juc_opus.lower() and self.matr[i + 2][j - 2] == Joc.GOL)
            if (i + 2 < Joc.NR_COLOANE and j + 2 < Joc.NR_COLOANE):
                ok = ok or (self.matr[i + 1][j + 1].upper() == juc_opus.upper() and self.matr[i + 2][j + 2] == Joc.GOL)
            if ok:
                tabla_noua = copy.deepcopy(self.matr)
                self.mutare_multipla(i, j, jucator, l_mutari, tabla_noua)

    def verificare_mutare_rege(self, i, j, jucator, l_mutari):
        juc_opus = Joc.JMIN if jucator == Joc.JMAX else Joc.JMAX

        #pentru rege trebuie verificate toate pozitiile

        #stanga sus gol
        if i - 1 >= 0 and j - 1 >=0:
            if self.matr[i - 1][j - 1] == Joc.GOL:
                tabla_noua = copy.deepcopy(self.matr)
                tabla_noua[i][j] = Joc.GOL
                tabla_noua[i - 1][j - 1] = jucator.upper()
                l_mutari.append(Joc(tabla_noua))

        #dreapta sus gol
        if i - 1 >= 0 and j + 1 <Joc.NR_COLOANE:
            if self.matr[i - 1][j + 1] == Joc.GOL:
                tabla_noua = copy.deepcopy(self.matr)
                tabla_noua[i][j] = Joc.GOL
                tabla_noua[i - 1][j + 1] = jucator.upper()
                l_mutari.append(Joc(tabla_noua))

        #stanga jos gol
        if i + 1 < Joc.NR_LINII and j - 1 >=0:
            if self.matr[i + 1][j - 1] == Joc.GOL:
                tabla_noua = copy.deepcopy(self.matr)
                tabla_noua[i][j] = Joc.GOL
                tabla_noua[i + 1][j - 1] = jucator.upper()
                l_mutari.append(Joc(tabla_noua))

        #dreapta jos gol
        if i + 1 < Joc.NR_LINII and j + 1 < Joc.NR_COLOANE:
            if self.matr[i + 1][j + 1] == Joc.GOL:
                tabla_noua = copy.deepcopy(self.matr)
                tabla_noua[i][j] = Joc.GOL
                tabla_noua[i + 1][j + 1] = jucator.upper()
                l_mutari.append(Joc(tabla_noua))

        ok = False
        if (i - 2 >=0 and j + 2 < Joc.NR_COLOANE):
            ok = ok or (self.matr[i - 1][j + 1].upper() == juc_opus.upper() and self.matr[i - 2][j + 2] == Joc.GOL)
        if (i - 2 >= 0 and j - 2 >= 0):
            ok = ok or (self.matr[i - 1][j - 1].upper() == juc_opus.upper() and self.matr[i - 2][j - 2] == Joc.GOL)
        if (i + 2 < Joc.NR_LINII and j + 2 < Joc.NR_COLOANE):
            ok = ok or (self.matr[i + 1][j + 1].upper() == juc_opus.upper() and self.matr[i + 2][j + 2] == Joc.GOL)
        if (i + 2 < Joc.NR_LINII and j - 2 >= 0):
            ok = ok or (self.matr[i + 1][j - 1].upper() == juc_opus.upper() and self.matr[i + 2][j - 2] == Joc.GOL)

        # daca jucatorul poate captura o piesa atunci vedem daca poate captura mai multe deodata
        if ok :
            tabla_noua = copy.deepcopy(self.matr)
            self.mutare_multipla_rege(i, j, jucator, l_mutari, tabla_noua)

    def mutari_joc(self, jucator):
        l_mutari = []

        for i in range(0, Joc.NR_LINII):
            for j in range(0, Joc.NR_COLOANE):
                if self.matr[i][j] == jucator:
                    self.verifica_mutare(i, j, jucator,l_mutari)
                if self.matr[i][j] == jucator.upper():
                    self.verificare_mutare_rege(i, j, jucator, l_mutari)

        return l_mutari

    # functie folosita pentru determinarea pozitiilor in care jucatorul are voie sa se miste si din care trebuie sa aleaga
    def pozitii(self, jucator):
        mutari = self.mutari_joc(jucator)

        l_pozitii = []
        for tabla in mutari:
            for i in range(0, Joc.NR_LINII):
                for j in range(0, Joc.NR_COLOANE):
                    # daca elemntul din matricea curenta si cea de mutari nu se potriveste, iar jucatorul este cel pe care il cautam noi, adaugam pozitia
                    if self.matr[i][j] != tabla.matr[i][j] and tabla.matr[i][j].upper() == jucator.upper():
                        l_pozitii.append(([i, j], tabla))

        return l_pozitii

    # numarul de piese corespunzatoare jucatorului de pe tabla
    def count_piese_tabla(self, jucator):
        nr = 0
        for i in range(0, Joc.NR_LINII):
            for j in range(0, Joc.NR_COLOANE):
                if self.matr[i][j].upper() == jucator.upper():
                    nr += 1
        return nr

    # numarul de regi corespunzatoare jucatorului de pe tabla
    def count_regi_tabla(self, jucator):
        nr = 0
        for i in range(0, Joc.NR_LINII):
            for j in range(0, Joc.NR_COLOANE):
                if self.matr[i][j] == jucator.upper():
                    nr += 1
        return nr

    def final(self):
        mutari_jmax = self.pozitii(Joc.JMAX)
        mutari_jmin = self.pozitii(Joc.JMIN)

        # daca nu mai are piese pe tabla sau mutari valide
        if self.count_piese_tabla(Joc.JMAX) == 0 or len(mutari_jmax) == 0:
            return Joc.JMIN

        if self.count_piese_tabla(Joc.JMIN) == 0 or len(mutari_jmin) == 0:
            return Joc.JMAX

        # daca niciun jucator nu mai are mutari valide inseamna ca este remiza
        if len(mutari_jmin) == 0 and len(mutari_jmax) == 0:
            if self.count_piese_tabla(Joc.JMIN) > self.count_piese_tabla(Joc.JMAX):
                return Joc.MIN
            elif self.count_piese_tabla(Joc.JMIN) < self.count_piese_tabla(Joc.JMAX):
                return Joc.MAX
            else:
                return 'remiza'

        return False

    def fct_euristica(self):
        return self.count_piese_tabla(Joc.JMAX) - self.count_piese_tabla(Joc.JMIN)

    def fct_euristica2(self):
        return  (self.count_piese_tabla(Joc.JMAX) + 2 * self.count_regi_tabla(Joc.JMAX)) - (self.count_piese_tabla(Joc.JMIN) + 2 * self.count_regi_tabla(Joc.JMIN))

    def estimeaza_scor(self, adancime):
        t_final = self.final()
        if t_final == Joc.JMAX :
            return (999+adancime)
        elif t_final == Joc.JMIN:
            return (-999-adancime)
        elif t_final == 'remiza':
            return 0
        else:
            return self.fct_euristica()

    def __str__(self):
        sir = "   0 1 2 3 4 5 6 7\n"
        for i in range(0, Joc.NR_LINII):
            sir += str(i) + ': '
            for j in range(0, Joc.NR_COLOANE):
                sir = sir + self.matr[i][j] + ' '
            sir += "\n"
        return sir


class Stare:
    """
    Clasa folosita de algoritmii minimax si alpha-beta
    Are ca proprietate tabla de joc
    Functioneaza cu conditia ca in cadrul clasei Joc sa fie definiti JMIN si JMAX (cei doi jucatori posibili)
    De asemenea cere ca in clasa Joc sa fie definita si o metoda numita mutari_joc() care ofera lista cu
    configuratiile posibile in urma mutarii unui jucator
    """

    ADANCIME_MAX = None

    def __init__(self, tabla_joc, j_curent, adancime, parinte=None, scor=None):
        self.tabla_joc = tabla_joc  # un obiect de tip Joc => „tabla_joc.matr”
        self.j_curent = j_curent  # simbolul jucatorului curent

        # adancimea in arborele de stari
        #	(scade cu cate o unitate din „tata” in „fiu”)
        self.adancime = adancime

        # scorul starii (daca e finala, adica frunza a arborelui)
        # sau scorul celei mai bune stari-fiice (pentru jucatorul curent)
        self.scor = scor

        # lista de mutari posibile din starea curenta
        self.mutari_posibile = []  # lista va contine obiecte de tip Stare

        # cea mai buna mutare din lista de mutari posibile pentru jucatorul curent
        self.stare_aleasa = None

    def jucator_opus(self):
        if self.j_curent == Joc.JMIN:
            return Joc.JMAX
        else:
            return Joc.JMIN

    def mutari_stare(self):
        l_mutari = self.tabla_joc.mutari_joc(self.j_curent)
        juc_opus = self.jucator_opus()

        l_stari_mutari = [Stare(mutare, juc_opus, self.adancime - 1, parinte=self) for mutare in l_mutari]

        return l_stari_mutari

    def __str__(self):
        sir = str(self.tabla_joc) + "Jucatorul curent:" + self.j_curent + "\n"
        return sir


""" Algoritmul MinMax """


def min_max(stare):
    # Daca am ajuns la o frunza a arborelui, adica:
    # - daca am expandat arborele pana la adancimea maxima permisa
    # - sau daca am ajuns intr-o configuratie finala de joc
    if stare.adancime == 0 or stare.tabla_joc.final():
        # calculam scorul frunzei apeland "estimeaza_scor"
        stare.scor = stare.tabla_joc.estimeaza_scor(stare.adancime)
        return stare

    # Altfel, calculez toate mutarile posibile din starea curenta
    stare.mutari_posibile = stare.mutari_stare()

    # aplic algoritmul minimax pe toate mutarile posibile (calculand astfel subarborii lor)
    mutari_scor = [min_max(mutare) for mutare in stare.mutari_posibile]

    if stare.j_curent == Joc.JMAX:
        # daca jucatorul e JMAX aleg starea-fiica cu scorul maxim
        stare.stare_aleasa = max(mutari_scor, key=lambda x: x.scor)
    else:
        # daca jucatorul e JMIN aleg starea-fiica cu scorul minim
        stare.stare_aleasa = min(mutari_scor, key=lambda x: x.scor)

    # actualizez scorul „tatalui” = scorul „fiului” ales
    stare.scor = stare.stare_aleasa.scor
    return stare


def alpha_beta(alpha, beta, stare):
    # Daca am ajuns la o frunza a arborelui, adica:
    # - daca am expandat arborele pana la adancimea maxima permisa
    # - sau daca am ajuns intr-o configuratie finala de joc
    if stare.adancime == 0 or stare.tabla_joc.final():
        # calculam scorul frunzei apeland "estimeaza_scor"
        stare.scor = stare.tabla_joc.estimeaza_scor(stare.adancime)
        return stare

    # Conditia de retezare:
    if alpha >= beta:
        return stare  # este intr-un interval invalid, deci nu o mai procesez

    # Calculez toate mutarile posibile din starea curenta (toti „fiii”)
    stare.mutari_posibile = stare.mutari_stare()

    if stare.j_curent.lower() == Joc.JMAX.lower():
        scor_curent = float('-inf')  # scorul „tatalui” de tip MAX

        # pentru fiecare „fiu” de tip MIN:
        for mutare in stare.mutari_posibile:
            # calculeaza scorul fiului curent
            stare_noua = alpha_beta(alpha, beta, mutare)

            # incerc sa imbunatatesc (cresc) scorul si alfa
            # „tatalui” de tip MAX, folosind scorul fiului curent
            if scor_curent < stare_noua.scor:
                stare.stare_aleasa = stare_noua
                scor_curent = stare_noua.scor

            if alpha < stare_noua.scor:
                alpha = stare_noua.scor
                if alpha >= beta:  # verific conditia de retezare
                    break  # NU se mai extind ceilalti fii de tip MIN


    elif stare.j_curent.lower() == Joc.JMIN.lower():
        scor_curent = float('inf')  # scorul „tatalui” de tip MIN

        # pentru fiecare „fiu” de tip MAX:
        for mutare in stare.mutari_posibile:
            stare_noua = alpha_beta(alpha, beta, mutare)

            # incerc sa imbunatatesc (scad) scorul si beta
            # „tatalui” de tip MIN, folosind scorul fiului curent
            if scor_curent > stare_noua.scor:
                stare.stare_aleasa = stare_noua
                scor_curent = stare_noua.scor

            if beta > stare_noua.scor:
                beta = stare_noua.scor
                if alpha >= beta:  # verific conditia de retezare
                    break  # NU se mai extind ceilalti fii de tip MAX

    # actualizez scorul „tatalui” = scorul „fiului” ales
    stare.scor = stare.stare_aleasa.scor

    return stare


def afis_daca_final(stare_curenta):
    final = stare_curenta.tabla_joc.final()
    if (final):
        if (final == "remiza"):
            print("Remiza!")
        else:
            print("A castigat " + final)

        print(f"Scorul jucatorului tau: {stare_curenta.tabla_joc.count_piese_tabla(Joc.JMIN) + 2 * stare_curenta.tabla_joc.count_regi_tabla(Joc.JMIN)}")
        print(f"Scorul calculatorului: {stare_curenta.tabla_joc.count_piese_tabla(Joc.JMAX) + 2 * stare_curenta.tabla_joc.count_regi_tabla(Joc.JMAX)}")
        return True

    return False

def main():
    global  nr_mutari_jucator, nr_mutari_calculator
    # initializare algoritm
    raspuns_valid = False
    while not raspuns_valid:
        tip_algoritm = input("Algorimul folosit? (raspundeti cu 1 sau 2)\n 1.Minimax\n 2.Alpha-Beta\n ")
        if tip_algoritm in ['1', '2']:
            raspuns_valid = True
        else:
            print("Nu ati ales o varianta corecta.")

    # initializare ADANCIME_MAX

    raspuns_valid = False
    nivele = [1, 2, 3]
    while not raspuns_valid:
        n = int(input("Nivelul de dificultate:\n1.Incepator\n2.Mediu\n3.Avansat\n "))
        if n in nivele:
            if n == 1:
                Stare.ADANCIME_MAX = 3
            elif n == 2:
                Stare.ADANCIME_MAX = 4
            else:
                Stare.ADANCIME_MAX = 5
            raspuns_valid = True
        else:
            print("Trebuie sa introduceti un numar natural nenul intre 1 si 3.")

    # initializare jucatori
    raspuns_valid = False
    while not raspuns_valid:
        Joc.JMIN = str(input("Doriti sa jucati cu {} sau cu {}? ".format(Joc.SIMBOLURI_JOC[0], Joc.SIMBOLURI_JOC[1]))).lower()
        if (Joc.JMIN in Joc.SIMBOLURI_JOC):
            raspuns_valid = True
        else:
            print("Raspunsul invalid")

    Joc.JMAX = Joc.SIMBOLURI_JOC[0] if Joc.JMIN == Joc.SIMBOLURI_JOC[1] else Joc.SIMBOLURI_JOC[1]

    # initializare tabla
    tabla_curenta = Joc()
    print("Tabla initiala")
    print(str(tabla_curenta))

    # creare stare initiala
    stare_curenta = Stare(tabla_curenta, 'n', Stare.ADANCIME_MAX)

    while True:
        if (stare_curenta.j_curent == Joc.JMIN):
            # muta jucatorul
            raspuns_valid = False
            tabla_nouaa = None

            t_inainte = int(round(time.time() * 1000))

            while not raspuns_valid:
                mutari_valide = stare_curenta.tabla_joc.pozitii(stare_curenta.j_curent)
                pozitii = [(i, poz[0]) for (i, poz) in enumerate(mutari_valide)]

                print("Mutari posibile: ")
                for i in range(len(pozitii)):
                    print(str(pozitii[i][0]) + ":" + str(pozitii[i][1]))

                alegere = input("Alege pozitia: ")
                if alegere == 'exit':
                    print(f"Scorul jucatorului tau: {stare_curenta.tabla_joc.count_piese_tabla(Joc.JMIN) + 2 * stare_curenta.tabla_joc.count_regi_tabla(Joc.JMIN)}")
                    print( f"Scorul calculatorului: {stare_curenta.tabla_joc.count_piese_tabla(Joc.JMAX) + 2 * stare_curenta.tabla_joc.count_regi_tabla(Joc.JMAX)}")
                    return
                pozitie_aleasa = int(alegere)

                if pozitie_aleasa in range(len(pozitii)):
                    tabla_nouaa = mutari_valide[pozitie_aleasa][1]
                    raspuns_valid = True
                else:
                    print(f"Indice invalid")
                nr_mutari_jucator += 1

            # dupa iesirea din while sigur am valide atat linia cat si coloana
            # deci pot plasa simbolul pe "tabla de joc"
            stare_curenta.tabla_joc = tabla_nouaa

            t_dupa = int(round(time.time() * 1000))
            print("Jucatorul a \"gandit\" timp de " + str(t_dupa - t_inainte) + " milisecunde.")

            # afisarea starii jocului in urma mutarii utilizatorului
            print("\nTabla dupa mutarea jucatorului")
            print(str(stare_curenta))

            # testez daca jocul a ajuns intr-o stare finala
            # si afisez un mesaj corespunzator in caz ca da
            if (afis_daca_final(stare_curenta)):
                break

            # S-a realizat o mutare. Schimb jucatorul cu cel opus
            stare_curenta.j_curent = stare_curenta.jucator_opus()

        # --------------------------------
        else:  # jucatorul e JMAX (calculatorul)
            # Mutare calculator

            # preiau timpul in milisecunde de dinainte de mutare
            t_inainte = int(round(time.time() * 1000))
            if tip_algoritm == '1':
                stare_actualizata = min_max(stare_curenta)
            else:  # tip_algoritm==2
                stare_actualizata = alpha_beta(-500, 500, stare_curenta)
            stare_curenta.tabla_joc = stare_actualizata.stare_aleasa.tabla_joc
            print("Tabla dupa mutarea calculatorului")
            print(str(stare_curenta))

            nr_mutari_calculator += 1

            # preiau timpul in milisecunde de dupa mutare
            t_dupa = int(round(time.time() * 1000))
            print("Calculatorul a \"gandit\" timp de " + str(t_dupa - t_inainte) + " milisecunde.")

            if (afis_daca_final(stare_curenta)):
                break

            # S-a realizat o mutare. Schimb jucatorul cu cel opus
            stare_curenta.j_curent = stare_curenta.jucator_opus()


if __name__ == "__main__":
    t_inainte = int(round(time.time() * 1000))

    nr_mutari_jucator = 0
    nr_mutari_calculator = 0

    main()

    print(f"Numarul de mutari al jucatorului: {nr_mutari_jucator}")
    print(f"Numarul de mutari al calculatorului: {nr_mutari_calculator}")

    t_dupa = int(round(time.time() * 1000))
    print("Jocul a rulat timp de " + str(t_dupa - t_inainte) + " milisecunde.")