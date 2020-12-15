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
			str = new StringBuffer("              Lift1     "
					+"Smjer/vrata:            "
					+"Stajanja:=====  ----  == Izašli"
					+"4:          |          |          "
					+"  ==========|          |"
					+"3:          |          |          "
					+"  ==========|          |"
					+"2:          |          |          "
					+"  ==========|          |"
					+"1:          |          |          "
					+"========================"
					+"Putnici:                "
					+"     od:                "
					+"     do:                ");
			//Ispis pokazivaca lifta za smjer kretanja i pozicije vrata(otvoreno-zatvoreno)
			switch(l1.getSmjer()) {
				case gore:
					str.setCharAt(41, 'G');
					break;
				case dole:
					str.setCharAt(42, 'D');
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
					str.setCharAt(265+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat1Izlaz().isEmpty()) {
				i = 0;
				for(Putnik p : l1.getKat1Izlaz()) {
					str.setCharAt(287+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat2Ulaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat2Ulaz()) {
					str.setCharAt(205+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat2Izlaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat2Izlaz()) {
					str.setCharAt(227+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat3Ulaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat3Ulaz()) {
					str.setCharAt(145+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat3Izlaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat3Izlaz()) {
					str.setCharAt(167+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat4Ulaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat4Ulaz()) {
					str.setCharAt(82+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			if(!l1.getKat4Ulaz().isEmpty()) {
				i = 0;
				for(Putnik p: l1.getKat4Izlaz()) {
					str.setCharAt(107+i, p.getInitial());
					i++;
					if(i==10)
						break;
				}
			}
			//Ispis putnika u liftu
			i = 0;
			switch(l1.getKat()) {
				case Prvi:
					str.setCharAt(276, '[');
					str.setCharAt(275, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(277+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case PoluPrviDrugi:
					str.setCharAt(251, '[');
					str.setCharAt(260, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(252+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case Drugi:
					str.setCharAt(216, '[');
					str.setCharAt(225, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(217+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case PoluDrugiTreci:
					str.setCharAt(191, '[');
					str.setCharAt(200, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(192+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case Treci:
					str.setCharAt(156, '[');
					str.setCharAt(165, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(157+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case PoluTreciCetvrti:
					str.setCharAt(131, '[');
					str.setCharAt(140, ']');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(132+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
				case Cetvrti:
					str.setCharAt(105, ']');
					str.setCharAt(96, '[');
					for(Putnik p : l1.getDizalo()) {
						str.setCharAt(97+i, p.getInitial());
						i++;
						if(i==8)
							break;
					}
					break;
			}
			
			//Ispis svih izlaznih putnika
			if(!l1.getIzasli().isEmpty()) {
				for(Putnik p: l1.getIzasli()) {
					i = 0;
					str.setCharAt(331+i, p.getInitial());
					str.setCharAt(356+i, getLevelChar(p.getPolazni()));
					str.setCharAt(381+i, getLevelChar(p.getZeljeni()));
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
