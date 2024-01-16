import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class TryAgainButton is a class for player to use to 
 * start a new game when the current game is over.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class TryAgainButton extends Actor
{
    /**
     * Act - do whatever the TryAgain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        
        // Only the player clicked and the game is over, call methods.
        if ( Greenfoot.mouseClicked(this) && world.checkGameOver() )
        {
            // Romove all the game actors.
            world.removeGameActors();
            // Set up a new game.
            world.setupNewGame();
        }
    }
}
