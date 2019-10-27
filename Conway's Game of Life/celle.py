##
#Oblig 7: Celle
#
#Oppretter klassen Celle.
#
class Celle:

    #Konstruktor.
    #Setter cellen som "doed" i konstruktoren.
    #
    def __init__(self):
        self._levende = False

    #Definerer metoden settDoed.
    #Endrer cellens status til "doed"
    #
    def settDoed(self):
        self._levende = False

    #Definerer metoden settLevende.
    #Endrer cellens status til "levende".
    #
    def settLevende(self):
        self._levende = True

    #Definerer metoden erLevende.
    #Henter status til cellen.
    #Returnerer om cellen er levende eller doed.
    #
    def erLevende(self):
        if self._levende == True:
            return True
        else:
            return False

    #Definerer metoden hentStatusTegn.
    #Henter status til cellen.
    #Dersom cellen er levende blir "O" returnert.
    #Dersom cellen er doed blir "." returnert.
    #
    def hentStatusTegn(self):
        levende = self.erLevende()
        if levende == True:
            return "O"
        else:
            return "."
