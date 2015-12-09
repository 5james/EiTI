package main;

import java.util.Random;

public class Nagroda {
	private int myId = -1;
	private String myTyp = new String("");
	private String myDziedzina = new String("");
	private String tableName = "NAGRODA";
	private String idName = "ID";
	private String[] columnsNagroda = {"ROK", "TYP", "DZIEDZINA", "ID"};
	public int drawMax() {
		MyConnection jdbcConnection = new MyConnection();
		Object rset = jdbcConnection.selectMax(tableName, idName);
		int max = Integer.parseInt(rset.toString());
//		System.out.println(max);
		return max;
	}
	public void insert() {
		MyConnection jdbcConnection = new MyConnection();
		int maxx = drawMax();
		for (int i = 0; i < 4000; i++) {
			String[] wrzuc = new String[columnsNagroda.length];
			MyAwards nagrody = new MyAwards();
			Integer wfirst = new Integer(randInt(1950, 2014));
			wrzuc[0] = new String(wfirst.toString());
			wrzuc[1] = nagrody.getNagrody();
			wrzuc[2] = nagrody.getDziedziny();
			Integer wfourth = new Integer(++maxx);
			wrzuc[3] = new String (wfourth.toString());
			jdbcConnection.insert(tableName, myId, columnsNagroda, wrzuc);
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
