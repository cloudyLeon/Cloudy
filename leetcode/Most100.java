package leetcode;

import org.junit.jupiter.api.Test;

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

    @Test
    public void fun() {
    String a = "cbaebabacd";
    String b = "abc";
    List list=findAnagrams(a, b);
        System.out.println("a");
    }
}
