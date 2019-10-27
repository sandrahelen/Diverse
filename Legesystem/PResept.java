//Del B: Resepter

//Utvider klassen HvitResept med PResept
class PResept extends HvitResept
{
  //Definerer variabel til subklassen
  private int rabatt = 116;

  protected PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p)
  {
    //Henter instansvariabler fra superklassen
    super(legemiddel, utskrivendeLege, p, 3);
  }

  //Overskriver metoden
  //Returnerer pris aa betale med 116 kr rabatt
  @Override
  public double prisAaBetale()
  {
    if (legemiddel.hentPris() - rabatt >= 0)
    {
      return (legemiddel.hentPris() - 116);
    }
    else
    {
      return 0;
    }
  }

  @Override
  public String toString()
  {
    return super.toString() + "\nPris aa betale: " + prisAaBetale();
  }
}
