//Del B: Resepter

//Utvider klassen Resept med HvitResept
class HvitResept extends Resept
{
  //Konstruktor
  protected HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit)
  {
    //Henter instansvariabler fra superklassen
    super(legemiddel, utskrivendeLege, p, reit);
  }

  //returnerer fargen paa resepten
  @Override
  public String farge()
  {
    return "hvit";
  }

  //returnerer pris aa betale
  @Override
  public double prisAaBetale()
  {
    return legemiddel.hentPris();
  }

  @Override
  public String toString()
  {
    return super.toString() + "\nFarge: " + farge();
  }
}
