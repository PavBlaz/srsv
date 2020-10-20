package hr.pb.fer.srsv;

import hr.pb.fer.srsv.helper.Pjesak;
import hr.pb.fer.srsv.helper.Pjesak.Pozicija;
import hr.pb.fer.srsv.helper.PrikaznikRaskrizja;
import hr.pb.fer.srsv.helper.UpravljackoRacunalo;
import hr.pb.fer.srsv.helper.Vozilo;
import hr.pb.fer.srsv.helper.Vozilo.Smjer;

public class RaskrizjeMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UpravljackoRacunalo racunalo = new UpravljackoRacunalo();
		PrikaznikRaskrizja prikaznik = new PrikaznikRaskrizja(racunalo);
				
		racunalo.setPriority(Thread.MAX_PRIORITY);
		prikaznik.setPriority(Thread.NORM_PRIORITY);
		
		racunalo.start();
		prikaznik.start();
		
		//Vozilo voziloSmjerI = new Vozilo(Smjer.I, racunalo);
		//Vozilo voziloSmjerJ = new Vozilo(Smjer.J, racunalo);
		//Vozilo voziloSmjerZ = new Vozilo(Smjer.Z, racunalo);
		//Vozilo voziloSmjerS = new Vozilo(Smjer.S, racunalo);
		//Pjesak pjesakSZsmjerI = new Pjesak(Smjer.I, Pozicija.SZ, racunalo);
		//Pjesak pjesakSZsmjerJ = new Pjesak(Smjer.J, Pozicija.SZ, racunalo);
		//Pjesak pjesakSIsmjerZ = new Pjesak(Smjer.Z, Pozicija.SI, racunalo);
		//Pjesak pjesakSIsmjerJ = new Pjesak(Smjer.J, Pozicija.SI, racunalo);
		//Pjesak pjesakJZsmjerI = new Pjesak(Smjer.I, Pozicija.JZ, racunalo);
		//Pjesak pjesakJZsmjerS = new Pjesak(Smjer.S, Pozicija.JZ, racunalo);
		//Pjesak pjesakJIsmjerZ = new Pjesak(Smjer.Z, Pozicija.JI, racunalo);
		//Pjesak pjesakJIsmjerS = new Pjesak(Smjer.S, Pozicija.JI, racunalo);
		//voziloSmjerI.setPriority(Thread.MIN_PRIORITY);
		//voziloSmjerJ.setPriority(Thread.MIN_PRIORITY);
		//voziloSmjerZ.setPriority(Thread.MIN_PRIORITY);
		//voziloSmjerS.setPriority(Thread.MIN_PRIORITY);
		//pjesakSZsmjerI.setPriority(Thread.MIN_PRIORITY);		
		//pjesakSZsmjerJ.setPriority(Thread.MIN_PRIORITY);
		//pjesakSIsmjerZ.setPriority(Thread.MIN_PRIORITY);
		//pjesakSIsmjerJ.setPriority(Thread.MIN_PRIORITY);
		//pjesakJZsmjerI.setPriority(Thread.MIN_PRIORITY);
		//pjesakJZsmjerS.setPriority(Thread.MIN_PRIORITY);
		//pjesakJIsmjerZ.setPriority(Thread.MIN_PRIORITY);		
		//pjesakJIsmjerS.setPriority(Thread.MIN_PRIORITY);
		/*try {
			Thread.sleep(18000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//voziloSmjerI.start();
		//voziloSmjerJ.start();
		//voziloSmjerZ.start();
		//voziloSmjerS.start();
		//pjesakSZsmjerI.start();
		//pjesakSZsmjerJ.start();
		//pjesakSIsmjerZ.start();
		//pjesakSIsmjerJ.start();
		//pjesakJZsmjerI.start();
		//pjesakJZsmjerS.start();
		//pjesakJIsmjerZ.start();
		//pjesakJIsmjerS.start();
		try {
			Thread.sleep(65000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Thread.sleep(120*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("First test ended");
			prikaznik.stop();
			racunalo.stop();
		}
	}

	
}
