import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Class Person is a generic class to represent a person.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class Person
{
    public int score;
    public boolean isBlackJack;
    public ArrayList<Card> cards;
    public boolean isDealer;
    public Label scoreLabel;
    
    public String name;
    
    /**
     * Constructors for objects of class Person.
     * 
     */
    public Person(boolean isDealer )
    {
        this.cards = cards;
        this.isDealer = isDealer;
        this.score = 0;
        this.cards = new ArrayList<Card>();
        this.isBlackJack = false;
        
        this.scoreLabel = new Label(this.name + "\nScore: 0");
    }
    
    /**
     * Method to update score label.
     */
    public void updateScoreLable()
    {
        this.scoreLabel.updateLabel(this.name + "\nScore: " + this.score);
    }
    
    /**
     * Method to reset a person's parameters.
     */
    public void reset()
    {
        this.cards.clear();
        this.score = 0;
        this.isBlackJack = false;
        updateScoreLable();
    }
    
    /**
     * Method to take a card.
     * 
     * @param Card card to be taken by person.
     */
    public void takeCard(Card card)
    {
        this.cards.add(card);
        
        updateValues();
    }
    
    /**
     * Method to update values of a person's attributes.
     */
    public void updateValues()
    {
        // Initial the scores and the ace.
        int score = 0;
        int aces = 0;
        // Use a for loop to check and calculate every cards values.
        for (int i = 0; i < this.cards.size(); i++)
        {
            Card card = this.cards.get(i);
            if (card.isFront())
            {
                // If it is ace, then determine its value according to the total scores.
                if ( card.getValue().equals("ace") )
                {
                    aces++;
                }
                else if ( card.getValue() == "2" )
                {
                    score += 2;
                }
                else if ( card.getValue() == "3" )
                {
                    score += 3;
                }
                else if ( card.getValue() == "4" )
                {
                    score += 4;
                }
                else if ( card.getValue() == "5" )
                {
                    score += 5;
                }
                else if ( card.getValue() == "6" )
                {
                    score += 6;
                }
                else if ( card.getValue() == "7" )
                {
                    score += 7;
                }
                else if ( card.getValue() == "8" )
                {
                    score += 8;
                }
                else if ( card.getValue() == "9" )
                {
                    score += 9;
                }
                else if ( card.getValue() == "10" )
                {
                    score += 10;
                }
                else if ( card.getValue().equals("jack" ))
                {
                    score += 10;
                }
                else if ( card.getValue().equals("queen" ))
                {
                    score += 10;
                }
                else if ( card.getValue().equals("king" ))
                {
                    score += 10;
                }
            }
        }
        
        // If the score plus 11 is busting which means above 21,
        // then set its value eqal to 1.
        for (int i = 0; i < aces; i++)
        {
            if ( (score + 11) <= 21 )
            {
                score += 11;
            }
            else
            {
                score += 1;
            }
        }
        this.score = score;
        // Determine whether is blackjack through the 
        // ace number, the total score and the card size.
        if(aces == 1 && this.score == 21 && this.cards.size() == 2)
        {
            this.isBlackJack = true;
        }
        // Update the score label through calculating.
        updateScoreLable();
    }
}
