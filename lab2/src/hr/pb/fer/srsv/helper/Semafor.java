package hr.pb.fer.srsv.helper;

public class Semafor{

	// Enumeracija za stanja semafora, C predstavlja crveno svijetlo semafora, Ž
	// žuto i Z zeleno
	public enum Stanje {
		C, Ž, Z
	}

	// Enumeracija za tip semafora
	public enum VrstaSemafora {
		Pjesacki, Automobilski
	}

	// Enumeracija za prepoznavanje da li se semafor gasi(G) ili pali(P), ovo se
	// odnosi na prelazak u crveno(G) ili zeleno(P) stanje
	public enum VrstaPrijelaza {
		G, P
	}

	public Stanje stanje;

	public VrstaSemafora vrstaSemafora;

	public Semafor(Stanje stanje, VrstaSemafora vrstaSemafora) {
		this.stanje = stanje;
		this.vrstaSemafora = vrstaSemafora;
	}

	public Stanje getStanje() {
		return stanje;
	}

	public synchronized void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	public VrstaSemafora getVrstaSemafora() {
		return vrstaSemafora;
	}

	public void prijelazStanja(VrstaPrijelaza prijelaz) {
		switch (this.vrstaSemafora) {
		case Pjesacki:
			switch (this.stanje) {
			case C:
				setStanje(Stanje.Z);
				break;
			case Z:
				setStanje(Stanje.C);
				break;
			default:
				// Napravi throw Exception za nedopušteno stanje
				break;
			}

			break;
		case Automobilski:
			switch (this.stanje) {
			case C:
				setStanje(Stanje.Ž);
				break;
			case Ž:
				if (prijelaz.equals(VrstaPrijelaza.P)) {
					setStanje(Stanje.Z);
					break;
				} else if(prijelaz.equals(VrstaPrijelaza.G)){
					setStanje(Stanje.C);
					break;
				} else {
					break;//da baci neki exception??
				}
			case Z:
				setStanje(Stanje.Ž);
				break;
			}
			break;
		}
	}
}