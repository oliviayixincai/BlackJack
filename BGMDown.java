import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class BGMDown to represent an icon to turn down background music.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class BGMDown extends Music
{
    private GreenfootSound bgmSound;
    
    /**
     * Constructors for objects of class BGMDown.
     * 
     */
    public BGMDown(GreenfootSound bgmSound)
    {
        this.bgmSound = bgmSound;
    }
    
    /**
     * This is a method for player to turn down the 
     * background music volume to 25.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        
        if ( Greenfoot.mouseClicked(this) )
        {
            this.bgmSound.setVolume(25);
        }
    }
}
