package hr.pb.fer.srsv.lift;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import hr.pb.fer.srsv.lift.Lift.Kat;
import hr.pb.fer.srsv.lift.Lift.Smjer;
import hr.pb.fer.srsv.lift.Lift.Vrata;

public class Putnik extends Thread{
	
	final private String ime;
	
	final private char initial;
	
	final private Kat polazni;
	
	final private Kat zeljeni;
	
	private Lift lift;
	
	private UUID id;
	
	FileHandler fh;
	Logger logger = Logger.getLogger("Putnik");
	
	private Smjer smjer;
	
	private boolean flag;
	
	
	public Putnik(String ime, Kat polazni, Kat zeljeni, Lift lift) {
		this.ime = ime;
		this.polazni = polazni;
		this.zeljeni = zeljeni;
		this.lift = lift;
		this.initial = ime.charAt(0);
		
		id = UUID.randomUUID();
		
		try {
			this.fh = new FileHandler(System.getProperty("user.dir") + "/log/Putnik"+ id.toString().substring(0, 10) +".log");
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		logger.info("Logger za putnika/cu: " +ime+ ", sa id-jem: " +id.toString()+ " je inicijaliziran");
		
		switch(polazni) {
			case Prvi:
				smjer = Smjer.gore;
				break;
			case Drugi:
				if(zeljeni.equals(Kat.Prvi))
					smjer = Smjer.dole;
				else
					smjer = Smjer.gore;
				break;
			case Treci:
				if(zeljeni.equals(Kat.Cetvrti))
					smjer = Smjer.gore;
				else
					smjer = Smjer.dole;
				break;
			case Cetvrti:
				smjer = Smjer.dole;
				break;
			default:
				smjer = Smjer.neodreden;
				break;
		}
		
		flag = false;
	}

	public String getIme() {
		return ime;
	}

	public Kat getPolazni() {
		return polazni;
	}

	public Kat getZeljeni() {
		return zeljeni;
	}
	
	public char getInitial() {
		return initial;
	}
	
	public Smjer getSmjer() {
		return smjer;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag() {
		flag = true;
		return;
	}
	
	@Override
	public void run() {
		//Upis putnika na ulazne liste sa pojedinog kata
		logger.info("Putnik/ca " +ime+" dolazi do lifta na " +polazni.name()+ " kat i namjerom na " +zeljeni.name()+ " kat");
		switch(polazni) {
			case Prvi:
				synchronized(this){
					lift.getKat1Ulaz().add(this);
				}
				break;
			case Drugi:
				synchronized(this){
					lift.getKat2Ulaz().add(this);
				}
				break;
			case Treci:
				synchronized(this){
					lift.getKat3Ulaz().add(this);
				}
				break;
			case Cetvrti:
				synchronized(this){
					lift.getKat4Ulaz().add(this);
				}
				break;
			default:
				
				break;
		}
		//Čekanje dok ne dođe lift na polazni kat, dok se vrata ne otvore, i dok ne postoji slobodno mjesto u liftu
		while(!(lift.getKat().equals(polazni) && lift.getVrata().equals(Vrata.otvoreno) && (lift.getDizalo().size()< 8) && (lift.getSmjer().equals(smjer)))) {
			try {
				super.sleep(1234);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Ulaz u lift
		logger.info("Putnik/ca " +ime+ " ulazi u lift");
		while(true) {
			synchronized(this) {
				if(lift.getKat().equals(polazni) && lift.getVrata().equals(Vrata.otvoreno) && (lift.getDizalo().size()< 8) && (lift.getSmjer().equals(smjer))) {
					lift.getDizalo().add(this);
					break;
				} else {
					try {
						super.sleep(617);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		switch(polazni) {
			case Prvi:
				synchronized(this){
					lift.getKat1Ulaz().remove(this);
				}
				break;
			case Drugi:
				synchronized(this){
					lift.getKat2Ulaz().remove(this);
				}
				break;
			case Treci:
				synchronized(this){
					lift.getKat3Ulaz().remove(this);
				}
				break;
			case Cetvrti:
				synchronized(this){
					lift.getKat4Ulaz().remove(this);
				}
				break;
			default:
				
				break;
		}
		//Čekanje u liftu na izlazak iz lifta na željenom katu, i na otvorenim vratima lifta
		while(!(lift.getKat().equals(zeljeni) && lift.getVrata().equals(Vrata.otvoreno))) {
			try {
				super.sleep(617);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("Putnik/ca " +ime+ " izlazi iz lifta na " +zeljeni+ " kat");
		synchronized(this) {
			lift.getDizalo().remove(this);
		}
		//Upis na listu za izlazak iz lifta i vraćanje iz run metode
		switch(zeljeni) {
			case Prvi:
				synchronized(this) {
					lift.getKat1Izlaz().add(this);
				}
				break;
			case Drugi:
				synchronized(this) {
					lift.getKat2Izlaz().add(this);
				}
				break;
			case Treci:
				synchronized(this) {
					lift.getKat3Izlaz().add(this);
				}
				break;
			case Cetvrti:
				synchronized(this) {
					lift.getKat4Izlaz().add(this);
				}
				break;
			default:
				
				break;	
		}
		synchronized(this) {
			lift.getIzasli().add(this);
		}
		//Gasi se dretva za ovog putnika
		logger.info("Zavrsava se dretva za putnika/cu " +ime);
		return;
	}
}
