//Del C: Leger

//Utvider Lege med Fastlege og implementerer grensesnittet Kommuneavtale
class Fastlege extends Lege implements Kommuneavtale
{
  //Definerer variabel til subklassen
  private int avtalenr;

  public Fastlege(String navn, int avtalenr)
  {
    //Henter instansvariabler fra superklassen
    super(navn);
    this.avtalenr = avtalenr;
  }

  //Returnerer avtalenummer
  public int hentAvtalenummer()
  {
    return this.avtalenr;
  }

  @Override
  public String toString()
  {
    return super.toString() + "\n" + "Avtalenummer: " + hentAvtalenummer();
  }
}
