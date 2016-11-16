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

    public void dadesTorn(int torn, Jugador jugador) {
        System.out.println("TORN: " + torn + "         " + jugador.getNom());
    }

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

    public void fitxesJugador(List<Fitxa> fitxes) {
        for (int i = 0; i < fitxes.size(); i++) {
            System.out.print(" " + fitxes.get(i).valors[0] + ":" + fitxes.get(i).valors[1] + " ");
        }
    }

    public int menu() {
        System.out.println("Escogeix la jugada: \n"
                + "1- Col·locar 1 fitxa \n"
                + "2- Col·locar 2 dobles \n"
                + "3- Passar\n");
        int opcio = comprovacioMenu();
        return opcio;
    }

    public int fitxaEscogida(List<Fitxa> fitxas) {
        int fitxa;
        do {
            System.out.print("Introdueix la posicio de la fitxa que vols escogir: ");
            fitxa = comprovarPosicioFitxa();
            break;
        } while (comprovarFitxa(fitxa, fitxas.size()));
        return fitxa - 1;
    }

    public boolean comprovarFitxa(int fitxa, int fitxesJugador) {
        boolean comprovar = true;
        if (fitxa > 0 && fitxesJugador <= 7) {
            comprovar = false;
        }
        return comprovar;
    }

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

    public void imprimirGuanyador(Jugador guanyador) {
        System.out.println("El guanyador es: " + guanyador.getNom());
    }

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

    public boolean comprovarCostat(String orientacio) {
        boolean comprovacio = true;
        if (orientacio.equals("e") || orientacio.equals("E") || orientacio.equals("d") || orientacio.equals("D")) {
            comprovacio = false;
        }
        return comprovacio;
    }

    public int comprovarPosicioFitxa() {
        int fitxa;
        do {
            if (lector.hasNextInt()) {
                fitxa = lector.nextInt();
                if (fitxa > 0 && fitxa <= jug.getFitxes().size()) {
                    break;
                } else {
                    System.out.print("Introdueix un numero valid: ");
                }
            } else {
                System.out.print("Ha de ser un numero: ");
                lector.nextLine();
            }
        } while (true);
        return fitxa;
    }
}
