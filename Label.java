import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Label represents a text label on the world.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class Label extends Actor
{
    // Instance variables
    private String labelText;
    private int labelSize;
    private Color labelColor = Color.YELLOW;
    
    /**
     * Constructors for objects of class Label.
     * 
     */
    // Constructor taking label text 
    public Label( String labelText)
    {
        this.labelText = labelText;
        // Set the size of the letters.
        this.labelSize = 50;
        // Putt image.
        putLabelImage();
    }
    
    // Constructor taking label text and label size
    public Label( String labelText, int labelSize)
    {
        this.labelText = labelText;
        // Set the size of the letters.
        this.labelSize = labelSize;
        // Putt image.
        putLabelImage();
    }
    
    // Constructor taking label text, label size and label color
    public Label( String labelText, int labelSize, Color labelColor)
    {
        this.labelText = labelText;
        // Set the size of the letters.
        this.labelSize = labelSize;
        // Set the clours of the letters.
        this.labelColor = labelColor;
        // Putt image.
        putLabelImage();
    }
    
    /**
     * Method to set the text to image
     */
    private void putLabelImage()
    {
        setImage( new GreenfootImage( this.labelText, this.labelSize, this.labelColor, new Color(0,0,0,0), Color.BLACK));
    }
    
    /**
     * Method to update the label text
     */
    public void updateLabel( String labelText)
    {
        this.labelText = labelText;
        putLabelImage();
    }
}
