import java.util.*;

public class WordFilter {
	static class TrieNode {
		Map<Integer, TrieNode> children;
		int weight;

		public TrieNode() {
			this.children = new HashMap<>();
		}
	}
	
	TrieNode root;
	
	public WordFilter(String[] words) {
		
	}

	public int getHashCode(char prefixChar, char suffixChar) {
		return (prefixChar - '`') * 27 + (suffixChar - '`');
	}

	public void insertPrefix(TrieNode cur, String word, int weight, int start) {
		for (int i = start; i <= word.length(); ++i) {
			char c = word.charAt(i);
			cur = cur.children.computeIfAbsent(getHashCode(c, '`'), k -> new TrieNode());
			cur.weight = weight;
		}
	}

	public void insertSuffix(TrieNode cur, String word, int weight, int start) {
		for (int i = start; i >= 0; --i) {
			char c = word.charAt(i);
			cur = cur.children.computeIfAbsent(getHashCode('`', c), k -> new TrieNode());
			cur.weight = weight;
		}
	}

	public TrieNode addNode(TrieNode cur, char prefixChar, char suffixChar, int weight) {
		cur = cur.children.computeIfAbsent(getHashCode(prefixChar, suffixChar), k -> new TrieNode());
		cur.weight = weight;
		return cur;
	}

	public int search(String prefix, String suffix) {
		TrieNode cur = this.root;
		for (int i = 0, j = suffix.length() - 1; i < prefix.length() || j >= 0; i++, j--) {
			char c1 = i < prefix.length() ? prefix.charAt(i) : '`';
			char c2 = j >= 0 ? suffix.charAt(j) : '`';
			int code = getHashCode(c1, c2);
			cur = cur.children.get(code);
			if (cur == null) {
				return -1;
			}
		}
		return cur.weight;
	}
}
