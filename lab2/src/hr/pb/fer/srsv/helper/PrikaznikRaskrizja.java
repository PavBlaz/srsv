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
	int s, i, j, z = 0;

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
			prikaz = new StringBuffer("\n              (sjever)\n" + "\n" + "              |   |  |\n"
					+ "              |   |  | \n" + "              |   |  |  \n" + "           ---+/|\\|  +---\n"
					+ "                     \\   \n" + "                     -   \n" + "(zapad)    ---       /   \n"
					+ "              /       ---    (istok)\n" + "              -           \n"
					+ "              \\          \n" + "           ---+  |\\|/+---\n" + "              |  |   |  \n"
					+ "              |  |   | \n" + "              |  |   |\n" + "           \n"
					+ "               (jug)");

			synchronized (racunalo.getSmjerPjeSJ()) {
				Iterator<Pjesak> pjesakSJ = racunalo.getSmjerPjeSJ().iterator();
				while (pjesakSJ.hasNext()) {
					Pjesak p = pjesakSJ.next();
					if (p != null) {
						switch (p.getPozicija()) {
						case SZ:
							if (p.getSmjer().equals(Smjer.J)) {
								if (racunalo.getSp4().getStanje().equals(Stanje.Z) || p.getEntered()) {
									prikaz.setCharAt(135, 'p');
									prikaz.setCharAt(161, 'p');
									prikaz.setCharAt(213, 'p');
									prikaz.setCharAt(250, 'p');
									prikaz.setCharAt(277, 'p');
								} else {
									prikaz.setCharAt(83, 'p');
								}
							} else {
								// pogrešan smjer za poziciju SZ, ili je pjesak naveden u pogrešnoj listi
							}
							break;
						case SI:
							if (p.getSmjer().equals(Smjer.J)) {
								if (racunalo.getSp2().getStanje().equals(Stanje.Z) || p.getEntered()) {
									prikaz.setCharAt(144, 'p');
									prikaz.setCharAt(170, 'p');
									prikaz.setCharAt(196, 'p');
									prikaz.setCharAt(259, 'p');
									prikaz.setCharAt(286, 'p');
								} else {
									prikaz.setCharAt(94, 'p');
								}
							} else {
								// pogrešan smjer za poziciju SI, ili je pjesak naveden u pogrešnoj listi
							}
							break;
						case JZ:
							if (p.getSmjer().equals(Smjer.S)) {
								if (racunalo.getSp4().getStanje().equals(Stanje.Z) || p.getEntered()) {
									prikaz.setCharAt(135, 'p');
									prikaz.setCharAt(161, 'p');
									prikaz.setCharAt(213, 'p');
									prikaz.setCharAt(250, 'p');
									prikaz.setCharAt(277, 'p');
								} else {
									prikaz.setCharAt(328, 'p');
								}
							} else {
								// pogrešan smjer za poziciju JZ, ili je pjesak naveden u pogrešnoj listi
							}
							break;
						case JI:
							if (p.getSmjer().equals(Smjer.S)) {
								if (racunalo.getSp2().getStanje().equals(Stanje.Z) || p.getEntered()) {
									prikaz.setCharAt(144, 'p');
									prikaz.setCharAt(170, 'p');
									prikaz.setCharAt(196, 'p');
									prikaz.setCharAt(259, 'p');
									prikaz.setCharAt(286, 'p');
								} else {
									prikaz.setCharAt(339, 'p');
								}
							} else {
								// pogrešan smjer za poziciju JI, ili je pjesak naveden u pogrešnoj listi
							}
							break;
						}
					}
				}
			}
			synchronized (racunalo.getSmjerPjeZI()) {
				Iterator<Pjesak> pjesakZI = racunalo.getSmjerPjeZI().iterator();
				while (pjesakZI.hasNext()) {
					Pjesak p = pjesakZI.next();
					if (p != null) {
						switch (p.getPozicija()) {
						case SZ:
							if (p.getSmjer().equals(Smjer.I)) {
								if (racunalo.getSp1().getStanje().equals(Stanje.Z) || p.getEntered()) {
									prikaz.setCharAt(86, 'p');
									prikaz.setCharAt(87, 'p');
									prikaz.setCharAt(88, 'p');
									prikaz.setCharAt(90, 'p');
									prikaz.setCharAt(91, 'p');
								} else {
									prikaz.setCharAt(60, 'p');
								}
							} else {
								// pogrešan smjer za poziciju SZ, ili je pjesak naveden u pogrešnoj listi
							}
							break;
						case SI:
							if (p.getSmjer().equals(Smjer.Z)) {
								if (racunalo.getSp1().getStanje().equals(Stanje.Z) || p.getEntered()) {
									prikaz.setCharAt(86, 'p');
									prikaz.setCharAt(87, 'p');
									prikaz.setCharAt(88, 'p');
									prikaz.setCharAt(90, 'p');
									prikaz.setCharAt(91, 'p');
								} else {
									prikaz.setCharAt(69, 'p');
								}
							} else {
								// pogrešan smjer za poziciju SI, ili je pjesak naveden u pogrešnoj listi
							}
							break;
						case JZ:
							if (p.getSmjer().equals(Smjer.I)) {
								if (racunalo.getSp3().getStanje().equals(Stanje.Z) || p.getEntered()) {
									prikaz.setCharAt(331, 'p');
									prikaz.setCharAt(332, 'p');
									prikaz.setCharAt(334, 'p');
									prikaz.setCharAt(335, 'p');
									prikaz.setCharAt(336, 'p');
								} else {
									prikaz.setCharAt(354, 'p');
								}
							} else {
								// pogrešan smjer za poziciju JZ, ili je pjesak naveden u pogrešnoj listi
							}
							break;
						case JI:
							if (p.getSmjer().equals(Smjer.Z)) {
								if (racunalo.getSp3().getStanje().equals(Stanje.Z) || p.getEntered()) {
									prikaz.setCharAt(331, 'p');
									prikaz.setCharAt(332, 'p');
									prikaz.setCharAt(334, 'p');
									prikaz.setCharAt(335, 'p');
									prikaz.setCharAt(336, 'p');
								} else {
									prikaz.setCharAt(363, 'p');
								}
							} else {
								// pogrešan smjer za poziciju JI, ili je pjesak naveden u pogrešnoj listi
							}
							break;
						}
					}
				}
			}
			synchronized (racunalo.getSmjerVozSJ()) {
				Iterator<Vozilo> voziloSJ = racunalo.getSmjerVozSJ().iterator();
				while (voziloSJ.hasNext()) {
					Vozilo v = voziloSJ.next();
					if (v != null) {
						switch (v.getSmjer()) {
						case J:
							if (racunalo.getSa1().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(40, 'R');
								prikaz.setCharAt(63, 'R');
								prikaz.setCharAt(87, 'R');
								prikaz.setCharAt(112, 'R');
								prikaz.setCharAt(138, 'R');
								prikaz.setCharAt(164, 'R');
								prikaz.setCharAt(190, 'R');
								prikaz.setCharAt(216, 'R');
								prikaz.setCharAt(253, 'R');
								prikaz.setCharAt(280, 'R');
								prikaz.setCharAt(306, 'R');
								prikaz.setCharAt(332, 'R');
								prikaz.setCharAt(357, 'R');
								prikaz.setCharAt(381, 'R');
							} else {
								prikaz.setCharAt(40, 'R');
							}
							break;
						case S:
							if (racunalo.getSa3().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(43, 'R');
								prikaz.setCharAt(66, 'R');
								prikaz.setCharAt(90, 'R');
								prikaz.setCharAt(115, 'R');
								prikaz.setCharAt(141, 'R');
								prikaz.setCharAt(167, 'R');
								prikaz.setCharAt(193, 'R');
								prikaz.setCharAt(219, 'R');
								prikaz.setCharAt(256, 'R');
								prikaz.setCharAt(283, 'R');
								prikaz.setCharAt(309, 'R');
								prikaz.setCharAt(335, 'R');
								prikaz.setCharAt(360, 'R');
								prikaz.setCharAt(384, 'R');
							} else {
								prikaz.setCharAt(384, 'R');
							}
							break;
						default:
							// pogrešan smjer vozila u listi vozila Sjever i Jug
						}
					}
				}
			}
			synchronized (racunalo.getSmjerVozZI()) {
				Iterator<Vozilo> voziloZI = racunalo.getSmjerVozZI().iterator();
				while (voziloZI.hasNext()) {
					Vozilo v = voziloZI.next();
					if (v != null) {
						switch (v.getSmjer()) {
						case Z:
							if (racunalo.getSa2().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(159, 'R');
								prikaz.setCharAt(160, 'R');
								prikaz.setCharAt(161, 'R');
								prikaz.setCharAt(162, 'R');
								prikaz.setCharAt(163, 'R');
								prikaz.setCharAt(164, 'R');
								prikaz.setCharAt(165, 'R');
								prikaz.setCharAt(166, 'R');
								prikaz.setCharAt(167, 'R');
								prikaz.setCharAt(168, 'R');
								prikaz.setCharAt(169, 'R');
								prikaz.setCharAt(170, 'R');
								prikaz.setCharAt(171, 'R');
								prikaz.setCharAt(172, 'R');
							} else {
								prikaz.setCharAt(172, 'R');
							}
							break;
						case I:
							if (racunalo.getSa4().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(248, 'R');
								prikaz.setCharAt(249, 'R');
								prikaz.setCharAt(250, 'R');
								prikaz.setCharAt(251, 'R');
								prikaz.setCharAt(252, 'R');
								prikaz.setCharAt(253, 'R');
								prikaz.setCharAt(254, 'R');
								prikaz.setCharAt(255, 'R');
								prikaz.setCharAt(256, 'R');
								prikaz.setCharAt(257, 'R');
								prikaz.setCharAt(258, 'R');
								prikaz.setCharAt(259, 'R');
								prikaz.setCharAt(260, 'R');
								prikaz.setCharAt(261, 'R');
							} else {
								prikaz.setCharAt(248, 'R');
							}
							break;
						default:
							// pogrešan smjer vozila u listi vozila Sjever i Jug
						}
					}
				}
			}
			synchronized (racunalo.getSmjerVozSJZI()) {
				Iterator<Vozilo> skretnicaSJ = racunalo.getSmjerVozSJZI().iterator();
				while (skretnicaSJ.hasNext()) {
					Vozilo v = skretnicaSJ.next();
					if (v != null) {
						switch (v.getSmjer()) {
						case JI:
							if (racunalo.getSaJI().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(41, 'L');
								prikaz.setCharAt(64, 'L');
								prikaz.setCharAt(88, 'L');
								prikaz.setCharAt(113, 'L');
								prikaz.setCharAt(139, 'L');
								prikaz.setCharAt(166, 'L');
								prikaz.setCharAt(193, 'L');
								prikaz.setCharAt(220, 'L');
								prikaz.setCharAt(258, 'L');
								prikaz.setCharAt(259, 'L');
								prikaz.setCharAt(260, 'L');
								prikaz.setCharAt(261, 'L');
							} else {
								prikaz.setCharAt(41, 'L');
							}
							break;
						case SZ:
							if (racunalo.getSaSZ().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(159, 'L');
								prikaz.setCharAt(160, 'L');
								prikaz.setCharAt(161, 'L');
								prikaz.setCharAt(162, 'L');
								prikaz.setCharAt(189, 'L');
								prikaz.setCharAt(216, 'L');
								prikaz.setCharAt(254, 'L');
								prikaz.setCharAt(282, 'L');
								prikaz.setCharAt(308, 'L');
								prikaz.setCharAt(334, 'L');
								prikaz.setCharAt(359, 'L');
								prikaz.setCharAt(383, 'L');
							} else {
								prikaz.setCharAt(383, 'L');
							}
							break;
						case JZ:
							if (racunalo.getSaJZ().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(39, 'D');
								prikaz.setCharAt(62, 'D');
								prikaz.setCharAt(86, 'D');
								prikaz.setCharAt(111, 'D');
								prikaz.setCharAt(136, 'D');
								prikaz.setCharAt(135, 'D');
								prikaz.setCharAt(134, 'D');
								prikaz.setCharAt(133, 'D');
							} else {
								prikaz.setCharAt(39, 'D');
							}
							break;
						case ZS:
							if (racunalo.getSaZS().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(146, 'D');
								prikaz.setCharAt(145, 'D');
								prikaz.setCharAt(144, 'D');
								prikaz.setCharAt(143, 'D');
								prikaz.setCharAt(116, 'D');
								prikaz.setCharAt(91, 'D');
								prikaz.setCharAt(67, 'D');
								prikaz.setCharAt(44, 'D');
							} else {
								prikaz.setCharAt(146, 'D');
							}
							break;
						case SI:
							if (racunalo.getSaSI().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(385, 'D');
								prikaz.setCharAt(361, 'D');
								prikaz.setCharAt(336, 'D');
								prikaz.setCharAt(310, 'D');
								prikaz.setCharAt(285, 'D');
								prikaz.setCharAt(286, 'D');
								prikaz.setCharAt(287, 'D');
								prikaz.setCharAt(288, 'D');
							} else {
								prikaz.setCharAt(385, 'D');
							}
							break;
						case IJ:
							if (racunalo.getSaIJ().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(275, 'D');
								prikaz.setCharAt(276, 'D');
								prikaz.setCharAt(277, 'D');
								prikaz.setCharAt(278, 'D');
								prikaz.setCharAt(305, 'D');
								prikaz.setCharAt(331, 'D');
								prikaz.setCharAt(356, 'D');
								prikaz.setCharAt(380, 'D');
							} else {
								prikaz.setCharAt(275, 'D');
							}
							break;
						default:
							break;
						}
					}
				}
			}
			synchronized (racunalo.getSmjerVozZIJS()) {
				Iterator<Vozilo> skretnicaZI = racunalo.getSmjerVozZIJS().iterator();
				while (skretnicaZI.hasNext()) {
					Vozilo v = skretnicaZI.next();
					if (v != null) {
						switch (v.getSmjer()) {
						case ZJ:
							if (racunalo.getSaZJ().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(198, 'L');
								prikaz.setCharAt(197, 'L');
								prikaz.setCharAt(196, 'L');
								prikaz.setCharAt(195, 'L');
								prikaz.setCharAt(194, 'L');
								prikaz.setCharAt(219, 'L');
								prikaz.setCharAt(255, 'L');
								prikaz.setCharAt(281, 'L');
								prikaz.setCharAt(306, 'L');
								prikaz.setCharAt(332, 'L');
								prikaz.setCharAt(357, 'L');
								prikaz.setCharAt(381, 'L');
							} else {
								prikaz.setCharAt(198, 'L');
							}
							break;
						case IS:
							if (racunalo.getSaIS().getStanje().equals(Stanje.Z) || v.getEntered()) {
								prikaz.setCharAt(211, 'L');
								prikaz.setCharAt(212, 'L');
								prikaz.setCharAt(213, 'L');
								prikaz.setCharAt(214, 'L');
								prikaz.setCharAt(215, 'L');
								prikaz.setCharAt(190, 'L');
								prikaz.setCharAt(165, 'L');
								prikaz.setCharAt(140, 'L');
								prikaz.setCharAt(115, 'L');
								prikaz.setCharAt(90, 'L');
								prikaz.setCharAt(66, 'L');
								prikaz.setCharAt(43, 'L');
							} else {
								prikaz.setCharAt(211, 'L');
							}
							break;
						default:
							break;
						}
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
