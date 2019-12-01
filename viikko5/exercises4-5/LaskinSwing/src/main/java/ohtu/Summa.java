package ohtu;

import javax.swing.*;

public class Summa extends Komento {
    public Summa(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        int arvo = lueArvo();
        sovellus.plus(arvo);
        naytaLaskunTulos();
    }

    @Override
    public void peru() {
        System.out.println("undo pressed");
    }
}
