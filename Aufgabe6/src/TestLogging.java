import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestLogging {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
		
		Logger log = Logger.getLogger(TestLogging.class.getName());
		
		log.info("Some INFO message");
		log.warning("Some WARNING message");
		log.severe("Some SEVERE message");
		log.config("Some CONFIG message");
		
		// Определяем файл в который будем писать лог
		try {
			
			
			System.setErr(new PrintStream(new File("log.txt")));
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Выводим сообщения
		System.err.println("Сообщение 1");
		System.err.println("Сообщение 2");
		// Выводим сообщение об ошибке
		try {
		     throw new Exception("Сообщение об ошибке");
		} catch (Exception e) {
		     e.printStackTrace();
		}

	}

}
