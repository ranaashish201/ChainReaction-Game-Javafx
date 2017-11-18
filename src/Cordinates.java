import java.io.Serializable;

public class Cordinates implements Serializable {
	/**
	 * SUID of the class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Cordintes of the cell
	 * x-Row index
	 * y-Column index
	 */
	private int x,y;
	/**
	 * Checking for it be a undo move or not
	 */
	private boolean undoCordinate;
	/**
	 * getter function for undoCordinate 
	 * @return boolean undoCordinate
	 */
	public boolean isUndoCordinate() {
		return undoCordinate;
	}
	/**
	 * setter function for undoCordinate
	 * @param value- Value to be set for undoCordintes
	 */
	public void setUndoCordinate(boolean value) {
		this.undoCordinate = value;
	}
	/**
	 * Constructor for COrdinate Class
	 * @param x-Row index
	 * @param y-Column index
	 */
	public Cordinates(int x,int y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * getter function for row index of the cordinate
	 * 
	 * @return int value of x
	 */
	public int getX() {
		return x;
	}
	/**
	 * getter function for column index of the cordinate
	 * 
	 * @return int value of y
	 */
	public int getY() {
		return y;
	}
}
