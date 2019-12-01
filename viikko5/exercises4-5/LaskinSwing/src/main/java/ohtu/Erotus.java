package ohtu;

import javax.swing.*;

public class Erotus extends Komento {
    public Erotus(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, sovellus);
    }

    @Override
    public void suorita() {
        int arvo = lueArvo();
        sovellus.miinus(arvo);
        naytaLaskunTulos();
        edellinenArvo = arvo;
    }

    @Override
    public void peru() {
        sovellus.plus(edellinenArvo);
        naytaLaskunTulos();
    }
}
