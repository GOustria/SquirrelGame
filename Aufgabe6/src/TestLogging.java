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
		
		// ���������� ���� � ������� ����� ������ ���
		try {
			
			
			System.setErr(new PrintStream(new File("log.txt")));
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ������� ���������
		System.err.println("��������� 1");
		System.err.println("��������� 2");
		// ������� ��������� �� ������
		try {
		     throw new Exception("��������� �� ������");
		} catch (Exception e) {
		     e.printStackTrace();
		}

	}

}
