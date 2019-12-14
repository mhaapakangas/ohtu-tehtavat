package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSPeli {
    protected static final Scanner scanner = new Scanner(System.in);

    public static KPSPeli luoKaksinpeli() {
        return new KPSPelaajaVsPelaaja();
    }

    public static KPSPeli luoHelppoYksinpeli() {
        return new KPSTekoaly();
    }

    public static KPSPeli luoVaikeaYksinpeli() {
        return new KPSParempiTekoaly();
    }

    public final void pelaa() {
        alustaPeli();
        Tuomari tuomari = new Tuomari();

        String ekanSiirto = ekanPelaajanSiirto();
        String tokanSiirto = toisenPelaajanSiirto(ekanSiirto);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            ekanSiirto = ekanPelaajanSiirto();
            tokanSiirto = toisenPelaajanSiirto(ekanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    private String ekanPelaajanSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    protected abstract String toisenPelaajanSiirto(String ekanSiirto);

    protected void alustaPeli() {}
}
