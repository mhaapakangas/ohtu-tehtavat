package ohtu;

import javax.swing.*;

public class Nollaa extends Komento {
    public Nollaa(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, sovellus);
    }

    @Override
    public void suorita() {
        edellinenArvo = sovellus.tulos();
        sovellus.nollaa();
        naytaLaskunTulos();
    }

    @Override
    public void peru() {
        sovellus.plus(edellinenArvo);
        naytaLaskunTulos();
    }
}
