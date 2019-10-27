//Del C: Leger

public class Lege extends Lenkeliste<Resept> implements Comparable<Lege>
{
  //Definerer variabel til klassen
  private String navn;
  private Lenkeliste<Resept> resepter = new  Lenkeliste<Resept>();

  //Konstruktor
  public Lege(String navn)
  {
    this.navn = navn;
  }

  //Returnerer navn paa lege
  public String hentNavnLege()
  {
    return this.navn;
  }

  @Override
  public String toString()
  {
    return "Lege: " + hentNavnLege();
  }

  @Override
  public int compareTo(Lege lege)
  {
    // navn.compareTo(lege.hentNavn());
    if (lege == null)
    {
      return -1;
    }
    if (navn != null && lege.hentNavnLege() == null)
    {
      return -1;
    }
    else if (navn == null && lege.hentNavnLege() != null)
    {
      return 1;
    }
    else if (navn != null && lege.hentNavnLege() != null)
    {
      int sammenlign = navn.compareToIgnoreCase(lege.hentNavnLege());
      if (sammenlign != 0)
      {
        return sammenlign;
      }
    }

    if (navn == null && lege.hentNavnLege() == null)
    {
      return 0;
    }
    else
    {
      return navn.compareToIgnoreCase(lege.hentNavnLege());
    }
  }

  public void leggTilResept(Resept resept)
  {
    resepter.leggTil(resept);
  }

  public Lenkeliste hentReseptliste()
  {
    return resepter;
  }
}
