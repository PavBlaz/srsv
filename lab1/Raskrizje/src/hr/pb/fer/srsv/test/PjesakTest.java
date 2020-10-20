package hr.pb.fer.srsv.test;

import java.util.concurrent.CyclicBarrier;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import hr.pb.fer.srsv.Raskrizje;
import hr.pb.fer.srsv.helper.Pjesak;
import hr.pb.fer.srsv.helper.Pjesak.Pozicija;
import hr.pb.fer.srsv.helper.Vozilo.Smjer;

public class PjesakTest {

	Raskrizje raskrizje;
	CyclicBarrier gate;
	
	@Test
	public void SZsmjerI() {
		Pjesak pjesakSZsmjerI = new Pjesak(Smjer.I, Pozicija.SZ, raskrizje.getRacunalo());
		pjesakSZsmjerI.setPriority(Thread.MIN_PRIORITY);
		pjesakSZsmjerI.start();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void SZsmjerJ() {
		Pjesak pjesakSZsmjerJ = new Pjesak(Smjer.J, Pozicija.SZ, raskrizje.getRacunalo());
		pjesakSZsmjerJ.setPriority(Thread.MIN_PRIORITY);
		pjesakSZsmjerJ.start();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void SIsmjerZ() {
		Pjesak pjesakSIsmjerZ = new Pjesak(Smjer.Z, Pozicija.SI, raskrizje.getRacunalo());
		pjesakSIsmjerZ.setPriority(Thread.MIN_PRIORITY);
		pjesakSIsmjerZ.start();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void SIsmjerJ() {
		Pjesak pjesakSIsmjerJ = new Pjesak(Smjer.J, Pozicija.SI, raskrizje.getRacunalo());
		pjesakSIsmjerJ.setPriority(Thread.MIN_PRIORITY);
		pjesakSIsmjerJ.start();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void JZsmjerI() {
		Pjesak pjesakJZsmjerI = new Pjesak(Smjer.I, Pozicija.JZ, raskrizje.getRacunalo());
		pjesakJZsmjerI.setPriority(Thread.MIN_PRIORITY);
		pjesakJZsmjerI.start();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void JZsmjerS() {
		Pjesak pjesakJZsmjerS = new Pjesak(Smjer.S, Pozicija.JZ, raskrizje.getRacunalo());
		pjesakJZsmjerS.setPriority(Thread.MIN_PRIORITY);
		pjesakJZsmjerS.start();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void JIsmjerZ() {
		Pjesak pjesakJIsmjerZ = new Pjesak(Smjer.Z, Pozicija.JI, raskrizje.getRacunalo());
		pjesakJIsmjerZ.setPriority(Thread.MIN_PRIORITY);
		pjesakJIsmjerZ.start();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void JIsmjerS() {
		Pjesak pjesakJIsmjerS = new Pjesak(Smjer.S, Pozicija.JI, raskrizje.getRacunalo());
		pjesakJIsmjerS.setPriority(Thread.MIN_PRIORITY);
		pjesakJIsmjerS.start();
		try {
			Thread.sleep(50000);
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
