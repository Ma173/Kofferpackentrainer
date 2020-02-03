import random
elemente={

  #TODO: Elemente sollten über die Nummer im Dict sperrbar sein
  #TODO: Kombinationen erfragen und falls machbar in jeweiligem Tupel nachtragen
  #TODO: Saltos hinzufügen
  # Geht Bauch - halbe Sitz?
  # Geht Bauch - Ganze Stand?
  # Geht Bücke in den Sitz?
  # Wie viele Punkte gibt Sitz ganze Sitz? 0.2?
  # Wie viele Punkte gibt Wende in den Rücken/ Bauch?
  #TODO: Für mich zum Üben: Ganze in den Sitz
  
  #INFO: Muffel zählt offiziell als Salto; Rücken vorwärtsrolle zählt auch als Salto oder Porpoise (Tümmler);


  #Elementname : (Schwierigkeit, Position vorher, Position nachher,Weirdheitsfaktor 0 (gewöhnlich)- 1 (ungewöhnlich) - 2 (schwer machbar/ sehr ungewohnt))

  #IN STAND
  "Stand":((0.1,"Rücken","Stand",0,1),(0.1,"Bauch","Stand",0,2),(0,"Sitz","Stand",0,3)),
  "Hocke":((0,"Stand","Stand",0,4),),
  "Bücke":((0,"Stand","Stand",0,5),),
  "Grätsche":((0,"Stand","Stand",0,6),),
  "halbe Schraube":((0.1,"Stand","Stand",0,7),),
  "halbe Stand":((0.2,"Rücken","Stand",0,8),(0.1,"Bauch","Stand",0,9),(0.1,"Sitz","Stand",0,10)),
  "halbe Hocke":((0.1,"Stand","Stand",1,11),),
  "halbe Bücke":((0.1,"Stand","Stand",1,12),),
  "halbe Grätsche":((0.1,"Stand","Stand",1,13),),
  "ganze Schraube":((0.2,"Stand","Stand",0,14),),
  "ganze Stand":((0.2,"Sitz","Stand",1,15),(0.3,"Rücken","Stand",1,16)),
  "Muffel":((0.3,"Rücken","Stand",0,17),),
  "Salto vorwärts z. Stand":((0.6,"Rücken","Stand",1,18),),

  #IN SITZ
  "Sitz":((0,"Stand","Sitz",0,19),(0,"Sitz","Sitz",0,20)),
  "halbe Sitz":((0.1,"Stand","Sitz",0,21),(0.1,"Sitz", "Sitz",0,22),(0.2,"Rücken","Sitz",1,23)),
  "ganze Sitz":((0.2,"Sitz","Sitz",0,24),(0.2,"Stand","Sitz",1,25)),
  "Hocke in den Sitz":((0,"Stand","Sitz",2,26),),
  "Grätsche in den Sitz":((0,"Stand","Sitz",2,27),),
  "Muffel in den Sitz":((0.3,"Rücken","Sitz",0,28),),
  
  #IN RÜCKEN
  "Rücken":((0.1,"Stand","Rücken",0,29),(0.1,"Sitz","Rücken",1,30),(0,"Rücken","Rücken",0,31),(0.2,"Bauch","Rücken",0,32)),
  "Halbe Rücken":((0.2,"Stand","Rücken",0,33),(0.3,"Rücken","Rücken",0,34),(0.2,"Sitz","Rücken",1,35)),
  "Salto vorwärts z. Rücken c":((0.5,"Rücken","Rücken",0,36),),
  "Muffel in den Rücken":((0.5,"Rücken","Stand",1,37),),
  "Wende in den Rücken":((0.2,"Bauch","Rücken",0,38),),
  
  #IN BAUCH
  "Bauch":((0.1,"Stand","Bauch",0,39),(0.1,"Sitz","Bauch",0,40),(0,"Bauch","Bauch",0,41),(0.2,"Rücken","Bauch",1,42)),
  "halbe Heli":((0.1,"Bauch","Bauch",0,43),),
  "Muffel in den Bauch":((0.2,"Rücken","Bauch",1,44),),
  "Wende in den Bauch":((0.2,"Rücken","Bauch",0,45),)

}

ausStand={}
ausSitz={}
ausRücken={}
ausBauch={}

#TODO: Schleife so umbauen, dass die Schleife bei Tupeln mit nur einem Element nicht eine Etage tiefer (in dessen vier Einträge) reingeht
for element in elemente.keys():
  #element z.B.: halbe Rücken
  for variante in elemente[element]:
    #variante z.B.: (0.2,"Sitz","Rücken",1)
    if True:
      startposition=variante[1]
      #startposition=""
      if startposition=="Stand":
        ausStand[element]=variante
      elif startposition=="Sitz":
        ausSitz[element]=variante
      elif startposition=="Rücken":
        ausRücken[element]=variante
      elif startposition=="Bauch":
        ausBauch[element]=variante
#print("___________________OUTPUT")
#print(ausStand)
#print(ausSitz)
#print(ausRücken)
#print(ausBauch)

alteElementsammlung = {
  # Start-Position:[(Name des Sprungs,Ziel-Position)]
    "Stand": [
        ("Bauch","Bauch"), ("halbe Bauch","Bauch"), ("Sitz","Sitz"), ("halbe Sitz","Sitz"), ("Rücken","Rücken"), ("halbe Rücken","Rücken"),
        ("ganze Rücken","Rücken"), ("halbe Schraube","Stand"), ("ganze Schraube","Stand"), ("Grätsche","Stand"),
        ("Hocke","Stand"), ("Bücke", "Stand"),("halbe Grätsche","Stand"), ("halbe Hocke","Stand"),
        ("Hocke in den halben Sitz","Sitz")
    ],
    "Rücken": [
        ("Stand","Stand"), ("halbe Stand","Stand"), ("ganze Stand","Stand"), ("halbe Rücken","Rücken"), ("Bauch","Bauch"),
        ("halbe Bauch","Bauch"),("Sitz","Sitz"), ("halbe Sitz","Sitz"), ("Muffel","Stand"), ("Muffel in den Sitz","Sitz"),
        ("Muffel in den Bauch","Bauch"), ("Muffel in den Rücken","Rücken"),("Wende in den Bauch","Bauch")
    ],
    "Sitz": [
        ("Stand","Stand"), ("halbe Stand","Stand"), ("ganze Stand","Stand"), ("Sitz","Sitz"), ("Bauch", "Bauch"),("halbe Bauch","Bauch"),
        ("Rücken","Rücken"), ("halbe Rücken","Rücken")
    ],
    "Bauch": [("Stand","Stand"),("halbe Stand","Stand"),("Rücken","Rücken"),("halbe Heli","Bauch"),("Bauch","Bauch"),("Wende in den Rücken","Rücken")],
    
}

gesperrteElemente=[]
erlaubteWeirdheit=2
users={
  #Name: [gesperrte Elemente (List) ,weirde Sprünge (Int, 0 (keine) - 2 (alle))]
  "malte":[[18,25],1]
}

currentuser=list(users)[0]
erlaubteWeirdheit=users[currentuser][1]
gesperrteElemente=users[currentuser][0]

def neueUebungGenerieren():
  global uebung
  uebung = []
  spruengeInUebung = 0
  #currentuser=input("User?")
  #if currentuser.lower() in users:
  # print("Willkommen zurück,",currentuser)
  # gesperrteElemente=users[currentuser][0]
  # weirdheitsfaktor=users[currentuser][1]
  letztePosition="Stand"
  while spruengeInUebung < 10:
    #print("Letzte Position:",letztePosition,"\n")
    if letztePosition=="Stand":
      zufallselement = list(ausStand.keys())[random.randrange(0, len(ausStand))]
      zufallselement = ((zufallselement,ausStand[zufallselement]))
      #print("Zufallselement Nr",spruengeInUebung+1,"ist:",zufallselement)
      weirdheitDesElements = zufallselement[1][3]
      while weirdheitDesElements>erlaubteWeirdheit:
        zufallselement = list(ausStand.keys())[random.randrange(0, len(ausStand))]
        zufallselement = ((zufallselement,ausStand[zufallselement]))
        weirdheitDesElements = zufallselement[1][3]
      while zufallselement[1][4] in gesperrteElemente:
        #print("Ziehe neues Element, da Weirdheit höher als gewünscht")
        zufallselement = list(ausStand.keys())[random.randrange(0, len(ausStand))]
        zufallselement = ((zufallselement,ausStand[zufallselement]))
        #print("Zufallselement Nr",spruengeInUebung+1,"ist:",zufallselement)

    elif letztePosition=="Sitz":
      zufallselement = list(ausSitz.keys())[random.randrange(0, len(ausSitz))]
      zufallselement = ((zufallselement,ausSitz[zufallselement]))
      #print("Zufallselement Nr",spruengeInUebung+1,"ist:",zufallselement)
      weirdheitDesElements = zufallselement[1][3]
      while weirdheitDesElements>erlaubteWeirdheit:
        #print("Ziehe neues Element, da Weirdheit höher als gewünscht")
        zufallselement = list(ausSitz.keys())[random.randrange(0, len(ausSitz))]
        zufallselement = ((zufallselement,ausSitz[zufallselement]))
        #print("Zufallselement Nr",spruengeInUebung+1,"ist:",zufallselement
        weirdheitDesElements = zufallselement[1][3]
      while zufallselement[1][4] in gesperrteElemente:
        #print("Ziehe neues Element, da Weirdheit höher als gewünscht")
        zufallselement = list(ausStand.keys())[random.randrange(0, len(ausStand))]
        zufallselement = ((zufallselement,ausStand[zufallselement]))
        

    elif letztePosition=="Bauch":
      zufallselement = list(ausBauch.keys())[random.randrange(0, len(ausBauch))]
      zufallselement = ((zufallselement,ausBauch[zufallselement]))
      #print("Zufallselement Nr",spruengeInUebung+1,"ist:",zufallselement)
      weirdheitDesElements = zufallselement[1][3]
      while weirdheitDesElements>erlaubteWeirdheit:
        print("Ziehe neues Element, da Weirdheit höher als gewünscht")
        zufallselement = list(ausBauch.keys())[random.randrange(0, len(ausBauch))]
        zufallselement = ((zufallselement,ausBauch[zufallselement]))
        #print("Zufallselement Nr",spruengeInUebung+1,"ist:",zufallselement)
        weirdheitDesElements = zufallselement[1][3]
      while zufallselement[1][4] in gesperrteElemente:
          #print("Ziehe neues Element, da Weirdheit höher als gewünscht")
          zufallselement = list(ausStand.keys())[random.randrange(0, len(ausStand))]
          zufallselement = ((zufallselement,ausStand[zufallselement]))

    elif letztePosition=="Rücken":
      zufallselement = list(ausRücken.keys())[random.randrange(0, len(ausRücken))]
      zufallselement = ((zufallselement,ausRücken[zufallselement]))
      #print("Zufallselement Nr",spruengeInUebung+1,"ist:",zufallselement)
      weirdheitDesElements = zufallselement[1][3]
      while weirdheitDesElements>erlaubteWeirdheit:
        #print("Ziehe neues Element, da Weirdheit höher als gewünscht")
        zufallselement = list(ausRücken.keys())[random.randrange(0, len(ausRücken))]
        zufallselement = ((zufallselement,ausRücken[zufallselement]))
        #print("Zufallselement Nr",spruengeInUebung+1,"ist:",zufallselement)
        weirdheitDesElements = zufallselement[1][3]
      while zufallselement[1][4] in gesperrteElemente:
          #print("Ziehe neues Element, da Weirdheit höher als gewünscht")
          zufallselement = list(ausStand.keys())[random.randrange(0, len(ausStand))]
          zufallselement = ((zufallselement,ausStand[zufallselement]))
    uebung.append((zufallselement))
    #print("Übung bisher:",uebung)
    letztePosition=uebung[-1][1][2]
    spruengeInUebung += 1
  print("\n\n")
  schwierigkeitDerUebung=0
  for element in uebung:
    schwierigkeitDerUebung+=element[1][0]
    print (element[0]," ",element[1][0])
  schwierigkeitDerUebung=round(schwierigkeitDerUebung,1)
  print("\nSchwierigkeit der Übung:",schwierigkeitDerUebung)
userinp=""
while userinp!="b":
  neueUebungGenerieren()
  userinp=input("Übung speichern? |c für cool | w für weird|\nDirekt eine neue Übung generieren mit: n\nBeenden mit b\n")
  if "c" in userinp.lower():
    with open('coolsteUebungen.txt', 'a') as filehandle:
      for element in uebung:
        filehandle.write(str(element))
      filehandle.write("\n_\n")
  elif "w" in userinp.lower():
    with open('weirdesteUebungen.txt', 'a') as filehandle:
      for element in uebung:
        filehandle.write(str(element))
      filehandle.write("\n_\n")
  #elif "n" in userinp.lower():
    #neueUebungGenerieren()
