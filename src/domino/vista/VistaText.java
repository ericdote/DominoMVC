package domino.vista;

import domino.control.ControlText;
import domino.model.Fitxa;
import domino.model.Joc;
import domino.model.Jugador;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class VistaText {

    private final Scanner lector = new Scanner(System.in);
    private Jugador jug;
    private Joc joc;
    private ControlText ct = new ControlText();

    /**
     * Dades que es mostraran al principi de cada torn
     *
     * @param torn torn actual
     * @param jugador jugador actual
     */
    public void dadesTorn(int torn, Jugador jugador) {
        System.out.println("TORN: " + torn + "         " + jugador.getNom());
    }

    /**
     * Mostra les fitxes jugades durant la partida
     *
     * @param fitxesJugades arrayDeque que te les fitxes que s'han jugat durant
     * la partida
     */
    public void fitxesJugades(Deque<Fitxa> fitxesJugades) {

        StringBuilder sb = new StringBuilder();

        for (Fitxa fitxes : fitxesJugades) {
            sb.append("|");
            sb.append(fitxes.getValors()[0]);
            sb.append(":");
            sb.append(fitxes.getValors()[1]);
            sb.append("|");
        }
        System.out.println(sb);
    }

    /**
     * Mostra les fitxes del jugador
     *
     * @param fitxes ArrayList que conte les fitxes del jugador
     */
    public void fitxesJugador(List<Fitxa> fitxes) {
        for (int i = 0; i < fitxes.size(); i++) {
            System.out.print(" " + fitxes.get(i).valors[0] + ":" + fitxes.get(i).valors[1] + " ");
        }
    }

    /**
     * Metode que mostra el menu per escogir una opcio La opcio es escogida amb
     * el metode que comprova que entri un valor correcte
     *
     * @return la opcio escogida per en ControlText donar pas a la accio
     * seleccionada
     */
    public int menu() {
        System.out.println("Escogeix la jugada: \n"
                + "1- Col·locar 1 fitxa \n"
                + "2- Col·locar 2 dobles \n"
                + "3- Passar\n");
        int opcio = comprovacioMenu();
        return opcio;
    }

    /**
     * Mostra per pantalla un sout on es demana la posicio de la fitxa La fitxa
     * s'escogeix amb comprovarPosicioFitxa.
     *
     * @param fitxas ArrayList de fitxes del jugador
     * @return Torna la posicio de la fitxa seleccionada pel jugador -1 per
     * indicar la posicio en l'array.
     */
    public int fitxaEscogida(List<Fitxa> fitxas) {
        int fitxa;
        do {
            System.out.print("Introdueix la posicio de la fitxa que vols escogir: ");
            fitxa = comprovarValorPosicio();
            if (!ct.comprovarPosicioFitxa(fitxa)) {
                System.out.print("No es valor correcte, introduiu un altre: ");
            }
        } while (!comprovarFitxa(fitxa, fitxas.size()));
        return fitxa - 1;
    }

    public boolean comprovarFitxa(int fitxa, int fitxesJugador) {
        boolean comprovar = false;
        if (fitxa > 0 && fitxesJugador <= 7) {
            comprovar = true;
        }
        return comprovar;
    }

    /**
     * Demana el costat on volem col·locar la fitxa. I fa una comprovacio de que
     * nomes es pugui col·locar la fitxa a esquerra o dreta
     *
     * @return Torna un boolean dient si es pot o no colocar la fitxa al costat
     * escogit.
     */
    public boolean costatFitxa() {
        boolean costat = false;
        String orientacio;
        do {
            System.out.println("En quin costat voleu col·locar la fitxa? (E|D)");
            orientacio = lector.next();
        } while (comprovarCostat(orientacio));

        if (orientacio.equals("e") || orientacio.equals("E")) {
            costat = true;
        } else if (orientacio.equals("d") || orientacio.equals("D")) {
            costat = false;
        }
        return costat;
    }

    /**
     * Imprimeix el guanyador
     *
     * @param guanyador li entra per parametre el nom del guanyador
     */
    public void imprimirGuanyador(Jugador guanyador) {
        System.out.println("El guanyador es: " + guanyador.getNom());
    }

    /**
     * Metode fet per comprovar que entra un numero correcte y no una lletra o
     * un numero invalid.
     *
     * @return Torna el numero de la opcio
     */
    public int comprovacioMenu() {
        int opcio = 0;
        do {
            if (lector.hasNextInt()) {
                opcio = lector.nextInt();
                if (opcio > 0 && opcio < 4) {
                    break;
                } else {
                    System.out.print("Introdueix un numero valid: ");
                }
            } else {
                System.out.print("Ha de ser un numero: ");
                lector.nextLine();
            }
        } while (true);
        return opcio;
    }

    /**
     * Metode per comprovar que la orientacio que entra es una orientacio
     * correcta, es a dir, e/E/d/D i no cap altre
     *
     * @param orientacio
     * @return
     */
    public boolean comprovarCostat(String orientacio) {
        boolean comprovacio = true;
        if (orientacio.equals("e") || orientacio.equals("E") || orientacio.equals("d") || orientacio.equals("D")) {
            comprovacio = false;
        }
        return comprovacio;
    }

    public int comprovarValorPosicio() {
        int posicio = 0;
        if (lector.hasNextInt()) {
            posicio = lector.nextInt();
        } else {
            System.out.println("Ha de ser un numero la posicio");
        }
        return posicio;
    }

}
