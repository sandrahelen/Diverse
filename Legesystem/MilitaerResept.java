//Del B: Resepter

//Utvider HvitResept med MilitaerResept
class MilitaerResept extends HvitResept
{
  protected MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit)
  {
    //Henter instansvariabler fra superklassen
    super(legemiddel, utskrivendeLege, p, reit);
  }

  //Overskriver metoden
  //Returnerer pris aa betale med 100% rabatt
  @Override
  public double prisAaBetale()
  {
    return 0;
  }

  @Override
  public String toString()
  {
    return super.toString() + "Pris aa betale: " + prisAaBetale() + "\n";
  }
}
