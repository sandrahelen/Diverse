//GUILabyrint
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;


public class GUILabyrint extends Application
{
  public static void main(String[] args)
  {
    //Kjorer programmet
    launch(args);
  }

  //Variabler
  public static GUIRute[][] ruter;
  private BorderPane rotnode;
  private GridPane rutenett;
  private Stage stage;
  private Labyrint labyrint;
  protected static Text statusinfo;

  //Oppsett til vinudet
  @Override
  public void start(Stage stage)
  {
    this.stage = stage;
    rotnode = new BorderPane();
    rotnode.setTop(toppBoks());
    statusinfo = new Text();
    rotnode.setBottom(statusinfo);

    Scene scene = new Scene(rotnode);

    stage.setTitle("Labyrint");
    stage.setScene(scene);
    stage.show();
  }

  //Oppretter topp-boks
  private HBox toppBoks()
  {
    //Oppretter knapper
    Button velgFilKnapp = new Button("Velg fil...");
    Button avsluttKnapp = new Button("Avslutt");

    velgFilKnapp.setOnAction(new EventHandler<ActionEvent>()
    {
      //Velger fil og leser fil
      @Override
      public void handle(ActionEvent e)
      {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg en Labyrint fil");

        //Begrenser filtype til .in-filer
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("*.in", "*.in"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile != null)
        {
          //Skriver ut filplassering i terminalen
          System.out.println(selectedFile.getPath());
          try
          {
            //Oppsett til labyrint
            labyrint = Labyrint.lesFraFil(selectedFile);
            rutenett = lagRutenett(labyrint);
            rotnode.setCenter(rutenett);
            stage.sizeToScene();
            statusinfo.setText("*Velg en rute");
          }
          catch (FileNotFoundException n)
          {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", selectedFile.getPath());
            System.exit(1);
          }
        }
        else
        {
          statusinfo.setText("*Ingen fil ble valgt");
        }
      }
    });
    //Avslutter programmet
    avsluttKnapp.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent e)
      {
        Platform.exit();
      }
    });
    //Returnerer toppboks
    return new HBox(velgFilKnapp, avsluttKnapp);
  }

  //Oppretter rutenett til labyrint
  private GridPane lagRutenett(Labyrint labyrint)
  {
    rutenett = new GridPane();
    ruter = new GUIRute[labyrint.hentRad()][labyrint.hentKolonne()];

    for (int rad  = 0; rad < labyrint.hentRad(); rad++)
    {
      for (int kol = 0; kol < labyrint.hentKolonne(); kol++)
      {
        ruter[rad][kol] = new GUIRute(labyrint, rad, kol);
        rutenett.add(ruter[rad][kol], rad, kol);
      }
    }
    return rutenett;
  }
}
