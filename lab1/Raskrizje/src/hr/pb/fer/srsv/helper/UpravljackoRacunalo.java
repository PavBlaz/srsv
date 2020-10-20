package hr.pb.fer.srsv.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import hr.pb.fer.srsv.helper.Semafor.*;

public class UpravljackoRacunalo extends Thread {

	public Semafor sa1;
	public Semafor sa2;
	public Semafor sa3;
	public Semafor sa4;

	public Semafor sp1;
	public Semafor sp2;
	public Semafor sp3;
	public Semafor sp4;

	private ArrayList<Vozilo> smjerVozSJ;
	private ArrayList<Vozilo> smjerVozZI;

	private ArrayList<Pjesak> smjerPjeSJ;
	private ArrayList<Pjesak> smjerPjeZI;

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

		smjerVozSJ = new ArrayList<Vozilo>();
		smjerVozZI = new ArrayList<Vozilo>();
		smjerPjeSJ = new ArrayList<Pjesak>();
		smjerPjeZI = new ArrayList<Pjesak>();
		
		try {
			this.fh = new FileHandler(System.getProperty("user.dir")+"/log/UpravljackoRacunalo.log");
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

	public ArrayList<Vozilo> getSmjerVozSJ() {
		return smjerVozSJ;
	}

	public ArrayList<Vozilo> getSmjerVozZI() {
		return smjerVozZI;
	}

	public ArrayList<Pjesak> getSmjerPjeSJ() {
		return smjerPjeSJ;
	}

	public ArrayList<Pjesak> getSmjerPjeZI() {
		return smjerPjeZI;
	}

	@Override
	public void run() {
		while (true) {
			date = new Date();
			long timeStart = date.getTime();
			// Inicijalno stanje -> žuto stanje
			sa1.setStanje(Stanje.Ž);
			sa3.setStanje(Stanje.Ž);
			logger.info("UpravljakoRacunalo: pokreće se žuto stanje iz stanja crveno na semaforima SA1 i SA3");
			try {
				super.sleep((long) 1963);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: pokreće se prvo stanje prebacivanjem semafora SA1, SA3, SP2 i SP4 u stanje zeleno nakon "+(date.getTime()-timeStart)+ " milisekundi");
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
				super.sleep((long) 9998);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// provjera da li se:
				// 1. u samom raskrizju ne postoji niti jedno vozilo ili pjesak
				// 2. pojavilo vozilo ili pjesak i onemogućen je prelazak raskrizja tog vozila
				// ili pjesaka
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 1. stanja u trajanju od " +(date.getTime()-timestart)+" milisekundi");
				timestart = date.getTime();
				if ((smjerVozSJ.size() != 0) || (smjerPjeSJ.size() != 0)
						|| ((smjerVozZI.size() == 0) && (smjerPjeZI.size() == 0))) {
					try {
						super.sleep((long) 10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						date = new Date();
						logger.info("UpravljackoRacunalo: kraj 1* stanja nakon " +(date.getTime()-timestart)+ " milisekundi");
					}
				}
			} // Početak drugog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 2. stanja kreće od početka ciklusa nakon " +(date.getTime()-timeStart)+ " milisekundi, semafori SP2 i SP4 se postavljaju u stanje crveno");
			sp2.prijelazStanja(VrstaPrijelaza.G);// Crveno za pjesacki semafor sp2
			sp4.prijelazStanja(VrstaPrijelaza.G);// Crveno za pjesacki semafor sp4
			try {
				super.sleep((long) 4999);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Drugo stanje u fazi paljenja žutog svjetla za semafore SA1 i SA3 nakon " +(date.getTime()-timestart)+ " milisekundi nakon početka drugog stanja");
				timestart = date.getTime();
				sa1.prijelazStanja(VrstaPrijelaza.G);// Žuto za vozilni semafor sa1
				sa3.prijelazStanja(VrstaPrijelaza.G);// Žuto za vozilni semafor sa3
			}
			try {
				super.sleep((long) 4996);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sa1.prijelazStanja(VrstaPrijelaza.G);// Crveno za vozilni semafor sa1
				sa3.prijelazStanja(VrstaPrijelaza.G);// Crveno za vozilni semafor sa3
				date = new Date();
				logger.info("UpravljackoRacunalo: Drugo stanje kraj, semafori SA1 i SA3 se postavljaju u stanje crveno nakon " +(date.getTime()-timestart)+ " miliseknudi od početka drugog stanja, drugo stanje završava");
			} // Kraj drugog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 3. stanja " +(date.getTime()-timeStart)+ "miliseknudi nakon početka ciklusa");
			// Početak trećeg stanja
			// Treće stanje: svi semafori su u stanju crveno, ne bi smjelo biti više vozila
			// i pješaka unutar semafora
			try {
				super.sleep((long) 4996);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 3. stanja, treće stanje je trajalo " +(date.getTime()-timestart)+ " milisekundi");
			}
			// kraj trećeg stanja
			// Početak četvrtog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak žutog stanja pred 4. stanje " +(date.getTime()-timeStart)+ " milisekundi nakon početka ciklusa");
			sa2.prijelazStanja(VrstaPrijelaza.P);
			sa4.prijelazStanja(VrstaPrijelaza.P);
			try {
				super.sleep((long) 1995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sa2.prijelazStanja(VrstaPrijelaza.P);
				sa4.prijelazStanja(VrstaPrijelaza.P);
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj žutog stanja nakon " +(date.getTime()-timestart)+ " milisekundi");
				sp1.prijelazStanja(VrstaPrijelaza.P);
				sp3.prijelazStanja(VrstaPrijelaza.P);
				date = new Date();
				timestart = date.getTime();
				synchronized (this) {
					super.notifyAll();
				}
			}
			logger.info("UpravljackoRacunalo: Početak 4. stanja " +(timestart-timeStart)+ " milisekundi nakon početka ciklusa");
			try {
				super.sleep((long) 9995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 4. stanja u trajanju od " +(date.getTime()-timestart)+ "");
				timestart = date.getTime();
				// provjera da li se:
				// 1. u samom raskrizju ne postoji niti jedno vozilo ili pjesak
				// 2. pojavilo vozilo ili pjesak i onemogućen je prelazak raskrizja tog vozila
				// ili pjesaka
				if ((smjerVozZI.size() != 0) || (smjerPjeZI.size() != 0)
						|| ((smjerVozSJ.size() == 0) && (smjerPjeSJ.size() == 0))) {
					try {
						super.sleep((long) 9998);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					date = new Date();
					logger.info("UpravljackoRacunalo: Kraj 4* stanja u trajanju od " +(date.getTime()-timestart)+ " milisekundi");
					
				}
			} // Početak petog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 5. stanja " +(timestart-timeStart)+ " milisekundi od početka ciklusa");
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
				logger.info("UpraljackoRacunalo: Gašenje žutog svjetla na semaforima SA2 i SA4 " +(date.getTime()-timestart)+ " milisekundi od početka 5. stanja");
			}
			try {
				super.sleep((long) 9995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sa2.prijelazStanja(VrstaPrijelaza.G);// Crveno za vozilni semafor sa2
				sa4.prijelazStanja(VrstaPrijelaza.G);// Crveno za vozilni semafor sa4
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 5. stanja u trajanju od " +(date.getTime()-timestart)+ " milisekundi");
			} // Kraj petog stanja
			date = new Date();
			timestart = date.getTime();
			logger.info("UpravljackoRacunalo: Početak 6. stanja " +(timestart-timeStart)+ " milisekundi od početka ciklusa");
				// Početak sestog stanja
			try {
				super.sleep((long) 4995);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				date = new Date();
				logger.info("UpravljackoRacunalo: Kraj 6. stanja u trajanju od " +(date.getTime()-timestart)+ " milisekundi");
				logger.info("UpravljackoRacunalo: Kraj ciklusa u trajanju od " +(date.getTime()-timeStart)+ " milisekundi");;
			} // Kraj sestog stanja i povratak na prvo stanje
		}
	}
}