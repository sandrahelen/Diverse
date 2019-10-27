##
#Oblig 7: Hovedprogram
#
#Importerer klassen Spillebrett.
#
from spillebrett import Spillebrett

#Definerer prosedyren main.
#Ber brukeren om aa skrive inn antall rader til spillebrettet i variabelen
#'rader'.
#Ber brukeren om aa skrive inn antall kolonner til spillebrettet i variabelen
#'kolonner'.
#Kaller paa klassen 'Spillebrett' med variabelene 'rader' og 'kolonner' som
#parametere.
#Kaller paa metoden 'tegnBrett' for aa tegne spillebrettet.
#Kaller paa metoden 'generasjon', og skirver ut hvilken generasjon av celler
#det er.
#Kaller paa metoden 'finnAntallLevende', og skriver ut hvor mange celler som 
#lever.
#
def main():
    rader = int(input("\nSkriv inn antall rader paa spillebrettet: "))
    kolonner = int(input("Skriv inn antall kolonner paa spillebrettet: "))
    print("")
    brett = Spillebrett(rader, kolonner)
    brett.tegnBrett()
    print("Generasjon:", brett.generasjon())
    print("Antall levende celler:", brett.finnAntallLevende())

    #Ber brukeren om aa presse enter eller skrive 'q'.
    #Hvis brukeren skriver 'q' stopper while-lokken, og spillet avsluttes.
    #Kaller paa metodene 'oppdatering' og 'tegnBrett' for aa tegne et nytt
    #spillebrett til neste generasjon.
    #Kaller paa metoden 'generasjon', og skirver ut hvilken generasjon av celler det er.
    #Kaller paa metoden 'finnAntallLevende', og skriver ut hvor mange celler som lever.
    #
    inp = input("\nPress enter for aa fortsette. Skriv inn q og trykk enter for aa avslutte: ")
    while inp != "q":
        brett.oppdatering()
        brett.tegnBrett()
        print("Generasjon:", brett.generasjon())
        print("Antall levende celler:", brett.finnAntallLevende())
        inp = input("\nPress enter for aa fortsette. Skriv inn q og trykk enter for aa avslutte: ")



#Starte hovedprogrammet.
#Kaller paa prosedyren main, for aa kjore spillet.
#
main()
