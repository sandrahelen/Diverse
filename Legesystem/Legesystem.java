//Del D: Legesystem
import java.util.Scanner;

public class Legesystem
{
  public static void main(String[] args)
  {
    //Oppretter legesystem
    Legesystem legesystem = new Legesystem();

    //Oppretter lister
    SortertLenkeliste<Lege> legeliste = new SortertLenkeliste<Lege>();
    Lenkeliste<Pasient> pasientliste = new Lenkeliste<Pasient>();
    Lenkeliste<Legemiddel> legemiddelliste = new Lenkeliste<Legemiddel>();
    Stabel<Resept> reseptliste = new Stabel<Resept>();
    Stabel<MilitaerResept> mReseptliste = new Stabel<MilitaerResept>();

    //Oppretter leger
    Lege lege0 = new Lege("Ola");
    Lege lege1 = new Lege("Kari");
    Lege lege2 = new Lege("Henrik");
    Lege lege3 = new Lege("Anna");

    //Oppretter pasienter
    Pasient pasient0 = new Pasient("Bente", "12345678900");
    Pasient pasient1 = new Pasient("Daniel", "09876543211");
    Pasient pasient2 = new Pasient("Eline", "11111111111");
    Pasient pasient3 = new Pasient("Hans", "00000000000");

    //Oppretter legemidler
    //Narkotisk
    LegemiddelA legemiddel0 = new LegemiddelA("Morfin", 267.77, 10, 100);
    //Vanedannende
    LegemiddelB legemiddel1 = new LegemiddelB("Valium", 127.20, 5, 50);
    LegemiddelC legemiddel2 = new LegemiddelC("Loette", 154.65, 0.1);
    LegemiddelC legemiddel3 = new LegemiddelC("Ibux", 97.50, 600);

    //Oppretter resepter
    //Narkotisk
    BlaaResept resept0 = new BlaaResept(legemiddel0, lege0, pasient0, 2);
    //Vanedannende
    MilitaerResept resept1 = new MilitaerResept(legemiddel1, lege1, pasient1, 3);
    PResept resept2 = new PResept(legemiddel2, lege2, pasient2);
    PResept resept3 = new PResept(legemiddel3, lege3, pasient3);

    System.out.println("");

    //Legger pasientene i pasientlista
    pasientliste.leggTil(pasient0);
    pasientliste.leggTil(pasient1);
    pasientliste.leggTil(pasient2);
    pasientliste.leggTil(pasient3);

    //Legger legene i legelista
    legeliste.leggTil(lege0);
    legeliste.leggTil(lege1);
    legeliste.leggTil(lege2);
    legeliste.leggTil(lege3);

    //Legger legemidlene i legemiddellista
    legemiddelliste.leggTil(legemiddel0);     //Narkotisk
    legemiddelliste.leggTil(legemiddel1);     //Vanedannende
    legemiddelliste.leggTil(legemiddel2);
    legemiddelliste.leggTil(legemiddel3);

    //Legger reseptene i reseptlista
    reseptliste.leggTil(resept0);             //Narkotisk
    reseptliste.leggTil(resept1);             //Vanedannende
    reseptliste.leggTil(resept2);
    reseptliste.leggTil(resept3);

    mReseptliste.leggTil(resept1);            //Vanedannende

    //Legger pasienter til resepter
    pasient0.leggTilResept(resept0);          //Narkotisk
    pasient0.leggTilResept(resept3);
    pasient1.leggTilResept(resept1);          //Vanedannende
    pasient1.leggTilResept(resept0);          //Narkotisk
    pasient2.leggTilResept(resept2);
    pasient3.leggTilResept(resept3);

    //Legger leger til resepter
    lege0.leggTilResept(resept0);             //Narkotisk
    lege1.leggTilResept(resept1);             //Vanedannende
    lege2.leggTilResept(resept2);
    lege2.leggTilResept(resept0);             //Narkotisk
    lege3.leggTilResept(resept3);
    lege3.leggTilResept(resept1);             //Vanedannende
    lege3.leggTilResept(resept2);

    System.out.println("");

    Scanner in = new Scanner(System.in);
    int u;
    double v;
    double w;
    int x;
    String y;
    String z;

    //Hovedmeny
    hovedmeny();
    x = Integer.parseInt(in.nextLine());
    while (x != 7)
    {
      if (x == 1)
      {
        pasienter(pasientliste);
      }
      else if(x == 2)
      {
        leger(legeliste);
      }
      else if(x == 3)
      {
        legemidler(legemiddelliste);
      }
      /*else if (x == 4)
      {
        //resept
        System.out.println("Skriv inn IDen til pasienten som skal få resept: ");
        pasienter(pasientliste);
        int pid = Integer.parseInt(in.nextLine());
        Pasient p = pasientliste.hent(pid);
        while(p == null)
        {
          System.out.println("Fann ikkje pasient, prøv på ny: ");
          pid = Integer.parseInt(in.nextLine());
          p = pasientliste.hent(pid);
        }

        System.out.println("Skriv inn navn på legen som skal skrive ut resepten: ");
        String lege = in.nextLine();
        Lege l = finnLege(lege);
        while(l == null)
        {
          System.out.println("Fann ikkje lege, prøv på ny: ");
          l = legeliste.hent(in.nextLine());
        }

        System.out.println("Skriv inn IDen til legemiddelet som skal skrivast på resepten, skriv -1 for å få ut liste: ");
        int lid = Integer.parseInt(in.nextLine());
        if(lid == -1)
        {
          System.out.println(oversiktLegemidler());
          lid = Integer.parseInt(in.nextLine());
        }
        Legemiddel lm = legemiddelliste.hent(lid);
        while(lm == null)
        {
          System.out.println("Fant ikke legemiddelet, prøv på nytt eller skriv -1 for oversikt");
          lid = Integer.parseInt(in.nextLine());
          if(lid == -1)
          {
            legemidler(legemiddelliste);
            lid = Integer.parseInt(in.nextLine());

          }
          lm = legemiddelliste.hent(lid);
        }
        System.out.println("Velg type resept: \n[1]Hvit\n[2]Prevensjon\n[3]Militær\n[4]Blå\n");
        int pinp = Integer.parseInt(in.nextLine());
        Resept r = null;
        if(pinp == 2)
        {
          r = new PResept(lm, l, p);
        }
        else
        {
          System.out.println("Skriv inn antall reit: ");
          int reit = Integer.parseInt(in.nextLine());
          if(pinp == 1)
          {
            r = new HvitResept(lm, l, p, reit);
          }
          else if(pinp == 3)
          {
            r = new MilitaerResept(lm, l, p, reit);
          }
          else if(pinp == 4)
          {
            r = new BlaaResept(lm, l, p, reit);
          }
        }
        if(r != null){
          resepter.settInn(r);
          p.leggTilResept(r);
          l.skrivUtResept(r);
          System.out.println("Resepten er registrert");
        }
        else
        {
          System.out.println("Feil i opprettelse av resept, prøv på ny");
        }
      }*/
      else if(x == 4)
      {
        System.out.println("\n--Resepter--");
        //System.out.println("\nHvilken pasient vil du se resepter for?");  //Får feilmelding her
        //pasientliste.skrivUtListe();                                      //og her.

        System.out.print("\n>");
        x = Integer.parseInt(in.nextLine());
        System.out.println("");
        //0: Navn1 (personnr. 00000000)
        //1: Navn2 (personnr. 11111111)...


        Pasient pasient = pasientliste.hent(x);
        System.out.println("\nValgt pasient " + pasient);
        System.out.println("");
        pasient.hentReseptliste().skrivUtListe();
        Stabel liste = pasient.hentReseptliste();
        //0: Resept1(x reit)
        //1: Resept2 (y reit)...

        System.out.println("\nVil du bruke resept?");
        System.out.println("0: Ja\n1: Nei");
        System.out.print("\n>");
        u = Integer.parseInt(in.nextLine());

        if (u == 0)
        {
          System.out.println("\nHvilken resept vil du bruke?");
          System.out.print("\n>");
          u = Integer.parseInt(in.nextLine());

          Resept r = reseptliste.hent(u);
          if (r.bruk() == true)
          {
            System.out.println("\nBrukte resept paa " + r.hentLegemiddelNavn() + ". Antall gjenvaerende reit: " + r.hentReit());
          }
          else
          {
            System.out.println("\nKunne ikke bruke resept paa Legemiddel (ingen gjenvaerende reit).");
          }
        }
    }
      else if(x == 5)
      {
        System.out.println("\n--Legg til element--");
        System.out.println("0: Pasient\n1: Lege\n2: Legemiddel");
        System.out.print("\n>");
        x = Integer.parseInt(in.nextLine());
        if (x == 0)
        {
          System.out.println("\nLegg til pasient");
          System.out.print("Navn: ");
          y = in.nextLine();
          System.out.print("Personnummer: ");
          z = in.nextLine();

          Pasient nyP = new Pasient(y, z);
          pasientliste.leggTil(nyP);
          System.out.println("");

          System.out.println("Liste med ny pasient");
          pasientliste.skrivUtListe();
        }
        else if (x == 1)
        {
          System.out.println("\nLegg til lege");
          System.out.print("Navn: ");
          y = in.nextLine();

          Lege nyL = new Lege(y);
          legeliste.leggTil(nyL);
          System.out.println("");

          System.out.println("Liste med ny lege");
          legeliste.skrivUtListe();
        }
        else if (x == 2)
        {
          System.out.println("\nLegg til legemiddel");
          System.out.print("Navn: ");
          y = in.nextLine();
          System.out.print("Pris: ");
          v = Double.parseDouble(in.nextLine());
          System.out.print("Virkestoff: ");
          w = Double.parseDouble(in.nextLine());

          Legemiddel nyttL = new Legemiddel(y, v, w);
          legemiddelliste.leggTil(nyttL);
          System.out.println("");

          System.out.println("Liste nytt legemiddel");
          legemiddelliste.skrivUtListe();
        }
      }
      else if (x == 6)
      {
        undermeny();
        x = Integer.parseInt(in.nextLine());
        System.out.println("");

        if (x == 0)
        {
          System.out.println("");
          int antall = 0;
          for (int i = 0; i < reseptliste.stoerrelse(); i++)
          {
            Resept r = reseptliste.hent(i);
            if (r.hentLegemiddel() instanceof LegemiddelB)
            {
              antall ++;
            }
          }
          System.out.println("Totalt antall utskrevne respter paa vanedannende legemidler: " + antall);
        }
        else if (x == 1)
        {
          System.out.println("");
          int antallM = 0;
          for (int i = 0; i < mReseptliste.stoerrelse(); i++)
          {
            MilitaerResept m = mReseptliste.hent(i);
            if (m.hentLegemiddel() instanceof LegemiddelB)
            {
              antallM ++;
            }
          }
          System.out.println("Antall vanedannende resepter utskrevne til militaere: " + antallM);
        }
        else if (x == 2)
        {
          System.out.println("Statisitkk om mulig misbruk av narkotika\n");
          System.out.println("--Leger--");
          //Leger som har skrevet ut gyldig/ikke gyldig resept på narkotisk legemiddel + antall resepter
          for (int i = 0; i < legeliste.stoerrelse(); i++)
          {
            int antall = 0;
            Lege lege = legeliste.hent(i);
            Lenkeliste liste = lege.hentReseptliste();
            for (int j = 0; j < liste.stoerrelse(); j++)
            {
              Resept r = (Resept) liste.hent(j);
              if (r.hentLegemiddel() instanceof LegemiddelB) //Får riktig antall, men ikke når det er instanceof LegemiddelA.
              {
                antall++;
              }
            }
            System.out.println(lege + " -- antall utskrevne resepter: " + antall); //lege0 og 2
          }
          System.out.println("");
          System.out.println("--Pasienter--");
          //Pasienter som har gyldig resept på narkotiske legemidler + antall resepter
          for (int i = 0; i < pasientliste.stoerrelse(); i++)
          {
            int antall = 0;
            Pasient pasient = pasientliste.hent(i);
            Lenkeliste liste = pasient.hentReseptliste();
            for (int j = 0; j < liste.stoerrelse(); j++)
            {
              Resept r = (Resept) liste.hent(j);
              if (r.hentLegemiddel() instanceof LegemiddelA)
              {
                antall++;
              }
            }
            System.out.println(pasient + " -- antall utskrevne resepter: " + antall); //pasient0 og 1
          }
        }
      }

      System.out.println("");
      System.out.println("");
      hovedmeny();
      x = Integer.parseInt(in.nextLine());
    }
  }

  private static void hovedmeny()
  {
    System.out.println("");
    System.out.println("--HOVEDMENY--");
    System.out.println("1: PASIENTER\n2: LEGER\n3: LEGEMIDLER\n4: RESEPTER");
    System.out.println("5: LEGG TIL ELEMENT\n6: STATISTIKK\n7: AVSLUTT ");
    System.out.print("\n>");
  }

  private static void pasienter(Lenkeliste pasientliste)
  {
    System.out.println("\n--Pasienter--");
    pasientliste.skrivUtListe();
  }

  private static void leger(SortertLenkeliste legeliste)
  {
    System.out.println("\n--Leger--");
    legeliste.skrivUtListe();
  }

  private static void legemidler(Lenkeliste legemiddelliste)
  {
    System.out.println("\n--Legemidler--");
    legemiddelliste.skrivUtListe();
  }

  private static void undermeny()
  {
    System.out.println("\n--UNDERMENY--");
    System.out.println("0: Totalt antall utskrevne respter paa vanedannende legemidler");
    System.out.println("1: Antall vanedannende resepter utskrevne til militaere");
    System.out.println("2: Statisitkk om mulig misbruk av narkotika");
    System.out.print("\n>");
  }
}
