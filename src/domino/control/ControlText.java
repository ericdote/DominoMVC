package domino.control;

import domino.model.Fitxa;
import domino.model.Joc;
import domino.model.Jugador;
import domino.model.Torn;
import domino.vista.VistaText;

public class ControlText {

    private final Joc joc;
    private final VistaText vt;
    private Torn torn;
    private Jugador jug;
    private Fitxa fitxa;

    public ControlText() {
        this.joc = new Joc(4, 28, 7);
        this.vt = new VistaText();
        this.torn = new Torn(joc);
    }

    public void inici() {
        joc.iniciar(new String[]{"J1", "J2", "J3", "J4"});
        torn.inicial();
        joc.torn = joc.getTorn() + 1;
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

    public void controlMenu() {
        int opcio = vt.menu();

        switch (opcio) {
            case 1:
                do {
                    boolean comprobacio = colocarFitxa();
                    if (comprobacio) {
                        break;
                    }
                } while (true);
                break;
            case 2:
                do {
                    boolean comprobacio = colocarDobles();
                    if (comprobacio) {
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

    public boolean colocarFitxa() {
        boolean comprobacio, extrem;
        Fitxa f;
        int posicio;

        posicio = vt.fitxaEscogida(joc.jugadors[joc.getTorn()].getFitxes());
        f = joc.jugadors[joc.getTorn()].getFitxes().get(posicio);
        extrem = vt.costatFitxa();
        comprobacio = torn.colocarUnaFitxa(f, extrem);

        return comprobacio;
    }

    public boolean colocarDobles() {
        boolean comprobacio, extrem1, extrem2;
        int posicio1, posicio2;
        Fitxa f1, f2;

        System.out.println("Primer doble");
        posicio1 = vt.fitxaEscogida(joc.jugadors[joc.getTorn()].getFitxes());
        extrem1 = vt.costatFitxa();
        f1 = jug.getFitxes().get(posicio1);

        System.out.println("Primer doble");
        posicio2 = vt.fitxaEscogida(joc.jugadors[joc.getTorn()].getFitxes());
        extrem2 = vt.costatFitxa();
        f2 = jug.getFitxes().get(posicio2);

        comprobacio = torn.colocarDosDobles(f1, extrem1, f2, extrem2);
        return comprobacio;
    }



}
