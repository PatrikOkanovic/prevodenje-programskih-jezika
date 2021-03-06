import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Cvor {

	private String friskNaredbe;

	private String ime = "";

	private Cvor otac;

	private List<Cvor> djecica;

	private String sadrzaj = "";

	private int dubina;

	private boolean jeDefiniran;

	private boolean lijeviIzraz;

	private boolean unutarPetlje;

	private boolean jeKonstanta;

	private String tip = "";

	private int duljinaNiza = -1;

	private ArrayList<String> tipovi = new ArrayList<String>();

	private ArrayList<String> imena = new ArrayList<String>();

	private String labela;

	private Function<String, Boolean> intIliChar = (t) -> t.equals("int") || t.equals("char");

	public Cvor(String sadrzaj, int dubina, Cvor otac, List<Cvor> djecica) {
		super();
		this.sadrzaj = sadrzaj;
		this.otac = otac;
		this.djecica = djecica;

		if (sadrzaj.contains(" ")) {
			ime = sadrzaj.split(" ")[2];
		} else {
			ime = sadrzaj;
		}

		this.friskNaredbe = "";
	}

	public Cvor(String sadrzaj, int dubina, ArrayList<Cvor> djecica) {

		this.sadrzaj = sadrzaj;
		this.djecica = djecica;

		this.friskNaredbe = "";
	}

	public void dodajFriskNaredbe(String naredba) {
		friskNaredbe += naredba;
	}

	public String getFriskNaredbe() {
		return friskNaredbe;
	}

	public void setFriskNaredbe(String friskNaredbe) {
		this.friskNaredbe = friskNaredbe;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Cvor getOtac() {
		return otac;
	}

	public void setOtac(Cvor otac) {
		this.otac = otac;
	}

	public List<Cvor> getDjecica() {
		return djecica;
	}

	public void setDjecica(List<Cvor> djecica) {
		this.djecica = djecica;
	}

	public static void ispisi(Cvor korijen, int br) {
		System.out.println(" " + korijen);
		List<Cvor> djecica = korijen.getDjecica();
		for (int i = djecica.size() - 1; i >= 0; i--) {
			ispisi(djecica.get(i), br + 1);
		}
	}

	public Cvor kopijaCvora() {
		return new Cvor(ime, dubina, otac, djecica);
	}

	public void dodajMalog(Cvor mali) {

		this.djecica.add(mali);

	}

	public String getSadrzaj() {

		return sadrzaj;

	}

	public void setSadrzaj(String sadrzaj) {

		this.sadrzaj = sadrzaj;

	}

	public int getDubina() {

		return dubina;

	}

	public Cvor dajMalogNaIndeksu(int index) {

		return djecica.get(index);

	}

	public boolean isJeDefiniran() {

		return jeDefiniran;

	}

	public void setJeDefiniran(boolean jeDefiniran) {

		this.jeDefiniran = jeDefiniran;

	}

	public boolean isLijeviIzraz(Tablica djelokrug) {

		if (!this.getSadrzaj().startsWith("IDN")) {
			return lijeviIzraz;
		}

		Tablica kopija = djelokrug.copy();

		while (kopija != null) {

			for (Cvor c : kopija.getDeklarirano()) {
				if (this.getIme().equals(c.getIme())) {

					return !c.isJeFunkcija() && intIliChar.apply(c.getTip(djelokrug));

				}
			}

			kopija = kopija.getOtac();

		}

		return lijeviIzraz;
	}

	public boolean isLijeviIzraz() {

		return lijeviIzraz;

	}

	public void setLijeviIzraz(boolean lijeviIzraz) {

		this.lijeviIzraz = lijeviIzraz;

	}

	public boolean isUnutarPetlje() {

		return unutarPetlje;

	}

	public void setUnutarPetlje(boolean unutarPetlje) {

		this.unutarPetlje = unutarPetlje;

	}

	public boolean isJeKonstanta() {

		return jeKonstanta;

	}

	public void setJeKonstanta(boolean jeKonstanta) {

		this.jeKonstanta = jeKonstanta;

	}

	public boolean isJeFunkcija() {
		return !tipovi.isEmpty();
	}

	public String getTip(Tablica djelokrug) {

		if (!this.getSadrzaj().startsWith("IDN")) {
			return tip;
		}

		Tablica kopija = djelokrug.copy();

		while (kopija != null) {

			for (Cvor c : kopija.getDeklarirano()) {
				if (this.getIme().equals(c.getIme())) {
					return c.getTip();
				}
			}

			kopija = kopija.getOtac();

		}

		return tip;
	}

	public String getTip() {

		return tip;

	}

	public void setTip(String tip) {

		this.tip = tip;

	}

	public void ispis() {
		try {
			List<String> redovi = Arrays.asList(friskNaredbe.split("\n"));
			int counter = 0;
			for (String red : redovi) {
				if (!red.startsWith(" ") && counter == 0) {
					counter++;

					GeneratorKoda.fos.write(" `BASE D\n".getBytes());
					GeneratorKoda.fos.write(" CALL F_MAIN\n".getBytes());
					GeneratorKoda.fos.write(" HALT\n".getBytes());

				}
				GeneratorKoda.fos.write(red.getBytes());
				GeneratorKoda.fos.write("\n".getBytes());
			}
		} catch (Exception e) {
		}

	}

	public ArrayList<String> getTipovi(Tablica djelokrug) {

		if (!this.getSadrzaj().startsWith("IDN")) {
			return tipovi;
		}

		Tablica kopija = djelokrug.copy();

		while (kopija != null) {

			for (int i = kopija.getDeklarirano().size() - 1; i >= 0; i--) {
				Cvor c = kopija.getDeklarirano().get(i);
				if (this.getIme().equals(c.getIme())) {
					return c.getTipovi(null);
				}
			}

			kopija = kopija.getOtac();

		}

		return tipovi;

	}

	public ArrayList<String> getTipovi() {
		return tipovi;
	}

	public void setTipovi(ArrayList<String> tipovi) {
		this.tipovi = tipovi;
	}

	public ArrayList<String> getImena() {
		return imena;
	}

	public void setImena(ArrayList<String> imena) {
		this.imena = imena;
	}

	public void setDubina(int dubina) {
		this.dubina = dubina;
	}

	public void dodajTip(String tip) {

		tipovi.add(tip);

	}

	public void dodajIme(String ime) {

		imena.add(ime);

	}

	public int getDuljinaNiza() {
		return duljinaNiza;
	}

	public void setDuljinaNiza(int duljinaNiza) {
		this.duljinaNiza = duljinaNiza;
	}

	public String dajLabelu() {
		return labela;
	}

	public void postaviLabelu(String labela) {
		this.labela = labela;
	}

}