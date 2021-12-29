Závěrečný projekt Java 1

Téma: matice

Autor: Michaela Sedlářová

Projekt obsahuje:
* matice.jar  balíček se třídami ze složky src, který se dá použít v jiných projektech
* složka src  obsahuje třídu Matice, rozhraní Matrix, Enum TypMatice
* build.xml soubor pomocí kterého jsem generovala matice.jar
* složka html obsahuje dokumentaci k projektu vygenerovanou pomocí JavaDoc
* všechny funkce jsou okomentované

Příklad použití:

System.out.println("Vytvářím matice:");     
Matice m = new Matice(4,4,TypMatice.RANDOM);    
Matice n = new Matice(3,3,TypMatice.JEDNOTKOVA);    
Matice zadat = new Matice(4,4,TypMatice.NULOVA);    
zadat.zadatData();  
System.out.println(m);  
System.out.println("Násobení matice m skalarem");   
System.out.println(m.nasobitSkalarem(5));   

System.out.println("Transponovaná matice m");   
System.out.println(m.transponovana());  

System.out.println("Matice m + Matice n");  
System.out.println(m.plus(n));  
System.out.println("Matice m + Matice m");  
System.out.println(m.plus(m));  

System.out.println("Matice m - Matice zadat");  
System.out.println(m.minus(zadat)); 

System.out.println("Matice m * Matice zadat");  
System.out.println(m.krat(zadat));  

System.out.println("Zapisuju matici m do souboru:");    
m.doSouboru("D:a.txt"); 

System.out.println("Matice m == Matice zadat"); 
System.out.println(m.equal(zadat)); 

System.out.println("Matice m == Matice m"); 
System.out.println(m.equal(m)); 


System.out.println("Spouštím vlákna");  
m.vlakna(3,"D:b.txt","D:c.txt");    

