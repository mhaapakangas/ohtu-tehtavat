package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"),
            eq("33333-44455"), eq(5));
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeinKahdelleEriTuotteelle() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(6);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "makkara", 8));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"),
            eq("33333-44455"), eq(13));
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeinKahdelleSamalleTuotteelle() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10, 9);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"),
            eq("33333-44455"), eq(10));
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeinKunTuoteOnLoppu() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(varasto, never()).haeTuote(2);
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"),
            eq("33333-44455"), eq(5));
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(6);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "makkara", 8));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"),
            eq("33333-44455"), eq(5));

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("matti", "4567");

        verify(pankki).tilisiirto(eq("matti"), eq(42), eq("4567"),
            eq("33333-44455"), eq(8));
    }

    @Test
    public void kauppaPyytaaJokaMaksulleUudenTilinumeron() {
        when(viite.uusi()).thenReturn(42)
            .thenReturn(43);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"),
            eq("33333-44455"), eq(5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("matti", "4567");

        verify(pankki).tilisiirto(eq("matti"), eq(43), eq("4567"),
            eq("33333-44455"), eq(5));
    }

    @Test
    public void tuotePoistetaanOstoskoristaJaPalautetaanVarastoon() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10, 9);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");

        verify(varasto).palautaVarastoon(new Tuote(1, "maito", 5));
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"),
            eq("33333-44455"), eq(5));
    }
}
