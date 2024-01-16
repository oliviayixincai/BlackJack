import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * class Dealer is a generic class to represent a dealer.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class Dealer extends Person
{
    /**
     * Constructors for objects of class Dealer.
     * 
     */
    public Dealer()
    {
        super(true);
        this.name = "Dealer";
    }
    
    /**
     * Method to reveal the card that is facing down from the dealer.
     */
    public void revealCard()
    {
        Card card = this.cards.get(1);
        if (!card.isFront())
        {
            card.frontCard();
            updateValues();
        }
    }
    
    /**
     * Act - do whatever the Dealer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
}
