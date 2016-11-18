package domino.vista;

import domino.model.Fitxa;
import domino.model.Jugador;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class VistaText {

    private Scanner lector;

    public VistaText() {
        resetearScanner();

    }

    /**
     * Dades que es mostren al iniciar el torn
     * @param torn actual
     * @param jugador actual
     */
    public void dadesTorn(int torn, Jugador jugador) {
        System.out.println("TORN: " + torn + "\t\t" + jugador.getNom());
    }

    /**
     * Metode que mostra les fitxes jugades durant el transcurs de la partida
     *
     * @param fitxesJugades arrayDeque que mostra les fitxes que s'han jugat
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
     * Mostra les fitxes actuals del jugador
     *
     * @param fitxes arrayList del jugador que conte les fitxes
     */
    public void fitxesJugador(List<Fitxa> fitxes) {
        for (int i = 0; i < fitxes.size(); i++) {
            System.out.print(" " + fitxes.get(i).valors[0] + ":" + fitxes.get(i).valors[1] + " ");
        }
    }

    /**
     * Metode que mostra les opcions del menu i crida a comprovacioMenu per escogir la opcio.
     * @return la opcio escogida
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
     * Metode que demana la posicio on volem colocar la fitxa i realitza comprovacions
     * @return torna el costat on es ficara
     */
    public boolean costatFitxa() {
        boolean costat = false;
        String orientacio;
        do {
            System.out.println("En quin costat voleu col·locar la fitxa? (E|D)");
            orientacio = lector.nextLine();
        } while (comprovarCostat(orientacio));

        if (orientacio.equals("e") || orientacio.equals("E")) {
            costat = true;
        } else if (orientacio.equals("d") || orientacio.equals("D")) {
            costat = false;
        }
        return costat;
    }

    /**
     * Metode que imprimeix el guanyador del joc
     * @param guanyador li entra per parametre el guanyador
     */
    public void imprimirGuanyador(Jugador guanyador) {
        System.out.println("El guanyador es: " + guanyador.getNom());
    }

    /**
     * Metode que comprova que la opcio escogida pel menu es correcte.
     * @return torna la opcio validada
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
    /**
     * Aquest metode obte per parametre el jugador del torn i demana la posicio de la fitxa.
     * Fa un bucle fins que obtenim un valor correcte de posicio (o 0 per sortir)
     * Un cop obtingut retorna la posicio de la fitxa
     * @param jugador li entra el jugador del torn acutal per parametre
     * @return torna la posicio de la fitxa validada
     */
    public int comprovarValorPosicio(Jugador jugador) {
        int num = 0;
        System.out.print("Introdueix la posicio de la fitxa que vols escogir\n"
                + "Si t'has equivocat i no tens cap fitxa per col·locar, posa 0: ");
        do {
            //Necessito aquest metode perque el apuntador per X motiu que desconec no apunta be.
            resetearScanner();
            String numero = lector.nextLine();
            try {
                num = Integer.parseInt(numero);
                if (num > 0 && num < jugador.getFitxes().size() +1) {
                    break;
                } else if (num == 0) {
                    break;
                } else {
                    System.out.print("Introdueix un valor adequat: ");
                }
            } catch (Exception e) {
                System.out.print("Introdueix un numero: ");
            }
        } while (true);
        return num;
    }
    /**
     * Aquest metode el necessitem per comprovarValorPosicio, ja que extranyament
     * apunta malament al metode en questio i necessitem resetejarlo.
     */
    private void resetearScanner() {
        lector = null;
        lector = new Scanner(System.in);
    }
}
