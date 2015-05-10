/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.io.BufferedReader;
import java.io.InputStreamReader;   
import java.io.IOException;

/**
 *
 * @author Emma
 */
public class Solution {
    public static void main (String args[]){
        int n;
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the number of elements : ");
            n=Integer.parseInt(br.readLine());
        }catch(IOException e){
            System.out.print("IOexception. Defalut n=5. ");
            n=5;
        }
            
        int[][] A = generateMatrix(n);
       PrintArray(A,0);
       PrintArray(A,1);
//        int[][] B = generateMatrix_index(1);
//       PrintArray(B,0);
    }
    
    public static int[][] generateMatrix_index(int n) {         
         if (n==0) { int[][] ret_value={}; return ret_value;}
         if (n==1) { int[][] ret_value={{1}}; return ret_value;}
         int[][] ret_value = new int[n][n]; 
         for (int i=0;i<n; i++){
             for ( int j=0;j<n;j++){
                 int indexOfCircle = getCircleIndex( i , j , n);
                 int base = (indexOfCircle==0)? 0 : ret_value[indexOfCircle][indexOfCircle-1] ;
                 if ( indexOfCircle==i && ret_value[i][j]==0){
                    ret_value[i][j] = base + (j-indexOfCircle+1);
                 } 
                 else if(indexOfCircle==n-1-j && ret_value[i][j]==0 ){
                     ret_value[i][j] = base + ( n-2*indexOfCircle-1)+(i-indexOfCircle+1);
                 }else if(indexOfCircle==n-1-i && ret_value[i][j]==0 ){
                     ret_value[i][j] = base + 2*( n-2*indexOfCircle-1)+(n-1-indexOfCircle-j+1);
                 }else if(indexOfCircle==j && ret_value[i][j]==0 ){
                     ret_value[i][j] = base + 3*( n-2*indexOfCircle-1)+(n-1-indexOfCircle-i+1);
                 }
             }
         }
         return ret_value;
    }
    public static int[][] generateMatrix(int n) {        
         if (n==0) { int[][] ret_value={};return ret_value;}
         if (n==1) { int[][] ret_value={{1}}; return ret_value;}
         int[][] ret_value = new int[n][n]; 
         int numOfCircle = n%2==0 ? n/2 : (n+1)/2;
         int mark=0;
         for(int x =0;x<numOfCircle;x++){
             for(int j=x;j<n-x;j++){
                 mark++;
                 ret_value[x][j]=mark;
             }
             for(int j=x+1;j<n-x;j++){
                 mark++;
                 ret_value[j][n-1-x]=mark;
             }
             for(int j=n-2-x;j>=x;j--){
                 mark++;
                 ret_value[n-1-x][j]=mark;
             }
             for(int j=n-2-x;j>x;j--){
                 mark++;
                 ret_value[j][x]=mark;
             }
         }
        return ret_value;
    }
    
    public static int getCircleIndex(int x, int y, int n){
        int min  = Math.min(Math.min(x,y),Math.min(n-1-x,n-1-y));
        return min;
    }
    
    public static void  PrintArray(int[][] B, int mode){
        // mode: 0 for by index ,1 for by level binary tree
        switch (mode){
            case 0:
                for (int i=0;i<B.length;i++){
                  for (int j=0;j<B[i].length;j++){
                     System.out.print(" "+B[i][j]);
                  }
                System.out.println();
                } 
                break;
         case 1: //spiral order
            int n = B.length;
            int circleIndex = (n%2==0) ? n/2 : (n+1)/2 ; 
            for (int x =0;x<circleIndex;x++){             
                    for(int j=x;j<n-x;j++){
                        System.out.print(" "+B[x][j]);
                    }
                    for(int j=x+1;j<n-x;j++){
                        System.out.print(" "+B[j][n-1-x]);
                    }
                    for(int j=n-2-x;j>=x;j--){
                        System.out.print(" "+B[n-1-x][j]);
                    }
                    for(int j=n-2-x;j>x;j--){
                        System.out.print(" "+B[j][x]);
                    }
                }
                System.out.println();
                break;
         default:
             ;
        }
    }
}
