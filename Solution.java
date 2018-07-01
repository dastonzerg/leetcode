import java.lang.Math;
import java.util.*;

import javax.xml.soap.Node;

import java.lang.StringBuilder;

public class Solution {
	// climbStairs
	// ***start***

	public static int climbStairs(int n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		else {
			return climbStairs(n - 1) + climbStairs(n - 2);
		}
	}

	public static class TreeNode {
		int val;
		TreeNode left = null;
		TreeNode right = null;

		TreeNode(int x) {
			val = x;
		}
	}

	// ****end****

	// minDepthBinary
	// ***start***
	public static int minDepthBinary(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return 1;
		}

		if (root.left == null) {
			return 1 + minDepthBinary(root.right);
		}
		if (root.right == null) {
			return 1 + minDepthBinary(root.left);
		}
		return 1 + Math.min(minDepthBinary(root.left), minDepthBinary(root.right));
	}

	// ****end****

	// maxDepthBinary
	// ***start***
	public static int maxDepthBinary(TreeNode root) {
		return root == null ? 0 : 1 + Math.max(maxDepthBinary(root.left), maxDepthBinary(root.right));
	}

	public static int hammingWeight(int n) {
		int counter = 0;
		while (n != 0) {
			counter += n & 1;
			n = n >>> 1;
		}
		return counter;
	}

	// ****end****

	// moving average
	// ***start***
	public static class MovingAverage {
		final int windowSize;
		double sum = 0.0;
		Queue<Integer> window = new LinkedList<>();

		MovingAverage(int windowSize) {
			this.windowSize = windowSize;
		}

		public double next(int val) {
			window.add(val);
			sum += val;
			if (window.size() > windowSize) {
				sum -= window.poll();
			}
			return sum / window.size();
		}
	}

	// ****end****

	// string compare Stack version
	// ***start***
	public static boolean stackStringCompare(String s, String t) {
		return removeBackSpace(s).equals(removeBackSpace(t));
	}

	private static Stack<Character> removeBackSpace(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i <= s.length() - 1; ++i) {
			if (s.charAt(i) == '#') {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			}
			else {
				stack.push(s.charAt(i));
			}
		}
		return stack;
	}

	// ****end****

	// string compare StringBuilder version
	// ***start***
	public static boolean sbStringCompare(String s, String t) {
		return sbRemoveBackspace(s).equals(sbRemoveBackspace(t));
	}

	private static String sbRemoveBackspace(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= s.length() - 1; ++i) {
			if (s.charAt(i) == '#') {
				if (sb.length() != 0) {
					sb.deleteCharAt(sb.length() - 1);
				}
			}
			else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}

	// ****end****

	// valid parentheses
	// ***start***

	public static boolean isValidParentheses(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i <= s.length() - 1; ++i) {
			char currentChar = s.charAt(i);
			if (currentChar == ')' || currentChar == '}' || currentChar == ']') {
				if (stack.empty() || (currentChar == ')' && stack.peek() != '(')
						|| (currentChar == '}' && stack.peek() != '{') || (currentChar == ']' && stack.peek() != '[')) {
					return false;
				}
				else {
					stack.pop();
				}
			}
			else {
				stack.push(currentChar);
			}
		}
		if (stack.empty()) {
			return true;
		}
		else {
			return false;
		}
	}

	// ****end****

	// two sum
	// ***start***

	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> valToIndex = new HashMap<>();
		for (int i = 0; i <= nums.length - 1; ++i) {
			if (!valToIndex.containsKey(target - nums[i])) {
				valToIndex.put(nums[i], i);
			}
			else {
				return new int[] { valToIndex.get(target - nums[i]), i };
			}
		}
		return new int[] {};
	}

	// ****end****

	// Hamming Distance
	// ***start***
	public static int hammingDistance(int x, int y) {
		int count = 0;
		while (x != 0 || y != 0) {
			if ((x % 2 ^ y % 2) == 1) {
				count += 1;
			}
			x = x >>> 1;
			y = y >>> 1;
		}
		return count;
	}

	// ****end****

	// 406 Queue Reconstruction by Height
	// ***start***

	public static void reconstructQueue(int[][] people) {
		Arrays.sort(people, new Comparator<Object>() {
			public int compare(Object p1, Object p2) {
				int[] person1 = (int[]) p1;
				int[] person2 = (int[]) p2;

				if (person1[0] != person2[0]) {
					return person2[0] - person1[0];
				}
				return person1[1] - person2[1];
			}
		});
		int len = people.length;
		int count;
		int position;
		int[] temp;
		for (int i = 1; i <= len - 1; i++) {
			count = i;
			position = people[i][1];
			while (count > position) {
				temp = people[count - 1];
				people[count - 1] = people[count];
				people[count] = temp;
				count--;
			}
		}

	}

	// ****end****

	// 494 Target Sum
	// ***start***
	public static int findTargetSumWays(int[] nums, int S) {
		int sum = 0;
		for (int i = 0; i <= nums.length - 1; i++) {
			sum += nums[i];
		}
		if (S > sum || (sum + S) % 2 == 1) {
			return 0;
		}
		return subsetSum(nums, (S + sum) / 2);
	}

	private static int subsetSum(int[] nums, int S) {
		int[] dp = new int[S + 1];
		dp[0] = 1;
		for (int i = 0; i <= nums.length - 1; i++) {
			for (int j = S; j >= nums[i]; j--) {
				dp[j] += dp[j - nums[i]];
			}
		}
		return dp[S];
	}

	// ****end****

	// 416 Partition Equal Subset Sum
	// ***start***
	public static boolean canPartition(int[] nums) {
		int sum = 0;
		for (int i = 0; i <= nums.length - 1; i++) {
			sum += nums[i];
		}
		if (sum % 2 == 1) {
			return false;
		}
		int flag = subsetSum(nums, sum / 2);
		if (flag == 0) {
			return false;
		}
		return true;
	}

	// ****end****

	// 448 Find All Numbers Disappeared in an Array
	// ***start***

	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> resList = new ArrayList<>();
		int temp;
		int tempIndex;
		for (int i = 0; i <= nums.length - 1; i++) {
			tempIndex = nums[i] - 1;
			if (nums[i] != nums[tempIndex]) {
				temp = nums[i];
				nums[i] = nums[tempIndex];
				nums[tempIndex] = temp;
				i--;
			}
		}
		for (int i = 0; i <= nums.length - 1; i++) {
			if (nums[i] != i + 1) {
				resList.add(i + 1);
			}
		}
		return resList;
	}

	// ****end****

	// 402 Remove K Digits
	// ***start***
	public static String removeKDigits(String num, int k) {
		return new String("");
	}
	// ****end****

	// 26 Remove Duplicates
	// ***start***
	public static int removeDuplicates(int[] a) {
		if (a.length == 0) {
			return 0;
		}
		int end = 0;
		for (int i = 1; i <= a.length - 1; i++) {
			if (a[i] != a[i - 1]) {
				end++;
				a[end] = a[i];
			}
		}
		return end + 1;
	}
	// ****end****

	// 27 Remove Element
	// ***start***
	public int removeElement(int[] nums, int val) {
		int end = -1;
		for (int i = 0; i <= nums.length - 1; i++) {
			if (nums[i] != val) {
				end++;
				nums[end] = nums[i];
			}
		}
		return end + 1;
	}
	// ****end****

	// 80 Remove Duplicates from Sorted Array II
	// ***start***
	public int removeDuplicatesII(int[] nums) {
		if (nums.length <= 2) {
			return nums.length;
		}
		int end = 1;
		for (int i = 2; i <= nums.length - 1; i++) {
			if (nums[i] != nums[end - 1]) {
				end++;
				nums[end] = nums[i];
			}
		}
		return end + 1;
	}
	// ****end****

	// 38 Count and Say
	// ***start***
	public static String countAndSay(int n) {
		String result = "1";
		int count;
		for (int row = 2; row <= n; row++) {
			StringBuilder sb = new StringBuilder();
			count = 1;
			for (int i = 1; i <= result.length() - 1; i++) {
				if (result.charAt(i) != result.charAt(i - 1)) {
					sb.append(count);
					sb.append(result.charAt(i - 1));
					count = 1;
				}
				else {
					count++;
				}
			}
			sb.append(count);
			sb.append(result.charAt(result.length() - 1));
			result = sb.toString();
		}

		return result;
	}
	// ****end****

	// 228 Summary Ranges
	// ***start***
	public static List<String> summaryRanges(int[] nums) {
		if (nums.length == 0) {
			return new ArrayList<>();
		}
		int start = nums[0];
		List<String> result = new ArrayList<>();
		for (int i = 1; i <= nums.length - 1; i++) {
			if (nums[i] != nums[i - 1] + 1) {
				result.add(group(start, nums[i - 1]));
				start = nums[i];
			}
		}
		result.add(group(start, nums[nums.length - 1]));
		return result;
	}

	private static String group(int start, int end) {
		if (start == end) {
			return start + "";
		}
		else {
			return start + "->" + end;
		}
	}

	// 163 Missing Ranges
	public List<String> findMissingRanges(int[] a, int lower, int upper) {
		List<String> result = new ArrayList<>();
		if (a.length == 0) {
			result.add(group(lower, upper));
			return result;
		}
		if (a[0] != lower) {
			result.add(group(lower, a[0] - 1));
		}
		for (int i = 1; i <= a.length - 1; i++) {
			if (a[i] != a[i - 1] + 1 && a[i] != a[i - 1]) {
				result.add(group(a[i - 1] + 1, a[i] - 1));
			}
		}
		if (a[a.length - 1] != upper) {
			result.add(group(a[a.length - 1] + 1, upper));
		}
		return result;
	}

	// 151 Reverse Words in a String
	// ***start***
	public String reverseWords(String s) {
		List<String> words = wordsList(s);
		if (words.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.insert(0, " " + word);
		}
		sb.deleteCharAt(0);
		return sb.toString();
	}

	public List<String> wordsList(String s) {
		if (s.length() == 0) {
			return new ArrayList<String>();
		}
		int start = s.charAt(0) == ' ' ? 1 : 0;
		List<String> result = new ArrayList<>();

		for (int i = 1; i <= s.length() - 1; i++) {
			if (s.charAt(i) == ' ') {
				if (s.charAt(i - 1) != ' ') {
					result.add(s.substring(start, i));
				}
				start = i + 1;
			}
		}

		if (s.charAt(s.length() - 1) != ' ') {
			result.add(s.substring(start, s.length()));
		}

		return result;
	}
	// ****end****

	// 205 Isomorphic Strings
	// ***start***
	public boolean isIsomorphic(String s, String t) {
		return isMappable(s, t) && isMappable(t, s);
	}

	public boolean isMappable(String s, String t) {
		if (s.length() == 0) {
			return true;
		}
		char[] map = new char[256];
		for (int i = 0; i <= s.length() - 1; i++) {
			char cS = s.charAt(i);
			char cT = t.charAt(i);
			if (map[cS] == 0) {
				map[cS] = cT;
			}
			else {
				if (map[cS] != cT) {
					return false;
				}
			}
		}
		return true;
	}

	// ****end****

	// 299 Bulls and Cows
	// ***start***
	public String getHint(String secret, String guess) {
		int[] map = new int[10];
		int aCount = 0;
		int bCount = 0;
		for (int i = 0; i <= secret.length() - 1; i++) {
			char cSecret = secret.charAt(i);
			char cGuess = guess.charAt(i);
			if (cSecret == cGuess) {
				aCount++;
			}
			else {
				map[cSecret - '0']++;
			}
		}
		for (int i = 0; i <= secret.length() - 1; i++) {
			char cSecret = secret.charAt(i);
			char cGuess = guess.charAt(i);
			if (cSecret != cGuess) {
				if (map[cGuess - '0'] != 0) {
					bCount++;
					map[cGuess - '0']--;
				}
			}
		}
		return aCount + "A" + bCount + "B";
	}
	// ****end****

	// 36 Valid Sudoku
	// ***start***
	public boolean isValidSudoku(char[][] board) {
		int num;
		char c;
		for (int row = 0; row <= 8; row++) {
			boolean[] set = new boolean[9];
			for (int col = 0; col <= 8; col++) {
				c = board[row][col];
				if (c != '.') {
					num = c - '1';
					if (set[num] == false) {
						set[num] = true;
					}
					else {
						return false;
					}
				}
			}
		}

		for (int col = 0; col <= 8; col++) {
			boolean[] set = new boolean[9];
			for (int row = 0; row <= 8; row++) {
				c = board[row][col];
				if (c != '.') {
					num = c - '1';
					if (set[num] == false) {
						set[num] = true;
					}
					else {
						return false;
					}
				}
			}
		}

		for (int blockRow = 0; blockRow <= 2; blockRow++) {
			for (int blockCol = 0; blockCol <= 2; blockCol++) {
				boolean[] set = new boolean[9];
				for (int row = 0; row <= 2; row++) {
					for (int col = 0; col <= 2; col++) {
						c = board[blockRow * 3 + row][blockCol * 3 + col];
						if (c != '.') {
							num = board[blockRow * 3 + row][blockCol * 3 + col] - '1';
							if (set[num] == false) {
								set[num] = true;
							}
							else {
								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

	// ****end****

	// 554 Brick Wall
	// ***start***
	public int leastBricks(List<List<Integer>> wall) {
		HashMap<Integer, Integer> gapBypass = new HashMap<>();
		for (List<Integer> bricks : wall) {
			int gap = 0;
			int width;
			Iterator<Integer> ite = bricks.iterator();
			while (ite.hasNext()) {
				width = ite.next();
				gap += width;
				if (!ite.hasNext()) {
					break;
				}
				if (!gapBypass.containsKey(gap)) {
					gapBypass.put(gap, 1);
				}
				else {
					gapBypass.put(gap, gapBypass.get(gap) + 1);
				}
			}
		}

		int numOfBrickPassed = 0;
		for (Map.Entry<Integer, Integer> entry : gapBypass.entrySet()) {
			if (entry.getValue() > numOfBrickPassed) {
				numOfBrickPassed = entry.getValue();
			}
		}
		return wall.size() - numOfBrickPassed;
	}

	// ****end****

	// 781 Rabbits in Forest
	// ***start***
	public int numRabbits(int[] answers) {
		int result = 0;
		HashMap<Integer, Integer> groupToCount = new HashMap<>();

		for (int ans : answers) {
			ans++;
			if (!groupToCount.containsKey(ans)) {
				groupToCount.put(ans, 1);
			}
			else {
				if (groupToCount.get(ans) == ans) {
					result += ans;
					groupToCount.put(ans, 1);
				}
				else {
					groupToCount.put(ans, groupToCount.get(ans) + 1);
				}
			}
		}

		for (Map.Entry<Integer, Integer> entry : groupToCount.entrySet()) {
			result += entry.getKey();
		}

		return result;
	}

	// ****end****

	// 356 Line Reflection
	// ***start***
	public boolean isReflected(int[][] points) {
		if (points.length == 0) {
			return true;
		}

		Map<Integer, Set<Integer>> yToXs = new HashMap<>();
		for (int[] xY : points) {
			if (!yToXs.containsKey(xY[1])) {
				Set<Integer> xS = new HashSet<>();
				xS.add(xY[0]);
				yToXs.put(xY[1], xS);
			}
			else {
				yToXs.get(xY[1]).add(xY[0]);
			}
		}

		Iterator<Map.Entry<Integer, Set<Integer>>> ite = yToXs.entrySet().iterator();
		Set<Integer> set0 = ite.next().getValue();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int x : set0) {
			if (x < min) {
				min = x;
			}
			if (x > max) {
				max = x;
			}
		}
		double mid = min + (max - min) / 2.0;

		for (Map.Entry<Integer, Set<Integer>> entry : yToXs.entrySet()) {
			Set<Integer> xS = entry.getValue();
			for (int x : xS) {
				if (!xS.contains((int) (mid * 2 - x))) {
					return false;
				}
			}
		}

		return true;
	}

	// ****end****

	// 819 Most Commond Words
	// ***start***
	private boolean isDelimiter(char c) {
		return c == '?' || c == '!' || c == ',' || c == '.' || c == '\'' || c == ';' || c == ' ';
	}

	private void notBannedAddToMap(String word, Set<String> bannedSet, Map<String, Integer> wordToCount) {
		if (!bannedSet.contains(word)) {
			if (!wordToCount.containsKey(word)) {
				wordToCount.put(word, 1);
			}
			else {
				wordToCount.put(word, wordToCount.get(word) + 1);
			}
		}
	}

	public String mostCommonWord(String paragraph, String[] banned) {
		paragraph = paragraph.toLowerCase();
		Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
		Map<String, Integer> wordToCount = new HashMap<>();
		int start = isDelimiter(paragraph.charAt(0)) ? 1 : 0;
		for (int i = 1; i <= paragraph.length() - 1; i++) {
			if (isDelimiter(paragraph.charAt(i))) {
				if (!isDelimiter(paragraph.charAt(i - 1))) {
					String word = paragraph.substring(start, i);
					notBannedAddToMap(word, bannedSet, wordToCount);
				}
				start = i + 1;
			}
		}
		if (!isDelimiter(paragraph.charAt(paragraph.length() - 1))) {
			String word = paragraph.substring(start, paragraph.length());
			notBannedAddToMap(word, bannedSet, wordToCount);
		}

		int count = 0;
		String mostCommonWord = "";
		for (Map.Entry<String, Integer> entry : wordToCount.entrySet()) {
			if (entry.getValue() > count) {
				mostCommonWord = entry.getKey();
				count = entry.getValue();
			}
		}

		return mostCommonWord;
	}

	// ****end****

	// 239 Sliding Window Maximum
	// ***start***
	public static int[] maxSlidingWindow(int[] nums, int k) {
		if (k == 1 || nums.length == 0) {
			return nums;
		}

		int[] maxes = new int[nums.length - k + 1];
		Deque<Integer> deque = new LinkedList<>();
		deque.addLast(nums[0]);
		for (int j = 1; j <= k - 1; j++) {
			while (!deque.isEmpty() && nums[j] > deque.getLast()) {
				deque.removeLast();
			}
			deque.addLast(nums[j]);
		}
		maxes[0] = deque.getFirst();

		for (int i = k; i <= nums.length - 1; i++) {
			if (nums[i - k] == deque.getFirst()) {
				deque.removeFirst();
			}
			while (!deque.isEmpty() && nums[i] > deque.getLast()) {
				deque.removeLast();
			}
			deque.addLast(nums[i]);
			maxes[i - k + 1] = deque.getFirst();
			printIntArray(maxes);
		}

		return maxes;
	}

	public static void printIntArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println("");
	}

	public static void printListArray(List<Integer> arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println("");
	}

	// ****end****

	// 833 Find and Replace in String
	// ***start***
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		Map<Integer, int[]> startToEnd = new HashMap<>();
		for (int i = 0; i <= indexes.length - 1; i++) {
			int start = indexes[i];
			int end = start + sources[i].length() - 1;
			if (end > S.length() - 1) {
				break;
			}
			if (S.substring(start, end + 1).equals(sources[i])) {
				int[] endAndTarget = new int[] { end, i };
				startToEnd.put(start, endAndTarget);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= S.length() - 1;) {
			if (!startToEnd.containsKey(i)) {
				sb.append(S.charAt(i));
				i++;
			}
			else {
				sb.append(targets[startToEnd.get(i)[1]]);
				i = startToEnd.get(i)[0] + 1;
			}
		}

		return sb.toString();
	}

	// ****end****

	// 187 Repeated DNA Sequence
	// ***start***
	private int[] getZValues(String s) {
		int[] z = new int[s.length()];
		int left = 0;
		int right = 0;
		for (int i = 1; i <= s.length() - 1; i++) {
			if (i > right) {
				if (s.charAt(i) == s.charAt(0)) {
					left = i;
					right = i;
					z[i]++;
				}
				else {
					break;
				}
				for (int j = 1; j <= s.length() - 1 - i; j++) {
					if (s.charAt(j) == s.charAt(i + j)) {
						right = i + j;
						z[i]++;
					}
					else {
						break;
					}
				}
			}
			else { // i<=right
				if (i + z[i - left] - 1 <= right) {
					z[i] = z[i - left];
				}
				else {
					z[i] = right - i + 1;
					left = i;
					for (int j = right + 1; j <= s.length() - 1 - i; j++) {
						if (s.charAt(j) == s.charAt(i + j)) {
							right = i + j;
							z[i]++;
						}
						else {
							break;
						}
					}
				}
			}
		}
		return z;
	}

	public List<String> findRepeatedDnaSequences(String s) {
		Map<String, Integer> patToCount = new HashMap<>();
		for (int i = 0; i <= s.length() - 1 - 9; i++) {
			String subS = s.substring(i, i + 10);
			if (!patToCount.containsKey(subS)) {
				patToCount.put(subS, 1);
			}
			else {
				patToCount.put(subS, patToCount.get(subS) + 1);
			}
		}

		List<String> result = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : patToCount.entrySet()) {
			if (entry.getValue() > 1) {
				result.add(entry.getKey());
			}
		}

		return result;
	}

	// ****end****

	// 735 Asteroid Collision
	// ***start***
	public int[] asteroidCollision(int[] asteroids) {
		if (asteroids.length == 0) {
			return asteroids;
		}

		Stack<Integer> s = new Stack<>();
		s.push(asteroids[0]);

		for (int i = 1; i <= asteroids.length - 1; i++) {
			int cur = asteroids[i];
			while (!s.isEmpty() && s.peek() > 0 && s.peek() + cur < 0) {
				s.pop();
			}

			if (!s.isEmpty() && s.peek() > 0 && s.peek() + cur == 0) {
				s.pop();
			}
			else if (!(!s.isEmpty() && cur < 0 && s.peek() + cur > 0)) {
				s.push(cur);
			}
		}

		int[] result = new int[s.size()];
		int i = 0;
		for (int num : s) {
			result[i] = num;
			i++;
		}
		return result;
	}

	// ****end****

	// 636 Exclusive Time of Functions
	// ***start***
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] result = new int[n];
		Stack<Integer> s = new Stack<>();

		for (String log : logs) {
			String[] tokens = log.split(":");
			if (tokens[1].equals("start")) {
				result[Integer.valueOf(tokens[0])] -= Integer.valueOf(tokens[2]);
				if (!s.isEmpty()) {
					result[s.peek()] += Integer.valueOf(tokens[2]);
				}
				s.push(Integer.valueOf(tokens[0]));
			}
			else {
				result[Integer.valueOf(tokens[0])] += Integer.valueOf(tokens[2]) + 1;
				s.pop();
				if (!s.isEmpty()) {
					result[s.peek()] -= Integer.valueOf(tokens[2]) + 1;
				}
			}
		}

		return result;
	}

	// 解释
	// current stack new stack
	// - start - (- start
	// + end +1 + end +1)

	// ****end****

	// 71 Simplify Path
	// ***start***
	private static void addToStack(String word, Stack<String> s) {
		if (word.equals("..")) {
			if (!s.isEmpty()) {
				s.pop();
			}
		}
		else if (!word.equals(".")) {
			s.push(word);
		}
	}

	public static String simplifyPath(String path) {
		int start = 1;
		Stack<String> s = new Stack<>();
		for (int i = 1; i <= path.length() - 1; i++) {
			if (path.charAt(i) == '/') {
				if (path.charAt(i - 1) != '/') {
					String word = path.substring(start, i);
					addToStack(word, s);
				}
				start = i + 1;
			}
		}
		if (path.charAt(path.length() - 1) != '/') {
			String word = path.substring(start, path.length());
			addToStack(word, s);
		}

		if (s.isEmpty()) {
			return "/";
		}

		StringBuilder sb = new StringBuilder();
		for (String word : s) {
			sb.append("/" + word);
		}
		return sb.toString();
	}

	// ****end****

	// 385 Mini Parser
	// ***start***
	public NestedInteger deserialize(String s) {
		ArrayList<String> commandList = new ArrayList<>();
		int start;
		if (s.charAt(0) == '[') {
			start = 1;
			commandList.add("[");
		}
		else {
			start = 0;
		}
		for (int i = 1; i <= s.length() - 1; i++) {
			if (s.charAt(i) == ',') {
				if (s.charAt(i - 1) != ']') {
					commandList.add(s.substring(start, i));
				}
			}
			if (s.charAt(i) == '[') {
				commandList.add("[");
			}
			if (s.charAt(i) == ']') {
				if (s.charAt(i - 1) != ']' && s.charAt(i - 1) != '[') {
					commandList.add(s.substring(start, i));
				}
				commandList.add("]");
			}
			if (!((s.charAt(i) >= '0' && s.charAt(i) <= '9') || s.charAt(i) == '-')) {
				start = i + 1;
			}
		}
		if (s.charAt(s.length() - 1) >= '0' && s.charAt(s.length() - 1) <= '9') {
			commandList.add(s.substring(start, s.length()));
		}

		Stack<NestedInteger> stack = new Stack<>();
		for (String word : commandList) {
			if (word == "[") {
				NestedInteger ni = new NestedInteger();
				stack.push(ni);
			}
			else if (word == "]") {
				NestedInteger doneNi = stack.pop();
				if (!stack.isEmpty()) {
					stack.peek().add(doneNi);
				}
				else {
					return doneNi;
				}
			}
			else {
				NestedInteger valueNi = new NestedInteger(Integer.valueOf(word));
				if (!stack.isEmpty()) {
					NestedInteger ni = stack.peek();
					ni.add(valueNi);
				}
				else {
					return valueNi;
				}
			}
		}

		return null;
	}

	// ****end****

	// 394 Decode String
	// ***start***
	private class KSb {
		int _k;
		StringBuilder _sb;

		KSb(int n) {
			_k = n;
			_sb = new StringBuilder();
		}

		void append(char c) {
			_sb.append(c);
		}

		void append(StringBuilder sb) {
			_sb.append(sb);
		}

		StringBuilder returnKSb() {
			StringBuilder result = new StringBuilder();
			for (int i = 1; i <= _k; i++) {
				result.append(_sb);
			}
			return result;
		}
	}

	public String decodeString(String s) {
		Stack<KSb> stack = new Stack<>();
		stack.push(new KSb(1));

		int start = 0;
		for (int i = 0; i <= s.length() - 1; i++) {
			if (s.charAt(i) == '[') {
				stack.push(new KSb(Integer.valueOf(s.substring(start, i))));
			}
			else if (s.charAt(i) == ']') {
				StringBuilder sb = stack.pop().returnKSb();
				stack.peek().append(sb);
			}
			else if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) { // chars
				stack.peek().append(s.charAt(i));
			}
			if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
				start = i + 1;
			}
			// numbers: do nothing
		}

		return stack.peek().returnKSb().toString();
	}
	
	// ****end****
}

// 295 Find Median from Data Stream
// ***start***
class MedianFinder {
	PriorityQueue<Integer> _leftMaxHeap;
	PriorityQueue<Integer> _rightMinHeap;
	int _numOfInt;
	double _median;

	/** initialize your data structure here. */
	public MedianFinder() {
		_leftMaxHeap = new PriorityQueue<>(1, new Comparator<Integer>() {
			public int compare(Integer num1, Integer num2) {
				return num2 - num1;
			}
		});
		_rightMinHeap = new PriorityQueue<>();
		_numOfInt = 0;
	}

	public void addNum(int num) {
		if (_numOfInt == 0) {
			_leftMaxHeap.add(num);
			_median = num;
			_numOfInt++;
			return;
		}

		if (_numOfInt % 2 == 0) {
			if (num > _leftMaxHeap.peek()) {
				_rightMinHeap.add(num);
				int fromRightToLeft = _rightMinHeap.poll();
				_leftMaxHeap.add(fromRightToLeft);
			}
			else {
				_leftMaxHeap.add(num);
			}
			_median = _leftMaxHeap.peek();
		}
		else {
			if (num > _leftMaxHeap.peek()) {
				_rightMinHeap.add(num);
			}
			else {
				_leftMaxHeap.add(num);
				int fromLeftToRight = _leftMaxHeap.poll();
				_rightMinHeap.add(fromLeftToRight);
			}
			_median = (_leftMaxHeap.peek() + _rightMinHeap.peek()) / 2.0;
		}

		_numOfInt++;
	}

	public double findMedian() {
		return _median;
	}
}

// ****end****

// 146 LRU Cache
// ***start***
class LRUCache {
	private class Node {
		int _key;
		int _value;
		Node _prev;
		Node _next;

		Node(int key, int value) {
			_key = key;
			_value = value;
			_prev = null;
			_next = null;
		}
	}

	int _capacity;
	HashMap<Integer, Node> _map;
	Node _head;
	Node _tail;

	private void addToTail(Node current) {
		_tail._prev._next = current;
		current._prev = _tail._prev;
		current._next = _tail;
		_tail._prev = current;
	}

	private void moveToTail(Node current) {
		current._prev._next = current._next;
		current._next._prev = current._prev;
		addToTail(current);
	}

	private void removeHead() {
		_head._next._next._prev = _head;
		_head._next = _head._next._next;
	}

	public LRUCache(int capacity) {
		_capacity = capacity;
		_map = new HashMap<>();
		_head = new Node(-1, -1);
		_tail = new Node(-1, -1);
		_head._next = _tail;
		_tail._prev = _head;
	}

	public int get(int key) {
		if (!_map.containsKey(key)) {
			return -1;
		}
		else {
			Node current = _map.get(key);
			moveToTail(current);
			return current._value;
		}
	}

	public void put(int key, int value) {
		if (!_map.containsKey(key)) {
			if (_map.size() == _capacity) {
				_map.remove(_head._next._key);
				removeHead();
			}
			Node current = new Node(key, value);
			_map.put(key, current);
			addToTail(current);
		}
		else {
			Node current = _map.get(key);
			current._value = value;
			moveToTail(current);
		}
	}

	public void printLinkedList() {
		Node current = _head._next;
		while (current._value > 0) {
			System.out.print("key:" + current._key + " value:" + current._value + " ");
			current = current._next;
		}
		System.out.println("");
	}

	public void printMap() {
		for (Map.Entry<Integer, Node> entry : _map.entrySet()) {
			System.out.print(entry.getKey() + " ");
		}
		System.out.println("");
	}

	// ****end****
}

// 716 Max Stack
// ***start***
class MaxStack {
	Stack<Integer> stack;

	public MaxStack() {
		stack = new Stack<>();
	}

	public void push(int x) {
		stack.add(x);
	}

	public int pop() {
		return stack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int peekMax() {
		int counter = 0;
		int pos = 1;
		int maxSoFar = Integer.MIN_VALUE;
		Stack<Integer> visitedStack = new Stack<>();
		while (!stack.isEmpty()) {
			int num = stack.pop();
			visitedStack.push(num);
			if (num > maxSoFar) {
				counter = pos;
				maxSoFar = num;
			}
			pos++;
		}
		for (int i = 1; i <= pos - 1 - counter; i++) {
			stack.push(visitedStack.pop());
		}

		while (!visitedStack.isEmpty()) {
			stack.push(visitedStack.pop());
		}

		return maxSoFar;
	}

	public int popMax() {
		int counter = 0;
		int pos = 1;
		int maxSoFar = Integer.MIN_VALUE;
		Stack<Integer> visitedStack = new Stack<>();
		while (!stack.isEmpty()) {
			int num = stack.pop();
			visitedStack.push(num);
			if (num > maxSoFar) {
				counter = pos;
				maxSoFar = num;
			}
			pos++;
		}
		for (int i = 1; i <= pos - 1 - counter; i++) {
			stack.push(visitedStack.pop());
		}
		visitedStack.pop();
		while (!visitedStack.isEmpty()) {
			stack.push(visitedStack.pop());
		}

		return maxSoFar;
	}
}
