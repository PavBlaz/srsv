package hr.pb.fer.srsv.helper;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import hr.pb.fer.srsv.helper.Semafor.Stanje;

public class Vozilo extends Thread {

	public enum Smjer {
		S, I, J, Z, JZ, JI, ZS, ZJ, SI, SZ, IJ, IS
	};

	final private UUID idVozila;

	final private Smjer smjer;

	private UpravljackoRacunalo racunalo;

	Logger logger = Logger.getLogger("Vozilo");
	FileHandler fh;

	Date date;

	boolean flag = false;

	boolean entered = false;

	public Vozilo(Smjer smjer, UpravljackoRacunalo racunalo) {
		this.idVozila = UUID.randomUUID();
		this.smjer = smjer;
		this.racunalo = racunalo;

		try {
			this.fh = new FileHandler(System.getProperty("user.dir") + "/log/Vozilo.log");
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

	public UUID getBrojVozila() {
		return idVozila;
	}

	public Smjer getSmjer() {
		return smjer;
	}

	@SuppressWarnings("finally")
	@Override
	public void run() {
		switch (smjer) {
		case S:
		case J:
			logger.info("Vozilo: " + idVozila.toString()
					+ " je došlo do raskrižja, te upravljačko računalo evidentira dolazak vozila za pravac sjever-jug");
			synchronized (this) {
				racunalo.getSmjerVozSJ().add(this);
			}
			break;
		case Z:
		case I:
			logger.info("Vozilo: " + idVozila.toString()
					+ " je došlo do raskrižja, te upravljačko računalo evidentira dolazak vozila za pravac zapad-istok");
			synchronized (this) {
				racunalo.getSmjerVozZI().add(this);
			}
			break;
		case JZ:
		case IJ:
		case ZS:
		case SI:
			logger.info("Vozilo: " + idVozila.toString()
					+ "je došlo do raskrižja, te upravljačko računalo evidentira dolazak vozila za pravac jug-sjever prema zapad-istok i zapad-istok prema jug-sjever");
			synchronized(this) {
				racunalo.getSmjerVozSJZI().add(this);
			}
			synchronized(this) {
				racunalo.getSmjerVozZIJS().add(this);
			}
			break;
		case ZJ:
		case IS:
			logger.info("Vozilo: " + idVozila.toString()
			+ "je došlo do raskrižja, te upravljačko računalo evidentira dolazak vozila za pravac zapad-istok prema jug-sjever");
			synchronized(this) {
				racunalo.getSmjerVozZIJS().add(this);
			}
			break;
		case JI:
		case SZ:
			logger.info("Vozilo: " + idVozila.toString()
			+ "je došlo do raskrižja, te upravljačko računalo evidentira dolazak vozila za pravac jug-sjever prema zapad-istok");
			synchronized(this) {
				racunalo.getSmjerVozSJZI().add(this);
			}
			break;
		}
		while (true) {
			date = new Date();
			long timestart = date.getTime();
			switch (smjer) {
			case S:
				if (racunalo.getSa3().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SA3 prema raskrizju u smjeru sjever");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozSJ().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case J:
				if (racunalo.getSa1().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SA1 prema raskrizju u smjeru juga");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozSJ().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case Z:
				if (racunalo.getSa2().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SA2 prema raskrizju u smjeru zapada");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozZI().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case I:
				if (racunalo.getSa4().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SA4 prema raskrizju u smjeru istoka");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozZI().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case JZ:
				if (racunalo.getSaJZ().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SAJZ prema raskrizju u smjeru juga i skrenulo prema zapadu");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozSJZI().remove(this);
						racunalo.getSmjerVozZIJS().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case JI:
				if (racunalo.getSaJZ().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SAJZ prema raskrizju u smjeru juga i skrenulo prema istoku");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozSJZI().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case ZS:
				if (racunalo.getSaZS().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SAZS prema raskrizju u smjeru zapada i skrenulo prema sjeveru");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozZIJS().remove(this);
						racunalo.getSmjerVozSJZI().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case ZJ:
				if (racunalo.getSaZJ().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SAZJ prema raskrizju u smjeru zapada i skrenulo prema jugu");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozZIJS().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case SI:
				if (racunalo.getSaSI().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SASI prema raskrizju u smjeru sjevera i skrenulo prema istoku");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozSJZI().remove(this);
						racunalo.getSmjerVozZIJS().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case SZ:
				if (racunalo.getSaSZ().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SASZ prema raskrizju u smjeru sjevera i skrenulo prema zapadu");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozSJZI().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case IJ:
				if (racunalo.getSaIJ().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SA4 prema raskrizju u smjeru istoka i skrenulo prema jugu");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozZIJS().remove(this);
						racunalo.getSmjerVozSJZI().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
						return;
					}
				} else {
					break;
				}
			case IS:
				if (racunalo.getSaIS().getStanje().equals(Stanje.Z)) {
					logger.info("Vozilo: " + idVozila.toString()
							+ " je krenulo sa semafora SAIS prema raskrizju u smjeru istoka i skrenulo prema sjeveru");
					date = new Date();
					long timeStart = date.getTime();
					this.entered = true;
					try {
						super.sleep((long) 1996);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						racunalo.getSmjerVozZIJS().remove(this);

						date = new Date();
						logger.info("Vozilo: " + idVozila.toString() + " je prošlo raskrižje u vremenu od "
								+ (date.getTime() - timeStart) + " miliseconds");
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
					logger.info("Vozilo: " + idVozila.toString() + " čekalo je za slijedeću provjeru semafora "
							+ (date.getTime() - timestart) + " milisekundi");
				}
			}
		}
	}
}