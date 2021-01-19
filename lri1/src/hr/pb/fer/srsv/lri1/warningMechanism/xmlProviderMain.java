package hr.pb.fer.srsv.lri1.warningMechanism;

public class xmlProviderMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		xmlProvider prov = new xmlProvider("https://api.weatherlink.com/v1/NoaaExt.xml?user=001D0A008F0E&pass=RTZFGH&apiToken=0C6E4D7D4E9E4F7EA690780EAC73C927");
		prov.run();
	}

}