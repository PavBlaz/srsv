package hr.pb.fer.srsv.lift;

public class LiftMain {

	public static void main(String[] args) {
		
		Lift lift = new Lift();
		lift.run();
		Prikaznik prikaznik = new Prikaznik(lift);
		prikaznik.run();
		
		int maxTimeGeneratingThreshold = 6000;
		GeneratorPutnika generator = new GeneratorPutnika(maxTimeGeneratingThreshold, lift);
		generator.run();

	}

}
