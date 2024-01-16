import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class StandButton represents a button for player to stand.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class StandButton extends Button
{
    /**
     * Act - do whatever the Stand wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        
        if ( Greenfoot.mouseClicked(this) && world.canClickBasicButton() )
        {
            // Call switchTurn in world if player choose to stand
            world.switchTurn();
        }
    }
}
