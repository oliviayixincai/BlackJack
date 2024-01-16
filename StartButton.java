import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class StartButton is a class for player to click to remove
 * all the game actors and initialize all the statistical data
 * and set up a new game to play.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class StartButton extends Actor
{
    /**
     * Act - do whatever the Start wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        
        if ( Greenfoot.mouseClicked(this) )
        {
            // Remove all the game actors.
            world.removeGameActors();
            // Initialize all the satistical data.
            world.initStats();
            // Set up a new game.
            world.setupNewGame();
        }
    }
}
