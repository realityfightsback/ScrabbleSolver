package trie;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import constants.Constants;

public class Trie {

	Node root = new Node("\0");

	public Trie() throws IOException {
		super();
		addFile();
		root.children.put("", new Node(""));
	}

	public void addFile() throws IOException {
		List<String> lines = FileUtils.readLines(new File(Constants.FILE_DIR
				+ "dictionary.txt"));
		for (String line : lines) {
			addWord(line);
		}
	}

	public void addWord(String s) {
		createPath(s);
	}

	public boolean startsAValidWord(String s) {
		return tracePath(s) != null;
	}

	public boolean isAWord(String s) {
		Node n = tracePath(s);
		return n != null && n.isWord;
	}

	/**
	 * Follow path provided.
	 * 
	 * @param s
	 * @return Null if the path is invalid, a Node otherwise
	 */
	private Node tracePath(String s) {
		HashMap<String, Node> childListToUse = root.children;
		Node desiredNode = null;

		for (int i = 0; i < s.length(); i++) {
			String letter = s.substring(i, i + 1);

			desiredNode = childListToUse.get(letter);

			if (desiredNode == null) {
				return null;
			}

			childListToUse = desiredNode.children;

		}
		return desiredNode;

	}

	private void createPath(String s) {
		HashMap<String, Node> childListToUse = root.children;
		Node desiredNode = null;

		for (int i = 0; i < s.length(); i++) {
			String letter = s.substring(i, i + 1);

			desiredNode = childListToUse.get(letter);

			if (desiredNode == null) {
				desiredNode = new Node(letter);
				childListToUse.put(letter, desiredNode);
			}

			childListToUse = desiredNode.children;
		}

		// terminates in a word
		desiredNode.isWord = true;
	}

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		trie.addWord("cat");
		trie.addWord("dog");

		System.out.println(trie.startsAValidWord("d"));
	}
}
