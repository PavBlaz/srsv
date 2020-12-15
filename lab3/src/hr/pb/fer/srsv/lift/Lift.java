package hr.pb.fer.srsv.lift;

import java.util.ArrayList;

public class Lift extends Thread{
	
	public enum Vrata{
		zatvoreno, otvoreno
	}
	
	public enum Smjer{
		gore, dole
	}
	
	public enum Kat{
		Prvi, PoluPrviDrugi, Drugi, PoluDrugiTreci, Treci, PoluTreciCetvrti, Cetvrti
	}
	
	private ArrayList<Putnik> dizalo;
	
	private ArrayList<Putnik> kat1Ulaz;
	private ArrayList<Putnik> kat1Izlaz;
	private ArrayList<Putnik> kat2Ulaz;
	private ArrayList<Putnik> kat2Izlaz;
	private ArrayList<Putnik> kat3Ulaz;
	private ArrayList<Putnik> kat3Izlaz;
	private ArrayList<Putnik> kat4Ulaz;
	private ArrayList<Putnik> kat4Izlaz;
	
	private ArrayList<Putnik> izasli;
	
	private Vrata vrata;
	private Smjer smjer;
	private Kat kat;
	
	public Lift() {
		dizalo = new ArrayList<Putnik>(8);
		kat1Ulaz = new ArrayList<Putnik>(10);
		kat1Izlaz = new ArrayList<Putnik>();
		kat2Ulaz = new ArrayList<Putnik>(10);
		kat2Izlaz = new ArrayList<Putnik>();
		kat3Ulaz = new ArrayList<Putnik>(10);
		kat3Izlaz = new ArrayList<Putnik>();
		kat3Ulaz = new ArrayList<Putnik>(10);
		kat4Izlaz = new ArrayList<Putnik>();
		vrata = Vrata.otvoreno;
		smjer = Smjer.gore;
		kat = Kat.Prvi;
		izasli = new ArrayList<Putnik>();
	}
	
	public ArrayList<Putnik> getDizalo() {
		return dizalo;
	}

	public ArrayList<Putnik> getKat1Ulaz() {
		return kat1Ulaz;
	}

	public ArrayList<Putnik> getKat1Izlaz() {
		return kat1Izlaz;
	}

	public ArrayList<Putnik> getKat2Ulaz() {
		return kat2Ulaz;
	}

	public ArrayList<Putnik> getKat2Izlaz() {
		return kat2Izlaz;
	}

	public ArrayList<Putnik> getKat3Ulaz() {
		return kat3Ulaz;
	}

	public ArrayList<Putnik> getKat3Izlaz() {
		return kat3Izlaz;
	}

	public ArrayList<Putnik> getKat4Ulaz() {
		return kat4Ulaz;
	}

	public ArrayList<Putnik> getKat4Izlaz() {
		return kat4Izlaz;
	}

	public Vrata getVrata() {
		return vrata;
	}

	public Smjer getSmjer() {
		return smjer;
	}

	public Kat getKat() {
		return kat;
	}

	public ArrayList<Putnik> getIzasli(){
		return izasli;
	}

	@Override
	public void run() {
		
	}
	
}
