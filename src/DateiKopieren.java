/**
 * Created by rlukas on 29.01.2015.
 */

import java.io.*;

/** Klasse DateiKopieren */
public class DateiKopieren {
    public static void main(String args[]) {
        if ( args.length < 2) {
            System.out.println("Aufruf : java DateiKopieren" + " quelle ziel ! ");
            System.exit(0);
        }
        /** Erzeuge ein File-Objekt für die Quelldatei */
        File quelle = new File(args[0]);

        /** Erzeuge ein File-Objekt für die Zieldatei */
        File ziel   = new File(args[1]);

        System.out.println("Kopiere " + quelle.getName() + " nach " + ziel.getName() + " !");

        try {
            /** FileReader zum Lesen aus Quelldatei */
            FileReader lesen = new FileReader(quelle);

            /** FileWriter zum Schreiben in Zieldatei */
            FileWriter schreiben = new FileWriter(ziel);

            /** char-Array als Puffer für das Lesen */
            char[] buffer = new char[128];

            /** Die Variable gibt die Anzahl der chars an,
             *  die pro Vorgang gelesen werden */
            int charsGelesen;

            /** erster Lesevorgang */
            charsGelesen = lesen.read(buffer);
            while (charsGelesen != -1) {
                /** Inhalt des Lesevorgangs in Zieldatei
                 *  schreiben */
                schreiben.write(buffer, 0, charsGelesen);
            }

            /** Streams schliessen */
            lesen.close();
            schreiben.close();
        } catch (FileNotFoundException e1) {
            /** die Datei existiert nicht */
            System.out.println("Datei nicht gefunden: " + quelle);
        } catch (IOException e2) {
            /** andere IOexceptions abfangen. */
            e2.printStackTrace();
        }
    }
}
