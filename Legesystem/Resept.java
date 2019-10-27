//Del A: Pasient p

abstract public class Resept
{
  //Definerer variablene til klassen
  private static int reseptId = -1;
  private int minReseptId;
  private Lege utskrivendeLege;
  private Pasient p;
  private int reit;
  protected Legemiddel legemiddel;

  //Konstruktor
  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit)
  {
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.p = p;
    this.reit = reit;
    reseptId ++;
    minReseptId = reseptId;
  }

  //returnerer resept ID
  public int hentId()
  {
    return minReseptId;
  }

  //returnerer legemiddel
  public Legemiddel hentLegemiddel()
  {
    return this.legemiddel;
  }

  public String hentLegemiddelNavn()
  {
    return this.legemiddel.hentNavn();
  }

  //returnerer lege
  public Lege hentLege()
  {
    return this.utskrivendeLege;
  }

  //returnerer pasient
  public Pasient hentPasient()
  {
    return this.p;
  }

  //returnerer reit
  public int hentReit()
  {
    return this.reit;
  }

  //returnerer om resepten kan brukes på nytt
  public boolean bruk()
  {
    if (hentReit() == 0)
    {
      return false;
    }
    else
    {
      this.reit--;
      return true;
    }
  }

  abstract public String farge();

  abstract public double prisAaBetale();

  @Override
  public String toString()
  {
    return hentId() + ": " + hentLegemiddelNavn() + " (" + hentReit() + " reit)";
  }
}
