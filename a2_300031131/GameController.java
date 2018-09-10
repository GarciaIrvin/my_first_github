import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel gameModel;
    private GameView gameView;


    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {
        gameModel=new GameModel(width,height,numberOfMines);
        gameView=new GameView(gameModel,this);
        gameModel.reset();

    // ADD YOU CODE HERE

    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
    // ADD YOU CODE HERE
        if(e.getSource() instanceof DotButton){
            DotButton src= (DotButton) e.getSource();
            int x=src.getColumn();
            int y=src.getRow();
            if(!gameModel.get(x,y).isCovered()){
                gameModel.uncover(x,y);
                gameModel.step();
            }
            gameModel.get(x,y).click();
            if(gameModel.isBlank(x,y) && !gameModel.isMined(x,y)){
                clearZone(gameModel.get(x,y));
            }
            gameView.update();
        }
        if(e.getActionCommand().equals("reset")){
            gameModel.reset();
            gameView.update();
        }
        if(e.getActionCommand().equals("quit")){
            gameModel.setQuit();
            gameModel.uncoverAll();
            gameView.update();
        }

    }

    /**
     * resets the game
     */
    private void reset(){
        gameModel.reset();
        gameView.update();

    // ADD YOU CODE HERE

    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

    // ADD YOU CODE HERE
        gameModel.uncover(width,heigth);

    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {
        int x,y;
        int xmin,xmax,ymin,ymax;
        int width,heigth;
        width=gameModel.getWidth();
        heigth=gameModel.getHeigth();
        
        // if(gameModel.isBlank(x,y)){
        //     for(int i=xmin;i<xmax;i++){
        //         for(int j=ymin;j<ymax;j++){
        //             gameModel.uncover(i,j);
        //         }
        //     }

        // }

    // ADD YOU CODE HERE
        GenericArrayStack<DotInfo> stack;
        stack= new GenericArrayStack<DotInfo>(8);
        stack.push(initialDot);
        DotInfo temp;
        while(!stack.isEmpty()){
            temp=stack.pop();
            x=temp.getX();
            y=temp.getY();
            xmin=x-1;
            xmax=x+1;
            ymin=y-1;
            ymax=y+1;
            if(x==width-1){
                xmax=x;
            }
            else if(x==0){
                xmin=0;
            }
            if(y==heigth-1){
                ymax=y;
            }
            else if(y==0){
                ymin=0;
            }




            for(int i=xmin;i<xmax+1;i++){
                for(int j=ymin;j<ymax+1;j++){
                    if(!gameModel.isCovered(i,j)){
                        gameModel.uncover(i,j);
                        if(gameModel.isBlank(i,j)){
                            stack.push(gameModel.get(i,j));
                        }
                    }
                }
            }
        }


    }



}
