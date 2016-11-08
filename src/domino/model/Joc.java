package domino.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class Joc {

    public final int NUMJUGADORS, NUMFITXES, NUMFITXESJUGADOR;
    private ArrayDeque<Fitxa> fitxesJugades;
    private ArrayList<Fitxa> fitxesInicials;
    private Jugador[] jugadors;
    private int comptPassar;
    private int torn;
    private boolean finalitzat;
    private Jugador guanyador;
    private Fitxa fitxaInicial;

    public Joc(int numJugadors, int numFitxes, int numFitxesJugador) {
        NUMJUGADORS = numJugadors;
        NUMFITXES = numFitxes;
        NUMFITXESJUGADOR = numFitxesJugador;
    }

    public ArrayDeque<Fitxa> getFitxesJugades() {
        return fitxesJugades;
    }

    public int getComptPassar() {
        return comptPassar;
    }

    public int getTorn() {
        return torn;
    }

    public Jugador[] getJugadors() {
        return jugadors;
    }

    public void setComptPassar(int comptPassar) {
        this.comptPassar = comptPassar;
    }

    public Fitxa getFitxaInicial() {
        return fitxaInicial;
    }

    public boolean isFinalitzat() {
        return finalitzat;
    }

    public Jugador getGuanyador() {
        return guanyador;
    }
/**
 * 
 * @param noms 
 */
    public void iniciar(String[] noms) {
        fitxesJugades = new ArrayDeque();
        comptPassar = 0;
        finalitzat = false;        
        crearJugadors(noms);
        crearFitxes();
        repartirFitxes();       
        iniciarTorn();
    }

    public void repartirFitxes() {
        int[] aleatoris = generarAleatoris();
        int pos = 0;
        for (int i = 0; i < NUMJUGADORS; i++) {
            for (int j = 0; j < NUMFITXESJUGADOR; j++) {
                jugadors[i].getFitxes().add(fitxesInicials.get(aleatoris[pos++]));
            }
        }
    }

    private void crearFitxes() {
        fitxesInicials = new ArrayList();
        int pos = 0;
        for (int i = 0; i < NUMFITXESJUGADOR; i++) {
            for (int j = i; j < NUMFITXESJUGADOR; j++) {
                int[] valors = {i, j};
                fitxesInicials.add(new Fitxa(valors));
            }
        } 
    }

    private void crearJugadors(String[] noms) {
        jugadors = new Jugador[NUMJUGADORS];
        for (int i = 0; i < NUMJUGADORS; i++) {
            jugadors[i] = new Jugador(noms[i]);
        }
    }

    private void iniciarTorn() {
        fitxaInicial=fitxesInicials.get(NUMFITXES-1);
        for (int i = 0; i < NUMJUGADORS; i++) {
            if (jugadors[i].getFitxes().contains(fitxaInicial)) {
                torn = i;
                break;
            }
        }
    }

    /**
     * Es generen els NUMFITXES nombres de l'0 al NUMFITXES-1 de forma
     * aleatòria i sense repetició utilitzant el mètode nexInt(int n) de la
     * classe Random.
     *
     * @return int[] aleatoris emplenat amb els valors generats.
     */
    public int[] generarAleatoris() {
        int[] aleatoris = new int[NUMFITXES];
        boolean[] aux = new boolean[NUMFITXES];
        int num;
        Random rand = new Random();

        for (int i = 0; i < NUMFITXES;) {
            num = rand.nextInt(NUMFITXES);
            if (!aux[num]) {
                aleatoris[i] = num;
                aux[num] = true;
                i++;
            }
        }
        return aleatoris;
    }

    public void actualitzarEstat() {       
        if (jugadors[torn].getFitxes().isEmpty()) {
            guanyador = jugadors[torn];
            finalitzat = true;
        } else if (comptPassar == NUMJUGADORS) {
            guanyador = trobarGuanyador();
            finalitzat = true;
        } else {
            torn = (++torn) % NUMJUGADORS;
        }
    }

    private ArrayList<Jugador> comptFitxes(ArrayList<Jugador> jugs) {
        ArrayList<Jugador> minimFitxes = new ArrayList();
        int minim = jugs.get(0).getFitxes().size();
        int aux;
        int pos = 0;

        for (int i = 1; i < jugs.size(); i++) {
            if ((aux = jugs.get(i).getFitxes().size()) < minim) {
                minim = aux;
                pos = i;
            }
        }
        minimFitxes.add(jugs.get(pos));
        for (int i = 1; i < jugs.size(); i++) {
            if (((aux = jugs.get(i).getFitxes().size()) == minim) && i != pos) {
                minimFitxes.add(jugs.get(i));
            }
        }
        return minimFitxes;
    }

    private ArrayList<Jugador> comptPunts() {
        ArrayList<Jugador> minimPunts = new ArrayList();
        int[] punts = new int[NUMJUGADORS];
        int minim, pos = 0;

        for (Fitxa f : jugadors[0].getFitxes()) {
            punts[0] += f.getValors()[0] + f.getValors()[1];
        }
        minim = punts[0];

        for (int i = 1; i < jugadors.length; i++) {

            for (Fitxa f : jugadors[i].getFitxes()) {
                punts[i] += f.getValors()[0] + f.getValors()[1];
            }
            if (punts[i] < minim) {
                minim = punts[i];
                pos = i;
            }
        }
        minimPunts.add(jugadors[pos]);
        for (int i = 1; i < jugadors.length; i++) {
            if (punts[i] == minim && i != pos) {
                minimPunts.add(jugadors[i]);
            }
        }
        return minimPunts;
    }

    private Jugador trobarGuanyador() {
        Jugador guanya;
        ArrayList<Jugador> minimPunts = comptPunts();
        if (minimPunts.size() == 1) {
            guanya = minimPunts.get(0);
        } else {
            guanya = comptFitxes(minimPunts).get(0);
        }
        return guanya;
    }
}
