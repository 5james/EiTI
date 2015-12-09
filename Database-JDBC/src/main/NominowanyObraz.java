package main;

import java.util.Random;

public class NominowanyObraz {
	private int myId = -1;
	private int nagrodaMax;
	private int obrazMax;
	private String tableName = "NOMINOWANY_OBRAZ";
	private String[] columnsNO = {"ID_OBRAZ", "ID_NAGRODA"};
	NominowanyObraz() {
		Nagroda n = new Nagroda();
		nagrodaMax = n.drawMax();
		Obraz o = new Obraz();
		obrazMax = o.drawMax();
	}
	
	public void insert() {
		MyConnection jdbcConnection = new MyConnection();
		for (int i = 0; i < 5000; i++) {
			String[] wrzuc = new String[columnsNO.length];
			Integer wfirst = new Integer(randInt(0, obrazMax));
			wrzuc[0] = new String(wfirst.toString());
			Integer wsecond = new Integer(randInt(0, nagrodaMax));
			wrzuc[1] = new String(wsecond.toString());
			jdbcConnection.insert(tableName, myId, columnsNO, wrzuc);
		}
	}
	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min)) + min;

		return randomNum;
	}

}
