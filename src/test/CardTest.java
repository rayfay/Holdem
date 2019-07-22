package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import entity.Card;

public class CardTest {
	
	@Test
	public void cardTest() {
		Card c1 = new Card("BA");
		Card c2 = new Card("RA");
		Card c3 = new Card("F6");
		List<Card> list = new ArrayList<Card>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.sort(null);
		list.forEach(System.out::println);
	}
}
