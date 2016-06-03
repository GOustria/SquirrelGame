package de.hsa.games.fatsquirrel.core;
import java.util.Random;

public final class XY {
	
	private final int x;
	private final int y;
	
	private Random rnd;
	
	public XY(int x,int y) {
		this.x=x;
		this.y=y;
		
		rnd=new Random();
	}
	
	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	
	public XY add(XY xy) {
		return new XY(x+xy.x,y+xy.y);
	}
	
	public XY add(int x,int y) {
		return new XY(this.x+x,this.y+y);
	}
	
	//nächstes Schritt für Bots (Schrittweite 1 in jede Richtung)
	public XY randomAdd() {
		XY temp=null;
		switch (rnd.nextInt(8)+1) {
		case 1: temp=add(0,1); //UP
				break;
		case 2: temp=add(0,-1);//DOWN
				break;
		case 3: temp=add(1,0); //RIGHT
				break;
		case 4: temp=add(-1,0); //LEFT
				break;
		case 5: temp=add(1,1); //UP-RIGHT
				break;
		case 6: temp=add(-1,1); //UP-LEFT
				break;
		case 7: temp=add(1,-1); //DOWN-RIGHT
				break;
		case 8: temp=add(-1,-1); //DOWN-LEFT
				break;
		default:;
				break;
		}
		return temp;
	}
	
	public static XY getRandomDirection() {
		switch (new Random().nextInt(4)) {
		case 0:
			return new XY(0, -1);		//UP
		case 1:
			return new XY(0, 1);		//DOWN
		case 2:
			return new XY(-1, 0);		//LEFT
		case 3:
			return new XY(1, 0);		//RIGHT
		default:
			return null;				//TODO: Exception?
		}
	}
	
	//generiert einen zufälligen Punkt im Bereich min-max in jede Richtung für den INIT
	public XY randomSet(XY xy) {
		int minX=1;
		int maxX=xy.getX()-2;
		int minY=1;
		int maxY=xy.getY()-2;
		return new XY(rnd.nextInt(maxX-(minX-1))+minX,rnd.nextInt(maxY-(minY-1))+minY);
	}

	public String toString() {
		return "("+x+":"+y+")";
	}
	
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		if(!(o instanceof XY)) {
			return false;
		}
		if(o==this) {
			return true;
		}
		XY xy=(XY) o;
		return (x==xy.x)&&(y==xy.y);
	}
	
	public double getDistanceTo(XY xy) {
		return Math.sqrt(Math.pow((xy.getX()-x),2)+Math.pow((xy.getY()-y),2));
	}

}
