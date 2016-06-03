import java.util.Random;

import de.hsa.games.fatsquirrel.core.XY;


public class TestXY {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		XY x1=new XY(0,-3);
		
		System.out.println(x1);
		
		Random rnd=new Random();
		
		while(true) {
//			System.out.println(rnd.nextInt(max-(min-1))+min);
//			Thread.sleep(1000);
			
			if(x1.getX()>0&&x1.getY()>0) {
				int max=0;
				int min=-1;
				XY pkt=new XY(rnd.nextInt(max-(min-1))+min,rnd.nextInt(max-(min-1))+min);
				if(pkt.equals(new XY(0,0))) {
					continue;
				}
				System.out.println(pkt);
			}
			if(x1.getX()>0&&x1.getY()<0) {
				int maxX=0;
				int minX=-1;
				int maxY=1;
				int minY=0;
				XY pkt=new XY(rnd.nextInt(maxX-(minX-1))+minX,rnd.nextInt(maxY-(minY-1))+minY);
				if(pkt.equals(new XY(0,0))) {
					continue;
				}
				System.out.println(pkt);
			}
			if(x1.getX()<0&&x1.getY()<0) {
				int max=1;
				int min=0;
				XY pkt=new XY(rnd.nextInt(max-(min-1))+min,rnd.nextInt(max-(min-1))+min);
				if(pkt.equals(new XY(0,0))) {
					continue;
				}
				System.out.println(pkt);
			}
			if(x1.getX()<0&&x1.getY()>0) {
				int maxX=1;
				int minX=0;
				int maxY=0;
				int minY=-1;
				XY pkt=new XY(rnd.nextInt(maxX-(minX-1))+minX,rnd.nextInt(maxY-(minY-1))+minY);
				if(pkt.equals(new XY(0,0))) {
					continue;
				}
				System.out.println(pkt);
			}
			if(x1.getX()==0&&x1.getY()>0) {
				int max=1;
				int min=-1;
				XY pkt=new XY(rnd.nextInt(max-(min-1))+min,-1);
				if(pkt.equals(new XY(0,0))) {
					continue;
				}
				System.out.println(pkt);
			}
			if(x1.getX()>0&&x1.getY()==0) {
				int max=1;
				int min=-1;
				XY pkt=new XY(-1,rnd.nextInt(max-(min-1))+min);
				if(pkt.equals(new XY(0,0))) {
					continue;
				}
				System.out.println(pkt);
			}
			if(x1.getX()<0&&x1.getY()==0) {
				int max=1;
				int min=-1;
				XY pkt=new XY(1,rnd.nextInt(max-(min-1))+min);
				if(pkt.equals(new XY(0,0))) {
					continue;
				}
				System.out.println(pkt);
			}
			if(x1.getX()==0&&x1.getY()<0) {
				int max=1;
				int min=-1;
				XY pkt=new XY(rnd.nextInt(max-(min-1))+min,1);
				if(pkt.equals(new XY(0,0))) {
					continue;
				}
				System.out.println(pkt);
			}
			Thread.sleep(1000);
		}
	}

}
