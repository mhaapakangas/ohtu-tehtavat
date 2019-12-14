package ohtu.kivipaperisakset;


public class KPSTekoaly extends KPSPeli {

    protected Tekoaly tekoaly;

    @Override
    protected void alustaPeli() {
        tekoaly = new Tekoaly();
    }

    @Override
    protected String toisenPelaajanSiirto(String ekanSiirto) {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return siirto;
    }
}