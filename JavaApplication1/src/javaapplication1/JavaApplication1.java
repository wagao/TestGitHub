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

//import 

/**
 *
 * @author Emma
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    
  public static class Interval {
     int start;
      int end;
    Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
      public static void print(Interval a){
          System.out.println("["+ a.start+","+a.end+"]");
      }
 }
  public static void inttest(){
 List<Interval> intervals = new LinkedList<>();
 Interval p1= new Interval(1,4);
 intervals.add(p1);
 intervals.add(p1);
 for(Interval i : intervals)Interval.print(i);
 merge(intervals);
  }
    public static List<Interval> merge(List<Interval> intervals) {
        if(intervals==null) return null;
        if(intervals.size()==1) return intervals;
        for(int i=0;i<intervals.size()-1;i++){
            Interval a=intervals.get(i);Interval.print(a);
            Interval b=intervals.get(i+1);Interval.print(b);
            while(a.end>=b.start){//merge
                a.end=Math.max(a.end, b.end);
                 System.out.println(i+" "+ (b.start));
                intervals.remove(i+1);
                if(i<intervals.size()-1) b=intervals.get(i+1);
            }
        }
        return intervals;
    }
     public static ListNode mergeKLists_3(List<ListNode> lists) {
         if(lists==null || lists.size()==0 ) return null;
         for(int i=0;i<lists.size();i++){
             try{  if( lists.get(i)==null )
                 lists.remove(i);
             } catch(Exception e){
                System.out.println("Empty listnode found..");
                lists.remove(i);
                continue;
            }
         }
         if(lists==null || lists.size()==0 ) return null; // now all nodes are good
          if( lists.size()==1 ) return lists.get(0); // now all nodes are good
         ListNode[] allN= toArray(lists);
         ListNode p = merge2Lists(allN[0],allN[1]);
         for(int i=2;i<allN.length;i++) {
             p=merge2Lists(p,allN[i]);
         }
         return p;
     }
     public static  ListNode[] toArray(List<ListNode> lists){
         int K = lists.size();
         ListNode[] ret = new ListNode[K];
         for (int i=0;i<K;i++) {
             ret[i]=lists.get(i);
         }
         return ret;
     }
      public static ListNode mergeKLists_4(List<ListNode> lists) {
         if(lists==null || lists.size()==0 ) return null;
         for(int i=0;i<lists.size();i++){
             try{  if( lists.get(i)==null )
                 lists.remove(i);
             } catch(Exception e){
                System.out.println("Empty listnode found..");
                lists.remove(i);
                continue;
            }
         }
         if(lists==null || lists.size()==0 ) return null; // now all nodes are good
          if( lists.size()==1 ) return lists.get(0); // now all nodes are good
         ListNode[] allN= toArray(lists);
         ListNode p=null, merging=null; 
         if( (lists.size())%2==1){ 
             p= merge2Lists(allN[0],allN[1]);
             lists.remove(0);
             lists.remove(1);
             lists.add(0,p);
         }
         while(lists.size()>=2){
             int m = lists.size()/2;
             for(int i=m;i>0;i--){
                 merging=merge2Lists(lists.get(2*m-2),lists.get(2*m-1));
                 lists.remove(2*m-2);
                 lists.remove(2*m-1);
                 lists.add(2*m-2,merging);
             }
             
         }
         return lists.get(0);
     }
      public static void mergeKtest(){
         ListNode a =new ListNode(0);
         ListNode.print(a);
         ListNode b =new ListNode(1);
         ListNode.print(b);
         List<ListNode> c=new LinkedList<>();
         c.add(a);
         c.add(b);
         
//         ListNode.print(mergeKLists_3(c));
         ListNode.print(mergeKLists_4(c));
         
     }
      public static  ListNode merge2Lists(ListNode A, ListNode B) {
          ListNode a=A;
          ListNode b=B;
          int count=0;
          List<ListNode> result = new LinkedList<>();
          while(a!=null && b!=null){
              if(a.val<b.val){
                  result.add(a);count++;a=a.next;
              }else if(b.val<a.val){
                  result.add(b);count++;b=b.next;
              }else {
                  result.add(a);
                  count++;a=a.next;
                  result.add(b);
                  count++;b=b.next;
              }
          }
          if(a!=null){
              while(a!=null){
                  result.add(a);
                  count++;a=a.next;
              }
          }else if(b!=null) {
              while(b!=null){
                  result.add(b);
                  count++;b=b.next;
              }
        }
        for(int i =0;i<result.size();i++){ // change the next pointer
            if(i==result.size()-1){
                 result.get(i).next=null;
            }else {
            result.get(i).next = result.get(i+1);
             System.out.println(  result.get(i).val +"'s next is "+result.get(i+1).val);
            }
        }
        
        return (result!=null&& result.size()>0) ? result.get(0) : null;
      }
    static ArrayList testList= new ArrayList();
    
//     public List<String> wordBreak(String s, Set<String> dict) {
//        // List<String> res = new LinkedList<>();
//         int len = s.length();
//         LinkedList<String> p = new LinkedList<>();
//        HashMap<Integer,LinkedList<String>> r = new HashMap<>();
//        if(s==null|| dict ==null || dict.size()==0) return p;
//        p.add("");
//        r.put(len, p);
//        for(int i =len-1;i>=0;i--){
//            for(int j =len; j>i; j--){
//                if (r.get(j)!=null  &&  dict.contains(s.substring(i,j))){
//                    for(String substr : r.get(j)){
//                        if( r.get(i)==null) {
//                            LinkedList<String> tmp = new LinkedList<>();
//                            tmp.add( s.substring(i,j)+" "+substr);
//                            r.put(i, tmp);
//                        } else {
//                            r.get(i).add(s.substring(i,j)+" "+substr);
//                        }
//                    }
//                }
//            }
//        }
//        return r.get(0);
//    }
                public static int maximumGap(int[] num) {
        if(num==null||num.length<=1) return 0;
        if( num.length==2 ) return Math.abs(num[0]-num[1]);
        int Max=0;
        int MaxDif=0;
        int previous = -1;
        TreeSet<Integer> count = new TreeSet<>();
        for(int i : num){
            // if(i>Max) Max=i;
            if(!count.contains(i)) count.add(i);
        }
        // int[] count=new int[Max+1]; 
        // for(int i : num){
        //     count[i]++;
        // }
        previous =count.pollFirst();
        while(!count.isEmpty()){
            int p =count.pollFirst();
            MaxDif=Math.max(MaxDif, p-previous);
            previous=p;
        }
        // for(int i=1;i<count.length;i++){
        //     if(count[i]==0) continue;
        //     else {
        //         if(previous==-1) previous=i;
        //         else {
        //             MaxDif=Math.max( MaxDif,i-previous);
        //             previous=i;
        //         }
        //     }
        // }
        return MaxDif;
    }
    public static  boolean isNumber(String s) {
        s=s.trim();
         System.out.println(s);
        if(s.length()==0) return false;
        if(s.lastIndexOf('.')!=s.indexOf('.') ||s.indexOf('.')==s.length()-1
        || s.lastIndexOf('+')!=s.indexOf('+')||s.indexOf('+')>0
        || s.lastIndexOf('-')!=s.indexOf('-')||s.indexOf('-')>0 )
            return false;
         if( s==null||s.length()==0 || s.compareTo(".")==0 || s.compareTo("+")==0 || s.compareTo("-")==0 ) return false;
         String a=".";
     System.out.println(a.matches("[+-]?[0-9 ]*[.]?[0-9 ]*") );
     System.out.println(a.matches("[-+]?\\d*((\\.\\d)|(\\d\\.))?(\\de\\d)?\\d*$") );
//     System.out.println(a.matches("\\d*\\.?\\d+") );
        return (s.matches("[-+]?\\d*\\.?(\\de\\d)?\\d*$" )&& s.matches("\\d+"));
    }
    public static void main(String[] args) {
//        double[] A={  123, -34.7, 3243,6.436 ,323,543745, 232,788, -432.23};
//        double[] B;= new double[3];
//        int[] A={  123, 34, 3243,6,436 ,323,543,745, 232,788, 432, 23};
//        double[] Ad={ 0, 123, 34, 3243,6,436 ,323,543,745, 232,788, 432, 23};
//        int[] B = new int[A.length+1];
        try{
            String a = "123";
            Set<String> dict= new TreeSet<>();
            dict.add("a");
            dict.add("aaa");
            dict.add("aa");
            dict.add("aaaa");
            dict.add("aaaaaa");
            dict.add("aaaaaaa");
            dict.add("aaaaaaaa");
            dict.add("aaaaaaaaa");
            dict.add("baaaaaaaaaa");
             Integer[] choi={0,1,2,3,5,6,7,8,9,4};
             Set<Integer> all=new HashSet<>(Arrays.asList(choi));
//             for(int i:choi) 
//                  all.add(i);
//        HashMap<Integer, HashMap<Integer,Integer>> tried = new HashMap<>();
//             System.out.println(all);
//           Integer[] test={1,2};
//           Integer[] test2={1,2};
//           List<Integer[]> hhhh= new LinkedList<>();
//           hhhh.add(test2); hhhh.add(test);
//           System.out.println(hhhh.get(0)[0]);
             
        List<List<Integer>> res = new LinkedList<>();
            for(List<Integer> i: res)
                i.add(0,2);
           System.out.println(res);
//               HashMap<Integer, Set<Character>> rows = new HashMap<>();
//        HashMap<Integer, Set<Character>> cols = new HashMap<>();
//        List<Integer[]> blank = new ArrayList<Integer[]>(); 
//        Set[][] blockCheck = new HashSet[3][3];
//        
//        for(int i =0;i<9;i++){
//            Set<Character> row = new HashSet<>();
//            Set<Character> col = new HashSet<>();
//            rows.put(i,row);
//            cols.put(i,col);
//        }
        
//            wordBreak aaa = new wordBreak();
//           List<String> res = aaa.wordBreak_3("baaaaaaaaaaaaaaaaaaaaaaa",dict);
//       System.out.println(res);
//           List<String> res2 = aaa.wordBreak_2("baaaaaaaaaaaaaaaaaaaaaaa",dict);
//       System.out.println(res2);
//        ArrayList<Integer> count = new ArrayList<>();
//        count.add(15, 5);
//        count.add(3, 1);
//           System.out.println( maximumGap(A));
//            System.out.println((int)a+" "+(int)b+" "+(int)d);
            
//            mergeKtest();
//            madetree.testMakeTree();
            // Stirng X, Y, find shortest substr in X has all letter in Y
//            String X="123456789012345678901111166666";
//            String Y="29";
//            findShortestSub(X,Y);
            
            
//        A= HeapSort( A, A.length-1 );
//        A= QuickSort( A,0, A.length-1 , false );
//        B=MaxSubarray(A,0,A.length-1);
//        B=countingSort(A, B, A.length-1, ArrayMax(A)+1);
            //min heap
//        BuildMinHeap(Ad, Ad.length-1);
//        System.out.println(Arrays.toString(Ad));
//        HeapSort(Ad,Ad.length-1,false);
//        System.out.println(Arrays.toString(Ad));
             // get pop number
//        int[] Apop={ 1,2,2,2,2,2,2,2,2,3,3,3,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5};
//        int[] Amatch={ 1,2,2,2,3,3,4,4,5,5,6,6};
//        int l= 6;//Apop.length/4;
//        int[] Bpop=new int [Apop.length/l];
//        int[] Bpop =new int [0];
//        int[][] testjava=new int[5][6];
//        Bpop = GetPop( Apop, Bpop, 0, Apop.length-1, l);
//             System.out.println(Amatch.length+";"+l);
//             System.out.println(Arrays.toString(Apop));
//            System.out.println(Arrays.toString(Bpop));
//            testTree();
//           testHash();
//          Graphtest();
     //          seachTest();
//            mergeTest();
//            LinkedList<String> tmp =getStair(5);
//            for(int i=0;i<tmp.size();i++)
//                System.out.println(tmp.get(i));
            
//            findRepeatSubstr("ABCACBABC",3);
//            findRepeatSubstr("ABCABCA",2);
//            System.out.println(findDistance.findShortestDistance());
//            System.out.println(findDistance.findallShortestDistance());
//            System.out.println(distance("hello how are you are you hello","hello","you"));
//            waveTest();
//            printExpress();
//            testJump();
        }catch (Exception e){
            System.out.println("Hello Word! ");
        } finally{
            System.out.println("\n Fianl; No matter "+ Integer.MAX_VALUE+"who you are, I am out."+(int)Double.POSITIVE_INFINITY) ;
        }
    }
    public static void testJump(){
    int[] A = { 3,2,5,6,3,5,2};
      System.out.println(jumpMax(A,0,A.length-1));
    }
public static int polishCal(String input){
    ArrayList<String> cal= new ArrayList<>();
    ArrayList<Integer> index= new ArrayList<>();
    
    String[] each = input.split(" ");
    int i =0;
    for(;i<each.length;i++){ 
//        System.out.print(i+":" +each[i]+" from C in ");
	if(each[i].compareTo("")==0) continue;
        if (each[i].compareTo("(")==0) {
                cal.add(each[i]);
               index.add(cal.size()-1);
//            System.out.println( );
       }else if(each[i].compareTo(")")==0 || ( i == each.length-1 && each[i].compareTo(")")!=0) ) {
            int pair;
            if( i == each.length-1 && each[i].compareTo(")")!=0) {
                cal.add(each[i]);
                pair=-1;
            }
            else {
                pair=index.remove(index.size()-1);
            } // get the last (        
            String operater = cal.get(pair+1);    
            int res;
	if(operater.compareTo("+")==0) {
            res=0;
            for(int h=cal.size()-1;h>pair+1;h--) {
//            System.out.print( res +"~~ "+ cal.get(h));
                 res += Integer.parseInt(cal.remove(h));
            }
            cal.remove(pair+1);
            if( i != each.length-1 || each[i].compareTo(")")==0){
                 cal.remove(pair);
            }
            cal.add(res+"");
//            System.out.println( res);
            }else if(operater.compareTo("*")==0) {
                res=1;
                for(int h=cal.size()-1;h>pair+1;h--)
                    res *= Integer.parseInt(cal.remove(h));
                cal.remove(pair+1);
                cal.remove(pair);
                cal.add(res+"");
//            System.out.println( res);
            }else if(operater.compareTo("-")==0) {
                res=1;
                for(int h=cal.size()-1;h>pair+1;h--)
                    res *= Integer.parseInt(cal.get(h));
                cal.remove(pair+1);
                cal.remove(pair);
                cal.add(res+"");
            }else {
            }
        } else {// this is an operand
            cal.add(each[i]);
        }
    } 
    
    if(index.size()!=0){
        System.out.println("() always comes with pairs ...");
        System.exit(1);
    }
    if(cal.size()!=1){
        System.out.println("Wrong ...");
        System.exit(1);
    }
return Integer.parseInt(cal.get(0));

}

        public static int jumpMax(int[] A, int n, int l ){
        int currentMax=n+A[n],nextMax=0,i=n,steps=1;
        if ( currentMax>=l) return 1;
        while(currentMax<l){
            steps++;
            for(;i<=currentMax;i++){
                nextMax=Math.max(currentMax, A[i]+i );
            }
                if(nextMax>=l) return steps;
                else if (nextMax==currentMax){
                    System.out.println("you are trapped.");
                    return Integer.MAX_VALUE;
                } else {
                    currentMax=nextMax;
                }
        }
        return steps;
    }
        
    public static void printExpress(){
        String A = "( + 2 3 ( * 5 6 ) )";
        String B =  "+ 2 3  ";
        String C= "+ 2 3 ( * ( + ( * 5 6 ) ( * 1 2 ) ) 2 ) 3";
        String D= " + 7 ( * 8 12 ) ( * 2 ( + 9 4 ) 7 ) 3 ";
     System.out.println( polishCal(B));
     System.out.println( polishCal(D));
     System.out.println( polishCal(C));
//     System.out.println( singleCalculation(A));     
    }
    
    public static boolean isSingleCal(String input){
    boolean ret_value= false;
    if ( input.startsWith("(") && input.endsWith(")") )
            input = input.substring(1,input.length()-1);
    if ( !input.contains("(") && !input.contains(")")  ){
            ret_value= true;
    }
    return ret_value;
    }
 public static int singleCalculation(String input){
    int result= -1;
    if ( input.startsWith("(") && input.endsWith(")") )
            input = input.substring(1,input.length()-1);
    if ( !input.contains("(") && !input.contains(")")  ){
            String[] ops=input.trim().split(" ");
    if(ops.length<2){ System.out.println("wrong input"); System.exit(1); }
    else if(ops.length==2){ return  Integer.parseInt(ops[1]);}

    result =  Integer.parseInt(ops[1]);
    if(ops[0].compareTo("+")==0) {
                    for(int i=2;i<ops.length;i++) {
                            result +=  Integer.parseInt(ops[i]) ;
    }} else if(ops[0].compareTo("*")==0) {
                    for(int i=2;i<ops.length;i++) {
                            result *=  Integer.parseInt(ops[i]) ;
    }} 
                    return result;
    }else{
    System.out.println(" not a single calculation");
    System.exit(1);
    }
    return result;
 }
    
    
    public static void waveTest(){
     int[] A={  123, 34, 3243,6,436,0,0,0,21,90,1 ,323,543,745, 232,788, 432, 23};
     int[] B={  -1};
     int[] C={  };
     int[] D={  2,1};
     int[] e=null;
     waveArray(A);
     waveArray(B);
     waveArray(C);
     waveArray(D);
     waveArray(e);
    }
    
    public static void waveArray(int[] A){
        if(A==null){
            System.out.println("Wroong");
            System.exit(1);
        }
        int n = A.length;
        if( n <2){System.out.println(Arrays.toString(A)); return ;}
        int i =1;
        for(;i<n-1;i=i+2){
            int max=Math.max(Math.max(A[i-1], A[i]),A[i+1]);
            if( max == A[i-1]){
                A= swap (A, i-1, i);
            }else if( max == A[i+1]){
                A= swap (A, i+1, i);
            }
        }
            if(i==n-1){ // the last one
                if(A[i]<A[i-1]) 
                    A= swap (A, i-1, i);
            }
        System.out.println(Arrays.toString(A));
    }

    public static int[] swap (int[] A, int i, int j){
	int tmp=A[i];
        A[i]=A[j];
        A[j]=tmp;
        return A;
    }

    public static int distance(String str, String a, String b){
        if( str==null || a==null || b==null || str.isEmpty() || a.isEmpty()|| b.isEmpty()){
            System.out.println("null input");
            System.exit(1);
        }
        int fa=str.indexOf(a);
        int fb=str.indexOf(b);
        HashMap<String,LinkedList<Integer>> indexMap = new HashMap<>();
        if(fa==-1 || fb==-1) return -1;
        int index=0;
        int head=0;
        String str2=str.toLowerCase()+" ";
        for(int i=0;i<str2.length();i++){
            if(str2.charAt(i)==' '){
                String currentWord = str2.substring(head, i);
                if(indexMap.containsKey(currentWord)){
                    indexMap.get(currentWord).add(index);
                }else{
                    LinkedList<Integer> newword = new LinkedList<>();
                    newword.add(index);
                    indexMap.put(currentWord, newword);
                }
                head=i+1;
                index++;
            }
        }
        System.out.println(indexMap.toString());
        return index;
    }
    public static int minOf4(int a ,int b,int c,int d){
        return Math.min(Math.min(a,b),Math.min(c, d));
    }
    
    public static int distance_good(String str, String a, String b){
        if( str==null || a==null || b==null || str.isEmpty() || a.isEmpty()|| b.isEmpty()){
            System.out.println("null input");
            System.exit(1);
        }
        if(str.indexOf(a)==-1 || str.indexOf(b)==-1) return -1;
        String[] words=str.toLowerCase().split("[ \t]+");
        int fa=-1;
        int fb=-1;
        int min_dis=Integer.MAX_VALUE;
        for(int i=0; i<words.length;i++){
            if(words[i].compareTo(a)==0){
                fa=i;
            }
            if(words[i].compareTo(b)==0){
                fb=i;
            }
            if ( fa!=-1 && fb!=-1 && fb-fa>0 && min_dis>fb-fa){
                min_dis = fb-fa;
            }
        }
        return min_dis;
    }
    public static void mergeTest(){
        int[][] A = {{20,40,80},{5,60,90},{45,50,55}};
        int[] B = merge_1(A,3,3);
        int[] C = merge_2(A,3,3);
        merge_3(A,3,3);
        System.out.println(Arrays.toString(B));
        System.out.println(Arrays.toString(C));
    }
    public static int[] merge_1(int[][] A,int m ,int n){
        int[] ret_value = new int[m*n];
        for(int i=0;i<m;i++ ){            
            for(int j=0;j<n;j++ ){
                ret_value[i*n+j] =A[i][j]; 
            }
        }
        QuickSort(ret_value,0,ret_value.length-1, true);
        return ret_value;
    }
    public static int[] merge_2(int[][] A,int m ,int n){
        int[] ret_value = new int[m*n];
        int[] head_index=new int[m];
        int[] head_value=new int[m];
        for(int i=0;i<m;i++ ){            
            head_value[i]=A[i][head_index[i]];
        }        
//      System.out.println(Arrays.toString(head_value));
//      System.out.println(Arrays.toString(head_index));
        for(int i=0;i<m*n;i++ ){        
            int smallest = getMin(head_value, m);
            ret_value[i] = head_value[smallest];
            head_index[smallest]++;
            if( head_index[smallest]>=n ){
                 head_value[smallest]=Integer.MAX_VALUE;
            }else
            head_value[smallest]=A[smallest][head_index[smallest]];
        }
        return ret_value;
    }
    public static Object[] findRepeatSubstr(String A, int len){
        if(len<1){
            System.out.print("length >1.");
            System.exit(1);
        }
        HashMap<String,Integer> allSub = new HashMap<>();
        TreeSet<String> repeats= new TreeSet<>(); 
        for(int i=0;i<=A.length()-len;i++){
            String substr = A.substring(i,i+len);
            if(allSub.containsKey(substr) ){
                System.out.println(substr + " "+allSub.get(substr)) ;
                if( allSub.get(substr) ==1 )
                    repeats.add(substr);
                allSub.put(substr, allSub.get(substr)  +1);
            }else{
                 allSub.put(substr,1);
            }
        }
        System.out.println(repeats.toString());
        return repeats.toArray();
    }
    public static int getMin(int[] A, int n){
        int ret_value=0;
        for(int i =1;i<n;i++){
            if(A[i]<A[ret_value])
                ret_value = i;
        }
        return ret_value;
    }
    public static void merge_3(int[][] A,int m ,int n){
        int[] head_index=new int[m];
        int min_index = 0;
        int min_value = Integer.MAX_VALUE;
        int id=m;
        while(id>0){
            min_value = Integer.MAX_VALUE;
            for(int i=0;i<m;i++ ){   
                if(head_index[i]<n) {
                    if( A[i][head_index[i]]<min_value ){
                        min_value =A[i][head_index[i]];
                        min_index = i;
                    }
                }
                if (i==m-1) {
                    System.out.print(" "+min_value);
                    head_index[min_index]++;
                    if(head_index[min_index]==n) { id--;}
                }
            } 
        }
    }
    public static LinkedList<String>  getStair(int n){
        if(n<0){
            System.out.println("n should be greater than 0");
            System.exit(1);//
        }
        LinkedList<String> ret_str= new LinkedList<>();
        if(n==0){
            ret_str.add("");
            return ret_str;
        }else if (n==1){
            ret_str.add("1");
            return ret_str;
        }
        LinkedList<String> str1=getStair(n-1);
        LinkedList<String> str2=getStair(n-2);
        
        if(!str1.isEmpty()){
            for(int i=0;i<str1.size();i++){
                String tmp = str1.get(i)+"1";
                ret_str.add(tmp);
            }
        }
        
        if(!str2.isEmpty()){
            for(int i=0;i<str2.size();i++){
                String tmp = str2.get(i)+"2";
                ret_str.add(tmp);
            }
        }
        
        return ret_str;
    }
    public static void Graphtest(){
//        for  (int i =0;i<5;i++){
//            for(int j=0;j <M[i].length;j++){
//            System.out.println (i+"; "+M[i][j][0]+";"+ M[i][j][1] +" ");
//           }
//        }
        myGraph a = new myGraph();
        a.printAdj();
//        System.out.println (a.traverseBFS_S(3));
        int[] tB = a.traverseBFS(3);
        int[] tD = a.traverseDFS(3);
        String tBs = a.traverseBFS_S(3);
        String tDs = a.traverseDFS_S(3);
        for  (int i =0;i<tB.length;i++) {  System.out.print (" "+tB[i]); } System.out.println("") ;
        for  (int i =0;i<tD.length;i++) {  System.out.print (" "+tD[i]);} System.out.println("") ;
        System.out.println(tBs) ;
//        System.out.println(tDs) ;
    }
    public static void testTree(){
        PhoneDirectory A=new PhoneDirectory();
         System.out.println(A.find("tim"));
         System.out.println(A.getName("111"));
         A.insert("Tim", "86432");
         A.insert("Tim", "186432");
         System.out.print(A.print());
    }
    public static void seachTest(){
        int[] A={  2,4,7,9,11,22,90};
        int[] B={ 22,90,91,92,93,  95, 100, 0,2,4, 7,9,11};
        int[] C={ 22,90,91,92,93,  95, 100, 0,2,4, 7,9,11};
        System.out.println(Arrays.equals(B, C));
        C=QuickSort(C,0,C.length-1,false);
//          PrintArray(C,1);
          System.out.println(binarySearch(A,0,A.length-1, 55,true ));
          System.out.println(binarySearch(A,0,A.length-1, 11,true ));
          System.out.println(binarySearch(C,0,C.length-1, 55,false ));
          System.out.println(binarySearch(C,0,C.length-1, 11,false ));
        shiftArray(C,true,4);
        PrintArray(C,0);
        for(int i: C){ System.out.print(i+" ");
                }
         System.out.println(Arrays.toString(C));
        
         System.out.println("3/2; "+ (int)(3/2));
//        System.out.println(""+checkShift(B,0, B.length-1,true));
         System.out.println(" shift ASC; "+  shiftSearch(B, true, 11));
         System.out.println(" shift Desc; "+  shiftSearch(C, false, 11));
//        System.out.println(""+checkShift(C,0, C.length-1,false));
//        System.out.println(""+binarySearch(A,0, A.length-1, 11));
    }
    public static int shiftSearch(int[] A, boolean ASCorNot, int key){
        int found = -1;
        int shiftIndex = 0;
        int l = A.length;
        int mode = ASCorNot? 1 : -1;
        
        while( (A[l-1]-A[0])*mode < 0 ){
            shiftIndex++;
            shiftArray(A, ASCorNot,1);
        }
        found = binarySearch(A, 0, l-1, key,ASCorNot);
        found = ASCorNot? (found+l-shiftIndex)%l : (found+l+shiftIndex)%l;             
        return found;
    }
    public static int[] shiftArray(int[] A, boolean toRight, int n){
        if (n<1){
             System.out.println("No need to shift");
        }else if (n==1){
            int l=A.length;
            if(toRight){
                int tmp=A[l-1];
                for(int i=l-1;i>0;i--){
                    A[i]=A[i-1];
                }
                A[0]=tmp;
            }else{
                int tmp=A[0];
                for(int i=0;i<l-1;i++){
                    A[i]=A[i+1];
                }
                A[l-1]=tmp;                
            }
        }else {
            for(int i=0;i<n;i++){
                A=shiftArray(A,toRight,1);
            }
        }
        return A;
    }
    public static int binarySearch(int[] A, int head, int tail, int key){
        if( A==null || head>tail || head<0 || tail>=A.length){
            System.out.println(key + " is not found.");
            return -1;
        }
        int mid=(head+tail)/2;
        if(key==A[mid]){
            System.out.println(key + " is at index "+ mid);
            return mid;
        }else if(key>A[mid]){
            return binarySearch(A, mid+1,tail,key);
        }else{
            return binarySearch(A, head, mid-1, key);
        }
    }
    public static int binarySearch(int[] A, int head, int tail, int key, boolean ASCorNot){
//        if (ASCorNot) return binarSearch(A, head, tail, key );
        
        if( A==null || head>tail || head<0 || tail>=A.length){ 
            System.out.println(key + " is not found.");
            return -1;
        }
        int mid=(head+tail)/2;
        if(key==A[mid]){
            System.out.println(key + " is at index "+ mid);
            return mid;
        }else if(key>A[mid]){
             if (ASCorNot)     return binarySearch(A, mid+1,tail,key,ASCorNot);
             else   { 
                 return binarySearch(A, head, mid-1, key,ASCorNot); 
             }
        }else{
            if (ASCorNot)   return binarySearch(A, head, mid-1, key,ASCorNot);
            else   {  
                return binarySearch(A, mid+1,tail,key,ASCorNot);
            }
        }
    }
     public static int checkShift(int[] A,int head, int tail, boolean ASCorNot){      
        if( A==null || head>tail || head<0 || tail>=A.length){
            System.out.println( "Wrong input.");
            return (int)Double.POSITIVE_INFINITY;
        }
        
         int mode = ASCorNot?1:-1; 
         int mark = (int)Double.POSITIVE_INFINITY;;   
         if( (A[tail]-A[head])*mode > 0 ){
             return head;
         }else if ((A[tail]-A[head])*mode < 0 ){
             int mid = (head + tail)/2;
             if( (A[mid]-A[mid-1])*mode < 0 && (A[mid+1]-A[mid])*mode >0){
                 return mid;
             }else  if ((A[mid]-A[head])*mode < 0 ){
                mark = checkShift(A, head, mid, ASCorNot );
             }else mark = checkShift(A, mid+1, tail, ASCorNot ) ;
         }
         return mark;
     }
     
     public static int uniquePaths(int m, int n) {
        if(m==0 && n==0)
        { return 0;} else if (m<0 || n<0 ){
            System.out.println("Wrong input");
            return 0;
        } else {
        return uniquePaths(m-1,n)+uniquePaths(m,n-1)+2; }
    }
    public static void testHash(){
        HashMap<Integer,String> hm = new  HashMap<>();
        hm.put(1,"test");
        hm.put(12,"test2");hm.put(14,"test3");hm.put(234321,"test5");hm.put(234321,"test6");hm.put(4321,"test5");
        hm.put(000,"test15");
        hm.put(-1,"null");
        
//        String a12=";";
//        a12 = a12+"hha";
//         System.out.println(a12);
        
//        AbstractMap<Integer,String> maptest=new AbstractMap<>();
         System.out.print(hm.entrySet().toString());
        for(int key : hm.keySet()){
//            if ( key!=null )
             System.out.println(key);
        }
        
        
    }
    
    public static void testList(){
            testList.add(123);
            testList.add(123);
            testList.add(1,123);
            testList.add(2, 123);
            testList.add(3,"hauhifu");
            testList.add(3,123);
            testList.add(123);
            testList.add(123);
            testList.add(123);
            testList.add(3,true);
            testList.add(123);
            testList.add(0,"930u4093ti");
            testList.add(123);
            testList.add(123);
            testList.add('C');
            testList.add(123);
            testList.add(123);
            
            for (Iterator it = testList.iterator(); it.hasNext();) {
                System.out.println(it.next());
            }
    }
    public class TreeNode<T> {
        T data;
        TreeNode<T> parent;
        TreeNode<T> left;
        TreeNode<T> right;
        public TreeNode(T rootData) {
            this.data = rootData;
            this.parent=null;
        }
    }

        public static <T> String printLevel(TreeNode<T> t, int level) {
            String ret_value = "";
            if (t != null && level > 0 ) {
                if (level == 1) {
                    ret_value =  t.data + " ";
                } else {
                    String leftStr = printLevel(t.left, level - 1);
                    String rightStr = printLevel(t.right, level - 1);
                    ret_value =  leftStr + rightStr;
                }                
            }
            return ret_value;
        }
        public static <T> void printLevelOrder(TreeNode<T> root, int depth) {
            for (int i = 1; i <= depth; i++) {
                System.out.print("Level " + (i-1) + ": ");
                String levelNodes = printLevel(root, i);
                System.out.print(levelNodes + "\n");
            }
        }

    public static int findShortestSub(String X, String Y){
            int m=X.length();
            int n=Y.length();
            if(m<n){
                return 0;
            }else if (m==n) {
                return (X.equals(Y))? m:0;
            } 
            char[] Source=X.toCharArray();
            char[] Target=Y.toCharArray();
            m=Source.length; n=Target.length;
            int[] count=new int[n];
            int[] mapYtoX=new int[m];
            for (int i=0;i<m;i++){
                mapYtoX[i]=-1;
            }
            int[][] position=new int[n][m];
           int  start=m-1, end=0, len=m;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(Source[i]==Target[j]){
                        count[j] +=1;
                        position[j][count[j]-1]=i;
                        mapYtoX[i]=j;
                        start=Math.min(start, i);
                        end=Math.max(end, i);
                        len=end-start+1;
                    }
                }
            }
            System.out.println(Arrays.toString(mapYtoX));
            System.out.println(Arrays.toString(count));
//            System.out.println(Arrays.toString(position[0]));
//            System.out.println(Arrays.toString(position[1]));
//            System.out.println(Arrays.toString(position[2]));
//            System.out.println(Arrays.toString(position[3]));
//            System.out.println(Arrays.toString(position[4]));
             int  startleft=start;
            for(int i=start+1;i<end;i++){
                if( mapYtoX[i]>=0 ){
                    for(int t =0;t< count[mapYtoX[startleft]] ;t++){
                        if( position[mapYtoX[startleft]][t] > i) {
                            startleft=i;
                            break;
                        }  
                    }
                    if (startleft!=i) {break;}
                }
            }
             int  endright =end;
            for(int i=end-1;i>start;i--){
                if( mapYtoX[i]>0 ){
                    for(int t =0;t< count[mapYtoX[endright]] ;t++){
                        if( position[mapYtoX[endright]][t] < i) {
                            endright=i;
                            break;
                        }  
                    }
                    if (endright!=i) {break;}
                }
            }
            
           int len1=end-start+1;
           int lenl=start;int lenr=end;           
           int len2=end-startleft+1;
           len=Math.min(len1,len2);
           if (len==len2) { lenl=startleft; }
           int len3=endright-start +1;
           len=Math.min(len,len3);
           if (len==len3) {lenl=start;  lenr=endright;}
           int r=0,l=0;
//           System.out.println(start + " "+ startleft+" "+end+ " "+len);
           for(int i=start;i<startleft;i++){
               if( mapYtoX[i]>0 ){
                    for(int j=end;j>endright;j--){
                        if( mapYtoX[i]== mapYtoX[j]) {
                            r=j;l=i;
                        }
                    }
                    if (l==0 && r==0) break;
               }
           }
           int len4 =(l==0 && r==0)? m :r-l+1;
           len=Math.min(len,len4);
           if (len==len4) {lenl=l;  lenr=r;}
           
            r=0;l=0;
           for(int i=end;i>endright;i--){
               if( mapYtoX[i]>0 ){
                    for(int j=start;j<startleft;j++){
                        if( mapYtoX[i]== mapYtoX[j]) {
                            r=i;l=j;
                        }
                    }
                    if (l==0 && r==0) break;
               }
           }
           int len5=(l==0 && r==0)? m :r-l+1;
           len=Math.min(len,len5);
           if (len==len5) {lenl=l;  lenr=r;}
           System.out.print(lenl +"0 --\n\\ " + lenr +" " +len);
            return len;
    }
    public static int[][] flipOverArray(int[][] A, int n){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int tmp=A[i][j];
                A[i][j]=A[j][i];
                A[j][i]=tmp;
            }
        }
        return A;
    }
    public static double[] BuildMinHeap(double[] A, int n){
        for(int i=n/2+1;i>=1;i--){
            A=MinHeapify(A,i,n);
        }
        return A;
    }

	public static void testMed(){
		int ramM= (int) ( Math.random()*10+1 );
		int ramN= (int) ( Math.random()*10+1 );
		int[] A= new int[ramM];
		int[] B= new int[ramN]; //{-789,-99,-99,-99,-3,  -1,0,2,3,3,  3,4,5,51,88};
		LinkedList<Integer> all=new LinkedList<>();
		for(int i=0;i<ramM;i++){
			A[i] =  (int) ( Math.random()*100-50 );
			all.add(A[i]);
		}
		for(int i=0;i<ramN;i++){
			B[i] =  (int) ( Math.random()*100-50 );
			all.add(B[i]);
		}
		Arrays.sort(A);
		Arrays.sort(B);
		
		Collections.sort(all);
		int l=all.size();
//		System.out.println( Arrays.toString(all.toArray())) ;
//		System.out.println( l%2==1? all.get( l/2) : ( (double)(all.get( l/2)+all.get(l/2-1))/2)  ) ;
//			System.out.println(findMedianSortedArrays(A,B));
//	System.out.println(getM(A,B));
		int[] c={1,2,3,4};
		int[] d={3,4,7} ;
		System.out.println( Arrays.toString(A)) ;
		System.out.println( Arrays.toString(B)) ;
//		System.out.println(getM_2( d , c)) ;
	}
	
	public static double getM(int[] A, int[] B){
		if(A==null||B==null) return 0;//
		int m = A.length;
		int n = B.length; 
		if(m>n) return getM(B,A); // let's say B longer than A
		
		int K=(m+n)/2;
		if(m==0){
			if(n==0) 
				return 0;
			else 
				return  (n&1)==1 ? (double)B[n/2] : ((double)B[n/2]+B[n/2-1])/2 ; 
		}else if(m==1){
			if (n==1) 
				return ((double)A[0]+B[0])/2 ;
		}
		
		if( (m+n)%2==1) // odd
			return getKth(A, B, 0,m-1, 0,n-1, K);
		else  // even
			return (getKth(A, B, 0,m-1, 0,n-1, K)+getKth(A, B, 0,m-1, 0,n-1, K-1))/2;
				
	}

	public static double getKth(int[] A, int[] B, int la,int ra,int lb,int rb, int K ){
		if(la==ra) {if(lb+K<=rb) return A[la]>=B[lb+K] ? B[lb+K]: (A[la]>B[lb+K-1]? A[la]: B[lb+K-1]); else return Integer.MAX_VALUE;}
		if(lb==rb) {if(la+K<=ra) return B[lb]>=A[la+K] ? A[la+K]: (B[lb]>A[la+K-1]? B[lb]: A[la+K-1]); else return Integer.MAX_VALUE;}
		if(K==0)  return Math.min(A[la], B[lb]);
		int midxA= (ra+la)/2;
		int midxB= lb+((K-1) - (midxA- la));
//		System.out.println(la+"-"+ra+" "+lb +"-"+rb+" K:"+K+ " midxA"+midxA+ " midxB"+midxB);
		if (A[midxA]==B[midxB]) return A[midxA];
		else if (A[midxA]<B[midxB]){ // we know that m<n : ra-la < rb-lb, only remove m/2 every time
			return getKth(A, B, midxA+1, ra,  lb, midxB , K-(midxA-la)-1);
		}else{
			return getKth(A, B, la, midxA,  midxB+1, rb, K-(midxB-lb)-1);
		}
	}
	
    public static double[] MinHeapify(double[] A, int i, int n){
          int leftChild=2*i;
          int rightChild=2*i+1;
          int min=i;
          if( leftChild<=n && A[leftChild]<A[min]){
              min = leftChild ;
          }
          if( rightChild<=n && A[rightChild]<A[min]){
              min= rightChild ;
          }
          if(min!=i){
             HeapSwap(A,i,min);
             A= MinHeapify(A,min,n);
          }
          return A;
    }
    public static int[] GetPop(int[] A, int[] B, int h, int r, int l){
//            System.out.println("get pop --- ; r"+r + " h;"+ h );
        if(r-h+1==l && A[r]==A[h]){     
            testList.add((Integer)A[h]);         
            if(B.length==0 ) {
                B = Arrays.copyOf(B, B.length+1 );
                B[0]=A[h];
            }else if( B[B.length-1]!=A[h] ){
                B = Arrays.copyOf(B,B.length+1 );
                B[B.length-1]=A[h];              
            }
        }else if (r-h+1 >l && A[r]-A[h]<=r-h-(l-1) ){
            int mid = (r+h)/2;
//          System.out.println(r+"-"+ h +" - "+ mid );
            B = GetPop(A, B,h,mid,l);
            B = GetPop(A,B,mid+1,r,l);
            B = GetPopCross(A,B,h,r,mid,l);
        }
        return B;
    }
    public static int[] GetPopCross(int[] A, int[] B, int h, int r, int mid, int l){
//        if (mid-h=1>=l)
        int left = mid-(l-2)<h ? h : mid-(l-2);
        int right= mid+(l-1)>r ? r : mid+(l-1);
        if (A[mid]==A[mid+1] && A[right]-A[left]<=right-left-(l-1) ){
            int i= FindMatch(A, h , mid, true );
            int j=FindMatch(A, mid+1,r, false );
//            System.out.println(left+";"+A[left]  +" - "+ mid +";"+A[mid] + " - "+ right+";"+A[right] +"    ~~ "+ i );
            if (j-i+1 >= l) {
                testList.add((Integer)A[mid]);  
                if(B.length==0 ) {
                    B = Arrays.copyOf(B, B.length+1 );
                    B[0]=A[mid];
                }else if( B[B.length-1]!=A[mid] ){   
                    B = Arrays.copyOf(B, B.length+1 );
                    B[B.length-1]=A[mid];          
                }
            }            
        }
        return B;
    }
    public static int FindMatch(int[] A, int h , int r, boolean LeftPartofPivot){
        int pivot= LeftPartofPivot? r : h;
        int ret_value = pivot;
        if (r-h<=1 && A[r]==A[h]){
            ret_value = LeftPartofPivot? h : r;
        }else if( r-h>1) {
            int mid = (h+r)/2;
            if (A[mid]==A[pivot]){ // search farther harf part
    //            ret_value=mid;
                if(LeftPartofPivot){
                    ret_value=FindMatch(A,h,mid,LeftPartofPivot);
                }else{
                    ret_value=FindMatch(A,mid,r,LeftPartofPivot);
                }
            }else{ // search nearer halfpart
                if(LeftPartofPivot){                
                    ret_value=FindMatch(A,mid+1,r,LeftPartofPivot);
                }else{
                    ret_value=FindMatch(A,h,mid,LeftPartofPivot);                
                }
            }
        }
        return ret_value;
    }
//    public static double CHAINED-HASH-INSERT()
    public static double RandomizedSelect(double[] A, int h, int r, int i){
        if (h==r){
            return A[h];
        } else{
            int q=RandomizedPartition(A,h,r,true);
            int k = q-h+1;
            if(k==i){
                return A[q];
            }else{
                if(i<k){
                    return RandomizedSelect(A,h,q-1,i);
                }else {
                    return RandomizedSelect(A,q+1,r,i-k);
                }
            }
        }
    }
    public static int[] countingSort(int[] A, int[] B, int n, int k ){
        int maxofA =ArrayMax(A);
        if ( maxofA>k ){
            System.out.println("Please increase K;"+k+" to be greeater than "+ maxofA );
        }else{
            int[] C=new int[k];
            for (int i=0;i<k;i++){
                C[i]=0;
            }
            for (int i=0;i<=n;i++){
                C[A[i]]=C[A[i]]+1;
//                System.out.println(" i="+i +" and C[A[i]]="+ C[A[i]] );
            }
            for (int i=1;i<k;i++){
                C[i]=C[i]+C[i-1];
                if (C[i]!=C[i-1]) {
                    System.out.println(" i="+i+" and C[i]="+ C[i] );
                }
            }
            for(int i=n;i>=0;i--){
                B[C[A[i]]]=A[i];
             System.out.println(Arrays.toString(B));
                C[A[i]]=C[A[i]]-1;
            }
            
        }
        return B;
    } 
    public static int partition(double[] A, int h, int r, boolean ASCornot){
        int mode = ASCornot? 1:-1;
        double x=A[r];
        int i = h-1;
        for (int j=h;j<r; j++){
            if( mode*(A[j]-x)<=0){
                i=i+1;
                HeapSwap(A,i,j);
            }
        }
        HeapSwap(A,i+1,r);
        return i+1;
    }    
    public static int partition(int[] A, int h, int r, boolean ASCornot){
        int mode = ASCornot? 1:-1;
        int x=A[r];
        int i = h-1;
        for (int j=h;j<r; j++){
            if( mode*(A[j]-x)<=0){
                i=i+1;
                HeapSwap(A,i,j);
            }
        }
        HeapSwap(A,i+1,r);
        return i+1;
    }
    public static int RandomizedPartition(double[] A, int h, int r, boolean ASCornot) {
        Random rnd = new Random();
        int i =rnd.nextInt(r-h)+h;
        HeapSwap(A, i,r);
        return partition(A,h,r,ASCornot);         
    }
    public static double[] RandomizedQuicksort(double[] A, int h, int r, boolean ASCornot) {
        if (h<r){
            int q = RandomizedPartition(A, h, r, ASCornot);
            RandomizedQuicksort(A,h,q-1, ASCornot);
            RandomizedQuicksort(A,q+1,r, ASCornot);
        }      
        return A;
    }
    public static double[] QuickSort( double[] A, int h, int r, boolean ASCornot){
        if (h>=r){
           // System.out.println("Input error; the head "+ h +" should be smaller than the tail "+ r);
        }else{            
//            System.out.println("Input "+ h +" ; "+ r);
            int q=partition(A, h,r, ASCornot);
            QuickSort(A,h,q-1,ASCornot);
            QuickSort(A,q+1,r,ASCornot);
        }
        return A;
    }
     public static int[] QuickSort( int[] A, int h, int r, boolean ASCornot){
        if (h>=r){
           // System.out.println("Input error; the head "+ h +" should be smaller than the tail "+ r);
        }else{            
            int q=partition(A, h,r, ASCornot);
            QuickSort(A,h,q-1,ASCornot);
            QuickSort(A,q+1,r,ASCornot);
        }
        return A;
    }
    public static double[] MaxHeapInsert( double[] A, int n,double Key){
        double[] B= new double[n+1];
        System.arraycopy(A, 0, B, 0, n);
        B[n]=Double.NEGATIVE_INFINITY;
        HeapIncKey(B,n,Key);
        return B;
    }
    public static boolean HeapIncKey( double[] A, int i,double K){
        if ( K<A[i]){            
            System.out.println("Key should be greater than the original.");
            return false;
        }
        A[i]=K;
        while ( i>1 && A[i/2]<K ) {
            HeapSwap(A,i/2,i);
            i=i/2;
        }
        return true;
    }
    public static double ExtractMaxPQueue(double[] A, int n) {
        if (n<1) return Double.NEGATIVE_INFINITY;
        
        double maxPQueue =A[1];
        A[1] = A[n];
        A[n]=Double.NEGATIVE_INFINITY;        
        MaxHeapify(A, 1, n-1);
        return maxPQueue;
    }
    public static double MaxPQueue(double[] A, int n) {
        if (n>=1) {
            return A[1];
        }else 
            return Double.NEGATIVE_INFINITY;
    }
    public static double[] HeapSort(double[] A, int n, boolean ASCornot) {
        if (ASCornot){
            BuildMaxHeap(A,n);
        }else{
            BuildMinHeap(A,n);
        }
        
        for (int i=n;i>=2;i--){
            HeapSwap(A,1,i);
            if (ASCornot){
                MaxHeapify(A,1,i-1);
            }else{ 
                MinHeapify(A,1,i-1);
            }
        }
        return A;
    }
    public static double[] BuildMaxHeap(double[] A, int n) {
       for (int i=n/2+1;i>=1;i--) {
           A=MaxHeapify(A,i,n);
       }
           return A;
    }
    public static double[] MaxHeapify(double[] A, int i, int n){
        int left  = 2*i;
        int right = left+1;
        int largest;
        if  ( left<=n  &&  A[left]> A[i] ){
            largest = left;
        } else {
            largest = i;
        }
        
        if (   right<=n  &&  A[right]> A[largest]  ){
            largest =right;
        }
        if (largest != i){
            A=HeapSwap(A,i,largest);
            MaxHeapify(A,largest,n);
        }
        return A;
    }
    
//    public static double[] {}
    
    public static int[] HeapSwap(int[] A, int i, int j){
        int temp = A[j];
        A[j]=A[i];
        A[i]=temp;
        return A;
    }
    
    public static double[] HeapSwap(double[] A, int i, int j){
        double temp = A[j];
        A[j]=A[i];
        A[i]=temp;
        return A;
    }
    
    public static double[] MaxSubarray(double[] A, int h, int r){
        double[] ret_array = {0,0,0};
        if (h>r){
            return ret_array;
        }else if (h==r){
            ret_array[2] = A[0]; 
        }else{
            int mid = (int)Math.floor((h+r)/2);
            double[] L;//= new double[3];
            double[] R;//= new double[3];
            double[] C;//= new double[3];
            L=MaxSubarray(A,h,mid);
            R=MaxSubarray(A,mid+1,r);
            C=MaxSubarrayCross(A,h,mid,r);
            if (L[2]>=R[2] && L[2]>=C[2] ){
                ret_array =L;
//        System.out.println( "L;"+L[0]+"; "+ L[1]+"; "+ L[2]);
            }else if (R[2]>=L[2] && R[2]>=C[2]){
                ret_array = R;
//        System.out.println( "R;"+R[0]+"; "+ R[1]+"; "+ R[2]);
            }else {
                ret_array = C;
//        System.out.println( "C;"+C[0]+"; "+ C[1]+"; "+ C[2]);
            }
        }
        return ret_array;
    }
    public static double[] MaxSubarrayCross(double[] A, int h, int mid, int r){        
        double[] ret_array = {0,0,0};
        double sumL=Double.NEGATIVE_INFINITY;
        double sumR=Double.NEGATIVE_INFINITY;
        double sum=0;
        for(int i=mid;i>=h;i--){
            sum=sum+A[i];
            if (sum>sumL){
                sumL =sum;
                ret_array[0]=i; 
            }
        }
        sum=0;
        for (int i=mid+1;i<=r;i++){
                sum=sum+A[i];
            if(sum>sumR){
                sumR=sum;
                ret_array[1]=i;//System.out.println("sumR; "+sumR);
            }
        }
        ret_array[2]=sumL+sumR;
       // System.out.println( ret_array[0]+"; "+ ret_array[1]+"; "+ ret_array[2]);
        return ret_array;
    }
    
public static int ArrayMax (int[] A){
    int ret_val=A[0];
    for(int i=1;i<A.length;i++){
        if(A[i] > ret_val){
            ret_val=A[i];
        }
    }
    return ret_val;
}
public static double ArrayMax (double[] A){
    double ret_val=A[0];
    for(int i=1;i<A.length;i++){
        if(A[i] > ret_val){
            ret_val=A[i];
        }
    }
    return ret_val;
}
public static int ArrayMin (int[] A){
    int ret_val=A[0];
    for(int i=1;i<A.length;i++){
        if(A[i] < ret_val){
            ret_val=A[i];
        }
    }
    return ret_val;
}
public static double ArrayMin (double[] A){
    double ret_val=A[0];
    for(int i=1;i<A.length;i++){
        if(A[i] < ret_val){
            ret_val=A[i];
        }
    }
    return ret_val;
}
public static void  PrintArray(double[] B, int mode){
    // mode; 0 for by index ,1 for by level binary tree
    switch (mode){
        case 0:
            for (int i=0;i<B.length;i++){
                 System.out.print(" "+B[i]);
            }break;
     case 1:
         for (int i =0;i<B.length;i++){
             if (i==0 || Math.log(i)/Math.log(2)==(int)(Math.log(i)/Math.log(2)) ){
                 System.out.println();
                 System.out.print(i/2+":");
             }
             System.out.print(" "+B[i]);
         }break;
     default:
         ;
    }
}
public static void  PrintArray(int[] B, int mode){
    // mode; 0 for by index ,1 for by level binary tree
    switch (mode){
        case 0:
            for (int i=0;i<B.length;i++){
                 System.out.print(" "+B[i]);
            } 
            System.out.println();
            break;
     case 1:
         for (int i =0;i<B.length;i++){
             if (i==0 || Math.log(i)/Math.log(2)==(int)(Math.log(i)/Math.log(2)) ){
                 System.out.println();
                 System.out.print(i/2+": ");
             }
             System.out.print(" "+B[i]);
         }break;
     default:
         ;
    }
}


public static class findDistance{
    
	
    public static int findShortestDistance(){
         int[] A={ 1,4,7,9 };
         int[] B={-3,6,9,55};
         int[] C={2,5,44};
        return  findShortestDistance( A, B,  C);
    }
    public static int findallShortestDistance( ){
         int[] A={ 1,4,7,9 };
         int[] B={-3,6,9,55};
        int[] C={2,5,44};
       return findallShortestDistance(A,B, C);
    }
    public static int findShortestDistance(int[] A, int[] B, int[] C){
	if( A==null || B==null  || A.length==0 || B.length==0){
                System.out.println("wrong");
                System.exit(1);
        }
        int lenA=A.length;
        int lenB=B.length;
        int lenC=C.length;

        int i =0;
        int j =0;
        int k=0;
        int min=Integer.MAX_VALUE;

        for (;i<lenA;i++){
            for(j =0;j <lenB;j++){
                if( Math.abs(A[i]-B[j])<min ){
                    for(k=0;k<lenC;k++){   
                        if ( ( A[i]>=B[j] && A[i]>=C[k] && B[j]<=C[k]) || ( A[i]<=B[j] && A[i]<=C[k] && B[j]>=C[k]) ){
                            min = Math.abs(A[i]-B[j]);
                            System.out.println(i+" "+j +" "+k+" ;"+ min); 
                            break;
                        }
                    }
                }
            }
        }
	if (min==Integer.MAX_VALUE) { 
        System.out.println("no C between A and B."); 
        return -1;
        }
        System.out.println(min);
        
    return min; 
    }
    
    public static int findallShortestDistance(int[] A, int[] B, int[] C){
        int AB=findShortestDistance(A,B,C);
        int BC=findShortestDistance(B,C,A);

        int CA=findShortestDistance(C,A,B);
        int min = Math.min( Math.min(AB, BC),CA);
        if ( min ==AB )
        System.out.print("shorest from AB;");
        else if ( min ==BC  )
        System.out.print("shorest from BC;");
        else 
        System.out.println("shorest from CA;");
        System.out.println(min);
        return min;
    }

}

}
