package set;

public class hand {
	private card[] cards;
	private int kicker;
	private int highPair;
	
	public hand(card[] c)
	{
		cards = c;
	}
	
	//Uses integers 1 through 10 to rank the value of each hand. The most valuable hand has a ranking of 10. Least, 1. 
	public static int judge(hand h)
	{
		card[] c = h.cards;
		int i = 0;
		
		String[] suits = new String[5];
		int hearts = 0, spades = 0, diamonds = 0, clubs = 0;
		
		do
		{
			suits[i] = c[i].getSuit();
			i++;
		} while (i <=4);
		
		for (int x=0; x<=4; x++)
		{
			if (suits[x] == "heart")
				hearts++;
			else if (suits[x] == "spade")
				spades++;
			else if (suits[x] == "diamond")
				diamonds++;
			else if (suits[x] == "club")
				clubs++;
		}
		
		if ((hearts == 5 || spades == 5 || diamonds == 5 || clubs == 5) &&
				(containsRanks(h, "10", "J", "Q", "K", "A")))
			{
				h.setKicker(1);
				h.setHighPair(-1);
				return 10; //Royal Flush
			}
		else if ((containsRanks(h, "A", "2", "3", "4", "5") || containsRanks(h, "2", "3", "4", "5", "6") || containsRanks(h, "3", "4", "5", "6", "7") ||
				containsRanks(h, "4", "5", "6", "7", "8") || containsRanks(h, "5", "6", "7", "8", "9") || containsRanks(h, "6", "7", "8", "9", "10") ||
				containsRanks(h, "7", "8", "9", "10", "J") || containsRanks(h, "8", "9", "10", "J", "Q") || containsRanks(h, "9", "10", "J", "Q", "K")) &&
				(hearts == 5 || spades == 5 || diamonds == 5 || clubs == 5))
			{
				h.setKicker(sortByRank(h)[4].getRankToInt());
				h.setHighPair(-1);
				return 9; //Straight Flush
			}
		else if (fourOfAKind(h))
			return 8; //Four of a Kind
		else if (fullHouse(h))
			return 7; //Full House
		else if (hearts == 5 || spades == 5 || diamonds == 5 || clubs == 5)
			return 6; //Flush
		else if (containsRanks(h, "A", "2", "3", "4", "5") || containsRanks(h, "2", "3", "4", "5", "6") || containsRanks(h, "3", "4", "5", "6", "7") ||
		containsRanks(h, "4", "5", "6", "7", "8") || containsRanks(h, "5", "6", "7", "8", "9") || containsRanks(h, "6", "7", "8", "9", "10") ||
		containsRanks(h, "7", "8", "9", "10", "J") || containsRanks(h, "8", "9", "10", "J", "Q") || containsRanks(h, "9", "10", "J", "Q", "K"))
			{
				h.setKicker(sortByRank(h)[4].getRankToInt());
				h.setHighPair(-1);
				return 5; //Straight
			}
		else if (threeOfAKind(h))
			return 4; //Three Of A Kind
		else if (twoPair(h))
			return 3; //Two Pair
		else if (onePair(h))
			return 2; //One Pair
		else
			return 1; //Refer to Kicker
		
	}
	
	public String judge(hand[] hLst)
	{
		int numHands = hLst.length - 1;
		hand bestHand = hLst[0];
		
		for (int i = 1; i <= numHands; i++)
		{
			if (judge(hLst[i]) > judge(hLst[i - 1]))
				bestHand = hLst[i];
			else if (judge(hLst[i]) == judge(hLst[i - 1]))
			{
				if (hLst[i].getHighPair() > hLst[i - 1].getHighPair())
					bestHand = hLst[i];
				else if (hLst[i].getHighPair() == hLst[i - 1].getHighPair())
				{
					if (hLst[i].getKicker() > hLst[i - 1].getKicker())
						bestHand = hLst[i];
						
				}
			}
		}
		
		return "Winner is " + judge(bestHand) + "with a High Pair of " + bestHand.getHighPair() + " and a kicker of " +
			bestHand.getKicker() + ".";
	}
	
	private boolean containsRank(String rank)
	{
		int i = 0;
		
		do
		{
			if (this.cards[i].getRank() == rank)
				return true;
			i++;
		} while (i <=4);
		
		return false;
	}
	
	private static boolean containsRanks(hand h, String r1, String r2, String r3, String r4, String r5)
	{
		if (h.containsRank(r1) && h.containsRank(r2) && h.containsRank(r3) && h.containsRank(r4) &&
				h.containsRank(r5))
			return true;
		else
			return false;
	}
	
	private static card[] sortByRank(hand h)
	   {
	      int i, j, min;

	      for ( i = 0 ; i < h.cards.length ; i ++ )
	      {
	         min = i;  

	         for ( j = i+1 ; j < h.cards.length ; j++ )
	         {
	            if ( h.cards[j].getRankToInt() < h.cards[min].getRankToInt() )
	            {
	               min = j;        
	            }
	         }

	         card help = h.cards[i];
	         h.cards[i] = h.cards[min];
	         h.cards[min] = help;
	      }
	      
	      return h.cards;
	   }
	
	private static boolean fourOfAKind(hand h)
	{
		card[] cardList = sortByRank(h);
		
		boolean test1, test2;
		
		test1 = cardList[0].getRankToInt() == cardList[1].getRankToInt() &&
				cardList[1].getRankToInt() == cardList[2].getRankToInt() &&
				cardList[2].getRankToInt() == cardList[3].getRankToInt() ;
		
		test2 = cardList[1].getRankToInt() == cardList[2].getRankToInt() &&
				cardList[2].getRankToInt() == cardList[3].getRankToInt() &&
				cardList[3].getRankToInt() == cardList[4].getRankToInt() ;
		
		if (test1)
		{
			h.setHighPair(cardList[0].getRankToInt());
			h.setKicker(cardList[4].getRankToInt());
		} else if (test2)
		{
			h.setHighPair(cardList[4].getRankToInt());
			h.setKicker(cardList[0].getRankToInt());
		}
		
		
		return( test1 || test2);
	}
	
	private static boolean fullHouse(hand h)
	{
		boolean test1, test2;

		card[] cardList = sortByRank(h);

		test1 = cardList[0].getRankToInt() == cardList[1].getRankToInt() &&
				cardList[1].getRankToInt() == cardList[2].getRankToInt() &&
				cardList[3].getRankToInt() == cardList[4].getRankToInt() ;

		test2 = cardList[0].getRankToInt() == cardList[1].getRankToInt() &&
				cardList[2].getRankToInt() == cardList[3].getRankToInt() &&
				cardList[3].getRankToInt() == cardList[4].getRankToInt() ;
		
		int p;
		 
		if (test1)
		{
			p = ((cardList[0].getRankToInt() > cardList[3].getRankToInt()))
					 ? cardList[0].getRankToInt() : cardList[3].getRankToInt();
			h.setHighPair(p);
			h.setKicker(-1);
		} else if (test2)
		{
			p = ((cardList[0].getRankToInt() > cardList[2].getRankToInt()))
					 ? cardList[0].getRankToInt() : cardList[2].getRankToInt();
			h.setHighPair(p);
			h.setKicker(-1);
		}

		return( test1 || test2);
	}
	
	private static boolean threeOfAKind(hand h)
	{
		boolean test1, test2, test3;
		
		card[] cardList = sortByRank(h);
		
		test1 = cardList[0].getRankToInt() == cardList[1].getRankToInt() &&
				cardList[1].getRankToInt() == cardList[2].getRankToInt() &&
				cardList[0].getRankToInt() != cardList[3].getRankToInt() &&
			    cardList[1].getRankToInt() != cardList[4].getRankToInt() &&
			    cardList[3].getRankToInt() != cardList[4].getRankToInt()
				;
		
		test2 = cardList[1].getRankToInt() == cardList[2].getRankToInt() &&
				cardList[2].getRankToInt() == cardList[3].getRankToInt() &&
				cardList[0].getRankToInt() != cardList[1].getRankToInt() &&
				cardList[4].getRankToInt() != cardList[1].getRankToInt() &&
				cardList[0].getRankToInt() != cardList[4].getRankToInt();
		
		test3 = cardList[2].getRankToInt() == cardList[3].getRankToInt() &&
				cardList[3].getRankToInt() == cardList[4].getRankToInt() &&
				cardList[0].getRankToInt() != cardList[2].getRankToInt() &&
				cardList[1].getRankToInt() != cardList[2].getRankToInt() &&
				cardList[0].getRankToInt() != cardList[1].getRankToInt();
		
		int p;
		
		if (test1)
		{
			p = ((cardList[3].getRankToInt() > cardList[4].getRankToInt()))
					 ? cardList[3].getRankToInt() : cardList[4].getRankToInt();
			h.setHighPair(cardList[0].getRankToInt());
			h.setKicker(p);
		} else if (test2)
		{
			p = ((cardList[0].getRankToInt() > cardList[4].getRankToInt()))
					 ? cardList[0].getRankToInt() : cardList[4].getRankToInt();
			h.setHighPair(cardList[1].getRankToInt());
			h.setKicker(p);
		} else if (test3)
		{
			p = ((cardList[0].getRankToInt() > cardList[1].getRankToInt()))
					 ? cardList[0].getRankToInt() : cardList[1].getRankToInt();
			h.setHighPair(cardList[2].getRankToInt());
			h.setKicker(p);
		}
		
		return( test1 || test2 || test3);
		
	}
	
	private static boolean twoPair(hand h)
	   {
	      boolean test1, test2, test3;

	     if ( fourOfAKind(h) || fullHouse(h) || threeOfAKind(h) )
	         return(false);           

	     card[] cardList = sortByRank(h);
                     
	      test1 = cardList[0].getRankToInt() == cardList[1].getRankToInt() &&
				  cardList[2].getRankToInt() == cardList[3].getRankToInt();

	      test2 = cardList[0].getRankToInt() == cardList[1].getRankToInt() &&
				  cardList[3].getRankToInt() == cardList[4].getRankToInt();

	      test3 = cardList[1].getRankToInt() == cardList[2].getRankToInt() &&
				  cardList[3].getRankToInt() == cardList[4].getRankToInt();
	      
	      int p;
			
			if (test1)
			{
				p = ((cardList[0].getRankToInt() > cardList[2].getRankToInt()))
						 ? cardList[0].getRankToInt() : cardList[2].getRankToInt();
				h.setHighPair(p);
				h.setKicker(cardList[4].getRankToInt());
			} else if (test2)
			{
				p = ((cardList[0].getRankToInt() > cardList[3].getRankToInt()))
						 ? cardList[0].getRankToInt() : cardList[3].getRankToInt();
				h.setHighPair(p);
				h.setKicker(cardList[2].getRankToInt());
			} else if (test3)
			{
				p = ((cardList[1].getRankToInt() > cardList[3].getRankToInt()))
						 ? cardList[1].getRankToInt() : cardList[3].getRankToInt();
				h.setHighPair(p);
				h.setKicker(cardList[0].getRankToInt());
			}
	      
	      return( test1 || test2 || test3 );
	   }
	
	private static boolean onePair(hand h)
	   {
	      boolean test1, test2, test3, test4;

	      if ( fourOfAKind(h) || fullHouse(h) || threeOfAKind(h) || twoPair(h) )
	         return(false);    

	      card[] cardList = sortByRank(h);
          
	      test1 = cardList[0].getRankToInt() == cardList[1].getRankToInt();

	      test2 = cardList[1].getRankToInt() == cardList[2].getRankToInt();

	      test3 = cardList[2].getRankToInt() == cardList[3].getRankToInt();

	      test4 = cardList[3].getRankToInt() == cardList[4].getRankToInt();
	      
	      if (test1)
			{
				h.setHighPair(cardList[0].getRankToInt());
				h.setKicker(Math.max(cardList[2].getRankToInt(), 
									Math.max(cardList[3].getRankToInt(), cardList[4].getRankToInt())));
			} else if (test2)
			{
				h.setHighPair(cardList[1].getRankToInt());
				h.setKicker(Math.max(cardList[0].getRankToInt(), 
									Math.max(cardList[3].getRankToInt(), cardList[4].getRankToInt())));
			} else if (test3)
			{
				h.setHighPair(cardList[2].getRankToInt());
				h.setKicker(Math.max(cardList[0].getRankToInt(), 
									Math.max(cardList[1].getRankToInt(), cardList[4].getRankToInt())));
			} else if (test4)
			{
				h.setHighPair(cardList[3].getRankToInt());
				h.setKicker(Math.max(cardList[0].getRankToInt(), 
									Math.max(cardList[1].getRankToInt(), cardList[2].getRankToInt())));
			}

	      return( test1 || test2 || test3 || test4 );
	   }

	protected void setHighPair(int a)
	{
		highPair = a;
	}
	
	protected void setKicker(int a)
	{
		kicker = a;
	}

	public int getHighPair()
	{
		return highPair;
	}
	
	public int getKicker()
	{
		return kicker;
	}
}
