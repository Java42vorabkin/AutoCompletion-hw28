import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoCompletionTest {
	
	private static final int COUNT = 100000;
	private static final String PREFIX = "prefix";
	LinkedList<String> ll = getRandomList();
	String first = ll.getFirst();
	String last = ll.getLast();
	AutoCompletionImpl instLL = new AutoCompletionImpl(new LinkedList());
	AutoCompletionImpl instHS = new AutoCompletionImpl(new HashSet());
	AutoCompletionImpl instTS = new AutoCompletionImpl(new TreeSet());
	@BeforeEach
	void setUp() throws Exception {
		fillCollection(ll, instLL);
		fillCollection(ll, instHS);
		fillCollection(ll, instTS);
	}

	private void fillCollection(Collection<String> source, AutoCompletionImpl dest) {
		Iterator<String> itr = source.iterator();
		while(itr.hasNext()) {
			dest.addWord(itr.next());
		}
	}
	
	@Test
	void performanceLLTest() {
		fillCollection(ll, instLL);
		Collection<String> llRemoved = instLL.getCompletionOptions(PREFIX);
		System.out.println("ll="+llRemoved.size());
	}
	
	@Test
	void performanceHSTest() {
		fillCollection(ll, instHS);
		Collection<String> hsRemoved = instHS.getCompletionOptions(PREFIX);
		System.out.println("hs="+hsRemoved.size());
	}
	
	@Test
	void performanceTSTest() {
		fillCollection(ll, instTS);
		Collection<String> tsRemoved = instTS.getCompletionOptions(PREFIX);
		System.out.println("ts="+tsRemoved.size());
	}
	@Test
	void removeWordTest() {
		assertTrue(instLL.removeWord(first));
		assertTrue(instLL.removeWord(last));
		assertFalse(instLL.removeWord("xxxx"));
		assertTrue(instHS.removeWord(first));
		assertTrue(instHS.removeWord(last));
		assertFalse(instHS.removeWord("xxxx"));
		assertTrue(instTS.removeWord(first));
		assertTrue(instTS.removeWord(last));
		assertFalse(instTS.removeWord("xxxx"));
	}
	
	private LinkedList<String> getRandomList() {
		LinkedList<String> res = new LinkedList<>();
		for(int i=0; i<COUNT; i++) {
			if(i % (COUNT/100) == 0) {
				res.add(PREFIX + getRandomWord());
			} else {
				res.add(getRandomWord());
			}
		}
		return res;
	}
	private String getRandomWord() {
		String alphaBet = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		StringBuilder word = new StringBuilder();
		int len = rand.nextInt(20) + 2;
		for(int i=0; i<len; i++) {
			int index = rand.nextInt(alphaBet.length()-1);
			word.append(alphaBet.charAt(index));
		}
		return word.toString();
	}
}
