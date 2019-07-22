package entity;
import java.util.Arrays;
/**
 * 扑克牌类
 * 扑克牌显示值为 card
 * 其花色真实值为 realCol 0-3,
 * 其点数真实值为 realPoint 0-11
 * @author rayqian
 *
 */
public class Card implements Comparable<Card>{

	private String card;
	private int realCol;
	private int realPoint;
	private static final String[]COLS= {"B","R","M","F"};
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
		String col=card.charAt(0)+"";
		String point=null;
		if (card.toCharArray().length>2) {
			point = card.charAt(1)+""+card.charAt(2);
		}else {
			point=card.charAt(1)+"";
		}
		System.out.println(col+" "+point);
		int indexC = this.getIndex(COLS, col);
		int indexP = this.getIndex(Points, point);
		this.setRealCol(indexC);
		this.setRealPoint(indexP);
//		System.out.println(card+":"+this.getRealCol()+" "+this.getRealPoint());
	}
	// 初始化时直接设置好扑克真实值
	public Card(String card) {
		this.card = card;
		this.getRealValue(card);
	}
	@Override
	public String toString() {
		return card;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		return true;
	}
	// 继承comparable 接口 实现牌面大小比较
	@Override
	public int compareTo(Card c) {
		return (this.getRealCol()+this.getRealPoint()*10)-(c.getRealCol()+c.getRealPoint()*10);
	}
	
	private int getIndex(Object[] objs,Object o) {
		for(int i=0;i<objs.length;i++) {
			if (o.equals(objs[i])) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	
	
	
	
	
}
