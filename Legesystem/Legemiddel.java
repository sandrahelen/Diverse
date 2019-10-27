//Del A: Legemiddel

public class Legemiddel
{
  //Definerer variablene i klassen
  private static int id = -1;
  private int minId;
  private String navn;
  private double pris;
  private double virkestoff;

  //Konstruktor
  public Legemiddel(String navn, double pris, double virkestoff)
  {
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    id ++;
    minId = id;
  }

  //returnerer ID til legemiddel.
  public int hentId()
  {
    return minId;
  }

  //returnerer navn paa legemiddel.
  public String hentNavn()
  {
    return this.navn;
  }

  //returnerer prisen til legemiddel.
  public double hentPris()
  {
    return this.pris;
  }

  //returnerer virkestoffet til legemiddel.
  public double hentVirkestoff()
  {
    return this.virkestoff;
  }

  //returenerer en ny pris til legemiddel.
  public double settNyPris(double nyPris)
  {
    this.pris = nyPris;
    return this.pris;
  }

  @Override
  public String toString()
  {
    return hentId() + ": " + hentNavn()
      + "\nPris: " + hentPris() + " kr" + "\nVirkestoff: " + hentVirkestoff() + " mg\n";
      // Returnerer utskrift...
  }
}
