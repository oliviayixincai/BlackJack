import greenfoot.*;
import java.util.*;

/**
 * Class Card represents a Card with type and value. It can face up or down.
 * 
 * Credits:
 * - Card class was inherited from Martin Rohwedder | https://www.greenfoot.org/scenarios/12104
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class Card extends Actor
{
    // Instance variables
    private final GreenfootImage CARD_BACK = new GreenfootImage("cards/misc/blue_back.png");
    private boolean front;
    private String type;
    private String value;
    GreenfootImage cardImage;
    
    private int rank;
    private int suit;
    
    /**
     * Constructors for objects of class Card.
     * 
     */
    public Card ( String cardType, String cardValue)
    {
        // Initialize variables 
        this.type = cardType;
        this.value = cardValue;
        this.front = false;
        // Import the imaged of the cards.
        cardImage = new GreenfootImage("cards/" + this.type + "/" + this.value + "_of_" + this.type + ".png");
        
        cardImage.scale(100, 145);
        CARD_BACK.scale(100, 145);
        // Card face down by default
        setImage(CARD_BACK);
    }
    
    /**
     * Method to get the card value.
     */
    public String getValue()
    {
        return this.value;
    }
    
    /**
     * Method to check if a card is facing up.
     */
    public boolean isFront()
    {
        return this.front;
    }
    
    /**
     * Method to turn a card facing up.
     */
    public void frontCard()
    {
        this.front = true;
        GreenfootImage image = getImage();
        image.clear();
        image.drawImage(cardImage, 0, 0);
    }
}
