package hr.pb.fer.srsv;

import hr.pb.fer.srsv.helper.UpravljackoRacunalo;
import hr.pb.fer.srsv.helper.PrikaznikRaskrizja;

public class Raskrizje {

	UpravljackoRacunalo racunalo;
	PrikaznikRaskrizja prikaznik;
	
	public Raskrizje(){
		this.racunalo = new UpravljackoRacunalo();
		this.prikaznik = new PrikaznikRaskrizja(racunalo);
		this.racunalo.setPriority(Thread.MAX_PRIORITY);
		this.prikaznik.setPriority(Thread.NORM_PRIORITY);
	}
	
	public void pokretanjeRaskrizja() {
		this.racunalo.start();
		this.prikaznik.start();
	}
	
	@SuppressWarnings("deprecation")
	public void zaustavljanjeRaskrizja() {
		this.racunalo.stop();
		this.prikaznik.stop();
	}

	public UpravljackoRacunalo getRacunalo() {
		return racunalo;
	}
	
	
}
