//SortRute
public class SortRute extends Rute
{
  SortRute(int rad, int kol, Labyrint labyrint)
  {
    super(rad, kol, labyrint);
  }

  @Override
  public void gaa(String vei, Rute komFra)
  {
    return; // Her skal vi ikke gjøre noe
  }

  @Override
  public char tilTegn()
  {
    return '#';
  }
}
