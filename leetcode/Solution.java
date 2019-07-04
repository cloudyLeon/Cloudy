package leetcode;

import MyLinkedlist.Linkedlist;
import com.sun.org.apache.bcel.internal.generic.DDIV;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.annotation.ElementType;
import java.util.*;

public class Solution {
    //Q20
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                ;
                if (c == ']' && topChar != '[') {
                    return false;
                }
                ;
                if (c == '}' && topChar != '{') {
                    return false;
                }
                ;

            }
        }
        return stack.isEmpty();
    }

    //Q849
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                }
            }
        }
        int[] res = new int[set.size()];
        int index = 0;
        for (int i : set) {
            res[index] = i;
            index++;
        }
        return res;
    }

    //Q850
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else {
                map.put(nums1[i], 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                list.add(nums2[i]);
                int count = map.get(nums2[i]) - 1;
                map.put(nums2[i], count);
                if (count == 0) {
                    map.remove(nums2[i]);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    //Q347
    public List<Integer> topKFrequent(int[] nums, int k) {
        class Freq implements Comparable<Freq> {
            Freq(int num, int freq) {
                this.num = num;
                this.freq = freq;
            }

            int num, freq;

            @Override
            public int compareTo(Freq another) {
                if (this.freq > another.freq) return 1;
                else if (this.freq < another.freq) return -1;
                else return 0;
            }
        }
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Freq> queue = new PriorityQueue<>();
        for (int key : map.keySet()) {
            Freq freq = new Freq(key, map.get(key));
            if (queue.size() < k) {
                queue.offer(freq);
            } else if (map.get(key) > queue.peek().freq) {
                queue.poll();
                queue.offer(freq);
            }

        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            Freq freq = queue.poll();
            int num = freq.num;
            ((LinkedList<Integer>) list).addFirst(num);
        }
        return list;
    }

    //Q69
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int start = 0;
        int end = x;
        int res = 0;
        int middle = 0;
        while (start <= end) {
            middle = start + (end - start) / 2;
            if (middle <= x / middle) {
                res = middle;
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return middle;
    }

    //Q771_1
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        for (int i = 0; i < J.length(); i++) {
            for (int j = 0; j < S.length(); j++) {
                if (J.charAt(i) == S.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    //Q771_2
    public int numJewelsInStones2(String J, String S) {
        return S.replaceAll("[^" + J + "]", "").length();
    }

    //Q929
    public int numUniqueEmails(String[] emails) {
        Set realemails = new HashSet();
        for (String email : emails) {
            String[] parts = email.split("@");
            String[] local = parts[0].split("\\+");
            realemails.add(local[0].replace(".", "") + "@" + parts[1]);
        }
        return realemails.size();
    }

    //Q709
    public String toLowerCase(String str) {
        char[] a = str.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if ('A' <= a[i] && a[i] <= 'Z') {
                a[i] += 32;
            }
        }
        return a.toString();
    }

    //Q905
    public int[] sortArrayByParity(int[] A) {
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if ((A[i] & 1) == 0) {
                int temp = A[index];
                A[index] = A[i];
                A[i] = temp;
                index++;
            }
        }
        return A;
    }

    //Q832
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        for (int[] row : A)
            for (int i = 0; i * 2 < n; i++)
                if (row[i] == row[n - i - 1])
                    row[i] = row[n - i - 1] ^= 1;
        return A;
    }

    //Q657
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') y++;
            else if (ch == 'D') y--;
            else if (ch == 'R') x++;
            else if (ch == 'L') x--;
        }
        return x == 0 && y == 0;
    }

    //Q804
    public int uniqueMorseRepresentations(String[] words) {
        String[] password = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
                "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> mosSet = new HashSet<>();
        for (String word : words) {
            String mosString = "";
            int index = 0;
            for (char c : word.toCharArray()) {
                mosString += password[c - 'a'];
            }
            mosSet.add(mosString);
        }
        return mosSet.size();
    }

    //Q922
    public int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1, n = A.length;
        while (i < n) {
            while (A[i] % 2 == 0 && i < n) {
                i += 2;
            }
            while (A[j] % 2 == 1 && j < n) {
                j += 2;
            }
            if (i < n && j < n) {
                int temp = A[i];
                A[i] = A[j];
                A[i] = temp;
            }
        }
        return A;
    }

    //Q942
    public int[] diStringMatch(String S) {
        int n = S.length(), left = 0, right = n;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; ++i)
            res[i] = S.charAt(i) == 'I' ? left++ : right--;
        res[n] = left;
        return res;
    }

    //Q961
    public int repeatedNTimes(int[] A) {
        int[] count = new int[10000];
        for (int a : A)
            if (count[a]++ == 1)
                return a;
        return -1;
    }

    //Q461
    public int hammingDistance(int x, int y) {
        int a = x ^ y;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (a >> i) & 1;
        }
        return count;
    }

    //Q852
    public int peakIndexInMountainArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] > A[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    //Q104
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //Q476
    public int findComplement(int num) {
        int i = 0, j = 0;
        while (num < i) {
            i += Math.pow(2, j);
        }
        return Math.abs(i - num);
    }

    //Q944
    public int minDeletionSize(String[] A) {
        int count = 0;
        int n = A.length;
        int m = A[0].length();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (A[j].charAt(i) - A[j + 1].charAt(i) > 0) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    //Q237
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //Q617
    public class TreeNode1 {
        int val;
        TreeNode1 left;
        TreeNode1 right;

        TreeNode1(int x) {
            val = x;
        }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        } else {
            t1.val = t1.val + t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
        }
        return t1;
    }

    //Q226
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode node = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = node;
        return root;
    }

    //Q292
    public boolean canWinNim(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("error param exception!");
        return !(n % 4 == 0);
    }

    //Q728
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i < right; i++) {
            if (dividingNumber(i)) list.add(i);
        }
        return list;
    }

    private boolean dividingNumber(int n) {
        int x = n;
        int d = 0;
        while (x > 0) {
            d = x % 10;
            x /= 10;
            if (d == 0 || n % d > 0) return false;
        }
        return true;
    }

    //Q344
    public String reverseString(String s) {
        byte[] bytes = s.getBytes();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            bytes[i] = (byte) (bytes[i] ^ bytes[j]);
            bytes[j] = (byte) (bytes[i] ^ bytes[j]);
            bytes[i] = (byte) (bytes[i] ^ bytes[j]);
            i++;
            j--;
        }
        return new String(bytes);
    }

    //Q589_1
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        for (Node node : root.children) {
            preorder(node, list);
        }
    }

    //Q589_2
    public List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            for (int i = root.children.size() - 1; i >= 0; i--) {
                stack.push(root.children.get(i));
            }
        }
        return list;
    }

    //Q965_1
    public boolean isUnivalTree2(TreeNode root) {
        int i = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode treeNode;
        queue.offer(root);
        while (!queue.isEmpty()) {
            treeNode = queue.poll();
            if (treeNode.val != i) return false;
            if (treeNode.left != null) queue.offer(treeNode.left);
            if (treeNode.right != null) queue.offer(treeNode.right);
        }
        return true;
    }

    //Q965_2
    public boolean isUnivalTree(TreeNode root) {
        if (root.left != null) {
            if (root.left.val != root.val) return false;
            if (!isUnivalTree(root.left)) return false;
        }
        if (root.right != null) {
            if (root.right.val != root.val) return false;
            if (!isUnivalTree(root.right)) return false;
        }
        return true;
    }

    //Q590
    List<Integer> list590 = new ArrayList();

    public List<Integer> postorder(Node root) {
        if (root == null)
            return list590;
        for (Node node : root.children) {
            postorder(node);
        }
        list590.add(root.val);
        return list590;
    }

    //Q500
    public String[] findWords(String[] words) {
        String[] keyborad = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < keyborad.length; i++) {
            for (char c : keyborad[i].toCharArray()) {
                map.put(c, i);
            }
        }
        ArrayList<String> res = new ArrayList<>();
        int index;
        for (String word : words) {
            char[] chars = word.toLowerCase().toCharArray();
            index = map.get(chars[0]);
            for (char c : chars)
                if (map.get(c) != index) {
                    index = -1;
                    break;
                }
            if (index != -1) res.add(word);
        }
        String[] a = new String[res.size()];
        return res.toArray(a);
    }

    //Q973
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> -a[0] * a[0] - a[1] * a[1]));
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K) {
                pq.poll();
            } // poll out the farthest among the K + 1 points.
        }
        int[][] ans = new int[K][2];
        while (K-- > 0) {
            ans[K] = pq.poll();
        }
        return ans;
    }

    //Q559_1
    public int maxDepth(Node root) {
        if (root == null) return 0;
        else {
            int max = 0;
            for (Node node : root.children) {
                max = Math.max(maxDepth(node), max);
            }
            return max + 1;
        }
    }

    //Q559_2
    public int maxDepth2(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node cur;
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                for (Node node : cur.children) queue.offer(node);
            }
            depth++;
        }
        return depth;
    }

    //Q171
    public int titleToNumber(String s) {
        int sum = 0;
        int index;
        for (char c : s.toCharArray()) {
            index = c - 'A' + 1;
            sum = sum * 26 + index;
        }
        return sum;
    }

    //Q867
    public int[][] transpose(int[][] A) {
        int x = A.length;
        int y = A[0].length;
        int[][] B = new int[y][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }

    //Q258
    public int addDigits(int num) {
        char[] ss = String.valueOf(num).toCharArray();
        if (ss.length == 1) return ss[0] - '0';
        num = 0;
        for (int i = 0; i < ss.length; i++) {
            num += ss[i] - '0';
        }
        return addDigits(num);
    }

    //Q908
    public int smallestRangeI(int[] A, int K) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int a : A) {
            if (a < min) min = a;
            if (a > max) max = a;
        }
        return max - min > 2 * K ? max - min - 2 * K : 0;
    }

    //Q509 递归
    public int fib(int N) {
        if (N == 0 || N == 1) return N;
        return fib(N - 1) + fib(N - 2);
    }

    //Q509_2 动态规划
    public int fib2(int N) {
        if (N < 2) return N;
        int[] fib = new int[N + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i < N + 1; i++) {
            fib[i] = fib[i - 2] + fib[i - 1];
        }
        return fib[N];
    }

    //Q557
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        String[] ss = new String(chars).split(" ");
        for (int i = 0, j = ss.length - 1; i < j; i++, j--) {
            String temp = ss[i];
            ss[i] = ss[j];
            ss[j] = temp;
        }
        String res = "";
        for (int i = 0; i < ss.length; i++) {
            res = res + ss[i];
            if (res.length() != s.length()) {
                res += " ";
            }
        }
        return res;
    }

    public String reverseWords1(String s) {
        char[] ca = s.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] != ' ') {   // when i is a non-space
                int j = i;
                while (j + 1 < ca.length && ca[j + 1] != ' ') {
                    j++;
                } // move j to the end of the word
                reverse(ca, i, j);
                i = j;
            }
        }
        return new String(ca);
    }

    private void reverse(char[] ca, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = ca[i];
            ca[i] = ca[j];
            ca[j] = tmp;
        }
    }

    //Q561
    public int arrayPairSum(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    //Q561_2 桶排序
    public int arrayPairSum2(int[] nums) {
        int[] a = new int[20000];
        for (int i = 0; i < nums.length; i++) {
            a[nums[i] + 10000]++;
        }
        int sum = 0;
        boolean flag = true;
        for (int i = 0; i < a.length; i++) {
            while (a[i] > 0) {
                if (flag) {
                    sum += i - 10000;
                }
                flag = !flag;
                a[i]--;
            }
        }
        return sum;
    }

    //Q700
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val > val) return searchBST(root.left, val);
        else if (root.val < val) return searchBST(root.right, val);
        else return root;
    }

    //Q821
    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        int a[] = new int[S.length()];
        int index = -n;
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == C) index = i;
            a[i] = i - index;
        }
        for (int i = n - 1; i >= 0; --i) {
            if (S.charAt(i) == C) index = i;
            a[i] = Math.min(a[i], Math.abs(i - index));
        }
        return a;
    }

    //Q933
    Queue<Integer> q = new LinkedList<>();

    public int ping(int t) {
        q.offer(t);
        while (q.peek() < t - 3000) q.poll();
        return q.size();
    }

    //Q811
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            int index = s.indexOf(' ');
            int num = Integer.valueOf(s.substring(0, index));
            s = s.substring(index + 1);
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '.') {
                    String d = s.substring(i + 1);
                    map.put(d, map.getOrDefault(d, 0) + num);
                }
            }
            map.put(s, map.getOrDefault(s, 0));
        }
        List list = new ArrayList();
        for (String s : map.keySet()) {
            list.add(map.get(s) + " " + s);
        }
        return list;
    }

    //Q693
    public boolean hasAlternatingBits(int n) {
        int flag = n % 2;
        n = n / 2;
        while (n > 0) {
            if (n % 2 == flag) {
                return false;
            }
            flag = n % 2;
            n = n / 2;
        }
        return true;
    }

    //Q108
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(0, nums.length - 1, nums);
    }

    private TreeNode buildTree(int l, int r, int[] nums) {
        if (l <= r) {
            int cur = l + (r - l) / 2;
            TreeNode node = new TreeNode(nums[cur]);
            node.left = buildTree(l, cur - 1, nums);
            node.right = buildTree(cur + 1, r, nums);
            return node;
        } else {
            return null;
        }
    }

    //Q521
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) return -1;
        return Math.max(a.length(), b.length());
    }

    //Q806
    public int[] numberOfLines(int[] widths, String S) {
        int row = 1;
        int index = 0;
        for (char c : S.toCharArray()) {
            index += widths[c - 'a'];
            if (index > 100) {
                row++;
                index = c - 'a';
            }
        }
        return new int[]{row, index};
    }

    //Q682
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<Integer>();
        int count = 0;
        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case "+":
                    if (stack.size() > 1) {
                        int a = stack.pop();
                        int b = stack.peek();
                        stack.push(a);
                        stack.push(a + b);
                        break;
                    }
                    break;
                case "C":
                    stack.pop();
                    break;
                case "D":
                    stack.push(stack.peek() * 2);
                    break;
                default:
                    stack.push(Integer.parseInt(ops[i]));
                    break;
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    //Q118
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new LinkedList<>();
        LinkedList<Integer> curRow = new LinkedList();
        curRow.add(1);
        rows.add(curRow);
        for (int i = 2; i <= numRows; i++) {
            LinkedList list = new LinkedList();
            list.add(1);
            for (int j = 2; j < i; j++) {
                int a = curRow.get(j - 1);
                int b = curRow.get(j - 2);
                list.add(a + b);
            }
            list.add(1);
            rows.add(list);
            curRow = list;
        }
        return rows;
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> allrows = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            allrows.add(new ArrayList<Integer>(row));
        }
        return allrows;

    }

    //Q371
    public int getSum(int a, int b) {
        while ((a & b) != 0) {
            int temp = a;
            a = a ^ b;
            b = (temp & b) << 1;
        }
        return a | b;
    }

    //Q9_1
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String num = String.valueOf(x);
        Stack<Character> stack = new Stack<>();
        if (num.length() % 2 == 0) {
            for (int i = 0; i < num.length(); i++) {
                if (i < num.length() / 2) {
                    stack.push(num.toCharArray()[i]);
                } else {
                    char c = stack.pop();
                    if (c != num.toCharArray()[i]) return false;
                }
            }
        } else {
            int middle = num.length() / 2;
            for (int i = 0; i < middle; i++) {
                stack.push(num.toCharArray()[i]);
            }

            for (int i = middle + 1; i < num.length(); i++) {
                char c = stack.pop();
                if (c != num.toCharArray()[i]) return false;
            }
        }
        return true;
    }

    //Q9_2$$
    public boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int reversednum = 0;
        while (x > reversednum) {
            reversednum = reversednum * 10 + x % 10;
            x /= 10;
        }
        return x == reversednum || reversednum / 10 == x;
    }

    //Q463
    public int islandPerimeter(int[][] grid) {
        int island = 0, neighbours = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    island++;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++;
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++;
                }
            }
        }
        return island * 4 - neighbours * 2;
    }

    //Q429
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) return list;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            LinkedList<Integer> level = new LinkedList();
            for (int i = 0; i < len; i++) {
                Node cur = queue.poll();
                level.add(cur.val);
                for (Node child : cur.children) {
                    queue.offer(child);
                }
            }
            list.add(level);
        }
        return list;
    }

    //Q107  使用队列
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            LinkedList<Integer> level = new LinkedList();
            for (int i = 0; i < len; i++) {
                TreeNode curNode = queue.poll();
                level.add(curNode.val);
                if (curNode.left != null) queue.offer(curNode.left);
                if (curNode.right != null) queue.offer(curNode.right);
            }
            list.add(0, level);
        }
        return list;
    }

    //Q107  使用递归
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        levelMaker(list, root, 0);
        return list;
    }

    private void levelMaker(List<List<Integer>> list, TreeNode node, int level) {
        if (node == null) return;
        if (level >= list.size()) list.add(0, new LinkedList<Integer>());
        levelMaker(list, node.left, level + 1);
        levelMaker(list, node.right, level + 1);
        list.get(list.size() - 1 - level).add(node.val);
    }

    //Q566
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int x = nums.length;
        int y = nums[0].length;
        int[][] res = new int[r][c];
        if (x * y != r * c) return nums;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int index = i * c + j;
                int a = index / y;
                int b = index % y;
                res[i][j] = nums[a][b];
            }
        }
        return res;
    }

    //Q412
    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<String>(n);
        for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
            fizz++;
            buzz++;
            if (fizz == 3 && buzz == 5) {
                ret.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            } else if (fizz == 3) {
                ret.add("Fizz");
                fizz = 0;
            } else if (buzz == 5) {
                ret.add("Buzz");
                buzz = 0;
            } else {
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }

    //Q669
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val < L) {
            root = trimBST(root.right, L, R);
        } else if (root.val > R) {
            root = trimBST(root.left, L, R);
        } else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
        }
        return root;
    }

    //Q575
    public int distributeCandies(int[] candies) {
        Set<Integer> kinds = new HashSet<>();
        for (int candy : candies) kinds.add(candy);
        return Math.min(candies.length / 2, kinds.size());
    }

    //Q876
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //Q206迭代
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = new ListNode(0);
        pre.next = null;
        ListNode p = head;
        ListNode q;
        while (p != null) {
            q = p.next;
            p.next = pre.next;
            pre.next = p;
            p = q;
        }
        return pre.next;
    }

    //Q206递归
    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        return reverseListInt(head, null);
    }

    private ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode p = head.next;
        head.next = newHead;
        return reverseListInt(p, head);
    }

    //Q496
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int leng1 = nums1.length;
        int leng2 = nums2.length;
        int[] res = new int[leng1];
        for (int i = 0; i < leng1; i++) {
            outer:
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    for (int k = j + 1; k < leng2; k++) {
                        if (nums2[k] > nums1[i]) {
                            res[i] = nums2[k];
                            break outer;
                        }
                    }
                }
                res[i] = -1;
            }
        }
        return res;
    }

    //Q496_STACK
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }

    //Q893
    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for (String s : A) {
            int[] odd = new int[26];
            int[] even = new int[26];
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    odd[s.charAt(i) - 'a']++;
                } else {
                    even[s.charAt(i) - 'a']++;
                }
            }
            set.add(Arrays.toString(odd) + Arrays.toString(even));
        }
        return set.size();
    }

    //Q232
    public class MyQueue {
        Stack<Integer> input;
        Stack<Integer> output;

        /**
         * Initialize your input structure here.
         */
        public MyQueue() {
            input = new Stack<>();
            output = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            input.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            peek();
            return output.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (output.isEmpty()) {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }
            return output.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return input.empty() && output.isEmpty();
        }
    }

    //Q883
    public int projectionArea(int[][] grid) {
        int xy = 0, xz = 0, yz = 0;
        for (int i = 0; i < grid.length; i++) {
            int maxY = 0;
            int maxX = 0;
            for (int j = 0; j < grid.length; j++) {
                maxX = Math.max(grid[j][i], maxX);
                maxY = Math.max(grid[i][j], maxY);
                if (grid[i][j] > 0) xy++;

            }
            yz += maxX;
            xz += maxY;
        }
        return xy + xz + yz;
    }

    //Q136
    public int singleNumber(int[] nums) {
        int theSingle = 0;
        for (int num : nums) {
            theSingle ^= num;
        }
        return theSingle;
    }

    //Q13
    public int romanToInt(String s) {
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I': {
                    nums[i] = 1;
                    break;
                }
                case 'V': {
                    nums[i] = 5;
                    break;
                }
                case 'X': {
                    nums[i] = 10;
                    break;
                }
                case 'L': {
                    nums[i] = 50;
                    break;
                }
                case 'C': {
                    nums[i] = 100;
                    break;
                }
                case 'D': {
                    nums[i] = 500;
                    break;
                }
                case 'M': {
                    nums[i] = 1000;
                    break;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                sum -= nums[i];
            } else {
                sum += nums[i];
            }
        }
        sum += nums[nums.length - 1];
        return sum;
    }

    //Q976
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i > 0; i--) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }
        return 0;
    }

    //Q762
    public int countPrimeSetBits(int L, int R) {
        int count = 0;
        for (int i = L; i <= R; i++) {
            int a = 0;
            int num = i;
            while (num > 0) {
                if (num % 2 == 1) {
                    a++;
                }
                num /= 2;
            }
            boolean flag = true;
            if (a == 1) break;
            for (int j = 2; j < a; j++) {
                if (a % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;
        }
        return count;
    }

    //Q766
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) return false;
            }
        }
        return true;
    }

    //Q257
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) return list;
        StringBuilder sb = new StringBuilder();
        paths(root, list, sb);
        return list;
    }

    private void paths(TreeNode root, List<String> list, StringBuilder sb) {
        sb.append(root.val);
        if (root.right == null && root.left == null) {
            list.add(sb.toString());
            return;
        }
        if (root.right != null) {
            sb.append("->");
            paths(root.right, list, sb);
            int a = sb.lastIndexOf(">") - 1;
            sb.delete(a, sb.length());
        }
        if (root.left != null) {
            sb.append("->");
            paths(root.left, list, sb);
            int a = sb.lastIndexOf(">") - 1;
            sb.delete(a, sb.length());
            System.out.println("aa");
        }

    }

    //Q868
    public int binaryGap(int N) {
        int a[] = new int[32];
        int t = 0;//数组索引
        for (int i = 0; i < 32; i++) {
            if (((N >> i) & 1) == 1) {
                a[t++] = i;
            }
        }
        int ans = 0;
        for (int i = 0; i < t - 1; i++) {
            ans = Math.max(ans, a[i + 1] - a[i]);
        }
        return ans;
    }

    //Q872
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        createList(list1, root1);
        createList(list2, root2);
        if (list1.size() != list2.size()) return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) return false;
        }
        return true;
    }

    private void createList(ArrayList list, TreeNode node) {
        if (node.left == null && node.right == null) {
            list.add(node.val);
        }
        if (node.left != null) {
            createList(list, node.left);
        }
        if (node.right != null) {
            createList(list, node.right);
        }
    }

    //Q872_2
    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root1);
        s2.push(root2);
        while (!s1.empty() && !s2.empty()) {
            if (dfs(s1) != dfs(s2)) return false;
        }
        return s1.isEmpty() == s2.isEmpty();
    }

    private int dfs(Stack<TreeNode> s) {
        while (!s.empty()) {
            TreeNode node = s.pop();
            if (node.right != null) s.push(node.right);
            if (node.left != null) s.push(node.left);
            if (node.right == null && node.left == null) return node.val;
        }
        return 0;
    }

    //Q169
    public int majorityElement(int[] nums) {
        int a = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int k = map.getOrDefault(nums[i], 0) + 1;
            if (k > a) return nums[i];
            map.put(nums[i], k);
        }
        return 0;
    }

    //Q169$$
    public int majorityElement2(int[] nums) {
        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) count++;
            else count--;
        }
        return major;
    }

    //Q637
    public List<Double> averageOfLevels(TreeNode root) {
        List list = new ArrayList();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int count = queue.size();
            TreeNode node;
            for (int i = 0; i < count; i++) {
                node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            list.add(sum / count);
        }
        return list;
    }

    //Q235
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode parent = null;
        if (root.val > p.val && root.val > q.val) parent = lowestCommonAncestor(root.left, p, q);
        else if (root.val < p.val && root.val < q.val) parent = lowestCommonAncestor(root.right, p, q);
        else if ((p.val <= root.val && root.val <= q.val) || (q.val <= root.val && root.val <= p.val)) parent = root;
        return parent;
    }

    //Q225
    class MyStack {
        Queue<Integer> list1;
        Queue<Integer> list2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            list1 = new LinkedList<>();
            list2 = new LinkedList<>();
        }

        private Queue<Integer> getNow() {
            return list1.size() == 0 ? this.list2 : this.list1;
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            getNow().add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (list1 == getNow()) {
                int length = list1.size();
                for (int i = 0; i < length - 1; i++) {
                    list2.offer(list1.poll());
                }
                return list1.poll();
            } else {
                int length = list2.size();
                for (int i = 0; i < length - 1; i++) {
                    list1.offer(list2.poll());
                }
                return list2.poll();
            }
        }

        /**
         * Get the top element.
         */
        public int top() {
            int res;
            if (list1 == getNow()) {
                int length = list1.size();
                for (int i = 0; i < length - 1; i++) {
                    list2.offer(list1.poll());
                }
                res = list1.peek();
                list2.offer(list1.poll());
            } else {
                int length = list2.size();
                for (int i = 0; i < length - 1; i++) {
                    list1.offer(list2.poll());
                }
                res = list2.peek();
                list1.offer(list2.poll());
            }
            return res;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return list2.size() == 0 && list1.size() == 0;
        }
    }

    //Q389
    public char findTheDifference(String s, String t) {
        int a = 0;
        for (char c : t.toCharArray()) {
            a += c;
        }
        for (char c : s.toCharArray()) {
            a -= c;
        }
        System.out.println(a);
        char c = (char) a;
        return c;
    }

    //Q389_$$
    public char findTheDifference2(String s, String t) {
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        char ch = cht[0];
        for (int i = 0; i < chs.length; i++) {
            ch ^= chs[i];
            ch ^= cht[i + 1];
        }
        return ch;
    }

    //Q985
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int len = queries.length;
        int[] ans = new int[len];
        int sum = 0;
        for (int num : A) {
            if (num % 2 == 0) {
                sum += num;
            }
        }
        for (int i = 0; i < len; i++) {
            int val = queries[i][0];
            int index = queries[i][1];
            if (A[index] % 2 == 0) sum -= A[index];
            A[index] += val;
            if (A[index] % 2 == 0) sum += A[index];
            ans[i] = sum;
        }
        return ans;
    }

    //Q119
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new LinkedList<>();
        if (rowIndex < 0) return list;
        for (int i = 0; i < rowIndex + 1; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
            ;
        }
        return list;
    }

    //Q706
    class MyHashMap {
        HashNode[] table;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            table = new HashNode[10000];
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            int index = getIndex(key);
            if (table[index] == null) {
                table[index] = new HashNode(-1, -1);
            }
            HashNode pre = findPre(key, table[index]);
            if (pre.next == null) {
                pre.next = new HashNode(key, value);
            } else {
                pre.next.value = value;
            }
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            int index = getIndex(key);
            if (table[index] == null) return -1;
            HashNode pre = findPre(key, table[index]);
            return pre.next == null ? -1 : pre.next.value;
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            int index = getIndex(key);
            if (table[index] == null) return;
            HashNode node = findPre(key, table[index]);
            if (node.next == null) return;
            else node.next = node.next.next;
        }

        private int getIndex(int key) {
            return Integer.hashCode(key) % 10000;
        }

        private HashNode findPre(int key, HashNode bucket) {
            HashNode node = bucket;
            HashNode pre = null;
            while (node != null && node.key != key) {
                pre = node;
                node = node.next;
            }
            return pre;
        }

        class HashNode {
            public int key;
            public int value;
            public HashNode next;

            public HashNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    //Q824
    public String toGoatLatin(String S) {
        String[] str = S.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            String a = str[i];
            a = goatism(a, i + 1);
            if (i != str.length - 1) a += " ";
            sb.append(a);
        }
        return sb.toString();
    }

    private String goatism(String s, int l) {
        char a = s.charAt(0);
        char c = s.toLowerCase().charAt(0);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            s += "ma";
            for (int i = 0; i < l; ++i) {
                s += "a";
            }
            return s;
        } else {
            String s1 = s.substring(0, 1);
            String s2 = s.substring(1);
            s = s2 + s1 + "ma";
            for (int i = 0; i < l; ++i) {
                s += "a";
            }
            return s;
        }
    }

    //Q27
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int len = nums.length;
        while (i < len) {
            if (nums[i] == val) {
                nums[i] = nums[len - 1];
                len--;
            } else {
                i++;
            }
        }
        return len;
    }

    //Q937
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> myCom = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int s1i1 = s1.indexOf(" ");
                int s2i2 = s2.indexOf(" ");
                char c1 = s1.charAt(s1i1 + 1);
                char c2 = s2.charAt(s2i2 + 1);
                if (c1 <= '9') {
                    if (c2 <= '9') return 0;
                    else return 1;
                }
                if (c2 <= '9') {
                    return -1;
                }
                int preCom = s1.substring(s1i1 + 1).compareTo(s2.substring(s2i2 + 1));
                if (preCom == 0) return s1.substring(0, s1i1).compareTo(s2.substring(0, s2i2));
                return preCom;
            }
        };
        Arrays.sort(logs, myCom);
        return logs;
    }

    //Q447
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;

                int d = getDistance(points[i], points[j]);
                map.put(d, map.getOrDefault(d, 0) + 1);
            }

            for (int val : map.values()) {
                res += val * (val - 1);
            }
            map.clear();
        }

        return res;
    }

    private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx * dx + dy * dy;
    }

    //Q884
    public String[] uncommonFromSentences(String A, String B) {
        String[] s1 = A.split(" ");
        String[] s2 = B.split(" ");
        HashMap<String, Integer> map = new HashMap();
        for (String s : s1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (String s : s2) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[0]);
    }

    //Q427
    /*public Node construct(int[][] g) { return build(0, 0, g.length - 1, g.length - 1, g);}

    Node build(int r1, int c1, int r2, int c2, int[][] g) {
        if (r1 > r2 || c1 > c2) return null;
        boolean isLeaf = true;
        int val = g[r1][c1];
        for (int i = r1; i <= r2; i++)
            for (int j = c1; j <= c2; j++)
                if (g[i][j] != val) {
                    isLeaf = false;
                    break;
                }
        if (isLeaf)
            return new Node(val == 1, true, null, null, null, null);
        int rowMid = (r1 + r2) / 2, colMid = (c1 + c2) / 2;
        return new Node(false, false,
            build(r1, c1, rowMid, colMid, g),//top left
            build(r1, colMid + 1, rowMid, c2, g),//top right
            build(rowMid + 1, c1, r2, colMid, g),//bottom left
            build(rowMid + 1, colMid + 1, r2, c2, g));//bottom right
    }
    }*/
    //Q21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode curr = res;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
                curr = curr.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
                curr = curr.next;
            }
        }
        if (l1 == null) {
            curr.next = l2;
        }
        if (l2 == null) {
            curr.next = l1;
        }
        return res.next;
    }

    //Q21_2$$
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l2.next, l1);
        }
        return head;
    }

    //Q705
    class MyHashSet {
        LinkNode[] ary = null;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            ary = new LinkNode[10000];
        }

        public void add(int key) {
            int index = idx(key);
            if (ary[index] == null) ary[index] = new LinkNode(-1);
            LinkNode cur = getPre(key, ary[index]);
            if (cur.next == null) {
                cur.next = new LinkNode(key);
                return;
            }
            cur.next.value = key;
        }

        private LinkNode getPre(int key, LinkNode node) {
            while (node.next != null && node.next.value != key) {
                node = node.next;
            }
            return node;
        }

        public void remove(int key) {
            int index = idx(key);
            if (ary[index] == null) return;
            LinkNode cur = getPre(key, ary[index]);
            if (cur.next == null) return;
            cur.next = cur.next.next;
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            int index = idx(key);
            if (ary[index] == null) return false;
            LinkNode cur = getPre(key, ary[index]);
            if (cur.next == null) return false;
            return true;
        }

        private int idx(int value) {
            return Integer.hashCode(value) % 10000;
        }

        class LinkNode {
            public int value;
            public LinkNode next;

            public LinkNode(int value) {
                this.value = value;
            }
        }
    }

    //Q788
    public int rotatedDigits(int N) {
        int count = 0;
        a:
        for (int i = 1; i <= N; ++i) {
            int num = i;
            boolean flag = false;
            while (num > 0) {
                int s = num % 10;
                if (num == 3 || num == 4 || num == 7) {
                    continue a;
                }
                if (num == 6 || num == 9 || num == 5) {
                    flag = true;
                }
                num /= 10;
            }
            if (flag == true) count++;
        }
        return count;
    }

    //Q788_$$
    public int rotatedDigits2(int N) {
        int[] dp = new int[10000];
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (i < 10) {
                if (i == 2 || i == 5 || i == 6 || i == 9) {
                    count++;
                    dp[i] = 2;
                } else if (i == 1 || i == 8) {
                    dp[i] = 1;
                }
            } else {
                int a = dp[i / 10];
                int b = dp[i % 10];
                if (a == 1 && b == 1) {
                    dp[i] = 1;
                } else if (a >= 1 && b >= 1) {
                    dp[i] = 2;
                    count++;
                }
            }
        }
        return count;
    }

    //Q748
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] bucket = new int[26];
        licensePlate = licensePlate.toLowerCase();
        for (Character c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                bucket[c - 'a']++;
            }
        }
        int minWord = Integer.MAX_VALUE;
        String res = null;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() < minWord && match(word, bucket)) {
                res = word;
                minWord = word.length();
            }
        }
        return res;
    }

    private boolean match(String word, int[] bucket) {
        int[] ary = new int[26];
        for (char c : word.toCharArray()) {
            ary[c - 'a']++;
        }
        for (int i = 0; i < ary.length; i++) {
            if (bucket[i] != 0 && ary[i] < bucket[i]) return false;
        }
        return true;
    }

    //Q94
    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode treeNode = root;
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                treeNode = stack.pop();
                list.add(treeNode.val);
                treeNode = treeNode.right;
            }
        }
        return list;
    }

    //Q953
    int a[] = new int[26];

    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++) {
            a[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            String s1 = words[i - 1];
            String s2 = words[i];
            if (compareTo(s1, s2) > 0)
                return false;
        }
        return true;
    }

    private int compareTo(String s1, String s2) {
        int comm = 0;
        for (int j = 0, k = 0; j < s1.length() && k < s2.length() && comm == 0; j++, k++) {
            comm = a[s1.charAt(j) - 'a'] - a[s2.charAt(k) - 'a'];
        }
        return comm == 0 ? s1.length() - s2.length() : comm;
    }

    //Q1002
    public List<String> commonChars(String[] A) {
        List<int[]> list = getArrays(A);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            int index = Integer.MAX_VALUE;
            for (int j = 0; j < list.size(); j++) {
                int[] b = list.get(j);
                index = Math.min(index, b[i]);
            }
            if (index > 0) {
                String s = String.valueOf((char) ('a' + i));
                for (int x = 0; x < index; x++) {
                    res.add(s);
                }
            }
        }
        return res;
    }

    private List<int[]> getArrays(String[] a) {
        List<int[]> list = new LinkedList<>();
        int[] ary = null;
        for (String s : a) {
            ary = new int[26];
            for (char c : s.toCharArray()) {
                ary[c - 'a']++;
            }
            list.add(ary);
        }
        return list;
    }

    //Q897
    public TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }

    private TreeNode increasingBST(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        root.right = increasingBST(root.right, tail);
        return res;
    }

    //Q202
    public boolean isHappy(int num) {
        Set<Integer> set = new HashSet<>();
        while (set.add(num)) {
            int sum = 0;
            while (num > 0) {
                int bit = num % 10;
                sum += bit * bit;
                num /= 10;
            }
            if (sum == 1) return true;
            num = sum;
        }
        return false;
    }

    //Q303
    class NumArray {
        int[] data;

        public NumArray(int[] nums) {
            data = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                data[i] = sum + nums[i];
                sum = data[i];
            }
        }

        public int sumRange(int i, int j) {
            return i == 0 ? data[j] : data[j] - data[i - 1];
        }
    }

    //Q485
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                current++;
                count = Math.max(current, count);
            } else {
                current = 0;
            }
        }
        return count;
    }

    //Q122 $$
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    //Q784 BFS
    public List<String> letterCasePermutation(String S) {
        if (S == null) return new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);
        for (int i = 0; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i))) continue;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                char[] chs = cur.toCharArray();
                chs[i] = Character.toUpperCase(chs[i]);
                queue.offer(String.valueOf(chs));
                chs[i] = Character.toLowerCase(chs[i]);
                queue.offer(String.valueOf(chs));
            }
        }
        return new LinkedList<>(queue);
    }

    //Q784 DFS
    public List<String> letterCasePermutation2(String S) {
        if (S == null) {
            return new LinkedList<>();
        }
        LinkedList<String> res = new LinkedList<>();
        solve(S.toCharArray(), res, 0);
        return res;
    }

    private void solve(char[] chs, LinkedList<String> res, int pos) {
        if (pos == chs.length) {
            res.add(String.valueOf(chs));
            return;
        }
        if (chs[pos] >= '0' && chs[pos] <= '9') {
            solve(chs, res, pos + 1);
            return;
        }
        chs[pos] = Character.toLowerCase(chs[pos]);
        solve(chs, res, pos + 1);
        chs[pos] = Character.toUpperCase(chs[pos]);
        solve(chs, res, pos + 1);
    }

    //Q530
    int minDiff = Integer.MAX_VALUE;
    TreeNode prev;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null) minDiff = Math.min(minDiff, root.val - prev.val);
        prev = root;
        inorder(root.right);
    }

    //Q100 $$
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    //Q520
    public boolean detectCapitalUse(String word) {
        int cnt = 0;
        for (char c : word.toCharArray()) if ('Z' - c >= 0) cnt++;
        return (cnt == 0 || cnt == word.length() || (cnt == 1 && 'Z' - word.charAt(0) >= 0));
    }

    //Q538
    public TreeNode convertBST(TreeNode root) {
        sumBST(root);
        return root;
    }

    int sum = 0;

    private void sumBST(TreeNode root) {
        if (root == null) return;
        sumBST(root.right);
        root.val += sum;
        sum = root.val;
        sumBST(root.left);
    }

    //Q242
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
        }
        for (Integer i : map.values()) {
            if (i != 0) return false;
        }
        return true;
    }

    //Q1012
    public int bitwiseComplement(int N) {
        int count = 0;
        int i = N;
        while (i > 0) {
            count++;
            i /= 2;
        }
        return (int) (Math.pow(2, count) - N - 1);
    }

    //Q653
    Set<Integer> nums = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        if (nums.contains(k - root.val)) return true;
        nums.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    //Q453
    public int minMoves(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i] - min;
        }
        return sum;
    }

    //Q191$$
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i <= 31; i++) {
            if ((n >>> i & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    //Q860
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : bills) {
            switch (i) {
                case 5:
                    map.put(5, map.getOrDefault(5, 0) + 1);
                    break;
                case 10:
                    if (map.get(5) == null || map.get(5) <= 0) return false;
                    map.put(5, map.get(5) - 1);
                    map.put(10, map.getOrDefault(10, 0) + 1);
                    break;
                case 20:
                    if (map.get(5) == null || map.get(10) == null || map.get(5) <= 0) return false;
                    if (map.get(10) <= 0) {
                        int num5 = map.get(5);
                        if (num5 <= 2) return false;
                        map.put(5, map.get(5) - 2);
                    } else {
                        map.put(5, map.get(5) - 1);
                        map.put(10, map.get(10) - 1);
                    }
                    break;
            }
        }
        return true;
    }

    //Q404
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.left.left == null && root.left.right == null)
            return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    //Q268
    public int missingNumber(int[] nums) {
        double sum = nums.length * (nums.length + 1) / 2;
        for (int i : nums) {
            sum -= i;
        }
        return (int) sum;
    }

    //Q268 $$
    public int missingNumber2(int[] nums) {
        int sum=nums.length;
        for(int i=0;i<nums.length;++i){
            sum^=nums[i];
            sum^=i;
        }
        return sum;
    }

    //Q121 $$
    public int maxProfits(int[] prices) {
        int profit=0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<prices.length;++i){
            min=Math.min(min,prices[i]);
            profit=Math.max(profit,prices[i]-min);
        }
        return profit;
    }

    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }
    //Q690
    Map<Integer,Employee> map=new HashMap<>();
    public int getImportance(List<Employee> employees, int id) {
        for(int i=0;i<employees.size();++i){
            Employee employee=employees.get(i);
            map.put(employee.id,employee);
        }
        int sum=0;
        sum=getSubordinate(map.get(id));
        return sum;
    }

    private int getSubordinate(Employee employee) {
        if(employee.subordinates==null) return employee.importance;
        int sum=0;
        for(int i:employee.subordinates){
            sum+=getSubordinate(map.get(i));
        }
        return employee.importance+sum;
    }

    //Q38
    public String countAndSay(int n) {
        String[] ary=new String[n];
        ary[0]="1";
        for(int i=1;i<ary.length;i++){
            ary[i]=count(ary[i-1]);
        }
        return ary[n];
    }

    private String count(String s) {
        StringBuilder sb=new StringBuilder();
        char cur=s.charAt(0);
        int count=1;
        for(int i=1;i<s.length();++i){
            if(cur==s.charAt(i)){
                count++;
            }else {
                sb.append(count+""+cur);
                count=1;
                cur=s.charAt(i);
            }
        }
        sb.append(count+cur);
        return sb.toString();
    }

    //Q606
    public String tree2str(TreeNode t) {
        if(t == null) return "";
            //叶子节点
            if(t.left == null && t.right == null){
                return "" + t.val;
            }
            //非叶子节点
            return t.val + (t.left == null ? "()": "(" + tree2str(t.left) + ")")
                    + (t.right == null ? "": "(" + tree2str(t.right) + ")");
    }

    //Q155
    class MinStack {
        private Stack<Integer> stack;
        /** initialize your data structure here. */
        public MinStack() {
            stack=new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            stack.push(Math.min(getMin(),x));
        }

        public void pop() {
            stack.pop();
            stack.pop();
        }

        public int top() {
            int min=stack.pop();
            int top=stack.peek();
            stack.push(min);
            return top;
        }

        public int getMin() {
            return stack.isEmpty()?Integer.MAX_VALUE:stack.peek();
        }
    }

    //Q455
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child=0;
        int cookie=0;
        while(child<g.length&&cookie<s.length){
            if(g[child]<=s[cookie]) child++;
            cookie++;
        }
        return child;
    }


    //Q437 $$
    int pathcount=0;
    public int pathSum(TreeNode root, int sum) {
       if(root==null) return 0;
       sum(root,sum);
       pathSum(root.left,sum);
       pathSum(root.right,sum);
       return pathcount;
    }

    private void sum(TreeNode root, int sum) {
        if(root==null) return;
        sum-=root.val;
        if(sum==0) pathcount++;
        sum(root.left,sum);
        sum(root.right,sum);
    }

    //Q704
    public int search(int[] nums, int target) {
        return binarySearch(nums,target,0,nums.length-1);
    }

    private int binarySearch(int[] nums, int target, int low, int high) {
        if(low>high) return -1;
        int middle=low+(high-low)/2;
        if(nums[middle]>target){
            return binarySearch(nums,target,low,middle-1);
        }else if(target>nums[middle]){
            return binarySearch(nums,target,middle+1,high);
        }else {
            return middle;
        }
    }

    //Q167
    public int[] twoSum(int[] numbers, int target) {
        int[] res=new int[2];
        int low=0;
        int high=numbers.length-1;
        while (low<high){
            int sum=numbers[low]+numbers[high];
            if(sum==target){
                res[0]=low+1;
                res[1]=high+1;
            }else if(sum>target){
                low++;
            }else {
                high--;
            }
        }
        return res;
    }

    //Q506
    public String[] findRelativeRanks(int[] nums) {
        int[][] pairs=new int[nums.length][2];
        for(int i=0;i<nums.length;i++){
            pairs[i][0]=nums[i];
            pairs[i][1]=i;
        }
        Arrays.sort(pairs,(a,b)->(b[0]-a[0]));
        String[] res=new String[nums.length];
        for(int i=0;i<nums.length;i++){
            if(i==0){
                res[pairs[i][1]]="Gold Medal";
            }else if(i==1){
                res[pairs[i][1]]="Silve Medal";
            }else if(i==2){
                res[pairs[i][1]]="Bronze Medal";
            }else {
                res[pairs[i][1]]=(i+1)+"";
            }
        }
        return res;
    }

    //Q598
    public int maxCount(int m, int n, int[][] ops) {
        int a=Integer.MAX_VALUE;
        int b=Integer.MAX_VALUE;
        for(int i=0;i<ops.length;i++){
            if(ops[i][0]<a) a=ops[i][0];
            if(ops[i][1]<b) b=ops[i][1];
        }
        return Math.min(m,a)*Math.min(n,b);
    }

    //Q563
    public int findTilt(TreeNode root) {
        if(root==null) return 0;
        return Math.abs(get(root.left)-get(root.right))+findTilt(root.left)+findTilt(root.right);
    }
    public int get(TreeNode root){
        if(root==null) return 0;
        return get(root.left)+get(root.right)+1;
    }

    //Q563$$
    int result=0;
    public int findTilt2(TreeNode root) {
       getcount(root);
       return result;
    }

    private int getcount(TreeNode root) {
        if(root==null) return 0;
        int left=getcount(root.left);
        int right=getcount(root.right);
        result+=Math.abs(left-right);
        return left+right+root.val;
    }

    //Q217
    public boolean containsDuplicate(int[] nums) {
        Set set=new HashSet();
        for(int i:nums){
            if(!set.add(i))return true;
        }
        return false;
    }
    //Q217??
    public boolean containsDuplicate2(int[] nums) {
        byte[] mark = new byte[150000];
        for (int i : nums) {
            int j = i/8;
            int k = i%8;
            int check = 1<<k;
            if ((mark[j] & check) != 0) {
                return true;
            }
            mark[j]|=check;
        }
        return false;
    }

    //Q448

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List list=new ArrayList();
        for(int i=0;i<nums.length;i++){
            nums[Math.abs(nums[i])-1]=-Math.abs(nums[Math.abs(nums[i])-1]);
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                list.add(i+1);
            }
        }
        return list;
    }

    //Q67
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }


    // Q101 递归
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }

    private boolean isMirror(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        return (root1.val == root2.val) && isMirror(root1.left, root2.right)
                &&isMirror(root1.right, root2.left);
    }

    // Q101 迭代
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }

    // Q1
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] res=new int[2];
        for(int i = 0; i <nums.length; ++i){
            if (map.containsKey(target-nums[i])){
                res[0] = i;
                res[1] = map.get(target-nums[i]);
                return res;
            }
            map.put(nums[i],i);
        }
        return res;
    }

    // Q70
    public int climbStairs(int n) {
        if (n < 2) return 1;
        int[] ary=new int[n];
        ary[0]=1;
        ary[1]=2;
        for(int i = 2; i < n; i++){
            ary[i] = ary[i-2] + ary[i-1];
        }
        return ary[n-1];
    }

    //Q53 $$ DP
    public int maxSubArray(int[] nums) {
        int sum=0;
        int ans=nums[0];
        for (int num : nums){
            if(sum > 0){
                sum+=num;
            }else {
                sum=num;
            }
            ans=Math.max(ans,sum);
        }
        return ans;
    }

    // Q53
    @Test
    public void fun() {
    List linkedlist= new LinkedList();
    for (int i = 0;i < 10; ++i){
        linkedlist.add(null);
    }
    }
    public void change(String s){
        s="b";
    }
}