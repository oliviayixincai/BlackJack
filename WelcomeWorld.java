import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Starting World.
 * This is a welcome to the game which is the cover.
 *
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class WelcomeWorld extends World
{
    // instance variables
    private MusicCollection musicCollection = new MusicCollection();
    private Label startLabel;

    /**
     * Constructor for objects of class WelcomeWorld.
     * 
     */
    public WelcomeWorld()
    {    

        // Create a new world with 1323x711 cells with a cell size of 1x1 pixels.
        super(1323, 711, 1); 
        
        // Set welcome backgroup image.
        setBackground (new GreenfootImage ("welcome.png"));
        // Instantiate start label and add to world.
        startLabel = new Label ("Press Enter to Start the Game", 75, Color.WHITE);
        addObject (startLabel, getWidth() / 2, getHeight() - 30);
        // Play a blackjack sound effect once.
        musicCollection.blackJackSound.play();
    }

    public void act () {
        // Enter the game if "Enter" is pressed.
        if (Greenfoot.isKeyDown("Enter")){
            Greenfoot.setWorld (new MyWorld());
        }

    }
}
