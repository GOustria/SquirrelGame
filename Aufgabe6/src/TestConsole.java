import java.io.Console;

public class TestConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      // Zugriff auf das Console-Objekt
	      Console cons = System.console();
	      // Ausgabe
	      if (cons != null) {      
	         cons.printf("\n");
	         cons.printf(" Ausgabe der Umlaute mit Console \n");      
	         cons.printf(" ä, ö, ü, ß \n");      
	      }
	}

}
