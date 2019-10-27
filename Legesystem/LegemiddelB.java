//Del A: Legemiddel B - Vanedannende

//Utvider klassen Legemiddel med subklassen LegemiddelB
class LegemiddelB extends Legemiddel
{
  //Definerer variabel til subklassen
  int styrke;

  //Konstruktor
  protected LegemiddelB(String navn, double pris, double virkestoff, int styrke)
  {
    //Henter instansvariabler fra superklassen.
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  //returnerer vanedannende styrke.
  protected int hentVanedannendeStyrke()
  {
    return this.styrke;
  }

  @Override
  public String toString()
  {
    return super.toString() + "Vanedannende styrke: " + hentVanedannendeStyrke() + "\n";
  }
}
