package set;

import java.util.Random;

public class deck {
	private card[] cards;
	private int deckCount;

	public deck()
	{
		deckCount=52;
		cards = new card[52];
		
		int x=0;
		for (int suit=1; suit<=4; suit++)
		{
			for (int rank=1; rank<=13; rank++)
			 {
			   cards[x] = new card(rank, suit);
			   x++;
			 }
		}
	}
		
	public card draw()
	{
		Random generator = new Random();
		int i= 0;

		do {
			i = generator.nextInt( 51 );
		} while (cards[i] == null);

		deckCount--;
		card temp = cards[i];
		cards[i]= null;
		return temp;
	}
	
	public int cardsRemaining()
	{
		return deckCount;
	}
	
	
}
