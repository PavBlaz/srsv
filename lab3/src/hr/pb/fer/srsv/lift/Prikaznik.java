package hr.pb.fer.srsv.lift;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import hr.pb.fer.srsv.lift.Lift.Kat;

public class Prikaznik extends Thread{
	
	Logger logger = Logger.getLogger("Prikaznik");
	FileHandler fh;
	
	private Lift l1;
	
	private StringBuffer str;
	
	public Prikaznik(Lift lift) {
		this.l1 = lift;

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
	
	public static char getLevelChar(Kat kat) {
		switch(kat) {
			case Prvi:
				return '1';
			case Drugi:
				return '2';
			case Treci:
				return '3';
			case Cetvrti:
				return '4';
			default:
				return '0';
		}
	}
	
	@Override
	public void run() {
		while(true) {
			str = new StringBuffer("              Lift1     \n"
					+"Smjer/vrata:            \n"
					+"Stajanja:=====  ----  == Izašli\n"
					+"4:          |          |          \n"
					+"  ==========|          |\n"
					+"3:          |          |          \n"
					+"  ==========|          |\n"
					+"2:          |          |          \n"
					+"  ==========|          |\n"
					+"1:          |          |          \n"
					+"========================\n"
					+"Putnici:                \n"
					+"     od:                \n"
					+"     do:                \n");
			//Ispis pokazivaca lifta za smjer kretanja i pozicije vrata(otvoreno-zatvoreno)
			switch(l1.getSmjer()) {
				case gore:
					str.setCharAt(41, 'G');
					break;
				case dole:
					str.setCharAt(42, 'D');
					break;
				default:
					break;
			}
			
			switch(l1.getVrata()) {
				case otvoreno:
					str.setCharAt(43, 'O');
					break;
				case zatvoreno:
					str.setCharAt(44, 'Z');
					break;
			}
			
			//Ispis ulaznih i izlaznih putnika po katovima
			int i = 0;
			if(!l1.getKat1Ulaz().isEmpty()) {
				i = 0;
				for(Putnik p : l1.getKat1Ulaz()) {
					str.setCharAt(264+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat1Izlaz().isEmpty()) {
				i = 0;
				for(Putnik p : l1.getKat1Izlaz()) {
					str.setCharAt(286+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat2Ulaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat2Ulaz()) {
					str.setCharAt(204+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat2Izlaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat2Izlaz()) {
					str.setCharAt(226+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat3Ulaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat3Ulaz()) {
					str.setCharAt(144+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat3Izlaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat3Izlaz()) {
					str.setCharAt(166+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat4Ulaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat4Ulaz()) {
					str.setCharAt(84+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat4Izlaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat4Izlaz()) {
					str.setCharAt(106+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			//Ispis putnika u liftu
			i = 0;
			switch(l1.getKat()) {
				case Prvi:
					str.setCharAt(275, '[');
					str.setCharAt(284, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(276+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case PoluPrviDrugi:
					str.setCharAt(250, '[');
					str.setCharAt(259, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(251+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case Drugi:
					str.setCharAt(215, '[');
					str.setCharAt(224, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(216+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case PoluDrugiTreci:
					str.setCharAt(190, '[');
					str.setCharAt(199, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(191+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case Treci:
					str.setCharAt(155, '[');
					str.setCharAt(164, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(156+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case PoluTreciCetvrti:
					str.setCharAt(130, '[');
					str.setCharAt(139, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(131+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case Cetvrti:
					str.setCharAt(104, ']');
					str.setCharAt(95, '[');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(96+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
			}
			
			//Ispis svih izlaznih putnika
			if(!l1.getIzasli().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getIzasli()) {
					str.setCharAt(331+i, p.getInitial());
					str.setCharAt(356+i, getLevelChar(p.getPolazni()));
					str.setCharAt(381+i, getLevelChar(p.getZeljeni()));
					i++;
					if(i==15)
						break;
				}
			}
			
			System.out.println(str);
			logger.info(str.toString());
			try {
				super.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
