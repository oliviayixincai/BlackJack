import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class HelpIcon is to represent an icon for player to click and display game rules.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class HelpIcon extends Actor
{
    // Instance variable
    private MusicCollection musicCollection = new MusicCollection();
    
    /**
     * This is a method for player to click to enter the help world.
     */
    public void act()
    {
        if (Greenfoot.mousePressed(this))
        {
            // Play the click sound effect.
            this.musicCollection.helpClickSound.play();
            MyWorld myWorld = (MyWorld) getWorld();
            // Instantiate a new help world and switch to it
            Greenfoot.setWorld(new HelpWorld(myWorld));
        } 
    }
}
