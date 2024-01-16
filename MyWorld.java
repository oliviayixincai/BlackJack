import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.*;

/**
 * This is the main class of the game. The game will be start and act here.
 * It includes amost every labels and objects to show on the screen.
 * The dealer's actions is controled by a method in this class. 
 * It includes all the basic functions using
 * conditions, and play the sound effects.
 * The chips will be calculated and show the animation.
 * It will determine the results and decided whether to restart a new game.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class MyWorld extends World
{
    //Thisis a final int, to make sure the amount for each game is the same.
    final public static int GAME_BET = 2;
    final public static int PLAYER_INIT_CHIP = 20;
    //Variables for music.
    GreenfootSound bgmSound = new GreenfootSound("popBeat.wav");
    public MusicCollection musicCollection = new MusicCollection();
    
    private BGMToggle bgmToggle;
    private BGMDown bgmDown;
    private BGMUp bgmUp;
    
    //Variable for the deck.
    private Deck deck = new Deck();
    
    //Variables for dealer and player.
    private Dealer dealer = new Dealer();
    private Player player= new Player();
    
    //Declare the texts of all the message labels.
    //Those message labels will be shown on the scrren.
    //Show some informations to tell the player.
    private Label tryAgainLabel = new Label("Click TRY AGAIN button.\nGambling in moderation!");
    private Label noAssetLabel = new Label("You don't have enough chips to play. \nClick Start button to play again.");
    private Label dealerWinInsuranceLabel = new Label("You lose. But you buy insurance wisely.");
    private Label dealerWinLabel = new Label("What a pity. You lose.");
    private Label pushLabel = new Label("You push with the dealer!");
    private Label playerWinBlackjackLabel = new Label("Great job! You win with BlackJack!");
    private Label playerWinDoubleDownLabel = new Label("Great job! You win with Double Down!");
    private Label playerWinLabel = new Label("Great job! You win.");
    private Label playerSurrenderLabel = new Label("You surrender.");
    
    private Label resultMessage = new Label("");
    
    private Label statsLabel = new Label("", 40);
    
    //Variable for seven buttons.
    //Two buttons to restart the game.
    private StartButton startButton = new StartButton();
    private TryAgainButton tryAgainButton = new TryAgainButton();
    //Five buttons to let player use during the game.
    private Button standButton = new StandButton();
    private Button hitButton = new HitButton();
    private Button doubleDownButton = new DoubleDownButton();
    private Button insuranceButton = new InsuranceButton();
    private Button surrenderButton = new SurrenderButton();
    
    private HelpIcon helpIcon = new HelpIcon();
    
    //Varialbles for collecting statistics about each game.
    private int playRound = 0;
    private int winRound = 0;
    private int loseRound = 0;
    private int pushRound = 0;
    private int blackJack = 0;
    
    //Boolean variables for determine the results and control the buttons.
    private boolean dealerTurn;
    private boolean gameOver;
    private boolean isDoubleDown;
    private boolean canHit;
    private boolean isInsurance;
    
    private ArrayList<Chip> chipList;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 1323x744 cells with a cell size of 1x1 pixels.
        super(1323,744, 1);
        //Setting all the parameters for a new game.
        resetParameters();
        
        //Updating the statistical label in a new game.
        updateStatsLabel();
        
        //Adding the background music icons from the start. 
        bgmToggle = new BGMToggle(bgmSound);
        bgmDown = new BGMDown(bgmSound);
        bgmUp = new BGMUp(bgmSound);
        addObject( bgmToggle, getWidth() - 220, (getHeight() / 2) - 180 );
        addObject( bgmDown, getWidth() - 140, (getHeight() / 2) - 180 );
        addObject( bgmUp, getWidth() - 60, (getHeight() / 2) - 180 );
        
        addObject(helpIcon, getWidth() - 60, 120);
        
        setupNewGame();
    }
    
    /**
     * Method used to determine the result of the game and call corresponding method.
     * It results into one of three results which are dealer's victory, player's victory
     * or push. After that, it updates UI objects and result statistics.
     */
    public void checkGameResult()
    {
        //Used to determine if the dealer won and call a method.
        //If the dealer's scores are higher than the player and not busting, the dealer wins.
        //If the player is busting which means the 
        //player's scores are higher than 21, the dealer wins.
        if ((dealer.score <= 21 && dealer.score > player.score) || player.score > 21)
        {
            dealerWin();
        }
        //Used to determine whether the player pushes with dealer and call a method.
        //If the dealer and the player al  have blackjack, they push.
        //If the player's scores are equal to the dealer's scores
        //and the player has no blackjack, they push.
        else if ( (dealer.isBlackJack && player.isBlackJack) || (dealer.score == player.score && !player.isBlackJack) )
        {
            push();
        }
        //Used to determine whether the player won and call a method.
        else
        {
            playerWin();
        }
        
        if (this.player.isBlackJack)
        {
            this.blackJack++;
        }
        
        //change the boolean gameOver is true.
        this.gameOver = true;
        //The variable playRound plus one;
        playRound++;
        
        //add tryAgainLabel Object to tell the player how to try again.
        addObject( this.tryAgainLabel, getWidth() / 2, (getHeight() / 2) );
        
        //Update the statistical labels at the end of the game.
        this.updateStatsLabel();
        //Hide the buttons so the player can not use them.
        hideButtons();
    }
    
    /**
     * Method called when the dealer wins.
     * It will play a normal lose sound effect, update the chips,
     * add message to show the result and increment the number of lose rounds.
     */
    public void dealerWin()
    {
        //Check whether the dealer has blackjack and the player bought insurance.
        //In such case, relevant message will be shown and the player will get 15 chips for feedback.
        this.musicCollection.normalLoseSound.play();
        if (dealer.isBlackJack && this.isInsurance)
        {
            addObject( dealerWinInsuranceLabel, getWidth() / 2, getHeight() / 2 - 120);
            this.player.updateChip( (int) (1.5 * GAME_BET) );
            collectChip((int) (1.5 * GAME_BET));
        }
        //If dose not, the lose sound effect will be played and an object will be shown. 
        else
        {
            addObject( dealerWinLabel, getWidth() / 2, getHeight() / 2 - 100);
            collectChip(0);
        }
        //The lose round number plus one.
        loseRound++;
    }
    
    /**
     * Method called when the player push with the dealer.
     * It will play a sound effect, add message to show the result,
     * update the chips and increment number of push round.
     */
    public void push()
    {
        //play the push sound effect.
        this.musicCollection.pushSound.play();
        //add a object to show the result and tell the player informations.
        addObject( pushLabel , getWidth() / 2, getHeight() / 2 - 100);
        int chipBack;
        //If the player double down, then the player get 20 chips for feedback.
        if (this.isDoubleDown)
        {
            chipBack = 2 * GAME_BET;
        }
        //If the dealer has blackjack and the player bought a insurance,
        //the player will get back 1.5 * GAME_BET chips
        else if (dealer.isBlackJack && this.isInsurance)
        {
            chipBack = (int) (1.5 * GAME_BET);
        }
        else
        //The player push with the dealer normaly so the player get 2 chips back.
        {
            chipBack = GAME_BET;
        }
        this.player.updateChip(chipBack);
        collectChip(chipBack);
        //The push round plus one.
        this.pushRound++;
    }
    
    /**
     * Method called when the player wins.
     * It will play the sound effect, add message to show the result,
     * update the chips and increment number of win rounds.
     * Increment number of blackjack count if the player has blackjack.
     * 
     */
    public void playerWin()
    {
        int chipBack;
        //If the player has blackjack, the blackJack number plus one
        //play the blackJack win sound effect, add label object
        //and give the player 25 chips.
        if (this.player.isBlackJack)
        {
            this.musicCollection.blackJackWinSound.play();
            addObject( playerWinBlackjackLabel, getWidth() / 2, getHeight() / 2 - 100 );
            chipBack = (int)(2.5 * GAME_BET);
        }
        //If the player double down, the double down win sound effect will be played,
        //the player will get 40 chips,
        //a label object will be shown on the screen.
        else if (isDoubleDown)
        {
            this.musicCollection.doubleDownWinSound.play();
            addObject( playerWinDoubleDownLabel, getWidth() / 2, getHeight() / 2 - 100 );
            chipBack = 4 * GAME_BET;
        }
        else
        //This is a normal situation which is player's scores are higher than 
        //the dealer's scores and not busting.
        //It will play a normal win sound effect and give player 20 chips.
        {
            this.musicCollection.normalWinSound.play();
            addObject( playerWinLabel, getWidth() / 2, getHeight() / 2 - 100 );
            chipBack = 2 * GAME_BET;
        }
        this.player.updateChip(chipBack);
        collectChip(chipBack);
        //the win round number plus one.
        winRound++;
    }
    
    /**
     * Method called when the player click the "surrender" buton.
     * It will update the the statistical numbers, play sound effects,
     * update chips, add message to show the result, set game over,
     * update UI objects and result statistics
     */
    public void surrender()
    {
        //Lose round number and play round number plus one.
        loseRound++;
        playRound++;
        //If the player use double down, play double 
        //down sound effect and update the chips.
        if (isDoubleDown)
        {
            this.musicCollection.doubleDownLoseSound.play();
            this.player.updateChip( GAME_BET );
            collectChip(GAME_BET);
        }
        //If not, play the surrender sound effect
        //and update the chips.
        else
        {
            this.musicCollection.surrenderLoseSound.play();
            this.player.updateChip( (int)(0.5 * GAME_BET) );
            collectChip((int)(0.5 * GAME_BET));
        }
        //The boolean is true.
        this.gameOver = true;
        //Add object to show the informations.
        addObject( playerSurrenderLabel, getWidth() / 2, getHeight() / 2 - 100 );
        //add tryAgainLabel Object to tell the player how to try again.
        addObject( this.tryAgainLabel, getWidth() / 2, (getHeight() / 2) );
        //Update the score label.
        this.updateStatsLabel();
        //Hide all the buttons to let player can not see them.
        hideButtons();
    }
    
    /**
     * Method to hide all Button objects from the world.
     */
    public void hideButtons()
    {
        ArrayList<Actor> actors = (ArrayList) getObjects(null);
        for (Actor actor : actors)
        {
            if ( actor instanceof Button )
            {
                removeObject(actor);
            }
        }
    }
    
    /**
     * Method used to determine whether the player could hit or not.
     * Only the game is not over, it is the player's turn,
     * the payer has not been double down whereas has only thtwo cards,
     * and the player's chips sre enough to pay double down,
     * then the player could use this.
     */
    public boolean canDoubleDown()
    {
        return !checkGameOver() && !checkDealerTurn() && this.player.cards.size() == 2
            && !isDoubleDown && player.chip >= GAME_BET;
    }
    
    /**
     * mehtod used to determine whether the player could hit or not.
     * Only the player could click the basic button and 
     */
    public boolean canHit()
    {
        return this.canClickBasicButton() && this.canHit;
    }
    
    /**
     * method used to determine whether the player could buy insurance or not.
     * Only the player could click the basic buttons, the dealer's
     * first revealed card is ace, the player has not been bought the insurance in
     * this game and has enough chips to pay, the player could buy it.
     */
    public boolean canInsurance()
    {
        return this.canClickBasicButton() && this.dealer.cards.get(0).getValue() == "ace"
            && !this.isInsurance && player.chip >=  (GAME_BET / 2);
    }
    
    /**
     * 
     *This is a method used to determine whether the player could click basic buttons.
     *Only the game is not over and it is the player's turn,
     *so the player could click basic buttons.
     */
    public boolean canClickBasicButton()
    {
        return !this.checkGameOver() && !this.checkDealerTurn();  
    }
    
    /**
     * Method used to act double down.
     * It will subtract ten from the player's chips, and change the isDoubleDown boolean into true.
     */
    public void doubleDown()
    {
        this.player.updateChip(-GAME_BET);
        betChip(GAME_BET);
        this.isDoubleDown = true;
    }
    
    /**
     * Method used to act insurance.It will subtract five from the player's chips, 
     * and change the isInsurance boolean into true.
     */
    public void insurance()
    {
        this.player.updateChip( (int)(0.5 * -GAME_BET) );
        betChip((int)(0.5 * GAME_BET));
        this.isInsurance = true;
    }
    
    /**
     * Method used to control the dealer's actions.
     * The dealer will only reveal one card,dealer will keep grabing cards 
     * until his score is seventeen or greater.
     * It will check the game result, because the game will be ovr after the dealer's actions.
     */
    public void dealerAction()
    {
        this.dealer.revealCard();
        
        while (this.dealer.score < 17)
        {
            turnNewCard( false );
        }
        checkGameResult();
    }
    
    /**
     * Method used to determine whether is the dealer's turn.
     */
    public boolean checkDealerTurn()
    {
        return this.dealerTurn;
    }
    
    /**
     * Method used to determine whether the game is over.
     */
    public boolean checkGameOver()
    {
        return this.gameOver;
    }
    
    /**
     * Method used to add the buttons except the start button.
     */
    private void addButtonObjects()
    {
        addObject( tryAgainButton, getWidth() - 140, (getHeight() / 2) + 300 );
        
        addObject( standButton, getWidth() / 2 - 250, (getHeight() / 2) + 140 );
        addObject( hitButton, getWidth() / 2 , (getHeight() / 2) + 140 );
        addObject( doubleDownButton, getWidth() / 2 + 250, (getHeight() / 2) + 140 );
        
        addObject( insuranceButton, getWidth() - 140, (getHeight() / 2) );
        addObject( surrenderButton, getWidth() - 140, (getHeight() / 2) + 140 );
    }
    
    /**
     * Method used to add the player'score label and the dealer' score labels.
     */
    private void addLabelObjects()
    {
        addObject( this.dealer.scoreLabel, 130, 100 );

        addObject( this.player.scoreLabel, 130, getHeight() - 100 );
        this.addStats();
    }
    
    /**
     * Method used to call a method to update the statistical labels and add them on the screen.
     */
    private void addStats()
    {
        this.updateStatsLabel();
        addObject(this.statsLabel, 130, getHeight() / 2);
        addObject(this.player.chipLabel, 130, getHeight() / 2 + 120);
    }
    
    /**
     * Method used to update the statistical labels.
     */
    private void updateStatsLabel()
    {
        this.statsLabel.updateLabel("Played Total: " + this.playRound 
            + "\nWin: " + this.winRound 
            + "\nLose: " + this.loseRound 
            + "\nPush: " + this.pushRound
            + "\nBlackJack: " + this.blackJack);
    }
    
    /**
     * Method used to remove most of the game actors.
     * Every actors except the music part have to be removed.
     */
    public void removeGameActors()
    {
        // Use array to get all actor objects
        ArrayList<Actor> actors = (ArrayList) getObjects(null);
        
        for (Actor actor : actors)
        {
            if ( !(actor instanceof Music || actor instanceof HelpIcon) )
            {
                removeObject(actor);
            }
        }
    }
    
    /**
     * Method used to switch the turn from the player to the dealer 
     * and call a method to start the dealer's turn.
     */
    public void switchTurn()
    {
        this.dealerTurn = true;
        dealerAction();
    }
    
    public void betChip(int num)
    {
        musicCollection.chipSound.play();
        int size = chipList.size();
        for (int i =0; i < num; i++)
        {
            Chip chip = new Chip();
            chipList.add(chip);
            Animation animation = new Animation(chip, 130, getHeight() / 2 + 140, (getWidth() / 2) - 260 + 100 * (chipList.size() - 1), getHeight() / 2 + 40);
            addObject(animation, 0, 0);
        }
    }
    
    public void collectChip(int num)
    {
        int betSize = chipList.size();
        if (num >= betSize)
        {
            for (int i = 0; i < betSize; i++)
            {
                Chip chip = chipList.get(i);
                Animation animation = new Animation(chip, (getWidth() / 2) - 260 + 100 * (i - 1), getHeight() / 2 + 40, 130, getHeight() / 2 + 140, true);
                addObject(animation, 0, 0);
            }
            for (int i = 0; i < num - betSize; i++)
            {
                Chip chip = new Chip();
                Animation animation = new Animation(chip, getWidth() / 2 - 260 + 100 * (i - 1), 40, 130, getHeight() / 2 + 140, true);
                addObject(animation, 0, 0);
            }
        }
        else if (num < betSize)
        {
            int i = 0;
            for (; i < num; i++)
            {
                Chip chip = chipList.get(i);
                Animation animation = new Animation(chip, (getWidth() / 2) - 260 + 100 * (i - 1), getHeight() / 2 + 40, 130, getHeight() / 2 + 140, true);
                addObject(animation, 0, 0);
            }
            for (; i < betSize; i++)
            {
                Chip chip = chipList.get(i);
                Animation animation = new Animation(chip, (getWidth() / 2) - 260 + 100 * (i - 1), getHeight() / 2 + 40, getWidth() / 2, 40, true);
                addObject(animation, 0, 0);
            }
        }
    }
    
    /**
     * Method used to add one card with a boolean parameter to determine the game's turn.
     * 
     * @param boolean to determine the game's turn
     */
    public void turnNewCard( boolean isPlayer )
    {
        if (isPlayer && !this.dealerTurn)
        {
            if (this.isDoubleDown)
            {
                this.canHit = false;
            }
            
            Card card = this.deck.getCard();
            card.frontCard();
            this.player.takeCard(card);
            
            int numberOfCards = this.player.cards.size() - 1;
            Animation animation = new Animation(card, getWidth() / 2, getHeight() / 2, (getWidth() / 2) - 150 + (110 * numberOfCards), getHeight() - 100);
            addObject(animation, 0, 0);

            if (this.player.score > 21)
            {
                checkGameResult();
            }
            else if (this.player.score == 21)
            {
                switchTurn();
            }
        }
        else
        {
            Card card = this.deck.getCard();
            card.frontCard();
            this.dealer.takeCard(card);
            
            int numberOfCards = this.dealer.cards.size() - 1;
            addObject( card, (getWidth() / 2) - 150 + (110 * numberOfCards) , 100 );
        }
        
    }
    
    /**
     * Method to reset parameters for a game.
     */
    private void resetParameters()
    {
        this.chipList = new ArrayList<Chip>();
        this.dealerTurn = false;
        this.gameOver = false;
        this.isDoubleDown = false;
        this.canHit = true;
        this.isInsurance = false;
    }
    
    /**
     * Method to set up object and parameters for a new game.
     */
    public void setupNewGame()
    {
        //Reset game
        if ( player.chip >= GAME_BET)
        {
            //Shuffle the deck.
            this.deck.shuffle();
            this.musicCollection.shuffleSound.play();
            
            //reset all scores
            resetParameters();
            
            //Add all objects again.
            addButtonObjects();
            addLabelObjects();
            
            //Remove the player cards and dealer cards
            this.dealer.reset();
            this.player.reset();
            
            //Initialize dealer with two cards
            //Reveal only the first card
            Card card = this.deck.getCard();
            card.frontCard();
            this.dealer.takeCard(card);
            card = this.deck.getCard();
            this.dealer.takeCard(card);
            
            //Add the dealer's cards to the game.
            addObject( this.dealer.cards.get(0), (getWidth() / 2) - 150 , 100 );
            addObject( this.dealer.cards.get(1), (getWidth() / 2) - 40 , 100 );
            
            // player put bet
            this.player.updateChip(-GAME_BET);
            betChip(GAME_BET);
            
            //Initialize player with two cards
            turnNewCard(true);
            turnNewCard(true);
            
            //Add the player cards to the game
            addObject( this.player.cards.get(0), (getWidth() / 2) - 150 , getHeight() - 100 );
            addObject( this.player.cards.get(1), (getWidth() / 2) - 40, getHeight() - 100 );
        }
        else
        {
            this.removeGameActors();
            this.addStats();
            addObject( noAssetLabel, getWidth() / 2, getHeight() / 2 - 150 );
            addObject( startButton, getWidth() / 2, (getHeight() / 2) );
        }
    }
    
    /**
     * Method to initialize statistics
     */
    public void initStats()
    {
        this.player.chip = PLAYER_INIT_CHIP;
        this.playRound = 0;
        this.winRound = 0;
        this.loseRound = 0;
        this.pushRound = 0;
        this.blackJack = 0;
    }
}
    
    
    
