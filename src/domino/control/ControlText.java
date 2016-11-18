package domino.control;

import domino.model.Fitxa;
import domino.model.Joc;
import domino.model.Jugador;
import domino.model.Torn;
import domino.vista.VistaText;

public class ControlText {

    //Variables que utilitzarem durant tota la clase
    private final Joc joc;
    private final VistaText vt;
    private Torn torn;
    private Jugador jug;
    private Fitxa fitxa;

    /**
     * Inicialitzem el constructor amb el numero de jugadors, les fitxes, i
     * quantes fitxes per jugador
     */
    public ControlText() {
        this.joc = new Joc(4, 28, 7);
        this.vt = new VistaText();
        this.torn = new Torn(joc);
    }

    /**
     * Aquest metode inicialitza el joc, creant els jugadors, fent el torn
     * inicial i comenzant al seguent torn Un cop fet aixo fa un bucle que
     * durará fins que el metode isFinalitzat indiqui que acaba el joc. Dins del
     * bucle es mostren les dades del torn, les fitxes jugades, les fitxes del
     * jugador, el menu i actualitza l'estat Un cop acabar mostra el guanyador.
     */
    public void inici() {
        joc.iniciar(new String[]{"J1", "J2", "J3", "J4"});
        torn.inicial();
        joc.torn = joc.getTorn();
        joc.actualitzarEstat();
        do {
            vt.dadesTorn(joc.getTorn(), joc.jugadors[joc.getTorn()]);
            System.out.println();

            vt.fitxesJugades(joc.fitxesJugades);
            System.out.println();

            vt.fitxesJugador(joc.jugadors[joc.getTorn()].getFitxes());
            System.out.println();

            controlMenu();

            joc.actualitzarEstat();
        } while (!joc.isFinalitzat());
        vt.imprimirGuanyador(joc.trobarGuanyador());
    }

    /**
     * Mostra el menu i rep l'opcio que vol escogir l'usuari. En els 2 primers
     * cassos fa comprovacions i en cas de poder colocar surt. En el cas 3 passa
     * torn.
     */
    public void controlMenu() {
        int opcio = vt.menu();
        boolean comprobacio = false;
        switch (opcio) {
            case 1:
                do {
                    comprobacio = colocarFitxa();
                    if (comprobacio) {
                        break;
                    } else {
                        torn.passar();
                        break;
                    }
                } while (true);
                break;
            case 2:
                do {
                    comprobacio = colocarDobles();
                    if (comprobacio) {
                        break;
                    } else {
                        torn.passar();
                        break;
                    }
                } while (true);
                break;
            case 3:
                torn.passar();
                break;
            default:
                System.out.println("No es una opcio valida");
                break;
        }
    }

    /**
     * Aquest metode agafa la posicio de la fitxa, la fitxa que es i el seu
     * extrem. Un cop agafat tot aixo realitza una comprovacio per probar a
     * colocar la fitxa.
     *
     * @return la comprobacio.
     */
    public boolean colocarFitxa() {
        boolean comprobacio = false, extrem;
        Fitxa f;
        int posicio;

        posicio = vt.comprovarValorPosicio(joc.jugadors[joc.getTorn()]) - 1;
        switch (posicio) {
            case -1:
                break;
            default:

                f = joc.jugadors[joc.getTorn()].getFitxes().get(posicio);
                extrem = vt.costatFitxa();
                comprobacio = torn.colocarUnaFitxa(f, extrem);
                break;
        }
        return comprobacio;
    }

    /**
     * Aquest metode agafa les 2 posicions (cadascuna d'una fitxa), la fitxa que
     * es i el seu extrem. Un cop agafat tot realitza una comprobacio i les
     * coloca.
     *
     * @return la comprobacio.
     */
    public boolean colocarDobles() {
        boolean comprobacio = false, extrem1, extrem2;
        int posicio1, posicio2, aux;
        Fitxa f1, f2;
        System.out.print("Introdueix el primer doble: ");
        posicio1 = vt.comprovarValorPosicio(joc.jugadors[joc.getTorn()]) - 1;
        switch (posicio1) {
            case -1:
                break;
            default:
                extrem1 = vt.costatFitxa();
                f1 = joc.jugadors[joc.getTorn()].getFitxes().get(posicio1);
                System.out.print("Introdueix el segon doble: ");
                posicio2 = vt.comprovarValorPosicio(joc.jugadors[joc.getTorn()]) - 1;
                extrem2 = vt.costatFitxa();
                f2 = joc.jugadors[joc.getTorn()].getFitxes().get(posicio2);
                comprobacio = torn.colocarDosDobles(f1, extrem1, f2, extrem2);
                break;
        }
        return comprobacio;
    }
}
