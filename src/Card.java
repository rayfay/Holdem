import java.util.Arrays;
/**
 * 扑克牌类
 * 扑克牌显示值为 card
 * 其花色真实值为 realCol 0-3,
 * 其点数真实值为 realPoint 0-11
 * @author rayqian
 *
 */
public class Card {

	private String card;
	private int realCol;
	private int realPoint;
	private static final char[]COLS= {'B','R','M','F'};
	private static final String[]Points= {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public int getRealCol() {
		return realCol;
	}
	public void setRealCol(int realCol) {
		this.realCol = realCol;
	}
	
	public int getRealPoint() {
		return realPoint;
	}
	public void setRealPoint(int realPoint) {
		this.realPoint = realPoint;
	}
	//将牌面转为真实的数值,比较时通过真实数值比较,德州扑克花色无大小
	private void getRealValue(String card) {
		char col=card.charAt(0);
		String point=null;
		if (card.toCharArray().length>2) {
			point = card.charAt(1)+""+card.charAt(2);
		}else {
			point=card.charAt(1)+"";
		}
		this.setRealCol(Arrays.binarySearch(COLS, col));
		this.setRealPoint(Arrays.binarySearch(Points, point));
	}
	// 初始化时直接设置好扑克真实值
	public Card(String card) {
		this.card = card;
		this.getRealValue(card);
	}
	@Override
	public String toString() {
		return "Card [card=" + card + "]";
	}
	
	
	
	
	
	
}
