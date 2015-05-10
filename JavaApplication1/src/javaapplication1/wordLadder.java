/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
/**
 *
 * @author Emma
 */
public class wordLadder {
    
    public static class TreeLinkNode {
      int val;
      TreeLinkNode left, right, next;
      TreeLinkNode(int x) { val = x; }
      TreeLinkNode(int x,int y, int z) { 
          val = x;
          left=new TreeLinkNode(y);
          right=new TreeLinkNode(z);
      }
}
    
    
    public static List<List<Integer>> permute( LinkedList<Integer> num){
         List<List<Integer>> res = new LinkedList<>();
         List<Integer> perm = new LinkedList<>();
         int n = num.size();
         int count=0;
        if(n==0) {
            res.add(perm);
            return res;
        }else {
            while(count<n){
                int A=num.remove();
                List<List<Integer>> sub = permute(num);
                for(List<Integer> item: sub){
                        List<Integer> p = new LinkedList<>();
                        p.addAll(item);
                        p.add(0,A);
                        res.add(p);
                }
                num.add(A); 
                count++;
           }
        }
            return res;
    } 
    
    public static List<List<Integer>> permuteUnique(int[] num) {
         List<List<Integer>> res = new LinkedList<>();
         List<Integer> perm = new LinkedList<>();
         
         if(num==null) return null;
         int n=num.length;
         if(n==1) { perm.add(num[0]); res.add(perm); return res;}
         int A[]=new int[n];// get [0,n-1] perm ,and put in num[0]-num[n-1]
         LinkedList<Integer> all= new LinkedList<>();
         for(int i=0;i<n;i++){
             A[i]=i;
//             all.add(A[i]);
        }
         
        res=permute(all);
        for(List<Integer> list : res){
            
            for(int i=0;i<list.size();i++){
                int j=list.get(i);list.remove(i);
                list.add(i,num[j]);
            }
        }
          
        return res;  
    }
    
     public static void main(String[] args) {
         TreeLinkNode p= new TreeLinkNode(1,2,3);
          
           int[] A={-1,-1, 2,3};
         
//        System.out.println(all+" ");
         List<List<Integer>> res = new LinkedList<>();
//          = new LinkedList<>();
         res = permuteUnique(A);
         for(List<Integer> perm: res) {
        System.out.println(perm+" "+res.size());
         }
     }
    public void connect(TreeLinkNode root) {
        if(root==null) return;
        if(root.left==null&&root.right==null)  {
            root.next=null;
            return;
        }
        LinkedList<TreeLinkNode> path=new LinkedList<>();
        TreeLinkNode newline=new TreeLinkNode(0);newline.next=null;newline.left=null;newline.right=null;
        path.add(root);
        path.add(newline);
        while(!path.isEmpty()){
            TreeLinkNode p=path.remove();
            if(p==newline) {if(!path.isEmpty()) path.add(newline);  }
            if(p.left!=null)
            path.add(p.left);
            if(p.right!=null)
            path.add(p.right);
            // path.add(newline);
        }
        for(int i=0;i<path.size();i++){
            TreeLinkNode p = path.get(i);
            if(p==newline) continue;
            
            if(path.get(i+1)==newline)
                p.next=null;
            else{
                p.next=path.get(i+1);
            }
                
        }
    }
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new LinkedList<>();
        List<String> words = new LinkedList<>();
        if(dict==null||start==null||end==null||dict.isEmpty()) return null;
        if(start.compareTo(end)==0) { words.add( start);res.add(words); return res;}
        if(isAdj(start,end)) { words.add( start); words.add( end);res.add(words); return res;}
        
        
        LinkedList<String> tmp = new LinkedList<>();
        tmp.add(start);
        while(tmp.isEmpty()){
            int size = tmp.size();
            for(int i=0;i<size;i++){
                String done = tmp.remove();
                dict.remove(done);
                if(isAdj(done,end)){
                    tmp.add(done);
                    tmp.add(end);
                    res.add(tmp);
                    return res;
                }else{
                    List<List<String>> subRes = findLadders(done, end, dict); 
                    for(List<String> list: subRes){
                        list.add(0, done);
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }
    public boolean isAdj(String A, String B){
        int count=0;
        for(int i=0;i<A.length();i--){
            if(A.charAt(i)!=B.charAt(i))
            count++;
            if (count==2) return false;
        }
        return true;
    }
}
