package hr.pb.fer.srsv.lift;

public class LiftMain {

	public static void main(String[] args) {
		
		Lift lift = new Lift();
		lift.start();
		Prikaznik prikaznik = new Prikaznik(lift);
		prikaznik.start();
		
		int maxTimeGeneratingThreshold = 6000;
		GeneratorPutnika generator = new GeneratorPutnika(maxTimeGeneratingThreshold, lift);
		generator.start();

	}

}
