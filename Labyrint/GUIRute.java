//GUIRute
import javafx.scene.layout.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.Border;
import javafx.scene.input.MouseEvent;
import java.util.List;

public class GUIRute extends Pane
{
  //Variabler
  public static Labyrint labyrint;
  private Boolean erHvit;
  private int kol;
  private int rad;
  private Rute rute;
  private Boolean valgtRute = false;
  private int teller;

  //Konstruktor
  public GUIRute(Labyrint labyrint, int rad, int kol)
  {
    this.labyrint = labyrint;
    this.rad = rad;
    this.kol = kol;
    rute = labyrint.hentRute(rad, kol);

    //Lager hvite ruter
    if (rute instanceof HvitRute)
    {
      setBackground(new Background(
      new BackgroundFill(Color.WHITE, null, null)));
      erHvit = true;
    }
    //Lager sorte ruter
    else if (rute instanceof SortRute)
    {
      setBackground(new Background(
      new BackgroundFill(Color.BLACK, null, null)));
      erHvit = false;
    }

    //Lager kantelinjer mellom rutene
    setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
    null, new BorderWidths(0.5))));


    //Definerer storrelese paa labyrint
    if (labyrint.hentRad() < 20)
    {
      dimensjon(50);
    }
    else if (labyrint.hentRad() < 30)
    {
      dimensjon(30);
    }
    else
    {
      dimensjon(10);
    }


    //Museklikk paa rute
    addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (erHvit == true)
        {
          if (!valgtRute)
          {
            //Skifter farge til graa
            setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
            valgtRute = true;
            //Finner losning til labyrinten
            finnLosning(rute.hentRad(), rute.hentKolonne());
            GUILabyrint.statusinfo.setText("*Antall løsninger: " + teller);
          }
        }
      }
    });
  }

  //Setter dimensjon til ruter
  private void dimensjon(int i){
    setMinWidth(i);
    setMinHeight(i);
  }

  //Finner losning(er) og farger utvei graa.
  private void finnLosning(int rad, int kol)
  {
    //Oppretter liste med utveier
    List<String> utveier = labyrint.finnUtveiFra(kol, rad);
    teller = 0;
    while (!utveier.isEmpty())
    {
      //Finner utvei
      String losning = utveier.remove(0);
      teller ++;
      boolean[][] tilTabell = losningStringTilTabell(losning, labyrint.hentKolonne(), labyrint.hentRad());
      for (int r = 0; r < labyrint.hentRad(); r++)
      {
        for (int k = 0; k < labyrint.hentKolonne(); k++)
        {
          if (tilTabell[r][k] == true)
          {
            //Farger utvei graa
            GUILabyrint.ruter[r][k].setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
            GUILabyrint.ruter[r][k].valgtRute = true;
          }
        }
      }
    }
  }

  /**
   * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
   * av losningstien.
   * @param losningString String-representasjon av utveien
   * @param bredde        bredde til labyrinten
   * @param hoyde         hoyde til labyrinten
   * @return              2D-representasjon av rutene der true indikerer at
   *                      ruten er en del av utveien.
   */
  static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
      boolean[][] losning = new boolean[hoyde][bredde];
      java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
      java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
      while (m.find()) {
          int x = Integer.parseInt(m.group(1));
          int y = Integer.parseInt(m.group(2));
          losning[y][x] = true;
      }
      return losning;
  }
}
