# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Navn Navnesen, S123456, s123456@oslomet.no
* Emil Thoresen, S377316, s377316@oslomet.no
* Sam kanaan, s364575, s364575@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Sam har hatt hovedansvar for oppgave 1, 2, 3 og 5. 
* Emil har hatt hovedansvar for oppgave 4, 7 og 10.
* Thanh har hatt hovedansvar for oppgave 6, 8 og 9. 

# Oppgavebeskrivelse

I oppgave 1 jeg lagde int antall som returnere seg selv, også lagde boolean tom som returnere true false avhengig om
listen er tom eller ikke. så lagde vi fobbeltlenketliste, i starten kaster vi unntak for null tabell også fjerner
vi alle null verdier, og hopper ut av metoden hvis listen er tom. vidre opretter vi noden og gir den verdi og setter 
hode lik første node. Deretter opretter ny node og opdaterer antall noder og setter halen lik siste node.

I oppgave 2  opreter vi string tostring og string gomvendtstring som begge løper vi gjennom listen så lenge aktuells 
nestepekeren er ulik null, og legger verdien til aktuell node til utskrifrstrengen. vidre lager vi boolean legginn 
som legger inn en ny node bakerst. vis det ikke fins noen noder fra før så setter hode lik ny node. 
også øker vi antall og endringer variablene med 1-


I oppgave 3 lager vi finnNode klasse, der starter vi å med å sjekke i hvilken halvparten indeksen ligger,
dermed søker vi i den halvparten der indeksen ligger. videre henter vi indeksen med metoden hent indeks,
som returne noden til indeksen. Deretter lagde jeg metoden oppdater indeks, nyverdi som starter med og kaste
et unntakk om nyverdi er null, så sjekker om indeksen er ugylidig,så finner noden til indeks og 
putter verdien inn i en variabel, deretter lager og oppdatere noden sin verdi og øker antall endringer med 1.

I oppgave 4 hentet jeg boolean inneholder fra kompendiet. Deretter begynte jeg på indeksTil, 
her starter vi med en if setning som sjekker om verdien finnes, om den ikke gjør det returnerer vi -1.
Deretter henter vi verdien til hode og gir den til noden aktuell. Deretter har vi en for løkke som intererer 
gjennom lista antall ganger. Videre har vi en if setning som sier at dersom aktuell.verdi er det samme som verdi, 
returneres i. Etter dette har vi en oppdatering av noden aktuell. Og til slutt return -1.

I oppgave 5 lager vi metoden legginn ideks, verdi. starter med å kaste unntak, hvis antall er lik null så legger vi
inn verdi hvis ikke så opretter ny hode og ny hale og sette hver på riktig plass.

I oppgave 6  

I oppgave 7 starter vi med en node som får verdien i hode. Deretter en while setning på når aktuell ikke er lik null. 
Da oppdateres hode til aktuell.neste. Hode oppdateres til null, og aktuell oppdateres til hode. 
Videre har vi en if setning dersom hode er lik hale. Denne gir antall verdien 0.
Deretter setter vi verdien til hode og hale = null. endringer++ øker antall endringer med en. 
Og break for å stoppe loopen. Til slutt har vi antall-- som teller nedover. Metode 2 fungerer også. 

I oppgave 8 

I oppgave 9 

I oppgave 10 starter vi med en requireNonNull som tester etter null-verdier som ikke er tillatt. 
Videre har vi en for løkke som itererer gjennom listen. Etter dette har vi en nestet for løkke. 
Så har vi en compare setning mellom liste.hent(i) og liste.hent(j) ved hjelp av komparatoren c. 
Deretter har vi to midlertidige variabler, verdi, og verdi2. Og til slutt oppdaterer vi i med verdien i verdi2, 
og j med verdien i verdi.
