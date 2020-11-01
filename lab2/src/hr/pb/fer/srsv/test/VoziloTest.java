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
			Thread.sleep(6000);
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
			Thread.sleep(6000);
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
			Thread.sleep(6000);
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
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test (priority = 5)
	public void smjerZS() {
		Vozilo voziloSmjerZS = new Vozilo(Smjer.ZS, raskrizje.getRacunalo());
		voziloSmjerZS.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerZS.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test (priority = 6)
	public void smjerSI() {
		Vozilo voziloSmjerSI = new Vozilo(Smjer.SI, raskrizje.getRacunalo());
		voziloSmjerSI.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerSI.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	@Test (priority = 7)
	public void smjerIJ() {
		Vozilo voziloSmjerIJ = new Vozilo(Smjer.IJ, raskrizje.getRacunalo());
		voziloSmjerIJ.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerIJ.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test (priority = 8)
	public void smjerJZ() {
		Vozilo voziloSmjerJZ = new Vozilo(Smjer.JZ, raskrizje.getRacunalo());
		voziloSmjerJZ.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerJZ.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test (priority = 9)
	public void smjerJI() {
		Vozilo voziloSmjerJI = new Vozilo(Smjer.JI, raskrizje.getRacunalo());
		voziloSmjerJI.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerJI.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test (priority = 10)
	public void smjerSZ() {
		Vozilo voziloSmjerSZ = new Vozilo(Smjer.SZ, raskrizje.getRacunalo());
		voziloSmjerSZ.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerSZ.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test (priority = 11)
	public void smjerIS() {
		Vozilo voziloSmjerIS = new Vozilo(Smjer.IS, raskrizje.getRacunalo());
		voziloSmjerIS.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerIS.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test (priority = 12)
	public void smjerZJ() {
		Vozilo voziloSmjerZJ = new Vozilo(Smjer.ZJ, raskrizje.getRacunalo());
		voziloSmjerZJ.setPriority(Thread.MIN_PRIORITY);
		voziloSmjerZJ.start();
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
