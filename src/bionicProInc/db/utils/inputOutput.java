package bionicProInc.db.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class inputOutput {
	
	public int getIntFromKeyboard() {
        int readInt;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String cadena = reader.readLine();
                readInt = Integer.parseInt(cadena);
                return readInt;
            } catch (IOException ioe) {
                System.out.println("There was an error while reading, please try again.");
            } catch (NumberFormatException nfe) {
                System.out.println("You have to introduce an integer.");
            }

        }

    }

    public float getFloatFromKeyboard() {
        float readFloat;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String cadena = reader.readLine();
                readFloat = Float.parseFloat(cadena);
                return readFloat;
            } catch (IOException ioe) {
            	System.out.println("There was an error while reading, please try again.");
            } catch (NumberFormatException nfe) {
            	System.out.println("You have to introduce a number.");
            }

        }

    }

}
