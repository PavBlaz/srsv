package hr.pb.fer.srsv.lift;

import java.util.ArrayList;

public class Lift extends Thread {

	public enum Vrata {
		zatvoreno, otvoreno
	}

	public enum Smjer {
		gore, dole, neodreden
	}

	public enum Kat {
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
	
	private boolean stop;
	

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
		kat4Ulaz = new ArrayList<Putnik>(10);
		vrata = Vrata.zatvoreno;
		smjer = Smjer.gore;
		kat = Kat.Prvi;
		izasli = new ArrayList<Putnik>();
		stop = false;
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

	public ArrayList<Putnik> getIzasli() {
		return izasli;
	}

	public void transition(boolean nextStop) {
		while (true) {
			switch (kat) {
			case Prvi:
				if (smjer.equals(Smjer.gore)) {
					try {
						super.sleep(3000);
						kat = Kat.PoluPrviDrugi;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case PoluPrviDrugi:
				try {
					super.sleep(3000);
					if (nextStop) {
						super.sleep(500);
					}
					if (smjer.equals(Smjer.gore))
						kat = Kat.Drugi;
					else if (smjer.equals(Smjer.dole))
						kat = Kat.Prvi;
					return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case Drugi:
				try {
					super.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (smjer.equals(Smjer.gore)) {
					kat = Kat.PoluDrugiTreci;
				} else {
					kat = Kat.PoluPrviDrugi;
				}
				break;
			case PoluDrugiTreci:
				try {
					super.sleep(3000);
					if (nextStop) {
						super.sleep(500);
					}
					if (smjer.equals(Smjer.gore))
						kat = Kat.Treci;
					else if (smjer.equals(Smjer.dole))
						kat = Kat.Drugi;
					return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case Treci:
				try {
					super.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (smjer.equals(Smjer.gore)) {
					kat = Kat.PoluTreciCetvrti;
				} else {
					kat = Kat.PoluDrugiTreci;
				}
				break;
			case PoluTreciCetvrti:
				try {
					super.sleep(3000);
					if (nextStop) {
						super.sleep(500);
					}
					if (smjer.equals(Smjer.gore))
						kat = Kat.Cetvrti;
					else if (smjer.equals(Smjer.dole))
						kat = Kat.Treci;
					return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case Cetvrti:
				if (smjer.equals(Smjer.dole)) {
					try {
						super.sleep(3000);
						kat = Kat.PoluTreciCetvrti;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
		}
	}
	
	private boolean nextStop() {
		
		switch(kat) {
			case Prvi:
				for(Putnik p : dizalo) {
					if(p.getZeljeni().equals(Kat.Drugi))
						return true;
				}
				for(Putnik p : kat2Ulaz){
					if(p.getSmjer().equals(smjer))
						return true;
				}
				break;
			case Drugi:
				for(Putnik p : dizalo) {
					if((p.getZeljeni().equals(Kat.Treci) && smjer.equals(Smjer.gore)) || (p.getZeljeni().equals(Kat.Prvi) && smjer.equals(Smjer.dole))) {
						return true;
					}
				}
				if(smjer.equals(Smjer.gore)) {
					for(Putnik p : kat3Ulaz) {
						if(p.getSmjer().equals(Smjer.gore))
							return true;
					}
				} else if(smjer.equals(Smjer.dole)) {
					if(!kat1Ulaz.isEmpty()) {
						return true;
					}
				}
				break;
			case Treci:
				for(Putnik p : dizalo) {
					if((p.getZeljeni().equals(Kat.Cetvrti) && smjer.equals(Smjer.gore)) || (p.getZeljeni().equals(Kat.Drugi) && smjer.equals(Smjer.dole))) {
						return true;
					}
				}
				if(smjer.equals(Smjer.dole)) {
					for(Putnik p : kat2Ulaz) {
						if(p.getSmjer().equals(Smjer.dole))
							return true;
					}
				} else if(smjer.equals(Smjer.gore)) {
					if(!kat4Ulaz.isEmpty()) {
						return true;
					}
				}
				break;
			case Cetvrti:
				for(Putnik p : dizalo) {
					if(p.getZeljeni().equals(Kat.Treci))
						return true;
				}
				for(Putnik p : kat3Ulaz){
					if(p.getSmjer().equals(smjer))
						return true;
				}
				break;
				default:
					break;
		}
		return false;
	}

	@Override
	public void run() {

		while (true) {

			if (dizalo.isEmpty() && kat1Ulaz.isEmpty() && kat2Ulaz.isEmpty() && kat3Ulaz.isEmpty()
					&& kat4Ulaz.isEmpty()) {
				try {
					if(kat.equals(Kat.Prvi))
						smjer = Smjer.gore;
					if(kat.equals(Kat.Cetvrti))
						smjer = Smjer.dole;
					super.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(dizalo.isEmpty() && (!kat1Ulaz.isEmpty() || !kat2Ulaz.isEmpty() || kat3Ulaz.isEmpty() || kat4Ulaz.isEmpty())) {
				switch(kat) {
					case Prvi:
						smjer = Smjer.gore;
						if(!kat1Ulaz.isEmpty()) {
							vrata = Vrata.otvoreno;
							try {
								super.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							vrata = Vrata.zatvoreno;
						}
						stop = nextStop();
						transition(stop);
						break;
					case Drugi:
						if(!kat2Ulaz.isEmpty()) {
							smjer = kat2Ulaz.get(0).getSmjer();
							vrata = Vrata.otvoreno;
							try {
								super.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							vrata = Vrata.zatvoreno;
						}
						if(!kat1Ulaz.isEmpty())
							smjer = Smjer.dole;
						else if(!kat3Ulaz.isEmpty() || !kat4Ulaz.isEmpty())
							smjer = Smjer.gore;
						stop = nextStop();
						transition(stop);
						break;
					case Treci:
						if(!kat3Ulaz.isEmpty()) {
							smjer = kat3Ulaz.get(0).getSmjer();
							vrata = Vrata.otvoreno;
							try {
								super.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							vrata = Vrata.zatvoreno;
						}
						if(!kat1Ulaz.isEmpty() || !kat2Ulaz.isEmpty())
							smjer = Smjer.dole;
						else if(!kat4Ulaz.isEmpty())
							smjer = Smjer.gore;
						stop = nextStop();
						transition(stop);
						break;
					case Cetvrti:
						smjer = Smjer.dole;
						if(!kat4Ulaz.isEmpty()) {
							vrata = Vrata.otvoreno;
							try {
								super.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							vrata = Vrata.zatvoreno;
						}
						stop = nextStop();
						transition(stop);
						break;
					default:
						break;
						
				}
			}
			else if(!dizalo.isEmpty()) {
				if(stop) {
					vrata = Vrata.otvoreno;
					try {
						super.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					vrata = Vrata.zatvoreno;
				} 
				if(!dizalo.isEmpty()) {
					stop = nextStop();
					transition(stop);
				}
			}
		}

	}

}
