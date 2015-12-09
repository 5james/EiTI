package main;

import java.sql.*;

public class Osoba {
	
	private int myId = -1;
	private String myZawod = new String("");
	private String myImie_Nazwisko = new String("");
	private String tableName = "OSOBA";
	private String idName = "ID";
	private String[] columns = {"ZAWÓD", "IMIE_NAZWISKO"};
	public Osoba() {
	}
	void get(int id) {
		MyConnection jdbcConnection = new MyConnection();
		Object[] result = jdbcConnection.download(tableName, id, idName, columns);
		myZawod = (String) result[0];
		myImie_Nazwisko = (String) result[1];
		try {
			myId = Integer.parseInt((String) result[2]);
			System.out.println(myZawod + "\t" + myImie_Nazwisko + "\t" + myId);
		}
		catch (NumberFormatException e) {
			myId = -1;
			System.out.println("Id wykroczylo poza zakres");
		}
	}
	void getInBackground(int id) {
		MyConnection jdbcConnection = new MyConnection();
		Object[] result = jdbcConnection.download(tableName, id, idName, columns);
		myZawod = (String) result[0];
		myImie_Nazwisko = (String) result[1];
		try {
			myId = Integer.parseInt((String) result[2]);
			//System.out.println(myZawod + "\t" + myImie_Nazwisko + "\t" + myId);
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
			Osoba myPerson = new Osoba();
			myPerson.getInBackground(myId);
			if (this.equals(myPerson)) {
				System.out.println("Nie potrzeba update");
				return;
			}
			else {
				MyConnection jdbcConnection = new MyConnection();
				Object[] toUpdate = new Object[columns.length];
				toUpdate[0] = myZawod;
				toUpdate[1] = myImie_Nazwisko;
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
	
	public String getZawod() {
		return myZawod;
	}
	
	public void setZawod(String z) {
		myZawod = z;
	}
	
	public String getImieNazwisko() {
		return myImie_Nazwisko;
	}
	
	
	public void setImieNazwisko(String z) {
		myImie_Nazwisko = z;
	}
	
	public boolean equals (Osoba o) {
		if (myId == o.getId() && myZawod.equalsIgnoreCase(o.getZawod())  && myImie_Nazwisko.equals(o.getImieNazwisko()) ) {
			return true;
		}
		else {
			return false;
		}
	}
}