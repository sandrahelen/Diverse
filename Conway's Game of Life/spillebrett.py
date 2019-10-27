##
#Oblig 7: Spillebrett
#
from random import randint

#Importerer klassen Celle.
#
from celle import Celle

#Oppretter klassen Spillebrett.
#
class Spillebrett:

    #Konstruktor.
    #Definerer variablene rader, kolonner og generasjonsnummer.
    #Oppretter en tom liste, 'rutenett'.
    #
    def __init__(self, rader, kolonner):
        self._rader = rader
        self._kolonner = kolonner
        self._rutenett = []
        self._generasjonsnummer = 0

        #Bruker en for-lokke for aa opprette rader i rutenettet.
        #Lager en tom liste, 'rad'.
        #Bruker en for-lokke for aa opprette kolonner i rutenettet.
        #Legger til celle-objekt i listen 'rad', slik at det er et celle-objekt i hver rad.
        #Legger listen 'rad' inn i listen 'rutenett',
        #slik at det er et celle-objekt i hver kolonne.
        #
        for i in range(self._rader):
            rad = []
            for j in range(self._kolonner):
                rad.append(Celle())
            self._rutenett.append(rad)

        #Kaller paa metoden 'generer'.
        #
        self.generer()


    #Definerer metoden 'tegnBrett'.
    #Bruker en for-lokke for aa gaa inn i hver enkeltcelle i rutenettet.
    #Kaller paa metoden 'hentStatusTegn' og skriver ut statusen til cellen.
    #
    def tegnBrett(self):
        for rad in self._rutenett:
            for celle in rad:
                print(celle.hentStatusTegn(), end="")
            print("")


    #Definerer metoden 'oppdatering'.
    #Oppretter to tomme lister, en for doede og en for levende celler.
    #
    def oppdatering(self):
        doed = []
        levende = []

        #Bruker en nostet-for-lokke for aa iterer gjennom alle cellene i rutenettet.
        #Definerer 'rutenett' som celle.
        #Definerer variabelen 'levendeNabo'.
        #Kaller paa metoden 'finnNabo' i variabelen 'naboer'.
        #Bruker en for-lokke for aa gaa gjennom alle naboene til cellen.
        #Kaller paa metoden 'erLevende' for aa sjekke om naboen til cellen er levende.
        #Dersom naboen lever oker 'levendeNabo' med 1.
        #Kaller paa metoden 'erLevende' for aa sjekke om cellen er levende.
        #Dersom cellen er levende og har mindre enn 2 eller mer enn 3 levende naboer,
        #skal cellen legges i listen 'doed'.
        #Hvis cellen er doed og har 3 levende naboer, skal cellen legges i listen 'levende'.
        #
        for i in range(self._rader):
            for j in range(self._kolonner):
                celle = self._rutenett[i][j]
                levendeNabo = 0
                naboer = self.finnNabo(i, j)
                for nabo in naboer:
                    if nabo.erLevende() == True:
                        levendeNabo += 1
                if celle.erLevende() == True:
                    if levendeNabo < 2 or levendeNabo > 3:
                        doed.append(celle)
                else:
                    if levendeNabo == 3:
                        levende.append(celle)

        #Bruker en for-lokke og gaar gjennom alle elementene i listen 'levende'.
        #Kaller paa metoden 'settLevende', slik at cellene i listen blir levende.
        #
        for e in levende:
            e.settLevende()

        #Bruker en for-lokke og gaar gjennom alle elementene i listen 'doed'.
        #Kaller paa metoden 'settDoed', slik at cellene i listen blir doede.
        #
        for f in doed:
            f.settDoed()

        #Oker generasjonsnummeret med 1.
        #
        self._generasjonsnummer += 1

    #Definerer metoden 'generasjon'.
    #Returnerer generasjonsnummeret.
    #
    def generasjon(self):
        return self._generasjonsnummer

    #Definerer metoden 'finnAntallLevende'.
    #Setter variabelen 'antallLevende' til 0.
    #Bruker en nostet-for-lokke for aa se paa hver enkelt celle i rutenettet.
    #Definerer rad og kolonne i rutenettet som 'celle'.
    #Hvis cellen er levende skal 'antallLevende' oke med 1.
    #Dersom cellen er doed skal 'antallLevende' forbli det samme.
    #Returnerer antallet celler som er levende.
    #
    def finnAntallLevende(self):
        antallLevende = 0
        for rad in range(self._rader):
            for kolonne in range(self._kolonner):
                celle = self._rutenett[rad][kolonne]
                if celle.erLevende() == True:
                    antallLevende += 1
                else:
                    antallLevende = antallLevende
        return antallLevende

    #Definerer metoden 'generer'.
    #Denne metoden genererer et tilfeldig tall for hver celle (her mellom 0 og 3)
    #og sammenligner det med et statisk sjekktall (her 3).
    #Dersom tallene matcher vil cellen settes til 'levende'.
    #
    def generer(self):
        for i in range(self._rader):
            for j in range(self._kolonner):
                rand = randint(0, 3)
                if rand == 3:
                    self._rutenett[i][j].settLevende()

    #Definerer metoden 'finnNabo' med instansvariablene 'rad' og 'kolonne'.
    #Metoden går gjennom de ni mulige plassene rundt en celle,
    #men luker ut senteret (som er selve cellen og ikke en nabo),
    #samt plasseringer som er for små eller for store til å eksistere i
    #spillebrettet (utenfor kanten av brettet).
    #Alle gyldige naboer legges i en liste av naboer som returneres.
    #
    def finnNabo(self, rad, kolonne):
        naboliste = []
        for i in range(-1, 2):
            for j in range(-1, 2):
                naboRad = rad + i
                naboKolonne = kolonne + j
                if (naboRad == rad and naboKolonne == kolonne) != True:
                    if (naboRad < 0 or naboKolonne < 0 or naboRad > self._rader - 1 or naboKolonne > self._kolonner - 1) != True:
                        naboliste.append(self._rutenett[naboRad][naboKolonne])
        return naboliste
