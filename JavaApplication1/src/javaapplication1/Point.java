/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.HashMap;
import java.util.LinkedList;
/**
 *
 * @author Emma
 */
public class Point {
     int x;
     int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
      
      public static void main(String[] args){
          Point a=new Point(84,250);
          Point b=new Point();
          Point c=new Point(1,0);
          Point d=new Point(0,-70);
          Point e=new Point(1,-1);
          Point f=new Point(21,10);
          Point g=new Point(42,90);
          Point h=new Point(-42, -230);
//          (),(0,2),(0,2),(3,10)
//          Point(),(0,0),(1,0),(0,-70),(0,-70),(1,-1),(21,10),(42,90),(-42,-230)
          Point[] points= {a,b,c,d,d,e,f,g,h};
          System.out.println(maxPoints(points));
      }
      
    public static int maxPoints(Point[] points) {
        // need to go through all points 
        // every two points [a,b] [c,d] can define one line:k = (b-d)/(a-c); y =  x* k + (b-a*K)
        // keep records how many different stright lines(y/x) 
        
        if(points==null){System.out.println("Wrong!"); return -1;}
        int l = points.length;
        HashMap<String, LinkedList<Integer>> lines = new HashMap<>();
    //    HashMap<String, Integer> lines = new HashMap<>();
        int i,j;
        int max=l<2? l : 2; // at least we have 2 points on a line
        for(i=0;i<l;i++){
            for(j=i+1;j<l;j++){
                double k,b;
                if(points[i].x-points[j].x==0 ) { 
                    if(points[i].y-points[j].y==0){// the same point, add to each pair contains i....
                        if(lines.size()==0){ // this is the vey first new point
                            LinkedList<Integer> allP= new LinkedList<>();
                            allP.add(i);
                            lines.put("Dup"+points[i].x+points[i].y, allP);
                        }
                        for( LinkedList<Integer> p :lines.values()){
                            if(p.contains(i)&& !p.contains(j))
                                p.add(j);
                            max = Math.max(max,p.size());
                        }
                        continue;
                    } else {
                        k=Integer.MAX_VALUE;
                        b=points[j].x;
                    }
                } else { k = (double)(points[i].y-points[j].y)/(double)(points[i].x-points[j].x) ;
                 b = points[i].y - points[i].x * k ;}
                String pair=k+","+b;
                System.out.println(i+" "+j+" pair :"+pair);
                if(lines.containsKey(pair)){
                    if ( !lines.get(pair).contains(i) ) 
                        lines.get(pair).add(i);
                    if ( !lines.get(pair).contains(j) ) 
                        lines.get(pair).add(j);
                    max = Math.max(max,lines.get(pair).size());
                }else{
                    LinkedList<Integer> allP= new LinkedList<>();
                    allP.add(i);
                    allP.add(j);
                    lines.put(pair, allP);
                }
            }
        }
        return max;
    }

}
 
 