package trie;

import java.util.HashMap;

public class Node {
	String letter;
	boolean isWord;

	HashMap<String, Node> children = new HashMap<String, Node>();

	
	public Node(String letter) {
		this.letter = letter;
	}

}
