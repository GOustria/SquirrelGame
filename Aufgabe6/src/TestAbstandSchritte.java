import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.core.XY;

public class TestAbstandSchritte {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		int n=3;
//		XY xy=new XY(5,5);
//		
//		for(int x=-n;x<=n;x++) {
//			for(int y=n;y>=-n;y--) {
//				System.out.println("("+(x+xy.getX())+","+(y+xy.getY())+")");
//			}
//			System.out.println("\n");
//		}
		
		BoardConfig bc=new BoardConfig(new XY(20,20));
		
		Board b=new Board(bc);
		
		FlattenedBoard fb=new FlattenedBoard(b);
		
		System.out.println(fb.toString());
		
		fb.nearestPlayerEntity(new XY(9,9));

	}

}
