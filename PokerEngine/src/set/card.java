package set;

public class card {
	
	private String rank;
	private int rankToInt;
	private String suit;
	
	public card(int r, int s){
        switch (s) {
            case 1:  suit = "heart";
                     break;
            case 2:  suit = "spade";
                     break;
            case 3:  suit = "club";
                     break;
            case 4:  suit = "diamond";
            		 break;
            default: suit = "Invalid suit";
                     break;  
        }
        
        switch (r) {
            case 1:  rank = "A";
                     break;
            case 2:  rank = "2";
                     break;
            case 3:  rank = "3";
                     break;
            case 4:  rank = "4";
                     break;
            case 5:  rank = "5";
                     break;
            case 6:  rank = "6";
                     break;
            case 7:  rank = "7";
                     break;
            case 8:  rank = "8";
                     break;
            case 9:  rank = "9";
                     break;
            case 10: rank = "10";
                     break;
            case 11: rank = "J";
                     break;
            case 12: rank = "Q";
                     break;
            case 13: rank = "K";
            		 break;
            default: rank = "Invalid rank";
                     break;
        }
        
        rankToInt = r;
        
	}
	
	public card(String r, String s){
		rank = r;
		suit = s;
	}
	
	public String getRank()
	{
		return rank;
	}
	
	public int getRankToInt()
	{
		return rankToInt;
	}
	
	public String getSuit()
	{
		return suit;
	}
	
	
	
}
