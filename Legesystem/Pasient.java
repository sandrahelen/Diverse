//Del A: Pasient
public class Pasient
{
  //Definerer variableme i klassen.
  private static int id = -1;
  private int minId;
  private String navn;
  private String personnr;
  private Stabel<Resept> reseptliste = new Stabel<Resept>();

  //Konstroktor
  public Pasient(String navn, String personnr)
  {
    this.navn = navn;
    this.personnr = personnr;
    id ++;
    minId = id;
  }

  //Returnerer ID til pasient.
  public int hentId()
  {
    return minId;
  }

  //Returnerer navn paa pasient.
  public String hentNavnPasient()
  {
    return this.navn;
  }

  //Returnerer personnummeret til pasient.
  public String hentPersonnummer()
  {
    return this.personnr;
  }

  //Legger til resept i reseptlisten.
  public void leggTilResept(Resept resept)
  {
    reseptliste.leggPaa(resept);
  }

  //Henter resept fra reseptlisten.
  public Stabel hentReseptliste()
  {
    return reseptliste;
  }


  @Override
  public String toString()
  {
    return hentId() + ": " + hentNavnPasient() + "(personnr. " + hentPersonnummer() + ")";
  }
}
