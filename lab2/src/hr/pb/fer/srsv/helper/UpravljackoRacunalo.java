package hr.pb.fer.srsv.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import hr.pb.fer.srsv.helper.Semafor.*;

public class UpravljackoRacunalo extends Thread {

	public Semafor sa1;
	public Semafor sa2;
	public Semafor sa3;
	public Semafor sa4;

	public Semafor saJZ;
	public Semafor saJI;

	public Semafor saZS;
	public Semafor saZJ;

	public Semafor saSI;
	public Semafor saSZ;

	public Semafor saIJ;
	public Semafor saIS;

	public Semafor sp1;
	public Semafor sp2;
	public Semafor sp3;
	public Semafor sp4;

	private List<Vozilo> smjerVozSJ;
	private List<Vozilo> smjerVozZI;

	private List<Vozilo> smjerVozZIJS;
	private List<Vozilo> smjerVozSJZI;

	private List<Pjesak> smjerPjeSJ;
	private List<Pjesak> smjerPjeZI;

	Logger logger = Logger.getLogger("UpravljackoRacunalo");
	FileHandler fh;

	Date date;

	public UpravljackoRacunalo() {
		sa1 = new Semafor(Stanje.C, VrstaSemafora.Automobilski);
		sa2 = new Semafor(Stanje.C, VrstaSemafora.Automobilski);
		sa3 = new Semafor(Stanje.C, VrstaSemafora.Automobilski);
		sa4 = new Semafor(Stanje.C, VrstaSemafora.Automobilski);

		sp1 = new Semafor(Stanje.C, VrstaSemafora.Pjesacki);
		sp2 = new Semafor(Stanje.C, VrstaSemafora.Pjesacki);
		sp3 = new Semafor(Stanje.C, VrstaSemafora.Pjesacki);
		sp4 = new Semafor(Stanje.C, VrstaSemafora.Pjesacki);

		saJZ = new Semafor(Stanje.C, VrstaSemafora.Automobilski);
		saJI = new Semafor(Stanje.C, VrstaSemafora.Automobilski);

		saZS = new Semafor(Stanje.C, VrstaSemafora.Automobilski);
		saZJ = new Semafor(Stanje.C, VrstaSemafora.Automobilski);

		saSI = new Semafor(Stanje.C, VrstaSemafora.Automobilski);
		saSZ = new Semafor(Stanje.C, VrstaSemafora.Automobilski);

		saIJ = new Semafor(Stanje.C, VrstaSemafora.Automobilski);
		saIS = new Semafor(Stanje.C, VrstaSemafora.Automobilski);

		smjerVozSJ = Collections.synchronizedList(new ArrayList<Vozilo>());
		smjerVozZI = Collections.synchronizedList(new ArrayList<Vozilo>());
		smjerPjeSJ = Collections.synchronizedList(new ArrayList<Pjesak>());
		smjerPjeZI = Collections.synchronizedList(new ArrayList<Pjesak>());

		smjerVozZIJS = Collections.synchronizedList(new ArrayList<Vozilo>());
		smjerVozSJZI = Collections.synchronizedList(new ArrayList<Vozilo>());

		try {
			this.fh = new FileHandler(System.getProperty("user.dir") + "/log/UpravljackoRacunalo.log");
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

	public Semafor getSa1() {
		return sa1;
	}

	public Semafor getSa2() {
		return sa2;
	}

	public Semafor getSa3() {
		return sa3;
	}

	public Semafor getSa4() {
		return sa4;
	}

	public Semafor getSp1() {
		return sp1;
	}

	public Semafor getSp2() {
		return sp2;
	}

	public Semafor getSp3() {
		return sp3;
	}

	public Semafor getSp4() {
		return sp4;
	}

	public Semafor getSaJZ() {
		return saJZ;
	}

	public Semafor getSaJI() {
		return saJI;
	}

	public Semafor getSaZS() {
		return saZS;
	}

	public Semafor getSaZJ() {
		return saZJ;
	}

	public Semafor getSaSI() {
		return saSI;
	}

	public Semafor getSaSZ() {
		return saSZ;
	}

	public Semafor getSaIJ() {
		return saIJ;
	}

	public Semafor getSaIS() {
		return saIS;
	}

	public List<Vozilo> getSmjerVozSJ() {
		return smjerVozSJ;
	}

	public List<Vozilo> getSmjerVozZI() {
		return smjerVozZI;
	}

	public List<Pjesak> getSmjerPjeSJ() {
		return smjerPjeSJ;
	}

	public List<Pjesak> getSmjerPjeZI() {
		return smjerPjeZI;
	}

	public List<Vozilo> getSmjerVozZIJS() {
		return smjerVozZIJS;
	}

	public List<Vozilo> getSmjerVozSJZI() {
		return smjerVozSJZI;
	}

	@Override
	public void run() {
		while (true) {
			// Inicijalno stanje -> žuto stanje
			sa1.setStanje(Stanje.Ž);
			sa3.setStanje(Stanje.Ž);
			date = new Date();
			long timeStart = date.getTime();
			logger.info("UpravljackoRacunalo: pokreće se žuto stanje iz stanja crveno na semaforima SA1 i SA3");
			try {
				super.sleep((long) 1996);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info(
						"UpravljackoRacunalo: pokreće se prvo stanje prebacivanjem semafora SA1, SA3, SP2 i SP4 u stanje zeleno nakon "
								+ (date.getTime() - timeStart) + " milisekundi");
				sa1.prijelazStanja(VrstaPrijelaza.P);
				sa3.prijelazStanja(VrstaPrijelaza.P);
				sp2.prijelazStanja(VrstaPrijelaza.P);
				sp4.prijelazStanja(VrstaPrijelaza.P);

				synchronized (this) {
					super.notifyAll();
				}
			}
			date = new Date();
			long timestart = date.getTime();
			try {
				super.sleep((long) 9996);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// provjera da li se:
				// 1. u samom raskrizju ne postoji niti jedno vozilo ili pjesak
				// 2. pojavilo vozilo ili pjesak i onemogućen je prelazak raskrizja tog vozila
				// ili pjesaka
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 1. stanja u trajanju od " + (date.getTime() - timestart)
						+ " milisekundi");
				timestart = date.getTime();
				if ((smjerVozSJ.size() != 0) || (smjerPjeSJ.size() != 0)
						|| ((smjerVozZI.size() == 0) && (smjerPjeZI.size() == 0))) {
					try {
						super.sleep((long) 9998);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						date = new Date();
						logger.info("UpravljackoRacunalo: kraj 1* stanja nakon " + (date.getTime() - timestart)
								+ " milisekundi");
					}
				}
			} // Početak drugog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 2. stanja kreće od početka ciklusa nakon "
					+ (date.getTime() - timeStart) + " milisekundi, semafori SP2 i SP4 se postavljaju u stanje crveno");
			sp2.prijelazStanja(VrstaPrijelaza.G);// Crveno za pjesacki semafor sp2
			sp4.prijelazStanja(VrstaPrijelaza.G);// Crveno za pjesacki semafor sp4
			try {
				super.sleep((long) 4997);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info(
						"UpravljackoRacunalo: Drugo stanje u fazi paljenja žutog svjetla za semafore SA1 i SA3 nakon "
								+ (date.getTime() - timestart) + " milisekundi nakon početka drugog stanja");
				timestart = date.getTime();
				sa1.prijelazStanja(VrstaPrijelaza.G);// Žuto za vozilni semafor sa1
				sa3.prijelazStanja(VrstaPrijelaza.G);// Žuto za vozilni semafor sa3
			}
			try {
				super.sleep((long) 4995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sa1.prijelazStanja(VrstaPrijelaza.G);// Crveno za vozilni semafor sa1
				sa3.prijelazStanja(VrstaPrijelaza.G);// Crveno za vozilni semafor sa3
				date = new Date();
				logger.info(
						"UpravljackoRacunalo: Drugo stanje kraj, semafori SA1 i SA3 se postavljaju u stanje crveno nakon "
								+ (date.getTime() - timestart)
								+ " miliseknudi od početka drugog stanja, drugo stanje završava");
			} // Kraj drugog stanja
				// Početak 2* stanja u kojemu svi semafori imaju stanje crveno koje traje 5
				// sekundi
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 2* stanja u kojemu svi semafori su u crvenom stanju");
			try {
				super.sleep((long) 4995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 2*. stanja, 2* stanje je trajalo " + (date.getTime() - timestart)
						+ " milisekundi");
			}
			// Kraj 2* stanja
			// Slijedi 1.skup-žuto stanje za 1. skup semafora koji skreću, lijevo skreću vozila
			// koja dolaze u raskrižje sa smjera sjever-jug, a desno skreću iz svih smjerova
			// gdje postoje automobili koji žele na desno skrenuti u raskrižju
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 1.skup-žuto stanja, ono započinje paljenje");
			try {
				saJI.prijelazStanja(VrstaPrijelaza.P);
				saSZ.prijelazStanja(VrstaPrijelaza.P);
				
				saJZ.prijelazStanja(VrstaPrijelaza.P);
				saZS.prijelazStanja(VrstaPrijelaza.P);
				saSI.prijelazStanja(VrstaPrijelaza.P);
				saIJ.prijelazStanja(VrstaPrijelaza.P);
				super.sleep((long) 1997);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 1.skup-žuto stanja, 1.skup-žuto stanje je trajalo " +(date.getTime()-timestart)+ " milisekundi");
			}
			//Kraj 1.skup-žuto stanja
			//Početak 1.skup-zelenog stanja, palimo sve semafore 1. skupa u stanje zeleno kako bi se promet na tim semaforima mogao odvijati
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 1.skup-zeleno stanja, svi semafori prvog skupa prelaze u stanje zeleno");
			try {
				saJI.prijelazStanja(VrstaPrijelaza.P);
				saSZ.prijelazStanja(VrstaPrijelaza.P);
				
				saJZ.prijelazStanja(VrstaPrijelaza.P);
				saZS.prijelazStanja(VrstaPrijelaza.P);
				saSI.prijelazStanja(VrstaPrijelaza.P);
				saIJ.prijelazStanja(VrstaPrijelaza.P);
				super.sleep((long) 9996);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 1.skup-zeleno stanja, 1.skup-zeleno stanje je trajalo " +(date.getTime()-timestart)+ " milisekundi");
			}
			//Kraj 1.skup-zeleno stanja
			//Početak 1.skup-gašenje stanje
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 1.skup-gašenje stanja, svi semafori prvog skupa prelaze u stanje žuto");
			try {
				saJI.prijelazStanja(VrstaPrijelaza.G);
				saSZ.prijelazStanja(VrstaPrijelaza.G);
				
				saJZ.prijelazStanja(VrstaPrijelaza.G);
				saZS.prijelazStanja(VrstaPrijelaza.G);
				saSI.prijelazStanja(VrstaPrijelaza.G);
				saIJ.prijelazStanja(VrstaPrijelaza.G);
				super.sleep((long) 4996);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				saJI.prijelazStanja(VrstaPrijelaza.G);
				saSZ.prijelazStanja(VrstaPrijelaza.G);
				
				saJZ.prijelazStanja(VrstaPrijelaza.G);
				saZS.prijelazStanja(VrstaPrijelaza.G);
				saSI.prijelazStanja(VrstaPrijelaza.G);
				saIJ.prijelazStanja(VrstaPrijelaza.G);
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 1.skup-gašenje stanja, svi semafori su trenutno u crevnom stanju, 1.skup-gašenje stanje je trajalo " +(date.getTime()-timestart)+ " milisekundi");
			}		
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 3. stanja " + (date.getTime() - timeStart)
					+ "miliseknudi nakon početka ciklusa");
			// Početak trećeg stanja
			// Treće stanje: svi semafori su u stanju crveno, ne bi smjelo biti više vozila
			// i pješaka unutar semafora
			try {
				super.sleep((long) 4998);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 3. stanja, treće stanje je trajalo "
						+ (date.getTime() - timestart) + " milisekundi");
			}
			// kraj trećeg stanja
			// Početak četvrtog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak žutog stanja pred 4. stanje " + (date.getTime() - timeStart)
					+ " milisekundi nakon početka ciklusa");
			sa2.prijelazStanja(VrstaPrijelaza.P);
			sa4.prijelazStanja(VrstaPrijelaza.P);
			try {
				super.sleep((long) 1997);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sa2.prijelazStanja(VrstaPrijelaza.P);
				sa4.prijelazStanja(VrstaPrijelaza.P);
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj žutog stanja nakon " + (date.getTime() - timestart)
						+ " milisekundi");
				sp1.prijelazStanja(VrstaPrijelaza.P);
				sp3.prijelazStanja(VrstaPrijelaza.P);
				date = new Date();
				timestart = date.getTime();
				synchronized (this) {
					super.notifyAll();
				}
			}
			logger.info("UpravljackoRacunalo: Početak 4. stanja " + (timestart - timeStart)
					+ " milisekundi nakon početka ciklusa");
			try {
				super.sleep((long) 9992);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 4. stanja u trajanju od " + (date.getTime() - timestart) + "");
				timestart = date.getTime();
				// provjera da li se:
				// 1. u samom raskrizju ne postoji niti jedno vozilo ili pjesak
				// 2. pojavilo vozilo ili pjesak i onemogućen je prelazak raskrizja tog vozila
				// ili pjesaka
				if ((smjerVozZI.size() != 0) || (smjerPjeZI.size() != 0)
						|| ((smjerVozSJ.size() == 0) && (smjerPjeSJ.size() == 0))) {
					try {
						super.sleep((long) 9995);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					date = new Date();
					logger.info("UpravljackoRacunalo: Kraj 4* stanja u trajanju od " + (date.getTime() - timestart)
							+ " milisekundi");

				}
			} // Početak petog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 5. stanja " + (timestart - timeStart)
					+ " milisekundi od početka ciklusa");
			sp1.prijelazStanja(VrstaPrijelaza.G);// Crveno za pjesacki semafor sp1
			sp3.prijelazStanja(VrstaPrijelaza.G);// Crveno za pjesacki semafor sp3
			try {
				super.sleep((long) 4995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sa2.prijelazStanja(VrstaPrijelaza.G);// Žuto za vozilni semafor sa2
				sa4.prijelazStanja(VrstaPrijelaza.G);// Žuto za vozilni semafor sa4
				date = new Date();
				logger.info("UpraljackoRacunalo: Gašenje žutog svjetla na semaforima SA2 i SA4 "
						+ (date.getTime() - timestart) + " milisekundi od početka 5. stanja");
			}
			try {
				super.sleep((long) 9996);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sa2.prijelazStanja(VrstaPrijelaza.G);// Crveno za vozilni semafor sa2
				sa4.prijelazStanja(VrstaPrijelaza.G);// Crveno za vozilni semafor sa4
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 5. stanja u trajanju od " + (date.getTime() - timestart)
						+ " milisekundi");
			} // Kraj petog stanja
			//Početak 5* stanja u kojemu su svi semafori u stanju crvenom 5 sekundi
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 5* stanja " + (timestart - timeStart)
					+ " milisekundi od početka ciklusa");
			try {
				super.sleep((long) 4995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 5* stanja u trajanju od " + (date.getTime() - timestart)
						+ " milisekundi");
			}//Kraj 5* stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 2.skup-žuto stanja " + (timestart - timeStart)
					+ " milisekundi od početka ciklusa");
			try {
				saZJ.prijelazStanja(VrstaPrijelaza.P);
				saIS.prijelazStanja(VrstaPrijelaza.P);
				
				saJZ.prijelazStanja(VrstaPrijelaza.P);
				saZS.prijelazStanja(VrstaPrijelaza.P);
				saSI.prijelazStanja(VrstaPrijelaza.P);
				saIJ.prijelazStanja(VrstaPrijelaza.P);
				super.sleep((long) 1997);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 2.skup-žuto stanja u trajanju od " + (date.getTime() - timestart)
						+ " milisekundi");
			}//Kraj 2.skup-žuto stanja
			//Početak 2.skup zelenog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 2.skup-zeleno stanja " + (timestart - timeStart)
					+ " milisekundi od početka ciklusa");
			try {
				saZJ.prijelazStanja(VrstaPrijelaza.P);
				saIS.prijelazStanja(VrstaPrijelaza.P);
				
				saJZ.prijelazStanja(VrstaPrijelaza.P);
				saZS.prijelazStanja(VrstaPrijelaza.P);
				saSI.prijelazStanja(VrstaPrijelaza.P);
				saIJ.prijelazStanja(VrstaPrijelaza.P);
				super.sleep((long) 9995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 2.skup-zeleno stanja u trajanju od " + (date.getTime() - timestart)
						+ " milisekundi");
			}
			//Kraj 2.skup-zelenog stanja i početak gašenja 2.skup semafora prijelazom u žuto stanje
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 2.skup-žuto-gašenje stanja " + (timestart - timeStart)
					+ " milisekundi od početka ciklusa");
			try {
				saZJ.prijelazStanja(VrstaPrijelaza.G);
				saIS.prijelazStanja(VrstaPrijelaza.G);
				
				saJZ.prijelazStanja(VrstaPrijelaza.G);
				saZS.prijelazStanja(VrstaPrijelaza.G);
				saSI.prijelazStanja(VrstaPrijelaza.G);
				saIJ.prijelazStanja(VrstaPrijelaza.G);
				super.sleep((long) 4997);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 2.skup-žuto-gašenje stanja u trajanju od " + (date.getTime() - timestart)
						+ " milisekundi");
			}
			logger.info("UpravljackoRacunalo: Početak gašenja semafora 2. skupa, semafori 2. skupa prijelaze u stanje crveno " + (timestart-timeStart) +" milisekundi od početka ciklusa");
			saZJ.prijelazStanja(VrstaPrijelaza.G);
			saIS.prijelazStanja(VrstaPrijelaza.G);
			
			saJZ.prijelazStanja(VrstaPrijelaza.G);
			saZS.prijelazStanja(VrstaPrijelaza.G);
			saSI.prijelazStanja(VrstaPrijelaza.G);
			saIJ.prijelazStanja(VrstaPrijelaza.G);
			logger.info("UpravljackoRacunalo: Semafori drugog ciklusa su prešli u stanje crveno");
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 6. stanja " + (timestart - timeStart)
					+ " milisekundi od početka ciklusa");
			// Početak sestog stanja
			try {
				super.sleep((long) 4995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 6. stanja u trajanju od " + (date.getTime() - timestart)
						+ " milisekundi");
				logger.info("UpravljackoRacunalo: Kraj ciklusa u trajanju od " + (date.getTime() - timeStart)
						+ " milisekundi");
			} // Kraj sestog stanja i povratak na prvo stanje
		}
	}
}