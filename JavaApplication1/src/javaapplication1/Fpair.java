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
public class Fpair {
    int fn;
    int[] S;
    int[] Spair;
    public Fpair(){
        this(10);
    } 
    public  int fNumber_M_BU( ){
        return fNumber_M_BU(this.fn);
    }
    public void printS(){
        if (S!=null)
        System.out.println(Arrays.toString(this.S));
        if (Spair!=null)
        System.out.println(Arrays.toString(this.Spair));
    }
    
    public int  Fpair_TD(){
        return Fpair_TD(this.fn);
    }
    public int Fpair_TD(int n){
        if(n==0){
            return 0;
        }else   if(n==1){
            return 1;
        } else 
            return (Fpair_TD(n-1)+Fpair_TD(n-2))%10;        
    }
    public int Fpair_BU(){
        return Fpair_TD(this.fn);
    }
    
    public int Fpair_BU(int n){
        Spair= new int[n+1];
        if(n==0){
            Spair[0]=0;
            return 0;
        }else   if(n==1){
            Spair[1]=1;
            return 1;
        } else {
            Spair[0]=0;
            Spair[1]=1;
            for (int i=2;i<=n;i++){
                 Spair[i] = (S[i-1]+S[i-2])%10;
            }
            System.out.println(Spair[n]);
                return Spair[n];
        }    
    }
    
    public Fpair(int n){
        fn=n;
        S= new int[n+1];
        Arrays.fill(S,-1);
    }
    public  int fNumber_TopDown(int n ){
        if(n==0){
            return 0;
        }else   if(n==1){
            return 1;
        } else 
    return fNumber_TopDown(n-1)+fNumber_TopDown(n-2);
    }
    
    public  int fNumber_M_TD(int n ){
        if (S[n]>-1)
            return S[n];
        if(n==0){
            S[n]=0;
            return 0;
        }else   if(n==1){
            S[n]=1;
            return 1;
        } else { 
            S[n]=fNumber_M_TD(n-1)+fNumber_M_TD(n-2);
            return S[n];
        }
    }
    
    public  int fNumber_M_BU(int n ){
         if(n==0){
            S[n]=0;
            return 0;
        }else   if(n==1){
            S[n]=1;
            return 1;
        } else { 
            S[0]=0;
            S[1]=1;
            for( int i=2;i<=n;i++ ){
                S[i] = S[i-1]+S[i-2];
            }
        }
         return S[n];
    }
    
    
}
