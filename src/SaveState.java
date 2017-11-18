import java.io.*;
import java.util.*;

public class SaveState implements Serializable {
    /**
	 * SUID of the class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Arraylist of Cordinates for storing moves
	 */
	public static ArrayList<Cordinates> list = new ArrayList<Cordinates>();
    /**
     * function for serailizing arrayList for saving the game
     */
    public static void saveGame()
    {
    	
           try{
               FileOutputStream fout= new FileOutputStream("SavedGame");
               ObjectOutputStream stream= new ObjectOutputStream(fout);
               stream.writeObject(list);
               stream.close();
               fout.close();
               FileOutputStream fout1= new FileOutputStream("PlayerSave");

               ObjectOutputStream stream2= new ObjectOutputStream(fout1);

               stream2.writeObject(new Cordinates(Player.p,0));

               stream2.close();

               fout1.close();
             }
           catch(IOException e){
                  e.printStackTrace();
             }
    }
    /**
     * adding Coordinates to the arraylist
     * @param C-Cordinate to be added
     */
    public static void add(Cordinates C) {
    	list.add(C);
    }

	/**
     * function for deserailizing arrayList for resuming the game
     * @return saved game ArrayList of Cordinates
     */
    public static ArrayList<Cordinates> loadGame() 
    {
//    	System.out.println("tm");
        try {
            FileInputStream fin = new FileInputStream("SavedGame");
            ObjectInputStream stream = new ObjectInputStream(fin);
            list = (ArrayList) stream.readObject();
            stream.close();
            fin.close();
            return list;
        }catch(Exception e)
        {
        	   e.printStackTrace();
        	return null;
          }
    }
    public static int loadPlayer()
    {
        try {
            FileInputStream fin = new FileInputStream("PlayerSave");
            ObjectInputStream stream = new ObjectInputStream(fin);
            Cordinates z = (Cordinates) stream.readObject();
            stream.close();
            fin.close();
            return z.getX();
        }catch(Exception e)
        {
                  e.printStackTrace();
              
               return (Integer) null;
          }
    }
    

}
