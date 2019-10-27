//Del A: Legemiddel A - Narkotisk

//Utvider klassen Legemiddel med subklassen LegemiddelA
class LegemiddelA extends Legemiddel
{
  //Definerer variabel i subklassen
  int styrke;

  //Konstruktor
  protected LegemiddelA(String navn, double pris, double virkestoff, int styrke)
  {
    //Henter instansvariabler fra superklassen.
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  //returnerer narkotisk styrke.
  protected int hentNarkotiskStyrke()
  {
    return this.styrke;
  }

  @Override
  public String toString()
  {
    return super.toString() + "Narkotisk styrke: " + hentNarkotiskStyrke() + "\n";
  }
}
