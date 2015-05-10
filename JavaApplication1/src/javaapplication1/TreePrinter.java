/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Emma
 */

import java.util.LinkedList;

public class TreePrinter {
// public TreePrinter(){}
  public static class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
      this.value = value;
      this.left = null;
      this.right = null;
    }
    public Node(int value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }
//    List<List<Integer>> res = new LinkedList<>();
//    public List<List<Integer>> pathSum_2(Node root, int sum) {
//        if(root==null) return res;
//        pathSum_2(root, sum, 0);
//        return res;
//    }
//    public void pathSum_2(Node root, int sum, int l) {
//        if(root==null) return ;
//        if(root.value==sum && root.left==null && root.right==null){
//            List<Integer> item= new LinkedList<>();
//            item.add(root.value);
//            res.add(item);
//            return;
//        }else {
//            pathSum_2(root.left, sum-root.value,l+1);
//            System.out.println( root.value+" l:"+res.size());
//            pathSum_2(root.right, sum-root.value,l+1);
//            System.out.println( root.value+" r:"+res.size());
//            for(List<Integer> i: res) {
//                i.add(0, root.value);
//            }
//        }
//        
//    }
  public List<List<Integer>> pathSum(Node root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> item = new LinkedList<>();
        if(root==null) { res.add(item);return res;}
        if(root.value<=sum){
            for(List<Integer> i: pathSum(root.left, sum-root.value)) 
                i.add(0,root.value);
            for(List<Integer> i: pathSum(root.right, sum-root.value)) 
                i.add(0,root.value);
        }
        return res;
    }
  
    public List<List<Integer>> levelOrderBottom(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        List<Node> queue = new  LinkedList<>();
        List<Node> queueN = new  LinkedList<>();
        List<Integer> item = new  LinkedList<>();
        
        queue.add(root);
        Node p ;
        while(!queue.isEmpty()){
            p =queue.remove(0);
            item.add(p.value);
            if(p.left!=null) queueN.add(p.left);
            if(p.right!=null) queueN.add(p.right);
            if(queue.isEmpty()){
                res.add(0,item);
                queue = queueN;
                queueN = new LinkedList<>(); 
            }
        }
        return res;
    }
  public  Node reverse(Node root){
//       if(root == null)return null;
//        if(root.left == null)return root;
//        Node newroot = reverse(root.left);
//        root.left.left = root.right;
//        root.left.right = root;
//        root.right = null;
//        root.left = null;
//        return newroot;

    if (root == null)  
        return null;  
    Node parent=root, left=root.left, right=root.right;
      if( left!=null){
          Node res = reverse(left);
          left.left=right;
          left.right=parent;
          parent.left=null;
          parent.right=null;
          return res;
      }
      return root;
  }
  public Node UpsideDownBinaryTree(Node root) {  
    Node node = root, parent = null, right = null;  
    while (node != null) {  
        Node left = node.left;  
        node.left = right;  
        right = node.right;  
        node.right = parent;  
        parent = node;  
        node = left;  
    }  
    return parent;  
} 
  
  private static double powerBinary(double a, int b){
		if( b<0) return 1/powerBinary(a,-1*b);
		if(b == 0)
			return 1;
		if(b == 1)
			return a;
		else if(b%2 == 0){
                    double root=powerBinary(a,b/2);
			return  root*root;}
		else
			return a* powerBinary(a,b-1) ;
		
	}
  
  public Node Upside_2(Node root) {  
    Node node = root, parent = null, sibling = null;  
    while (node != null) {  
        Node left = node.left;  
        node.left = sibling;  
        sibling=node.right;
        node.right = parent;  
        parent = node;  
        node = left;  
    }  
    return parent;  
} 
 public static void morrisT(Node root){
    // implementation here
    if (root == null ){
        System.out.println("The Tree should not be null.");
        return;
    }
    
    while(root!=null){
        if(root.left!=null){
            Node p=root.left;
            while(p.right!=null&& p.right!=root){
                p = p.right;
            }
            //reach the right most/ unthreaded node leaf
            if(p.right==null){ // the right most, need to construct its thread to root
                p.right=root;
                root= root.left;
            }else{ // p.right=root, thread already there
                // recover the tree , print, move to root.right
                 p.right = null;
                System.out.println(root.value);
                 root = root.right;
            }
        
        }else {
        System.out.println(root.value);
        root=root.right;
        }
    }
 
 
 }
  public void printTree(Node root) {
    // implementation here
    if (root == null ){
        System.out.println("The Tree should not be null.");
        return;
    }
    LinkedList<Node> currentLevel= new LinkedList<>();
    LinkedList<Node> nextLevel= new LinkedList<>();
    currentLevel.add(root);
    while( !currentLevel.isEmpty() ){
         Node p=currentLevel.remove();
         System.out.print(" "+ p.value);
         if(p.left!=null){
             nextLevel.add(p.left);
         }
         if(p.right!=null){
             nextLevel.add(p.right);
         }
         if( currentLevel.isEmpty()){
             if (!nextLevel.isEmpty()){
                 currentLevel = nextLevel;
                 nextLevel = new LinkedList<>();
             }
             System.out.println("");
         }    
    }    
  }
}


