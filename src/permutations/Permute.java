package permutations;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.MathUtils;

import trie.Trie;

public class Permute {

	static Trie trie = null;
	static HashMap<Integer, String> fixedPositions = new HashMap<Integer, String>();

	public static void main(String[] args) throws IOException {
		trie = new Trie();

		// execute("", "p", "a");
		// execute("", "p", "ad");
		long start = System.currentTimeMillis();

		fixedPositions.put(4, "e");
		fixedPositions.put(5, "d");
		permutation("ventdbs");


		System.out.println("Execution time: "
				+ (System.currentTimeMillis() - start));

	}

	public static void permutation(String str) {
		permutation("", str, 0);
	}

	private static void permutation(String prefix, String str, int depth) {


		for (int i = 0; i < str.length(); i++) {
			if (trie.startsAValidWord(prefix) || prefix.equals("")) {
				if (trie.isAWord(prefix)) {
					System.out.println(prefix);
				}
				
				String fixedLetter = "";
				do {
					fixedLetter = fixedPositions.get(depth);
					if (fixedLetter != null) {
						prefix += fixedLetter;
						depth++;
					}
				} while (fixedLetter != null);

				permutation(prefix + str.charAt(i),
						str.substring(0, i) + str.substring(i + 1, str.length()),
						depth + 1);
			} else {
				return;
			}

		}
	}


}
