import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {
    private Random generator=new Random();
    private int widthOfGame;
    private int heigthOfGame;
    private int numberOfMines;
    private int numberUncovered;
    private int numberofSteps;
    private int numberofFlags;
    private boolean quit;
    private DotInfo[][] model;


     // ADD YOUR INSTANCE VARIABLES HERE

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        heigthOfGame=heigth;
        widthOfGame=width;
        model=new DotInfo[widthOfGame][heigthOfGame];
        for(int i=0;i<widthOfGame;i++){
            for(int j=0;j<heigthOfGame;j++){
                model[i][j]=new DotInfo(i,j);
            }
        }
        this.numberOfMines=numberOfMines;
        numberofFlags=numberOfMines;
        numberofSteps=0;
        quit=false;
        
    // ADD YOU CODE HERE

    }


 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){
        numberofFlags=numberOfMines;
        numberofSteps=0;
        model=new DotInfo[widthOfGame][heigthOfGame];
        for(int i=0;i<widthOfGame;i++){
            for(int j=0;j<heigthOfGame;j++){
                model[i][j]=new DotInfo(i,j);
            }
        }

        int a,b;
        for(int i=0;i<numberOfMines;i++){
            a=generator.nextInt(widthOfGame-1);
            b=generator.nextInt(heigthOfGame-1);
            if(model[a][b].isMined()){
               a=generator.nextInt(widthOfGame-1);
                b=generator.nextInt(heigthOfGame-1); 
            }
            model[a][b].setMined();
            int rangexmin=a-1;
            int rangexmax=a+1;
            int rangeymin=b-1;
            int rangeymax=b+1;
            //System.out.println("x:"+a+" y:"+b);
            if(a==widthOfGame-1){
                rangexmax=a;
            }
            else if(a==0){
                rangexmin=0;
            }
            if(b==heigthOfGame-1){
                rangeymax=b;
            }
            else if(b==0){
                rangeymin=0;
            }
            for(int k=rangexmin;k<rangexmax+1;k++){
                for(int j=rangeymin;j<rangeymax+1;j++){
                    if(!model[a][b].equals(model[k][j])){
                        model[k][j].incrementNeighbor();
                    }
                }
            }


        }
        
    // ADD YOU CODE HERE

    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        return heigthOfGame;
        
    // ADD YOU CODE HERE

    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
        return widthOfGame;
        
    // ADD YOU CODE HERE

    }
    /**
     * Setter method for quit
     * 
     * 
     */
    public void setQuit(){
        quit=true;
    }
    /**
     * Getter method for quit
     * 
     * @return the value of quit
     */ 
    public boolean isQuit(){
        return quit;
    } 



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        return model[i][j].isMined();
        
    // ADD YOU CODE HERE

    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        
    // ADD YOU CODE HERE
        return model[i][j].hasBeenClicked();

    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        return(model[i][j].getNeighbooringMines()==0);
        
    // ADD YOU CODE HERE

    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
        
    // ADD YOU CODE HERE
        return model[i][j].isCovered();

    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        
    // ADD YOU CODE HERE
        return model[i][j].getNeighbooringMines();

    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        
    // ADD YOU CODE HERE
        model[i][j].uncover();

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        
    // ADD YOU CODE HERE
        model[i][j].click();

    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE
        for(int i=0;i<widthOfGame;i++){
            for(int j=0;j<heigthOfGame;j++){
                if(model[i][j].isFlag()){
                    model[i][j].setFlag();
                }
                model[i][j].uncover();
            }
        }

    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
    // ADD YOU CODE HERE
        return numberofSteps;

    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        
    // ADD YOU CODE HERE
        return model[i][j];

    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
        numberofSteps++;
    // ADD YOU CODE HERE

    }
    /**
     * The method updates the number of flags. 
     * 
     */
    public void decrementFlag(){
        numberofFlags--;
    }
    /**
     * The method updates the number of flags. 
     * 
     */
    public void incrementFlag(){
        numberofFlags++;
    }
    /**
     * The method updates the number of flags. 
     * @return numberofFlags
     */
    public int getFlag(){
        return numberofFlags;
    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        
    // ADD YOU CODE HERE
        // int total, nonmined;
        // total=heigthOfGame*widthOfGame;
        // nonmined=total- numberOfMines;
        boolean val=true;
        int counter=0;
        for(int i=0;i<widthOfGame;i++){
            for(int j=0;j<heigthOfGame;j++){
                if(model[i][j].isMined()){
                    if(model[i][j].isFlag()){
                        counter++;
                    }
                }
            }
        }
        if(counter==numberOfMines){
            return true;
        }
        for(int i=0;i<widthOfGame;i++){
            for(int j=0;j<heigthOfGame;j++){
                if(model[i][j].getNeighbooringMines()>0){
                    if(model[i][j].isCovered()){
                        val=true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return val;

    }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        
    // ADD YOU CODE HERE
        return "Number of Steps: " +numberofSteps+" Number of Flags: "+numberofFlags;

    }
}
