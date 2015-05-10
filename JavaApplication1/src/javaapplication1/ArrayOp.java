/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 *
 * @author Emma
 */
public class ArrayOp {
    public class Qaz{
        int val;
        int q;
        public Qaz(int x){
            val=x;
            q=0;
        }
        public Qaz(int x, int y){
            val=x;
            q=y;
        }
        
        public String toString(){
            return val+":"+q;
        }
    }
    
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n=gas.length;
        int[] minGas=new int[n]; 
        for(int i =0;i<n;i++)// the min gas remain at  needed at i to get i+1
            minGas[i]=Math.max(0, cost[i]-gas[i]);
        
        for(int i =0;i<n;i++){
            if(minGas[i]==0){ // try to start
                int g=0;
                for(int j =1;j<=n;j++){
                    g += gas[(i+j+n)%n] - cost[(i+j+n)%n];
                    if(g<minGas[(i+j+n)%n])
                        break;
                    if(j==n) return i;
                }
                
            }
        }
        return -1;
    }
    
    public void sortColors(int[] A) {
        if(A==null) return;
        //bucket sort
        int n = A.length;
        for(int i=1;i<n;i++){
           
            if(A[i-1]>A[i]) {
                int j=i;
                while(j-1>=0 && A[j-1]>A[j]){
                    swap(A, j-1, j);
                    j=j-1;
                }
//                swap(A, j, i);
            }
        }
    }
//    public void swap(int[] a, int i, int j){
//        int tmp = a[i];
//        a[i] = a[j];
//        a[j] = tmp;
//    }
    public void rotate(int[][] matrix) {
        if(matrix==null||matrix.length<=1||matrix[0].length==0 ) return ; 
        int n=matrix.length;
        int top=0,bottom=n-1,left=0,right=n-1;
        while(top<bottom){
            
            for(int i=0;i<n-1-2*top;i++){// top and left
                swap(matrix, top, left+i, bottom-i,left, n);
//                System.out.println( i+" "+matrix[top][left+i]) ;
            }
            for(int i=0;i<n-1-2*top;i++){// left and  bottom 
                swap(matrix,  bottom-i,left, bottom, right-i, n);
            }
            for(int i=0;i<n-1-2*top;i++){//  bottom and right 
                swap(matrix,  bottom, right-i,top+i,right, n);
            }
            
            top++;bottom--;left++;right--;
        }
    }
    
    // swap matrix[x][y] and matrix[s][t]
    public void swap(int[][] matrix, int x, int y, int s, int t, int n) { 
        int tmp =matrix[x][y];
        matrix[x][y] = matrix[s][t];
        matrix[s][t] = tmp;
    }
    
    public int rangeBitwiseAnd(int m, int n) {
        if(m==n) return m;
        if(m+1==n) return m&n;
        int i =0;
        for(;i<32;i++){
            if((m>>i)<=0 || (n>>i)<=0) break;
            if( ((m>>i)^(n>>i))==0){
                return (m>>i)<<i;
            }
        }
        return 0;
    }
    
  
    public int divide_2(int dividend, int divisor) {
        if( dividend==0  ) return 0; 
        if( divisor==0  ) return Integer.MAX_VALUE; 
        if( divisor==1  ) return dividend; 
        if( dividend == divisor) return 1;
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        if(a==b) return (divisor>0)^(dividend>0) ? -1 : 1;
        long res=0;
        while(a>=b){
            long product= b;
            long m = 1;
            while((product<<1)<a) {
                product<<=1;
                m<<= 1;
            }
            res += m;
            a -= product;
        }
//         System.out.println(res);
        if( (divisor>0)^(dividend>0) ) {
            res = -res;
//         System.out.println(res);
        }
        
        if (res>Integer.MAX_VALUE || res<Integer.MIN_VALUE) return Integer.MAX_VALUE;
        else return (int)res;        
    }
    
    
    public int divide(int dividend, int divisor) {
        if( dividend==0 || divisor==Integer.MIN_VALUE ) return 0; 
        // if(  divisor==1 ) return dividend; 
        if( divisor==0  ) return Integer.MAX_VALUE; 
        if( dividend == divisor) return 1;
        
        if( dividend==Integer.MIN_VALUE) { 
            if( divisor ==-1) { 
                return Integer.MAX_VALUE; 
            } else if(divisor ==1 ){
                return Integer.MIN_VALUE; 
            }else if(divisor > 1){ return -1+divide( dividend+divisor, divisor); 
            } else{ return 1+divide( dividend-divisor, divisor); 
            }
        }
        // if( divisor > 0)> Math.abs(dividend) ) return 0; 
        
        double a = (double)Math.abs(dividend);
        double b = (double)Math.abs(divisor);
        try {
            double res = Math.exp(Math.log(a)-Math.log(b));
      System.out.println( res);
            if( ((dividend>0)^(divisor>0)) ) 
                return 0-(int)Math.floor(res); 
            else  return (int)Math.floor(res);
        }catch(Exception e){
            System.out.println("Wrong");
            return Integer.MAX_VALUE; 
        }
    }
    
    public double pow(double x, int n) {
        if(n==0||x==1) return 1;
        if(n==1||x==0) return x;
        if(n<0) return (double)1/pow(x, -1*n);
        if(n%2==1){
            return pow(x,n-1) *x;
        }else{
            double res = pow(x, n/2);
            return res*res;
        }
    }
    public int single3Number(int[] A) {
        if( A==null ) return 0;
        int n=A.length;
        int res=0;
        int sum=0;
        for (int i=0;i<32;i++) {
            sum=0;
            for ( int j=0;j<n;j++){
                sum += ((A[j]>>i)& 1); 
            }
            res |=  (sum%3)<<i ; 
        }
        
        return res;
    }
    
    
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null||triangle.isEmpty()||triangle.get(0).isEmpty()) return 0;
        return minimumTotal(triangle, 0, 0);
    }
    public int minimumTotal(List<List<Integer>> triangle, int idx, int row) {
        if(triangle==null || triangle.isEmpty() || triangle.get(0).isEmpty()) return 0;
        if (triangle.size()-1==row) 
            return triangle.get(row).get(idx);
            // return Math.min(triangle.get(row).get(idx), triangle.get(row).get(idx+1) );
        List<Integer> list  = triangle.get(row);
        int op1 =list.get(idx)+ minimumTotal( triangle ,idx, row+1 );
        int op2 =list.get(idx)+ minimumTotal( triangle ,idx+1, row+1 );
        return Math.min(op1,op2);
    }

    
    public List<String[]> solveNQueens(int n) {
        List<String[]> res = new ArrayList<>();
        // permutation of n
        int[] board=new int[n];
        int i=0;
        while(i<n){
            board[i]=i++;
        }
        permutation(res, board, 0, n-1);
        return res;
    }
    
    public void permutation( List<String[]> res ,int[] A, int start, int n){
        if(start>n) return;
        if(start==n){// get all permutations
            if(checkDiag(A)){
                String[] resB = toString(A);
//            System.out.println("add to res:"+Arrays.toString(resB));
                res.add(resB);
            }
            return;
        }
        
        for(int i=start;i<=n;i++){
            swap(A, i ,start);
            permutation(res, A, start+1,n);
            swap(A, i ,start);
        }
    }
    
    public boolean checkDiag(int[] board){
        int n= board.length;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(Math.abs(board[j]-board[i])==j-i)
                   return false;
            }
        }
            System.out.println("this is a solution:"+Arrays.toString(board));
        return true;
    }
    
    public String[] toString(int[] board){
        int n= board.length;
        String[] res = new String[n];
        for(int i=0;i<n;i++){
            char[] empty=new char[n];
            Arrays.fill(empty, '.');
            empty[board[i]] ='Q';
            res[i] = new String(empty);
            System.out.println(res[i]);
        }
        return res;
    }
    
    
    public void swap(int[] A, int i ,int j){
        if(i!=j){
            A[i] ^= A[j];
            A[j] ^= A[i]; // A[i]^A[j]^A[j] = A[i]
            A[i] ^= A[j]; // A[i]^A[j]^A[i] = A[j]
        }
    }
    
    
    public int mySqrt(int x) {
        if(x<=1) return x;
        int low= 1;
        int high=Integer.MAX_VALUE;
        while(low<high-1 ){
            int mid = low+(high-low)/2;
            double p = Math.pow(mid, 2);            
//            System.out.println(p+" from "+mid);
            if(p==x){
                return mid;
            }else if(p<x){
                low=mid;
            }else {
                high = mid;
            }
        }
        return low;
    }
    
    public int singleNumber_2(int[] A){
        if(A==null) return Integer.MIN_VALUE;
        int n = A.length;
        int ones=0, twos=0, threes=0;
        for(int i=0;i<n;i++){
            twos |= ones & A[i];
            ones ^= A[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }
    
    public int climbStairs(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        if(n==2) return 2;
        return climbStairs(n-1)+climbStairs(n-2);
    }
        public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) return 0;
        if(grid.length==1 && grid[0].length==1 ) return grid[0][0]+0;
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]==0){//has not been visited
                    if(grid[i][j]=='1') count++;
                    visit( grid, i , j,m,n, visited);
                }
            }
        }
        return count;
    }
    
    public void visit(char[][] grid, int i , int j,int m, int n, int[][] v){
        v[i][j]=1;
        if(grid[i][j]=='1'){
            //check top 
            if(i>0 && grid[i-1][j]=='1' && v[i-1][j]==0 )    visit(grid, i-1, j,m,n, v);
            //check down
            if(i<m-1 && grid[i+1][j]=='1'&& v[i+1][j]==0 )  visit(grid, i+1, j,m,n, v);
            //check left
            if(j>0 && grid[i][j-1]=='1'&& v[i][j-1]==0 )    visit(grid, i, j-1, m,n, v);
            //check right
            if(j<n-1 && grid[i][j+1]=='1'&& v[i][j+1]==0 )  visit(grid, i, j+1,m,n, v);
        }  
    }
    
    public int removeDuplicates_2(int[] A) {        
         if(A==null) return 0;
         int n=A.length;
         if(n==0||n==1) return n;
         return remove_2(A, 0, n-1);
    }
    public int remove_2(int[] A, int h, int r){
        if(h>r||h<0||h>=A.length||r<0||r>=A.length) return 0;
        if(h==r){
            return 0;
        }else if(h+1==r){
            if(A[h]==A[r]){
                for(int t=r+1;t<A.length;t++)
                    A[t-r+h] =  A[t];
            return 1;
            }else return 0;
        }else{
            if(A[h]==A[r]){//move r+1 to h+1
                for(int t=r+1;t<A.length;t++)
                    A[t-r+h] =  A[t];
                return r-h;
            }else {// A[h]<A[r]
                int mid=(h+r)/2;
                 int a = remove_2( A, mid+1, r);
                 int b =remove_2( A, h, mid);
                 return a+b;
            }
        }
    }
    
   
    public List<Integer> grayCode(int n) {
        List<Integer> res= new LinkedList<>();
        res.add(0);
        if(n==0) return res;
        res.add(1);
        if(n==1) return res;
        int size=0;
        for(int i=1;i<n;i++){
            size=res.size();
            for(int j=size-1;j>=0;j--){
                res.add(res.get(j)^(1<<(i)));
            }
        }
        
        return res;
    }
    
    public int countPrimes(int n) {
       
        int p=2;
        int count=1;
        while(p<=n){
            int r=p%10;
            if((r==1|| r==3||r==5| r==7|| r==9)&&isPrime(p)){
            count++;System.out.println( p );}
            
            p ++;
        }
        return n<2 ? 0 : count;
    }
    public boolean isPrime(int n){
        int dividor =2;
        while(dividor<n){
            if(n%dividor==0)
                return false;
            dividor++;
        }
        return n==0 ? false : true;
    }
    
    public String getPermutation(int n, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        k = k-1;
        int factorial  = 1;
        for(int i=2;i<n;i++)  factorial *= i;  // (n-1)!
        for(int i=1;i<=n;i++) list.add(i);
       
       StringBuilder res = new StringBuilder();
       while(n>1){
           int d=k/factorial;
           res.append(list.remove(d));
           k = k%factorial;
           n--;
           factorial /= n;
       }
       
           res.append(list.remove(0));
       return res.toString();
    }
    public int[] plusOne(int[] digits) {
        if(digits==null||digits.length==0) return digits;
        int n=digits.length;
        
        int last= digits[n-1]+1;
        int carryover = (last>=10) ? 1 :0;
        digits[n-1] = last%10; 
        int i=n-2;
        while( carryover>0&&i>=0){
            int tmp =digits[i]+carryover;
            carryover =  (tmp>=10)? 1:0;
            digits[i]= tmp%10;
            i--;
        }
        if(carryover>0){
            int[] res = new int[n+1]; 
            res[0]=1;
            for(int j=0;j<n;j++)
                res[j+1]=digits[j];
            return res;
        }
        return digits;
    }
    
    public int longestValidParentheses_2(String s) {
        if(s==null||s.length()<2) return 0;
        Deque<String> stack = new ArrayDeque<>(); 
        int n=s.length();
        int max=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                stack.push("(");
            }else if(s.charAt(i)==')'){
                String p =stack.peek();
                if(stack.isEmpty()||p.equals(")")) {
                    stack.push(")"); 
                    continue;
                }
                boolean closedflag=false;
                
                int t = 0; 
                while( p!=null && ( (p.equals("(")&& !closedflag) || ( !p.equals("(") && !p.equals(")")&&Integer.parseInt(p)>0) ) ){
                    if(p.equals("(")&& !closedflag) { t +=2 ;closedflag=true;}
                    else { t += Integer.parseInt(p);}
                    max=Math.max(max, t);
                    stack.pop();
                    if(!stack.isEmpty()){
                         p =stack.peek(); 
                         if( !(p.equals("(")&& !closedflag) && !( !p.equals("(") && !p.equals(")")&&Integer.parseInt(p)>0) )
                             stack.push(t+"");
                    } else {
                        stack.push(t+""); break;
                    }
                }               
                    if(!closedflag)stack.push(")"); 
            }
        }
        return max;
    }
      public int longestValidParentheses_3(String s) {
        if(s==null||s.length()<2) return 0;
        Deque<Integer> stack = new ArrayDeque<>(); 
        int n=s.length();
        int max=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                stack.push(i);
            }else if(   s.charAt(i)==')'){
                if(!stack.isEmpty() && s.charAt(stack.peek())=='('){
                    stack.pop();
                    int last = -1;
                    if(!stack.isEmpty()){
                        last = stack.peek();
                    }
                    max = Math.max(max, i-last);
                }else {
                    stack.push(i);
                }
            }
        }
        return max;
    }
 public int longestValidParentheses(String s) {
        if(s==null||s.length()<2) return 0;
        Deque<String> stack = new ArrayDeque<>(); 
        int open=0;
        int close=0;
        int n=s.length();
        int max=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                stack.push("(");
            }else if(s.charAt(i)==')'){
                if(stack.isEmpty()) {
                    stack.push(")"); 
                    continue;
                }
                
                String p =stack.peek();
                int t = 0; 
                while(!p.equals("(")&&!p.equals(")")&&Integer.parseInt(p)>0){
                    t += Integer.parseInt(p);
                    stack.pop();
                    if(!stack.isEmpty())
                        p =stack.peek(); 
                    else 
                        break;
                }
                if( p.equals("("))  {
                    stack.pop();
                    max=Math.max(max, t+2);
                    stack.push((t+2)+"");
                }else if( p.equals(")") )  {
                    if(t!=0) stack.push(t+"");
                    stack.push(")");
                }
                
              if(!stack.isEmpty()){
                    p = stack.peek();
                    int tt=0;
                    while (!p.equals("(")&&!p.equals(")")&&Integer.parseInt(p)>0) {
                        tt  += Integer.parseInt(p);
                        max=Math.max(max, tt);
                        stack.pop();
                        if(!stack.isEmpty()){
                            p =stack.peek(); 
                            if(p.equals("(") || p.equals(")")) stack.push(tt+"");
                        } else {
                            stack.push(tt+"");
                            break;
                        }
                    }                 
              }
            }
        }
        return max;
    }
  
  
    public boolean isValidSudoku(char[][] board) {
        if(board==null||board.length!=9||board[0].length!=9) return false;
        
        HashSet<Integer>[] cols = new HashSet[9];
        HashSet<Integer>[] rows = new HashSet[9];
        HashSet<Integer>[][] sq = new HashSet[3][3];
        for(int i=0;i<9;i++){
            cols[i]=new HashSet<>();
        }
        for(int i=0;i<9;i++){
            rows[i]=new HashSet<>();
        }
        return true ;
    }
    
    public int removeDuplicates_3(int[] A) {
        if(A==null) return 0;
        int n=A.length;
        if(n<2) return 0;
        int id=1;
        for(int i =1; i<n;i++){
            if(A[i-1]!=A[i]){
                A[id]=A[i];
                id++;
            }
        }
        return id;
    }
    public int removeDuplicates(int[] A) {
        if(A==null) return 0;
        int n=A.length;
        if(n==0||n==1) return n;
        int remove =0 ;
        for(int i =0; i<n-1-remove;i++){
            if(A[i+1]==A[i]){
                remove++;
                for(int j=2;i+j<n;j++){
                    if(A[i+j]==A[i]){
                        remove++;
                    }else {
                        //  dups [i, i+j-1], move all numbers  i+j->i+1
                        for(int t=i+j;t<n;t++){
                            A[t-j+1] = A[t];
                        }
                        break;
                    }
                }
            }        
        }
        return n-remove;
    }
    // Max Qaz of unsorted array
    // Qaz is the count of nums who are bigger than current one and appears after current in the array
    // merge-sort
    
    public int  maxQms(int[] num){
        if( num==null|| num.length<=1) return 0;
       int n=num.length;
       Qaz[] res = new Qaz[n];
       for(int i=0;i<n;i++){
           res[i] = new Qaz(num[i]);
       }
       maxQms( res , 0 ,  n-1);
//      for(Qaz i : res)
//       System.out.println(i.toString());
       int max=0;
       for(Qaz i: res){
           if(i.q>max)
               max=i.q;
       }
       return max;
    }    
    public void maxQms(Qaz[] num, int h,  int r){
       if(h>=r) return;
       int mid = (h+r)/2;
       maxQms(num, h, mid);
       maxQms(num, mid+1, r);
      
       maxQmerge_q( num, h , mid , r); // update  q, and sort by q
//       maxQmerge( num, h , mid , r); // sort by value
    }    
    
    // sort by val, and update q
   public void maxQmerge_q(Qaz[] num, int h, int mid , int r){        
//    public void maxQmerge_q(Qaz[] nums, int start, int mid, int end){
//        Qaz[] temp = new Qaz[end-start+1];
//  int start2 = mid+1; 
//  int k = temp.length-1;
//  int add = 0;
//  while(start<=mid && start2<=end){
//   if(nums[mid].val<nums[end].val){
//    temp[k--] = nums[end--];
//    add++;
//   }
//   else{
//    temp[k] = nums[mid--];
//    temp[k].q = temp[k].q + add;
//    k--;
//   }
//  }  
//  while(start<=mid){
//   temp[k] = nums[mid--];
//   temp[k].q = temp[k].q + add;
//   k--;
//  }
//  while(start2<=end)
//   temp[k--] = nums[end--];
//  
//    System.arraycopy(temp, 0, nums, start, temp.length);
    
        if(h>=r) return;
        int lEnd=mid;
        int rEnd=r;
        int addQaz=0;
        while(lEnd>=h&&rEnd>mid){
            if(num[lEnd].val<num[rEnd].val){
                rEnd--;
                addQaz++;
            }else{ // apply change on lEnd
                num[lEnd].q += addQaz;
                lEnd--;
            }
        }
        if(rEnd<=mid){ //rightpart reach left first , 
            for(int i=h;i<=lEnd;i++)
                num[i].q += addQaz;
        }
        
        //merge by value
        maxQmerge(  num,  h,  mid ,  r);
    }
    // sort by value
    public void maxQmerge(Qaz[] num, int h, int mid , int r){
        if(h>=r||num==null||h<0||h>=num.length||r<0||r>=num.length) return;
        Qaz[] left= new Qaz[mid-h+2];
        Qaz[] right= new Qaz[r-mid+1];
        for(int i=0;i<mid-h+1;i++){
            left[i] =new Qaz(num[h+i].val, num[h+i].q);
        }
        for(int i=0;i<r-mid;i++){
            right[i] =new Qaz(num[mid+1+i].val,num[mid+1+i].q);
        }
        left[mid-h+1]= new Qaz(Integer.MAX_VALUE);
        right[r-mid]= new Qaz(Integer.MAX_VALUE);
        
        int i=0;
        int j=0;
        int count=0;
        for(int k=h;k<=r;k++){
            if(left[i].val<= right[j].val){
                num[k].val=left[i].val;
                num[k].q=left[i].q;
                i++;
            } else {
                num[k].val=right[j].val;
                num[k].q= right[j].q;
                j++;
            }
        }
    } 
    
    public void  mergeSort(int[] A, int h, int r){
        if(h>=r) return ;
        if (h<0||r>=A.length) {  System.out.println("h or r is out of range."); return;}
        int mid=(h+r)/2;
        mergeSort(A,  h, mid );
        mergeSort( A, mid+1, r);
        merge(A,h, mid ,r );
        return ;
    }
    public void merge(int[] A, int h, int mid, int r){
        int[] left =Arrays.copyOfRange(A, h,mid+2);
        left[left.length-1]=Integer.MAX_VALUE;
        int[] right =Arrays.copyOfRange(A, mid+1,r+2);
        right[right.length-1]=Integer.MAX_VALUE;
        int i=0;
        int j=0;
        for( int k=h;k<=r;k++ ){
       
            if(left[i]<=right[j]){
                A[k]=left[i];
                i++;
            }else{
                A[k]=right[j];
                j++;
            }
        }
    }
    
    public void  updateQaz(int[] num, int h, int r){
        if(h>r||num==null||h<0||h>=num.length||r<0||r>=num.length) return ;
        int mid =(h+r)/2;
        int m=mid;
        int n=r;
        int count=0;
        int[] res=new int[num.length];
        while( m>=0&&n>mid) {
            if(num[n]>num[m]){
                count++;
                n--;
            }else if(num[n]<num[m]){
                res[n]+=count;
                m--;
            }else {
                n--;
                m--;
            }
        }
        if(n<=mid){//the second half still 
        }
        
    } 
         
  
    
    public int maxQRun_2(int[] num){
        int max=Integer.MIN_VALUE;
        int maxNum=num[0];
        for(int i=0;i<num.length;i++){
            int tmp =qaz(num, i);
           if (max<tmp){
               max = tmp;
               maxNum = num[i];
           }
        }
        System.out.println(maxNum+" :"+max);
        return max;
    }
    
    public int qaz(int[] num, int idx ){
        if( num==null|| idx<0 || idx>=num.length) return Integer.MIN_VALUE;
        int res = 0;
        for(int i=idx+1;i<num.length;i++){
            if(num[i]>num[idx])
                res++;
        }
        return res;
    }
    
    
      public int maxQRun_3(int[] num){
        int[] res= maxQ_3(num, 0, num.length-1);
        System.out.println(res[0]+" :"+res[1]);
        return res[1];
    }
    public int[] maxQ_3(int[] num, int h, int r){
        if(h>r||num==null||h<0||h>=num.length||r<0||r>=num.length) return null;
        int[] res = new int[4];
        if(h==r){
            res[0]=num[h];
            res[1]=0;
            res[2]=num[h];
            res[3]=0;
        }else if(h+1==r){ // merge the first two singles, they both have the maxQ=0
            res[0]=Math.min(num[h],num[r]);
            res[1]=(num[h]<num[r])? 1:0;
            res[2]=res[0];
            res[3]=res[1];
        }else { // if(h<r)
          int mid =(h+r)/2;
          int[] left = maxQ_3(num,h,mid);
          int[] right = maxQ_3(num,mid+1, r);
          if(left[0]<right[0]){
              res[0]=left[0];
              res[1]=left[1]+right[1];
              if( left[2]<=right[2]){
                  res[2]=left[2];
                  res[3]=left[3]+right[3];
              }else{
                  res[2]=right[2];
                  res[3]=right[3];
              }
          }else if(left[0]<right[0]) {
          }else{
              for(int j=mid+1;j<=r;j++){
                  if(num[j]>left[0])
                      left[1]++;
              }
              
              if(left[1]>right[1]){
                res[0]=left[0];
                res[1]=left[1];
              }else if(left[1]<right[1]){
                res[0]=right[0];
                res[1]=right[1];
              }else{
                  if(right[0]<left[0]){
                    res[0]=right[0];
                    res[1]=right[1];
                  }else {
                    res[0]=left[0];
                    res[1]=left[1];
                  }
              }
          }
        }
        return res;
    } 
   
    public int rotatesearch(int[] A, int target) {
        if(A==null||A.length==0) return -1;
        return findRange(A, 0 , A.length-1 ,target );
    }
    public int findRange(int[] A, int h, int r, int target){
        if(h<=r){
            if(h==r){ // this is the correct part
                return A[h]==target ? h : -1 ;
            }else{
            // if( A[h]<=target && target<=A[r] ){ // this is the correct part
            //     return binnarysearch(A, h , r, target) ;
            // }else{
                // A[h]>A[r], need to find with part is
                int mid= (h+r)/2;
                
                if(A[mid]==target){
                    return mid;
                }
                if( A[h]<=A[mid] ){// left part is good, right part is broken. test if target is with in left part
                    if(A[h]<=target &&target< A[mid]) 
                        return binnarysearch(A , h, mid-1, target);
                     else    
                        return findRange(A, mid+1, r, target);
                } else { 
                    if(A[mid] <target && target<= A[r]) 
                        return binnarysearch(A, mid+1, r, target);
                    else 
                        return findRange(A, h, mid-1, target);
                }
           }            
        }
        return -1;        
    }
    
    public int binnarysearch(int[] A, int h, int r, int target){
        if(h<=r){
            if(h==r){
                 if(A[h]==target)
                    return h;
                else 
                    return -1;
            }else{
                int mid= (h+r)/2;
                if(A[mid]==target){
                    return mid;
                }else if (target<A[mid]){
                    return binnarysearch(A, h, mid-1, target);
                }else{
                    return binnarysearch(A, mid+1, r, target);
                }
            }
        }
        return -1;
    }
    
public int generateTarget(int Max){
    int target =(int)(Math.random()*Max);
      System.out.println(" target:"+ target);    
    return target;
}
public int[] generateRotateArray(int Max){
    int seed = (int)(Math.random()*Max)+1;
    int r = (int)(Math.random()*Max/2)+1;
     int[] num = new int[seed];
     for(int i=0;i<seed;i++)
         num[i]= (i+r+seed)%seed;
     
      System.out.print(" Array: "+ Arrays.toString(num));          
     return num;
 }

 public int binnarySearchIter(int[] A,int target){
     int h = 0;
     int r= A.length-1;
     while(h<=r){
         int mid= (h+r)/2;
         if(A[mid]==target) return mid;
         if(A[mid]<target) h=mid+1;
         else r=mid-1;
     }
     return -1;
 }

    public int searchRotate(int[] A, int target) {
        if(A==null||A.length==0) return -1;
        int n=A.length;
        int h=0;
        int r=n-1;
        
        while(h<r){
            int mid=(h+r)/2;
            if(A[mid]>A[r]) // if any part broken , always go to broken part for the smallest int
                h=mid+1;
            else // if no broken part, smallest should always to the left
                r=mid;
        }
        // after the loop above, h ==r is the smallest int,
        int base = h; //treat the smallest as a base
        h=0;
        r=n-1;
        while(h<=r){
            int mid=(h+r)/2;
            int mmid = (base+mid)%n;
            if(A[mmid]==target) return mmid;
            else if(A[mmid]<target) h=mid+1;
            else r=mid-1;
        }
        return -1;
        // return findRange(A, 0 , A.length-1 , target);
    }
}
