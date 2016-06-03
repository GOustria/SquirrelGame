import de.hsa.games.fatsquirrel.core.XY;

public class TestDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XY x1=new XY(1,1);
		XY x2=new XY(4,4);
		
		
		
		System.out.println(x1.getDistanceTo(x2));
		System.out.println("in "+(int)x1.getDistanceTo(x2)+" steps");

	}

}
