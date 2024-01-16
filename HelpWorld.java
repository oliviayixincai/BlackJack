import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class HelpWorld is to display the game rules.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class HelpWorld extends World
{
    // instance variables
    private Label gameRules = new Label(
        "The player's goal is to keep the cards close to, but not greater \n" +
        "than 21. The king, queen, jack and ten count as ten points; An ace \n" +
        "can count as either 1 or an 11. If you get A and T (in any order), \n" +
        "you get blackjack. If the player is blackjack and the dealer is other, \n" +
        "the player wins 1.5 times the bet; If both the dealer and the player \n" +
        "are  blackjack, they push. If only the dealer is blackjack, the dealer \n" +
        "wins. If the dealer's card is A, the player can buy Insurance, which \n" +
        "is half of the bet and non-refundable if the dealer has no blackjack. \n" +
        "If the dealer has blackjack, the player who bought the insurance gets \n" +
        "1.5 times the bet. Click “Hit” to get one more card. Click “Stand” to \n" +
        "keep the cards. Click“double down” to double the bets. If you surrender. \n" +
        "You will lose half of your bet right away.", 20, Color.BLACK);
    //Declare objects
    private HelpBoard helpBoard = new HelpBoard();
    private HelpCloseIcon helpCloseIcon;
    
    /**
     * Constructor for objects of class HelpWorld.
     * 
     */
    public HelpWorld(MyWorld myWorld)
    {    
        // Create a new world with 1323x744 cells with a cell size of 1x1 pixels.
        super(1323, 744, 1);
        
        // instantiate helpCloseIcon variable
        this.helpCloseIcon = new HelpCloseIcon(myWorld);
        
        // add objects to the help world
        addObject(this.helpBoard, getWidth() / 2, getHeight() / 2);
        addObject(this.gameRules, getWidth() / 2, getHeight() / 2);
        addObject(this.helpCloseIcon, getWidth() - 60, 120);
    }
}
