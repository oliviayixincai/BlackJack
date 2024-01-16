import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class SurrenderButton represents a button for player to surrender
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class SurrenderButton extends Button
{
    /**
     * Call the surrender if the player clicked it.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        
        if ( Greenfoot.mouseClicked(this) && world.canClickBasicButton() )
        {
            // Call surrender method in world to surrender
            world.surrender();
        }
    }
}
