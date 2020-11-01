package hr.pb.fer.srsv.helper;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import hr.pb.fer.srsv.helper.Semafor.Stanje;
import hr.pb.fer.srsv.helper.Vozilo.Smjer;

public class Pjesak extends Thread {

	public enum Pozicija {
		SZ, SI, JZ, JI
	};

	final private UUID idPjesaka;

	final private Smjer smjer;

	final private Pozicija pozicija;

	private UpravljackoRacunalo racunalo;

	Logger logger = Logger.getLogger("Pjesak");
	FileHandler fh;

	Date date;

	boolean flag = false;

	boolean entered = false;

	public Pjesak(Smjer smjer, Pozicija pozicija, UpravljackoRacunalo racunalo) {

		this.idPjesaka = UUID.randomUUID();
		this.smjer = smjer;
		this.pozicija = pozicija;
		this.racunalo = racunalo;
		try {
			this.fh = new FileHandler(System.getProperty("user.dir") + "/log/Pjesak.log");
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

	public boolean getEntered() {
		return entered;
	}

	public UUID getBrojPjesaka() {
		return idPjesaka;
	}

	public Smjer getSmjer() {
		return smjer;
	}

	public Pozicija getPozicija() {
		return pozicija;
	}

	public String toString() {

		return "Vrijeme: " + String.format("%02d", idPjesaka);
	}

	@SuppressWarnings("finally")
	@Override
	public void run() {
		switch (smjer) {
		case S:
		case J:
			synchronized (this) {
				racunalo.getSmjerPjeSJ().add(this);
			}
			break;
		case Z:
		case I:
			synchronized (this) {
				racunalo.getSmjerPjeZI().add(this);
			}
			break;
		default:
			logger.info("Pjesak: "+ idPjesaka.toString() + " ima naveden nedozvoljen smjer");
			return;
		}
		while (true) {
			date = new Date();
			long timestart = date.getTime();
			switch (this.pozicija) {
			case SZ:
				if (smjer.equals(Smjer.I) && racunalo.getSp1().getStanje().equals(Stanje.Z)) {
					// prelazak
					logger.info("Pjesak: " + idPjesaka.toString()
							+ " je krenuo sa semafora SP1 prema raskrizju u smjeru istoka");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 6997);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerPjeZI().remove(this);
						date = new Date();
						logger.info("Pjesak: " + idPjesaka.toString() + " je prošao kroz raskrizje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseknudi");
						return;
					}
				} else if (smjer.equals(Smjer.J) && racunalo.getSp4().getStanje().equals(Stanje.Z)) {
					// prelazak
					logger.info("Pjesak: " + idPjesaka.toString()
							+ " je krenuo sa semafora SP4 prema raskrizju u smjeru juga");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 6997);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerPjeSJ().remove(this);
						date = new Date();
						logger.info("Pjesak: " + idPjesaka.toString() + " je prošao kroz raskrizje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseknudi");
						return;
					}
				} else {
					break;
				}
			case SI:
				if (smjer.equals(Smjer.Z) && racunalo.getSp1().getStanje().equals(Stanje.Z)) {
					// prelazak
					logger.info("Pjesak: " + idPjesaka.toString()
							+ " je krenuo sa semafora SP1 prema raskrizju u smjeru zapada");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 6997);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerPjeZI().remove(this);
						date = new Date();
						logger.info("Pjesak: " + idPjesaka.toString() + " je prošao kroz raskrizje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseknudi");
						return;
					}
				} else if (smjer.equals(Smjer.J) && racunalo.getSp2().getStanje().equals(Stanje.Z)) {
					// prelazak
					logger.info("Pjesak: " + idPjesaka.toString()
							+ " je krenuo sa semafora SP2 prema raskrizju u smjeru juga");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 6997);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerPjeSJ().remove(this);
						date = new Date();
						logger.info("Pjesak: " + idPjesaka.toString() + " je prošao kroz raskrizje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseknudi");
						return;
					}
				} else {
					break;
				}
			case JZ:
				if (smjer.equals(Smjer.I) && racunalo.getSp3().getStanje().equals(Stanje.Z)) {
					// prelazak
					logger.info("Pjesak: " + idPjesaka.toString()
							+ " je krenuo sa semafora SP3 prema raskrizju u smjeru istoka");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 6997);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerPjeZI().remove(this);
						date = new Date();
						logger.info("Pjesak: " + idPjesaka.toString() + " je prošao kroz raskrizje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseknudi");
						return;
					}
				} else if (smjer.equals(Smjer.S) && racunalo.getSp4().getStanje().equals(Stanje.Z)) {
					// prelazak
					logger.info("Pjesak: " + idPjesaka.toString()
							+ " je krenuo sa semafora SP4 prema raskrizju u smjeru sjevera");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 6997);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerPjeSJ().remove(this);
						date = new Date();
						logger.info("Pjesak: " + idPjesaka.toString() + " je prošao kroz raskrizje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseknudi");
						return;
					}
				} else {
					break;
				}
			case JI:
				if (smjer.equals(Smjer.Z) && racunalo.getSp3().getStanje().equals(Stanje.Z)) {
					// prelazak
					logger.info("Pjesak: " + idPjesaka.toString()
							+ " je krenuo sa semafora SP3 prema raskrizju u smjeru zapada");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 6997);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerPjeZI().remove(this);

						date = new Date();
						logger.info("Pjesak: " + idPjesaka.toString() + " je prošao kroz raskrizje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseknudi");
						return;
					}
				} else if (smjer.equals(Smjer.S) && racunalo.getSp2().getStanje().equals(Stanje.Z)) {
					// prelazak
					logger.info("Pjesak: " + idPjesaka.toString()
							+ " je krenuo sa semafora SP4 prema raskrizju u smjeru sjevera");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 6997);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerPjeSJ().remove(this);
						date = new Date();
						logger.info("Pjesak: " + idPjesaka.toString() + " je prošao kroz raskrizje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseknudi");
						return;
					}
				} else {
					break;
				}
			}
			try {
				synchronized (this) {
					super.wait((long) 1498);
				}
				flag = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				if (flag) {
					flag = false;
					date = new Date();
					logger.info("Pjesak: " + idPjesaka.toString() + " čekao je za slijedeću provjeru semafora "
							+ (date.getTime() - timestart) + " milisekundi");
				}
			}
		}
	}
}