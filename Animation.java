import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Movement allows to move an object from start location to end location.
 * 
 * The Movement is an actor. It moves an actor by removing an object from world
 * and add it back to the world at a new location. The new location calculated
 * based on the count down progress. The count down will progress on each actor
 * method call.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class Animation extends Actor
{
    // instance variables
    // Total count down for a movement
    final static int COUNT_DOWN_TOTAL = 20;
    // Current count down progress
    public int countDown;
    // Start and end locations
    public int xStart;
    public int yStart;
    public int xEnd;
    public int yEnd;
    // Object that being moved
    public Actor actor;
    // Whether to remove the object after movement finishes
    public boolean removeOnFinish;
    
    /**
     * Constructors for objects of class Animation.
     * It includes the start coordinate and the end coordinate.
     * And it setup the refresh rate.
     */
    public Animation(Actor actor, int xStart, int yStart, int xEnd, int yEnd)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.actor = actor;
        // Use this final int COUNT_DOWN_TOTAL as the default count down.
        this.countDown = COUNT_DOWN_TOTAL;
    }
    
    public Animation(Actor actor, int xStart, int yStart, int xEnd, int yEnd, boolean removeOnFinish)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.actor = actor;
        this.countDown = COUNT_DOWN_TOTAL;
        this.removeOnFinish = removeOnFinish;
    }
    
    /**
     * Act - do whatever the Movement wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // If count down is still in progress, move the object to next location,
        // which is proportional to the start and end location.
        if (countDown >= 0)
        {
            MyWorld world = (MyWorld) getWorld();
            
            int x = xStart + (xEnd - xStart) * (COUNT_DOWN_TOTAL - countDown) / COUNT_DOWN_TOTAL;
            int y = yStart + (yEnd - yStart) * (COUNT_DOWN_TOTAL - countDown) / COUNT_DOWN_TOTAL;
            
            world.removeObject(actor);
            world.addObject(actor, x, y);
            
            countDown--;
        }
        // Remove the object from world if count down finshed and removeOnFinish is set to true.
        else if (removeOnFinish)
        {
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(actor);
        }
    }
}
