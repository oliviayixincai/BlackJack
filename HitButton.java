import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class HitButton represent a button for player to hit for a new card.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class HitButton extends Button
{
    // Instance variables
    private final GreenfootImage hit = new GreenfootImage("hit.png");
    private final GreenfootImage hitDisabled = new GreenfootImage("hitDisabled.png");
    // Indicate whether endabled image or disabled image to be displayed
    private boolean isEnabled = true;
    
    /**
     * This is a act method to contrlo the hit functionality and
     * determine the condition to set image.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        boolean canHit = world.canHit();
        // Swap image and isEnabled if necessary
        if (this.isEnabled && !canHit)
        {
            setImage(hitDisabled);
            this.isEnabled = false;
        }
        else if (!this.isEnabled && canHit)
        {
            setImage(hit);
            this.isEnabled = true;
        }
        
        if ( Greenfoot.mouseClicked(this)&& canHit )
        {
            // Turn a new card and play sound effect
            world.turnNewCard(true);
            world.musicCollection.flipCardSound.play();
        }
    }
}
