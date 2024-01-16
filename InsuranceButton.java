import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class InsuranceButton represents a button for player buy insurance.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class InsuranceButton extends Button
{
    // Instance variables
    private final GreenfootImage image = new GreenfootImage("insurance.png");
    private final GreenfootImage imageHide = new GreenfootImage("insurance.png");
    // Indicate whether endabled image or disabled image to be displayed
    private boolean isEnabled = true;
    
    /**
     * This is a act method to contrlo the insurance functionality and 
     * determine the condition to set image.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        boolean canInsurance = world.canInsurance();
        
        // Swap image and isEnabled if necessary
        if (this.isEnabled && !canInsurance)
        {
            setImage(imageHide);
            this.isEnabled = false;
        }
        else if (!this.isEnabled && canInsurance)
        {
            setImage(image);
            this.isEnabled = true;
        }
        
        if ( Greenfoot.mouseClicked(this) && canInsurance )
        {
            // Call insurance in world to buy insurance
            world.insurance();
        }
    }
}
