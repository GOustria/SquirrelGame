import java.util.Timer;
import java.util.TimerTask;

class MyTimerTask extends TimerTask {
	String str;
	
	MyTimerTask(String str) {
		this.str=str;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Ausgabe von: "+str);
	}
	
}

public class TestTimer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timer myTimer=new Timer();
		
		MyTimerTask myTimertask=new MyTimerTask("timerTask, jede Sekunde");
		
		MyTimerTask myTimertask1=new MyTimerTask("timerTask1, jede 5-te Sekunde");
		
		MyTimerTask myTimertask2=new MyTimerTask("timerTask2, jede 10-te Sekunde");
		
		myTimer.schedule(myTimertask,0,1000);
		
		myTimer.schedule(myTimertask1,0,5000);
		
		myTimer.schedule(myTimertask2,0,10000);

	}

}
