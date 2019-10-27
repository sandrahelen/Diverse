//Labyrint
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Labyrint
{
  private final Rute[][] ruter;
  final int hoyde, bredde;
  List<String> utveier;

  private Labyrint(Rute[][] ruter, int hoyde, int bredde)
  {
    this.ruter = ruter;
    this.hoyde = hoyde;
    this.bredde = bredde;
  }

  static Labyrint lesFraFil(File fil) throws FileNotFoundException
  {
    Scanner scanner = new Scanner(fil);
    String[] storrelser = scanner.nextLine().split(" ");
    int hoyde = Integer.parseInt(storrelser[0]);
    int bredde = Integer.parseInt(storrelser[1]);
    Rute[][] ruter = new Rute[hoyde][bredde];
    Labyrint labyrint = new Labyrint(ruter, hoyde, bredde);

    // Fyll rute-arrayet med ruter
    for (int rad = 0; rad < hoyde; rad++)
    {
      char[] tegn = scanner.nextLine().toCharArray();
      for (int kol = 0; kol < bredde; kol++)
      {
        ruter[rad][kol] = Rute.lagRute(tegn[kol], rad, kol, labyrint);
      }
    }

    // Sett nabo-referanser
    for (int rad = 1; rad < hoyde-1; rad++)
    {
      for (int kol = 1; kol < bredde-1; kol++)
      {
        ruter[rad][kol].nord = ruter[rad-1][kol];
        ruter[rad][kol].oest = ruter[rad][kol+1];
        ruter[rad][kol].soer = ruter[rad+1][kol];
        ruter[rad][kol].vest = ruter[rad][kol-1];
      }
    }
    return labyrint;
  }
  // Merk: Vi bruker java.util.List i stedet for Liste fra oblig 3. Ellers likt.
  List<String> finnUtveiFra(int kol, int rad)
  {
    utveier = new ArrayList<String>();
    ruter[rad][kol].finnUtvei();

    // Collections.sort() er for sortering av utveier etter lengde slik at vi kan finne
		// korteste utvei (valgfri del). Se opptaket av plenum (siste par minutter) for
		// forklaring. Collections.sort fungerer ikke med Liste fra oblig 3, da må dette
		// implementeres med en "wrapper-klasse" som implementerer Comparable i stedet.
		// Om det er uklart, er det bare å spørre på Piazza!
    Collections.sort(utveier, (a, b) ->
    {
      int lengdeA = a.length() - a.replace(",", "").length();
      int lengdeB = b.length() - b.replace(",", "").length();
      return lengdeA - lengdeB;
    });
    return utveier;
  }

  @Override
  public String toString()
  {
    String retur = "";
    for (Rute[] rad : ruter)
    {
      for (Rute rute : rad)
      {
        retur += rute.tilTegn();
      }
      retur += "\n";
    }
    return retur;
  }

  public Rute hentRute(int rad, int kolonne)
  {
    return ruter[rad][kolonne];
  }

  public int hentRad()
  {
    return this.hoyde;
  }

  public int hentKolonne()
  {
    return this.bredde;
  }
}

//Rute
public abstract class Rute
{
  final int rad, kol;
  final Labyrint labyrint;
  Rute nord, oest, soer, vest;
  boolean besokt = false; // For å løse sykliske labyrinter (valgfri del)

  protected Rute(int rad, int kol, Labyrint labyrint)
  {
    this.rad = rad;
    this.kol = kol;
    this.labyrint = labyrint;
  }

  public abstract char tilTegn();
  public abstract void gaa(String vei, Rute komFra);

  static Rute lagRute(char tegn, int rad, int kol, Labyrint labyrint)
  {
    if (tegn == '#')
    {
      return new SortRute(rad, kol, labyrint);
    }
    else if (tegn == '.' && rad > 0 && kol > 0 && rad < labyrint.hoyde-1 && kol < labyrint.bredde-1)
    {
      return new HvitRute(rad, kol, labyrint);
    }
    else if (tegn == '.')
    {
      return new Aapning(rad, kol, labyrint);
    }
    else
    {
      throw new RuntimeException("Ugyldig tegn funnet: " + tegn);
    }
  }

  public void finnUtvei()
  {
    gaa("", null);
  }

  @Override
  public String toString()
  {
    return String.format("(%d, %d)", kol, rad);
  }

  public int hentRad()
  {
    return this.rad;
  }

  public int hentKolonne()
  {
    return this.kol;
  }
}

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

//HvitRute
public class HvitRute extends Rute
{
  HvitRute(int rad, int kol, Labyrint labyrint)
  {
    super(rad, kol, labyrint);
  }
  @Override
  public void gaa(String vei, Rute komFra)
  {
    if (besokt) return; // For å løse sykliske labyrinter (valgfri del)

    besokt = true; // For å løse sykliske labyrinter (valgfri del)
    vei += toString() + " --> ";

    if (komFra != nord) nord.gaa(vei, this);
    if (komFra != oest) oest.gaa(vei, this);
    if (komFra != soer) soer.gaa(vei, this);
    if (komFra != vest) vest.gaa(vei, this);
    besokt = false; // For å løse sykliske labyrinter (valgfri del)
  }

  @Override
  public char tilTegn()
  {
    return '.';
  }
}

//Aapning
public class Aapning extends HvitRute
{
  Aapning(int rad, int kol, Labyrint labyrint)
  {
    super(rad, kol, labyrint);
  }
  @Override
  public void gaa(String vei, Rute komFra)
  {
    vei += toString();
    labyrint.utveier.add(vei);
  }

  @Override
  public char tilTegn()
  {
    return 'A';
  }
}

