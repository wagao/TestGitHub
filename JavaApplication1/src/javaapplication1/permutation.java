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
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
    import java.util.Collections;
import javaapplication1.TreePrinter.Node;
import java.util.Comparator;
/**
 *
 * @author Emma
 */
public class permutation {
      List<List<Integer>>  res=new  LinkedList<> (); 
      
      public char[][] solve(char[][] board) {
         if(board==null||board.length==0|| board.length<=2) return board;
        int m=board.length;
        int n=board[0].length;
        if(n<=2) return board;
        char[][] solid=new char[m][n];
//        for(int i =0; i<m;i++){
//            Arrays.fill(solid[i],'X') ; // solid[i] = Arrays.copyOf( board[i], n );
//        }
        
         solid[0] =  board[0];
         solid[m-1] =  board[m-1];
        for(int i =1; i<m-1;i++){
            Arrays.fill(solid[i],'X') ; // solid[i] = Arrays.copyOf( board[i], n );
        }
        
        int top=0;
        int left=0;
        int right=n-1;
        int bottom=m-1;
        while(top<=bottom&&left<=right){
             boolean atleastOneO = false;
            for ( int i =left;i<=right;i++){
                if(board[top][i]=='O' && ( top==0 || solid[top-1][i]=='O'|| (i<n-1&&solid[top][i+1]=='O') || (i>=1&&solid[top][i-1]=='O'))){
                    if(!atleastOneO) atleastOneO=true;
                    solid[top][i]='O';
                }
            }
            for ( int i =top;i<=bottom;i++){
                if(board[i][right]=='O' && ( right==n-1 || solid[i][right+1]=='O'|| (i<m-1&&solid[i+1][right]=='O')|| (i>=1&&solid[i-1][right]=='O'))){
                    if(!atleastOneO) atleastOneO=true;
                    solid[i][right]='O';
                }
            }
            for ( int i =right;i>=left;i--){
                if(board[bottom][i]=='O' && ( bottom==m-1 || solid[bottom+1][i]=='O'|| (i>=1&& solid[bottom][i-1]=='O')|| (i<n-1&&solid[bottom][i+1]=='O'))){
                    if(!atleastOneO) atleastOneO=true;
                    solid[bottom][i]='O';
                }
            }
            for ( int i =bottom ;i>=top;i--){
                if(board[i][left]=='O' && ( left==0 || solid[i][left-1]=='O'|| (i<m-1&&solid[i+1][left]=='O') ||(i>=1&& solid[i-1][left]=='O'))){
                    if(!atleastOneO) atleastOneO=true;
                    solid[i][left]='O';
                }
            }
            
            if(atleastOneO==false)
                break;
            top++;bottom--;left++;right--;
        }
        return solid;
    }
       
    public int abs (int[] a, int[] b) {
        int m =a.length;
        int n =b.length;
        int min= Integer.MAX_VALUE;
        
        int i=0,j=0;
        while(i<m&&j<n){
            if (a[i] <= b[j]) {  min = Math.min(min, b[j]-a[i] ); i++; } 
            else {  min = Math.min( min, a[i]-b[j]); j++; }
        }
        if(i==m&&j<n  && b[j]<=a[m-1]){ // a reach the end, while b not,b increasing while a[m-1]
            while(j<n){
                 min = Math.min( min, a[m-1]-b[j]); j++;
            }
        }else  if(j==n &&i<m && b[n-1]>=a[i]){ // b reach the end, while a not, a increasing while b[n-1]
            while(i<m){
                 min = Math.min( min, b[n-1]-a[i]); i++;
            }
        }
        return min;
    }
    public static void main(String[] args) {
    permutation test=new permutation();
//        int[] a={200,45,67,27,-20,16,17};
//        int[] b={145,6,7,0,-121,-6,-7};
//        int[] c={-2,0,1, 221,222};
//        int[] d={ 33, 201,225};
//        CharSequence s=new StringBuffer("fr");
//        
//        Arrays.sort(a);
//        Arrays.sort(b);
//         System.out.println(Arrays.toString(a));
//         System.out.println(Arrays.toString(b));
//         System.out.println("ewfrkewo".contains(s));
//         System.out.println(test.abs(c , d));
//    char[][] abc ={{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}} ;
//    abc = test.solve(abc);
//    for(char[] i:abc){
//        System.out.println(Arrays.toString(i));
//    }
    int x =9;
        String s=Integer.toString(Math.abs(x));
    TreePrinter test2= new TreePrinter();
    Node root5= new Node(5);
    Node root4= new Node(4);
    Node root2= new Node(2, root4, root5);
    Node root3= new Node(3);
    Node root1= new Node(1,root2, root3);
test2.printTree(root1);
//    Node p2 = test2.UpsideDownBinaryTree(root1);
//test2.printTree(p2);
// Node p = test2.Upside_2(root1);
//test2.printTree(p);
//test2.pathSum_2(root1, 8);
//for(List<Integer> i:test2.res)
//System.out.println( i);



//    Node p3 = test2.reverse(root1);
//    test2.printTree(p3);

//     TreePrinter.morrisT(root3);
//        int[] a = {4,1,2,3};  
////        test.runpDuup(a); 
//        
//         List<List<Integer>>  pres = test.runP(a);
////        test.runCom(a, 2); 
//        for(List item: pres){
//        System.out.println(item+":"+pres.size());
//        }
    }
    public   List<List<Integer>>  runCom(int[] num, int l){ // if no l ,or l==num.length, only one combination 
        if(num==null||num.length==0 || l>num.length||l<=0 ) return null;
        int n = num.length;
        int[] b = new int[l];
        getCom(num,  l, 0, b, 0 );
        return res; 
    }
    public   void getCom(int[] num, int stillNeed, int begin,int[] b,int idx){ 
        if(stillNeed==0) { // find enough for one com, store output
            List<Integer>  perm =new  LinkedList<> ();
            for(int i: b ){
                perm.add(i);
            }
            res.add(perm);
        }else{
            for(int i=begin;i<num.length;i++){
                b[idx]=num[i];
                getCom(num, stillNeed-1, i+1, b, idx+1);
            }
        }
//        return res;
    }
    public   List<List<Integer>>  runP(int[] num){
        if(num==null||num.length==0) return null;
        int n = num.length;
        
      List<List<Integer>>  resP;// =new  LinkedList<> (); 
        resP = getPerm(num, 0 );
        return resP; 
    }
    public    List<List<Integer>>   getPerm(int[] num, int start){
      List<List<Integer>>  resP=new  LinkedList<> (); 
        if(start==num.length){
            List<Integer>  perm =new  LinkedList<> ();
            for(int i: num ){
                perm.add(i);
            }
            resP.add(perm);
        }else{
            for(int i= start;i<num.length;i++){
                swap(num, i, start);
                List<List<Integer>>  tmp =getPerm(num, start+1);
                for(List<Integer> list: tmp){
                    resP.add(list);
                }
//                resP = getPerm(num, start+1);
                swap(num, i, start);
            }
        }
        return resP;
    }
   
    
    
//    List<List<Integer>> res= new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] num) {
        if(num==null) return res;
        int n=num.length;
        List<Integer> item=new ArrayList<>();
        res.add(item);
        if(n==0) return res;
        for(int i=0;i<n;i++){
            comWithDup(num, 0, i+1);
        }
//        comWithDup(num, 0, 2);
        return res;
    }
    
    public void comWithDup(int[] num, int start, int m) {
        int n=num.length;
        if(start>m) return;
        if(start==m){//enough element to pick [0,m-1] ,output
            List<Integer> item=new ArrayList<>();
            for(int i=0;i<start;i++) item.add(num[i]);
             Collections.sort(item, new Comparator() {  
                public int compare(Object o1, Object o2) {  
                    return (Integer)o1 - (Integer)o2;  
                }  
                });  
            for( List<Integer> each : res){
                if(each.equals(item)) return;
            }
            res.add(item);
            return ;
        }
        
        for(int i=start;i<n;i++){
            if(contains(num,start, i)) continue;
             swap(num, start, i);
             comWithDup(num,start+1, m);
             swap(num, start, i);
        }
    }
    
    
    public boolean contains(int[] A, int start, int target){
        if(A==null||start>target) return false;
        for(int i=start;i<target;i++){
            if(A[i]==A[target]) {System.out.println("true" );
            return true;}
        }
        return false;
    }
    public void swap(int[] A, int i, int j){
        if(i==j|| A[i]==A[j]||i<0||j<0||i>=A.length||j>=A.length) return;
        int tmp=A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
       
    public   List<List<Integer>>  runpDuup(int[] num){
        if(num==null||num.length==0) return null;
        int n = num.length;
        int[] b = new int[n];
        pDuup(num, b, 0);
        return res; 
    }
    public   void  pDuup(int[] num, int[] b, int idx){
        if(num==null||num.length==0) return;
        int n = num.length;
        if(idx==n){
            List<Integer>  perm =new  LinkedList<> ();
            for(int i:b){
                perm.add(i);
            }
            res.add(perm);
            return;
        }
        for(int i=0; i<n;i++){
            b[idx]=num[i];
            pDuup(num,b, idx+1);
        }
     }
}