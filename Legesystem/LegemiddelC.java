//Del A: Legemiddel C - Vanlig legemiddel

//Utvider klassen Legemiddel med subklassen LegemiddelC
class LegemiddelC extends Legemiddel
{
  //Konstruktor
  protected LegemiddelC(String navn, double pris, double virkestoff)
  {
    //Henter instansvariabler fra superklassen.
    super(navn, pris, virkestoff);
  }

  @Override
  public String toString()
  {
    return super.toString();
  }
}
