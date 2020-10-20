package hr.pb.fer.srsv.test;

import java.util.concurrent.CyclicBarrier;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import hr.pb.fer.srsv.Raskrizje;
import hr.pb.fer.srsv.helper.Vozilo;
import hr.pb.fer.srsv.helper.Vozilo.Smjer;

public class VoziloTest {
	
	Raskrizje raskrizje;
	CyclicBarrier gate;
	
	@Test (priority = 1)
	public void smjerI() {
		Vozilo voziloSmjerI = new Vozilo(Smjer.I, raskrizje.getRacunalo());
		voziloSmjerI.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerI.start();
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test (priority = 2)
	public void smjerJ() {
		Vozilo voziloSmjerJ = new Vozilo(Smjer.J, raskrizje.getRacunalo());
		voziloSmjerJ.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerJ.start();
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test (priority = 3)
	public void smjerZ() {
		Vozilo voziloSmjerZ = new Vozilo(Smjer.Z, raskrizje.getRacunalo());
		voziloSmjerZ.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerZ.start();
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test (priority = 4)
	public void smjerS() {
		Vozilo voziloSmjerS = new Vozilo(Smjer.S, raskrizje.getRacunalo());
		voziloSmjerS.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerS.start();
		try {
			Thread.sleep(40000);
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
