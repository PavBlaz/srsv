package hr.pb.fer.srsv.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import hr.pb.fer.srsv.Raskrizje;
import hr.pb.fer.srsv.helper.Pjesak;
import hr.pb.fer.srsv.helper.Pjesak.Pozicija;
import hr.pb.fer.srsv.helper.Vozilo;
import hr.pb.fer.srsv.helper.Vozilo.Smjer;

public class KompleksniTest {

	Raskrizje raskrizje;
	

	@Test
	public void punoRaskrizje() {
		Vozilo voziloSmjerI = new Vozilo(Smjer.I, raskrizje.getRacunalo());
		voziloSmjerI.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerJ = new Vozilo(Smjer.J, raskrizje.getRacunalo());
		voziloSmjerJ.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerZ = new Vozilo(Smjer.Z, raskrizje.getRacunalo());
		voziloSmjerZ.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerS = new Vozilo(Smjer.S, raskrizje.getRacunalo());
		voziloSmjerS.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerZS = new Vozilo(Smjer.ZS, raskrizje.getRacunalo());
		voziloSmjerZS.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerJZ = new Vozilo(Smjer.JZ, raskrizje.getRacunalo());
		voziloSmjerJZ.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerIJ = new Vozilo(Smjer.IJ, raskrizje.getRacunalo());
		voziloSmjerIJ.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerSI = new Vozilo(Smjer.SI, raskrizje.getRacunalo());
		voziloSmjerSI.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerJI = new Vozilo(Smjer.JI, raskrizje.getRacunalo());
		voziloSmjerJI.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerSZ = new Vozilo(Smjer.SZ, raskrizje.getRacunalo());
		voziloSmjerSZ.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerIS = new Vozilo(Smjer.IS, raskrizje.getRacunalo());
		voziloSmjerIS.setPriority(Thread.MIN_PRIORITY);
		Vozilo voziloSmjerZJ = new Vozilo(Smjer.ZJ, raskrizje.getRacunalo());
		voziloSmjerZJ.setPriority(Thread.MIN_PRIORITY);
		Pjesak pjesakSZsmjerI = new Pjesak(Smjer.I, Pozicija.SZ, raskrizje.getRacunalo());
		pjesakSZsmjerI.setPriority(Thread.MIN_PRIORITY);
		Pjesak pjesakSZsmjerJ = new Pjesak(Smjer.J, Pozicija.SZ, raskrizje.getRacunalo());
		pjesakSZsmjerJ.setPriority(Thread.MIN_PRIORITY);
		Pjesak pjesakSIsmjerZ = new Pjesak(Smjer.Z, Pozicija.SI, raskrizje.getRacunalo());
		pjesakSIsmjerZ.setPriority(Thread.MIN_PRIORITY);
		Pjesak pjesakSIsmjerJ = new Pjesak(Smjer.J, Pozicija.SI, raskrizje.getRacunalo());
		pjesakSIsmjerJ.setPriority(Thread.MIN_PRIORITY);
		Pjesak pjesakJZsmjerI = new Pjesak(Smjer.I, Pozicija.JZ, raskrizje.getRacunalo());
		pjesakJZsmjerI.setPriority(Thread.MIN_PRIORITY);
		Pjesak pjesakJZsmjerS = new Pjesak(Smjer.S, Pozicija.JZ, raskrizje.getRacunalo());
		pjesakJZsmjerS.setPriority(Thread.MIN_PRIORITY);
		Pjesak pjesakJIsmjerZ = new Pjesak(Smjer.Z, Pozicija.JI, raskrizje.getRacunalo());
		pjesakJIsmjerZ.setPriority(Thread.MIN_PRIORITY);
		Pjesak pjesakJIsmjerS = new Pjesak(Smjer.S, Pozicija.JI, raskrizje.getRacunalo());
		pjesakJIsmjerS.setPriority(Thread.MIN_PRIORITY);
		try {
			Thread.sleep(18000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		voziloSmjerI.start();
		voziloSmjerJ.start();
		voziloSmjerZ.start();
		voziloSmjerS.start();
		voziloSmjerZS.start();
		voziloSmjerJZ.start();
		voziloSmjerIJ.start();
		voziloSmjerSI.start();
		voziloSmjerJI.start();
		voziloSmjerSZ.start();
		voziloSmjerIS.start();
		voziloSmjerZJ.start();
		pjesakSZsmjerI.start();
		pjesakSZsmjerJ.start();
		pjesakSIsmjerZ.start();
		pjesakSIsmjerJ.start();
		pjesakJZsmjerI.start();
		pjesakJZsmjerS.start();
		pjesakJIsmjerZ.start();
		pjesakJIsmjerS.start();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void beforeTest() {
		raskrizje = new Raskrizje();
		raskrizje.pokretanjeRaskrizja();
	}

	@AfterTest
	public void afterTest() {
		raskrizje.zaustavljanjeRaskrizja();
	}
}
