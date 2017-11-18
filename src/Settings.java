import java.util.HashSet;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.*;
/**
 * Settings class to set color of palyers.
 *
 */
public class Settings  {
    /**
     * Color array for default colors of palyers
     */
    public static Color colorarr[] = {Color.WHITE,Color.GREEN,Color.RED,Color.YELLOW,Color.BLUE,Color.PINK,Color.VIOLET,Color.AQUAMARINE,Color.BEIGE};
   
    /**
     * Static method fillcolor() to update color array according to user input.
     * It will create three arrays R[],G[],B[] which will store values given by users in the textfield
     * Then those values will be stored as Color object in colorarr.
     * an OK button is also placed which on clicked will call dowork() method.
     */
    public static void fillcolor()
    {
        Stage window = new Stage();
        window.setWidth(400);
        window.setHeight(600);
        Label l [] = new Label[9];
        Pane layout = new Pane();
        Scene scene = new Scene(layout);
        TextField txt[][] = new TextField[9][4];
        for (int i = 1;i<=8;i++)
        {
            l[i] = new Label("Player "+i);
            l[i].setLayoutX(20);
            l[i].setLayoutY(i*60);
            layout.getChildren().add(l[i]);
            for (int j = 1;j<=3;j++)
            {
                txt[i][j] = new TextField(60+i+j+"");
                txt[i][j].setLayoutX(j*80);
                txt[i][j].setMaxWidth(60);
                txt[i][j].setLayoutY(i*60);
                layout.getChildren().add(txt[i][j]);
            }
        }
        int R[] = new int[9];
        int G[] = new int[9];
        int B[] = new int[9];       
        Button btn = new Button("OK");
        btn.setLayoutX(250);
        btn.setLayoutY(535);
        layout.getChildren().add(btn);
        btn.setOnAction(e->{
            dowork(R,G,B,window,layout,txt);
                        });
        window.setScene(scene);
        window.showAndWait();
    }
   
    /**
     * @param R  takes parameter R as array of Red values of color inputted by user
     * @param G takes parameter G as array of Green values of color inputted by user
     * @param B takes parameter B as array of Blue values of color inputted by user
     * @param window  main window taken as parameter to close it if two players have same rgb values which will be checked by check(R,G,B) method
     * @param layout main layout sent as argument to add a label to prompt about same colors of players
     * @param txt  a TextField[][] array to extract value from it inputted by user
     */
    public static void dowork(int R[],int G[],int B[],Stage window,Pane layout,TextField[][] txt)
    {
        for(int i = 1;i<9;i++)
        {
            R[i] = Integer.parseInt((txt[i][1].getText()));
            G[i] = Integer.parseInt((txt[i][2].getText()));
            B[i] = Integer.parseInt((txt[i][3].getText()));
        }
        for (int i = 1;i< 9;i++)
        {
            colorarr[i] = Color.rgb(R[i],G[i],B[i]);
        }

        if (check(R,G,B))
            window.close();
        else
        {
            Label x = new Label("Cant have same color for two players");
            x.setLayoutX(20);
            x.setLayoutY(570);
            layout.getChildren().add(x);
        }

    }
    /**
     * @param R  R  takes parameter R as array of Red values of color inputted by user
     * @param G  G takes parameter G as array of Green values of color inputted by user
     * @param B  B  takes parameter B as array of Blue values of color inputted by user
     * @return returns true if there is no issue of having same color for more than 1 palyer, otherwise false
     */
    public  static boolean  check(int R[],int G[],int B[])
    {
        HashSet<Node> h = new HashSet<Node>();
        for (int i = 1;i<9;i++)
        {
            Node n = new Node(R[i],G[i],B[i]);
            if (!h.contains(n))
                h.add(n);
            else
                return false;
        }
        System.out.print(h.toString());
        return true;
    }
}
/**
 * Node class with to store rgb as a object to interpret for its equality later
 *
 */
class Node{
    /**
     *  x is R value,y is G value,z is B value
     */
    int x,y,z;
    /**
     * Constructor taking R,G,B value of particular player
     * @param x
     * @param y
     * @param z
     * 
     */
    Node(int x,int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        Node ob = (Node)o;
        return (ob.x == x && ob.y == y && ob.z == z);
    }
    @Override
    public int hashCode()
    {
        return x+y+z;
    }
    public String toString()
    {
        return x+" "+y+" "+z;
    }
}
