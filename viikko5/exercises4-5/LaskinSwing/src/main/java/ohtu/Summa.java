package ohtu;

import javax.swing.*;

public class Summa extends Komento {
    public Summa(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, sovellus);
    }

    @Override
    public void suorita() {
        int arvo = lueArvo();
        sovellus.plus(arvo);
        naytaLaskunTulos();
        edellinenArvo = arvo;
    }

    @Override
    public void peru() {
        sovellus.miinus(edellinenArvo);
        naytaLaskunTulos();
    }
}
