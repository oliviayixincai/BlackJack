import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * class MusicCollection holds all sound effects except for backgroup music.
 * 
 * Music Credits:
 * - Music effects found at https://freesound.org/
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class MusicCollection extends Music
{
    // Instance variables
    public GreenfootSound flipCardSound;
    public GreenfootSound doubleDownLoseSound;
    public GreenfootSound blackJackWinSound;
    public GreenfootSound blackJackSound;
    public GreenfootSound surrenderLoseSound;
    public GreenfootSound normalLoseSound;
    public GreenfootSound normalWinSound;
    public GreenfootSound popBeatSound;
    public GreenfootSound shuffleSound;
    public GreenfootSound pushSound;
    public GreenfootSound doubleDownWinSound;
    public GreenfootSound helpClickSound;
    public GreenfootSound chipSound;
    
    /**
     * Constructor for objects of class MusicCollection.
     * 
     */
    public MusicCollection()
    {
        flipCardSound = new GreenfootSound ("flipCard.wav");
        doubleDownLoseSound = new GreenfootSound ("doubleDownLose.wav");
        doubleDownWinSound = new GreenfootSound ("doubleDownWin.wav");
        blackJackWinSound = new GreenfootSound ("BlackJackWin.wav");
        blackJackSound = new GreenfootSound ("BlackJack.wav");
        surrenderLoseSound = new GreenfootSound ("surrenderLose.wav");
        normalLoseSound = new GreenfootSound ("normalLose.wav");
        normalWinSound = new GreenfootSound ("normalWin.wav");
        popBeatSound = new GreenfootSound ("popBeat.wav");
        shuffleSound = new GreenfootSound ("shuffle.wav");
        pushSound = new GreenfootSound ("push.wav");
        helpClickSound = new GreenfootSound ("helpClick.wav");
        chipSound = new GreenfootSound ("chipSound.wav");
        
        // Set the two sound effects volume to 100.
        helpClickSound.setVolume(100);
        chipSound.setVolume(100);
    }
}
