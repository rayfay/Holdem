package test;

import java.util.List;

import org.junit.Test;

import entity.Card;
import entity.Poker;

public class PokerTest {

	@Test
	public void test() {
		Poker p = new Poker();
		p.cutCard(5);
		List<Card> list = p.getPoker();
		list.forEach(System.out::println);
	}
}
