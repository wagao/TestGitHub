/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Arrays;

/**
 *
 * @author Emma
 */
public class WaterFlow {
    public int[][] fMatrix;
    int m;
    int n;
    public boolean[][] sLeftTop;
    public boolean[][] sRightBottom;
    public WaterFlow(){
        this(5,5);
        int[][] A= {{ 1,2,2,3,5 },
                     { 3,2,3,4,4 },
                     { 2,4,5,3,1 },
                     { 6,7,1,4,5 },
                     { 5,1,1,2,4 }};
        this.fMatrix = A;
        iniS();
    }
    public WaterFlow(int[][] A){
        this.fMatrix = A;
        this.m=A.length;
        this.n=A[0].length;
        sLeftTop= new boolean[this.m][this.n];
        sRightBottom= new boolean[this.m][this.n];
    }
    public WaterFlow(int m, int n){
        this.m=m;
        this.n=n;
        this.fMatrix = new int[m][n];
        sLeftTop= new boolean[m][n];
        sRightBottom= new boolean[m][n];
    }
    public void iniS(){
        if(sLeftTop==null)
        sLeftTop= new boolean[m][n];
        if(sRightBottom==null)
        sRightBottom= new boolean[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 || j==0 ) 
                    sLeftTop[i][j] = true;
                else 
                    sLeftTop[i][j] = false;
                
                if( i==m-1 || j==n-1) 
                    sRightBottom[i][j] = true;
                else 
                    sRightBottom[i][j] = false;
            }
        }
        
    }
    public void findAllLefTop(){
        int noOfLayer = Math.min(m,n)-1;
        for(int i=0;i<noOfLayer;i++){ // check each layer
            for(int j=i;j<n;j++){ // for the top part of this layer
                if(sLeftTop[i][j]){ // check the one below and the one to the right 
                    // check below if existed
                    if( i+1<m && !sLeftTop[i+1][j] && this.fMatrix[i+1][j] >= this.fMatrix[i][j] ) 
                        sLeftTop[i+1][j] = true;                    
                    // check right if existed
                    if( j+1<n && !sLeftTop[i][j+1] && this.fMatrix[i][j+1] >= this.fMatrix[i][j] ) 
                        sLeftTop[i][j+1] = true;                    
                }
            }
            for(int j=i;j<m;j++){ // for the left part of this layer
                if(sLeftTop[j][i]){ // heck the one below and the one to the right 
                    // check below if existed
                    if( j+1<m && !sLeftTop[j+1][i] && this.fMatrix[j+1][i] >= this.fMatrix[j][i] ) 
                        sLeftTop[j+1][i] = true;                    
                    // check right if existed
                   if( i+1<n && !sLeftTop[j][i+1] && this.fMatrix[j][i+1] >= this.fMatrix[j][i] ) 
                        sLeftTop[j][i+1] = true;                    
                }
            }
        }
    }
    
    public void findS(){
        int top = 0;
        int left = 0;
        int bottom= m-1;
        int right = n-1;
        while( top>=0 && top<m-1 && left>=0 && left<n-1 && bottom>0 && bottom<m && right >0 && right < n){
            for(int i =0;i<n;i++){
                if(sLeftTop[top][i] ){ // go down
                    if( !sLeftTop[top+1][i] && fMatrix[top+1][i]>=fMatrix[top][i] ) sLeftTop[top+1][i] = true;
                }
                if(sRightBottom[bottom][i]){ // go up
                    if( !sRightBottom[bottom-1][i] && fMatrix[bottom-1][i]>=fMatrix[bottom][i] ) sRightBottom[bottom-1][i] = true;
                }
            }
            for(int i =0;i<m;i++){
                if(sLeftTop[i][left] ){//go right
                    if( !sLeftTop[i][left+1] && fMatrix[i][left+1]>=fMatrix[i][left] ) sLeftTop[i][left+1] = true;
                }
                if(sRightBottom[i][right]){//go left
                    if( !sRightBottom[i][right-1] && fMatrix[i][right-1]>=fMatrix[i][right] ) sRightBottom[i][right-1] = true;
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
    }

    public void findAllRightBottom(){
        int noOfLayer = Math.min(m,n)-1;
        for(int i=0;i<noOfLayer;i++){ // check each layer
            for(int j=0;j<n-i;j++){ // for the bottom part of this layer
                if(sRightBottom[n-1-i][j]){ // check the one above and the one to the left
                    //  check above
                    if( n-1-i-1>0 && !sRightBottom[n-1-i-1][j]& this.fMatrix[n-1-i-1][j] >= this.fMatrix[n-1-i][j] ) 
                        sRightBottom[n-1-i-1][j] = true;                    
                    //  check left
                    if( j-1>0 && !sRightBottom[n-1-i][j-1]& this.fMatrix[n-1-i][j-1] >= this.fMatrix[n-1-i][j] ) 
                        sRightBottom[n-1-i][j-1] = true;                    
                }
            }
            for(int j=0;j<m-i;j++){ // for the right part of this layer
                if(sRightBottom[j][n-1-i]){ // check left and above
                    //  check above    
                    if( j-1>0 && !sRightBottom[j-1][n-1-i] && this.fMatrix[j-1][n-1-i] >= this.fMatrix[j][n-1-i] ) 
                        sRightBottom[j-1][n-1-i] = true;  
                    //  check left
                    if( n-1-i-1>0 && !sRightBottom[j][n-1-i-1] && this.fMatrix[j][n-1-i-1] >= this.fMatrix[j][n-1-i] ) 
                        sRightBottom[j][n-1-i-1] = true;  
                    
                }
            }
        }
    }
   
    public void printM(Object[][] A){
        for(int i=0;i<this.m;i++){
            System.out.println(Arrays.toString(A[i]));
        }
             System.out.println();
    }
    public void printM(int[][] A){
        for(int i=0;i<this.m;i++){
            System.out.println(Arrays.toString(A[i]));
        }
             System.out.println();
    }
    public void printM(boolean[][] A){
        for(int i=0;i<this.m;i++){
            for(int j=0;j<this.n;j++) 
                System.out.print(" "+A[i][j]);
             System.out.println();
        }
             System.out.println();
    }
    public boolean toOcean(int i, int j){
        if( i<0 || i>=m || j<0 ||j>=n ){
            System.out.println("This spot is not in the land.");
            System.exit(1);// out of fMatrix range
        }
        boolean ret_val = false;
        //test if go to left-top
        if(sLeftTop[i][j]&&sRightBottom[i][j])  ret_val= true;
        //test if go to right-bottom
        
        return ret_val;
    }
   public void getToOceanMap(){
        for(int i=0;i<this.m;i++){
            for(int j=0;j<this.n;j++) {
                if(toOcean(i,j)){
                    System.out.print(" ("+this.fMatrix[i][j]+")");
                }else {
                    System.out.print("  "+this.fMatrix[i][j]+" ");
                }
            }
             System.out.println();
        }
        System.out.println();
   }
}
