import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class BGMToggle represents an icon to toggle background music.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class BGMToggle extends Music
{
    // Import the images.
    private final GreenfootImage bgmOn = new GreenfootImage("bgmOn.png");
    private final GreenfootImage bgmOff = new GreenfootImage("bgmOff.png");
    // Variable.
    private GreenfootSound bgmSound;
    
    /**
     * Constructors for objects of class BGMToggle.
     * 
     */
    public BGMToggle(GreenfootSound bgmSound)
    {
        this.bgmSound = bgmSound;
        bgmSound.setVolume(50);
        bgmSound.playLoop();
        setImage(bgmOn);
    }
    
    /**
     * This is a method for player to turn on or turn off the 
     * background music. It will change the inmage to show the situation.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        
        if ( Greenfoot.mouseClicked(this) )
        {
            // Stop bgm and update image if bgm is playing
            if (this.bgmSound.isPlaying() )
            {
                this.bgmSound.stop();
                setImage(bgmOff);
            }
            // Start to play bgm in loop if bgm is not playing
            // and set the volume equal to 50.
            else 
            {
                this.bgmSound.playLoop();
                setImage(bgmOn);
                this.bgmSound.setVolume(50);
            }
        }
    }
}
