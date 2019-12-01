package ohtu;

import javax.swing.*;

public class Erotus extends Komento {
    public Erotus(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        int arvo = lueArvo();
        sovellus.miinus(arvo);
        naytaLaskunTulos();
    }

    @Override
    public void peru() {
        System.out.println("undo pressed");
    }
}
