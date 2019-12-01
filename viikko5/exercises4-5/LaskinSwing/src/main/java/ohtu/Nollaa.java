package ohtu;

import javax.swing.*;

public class Nollaa extends Komento {
    public Nollaa(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        sovellus.nollaa();
        naytaLaskunTulos();
    }

    @Override
    public void peru() {
        System.out.println("undo pressed");
    }
}
