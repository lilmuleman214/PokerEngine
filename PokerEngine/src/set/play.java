package set;

public class play {

	public static void main(String[] args) {
		deck deck1 = new deck();
		
		card[] lst1 = {deck1.draw(), deck1.draw(), deck1.draw(), deck1.draw(), deck1.draw()};
		hand player1 = new hand(lst1);
		
		card[] lst2 = {deck1.draw(), deck1.draw(), deck1.draw(), deck1.draw(), deck1.draw()};
		hand player2 = new hand(lst2);
		
		card[] lst3 = {deck1.draw(), deck1.draw(), deck1.draw(), deck1.draw(), deck1.draw()};
		hand player3 = new hand(lst3);
		
		card[] lst4 = {deck1.draw(), deck1.draw(), deck1.draw(), deck1.draw(), deck1.draw()};
		hand player4 = new hand(lst4);
		
		hand[] playerlst = {player1, player2, player3, player4};
		System.out.print(player1.judge(playerlst));
	}

}
