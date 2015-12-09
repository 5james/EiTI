package main;

import java.util.Random;

public class MyAwards {
	private String[] nagrody = {"Z³oty Glob", "Grammy", "Oscar", "Emmy"};
	private String[] dziedziny = {"najlepszy re¿yser", "najlepszy scenariusz", "najlepszy aktor pierwszoplanowy", "najlepszy aktor durogplanowy", "najlepsza piosenka", "najlepsze kostiumy", "najlepszy film", "najlepsze efekty specjalne", "okolicznoœciowe", "pozosta³e"};
	
	public String getNagrody() {
		return nagrody[randInt(0, nagrody.length)];
	}

	public String getDziedziny() {
		return dziedziny[randInt(0, dziedziny.length)];
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
