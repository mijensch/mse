package moderneSoftwareentwicklung;

import java.util.*;

public class NGewinnt {
	
public static void main(String[] args) {

		char[][] feld = generiereZufaelligesFeld(5, 5, "ab");
		System.out.println("Dieses Feld wurde zufällig bestimmt: \n");
		System.out.println(feldToString(feld));
		
		List<Reihe> reihen = bestimmeAlleReihen(feld);

		for (int n = feld.length - 1; n <= feld.length; n++) {
			System.out.println("Auswertung für " + n + "-Gewinnt:");
			List<Reihe> gewinnReihen = filterReihen(reihen, 'r', n);
			
gewinnReihen.addAll(filterReihen(reihen, 'b', n));	//von welchen Mitspielern sollen die Reihen gesucht werden
			gewinnReihen.addAll(filterReihen(reihen, 'x', n));	
			for (Reihe r : gewinnReihen)
				System.out.println("- Diese Reihe gewinnt " + n + "-gewinnt:\n " + r);
				char[][] nGewinnt = markiereReihen(gewinnReihen, n, feld);
				System.out.println("\n" + feldToString(nGewinnt));
		}
	}
	
	
	public static Random r = new Random();

	private static char[][] generiereZufaelligesFeld(int i, int j, String string) {
		char[][] feld = new char[i][j];
		int iFreieFelder = i * j;
		boolean player1 = true;
		int x, y;
		while(iFreieFelder > 0) {
			x = r.nextInt(i);				//x und y Werte werden bestimmt
			y = r.nextInt(j);
			if(feld[x][y] == '\u0000') {
				if(player1) {
feld[x][y] = string.charAt(0); 	// es wird ein Feld für Spieler 1 markiert
player1 = !player1;	// player1 wird zu false, somit ist player2 dran
				} else {
feld[x][y] = string.charAt(1);	//es wird ein Feld für Spieler 2 markiert
player1 = !player1;	// player2 wird zu true, beim nächsten Zug ist player1 dran
				}
				iFreieFelder -= 1;
			}
		}
		return feld;
	}
	
	private static String feldToString(char[][] feld) {
		String sReturnFeld = "";
		for(char[] reihe : feld) {		//es werden die einzelnen Reihen durchgegangen 
for(char feldElement : reihe) {	//es werden die einzelnen Felder durchgegangen 	
sReturnFeld += feldElement + " ";	//Die Felder werden mit Leerzeichen getrennt
			}
sReturnFeld += "\n";	//Die Felder werden mit Absätzen getrennt
		}		
		return sReturnFeld;
	}
	private static List<Reihe> filterReihen(List<Reihe> reihen, char c, int n) {
		List<Reihe> reiheReturnList = new LinkedList<Reihe>();
		for(Reihe reiheX : reihen) {
if(reiheX.zeichen == c && reiheX.laenge >= n) {	//überprüft die Einzelnen Elemente einer Reihe auf das vorkommen eines Zeichens in einer gewissen Länge
				reiheReturnList.add(reiheX);	
			}
		}
		return reiheReturnList;
	}
public static List<Reihe> bestimmeAlleReihen(char[][] feld) {	//überprüft das Auftreten von Reihen, wird eine gefunde, erstellt er
List<Reihe> returnList = new LinkedList<Reihe>();	//eine Datei vom Datentyp Reihe und bestimmt durch die Mehtode checkRow
		int offsetX = 0, offsetY = 0;				//noch die Länge
		for(int ix = 0; ix < feld.length; ix++) {
			for(int iy = 0; iy < feld[0].length; iy++) {
				offsetX = 1;
				offsetY = 0;  				// Nach unten testen
				Reihe tempReihe1 = new Reihe(feld[ix][iy], ix, iy, offsetX, offsetY,
						checkRow(feld, offsetX, offsetY, ix, iy, feld[ix][iy]));
				returnList.add(tempReihe1);
				offsetX = 0;
				offsetY = 1;  				// Nach rechts testen
				Reihe tempReihe2 = new Reihe(feld[ix][iy], ix, iy, offsetX, offsetY,
						checkRow(feld, offsetX, offsetY, ix, iy, feld[ix][iy]));
				returnList.add(tempReihe2);
				offsetX = 1;
				offsetY = 1;  				// Nach schräg rechts testen
				Reihe tempReihe3 = new Reihe(feld[ix][iy], ix, iy, offsetX, offsetY,
						checkRow(feld, offsetX, offsetY, ix, iy, feld[ix][iy]));
				returnList.add(tempReihe3);
				offsetX = 1;
				offsetY = -1;  				// Nach unten links testen
				Reihe tempReihe4 = new Reihe(feld[ix][iy], ix, iy, offsetX, offsetY,
						checkRow(feld, offsetX, offsetY, ix, iy, feld[ix][iy]));
				returnList.add(tempReihe4);
			}
		}
		return returnList;
	}
	
	private static int checkRow(char[][] feld, int offsetX, int offsetY, int ix, int iy, char c) {
		int counter = 0;
		while(ix + counter * offsetX < feld.length && iy + counter * offsetY < feld[0].length 
				&& ix + counter * offsetX >= 0 && iy + counter * offsetY >= 0 
				&& feld[ix + counter * offsetX][iy + counter * offsetY] == c) {
			counter++;
		}
		return counter;
	}
	
	
	private static char[][] markiereReihen(List<Reihe> gewinnReihen, int n, char[][] feld) {
		char[][] feldReturnLoesungen = new char[feld.length][feld[0].length];
		for(int iy = 0; iy < feld[0].length; iy++) {
			for(int ix = 0; ix < feld.length; ix++) {
				feldReturnLoesungen[ix][iy] = '.';  //erstellt ein blanco Feld aus Punkten
			}
		}
		for(Reihe reiheX : gewinnReihen) {
for(int laengeReihe = 0; laengeReihe < reiheX.laenge; laengeReihe++) {		feldReturnLoesungen[reiheX.startX + laengeReihe * reiheX.richtungX] 		[reiheX.startY + laengeReihe * reiheX.richtungY] = reiheX.zeichen;
			}
		}
		return feldReturnLoesungen;
	}
}

