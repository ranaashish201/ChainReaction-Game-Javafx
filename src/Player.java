import javafx.scene.paint.*;

/**
 * @author Deepak And Ashish
 * @version 1.0
 * Date- 19/11/2017
 *
 */
public class Player{
	
//	static int[] totalUndo;
    /**
     * int value for total number of Orbs present in the grid
     */
    
    static   int totalOrbs;

    /**
     * int value for storing player Id during recusion burst
     */
    static   int tempPlayerId;
    /**
     * int value for number of players in the game
     */
	static    int p;
	/**
	 * int[] array for storing each players number of Orbs
	 */
	static   int[] PlayerOrbs;
	/**
	 * boolean array for player validity to continue
	 */
	static   boolean[] canContinue;
	/**
	 * initiating playerOrbs array
	 */
	public static void validity() {
		canContinue[0]=false;
		for(int i=1;i<=p;i++) {
		//	totalUndo[i]=2;
			canContinue[i]=true;
		}
	}
	/**
	 * color Identity of the player
	 */
	static   Color[] colorarr={Color.WHITE,Color.GREEN,Color.RED,Color.YELLOW,Color.BLUE,Color.PINK,Color.VIOLET,Color.AQUAMARINE,Color.BEIGE};
	
	/**
	 * check for the continuity of the player
	 */
	public static void checkContinuity() {
		for(int i=1;i<=p;i++) {
			if(PlayerOrbs[i]==0) {
				canContinue[i]=false;
			}
		}
	}
	/**
	 * Set number of player in the game
	 * @param n for number of players to set
	 */
	
	public static void setPlayer(int n)
	{
//		totalUndo=new int[p+1];
		p = n;
		PlayerOrbs=new int[p+1];
		canContinue=new boolean[p+1];
		colorarr = Settings.colorarr;
	}
	
	/**
	 * printing the number of Orbs for each player at a call
	 */
	public static void print() {

		System.out.println("Player Orbs Table");
		for(int i=0;i<=p;i++) {
			System.out.println("Player "+ i +" orbs"+PlayerOrbs[i] );
		}
		System.out.println("Total Orbs-"+totalOrbs);
	}
}
