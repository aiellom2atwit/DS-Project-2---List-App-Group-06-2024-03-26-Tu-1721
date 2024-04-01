/**
 * 
 */
package edu.wit.scds.ds.list.app ;

import java.util.Random ;
import java.util.Scanner ;
import java.util.random.RandomGenerator ;

/**
 * 
 */
public class CrazyEights {

	/**
	 * 
	 */
	
	private static final String[] gameGraphic = {	"   _____                        ___  _     \n",
									"  / ____|                      / _ \\( )    \n",
									" | |     _ __ __ _ _____   _  | (_) |/ ___ \n",
									" | |    | '__/ _` |_  / | | |  > _ <  / __|\n",
									" | |____| | | (_| |/ /| |_| | | (_) | \\__ \\\n",
									"  \\_____|_|  \\__,_/___|\\__, |  \\___/  |___/\n",
									"                        __/ |              \n",
									"                       |___/               \n" } ;
	
	
	private static boolean botMode = false ;
	
	static Deck deck ;
	static Pile discardPile ;
	static Player[] players = new Player[ 2 ] ;
	
	static Card topCard ;
	
	static boolean wildcardActive = false ;
	static Suit wildcardSuit ;
	
	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		// Main method to run program
		
		// Print Stored Graphic For Crazy 8's
		for ( int i = 0 ; i < gameGraphic.length ; i++ ) {
			System.out.printf( "%s", gameGraphic[ i ] ) ;
		}
		
		String[] choiceOptions = { "0", "1" } ;
		String userChoice = userInput( choiceOptions, "Would you like to start a new game?%n"
						+ "(0: Start Game  1: Quit)" ) ;
		
		//Player chose to start game
		if ( userChoice.equals( "0" ) ) {
			//Ask player to choose 2 player mode or player vs bot
			userChoice = userInput( choiceOptions, "Would you like to play against a bot or in two player mode?%n"
					+ "(0: Player vs Player  1: Player vs Bot)" ) ;
			//Two player mode
			if ( userChoice.equals( "0" ) ) {
				botMode = false ;
			}
			//Bot mode
			if ( userChoice.equals( "1" ) ) {
				botMode = true ;
			}
			
			//Initialize game of crazy 8's
			System.out.println( "Initializing game..." ) ;
			System.out.println( "Please wait." ) ;
			
			StartGame() ;
		}
		//Player chose to quit game
		if ( userChoice.equals( "1" ) ) {
			System.out.println( "Terminating..." ) ;
			System.exit( 0 ) ;
		}
		
		//Game over -> allow player to start another game
		while ( true )
			{
			userChoice = userInput( choiceOptions, "Would you like to start a new game?%n"
							+ "(0: Start Another Game  1: Quit)" ) ;
			if ( userChoice.equals( "0" ) )
				{
				//Call StartGame method from beginning to start another game
				StartGame() ;
				}
			if ( userChoice.equals( "1" ) )
				{
				System.out.println( "Thank you for playing!" ) ;
				System.out.print( "Terminating..." ) ;
				System.exit( 0 ) ;
				}
			}
	}
	
	public static void StartGame() {
		//Generate players
		players[ 0 ] = new Player( botMode ) ;
		players[ 1 ] = new Player( false ) ;
		
		//Generate deck
		deck = new Deck() ;
		
		//Generate new discard pile
		discardPile = new Pile() ;
		
		//Draw 5 cards into each player's hand
		for ( int j = 0 ; j < 5 ; j++ )
			{
			for ( int i = 0 ; i < players.length ; i++ )
				{
				players[ i ].addCard( deck.deal() ) ;
				}
			}
		
		// Deal top card on deck onto pile
		topCard = deck.deal() ;
		discardPile.addCard( topCard ) ;
		
		boolean gameOver = false ;
		//Players take turns
		while ( !gameOver )
			{
			for ( int i = 0 ; i < players.length ; i++ )
				{
				if ( botMode == true && i == 0 )
					{
					//Bot turn
					System.out.println( "Bot's Turn" ) ;
					botTurn( players[ i ] ) ;
					}
				else
					{
					//Player turn
					System.out.println( players[ i ].getPlayerName() + "'s Turn" ) ;
					playerTurn( players[ i ] ) ;
					}
				} // end for
			
			// TODO check for winners
			if ( deck.isEmpty() )
				{
				//Deck is empty
				gameOver() ;
				}
			for ( int i = 0 ; i < players.length ; i++ )
				{
				if (players[ i ].getHand().isEmpty() )
					{
					//Player played all cards in hand
					gameOver() ;
					}
				}
			
			
			} // end while
		
		
	}
	
	public static void gameOver() {
		int[] playerScores = new int[players.length] ;
		
		int bestScore = 999 ;
		for ( int i = 0 ; i < playerScores.length ; i++ ) {
			playerScores[ i ] = players[ i ].getHand().cardsLeft() ;
			//if lower score than best score, lower best score
			if ( playerScores[ i ] < bestScore )
				{
				bestScore = playerScores[ i ] ;
				}
		}
		
		boolean tieGame = false ;
		boolean foundWinner = false ;
		for ( int i = 0 ; i < playerScores.length ; i++ )
			{
			if (foundWinner == true)
				{
				tieGame = true;
				}
			if ( playerScores[ i ] == bestScore ) ;
			foundWinner = true ;
			}
		
		//Tied game
		if ( tieGame == true )
			{
			System.out.println( "GAME RESULT: TIED GAME" ) ;
			}
		int winnerIndex = -1 ;
		if ( foundWinner ) {
			for ( int i = 0 ; i < players.length ; i++ )
				{
				if ( players[ i ].getHand().handLength == bestScore )
					{
					winnerIndex = i ;
					}
				}
		}
		else {
			System.out.println( "Error in declaring winner." ) ;
		}
		
		System.out.printf( "GAME RESULT: %s WINS!%n", players[ winnerIndex ].getPlayerName() ) ;
	}
	
	public static void botTurn( Player bot )
		{
		//Print top card
		System.out.println( "--TOP CARD: " + topCard.toString() + "--" ) ;

		//Have bot repeat turn until can play a card
		boolean turnOver = false ;
		while ( !turnOver )
			{
			int canPlayIndex = -1 ;
			//check if wildcard
			if ( wildcardActive )
				{
				//check if bot has matching SUIT
				for ( int i = 0 ; i < bot.getHand().handLength ; i++ )
					{
					Card currentCard = bot.getHand().toArray()[ i ] ;
					Rank currentRank = currentCard.rank ;
					Suit currentSuit = currentCard.suit ;
					if ( wildcardSuit == currentSuit )
						{
						canPlayIndex = i ;
						}
					else if ( currentRank == Rank.EIGHT )
						{
						//Don't use a wildcard unless no other option
						if ( canPlayIndex == -1 )
							{
							canPlayIndex = i ;
							}
						}
					}
				}
			//check if bot has matching suit or rank as topCard
			if ( !wildcardActive )
				{
				for ( int i = 0 ; i < bot.getHand().handLength ; i++ )
					{
					Card currentCard = bot.getHand().toArray()[ i ] ;
					Rank currentRank = currentCard.rank ;
					Suit currentSuit = currentCard.suit ;
					if ( currentRank == topCard.rank )
						{
						canPlayIndex = i ;
						}
					if ( currentSuit == topCard.suit )
						{
						canPlayIndex = i ;
						}
					}
				}
			
			if ( canPlayIndex == -1 ) {
				//didn't find a card to play
				//draw card
				System.out.println( "Bot is drawing a card." ) ;
				Card drawnCard = deck.deal() ;
				bot.addCard( drawnCard ) ;
				System.out.println( "Bot now has " + bot.getHand().handLength + " cards." ) ;
				//restart bot turn
			}
			else if ( canPlayIndex >= 0 )
				{
				//found a card to play
				Card foundCard = bot.getHand().toArray()[ canPlayIndex ] ;
				//remove card from hand
				bot.getHand().removeCard( foundCard ) ;
				
				//add foundCard to topCard
				topCard = foundCard ;
				discardPile.addCard( topCard ) ;
				
				System.out.println( "Bot is playing " + foundCard.toString() ) ;
				System.out.println( "Bot now has " + bot.getHand().handLength + " cards." ) ;
				
				//if card is eight declare random suit
				if ( foundCard.rank == Rank.EIGHT )
					{
					wildcardActive = true ;
					//TODO replace 0 with random number 0 -3
					Random rand = new Random() ; 
					wildcardSuit = Suit.values()[rand.nextInt(3)] ;
					}
				turnOver = true ;
				break ;
				}
			}
		
		
		}
	public static void playerTurn( Player currentPlayer )
		{
		System.out.println() ;
		
		currentPlayer.printHand() ;
		
		discardPile.readTopCard() ;
		
		boolean turnOver = false ;
		while ( !turnOver )
			{
			String[] choiceOptions = { "0", "1" } ;
			String userChoice = userInput( choiceOptions, "\nWould you like to play a card or draw?%n"
							+ "(0: Play Card  1: Draw)" ) ;
			if (userChoice.equals( "1" ))
				{
				//Check if cards left
				if ( deck.isEmpty() )
					{
					System.out.println( "No cards to draw. Calculating game results..." ) ;
					gameOver() ;
					}
				
				//Player draws card
				System.out.print( "\nDrawing card..." ) ;
				Card drawnCard = deck.deal() ;
				
				//Show card drawn
				System.out.print( "\nYou drew " + drawnCard.toString() ) ;
				currentPlayer.addCard( drawnCard ) ;
				
				//Print top card
				System.out.println( "--TOP CARD: " + topCard.toString() + "--" ) ;
				
				//Print full player hand
				currentPlayer.printHand() ;
				}
			if ( userChoice.equals( "0" ) )
				{
				//Playing a card
				boolean validMove = true ;
				if ( wildcardActive )
					{
					System.out.println( "WILDCARD ACTIVE! MUST PLAY CARD OF SUIT " + wildcardSuit.toString() ) ;
					System.out.println( "(You may also play another wildcard.)" ) ;
					if ( !validWildcardPlay( currentPlayer ) )
						{
						System.out.print( "No valid moves. Draw a card." ) ;
						validMove = false ;
						}
					}
				if ( !wildcardActive && !validPlay( currentPlayer ) )
					{
					System.out.print( "No valid moves. Draw a card." ) ;
					validMove = false ;
					}
				if ( validMove )
					{ 
					// Player has a valid move to make
					// Set options to card indices
					choiceOptions = new String[ currentPlayer.hand.handLength ] ;
					for ( int i = 0 ; i < choiceOptions.length ; i++ )
						{
						choiceOptions[ i ] = i + "" ;
						}
					userChoice = userInput(choiceOptions, "Please enter associated card index."
							+ "%n(e.g 0, 1...)") ;
					//Desired card player chose
					Card desiredCard = currentPlayer.hand.toArray()[ Integer.parseInt( userChoice ) ] ;
					
					if ( wildcardActive && wildcardCanPlay( desiredCard ) )
						{
						//Player chose playable card
						System.out.println( "Playing " + desiredCard.toString() ) ;
						
						currentPlayer.getHand().removeCard( desiredCard ) ;
						topCard = desiredCard ;
						discardPile.addCard( topCard ) ;
						
						turnOver = true ;
						break ;
						}
					
					if ( !wildcardActive && canPlay( desiredCard ) )
						{
						//Player chose playable card
						System.out.println( "Playing " + desiredCard.toString() ) ;
						
						if ( desiredCard.rank == Rank.EIGHT )
							{
							wildcardActive = true ;
							
							String[] suitOptions = {"0", "1", "2", "3"} ;
							userChoice = userInput(suitOptions, "\nPlayed wildcard. What suit would you like to declare it as?%n"
											+ "(0: Spades  1: Diamonds  2: Hearts  3: Clubs)") ;
							if ( userChoice.equals( "0" ) )
								{
								//Spades
								wildcardSuit = Suit.SPADES ;
								}
							if ( userChoice.equals( "1" ) )
								{
								//Diamonds
								wildcardSuit = Suit.DIAMONDS ;
								}
							if ( userChoice.equals( "2" ) )
								{
								//Hearts
								wildcardSuit = Suit.HEARTS ;
								}
							if ( userChoice.equals( "3" ) )
								{
								//Clubs
								wildcardSuit = Suit.CLUBS ;
								}
							}
						
						currentPlayer.getHand().removeCard( desiredCard ) ;
						topCard = desiredCard ;
						discardPile.addCard( topCard ) ;
						
						turnOver = true ;
						break ;
						}
					else
						{
						//Player chose invalid card option
						System.out.println( "Cannot play " + desiredCard.toString() + 
								"\nPlease select another option." ) ;
						}
					}
				
				}
			}
		
		
		}
	public static boolean validWildcardPlay( Player currentPlayer )
		{
		//check if player can play from selection
		Suit topSuit = topCard.suit ;
				
		Card[] playerHand = currentPlayer.getHand().toArray() ;
		for ( int i = 0 ; i < playerHand.length ; i++ )
			{
			Card currentCard = playerHand[ i ];

			if ( currentCard.rank == Rank.EIGHT )
				{
				return true ;
				}
			if ( currentCard.suit == wildcardSuit )
				{
				return true ;
				}
			}
		return false ;
		}
	
	//Return true if card matches suit of wildcard
	public static boolean wildcardCanPlay( Card desiredCard ) {
		//Can only play a card if it matches Suit
		if ( desiredCard.rank == Rank.EIGHT )
			{
			//Player trying to play another wildcard
			return true ;
			}
		else if ( desiredCard.suit == wildcardSuit )
			{
			wildcardActive = false ;
			return true ;
			}
			
		return false ;
	}
	
	//Return true if card can be played on top of discard pile
	public static boolean canPlay( Card desiredCard ) {
		//Eights are wildcards and can always be played
		if ( desiredCard.rank == Rank.EIGHT )
			{
			return true ;
			}
		if ( desiredCard.rank == topCard.rank )
			{
			return true ;
			}
		if ( desiredCard.suit == topCard.suit )
			{
			return true ;
			}
		
		return false ;
	}
	
	public static boolean validPlay(Player currentPlayer) {
		//check if player can play from selection
		Rank topRank = topCard.rank ;
		Suit topSuit = topCard.suit ;
		
		Card[] playerHand = currentPlayer.getHand().toArray();
		for ( int i = 0 ; i < playerHand.length ; i++ )
			{
			Card currentCard = playerHand[ i ] ;
			if ( currentCard.rank == Rank.EIGHT )
				{
				return true ;
				}
			if ( currentCard.rank == topRank )
				{
				return true ;
				}
			if ( currentCard.suit == topSuit )
				{
				return true ;
				}
			}
		return false ;
	}
	/**
	 * Asks user for input until it matches options
	 * 
	 * @param options
	 * @param message
	 * @return String value from options
	 */
 	public static String userInput( String[] options, String message ) {
		//Return empty string if given no options
		if ( options.length <= 0 ) {
			return "" ;
		}
		//Print message
		System.out.printf( message );
		
		//Create scanner object for player input
		Scanner userInput = new Scanner( System.in ) ;
		
		
		
		//Create visual indent
		System.out.print( "\n > " ) ;
		
		//Receive user String
		String userChoice = userInput.next() ;
		
		//Loop until player chooses valid option
		boolean validInput = false ;
		while ( !validInput )
			{
			for ( int i = 0 ; i < options.length ; i++ )
				{
				//Check if userInput matches options
				if ( userChoice.toUpperCase().equals( options[ i ] ) )
					{
					//User input matches, break from while loop
					validInput = true ;
					return userChoice ;
					}
				}
			// userInput does not match options
			String incorrectInputMsg = "" ;
			incorrectInputMsg += "Invalid Input!%n" ;
			incorrectInputMsg += "Valid entries: (\"" ;
			for ( int i = 0 ; i < options.length ; i++ ) 
				{
				incorrectInputMsg += options[i] + "\"" ;
				//Add comma between options if not last option
				if ( i != options.length - 1 )
					{
					incorrectInputMsg += ", \"" ;
					}
				}
			incorrectInputMsg += ")" ;
			
			System.out.printf( incorrectInputMsg ) ;
			
			System.out.print( "\n > " ) ;
			userChoice = userInput.next() ;
			} // end while loop
		
		
		
		
		userInput.close() ;
		
		// Input is correct
		return userChoice ;
	}

}
