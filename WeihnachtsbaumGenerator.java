package moderneSoftwareentwicklung;

import java.util.Scanner; 

/**
 * Generiert nach Angabe der gewünschen Höhe einen Weihnachtsbaum.
 * Hier ist noch ein Testkommentar für mse ESA5.
 * Hier kommt noch ein Uptdate-Kommentar.
 * @author: Michaela 
 * @version: 1.0
 */

public class WeihnachtsbaumGenerator {
  public static void main(String [] args) {
      Scanner in = new Scanner(System.in);
      System.out.println("Geben Sie die Höhe an:");

      int hoehe = in.nextInt();
      if (hoehe <= 0) {
        System.out.println("Bitte geben Sie eine Zahl ein, die größer ist als 0");
      }

      for (int i = 0; i < hoehe; i++) {
      for (int k = 0; k < hoehe - 1 - i; k++) {
        System.out.print(" ");
      }
      for (int j = 0; j < i * 2 + 1; j++) { 
        System.out.print("*");
      }
        System.out.println();
      }

      int i;
      for (i = 1; i < hoehe; i++) {
        System.out.print(" ");	
      }
      System.out.println("I");
      in.close();
   }
}

