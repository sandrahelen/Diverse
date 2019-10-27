if(leggTilInp == 4){
  //resept

  System.out.println("Skriv inn IDen til pasienten som skal få resept: ");
  System.out.println(allePasienter());
  int pid = Integer.parseInt(inn.nextLine());
  Pasient p = finnPasient(pid);
  while(p == null){
    System.out.println("Fann ikkje pasient, prøv på ny: ");
    pid = Integer.parseInt(inn.nextLine());
    p = finnPasient(pid);
  }

  System.out.println("Skriv inn navn på legen som skal skrive ut resepten: ");
  String lege = inn.nextLine();
  Lege l = finnLege(lege);
  while(l == null){
    System.out.println("Fann ikkje lege, prøv på ny: ");
    l = finnLege(inn.nextLine());
  }

  System.out.println("Skriv inn IDen til legemiddelet som skal skrivast på resepten, skriv -1 for å få ut liste: ");
  int lid = Integer.parseInt(inn.nextLine());
  if(lid == -1){
    System.out.println(oversiktLegemidler());
    lid = Integer.parseInt(inn.nextLine());
  }
  Legemiddel lm = finnLegemiddel(lid);
  while(lm == null){
    System.out.println("Fann ikkje legemiddelet, prøv på ny eller skriv -1 for oversikt");
    lid = Integer.parseInt(inn.nextLine());
    if(lid == -1){
      System.out.println(oversiktLegemidler());
      lid = Integer.parseInt(inn.nextLine());

    }
    lm = finnLegemiddel(lid);

  }
  System.out.println("Velg type resept: \n[1]Kvit\n[2]Prevensjon\n[3]Militær\n[4]Blå\n");
  int pinp = Integer.parseInt(inn.nextLine());
  Resept r = null;
  if(pinp == 2){
    r = new PResept(lm, l, p);
  }else{
    System.out.println("Skriv inn antall reit: ");
    int reit = Integer.parseInt(inn.nextLine());
    if(pinp == 1){
      r = new HvitResept(lm, l, p, reit);
    }
    else if(pinp == 3){
      r = new MilitærResept(lm, l, p, reit);
    }
    else if(pinp == 4){
      r = new BlåResept(lm, l, p, reit);
    }
  }
  if(r != null){
    resepter.settInn(r);
    p.leggTilResept(r);
    l.skrivUtResept(r);
    System.out.println("Resepten er registrert");
  }
  else{
    System.out.println("Feil i opprettelse av resept, prøv på ny");
  }
}