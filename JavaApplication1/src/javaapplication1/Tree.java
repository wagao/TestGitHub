/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Hashtable;
import myGraph.myGraph;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;
import javaapplication1.ListNode;
/**
 *
 * @author Emma
 */
public class Tree {
public class BSTIterator {
    TreeNode head;
    Deque<TreeNode> stack = new ArrayDeque<>();
    
    public BSTIterator(TreeNode root) {
        head = root;
        stack.push(root);
        while(root.left!=null) {
            stack.push(root.left);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.isEmpty() ;
    }

    /** @return the next smallest number */
    public int next() { // DFS preorder O(h)
        if(stack.isEmpty()) return Integer.MIN_VALUE;
        TreeNode p=stack.pop();
        if(p.right!=null){
            stack.push(p.right);
            p = p.right;
            while(p.left!=null){
                stack.push(p.left);
                p = p.left;
            }
        }
        return p.val;
    }
}

  public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;
      public TreeNode(int x) { val = x; }
  }
  public   List<List<Integer>>  res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) { // get depth and assign 
         if(root==null) return res;
         List<TreeNode> queue = new LinkedList<>();
        depth(root, 1);         
        return res;
    } 
    // public void traverse (TreeNode root) {
    //     while(root!=null){
    //         traverse(root.left);
    //         // do sth
    //         int d =depth(root);
    //         List<Integer> cur=res.get(depth)
            
    //         traverse(root.right);
    //     }
    // }
    public void depth(TreeNode root, int a ){
        if(root==null) { return ; }
        if(root.left==null&& root.right==null){
              while(a>res.size()){
                   List<Integer> item = new LinkedList<>();
                   res.add(item);
              }
            List<Integer> item = res.get(a-1);  
            item.add(root.val);
            return;
         }
            depth(root.left, a+1);
           depth(root.right, a+1);
         
            List<Integer> item = res.get(a-1);
            item.add(root.val);
    }
    
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        rightSideView(res , root, 0);
        return res;
    }
    public void rightSideView(List<Integer> res, TreeNode root, int level) {
        if(root ==null ) return;
        if(res.size()==level){//there is no for this level, need to add one 
            res.add(root.val);
        }
        rightSideView(res, root.right, level+1);
        rightSideView(res, root.left,  level+1);
    }
    
    
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        if(head.next==null) return new TreeNode(head.val);
        int count = 0;
        ListNode p = head;
        while(p!=null){
            count++;
            p=p.next;
        }
        if(count<3){
            return left(head, count-1);
        }else {
            int rootIdx =count/2;
            TreeNode root = left(head, rootIdx);
            
            int tmp=0;
            p = head;
            while(tmp<rootIdx){tmp++; p =p.next;}
            
            TreeNode parent = new TreeNode(p.val);
            parent.left= root;
            p=p.next;
            
            right(parent, p , count-rootIdx-1);
            
//         System.out.println(count + " "+ rootIdx+ "  root_val:"+ parent.val+"  P:"+p.val);
//            right(root, p, count-rootIdx-1);
            return parent;
        }
    }
    public TreeNode left(ListNode head , int count) {
         if(count==1){// to be a new left leaf
//            TreeNode parent=new TreeNode(head.next.val);
            TreeNode left=new TreeNode(head.val);
            left.left=null;
            left.right=null;
//            parent.left=left;
//            parent.right=null;
            return left;
        }else if(count==2){
            TreeNode left=new TreeNode(head.val);
            TreeNode parent=new TreeNode(head.next.val);
//            TreeNode superparent=new TreeNode(head.next.next.val);
            left.left=null;
            left.right=null;
            parent.left=left;
            parent.right=null;
//            superparent.left=parent;
//            superparent.right=null;
            return parent;
        }else { // count >=3, distribute in left and right
            int rootIdx = count/2;
            TreeNode root = left(head, rootIdx);
            int tmp=0;
            ListNode p = head;
            while(tmp<rootIdx){ tmp++;p =p.next;}
            TreeNode parent = new TreeNode(p.val);
            parent.left= root;
            p=p.next;
             right(parent, p , count-rootIdx-1);
//         System.out.println(  " left_root_val:"+ parent.val+"  count:"+ count + " rid:"+ rootIdx+"  P:"+p.val);
//             right(root, p , count-rootIdx-1);
             return parent;
        }
        
    }
    public TreeNode right(TreeNode parent, ListNode head , int count) {
        
        if(count==1){// to be a new right leaf
            TreeNode right=new TreeNode(head.val);
            right.left=null;
            right.right=null;
            parent.right=right;
            return parent;
        }else if(count==2){
            TreeNode right=new TreeNode(head.val);
            TreeNode rr=new TreeNode(head.next.val);
            right.left=null;
            right.right=rr;
            rr.left=null;
            rr.right=null;
            parent.right = right;
            return parent;
        }else { // count >=3, distribute in left and right
            int rootIdx = count/2;
            TreeNode root = left(head, rootIdx);
//            parent.right=root;
            int tmp=0;
            ListNode p = head;
            while(tmp<rootIdx){ tmp++; p = p.next;}
            
            TreeNode parentp = new TreeNode(p.val);
            parentp.left= root;
            p=p.next;
             right(parentp, p , count-rootIdx-1);
             parent.right=parentp;
//             right(root, p , count-rootIdx-1);
             return parent;
        }
    }
    
    
    public TreeNode sortedArrayToBST(int[] num) {
        if(num==null) return null;
        int n = num.length;
        return helper(num,0,n-1);
    }
    public TreeNode helper(int[] num, int start, int end){
        if( start<0 || end<0 || start>=num.length || end>=num.length|| start>end) return null;
        int rootIdx = (start+end)/2;
        TreeNode root= new TreeNode(num[rootIdx]);
        root.left = helper(num, start, rootIdx-1);
        root.right = helper(num, rootIdx+1, end);
        
        return root;
    }
    
}
