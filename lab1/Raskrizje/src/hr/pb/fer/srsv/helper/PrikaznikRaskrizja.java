package hr.pb.fer.srsv.helper;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import hr.pb.fer.srsv.helper.Semafor.Stanje;
import hr.pb.fer.srsv.helper.Vozilo.Smjer;

public class PrikaznikRaskrizja extends Thread {

	private UpravljackoRacunalo racunalo;

	private StringBuffer prikaz;

	Logger logger = Logger.getLogger("Prikaznik");
	FileHandler fh;

	public PrikaznikRaskrizja(UpravljackoRacunalo racunalo) {
		this.racunalo = racunalo;

		try {
			this.fh = new FileHandler(System.getProperty("user.dir") + "/log/Prikaznik.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			prikaz = new StringBuffer(
					  "              (sjever)\n" 
					+ "\n" 
					+ "              | | |\n"
					+ "              | | | \n" 
					+ "              | | |  \n" 
					+ "           ---+ | +---\n"
					+ "                      \n" 
					+ "(zapad)    ---     ---    (istok)\n" 
					+ "                      \n"
					+ "           ---+ | +---\n" 
					+ "              | | |  \n" 
					+ "              | | | \n"
					+ "              | | |\n" 
					+ "           \n" 
					+ "              (jug)\n");

			Iterator<Pjesak> pjesakSJ = racunalo.getSmjerPjeSJ().iterator();
			while (pjesakSJ.hasNext()) {
				Pjesak p = pjesakSJ.next();
				if (p != null) {
					switch (p.getPozicija()) {
					case SZ:
						if (p.getSmjer().equals(Smjer.J)) {
							if (racunalo.getSp4().getStanje().equals(Stanje.Z) || p.getEntered()) {
								prikaz.setCharAt(123, 'p');
								prikaz.setCharAt(180, 'p');
							} else {
								prikaz.setCharAt(77, 'p');
							}
						} else {
							// pogrešan smjer za poziciju SZ, ili je pjesak naveden u pogrešnoj listi
						}
						break;
					case SI:
						if (p.getSmjer().equals(Smjer.J)) {
							if (racunalo.getSp2().getStanje().equals(Stanje.Z) || p.getEntered()) {
								prikaz.setCharAt(129, 'p');
								prikaz.setCharAt(186, 'p');
							} else {
								prikaz.setCharAt(85, 'p');
							}
						} else {
							// pogrešan smjer za poziciju SI, ili je pjesak naveden u pogrešnoj listi
						}
						break;
					case JZ:
						if (p.getSmjer().equals(Smjer.S)) {
							if (racunalo.getSp4().getStanje().equals(Stanje.Z) || p.getEntered()) {
								prikaz.setCharAt(123, 'p');
								prikaz.setCharAt(180, 'p');
							} else {
								prikaz.setCharAt(225, 'p');
							}
						} else {
							// pogrešan smjer za poziciju JZ, ili je pjesak naveden u pogrešnoj listi
						}
						break;
					case JI:
						if (p.getSmjer().equals(Smjer.S)) {
							if (racunalo.getSp2().getStanje().equals(Stanje.Z) || p.getEntered()) {
								prikaz.setCharAt(129, 'p');
								prikaz.setCharAt(186, 'p');
							} else {
								prikaz.setCharAt(233, 'p');
							}
						} else {
							// pogrešan smjer za poziciju JI, ili je pjesak naveden u pogrešnoj listi
						}
						break;
					}
				}
			}
			Iterator<Pjesak> pjesakZI = racunalo.getSmjerPjeZI().iterator();
			while (pjesakZI.hasNext()) {
				Pjesak p = pjesakZI.next();
				if (p != null) {
					switch (p.getPozicija()) {
					case SZ:
						if (p.getSmjer().equals(Smjer.I)) {
							if (racunalo.getSp1().getStanje().equals(Stanje.Z) || p.getEntered()) {
								prikaz.setCharAt(80, 'p');
								prikaz.setCharAt(82, 'p');
							} else {
								prikaz.setCharAt(57, 'p');
							}
						} else {
							// pogrešan smjer za poziciju SZ, ili je pjesak naveden u pogrešnoj listi
						}
						break;
					case SI:
						if (p.getSmjer().equals(Smjer.Z)) {
							if (racunalo.getSp1().getStanje().equals(Stanje.Z) || p.getEntered()) {
								prikaz.setCharAt(80, 'p');
								prikaz.setCharAt(82, 'p');
							} else {
								prikaz.setCharAt(63, 'p');
							}
						} else {
							// pogrešan smjer za poziciju SI, ili je pjesak naveden u pogrešnoj listi
						}
						break;
					case JZ:
						if (p.getSmjer().equals(Smjer.I)) {
							if (racunalo.getSp3().getStanje().equals(Stanje.Z) || p.getEntered()) {
								prikaz.setCharAt(228, 'p');
								prikaz.setCharAt(230, 'p');
							} else {
								prikaz.setCharAt(248, 'p');
							}
						} else {
							// pogrešan smjer za poziciju JZ, ili je pjesak naveden u pogrešnoj listi
						}
						break;
					case JI:
						if (p.getSmjer().equals(Smjer.Z)) {
							if (racunalo.getSp3().getStanje().equals(Stanje.Z) || p.getEntered()) {
								prikaz.setCharAt(228, 'p');
								prikaz.setCharAt(230, 'p');
							} else {
								prikaz.setCharAt(254, 'p');
							}
						} else {
							// pogrešan smjer za poziciju JI, ili je pjesak naveden u pogrešnoj listi
						}
						break;
					}
				}
			}
			Iterator<Vozilo> voziloSJ = racunalo.getSmjerVozSJ().iterator();
			while (voziloSJ.hasNext()) {
				Vozilo v = voziloSJ.next();
				if (v != null) {
					switch (v.getSmjer()) {
					case J:
						if (racunalo.getSa1().getStanje().equals(Stanje.Z) || v.getEntered()) {
							prikaz.setCharAt(39, 'A');
							prikaz.setCharAt(59, 'A');
							prikaz.setCharAt(80, 'A');
							prikaz.setCharAt(102, 'A');
							prikaz.setCharAt(125, 'A');
							prikaz.setCharAt(148, 'A');
							prikaz.setCharAt(182, 'A');
							prikaz.setCharAt(205, 'A');
							prikaz.setCharAt(228, 'A');
							prikaz.setCharAt(250, 'A');
							prikaz.setCharAt(271, 'A');
						} else {
							prikaz.setCharAt(39, 'A');
						}
						break;
					case S:
						if (racunalo.getSa3().getStanje().equals(Stanje.Z) || v.getEntered()) {
							prikaz.setCharAt(41, 'A');
							prikaz.setCharAt(61, 'A');
							prikaz.setCharAt(82, 'A');
							prikaz.setCharAt(104, 'A');
							prikaz.setCharAt(127, 'A');
							prikaz.setCharAt(150, 'A');
							prikaz.setCharAt(184, 'A');
							prikaz.setCharAt(207, 'A');
							prikaz.setCharAt(230, 'A');
							prikaz.setCharAt(252, 'A');
							prikaz.setCharAt(273, 'A');
						} else {
							prikaz.setCharAt(273, 'A');
						}
						break;
					default:
						// pogrešan smjer vozila u listi vozila Sjever i Jug
					}
				}
			}
			Iterator<Vozilo> voziloZI = racunalo.getSmjerVozZI().iterator();
			while (voziloZI.hasNext()) {
				Vozilo v = voziloZI.next();
				if (v != null) {
					switch (v.getSmjer()) {
					case Z:
						if (racunalo.getSa2().getStanje().equals(Stanje.Z) || v.getEntered()) {
							prikaz.setCharAt(121, 'A');
							prikaz.setCharAt(122, 'A');
							prikaz.setCharAt(123, 'A');
							prikaz.setCharAt(124, 'A');
							prikaz.setCharAt(125, 'A');
							prikaz.setCharAt(126, 'A');
							prikaz.setCharAt(127, 'A');
							prikaz.setCharAt(128, 'A');
							prikaz.setCharAt(129, 'A');
							prikaz.setCharAt(130, 'A');
							prikaz.setCharAt(131, 'A');
						} else {
							prikaz.setCharAt(131, 'A');
						}
						break;
					case I:
						if (racunalo.getSa4().getStanje().equals(Stanje.Z) || v.getEntered()) {
							prikaz.setCharAt(178, 'A');
							prikaz.setCharAt(179, 'A');
							prikaz.setCharAt(180, 'A');
							prikaz.setCharAt(181, 'A');
							prikaz.setCharAt(182, 'A');
							prikaz.setCharAt(183, 'A');
							prikaz.setCharAt(184, 'A');
							prikaz.setCharAt(185, 'A');
							prikaz.setCharAt(186, 'A');
							prikaz.setCharAt(187, 'A');
							prikaz.setCharAt(188, 'A');
						} else {
							prikaz.setCharAt(178, 'A');
						}
						break;
					default:
						// pogrešan smjer vozila u listi vozila Sjever i Jug
					}
				}
			}
			System.out.println(prikaz);
			logger.info(prikaz.toString());
			try {
				synchronized (this) {
					super.wait((long) 1940);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// logger za prolazno vrijeme ciklusa
			}
		}
	}
}
