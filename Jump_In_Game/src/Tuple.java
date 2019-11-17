/**
 * A self-created Tuple data structure for this game
 * Store a piece of coordinator like (2, 4), get rid of array
 * Also be able to store a couple of coordinator. 
 * @author zheji, Defa Lu 
 *
 */
public class Tuple {
	
	private int x; 
	private int y; 
	private int x1;
	private int y1;
	
	/**
	 * For just one coordinator
	 * @param x
	 * @param y
	 */
	public Tuple (int x, int y) {
		this.x = x; 
		this.y = y; 
		x1 = -100; 
		y1 = -100; 
	}

	/**
	 * For Fox, it need two coordinators. 
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 */
	public Tuple (int x, int y, int x1, int y1) {
		this.x = x; 
		this.y = y; 
		this.x1 = x1; 
		this.y1 = y1;
	}
	
	public boolean has4Value () {
		if (x1 > 0 && y1>0) {
			return true;
		} else {
			return false; 
		}
	}
	
	public void setRowNum (int x) {
		this.x = x;
	}
	
	public void setColNum (int y) {
		this.y = y;
	}

	public void setRow1Num (int x1) {
		this.x1 = x1;
	}

	public void setCol1Num (int y1) {
		this.y1 = y1;
	}

	
	public int getRowNum () {
		return this.x;
	}
	
	public int getColNum () {
		return this.y;
	}
	
	public int getRow1Num () {
		return this.x1;
	}
	
	public int getCol1Num () {
		return this.y1;
	}
	
	/**
	 * Check if the location stored is a valid location (both >=0 )
	 * @return true for valid
	 */
	public boolean isValidfor1 () {
		return (x>=0 && y>=0);
	}
	
	/**
	 * Check if the location stored is a valid location (both >=0 )
	 * @return true for valid
	 */
	public boolean isValidfor2 () {
		return (x>=0 && y>=0 && x1>=0 && y1>=0);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Tuple) {
			Tuple location = (Tuple) o; 
			if (location.getRowNum() == this.getRowNum() && location.getColNum() == this.getColNum()) {
				return true;
			}
		} 
		return false;
	}
}