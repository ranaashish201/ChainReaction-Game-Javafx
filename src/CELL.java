import javafx.animation.PathTransition;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration; 
 
/** 
 * CELL class represents each block in the grid and contains values which tells its maximum capacity of holding spheres and thier rotation 
 * 
 */ 
public class CELL{ 
     
    /** 
     * playerId is assigned to each CELL object which shows which player's ball is in there or who is the current owner of that cell 
     * i is the width of cell 
     * j is the height of cell 
     * x is the count of row it represents in the grid 
     * y is the count of column it represents in the grid\ 
     * mass is the current number of spheres in the cell 
     * stability is the max no. of spheres cell can hold 
     */ 
    int playerId=0,i,j,x,y,stability,mass=0; 
    /** 
     * rec is the rectangle object which is spread over whole cell for showing cell as it is  
     */ 
    Rectangle rec;//Label temp; 
    /** 
     * sphere,sphere2,sphere3 are the sphere which will be placed in the cell whenever user clicks the particular cell. Initially all the spheres are not visible 
     *  
     */ 
    Sphere sphere = new Sphere(8); 
    Sphere sphere2 = new Sphere(8); 
    Sphere sphere3 = new Sphere(8); 
    /** 
     * primarystage is the Stage object which CELL object will share with Grid and FrontPage.So that whenever user win it can go back to FrontPage by this. 
     */ 
    Stage primarystage; 
    /** 
     * PhongMaterial to color the spheres 
     */ 
    PhongMaterial pm=new PhongMaterial(); 
    static int rows,columns,flag;  // ye dekh lio 
    PathTransition t = new PathTransition(); 
    PathTransition t1 = new PathTransition(); 
    PathTransition t2 = new PathTransition(); 
    /** 
     * to reset the cell that is set its mass to be zero and make all spheres invisible 
     */ 
    public void reset() { 
         
        playerId=mass=0; 
        setSphere(mass); 
    } 
    /** 
     * depending on the value of n it will set no. of spheres visible and start their rotation 
     * if n is 0, make all spheres invisible 
     * if n is 1, make 1 sphere visible 
     * if n is 2, make 2 spheres visible and start vibration, similar is the case with n = 3 
     * also set the color of spheres according to the playerId of cell 
     * @param n n is the current mass value 
     */ 
    public void setSphere(int n) 
    {     
        pm.setDiffuseColor(Player.colorarr[playerId]); 
        sphere.setMaterial(pm);sphere2.setMaterial(pm);sphere3.setMaterial(pm); 
        if (n == 0) 
        { 
            sphere.setVisible(false); 
            sphere2.setVisible(false); 
            sphere3.setVisible(false); 
        } 
        if (n >= 1) 
            { 
                sphere.setVisible(true); 
                sphere.toFront(); 
            } 
        if (n >= 2) 
        { 
            sphere2.setVisible(true); 
            sphere.toFront(); 
            sphere2.toFront();t.setNode(sphere); 
            t.setDuration(Duration.seconds(8)); 
            t.setPath(new Circle(0.1)); 
            t.setCycleCount(t.INDEFINITE); 
            t.play();t1.setNode(sphere2); 
            t1.setDuration(Duration.seconds(8)); 
            t1.setPath(new Circle(6)); 
            t1.setCycleCount(t.INDEFINITE); 
            t1.play(); 
        } 
        if (n>=3) 
            { 
                sphere3.setVisible(true); 
                sphere.toFront(); 
                sphere2.toFront(); 
                sphere3.toFront();t.setNode(sphere2); 
                t.setDuration(Duration.seconds(2)); 
                t.setPath(new Circle(6)); 
                t.setCycleCount(t.INDEFINITE); 
                t.play(); 
                t1.setNode(sphere3); 
                t1.setDuration(Duration.seconds(2)); 
                t1.setPath(new Circle(6)); 
                t1.setCycleCount(t.INDEFINITE); 
                t1.play(); 
            } 
    } 
    /** 
     * this is constructor of CELL which sets up stability of cells according to their position in grid and also set up rectangle of cell 
     * @param x x is row number of cell in grid 
     * @param y y is column number of cell in grid 
     * @param i is the width of cell 
     * @param j is the height of cell 
     */ 
    public CELL(int x,int y,int i,int j) { 
        this.x=x; 
        this.y=y; 
        if(x%(rows-1)<1 && y%(columns-1)<1) { 
            stability=1; 
        } 
        else if(x%(rows-1)<1 || y%(columns-1)<1) 
            stability=2; 
        else {     
            stability=3; 
        } 
        rec=new Rectangle(i,j);     
        rec.setFill(Paint.valueOf("white"));     
    } 
    /** 
     * @return false if there is no overflow of spheres and true if spheres overflow and will burst by calling Grid.burstmode2 
     * if there is no overflow then it will increment mass by 1 and call setSphere on mass 
     * else it will set mass of current cell as zero and will call burstMode2 which will add cells to its neighbouring cells 
     */ 
    private boolean CellmouseListener() { 
        playerId=Grid.playerTurn; 
        pm.setDiffuseColor(Player.colorarr[playerId]); 
        sphere.setMaterial(pm);sphere2.setMaterial(pm);sphere3.setMaterial(pm); 
        int flag = 0; 
        if(mass<stability) { 
            flag = 1; 
            mass++; 
            setSphere(mass); 
            return false; 
        } 
        else 
        { 
            mass=0; 
                    setSphere(mass); 
                    Player.tempPlayerId=playerId; 
                    playerId=0; 
                    Grid.burstMode(x, y); 
                    return true; 
        }     
    } 
     
    /** 
     * Will increment totolOrbs of players and also totalorbs of current players and will call CellmouseListener then 
     * and after doing this next player turn will come by calling Grid.turnPlayer() 
     */ 
    public void masterListener() { 
        if(playerId==0 || playerId==Grid.playerTurn) { 
             
            Player.totalOrbs++; 
            Player.PlayerOrbs[Grid.playerTurn]++; 
            boolean a = CellmouseListener();//Function Named Change from PlayRecursive To mouseListener 
            Grid.turnPlayer(); 
        } 
         
    } 
     
    /** 
     * @return does not matter here. doesn't signifies anything important 
     * this function is recursively call by Grid.burstMode2() for all the neigbours of its calling cell 
     * this function in turn will behave similarly as Cellmouselistener 
     */ 
    public boolean move() { 
        if(Player.PlayerOrbs[Player.tempPlayerId]==Player.totalOrbs) { 
            if(flag==0) { 
                AlertBox.display("Game Finish", Player.tempPlayerId +"wins",primarystage); 
                flag=1; 
            } 
                return true; 
        } 
        else if(mass<stability) { 
            Player.PlayerOrbs[playerId]-=mass; 
            Player.PlayerOrbs[Player.tempPlayerId]+=mass; 
            mass++; 
             
            playerId=Player.tempPlayerId; 
            setSphere(mass); 
            return false; 
        } 
        else { 
            mass=0;; 
            Player.PlayerOrbs[playerId]-=stability; 
            playerId=0; 
            setSphere(mass); 
            Player.PlayerOrbs[Player.tempPlayerId]+=stability; 
            pm.setDiffuseColor(Player.colorarr[Player.tempPlayerId]); 
            Grid.burstMode(x, y); 
            return false; 
        } 
    } 
} 
