package ohtu.kivipaperisakset;


public class KPSParempiTekoaly extends KPSTekoaly {

    @Override
    protected void alustaPeli() {
        tekoaly = new TekoalyParannettu(20);
    }

}
