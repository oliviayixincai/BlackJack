import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class DoubleDownButton represents a button for player to doubledown.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class DoubleDownButton extends Button
{
    // Instance variables
    private final GreenfootImage doubleDown = new GreenfootImage("doubledown.png");
    private final GreenfootImage doubleDownDisabled = new GreenfootImage("doubledownDisabled.png");
    // Indicate whether endabled image or disabled image to be displayed
    private boolean isEnabled = true;
    
    /**
     * This is a act method to control the double down functionality 
     * and determine the condition to set image.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        boolean canDoubleDown = world.canDoubleDown();
        // Swap image and isEnabled if necessary
        if (this.isEnabled && !canDoubleDown)
        {
            setImage(doubleDownDisabled);
            this.isEnabled = false;
        }
        else if (!this.isEnabled && canDoubleDown)
        {
            setImage(doubleDown);
            this.isEnabled = true;
        }

        // Call doubledown method in world if button clicked and can doubledown
        if ( Greenfoot.mouseClicked(this) && canDoubleDown )
        {
            world.doubleDown();
        }
    }
}
