package hr.pb.fer.srsv.lift;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import hr.pb.fer.srsv.lift.Lift.Kat;

public class GeneratorPutnika extends Thread {

	private int maxMil; // Maksimalan broj milisekundi između kreiranja novog putnika
	private Lift lift;

	ArrayList<Putnik> putnikPool;
	ArrayList<String> names;

	public GeneratorPutnika(int maxMil, Lift lift) {
		this.maxMil = maxMil;
		this.lift = lift;
		putnikPool = new ArrayList<Putnik>();
		names = new ArrayList<String>();
		try {
			Scanner s = new Scanner(new File("constant/names.properties"));
			while (s.hasNext()) {
				names.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			int milisec;
			Random rand = new Random();
			milisec = rand.nextInt(maxMil);

			try {
				super.sleep(milisec);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String ime;
			ime = names.get(rand.nextInt(names.size()));
			Kat polazni;
			Kat zeljeni;
			switch (rand.nextInt(4)) {
			case 0:
				polazni = Kat.Prvi;
				switch (rand.nextInt(3)) {
				case 0:
					zeljeni = Kat.Drugi;
					break;
				case 1:
					zeljeni = Kat.Treci;
					break;
				case 2:
					zeljeni = Kat.Cetvrti;
					break;
				default:
					System.out.println("Zeljeni kat za slucaj pocetnog prvog kata nije naveden");
					return;
				}
				break;
			case 1:
				polazni = Kat.Drugi;
				switch (rand.nextInt(3)) {
				case 0:
					zeljeni = Kat.Prvi;
					break;
				case 1:
					zeljeni = Kat.Treci;
					break;
				case 2:
					zeljeni = Kat.Cetvrti;
					break;
				default:
					System.out.println("Zeljeni kat za slucaj pocetnog drugog kata nije naveden");
					return;
				}
				break;
			case 2:
				polazni = Kat.Treci;
				switch (rand.nextInt(3)) {
				case 0:
					zeljeni = Kat.Prvi;
					break;
				case 1:
					zeljeni = Kat.Drugi;
					break;
				case 2:
					zeljeni = Kat.Cetvrti;
					break;
				default:
					System.out.println("Zeljeni kat za slucaj pocetnog treceg kata nije naveden");
					return;
				}
				break;
			case 3:
				polazni = Kat.Cetvrti;
				switch (rand.nextInt(3)) {
				case 0:
					zeljeni = Kat.Prvi;
					break;
				case 1:
					zeljeni = Kat.Drugi;
					break;
				case 2:
					zeljeni = Kat.Treci;
					break;
				default:
					System.out.println("Zeljeni kat za slucaj pocetnog cetvrtog kata nije naveden");
					return;
				}
				break;
			default:
				System.out.println("Pocetni i zeljeni katovi nisu navedeni");
				return;
			}

			Putnik putnik = new Putnik(ime, polazni, zeljeni, lift);
			putnikPool.add(putnik);
			putnik.start();
		}
	}

}
