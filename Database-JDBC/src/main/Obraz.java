package main;

import java.sql.*;
import java.util.Random;

public class Obraz {

	private int myId = -1;
	private String myTytul = new String("");
	private String myTytulOryginalny = new String("");
	private String myGatunek = new String ("");
	private String tableName = "OBRAZ";
	private String idName = "ID";
	private String[] columns = {"TYTU£", "TYTU£_ORYGINALNY", "GATUNEK"};
	private String[] columnsSerial = {"TYTU£", "TYTU£_ORYGINALNY", "GATUNEK", "ID", "DATA_ROZPOCZÊCIA", "LICZBA SEZONÓW", "LICZBA_ODCINKÓW", "FLAGA"};
	private String[] columnsFilm = {"BUD¯ET", "TYTU£", "TYTU£_ORYGINALNY", "GATUNEK", "ID", "ROK", "D£UGOŒÆ", "FLAGA"};
	public Obraz() {
	}
	public int drawMax() {
		MyConnection jdbcConnection = new MyConnection();
		Object rset = jdbcConnection.selectMax(tableName, idName);
		int max = Integer.parseInt(rset.toString());
//		System.out.println(max);
		return max;
	}

	void insert() {
		MyConnection jdbcConnection = new MyConnection();
		int maxx = drawMax();
		for (int i = 0; i < 5000; i++) {
			String[] wrzuc = new String[columnsFilm.length];
			MyTitles tytuly = new MyTitles();
			Integer wfirst = new Integer(randInt(900000, 1000000));
			wrzuc[0] = new String(wfirst.toString());
			wrzuc[1] = tytuly.getTitles();
			wrzuc[2] = wrzuc[1].toString();
			wrzuc[3] = tytuly.getGatunki();
			Integer wfourth = new Integer(++maxx);
			wrzuc[4] = new String (wfourth.toString());
			wrzuc[5] = new String (new Integer(randInt(1950, 2014)).toString());
			wrzuc[6] = new String (new Integer(randInt(70, 120)).toString());	
			wrzuc[7] = new String("F");
			jdbcConnection.insert(tableName, myId, columnsFilm, wrzuc);
		}
	}
	void get(int id) {
		MyConnection jdbcConnection = new MyConnection();
		Object[] result = jdbcConnection.download(tableName, id, idName, columns);
		myTytul = (String) result[0];
		myTytulOryginalny = (String) result[1];
		myGatunek = (String) result[2];
		try {
			myId = Integer.parseInt((String) result[3]);
			System.out.println(myTytul + "\t" + myTytulOryginalny + "\t" + myGatunek);
		}
		catch (NumberFormatException e) {
			myId = -1;
			System.out.println("Id wykroczylo poza zakres");
		}
	}
	void getInBackground(int id) {
		MyConnection jdbcConnection = new MyConnection();
		Object[] result = jdbcConnection.download(tableName, id, idName, columns);
		myTytul = (String) result[0];
		myTytulOryginalny = (String) result[1];
		myGatunek = (String) result[2];
		try {
			myId = Integer.parseInt((String) result[3]);
			//System.out.println(myTytul + "\t" + myTytulOryginalny + "\t" + myGatunek);
		}
		catch (NumberFormatException e) {
			myId = -1;
			//System.out.println("Id wykroczylo poza zakres");
		}
	}

	void update() {
		try {
			if (myId == -1)
				throw new Exception("Klasa " + this.getClass().getName() + " nie zostala zintegrowana z baza danych, nie mozna update'owac");
			Obraz myPicture = new Obraz();
			myPicture.getInBackground(myId);
			if (this.equals(myPicture)) {
				System.out.println("Nie potrzeba update");
				return;
			}
			else {
				MyConnection jdbcConnection = new MyConnection();
				Object[] toUpdate = new Object[columns.length];
				toUpdate[0] = myTytul;
				toUpdate[1] = myTytulOryginalny;
				toUpdate[2] = myGatunek;
				jdbcConnection.upload(tableName, myId, idName, columns, toUpdate);
				System.out.println("Baza zostala zaktualizowana");
				this.get(myId);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public int getId() {
		return myId;
	}

	public String getTytul() {
		return myTytul;
	}

	public void setTytul(String z) {
		myTytul = z;
	}

	public String getTytulOryginalny() {
		return myTytulOryginalny;
	}

	public void setTytulOryginalny(String z) {
		myTytulOryginalny = z;
	}

	public String getGatunek() {
		return myGatunek;
	}

	public void setGatunek(String z) {
		myGatunek = z;
	}

	public boolean equals (Obraz o) {
		if (myId == o.getId() && myTytul.equals(o.getTytul())  && myTytulOryginalny.equals(o.getTytulOryginalny()) && myGatunek.equalsIgnoreCase(o.getGatunek()) ) {
			return true;
		}
		else {
			return false;
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