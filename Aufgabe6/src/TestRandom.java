import java.util.Random;

public class TestRandom {
//	randomInt
	private static Random randomNumberGenerator;
    private static synchronized void initRNG() {
        if (randomNumberGenerator == null) 
            randomNumberGenerator = new Random();
    }
    public static int randomInt(int min, int max) {
        if (randomNumberGenerator == null) 
            initRNG();
        int number = randomNumberGenerator.nextInt( max+1-min );
        return min + number;
    }
//    ***

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Random rnd=new Random();
		
		int min=-1;
		int max=0;
		
		while(true) {
			System.out.println(rnd.nextInt(max-(min-1))+min);
			Thread.sleep(1000);
		}

	}

}
