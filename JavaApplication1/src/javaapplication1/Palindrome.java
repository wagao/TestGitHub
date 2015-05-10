/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
/**
 *
 * @author Emma
 */
public class Palindrome {
    public int[][] mem;
    public int n;
    public Palindrome(){        
    }
    public int getPalindrome(int[] foo){
        n = foo.length;
        mem = new int[n][n];
        for(int i =0;i<n;i++){
            mem[i][i]=1;
        }
        return getPalindrome(foo, 0, n-1);
    }
    private int getPalindrome(int[] foo, int h, int t){
        int max;
        if( h<0 || h>=n || t>=n || t<0 || h>t ) {
            if( h>t+1  ) System.out.println("Wrong index.");
            return 0;
        }
        if(mem[h][t]>0) return  mem[h][t];
        if(foo[h]==foo[t]){
            max = getPalindrome( foo, h+1, t-1) +2 ;
        }else{
            max = Math.max(getPalindrome( foo, h+1, t), getPalindrome( foo, h, t-1));
        }
        mem[h][t]= max;
        return max;
    }
}
