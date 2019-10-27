//Del B: Resepter

//Utvider klassen Resept med BlaaResept
class BlaaResept extends Resept
{
  protected BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit)
  {
    //Henter instansvariabler fra superklassen
    super(legemiddel, utskrivendeLege, p, reit);
  }

  //Overskriver metoden
  //Retrunerer fargen paa resepten
  @Override
  public String farge()
  {
    return "blaa";
  }

  //Overskriver metoden
  //Returnerer pris aa betale med 75% rabatt
  @Override
  public double prisAaBetale()
  {
    return ((legemiddel.hentPris() * 25) / 100);
  }

  @Override
  public String toString()
  {
    return super.toString() + "\nFarge: " + farge() + "\nPris aa betale: " + prisAaBetale() + "\n";
  }
}
