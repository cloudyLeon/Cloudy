package leetcode;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ToStringBuilder;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ Author :cloudy
 * @ Date   :Created in 10:24 2019/6/18
 * @ Description: leetcode最火的100道
 */
public class Most100 {
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

    // Q141
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }

    // Q142
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode cur = head;
        while (cur != null){
           if(visited.contains(cur)){
               return cur;
           }
           visited.add(cur);
           cur = cur.next;
        }

        return cur;
    }

    // Q160 双指针
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode last = headB;
        while (last.next != null) {
            last = last.next;
        }
        last.next = headB;

        ListNode fast = headA;
        ListNode slow = headA;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = headA;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                last.next = null;
                return fast;
            }
        }
        last.next = null;
        return null;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Q543
    int res;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        depth(root);
        return res;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        res = Math.max(res,left+right);
        return Math.max(left,right)+1;
    }

    // Q198 DP
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0],nums[1]);
        }
        int[] mo=new int[n];
        mo[n-1]=nums[n-1];
        mo[n-2]=Math.max(nums[n-1],nums[n-2]);
        for (int i=n-3; i>=0; i--) {
            mo[i]=mo[i+2]+nums[i]<mo[i+1]?mo[i+1]:mo[i+2]+nums[i];
        }
        return mo[0];
    }

    // Q76 滑动双指针
    public String minWindow(String s, String t) {
        String res="";
        int min = Integer.MAX_VALUE;
        int[] table = new int[64];
        int[] tableS = new int[64];
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i)-'A']++;
        }

        for (int i = 0; i < s.length(); i++) {
            tableS[s.charAt(i)-'A']++;
        }

        if (s.length() <= t.length() && !contain(table,tableS)) return "";
        if (s.equals(t)) return s;

        int start = 0;
        int end = 0;
        int [] freq = new int[64];
        while (end < s.length()) {
            while (end < s.length() && !contain(table,freq)) {
                freq[s.charAt(end)-'A']++;
                end++;
            }
            if (!contain(table,freq)) return res;
            while (contain(table,freq)) {
                freq[s.charAt(start)-'A']--;
                start++;
            }
            String cur = s.substring(start-1,end);
            if (cur.length() < min){
                res = cur;
                min = cur.length();
            }
        }
        return res;
    }

    private boolean contain(int[] table, int[] freq) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] > freq[i])return false;
        }
        return true;
    }

    // Q76 官方题解
    public String minWindow2(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        //保存t中所有唯一字符计数的字典。
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }
        // t中的唯一字符数，需要出现在所需的窗口中。
        int required = dictT.size();
        // 左 右 指针
        int l = 0, r = 0;
    /*
    formed 用于跟踪当前窗口中以其所需频率存在多少个唯一字符。
    例如 如果t是“AABC”那么窗口必须有两个A，一个B和一个C.
    因此，当满足所有这些条件时，formed = 3。
    */
        int formed = 0;
        // 字典，用于保存当前窗口中所有唯一字符的计数。
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
        // (窗口长度, 左指针, 右指针)
        int[] ans = {-1, 0, 0};
        while (r < s.length()) {
            // 从右侧向窗口添加一个字符
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            //如果添加的当前字符的频率等于t中的所需计数，则将formed增加1。
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            // 尝试并收缩窗口，直到它不再是“理想的”。
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // 更新满足条件的最小的窗口和 l r 指针
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                //Left”指针指向的位置处的字符不再是窗口的一部分。
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                // 将左指针向前移动，这将有助于查找新窗口。
                l++;
            }
            // 继续扩大窗口
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
    // Q438
    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;


        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;

            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == t.length()){
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
    }


    // Q234
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        int nodeCount = 1;
        ListNode node = head;
        while (node.next != null) {
            nodeCount++;
            node = node.next;
        }
        if (nodeCount == 1) return true;
        boolean isOdd ;
        if (nodeCount%2 == 0) {
            isOdd = true;
        }else {
            isOdd = false;
            nodeCount --;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        for (int i = nodeCount; i > nodeCount/2; i--) {
            stack.push(cur.val);
            cur = cur.next;
        }
        if (!isOdd) {
            cur = cur.next;
        }
        while (!stack.empty()) {
            if (stack.pop() != cur.val) return false;
            cur = cur.next;
        }
        return true;
    }

    // Q234官方
    public boolean isPalindrome2(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head, fast = head.next, pre = null, prepre = null;
        while(fast != null && fast.next != null) {
            //反转前半段链表
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            //先移动指针再来反转
            pre.next = prepre;
            prepre = pre;
        }
        ListNode p2 = slow.next;
        slow.next = pre;
        ListNode p1 = fast == null? slow.next : slow;
        while(p1 != null) {
            if(p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    // Q581 官方
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[len - 1];
        int end = 0;
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (max > nums[i]) {
                end = i;
            }else {
                max = nums[i];
            }
        }
        for (int j = len-1; j >= 0; j--) {
            if (min < nums[j]) {
                start = j;
            }else {
                min = nums[j];
            }
        }
        return end <= start ? 0 : end - start +1;
    }
    // Q78
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 1<<nums.length; i++) {
            List<Integer> set = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1 ) == 1) set.add(nums[j]);
            }
            res.add(set);
        }
        return res;
    }

    // Q338
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        int i = 0, b = 1;
        // [0, b) is calculated
        while (b <= num) {
            // generate [b, 2b) or [b, num) from [0, b)
            while (i < b && i + b <= num) {
                ans[i + b] = ans[i] + 1;
                ++i;
            }
            i = 0;   // reset i
            b <<= 1; // b = 2b
        }
        return ans;
    }

    // Q22
    public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    generatedAll(new char[2*n], 0, res);
    return res;
    }

    private void generatedAll(char[] chars, int pos, List<String> res) {
        if (pos == chars.length) {
            if (validate(chars)) {
                res.add(new String(chars));
            }
        }else {
            chars[pos] = '(';
            generatedAll(chars, pos+1, res);
            chars[pos] = ')';
            generatedAll(chars, pos+1, res);
        }
    }

    private boolean validate(char[] chars) {
        int count = 0;
        for (char c:chars) {
            if (c == '(') {
                count++;
            }else {
                count--;
            }
            if (count < 0) return false;
        }
        return count == 0;
    }


    // Q46
    public void backtrack(int n,
                          ArrayList<Integer> nums,
                          List<List<Integer>> output,
                          int first) {
        // if all integers are used up
        if (first == n)
            output.add(new ArrayList<Integer>(nums));
        for (int i = first; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(n, nums, output, first + 1);
            // backtrack
            Collections.swap(nums, first, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }

    public List<List<Integer>> permute2(int[] nums) {
        //一个全局变量，用于保存所有集合
        List<List<Integer>> list = new ArrayList<>();
        //传入三个参数，没有附加参数
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        //一个终结条件，也就是满足条件的时候
        if(tempList.size() == nums.length){
            //全局变量添加一个满足条件的集合
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue;
                //如果tempList没有包含nums[i]才添加
                tempList.add(nums[i]);
                //递归调用，此时的tempList一直在变化，直到满足终结条件才结束
                backtrack(list, tempList, nums);
                System.out.println("tempList的内容:"+tempList+"-------"+"i的值:"+i);
                //它移除tempList最后一个元素的作用就是返回上一次调用时的数据，也就是希望返回之前的节点再去重新搜索满足条件。这样才能实现回溯
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // Q39
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        //System.out.println(candidates);
        backtrack(candidates, target, res, 0, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> res, int i, ArrayList<Integer> tmp_list) {
        if (target < 0) return;
        if (target == 0) {
            // 一定要将临时list拷贝份再加入到结果集中
            res.add(new ArrayList<>(tmp_list));
            return;
        }
        for (int start = i; start < candidates.length; start++) {
            if (target < candidates[start]) break;
            //System.out.println(start);
            tmp_list.add(candidates[start]);
            //System.out.println(tmp_list);
            backtrack(candidates, target - candidates[start], res, start, tmp_list);
            tmp_list.remove(tmp_list.size() - 1);
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        if (people.length <= 1) return people;
        Arrays.sort(people, (p1,p2)->{
            return p2[0] - p1[0] == 0 ? p1[1] - p2[1] : p2[0] - p1[0];
        });
        ArrayList<int[]> list = new ArrayList();
        for (int[] p : people) {
            list.add(p[1],p);
        }
        int[][] res = list.toArray(new int[people.length][]);
        return res;
    }


    // Q912
    //插入排序
    public int[] sortArray(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int j = i;
            while (j > 0 && nums[j-1] > cur) {
                nums[j] = nums[j-1];
                j--;
            }
            nums[j] = cur;
        }
        return nums;
    }

    //Q114
    TreeNode node = null;
    public void flatten(TreeNode root) {
        while (root != null) {
        if(root.left == null) {
            root = root.right;
        }else {
            TreeNode pre = root.left;
            while (pre.right != null) {
                pre = pre.right;
            }
            pre.right = root.right;
            root.right = root.left;
            root.left = null;
            root = root.right;
        }
        }
    }

    //Q48
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; ++i){
            for(int j = 0; j<len; ++j){
                if(i != j){
                   int temp =  matrix[i][j];
                   matrix[i][j] = matrix[j][i];
                   matrix[j][i] = temp;
                }
            }
        }
        System.out.println(matrix);
        reversArray(matrix);
    }

    private void reversArray(int[][] matrix) {
        for(int i = 0; i< matrix.length; i++) {
            int[] ary = matrix[i];
            for (int j = 0; j < matrix.length/2; j++ ){
                int temp = ary[j];
                ary[j] = ary[matrix.length - j-1];
                ary[matrix.length - j-1] = temp;
            }
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for(int i = 0; i < res.length; i++){
            res[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;
        for(int i = res.length - 1; i >= 0; i--){
            res[i] *= k; // k为该数右边的乘积。
            k *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }

    @Test
    public void fun() {
        /*String a = "a";
        String b = new String("a");
        String c = a+b;
        System.out.println(a==b);

        int [][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        rotate(matrix);*/
        String s =null;
        if((s == null) || (s.length() == 0)){}
    }
    @Test
    public void fun2() {
        String a = "a";
        String b = "b";
        exchange(a, b);
        System.out.println(a);
        System.out.println(b);
    }
    public void exchange(Object a, Object b){
        Object temp = a;
        a = b;
        b = temp;
    }
}
