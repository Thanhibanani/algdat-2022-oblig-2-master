package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        // Kaster unntak for null tabell
        if (a == null) {
            throw new NullPointerException("Tabell a er null!");
        }

        a = fjernNullVerdier(a);             // Fjerner alle null-verdier
        if (a.length == 0) {
            return;
        }         // Hopper ut av metoden hvis listen er tom

        Node<T> aktuell = new Node<>(a[0]);  // Opretter første node, og gir den verdi
        antall++;                            // Opdaterer antall noder
        hode = aktuell;                      // Setter hode lik første node

        for (int i = 1; i < a.length; i++) {
            Node<T> neste = new Node<>(a[i]);    // Opretter ny node
            antall++;                            // Opdaterer antall noder
            aktuell.neste = neste;               // Setter aktuell sin neste peker
            neste.forrige = aktuell;             // Setter neste sin forrige peker
            aktuell = neste;                     // Setter aktuell lik neste
        }
        hale = aktuell;                          // Setter halen lik siste node
    }

    //Hjelpemetode
    private T[] fjernNullVerdier(T[] a) {
        int antallNullverdier = 0;              // Teller for antall nullverdier

        for (T verdi : a) {
            if (verdi == null) {                  // Tester hvor mange verdier som er null
                antallNullverdier++;
            }
        }

        if (antallNullverdier == 0) {
            return a;
        }     // Returnerer oprinnelig liste hvis det ikke er noe nullverdier

        T[] b = (T[]) new Object[a.length - antallNullverdier];     // Opretter returlisten

        int j = 0;  // indeks for returlisten

        // Legger til alle verdiene som ikke er null til returlisten
        for (T t : a) {
            if (t != null) {
                b[j] = t;
                j++;
            }
        }

        return b;

    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);            // Sjekker at fra og til argumentene er innenfor listen sin lengde
        Node<T> aktuell = hode;                      // Lagerer hodet
        for (int i = 0; i < fra; i++) {
            aktuell = aktuell.neste;                 // Flytter aktuell fra hode til fra
        }
        T[] sublisteInput = (T[]) new Object[til - fra]; // Oppretter sublisteInput

        int indeks = 0;

        for (int i = fra; i < til; i++) {
            sublisteInput[indeks] = aktuell.verdi;        // Fyller sublisteInput med verdiene til [fra-til> sine noder
            aktuell = aktuell.neste;
            indeks++;
        }
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>(sublisteInput); // Oppretter sublisten, og fyller den
        return subliste;
    }

    //Hjelpemetode
    private void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0) {                                // fra er negativ
            throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        }
        if (til > antall) {                        // til er utenfor tabellen
            throw new IndexOutOfBoundsException("til(" + til + ") > antall(" + antall + ")");
        }
        if (fra > til) {                               // fra er større enn til
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }
    }


    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if(antall > 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt");// Tester for null-verdi

        //legger inn en ny node bakerst
        if(antall > 0){
            Node<T> ny = new Node<>(verdi);                 // Opretter ny node
            hale.neste = ny;                                // Setter hale sin neste-peker lik ny
            ny.forrige = hale;                              // Setter ny sin forrige-peker lik halen
            hale = ny;                                      // Setter hale lik ny node

        } else {   // Hvis det ikke fins noen noder fra før
            Node<T> ny = new Node<>(verdi);                 // Opretter ny node
            hode = ny;                                      // Setter hode lik ny node
            hale = ny;                                      // Setter hale lik ny node
        }

        // Legger 1 til antall og endringer variablene
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        if(indeks < 0 || antall < indeks){                  //Unntak
            throw new IndexOutOfBoundsException("Kan ikke ha negativ indeks og antall kan ikke være mindre enn indeks.");
        }

        if(antall == 0) {
            leggInn(verdi);
            return;
        }

        else if(indeks == 0){
            Node<T> ny = new Node<>(verdi);                 //Oppretter ny hode
            ny.neste = hode;                                //Setter ny sin neste-peker lik hode
            hode.forrige = ny;                              //Setter hode sin forrige-peker lik ny
            hode = ny;                                      //Setter hode lik ny

        }

        else if(indeks == antall){
            Node<T> ny = new Node<>(verdi);                 //Oppretter ny hale
            ny.forrige = hale;                              //Setter ny sin forrige-peker lik hale
            hale.neste = ny;                                //Setter hale sin neste-peker lik ny
            hale = ny;                                      //Setter hale lik ny
        }

        else if(indeks > antall/2){                         //legger inn node fra høyre
            Node<T> aktuell = hode;
            for(int i = 0; i < indeks-1; i++){
                aktuell = aktuell.neste;
            }

            Node<T> ny = new Node<>(verdi);
            ny.neste = aktuell.neste;
            ny.forrige = aktuell;
            aktuell.neste = ny;
            ny.neste.forrige = ny;
        }

        else{
            Node<T> aktuell = hale;                         //legger inn node fra venstre
            for(int i = antall; i > indeks; i--){
                aktuell = aktuell.forrige;
            }

            Node<T> ny = new Node<>(verdi);
            ny.neste = aktuell.neste;
            ny.forrige = aktuell;
            aktuell.neste = ny;
            ny.neste.forrige = ny;


        }
        antall++;
        endringer++;
    }

    // Til Oppgave 4
    @Override
    public boolean inneholder(T verdi) {
            return indeksTil(verdi) != -1;
        }


    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);      // Sjekker om indeksen er ugyldig
        Node<T> returNode = finnNode(indeks);        // Finner noden til indeks og putter verdien inn i en variabel
        return returNode.verdi;                      // Returnerer noden til indeks

    }

    // Hjelpemetode
    private Node<T> finnNode(int indeks) {
        Node<T> returNode;

        if(indeks < antall/2) {                 // Hvis indeksen er mindre enn antall / 2, søker fra hode
            returNode = hode;
            int i = 0;

            // Setter returnNode lik neste verdi helt til indeksen stemmer
            while (i < indeks) {
                returNode = returNode.neste;
                i++;
            }
        } else {                                // Hvis indeks er >= antall / 2, søker fra hale
            returNode = hale;
            int i = antall-1;

            // Setter returnNode lik forrige verdi helt til indeksen stemmer
            while (i > indeks) {
                returNode = returNode.forrige;
                i--;
            }
        }

        return returNode;
    }
    
    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {                            // Sjekker om verdi finnes
            return -1;                                  // Returnerer -1 dersom den ikke gjør det
        }
        Node<T> aktuell = hode;                         // Finner verdien til hode og putter den verdien i en variabel
        for (int i = 0; i < antall; i++) {              // Standard for løkke som itererer gjennom lista antall ganger.
            if (aktuell.verdi.equals(verdi)) {          // Dersom aktuell.verdi er det samme som verdi, returneres i.
                return i;
            }
            aktuell = aktuell.neste;                    // Oppdaterer noden aktuell til verdien i aktuell.neste
        }
        return -1;                                      // Returnerer -1
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        //Om nyverdi er null kastes et unntak
        if(nyverdi == null) { throw new NullPointerException("Nyverdi kan ikke være null"); }
        indeksKontroll(indeks, false);          // Sjekker om indeksen er ugyldig
        Node<T> node = finnNode(indeks);                 // Finner noden til indeks og putter verdien inn i en variabel
        T gammelVerdi = node.verdi;                      // Lagrer nodens nåværende veri
        node.verdi = nyverdi;                            // Oppdaterer noden sin verdi
        endringer++;                                     // Øker antall endringer med 1
        return gammelVerdi;                              // Returnerer nodens verdi før den ble oppdatert
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }
    
    // Oppgave 7
    @Override
    public void nullstill() {
        // Metode 1
        Node<T> aktuell = hode;                         // Node aktuell får verdien i hode
        while (aktuell != null) {                       // While setning på når aktuell ikke er lik null
            hode = aktuell.neste;                       // Oppdaterer hode til aktuell.neste
            hode.forrige = null;                        // Oppdaterer hode forrige til null
            aktuell = hode;                             // Oppdaterer aktuell til hode

            if (hode == hale) {                         // If setning på om hode er det samme som hale
                antall = 0;                             // Gir antall verdien 0
                hode = hale = null;                     // Setter hode og hale sin verdi til null
                endringer++;                            // Øker antall endringer med en.
                break;                                  // Stopper loop.
            }
            antall--;                                   // Antall teller nedover
        }
    }



    @Override
    public String toString() {
        StringBuilder utskrift = new StringBuilder("[");
        Node<T> aktuell  = hode; //starter på hode
        /*Løper gjennom listen, så lenge aktuells nestepekeren er ulik null,
          og legger verdien til aktuell node til utskriftstrengen
         */
        while(aktuell != null) {
            if(aktuell == hale){
                utskrift.append(aktuell.verdi);
            }else{
                utskrift.append(aktuell.verdi + ", ");
            }
            aktuell = aktuell.neste;
        }
        utskrift.append("]");
        return utskrift.toString();
    }


    public String omvendtString() {
        StringBuilder utskrift = new StringBuilder("[");
        Node<T> aktuell = hale; //starter på halen

        /*Løper gjennom listen, så lenge aktuells forrigepekeren er ulik null,
          og legger verdien til aktuell node til utskriftstrengen
         */
        while(aktuell != null) {
            if(aktuell == hode){
                utskrift.append(aktuell.verdi);
            }else{
                utskrift.append(aktuell.verdi + ", ");
            }
            aktuell = aktuell.forrige;
        }
        utskrift.append("]");
        return utskrift.toString();
    }


    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


