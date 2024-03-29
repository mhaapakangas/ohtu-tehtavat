package ohtu;

import javax.swing.*;

public abstract class Komento {
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private JButton nollaa;
    protected Sovelluslogiikka sovellus;
    protected int edellinenArvo;

    public Komento(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.sovellus = sovellus;
    }

    public abstract void suorita();

    public abstract void peru();

    protected int lueArvo() {
        try {
            return Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    protected void naytaLaskunTulos() {
        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        if (laskunTulos == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
    }
}
