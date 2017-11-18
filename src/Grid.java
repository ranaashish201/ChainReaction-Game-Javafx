import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage; 
/**
 * @author Deepak
 *
 */
/**
 * @author Deepak
 *
 */
/**
 * @author Deepak
 *
 */
public class Grid extends Application { 
	/**
	 * Total No. of valid moves in the Grid
	 */
    public static int totalMoves=0; 
    /**
     * Current player turn value
     */
    public static int playerTurn=1;
    /**
     * Columns in the Grid class
     */
    
    public static int columns;
    /**
     * Rows in the Grid Class
     */
    public static int rows; 
    /**
     * CELL object array of the grid 
     */
    private static CELL cells[][]; 
    
    static MenuBar menuBar; 
    static Menu optionsMenu; 
    /**
     * Button for Undo		
     */
    static MenuItem UndoButton; 
    static MenuItem exitMenuItem; 
    static boolean UndoAvailable=true; 
    /**
     * Stage for Grid 
     */
    static Stage PrimaryStage; 
    BorderPane grid; 
    /**
     * Reference to the Saved List
     */
    ArrayList<Cordinates> resumeList; 
    boolean resumeAvailable=false;
    /**
     * function to be call by Undo for resetting values to solve logical errors
     */
    public void reset() { 
        totalMoves=0; 
        playerTurn=1; 
        for(int i=0;i<rows;i++) { 
            for(int j=0;j<columns;j++) { 
                cells[i][j].reset(); 
            } 
        } 
    } 
    /**
     * 
     * @param n- number of players
     * @param hdGrid-Different size grid
     * @param resumeList-List deserialized bu DaveState class
     */
    public Grid(int n,boolean hdGrid,ArrayList<Cordinates> resumeList) 
    { 
        this.resumeList=resumeList; 
        Player.setPlayer(n); 
        if (hdGrid) 
        { 
            rows = 10; 
            columns = 15; 
        } 
        else 
        { 
            columns=9;rows=6; 
        } 
    } 
    /**
     * 
     * @param n- number of players
     * @param hdGrid-Different size grid
     */
    public Grid(int n,boolean hdGrid) 
    { 
        resumeList=new ArrayList<Cordinates>(); 
        Player.setPlayer(n); 
        if (hdGrid) 
        { 
            rows = 10; 
            columns = 15; 
        } 
        else 
        { 
            columns=9;rows=6; 
        } 
    } 
    /**
     * Function for showing Window Stage
     * @param primaryStage -Grid class Stage
     */
    public void start(Stage primaryStage) throws Exception { 
        PrimaryStage = primaryStage; 
        menuBar = new MenuBar(); 
        optionsMenu = new Menu("Options"); 
        UndoButton = new MenuItem("Undo"); 
        exitMenuItem = new MenuItem("Exit"); 
        optionsMenu.getItems().addAll(UndoButton, exitMenuItem); 
        menuBar.getMenus().add(optionsMenu); 
        menuBar.setPrefHeight(30); 
        exitMenuItem.setOnAction(e->{System.out.print("exit");}); 
        UndoButton.setOnAction(e->{ 
//            if (UndoAvailable == true) { 
                Player.totalOrbs--; 
                Player.PlayerOrbs[Grid.playerTurn]--; 
                reset(); 
//                Player.totalUndo[Grid.playerTurn]--; 
//                if (Player.totalUndo[Grid.playerTurn] == 0) { 
//                    UndoButton.setDisable(true); 
//                } 
                     
                 
                int tm = resumeList.size(); 
                System.out.println(tm+"total move"); 
                for (int i = 0; i < tm-1; i++) { 
                    if(!resumeList.get(i).isUndoCordinate()) { 
                    cells[(int) Math.floor(resumeList.get(i).getX())][(int) Math.floor(resumeList.get(i).getY())] 
                            .masterListener(); 
                    } 
                    else {     
//                        Player.totalOrbs--; 
//                        Player.PlayerOrbs[Grid.playerTurn]--; 
//                        Player.totalUndo[Grid.playerTurn]--; 
//                        if (Player.totalUndo[Grid.playerTurn] == 0) { 
//                            UndoButton.setDisable(true); 
//                        } 
                    } 
                } 
                resumeList.get(resumeList.size()-1).setUndoCordinate(false); 
                UndoAvailable = false; 
        }); 
        grid = new BorderPane(); 
        grid.setBottom(menuBar); 
//        SaveState.saveGame(); 
        Player.validity(); 
//        grid = new BorderPane(); 
        cells=new CELL[rows][columns]; 
        Scene scene=new Scene(grid, 40*rows,40*columns+30); 
        System.out.println(scene.getHeight()); 
        runbeforeStart(); 
        new SaveState(); 
        SaveState.list=resumeList; 
        if(resumeAvailable==true) { 
            int tm=resumeList.size(); 
            for(int i=0;i<tm;i++) { 
                cells[(int) Math.floor(resumeList.get(i).getX())][(int) Math.floor(resumeList.get(i).getY())].masterListener(); 
            } 
        } 
         
        grid.setOnMouseClicked(new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
                cells[(int) Math.floor(e.getSceneX()/40)][(int) Math.floor(e.getSceneY()/40)].masterListener(); 
                resumeList.add(new Cordinates((int)e.getSceneX()/40,(int)e.getSceneY()/40)); 
                SaveState.list=resumeList; 
                SaveState.saveGame(); 
             
            } 
             
        } 
 
            ); 
 
        for (int i = 1 ; i <= rows ; i++) { 
            for (int j = 1; j <= columns; j++) { 
                grid.getChildren().addAll(cells[i-1][j-1].rec,/*cells[i-1][j-1].temp,*/cells[i-1][j-1].sphere,cells[i-1][j-1].sphere2,cells[i-1][j-1].sphere3); 
 
            } 
        } 
        scene.setFill(Color.BLUE); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    } 
    /**
     * Main function of the class for iniating game Grid
     * @param args-syntax
     */
    public static void main(String[] args) { 
    launch(args); 
    } 
    private void UndoFuntion() { 
        Player.totalOrbs--; 
 
        Player.PlayerOrbs[Grid.playerTurn]--; 
 
        reset(); 
 
        int tm = resumeList.size(); 
 
        System.out.println(tm + " total move"); 
 
        int done = 0; 
 
        for (int i = 0; i < tm - 1; i++) { 
 
            System.out.println(resumeList.get(i).getX() + " " + resumeList.get(i).getY() + " " 
                    + resumeList.get(i).isUndoCordinate()); 
 
            if (resumeList.get(i).isUndoCordinate()) { 
 
                cells[(int) Math.floor(resumeList.get(i).getX())][(int) Math.floor(resumeList.get(i).getY())] 
                        .masterListener(); 
 
                done++; 
 
            } 
 
            else { 
 
            } 
 
        } 
 
        resumeList.get(resumeList.size() - 1).setUndoCordinate(false); 
 
        UndoAvailable = false; 
     
    } 
    /** 
     * this will set up the cells[][] array by creating cells object and setting the x and y layout of them and their spheres and rectangles also it will share it's stage with cells[i][j] objects 
     */ 
   /**
    *  Main function for Making Grid and filling it with object
    * 
    */
    
    private static void runbeforeStart() { 
        CELL.rows=rows; 
        CELL.columns=columns; 
        for(int i=0;i<rows;i++) { 
            for(int j=0;j<columns;j++) { 
                cells[i][j]=new CELL(i,j,38,38); 
                cells[i][j].rec.setLayoutX((i)*40); 
                cells[i][j].rec.setLayoutY((j)*40); 
                cells[i][j].rec.setFill(Color.BLACK); 
                cells[i][j].sphere.setLayoutX(i*40+20); 
                cells[i][j].sphere.setLayoutY(j*40+20); 
                cells[i][j].sphere2.setLayoutX(i*40+22); 
                cells[i][j].sphere2.setLayoutY(j*40+22); 
                cells[i][j].sphere3.setLayoutX(i*40+14); 
                cells[i][j].sphere3.setLayoutY(j*40+24); 
                cells[i][j].sphere.setVisible(false); 
                cells[i][j].sphere2.setVisible(false); 
                cells[i][j].sphere3.setVisible(false); 
                cells[i][j].primarystage = PrimaryStage; 
            } 
        } 
             
    } 
     
    /** 
     * @param x x is the row number of cell which called it 
     * @param y y is the row number of cell which called it 
     * it will call move method of CELL class on all the possible neighbors in all possible directions(according to row and column value) 
     * On whom to call move method is decided by row and column value. e.g. if cell is in corner then only call the two possible cells and so on 
     */ 
    public static void burstMode(int x,int y) { 
        if(Player.PlayerOrbs[Player.tempPlayerId]==Player.totalOrbs) {//recursion stopping condition 
            return; 
        } 
        else if(x%(rows-1)==0 && y%(columns-1)==0) {//corner cells 
            if(x==0) { 
                cells[x+1][y].move();//Cell just right 
                if(y==0)//checking for the left =most column 
                    cells[x][y+1].move(); 
                else 
                    cells[x][y-1].move(); 
            } 
            else { 
                cells[x-1][y].move(); 
                if(y==0) 
                    cells[x][y+1].move(); 
                else 
                    cells[x][y-1].move(); 
            } 
             
        } 
        else if(x%(rows-1)==0 || y%(columns-1)==0) {//cells in side rows or in side columns 
            if(x%(rows-1)==0) { 
                if(x==0) { 
                    cells[x+1][y].move(); 
                    cells[x][y-1].move(); 
                    cells[x][y+1].move(); 
                } 
                else { 
                    cells[x-1][y].move(); 
                    cells[x][y-1].move(); 
                    cells[x][y+1].move(); 
                } 
            } 
            else { 
                if(y==0) { 
                    cells[x-1][y].move(); 
                    cells[x+1][y].move(); 
                    cells[x][y+1].move(); 
                } 
                else { 
                    cells[x-1][y].move(); 
                    cells[x+1][y].move(); 
                    cells[x][y-1].move(); 
                } 
            } 
        } 
        else {//cells in the middle 
            cells[x-1][y].move(); 
            cells[x+1][y].move(); 
            cells[x][y-1].move(); 
            cells[x][y+1].move(); 
        } 
    } 
 
    /** 
     * It will change the current player turn to give another player chance to play aage ka dekh lio mujhe nahi samajha aaya  
     */ 
    public static void turnPlayer() { 
        totalMoves++; 
 
        if (Player.PlayerOrbs[Player.tempPlayerId] == Player.totalOrbs && CELL.flag == 0) {// flag should be 
            CELL.flag = 1; 
            AlertBox.display("Game Finish", Player.tempPlayerId + "wins",PrimaryStage); 
        } 
        if (totalMoves > Player.p) 
            Player.checkContinuity(); 
        for (int i = 1; i <= Player.p; i++) { 
            if (Player.canContinue[(i + Grid.playerTurn) % (Player.p + 1)] == true) { 
                Grid.playerTurn = (i + Grid.playerTurn) % (Player.p + 1); 
                break; 
            } 
        } 
 
        UndoAvailable = false; 
        } 
} 
