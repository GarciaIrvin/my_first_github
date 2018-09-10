import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE
    private DotButton[][] board;
    private GameModel gameModel;
    private JLabel nbreofStepsLabel;
    private boolean loses=false;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
        
    // ADD YOU CODE HERE
        setTitle("Minesweeper");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        this.gameModel=gameModel;
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(12,20));
        JPanel south=new JPanel();

        board=new DotButton[20][12];
        for(int i=0;i<20;i++){
            for(int j=0;j<12;j++){
                board[i][j]=new DotButton(i,j,11);
                board[i][j].setIconNumber(11);
                board[i][j].addActionListener(gameController);
            }
        }
        for(int i=0;i<12;i++){
            for(int j=0;j<20;j++){
                panel.add(board[j][i]);
            }
        }
        JButton quit,reset;
        JRadioButton flag;
        quit= new JButton("quit");
        reset=new JButton("reset");
        flag=new JRadioButton("Flag");
        quit.addActionListener(gameController);
        reset.addActionListener(gameController);
        flag.addActionListener(gameController);
        nbreofStepsLabel=new JLabel(gameModel.toString());
        south.add(nbreofStepsLabel);
        south.add(quit);
        south.add(reset);
        south.add(flag);
        add(panel,BorderLayout.CENTER);
        add(south,BorderLayout.SOUTH);

        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);


    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
    // ADD YOU CODE HERE
        boolean mine=false;
        nbreofStepsLabel.setText(gameModel.toString());
        for(int i=0;i<20;i++){
            for(int j=0;j<12;j++){
                if(gameModel.get(i,j).isCovered() && !gameModel.get(i,j).isMined()){
                    board[i][j].setIconNumber(0);

                }
                if(gameModel.hasBeenClicked(i,j) && gameModel.get(i,j).isMined()&& gameModel.get(i,j).isCovered() && loses==false){
                    board[i][j].setIconNumber(10);
                    mine=true;
                    String[] buttons = { "Quit","Reset"};
                    JOptionPane lose=new JOptionPane();
                    int rc = lose.showOptionDialog(null, "You lost in "+gameModel.getNumberOfSteps()+" Steps \n Would you like to play again", "Game Over",lose.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
                
                    if(rc==0){
                        gameModel.uncoverAll();
                        loses=true;
                        update();
                        JOptionPane message=new JOptionPane();
                        message.showMessageDialog(this, "Click Ok to Close.");
                        this.dispose();
                    }
                    else if(rc==1){
                        gameModel.reset();
                        update();
                    } 
                }
                else if((gameModel.get(i,j).isCovered() || gameModel.hasBeenClicked(i,j)) && gameModel.get(i,j).getNeighbooringMines() == 1){
                    board[i][j].setIconNumber(1);
                }
                else if((gameModel.get(i,j).isCovered() || gameModel.hasBeenClicked(i,j)) && gameModel.get(i,j).getNeighbooringMines() == 2){
                    board[i][j].setIconNumber(2);
                }
                else if((gameModel.get(i,j).isCovered() || gameModel.hasBeenClicked(i,j)) && gameModel.get(i,j).getNeighbooringMines() == 3){
                    board[i][j].setIconNumber(3);
                }
                else if((gameModel.get(i,j).isCovered() || gameModel.hasBeenClicked(i,j)) && gameModel.get(i,j).getNeighbooringMines() == 4){
                    board[i][j].setIconNumber(4);
                }
                else if((gameModel.get(i,j).isCovered() || gameModel.hasBeenClicked(i,j)) && gameModel.get(i,j).getNeighbooringMines() == 5){
                    board[i][j].setIconNumber(5);
                }
                else if((gameModel.get(i,j).isCovered() || gameModel.hasBeenClicked(i,j)) && gameModel.get(i,j).getNeighbooringMines() == 6){
                    board[i][j].setIconNumber(6);
                }
                else if((gameModel.get(i,j).isCovered() || gameModel.hasBeenClicked(i,j)) && gameModel.get(i,j).getNeighbooringMines() == 7){
                    board[i][j].setIconNumber(7);
                }
                else if((gameModel.get(i,j).isCovered() || gameModel.hasBeenClicked(i,j)) && gameModel.get(i,j).getNeighbooringMines() == 8){
                    board[i][j].setIconNumber(8);
                }
                if(!gameModel.get(i,j).isCovered()){
                    board[i][j].setIconNumber(11);
                }
                if(gameModel.get(i,j).isMined() && gameModel.get(i,j).isCovered() && !gameModel.hasBeenClicked(i,j)){
                    board[i][j].setIconNumber(9);
                }
                if(gameModel.get(i,j).isFlag()){
                    board[i][j].setIconNumber(12);
                }
                repaint();
            }
        }
        if(gameModel.isQuit()){
            String[] buttons = { "Quit"};
            JOptionPane lose=new JOptionPane();
            int dc = lose.showOptionDialog(null, "You Quit, click close to close the game", "Game Over",lose.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
            if(dc==0){
                this.dispose();
            }

        }
        else if(gameModel.isFinished() && !mine && loses==false){
            String[] buttons = { "Quit","Play Again"};
            JOptionPane lose=new JOptionPane();
            int cc = lose.showOptionDialog(null, "Congratulations You Win in"+gameModel.getNumberOfSteps()+" Steps","You Win",lose.PLAIN_MESSAGE, 0, null, buttons, buttons[0]);
            if(cc==0){
                this.dispose();
             }
             else{
                gameModel.reset();
                update();
             }
        }


    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
        
    // ADD YOU CODE HERE
        return board[i][j].getIconNumber();

    }


}
