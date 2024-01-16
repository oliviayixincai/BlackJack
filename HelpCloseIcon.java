import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class HelpCloseIcon represents an icon that can be clicked to close the help world.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class HelpCloseIcon extends Actor
{
    // Instance variables
    // Variable to reference the world for the game play
    private MyWorld myWorld;
    private MusicCollection musicCollection = new MusicCollection();
    
    /**
     * Constructors for objects of class HelpCloseIcon.
     * 
     */
    public HelpCloseIcon(MyWorld myWorld)
    {
        this.myWorld = myWorld;
    }
    
    /**
     * This is a method to close the help board and back to the game world.
     */
    public void act()
    {
        if (Greenfoot.mousePressed(this))
        {
            this.musicCollection.helpClickSound.play();
            // Instantiate a new MyWorld if null
            if(myWorld == null)
            {
                Greenfoot.setWorld (new MyWorld());
            }
            // Switch to back to game
            else
            {
                Greenfoot.setWorld(myWorld);
            }
        } 
    }
}
