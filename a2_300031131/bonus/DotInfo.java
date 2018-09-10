
/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbooring mines...) 
 * at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotInfo {
    private boolean covered;
    private boolean mined;
    private boolean flag;
    private int neighbooringMines;
    private boolean wasClicked;
    private int x;
    private int y;

     // ADD YOUR INSTANCE VARIABLES HERE


    /**
     * Constructor, used to initialize the instance variables
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public DotInfo(int x, int y){
        this.x=x;
        this.y=y;
        neighbooringMines=0;
        flag=false;
        covered=false;

    // ADD YOU CODE HERE

    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){
        return x;

    // ADD YOU CODE HERE

    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){
        return y;

    // ADD YOU CODE HERE

    }
    
 
    /**
     * Setter for mined
     */
    public void setMined() {

    // ADD YOU CODE HERE
        mined=true;
    }
    /**
     * Setter for flag
     */
    public void setFlag(){
        if(flag==true){
            flag=false;
        }
        else if(flag==false){
            flag=true;
        }
    }
    /**
     * Getter for flag
     *@return flag
     */
    public boolean isFlag(){
        return flag;
    }

    /**
     * Getter for mined
     *
     * @return mined
     */
    public boolean isMined() {

    // ADD YOU CODE HERE
        return mined;

    }


    /**
     * Setter for covered
     */
    public void uncover() {

    // ADD YOU CODE HERE
        covered=true;

    }

    /**
     * Getter for covered
     *
     * @return covered
     */
    public boolean isCovered(){

    // ADD YOU CODE HERE
        return covered;

    }



    /**
     * Setter for wasClicked
     */
    public void click() {

    // ADD YOU CODE HERE
        wasClicked=true;

    }


    /**
     * Getter for wasClicked
     *
     * @return wasClicked
     */
    public boolean hasBeenClicked() {

    // ADD YOU CODE HERE
        return wasClicked;

    }


    /**
     * Setter for neighbooringMines
     *
     * @param neighbooringMines
     *          number of neighbooring mines
     */
    public void setNeighbooringMines(int neighbooringMines) {

    // ADD YOU CODE HERE
        this.neighbooringMines=neighbooringMines;
    }
    /**
     * Setter for neighbooringMines
     *
     * increments neighbooringMines
     *          
     */
    public void incrementNeighbor() {
        neighbooringMines++;
    }


    /**
     * Check Equality
     *
     * @param other DotInfo
     * @return boolean 
     */
    public boolean equals(DotInfo other){
        return (x==other.x && y==other.y);
    }


    /**
     * Get for neighbooringMines
     *
     * @return neighbooringMines
     */
    public int getNeighbooringMines() {

    // ADD YOU CODE HERE
        return neighbooringMines;

    }

 }
