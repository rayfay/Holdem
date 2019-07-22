package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Poker {
	private List<Card> poker = new ArrayList<Card>();
	private static final String[]COLS= {"B","R","M","F"};
	private static final String[]Points= {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	
	
	public List<Card> getPoker() {
		return poker;
	}

	public void setPoker(List<Card> poker) {
		this.poker = poker;
	}

	// 扑克初始化
	public void initPoker() {
		for(String col:COLS) {
			for(String p:Points) {
				String card=col+p;
				Card c = new Card(card);
				poker.add(c);
			}
		}
	}
	
	// 洗牌方法
	public void shuffle() {
		Collections.shuffle(poker);
	}
	
	// 切牌方法
	public void cutCard(int index) {
		
		List<Card> fpoker=poker.subList(0, index);
		List<Card> bpoker=poker.subList(index,poker.size()-1 );
		List<Card> newPoker = new ArrayList<Card>();
		newPoker.addAll(bpoker);
		newPoker.addAll(fpoker);
		this.setPoker(newPoker);
		
	}
	// 发牌
	public Card getCard() {
		if (poker.size()>0)
			return poker.remove(0);
		else {
			this.initPoker();
			return this.getCard();
		}
			
	}
	//牌面鉴定
	public int getCardSign(List<Card> cards) {
		cards.sort(null);
		Card c1 = cards.get(0);
		Card c2 = cards.get(1);
		Card c3 = cards.get(2);
		Card c4 = cards.get(3);
		Card c5 = cards.get(4);		
		//同花
		if(this.isStraight(cards)) {
			if(this.isFlush(cards)) {
				//同花顺
				return 8;
			}
			return 5;
		}
		// 顺子
		if(this.isFlush(cards)) {
			return 4;
		}
		//满堂彩判定 3+2
		if(this.isFullHouse(cards)) {
			return 6;
		}
		//4张判定
		if(this.isFourOfAKind(cards)) {
			return 7;
		}
		//3张
		if(this.isThreeOfAKind(cards)) {
			return 3;
		}
		//2 对
		if(this.isTwoPair(cards)) {
			return 2;
		}
		//1 对
		if(this.isOnePair(cards)) {
			return 1;
		}
		//散牌
		return 0;
	}
	//同花 判定
	private boolean isStraight(List<Card> cards) {
		cards.sort(null);
		Card c1 = cards.get(0);
		Card c2 = cards.get(1);
		Card c3 = cards.get(2);
		Card c4 = cards.get(3);
		Card c5 = cards.get(4);	
		if(c1.getRealCol()==c2.getRealCol()&&c2.getRealCol()==c3.getRealCol()
				&&c3.getRealCol()==c4.getRealCol()&&c4.getRealCol()==c5.getRealCol()) {
			return true;
		}
		return false;
	}
	// 顺子 判定
	private boolean isFlush(List<Card> cards) {
		cards.sort(null);
		Card c1 = cards.get(0);
		Card c2 = cards.get(1);
		Card c3 = cards.get(2);
		Card c4 = cards.get(3);
		Card c5 = cards.get(4);	
		if(c1.getRealPoint()+1==c2.getRealPoint()&&c2.getRealPoint()+1==c3.getRealPoint()
				&&c3.getRealPoint()+1==c4.getRealPoint()&&c4.getRealPoint()+1==c5.getRealPoint()) {
			return true;
		}
		// 特殊情况 A 可以做最大也可以做最小
		if(c5.getRealPoint()==13) {
			if(c2.getRealPoint()==2&&c3.getRealPoint()==3&&
					c4.getRealPoint()==4&&c1.getRealPoint()==5) {
				return true;
			}
		}
		return false;
	}
	// 满堂彩 判定
	private boolean isFullHouse(List<Card> cards) {
		cards.sort(null);
		Card c1 = cards.get(0);
		Card c2 = cards.get(1);
		Card c3 = cards.get(2);
		Card c4 = cards.get(3);
		Card c5 = cards.get(4);	
		// 123+45 or 12+345
		if((c1.getRealPoint()==c2.getRealPoint()&&c2.getRealPoint()==c3.getRealPoint()
				&& c4.getRealPoint()==c5.getRealPoint())||
				(c1.getRealPoint()==c2.getRealPoint()&&c3.getRealPoint()==c4.getRealPoint()
						&& c4.getRealPoint()==c5.getRealPoint())) {
			return true;
		}
		return false;
	}
	// 4张
	private boolean isFourOfAKind(List<Card> cards) {
		cards.sort(null);
		Card c1 = cards.get(0);
		Card c2 = cards.get(1);
		Card c3 = cards.get(2);
		Card c4 = cards.get(3);
		Card c5 = cards.get(4);
		if((c1.getRealPoint()==c2.getRealPoint()&&c2.getRealPoint()==c3.getRealPoint()
				&&c3.getRealPoint()==c4.getRealPoint())||(c2.getRealPoint()==c3.getRealPoint()
				&&c4.getRealPoint()==c4.getRealPoint()&&c4.getRealPoint()==c5.getRealPoint())) {
			return true;
		}
		return false;
	}
	// 3张
	private boolean isThreeOfAKind(List<Card> cards) {
		cards.sort(null);
		Card c1 = cards.get(0);
		Card c2 = cards.get(1);
		Card c3 = cards.get(2);
		Card c4 = cards.get(3);
		Card c5 = cards.get(4);
		// 这里只需判断是 123 相等还是 345 相等即可,3 张判定放在满堂彩和 4 张之后即可无需再次比较
//			if(this.isFourOfAKind(cards)) {
//				return false;
//			}else if(this.isFullHouse(cards)) {
//				return false;
//			}
		if((c1.getRealPoint()==c2.getRealPoint()&&c2.getRealPoint()==c3.getRealPoint())||
				c3.getRealPoint()==c4.getRealPoint()&&c4.getRealPoint()==c5.getRealPoint()) {
			return true;
		}
		return false;
	}
	//2对
	private boolean isTwoPair(List<Card> cards) {
		cards.sort(null);
		Card c1 = cards.get(0);
		Card c2 = cards.get(1);
		Card c3 = cards.get(2);
		Card c4 = cards.get(3);
		Card c5 = cards.get(4);
		if(c1.getRealPoint()==c2.getRealPoint()&&
				(c3.getRealPoint()==c4.getRealPoint()||c4.getRealPoint()==c5.getRealPoint())) {
			return true;
		}
		if(c2.getRealPoint()==c3.getRealPoint()&&c4.getRealPoint()==c5.getRealPoint()) {
			return true;
		}
		return false;
	}
	// 1对
	private boolean isOnePair(List<Card> cards) {
		cards.sort(null);
		Card c1 = cards.get(0);
		Card c2 = cards.get(1);
		Card c3 = cards.get(2);
		Card c4 = cards.get(3);
		Card c5 = cards.get(4);
		if(c1.getRealPoint()==c2.getRealPoint()||c2.getRealPoint()==c3.getRealPoint()
				||c3.getRealPoint()==c4.getRealPoint()||c4.getRealPoint()==c5.getRealPoint()) {
			return true;
		}
		
		return false;
	}
	
	//c1>c2 返回 ture
	public boolean compareCards(List<Card> cardFrist, List<Card> cardSecond) {
		int sign1 = this.getCardSign(cardFrist);
		int sign2 = this.getCardSign(cardSecond);
		if(sign1-sign2>0) {
			return true;
		}else if(sign1-sign2==0) {
			cardFrist.sort(null);
			cardSecond.sort(null);
			for(int i=cardFrist.size()-1;i>=0;i--) {
				if(cardFrist.get(i).compareTo(cardSecond.get(i))>0) {
					return true;
				}
			}
		}
		return false;
	}
		
	public Poker() {
		this.initPoker();
	}
	
	
}
