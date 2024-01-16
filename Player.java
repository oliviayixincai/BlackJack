import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * class Dealer is a generic class to represent a Player.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class Player extends Person
{
    public int chip;
    public Label chipLabel;
    
    /**
     * Constructors for objects of class Player.
     * 
     */
    public Player()
    {
        super(false);

        this.name = "Player";
        this.chip = MyWorld.PLAYER_INIT_CHIP;
        this.chipLabel = new Label("Chip: " + this.chip);
    }
    
    /**
     * Mehtod to update the chip.
     * 
     * @param int numumber of chip to be updated to player.
     */
    public void updateChip(int num)
    {
        this.chip += num;
        updateChipLabel();
    }
    
    /**
     * Method to update the chip label
     */
    private void updateChipLabel()
    {
        this.chipLabel.updateLabel("Chip: " + this.chip);
    }
}
