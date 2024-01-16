import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class BGMUp to represent an icon to turn up background music.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class BGMUp extends Music
{
    private GreenfootSound bgmSound;
    
    /**
     * Constructors for objects of class BGMUp.
     * 
     */
    public BGMUp(GreenfootSound bgmSound)
    {
        this.bgmSound = bgmSound;
    }
    
    /**
     * This is a method for player to click to turn up the background 
     * music volume to 75.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if ( Greenfoot.mouseClicked(this) )
        {
            // Set bgm volume to 75
            this.bgmSound.setVolume(75);
        }
    }
}
