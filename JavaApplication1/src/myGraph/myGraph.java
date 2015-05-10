/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myGraph;

import java.util.LinkedList;
import java.util.HashMap;
/**
 *
 * @author Emma
 */
enum v_color {white, grey, black}

public class myGraph {
    
    public class vertex{
        public int id;
    //    E   data;
        private v_color color;
        int Stime=0;
        int Ftime=0;
        public HashMap<Integer,Integer> Adj;  // the weight for each edge from this to adjacent vertex
        public vertex(){
            this(0);
        }
        public vertex(int vid){
            this.id = vid;
            this.Adj = new HashMap<>();
            this.color = v_color.white;
        }
        public vertex(int vid,int[] Matrix){
            this(vid);
            if (Matrix.length<vid){
                System.out.println("Wrong Adjacency Matrix");
            }else{
                for(int i=0;i<Matrix.length;i++){
                    if(Matrix[i]>0){
//         System.out.println("Matrix "+i+": "+ Matrix[i]); 
                        this.Adj.put(i,Matrix[i]);
                    }
                }
            }
        }
        public void printAdj(boolean fullMatrixNot ,int n ){
            if (fullMatrixNot){
                for(int i =0;i<n;i++){
                    if(this.Adj.containsKey(i)){
                          System.out.print(" "+this.Adj.get(i));
                    }else {
                          System.out.print(" 0");
                    }
                }    
            }else {
                for(int key:this.Adj.keySet()){
                      System.out.print(" "+key);
                }
                System.out.println("");
            }
        }
   }
    public vertex[] allV;
    public int getNumofVertex(){return allV.length;}
//    public vertex[] getAllV(){return allV;}
    public myGraph(int n, boolean defaultIni){
        allV = new vertex[n];
        if (defaultIni) {
            int[][] Matrix=new int[n][n];
            for (int i =0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(i!=j){
                        Matrix[i][j]=1;
                    }else
                        Matrix[i][j]=0;
                } //evenly connect to other vertex
            }        
            for  (int i =0;i<n;i++){
                allV[i]=new vertex(i, Matrix[i]);
            }    
        }else{
            for  (int i =0;i<n;i++){
                allV[i]=new vertex(i);
            }   
        }
    }
    public myGraph(){    
        allV = new vertex[5];
        for  (int i =0;i<5;i++){
            allV[i]=new vertex(i);
        }    
        int[][] M0= {{1,1},{4,1}};
        int[][] M1= {{0,1},{4,1}};
        int[][] M2= {{3,1}};
        int[][] M3=  {{4,1},{2,1}};
        int[][] M4=  {{3,1},{0,1},{1,1}};
         for(int i=0;i<2;i++){ 
            allV[0].Adj.put(M0[i][0],M0[i][1]);
         }
         for(int i=0;i<M1.length;i++){ 
            allV[1].Adj.put(M1[i][0],M1[i][1]);
         }
         for(int i=0;i<M2.length;i++){ 
            allV[2].Adj.put(M2[i][0],M2[i][1]);
         }
         for(int i=0;i<M3.length;i++){ 
            allV[3].Adj.put(M3[i][0],M3[i][1]);
         }
         for(int i=0;i<M4.length;i++){ 
            allV[4].Adj.put(M4[i][0],M4[i][1]);
         }        
    }
    public myGraph(int n, int[][] Matrix){
        allV = new vertex[n];
        for  (int i =0;i<n;i++){
            allV[i]=new vertex(i,Matrix[i]);
        }
        printMatrix(Matrix);
    }
    
    public void traverseBFS(){traverseBFS(0);}
    public int[] traverseBFS(int start){
        int[] ret_value = new int[allV.length];
        int index = 0;
        
        if(start<0 && start>=this.allV.length){
            System.out.println("Wrong start point. Should between 0 and "+ (this.allV.length-1)); 
            return null;
        }
        for(int i=0;i<allV.length;i++){
            allV[i].color = v_color.white;
            allV[i].Stime = (int)Double.POSITIVE_INFINITY;
            if(i==start)  allV[i].Stime = 0;
        }
        LinkedList<vertex> Queue=new LinkedList<>();
        vertex p; // =this.allV[start];
        Queue.add(this.allV[start]);
        while( !Queue.isEmpty()){
            p = Queue.remove();   
//            System.out.println(" "+p.id);
            ret_value[index]=p.id;
            index++;
            for(int key : p.Adj.keySet()){
                 if(this.allV[key].Stime== (int)Double.POSITIVE_INFINITY){
                     this.allV[key].Stime = p.Stime+1 ;
                     Queue.add(this.allV[key]);
                 }
             }  
        }
        return ret_value;
    }
    public String traverseBFS_S(int start){
        String ret_value = "";
        if(start<0 && start>=this.allV.length){
            System.out.println("Wrong start point. Should between 0 and "+ (this.allV.length-1)); 
            return ret_value;
        }
        for(int i=0;i<allV.length;i++){
            allV[i].color = v_color.white;
            allV[i].Stime = (int)Double.POSITIVE_INFINITY;
            if(i==start)  allV[i].Stime = 0;
        }
        LinkedList<vertex> Queue=new LinkedList<>();
        vertex p; // =this.allV[start];
        Queue.add(this.allV[start]);
        while( !Queue.isEmpty()){
            p = Queue.remove();   
//            System.out.println(" "+p.id);
            ret_value = ret_value + " " + p.id;
            for(int key : p.Adj.keySet()){
                 if(this.allV[key].Stime== (int)Double.POSITIVE_INFINITY){
                     this.allV[key].Stime = p.Stime+1 ;
                     Queue.add(this.allV[key]);
                 }
             }  
        }
        return ret_value;
    }
    
    public void traverseDFS(){traverseDFS(0);}
    public int[] traverseDFS(int start){
        int[] ret_sequence=new int[allV.length*2+1]; 
        if(start<0 && start>=this.allV.length){
            System.out.println("Wrong start point. Should between 0 and "+ (this.allV.length-1)); 
            return null;
        }
        for(int i=0;i<allV.length;i++){
            allV[i].color = v_color.white;
        }
        int time = 0;
        ret_sequence[time]=start;
        for (int i=start;i<allV.length;i++ ){
            if(allV[i].color==v_color.white){
                time = DFSvisit(i, time, ret_sequence);
            }
        }
        for (int i=start-1;i>=0;i-- ){
            if(allV[i].color==v_color.white){
                time = DFSvisit(i, time, ret_sequence);
            }
        }
        return ret_sequence;
    }
    public  String traverseDFS_S(int start){
        String ret_sequence = "";
        if(start<0 && start>=this.allV.length){
            System.out.println("Wrong start point. Should between 0 and "+ (this.allV.length-1)); 
            return ret_sequence;
        }
        for(int i=0;i<allV.length;i++){
            allV[i].color = v_color.white;
        }
        int time = 0;
        for (int i=start;i<allV.length;i++ ){
            if(allV[i].color==v_color.white){
                time = DFSvisit(i, time, ret_sequence);
            }
        }
        for (int i=start-1;i>=0;i-- ){
            if(allV[i].color==v_color.white){
                time = DFSvisit(i, time, ret_sequence);
            }
        }
        return ret_sequence;
    }
    public int DFSvisit(int id,int time){
        time++;
        allV[id].Stime=time;
        allV[id].color=v_color.grey;
        System.out.println(" " + id); 
        for(int key: allV[id].Adj.keySet()){
            while(allV[key].color==v_color.white){
                time = DFSvisit(key,time);
            }
        }
        time++;
        allV[id].Ftime=time;
        allV[id].color=v_color.black;
        System.out.println(" " + id); 
        return time;
    } 
    public int DFSvisit(int id,int time, String sequence){
        time++;
        allV[id].Stime=time;
        allV[id].color=v_color.grey;
        sequence = sequence + " " + id;
        for(int key: allV[id].Adj.keySet()){
            while(allV[key].color==v_color.white){
                time = DFSvisit(key,time,sequence);
            }
        }
        time++;
        allV[id].Ftime=time;
        allV[id].color=v_color.black;
        sequence = sequence + " " + id;
        return time;
    }
    public int DFSvisit(int id,int time, int[] sequence){
        time++;
        allV[id].Stime=time;
        allV[id].color=v_color.grey;
        sequence[time]=id;
        for(int key: allV[id].Adj.keySet()){
            while(allV[key].color==v_color.white){
                time = DFSvisit(key,time,sequence);
            }
        }
        time++;
        allV[id].Ftime=time;
        allV[id].color=v_color.black;
        sequence[time]=id;
        return time;
    }
    
    public void printAdj(){
        for (int i=0;i<allV.length;i++){
            allV[i].printAdj(true, allV.length);
            System.out.println("");
        }
    }
    
    public void printMatrix(int[][] M){
        for (int i=0;i<M.length;i++){ 
            System.out.printf(" %d :",i);
            for(int j=0;j<M[i].length;j++){
                System.out.printf(" %d ",M[i][j]);
            }
            System.out.println("");
        }
    }
}