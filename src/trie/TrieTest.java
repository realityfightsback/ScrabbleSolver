package trie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TrieTest {

	Trie trie = null;

	@Before
	public void setUp() {
		trie = new Trie();
	}

	@Test
	public void test() {
		trie.addWord("cat");
		trie.addWord("dog");
		trie.addWord("dogfight");
		trie.addWord("automobile");

		assertTrue(trie.isAWord("cat"));
		assertFalse(trie.isAWord("cats"));
		assertTrue(trie.isAWord("dogfight"));
		
		assertTrue(trie.startsAValidWord("dog"));
		assertTrue(trie.startsAValidWord("dogf"));
		assertTrue(trie.startsAValidWord("au"));
		
		assertFalse(trie.isAWord("dogf"));

	}

}
